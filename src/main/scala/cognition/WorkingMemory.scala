package cognition

import cognition.WorkingMemory.degrade
import cognition.value.{Sequence, Ttl, Volume}

import scala.collection.immutable.HashSet

class WorkingMemory(fcus: List[Fcu]) {
  // reaction types:
  // - reflex based on a simple association (meme patterns associated with other meme patterns)
  // - planned actions based on purpose -> Working Memory transformation + injecting FCUs into WorkingMemory + forgetting
  // purpose can be set by a free will / creativity (randomly + statistics based on persistent memory)
  // learning = storing working memory in persistent memory + bringing the experience for planning to achieve a purpose
  // all mental activity can be modelled as a working memory transformation
  // external systems like will, emotions, etc can inject its results into the working memory
  //
  // working memory = awareness
  // focus is a function of processing FCUs in the working memory and itself can be represented as FCU
  // persistent memory = memory
  //
  // drive (values):
  // - maximizing qualia number in working memory (NOISEHEAD)
  // - minimizing qualia number in working memory (ZEN MASTER)
  // - maximizing good qualia in working memory (CONQUEROR)
  // - minimizing bad qualia in working memory (DEFENDER)
  //
  // Destiny = a process of trait setting (randomly)

  if (fcus.isEmpty) Clock.reset()

  def process(): WorkingMemory = {
    new WorkingMemory(
      WorkingMemory.taxonomize(fcus) ::: degrade(fcus)
//      WorkingMemory.reflex(
//        WorkingMemory.reason(
//          WorkingMemory.reflex(
//            WorkingMemory.degrade(fcus) ::: fcus.filter(isReflexEligible).map(deriveQuickAssociations).filter(_.nonEmpty)
//          ),
//          None,
//          Nil
//        ).map(deriveQuickAssociations).filter(_.nonEmpty)
//      )
    )
  }

  def content: List[Fcu] = fcus

  override def toString: String = s"workingMemory=(${fcus mkString ",\n"})"
}

object WorkingMemory {
  val ReflexTtlThreshold: Ttl = new Ttl(3)
  val ReflexLevelThreshold: Volume = new Volume((Quale.VolumeLow.toByte + 1).toByte)

  def taxonomize(fcus: List[Fcu]): List[Fcu] = fcus.map {
    case x if x.idea == Idea.QUALE && x.associations.isEmpty => x.copy(seq = new Sequence, idea = Idea.LETTER, associations = HashSet(x))
    case x => x
  }

  private def isReflexEligible(f: Fcu): Boolean =
    f.quale.volume > WorkingMemory.ReflexLevelThreshold && f.ttl >= WorkingMemory.ReflexTtlThreshold

  private def deriveQuickAssociations(f: Fcu): Fcu =
    f match {
      case Fcu(_, Idea.WORD, Quale(_, _, _, _, "hej", SensoryType.TextPseudoVision), _, _, _) => Fcu(idea = Idea.GREETINGS)
      case _ => f
    }

  private def reflex(fcus: List[Fcu]): List[Fcu] = fcus match {
    case Fcu(_, Idea.GREETINGS, _, _, ttl, _) :: fs if ttl >= ReflexTtlThreshold => Fcu(idea = Idea.GREETINGS, call = true) :: degrade(fcus)
    case _ => degrade(fcus)
  }

  def isAvailableForReasoning(f: Fcu): Boolean = f.ttl > Fcu.TtlLow

  // proximity with the same variance is interpreted as a complex
  private def reason(fcus: List[Fcu], complex: Option[Fcu], complexes: List[Fcu]): List[Fcu] = {
    if (fcus.isEmpty)
      if (complex.nonEmpty)
        complex.get :: complexes
      else
        complexes
    else if (fcus.head.quale.variance > Quale.VarianceLow)
      if (complex.isEmpty)
        fcus.head :: reason(
          fcus.tail,
          Option(
            Fcu(idea = Idea.WORD, quale = fcus.head.quale, associations = HashSet(fcus.head))
          ),
          complexes
        )
      else
        fcus.head :: reason(fcus.tail, Option(complex.get + fcus.head), complexes)
    else if (complex.nonEmpty)
      fcus.head :: reason(fcus.tail, None, complex.get.copy(seq = new Sequence) :: complexes)
    else
      fcus.head :: reason(fcus.tail, complex, complexes)
  }

  private def degrade(fcus: List[Fcu]): List[Fcu] = fcus.map(_.degrade()).filter(_.ttl > 0)
}