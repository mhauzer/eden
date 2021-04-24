package cognition

import cognition.WorkingMemory.{deriveQuickAssociations, isReflexEligible}
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
  println(toString)

  def process(): WorkingMemory = {
    // interpretation of strings as words that have meaning - for optimisation the parser generates complete morphologic information
    new WorkingMemory(
      WorkingMemory.reflex(
        WorkingMemory.reason(
          WorkingMemory.reflex(
            WorkingMemory.degrade(fcus) ::: fcus.filter(isReflexEligible).map(deriveQuickAssociations).filter(_.nonEmpty)
          )
        ).map(deriveQuickAssociations).filter(_.nonEmpty)
      )
    )
  }

  def content: List[Fcu] = fcus

  override def toString: String = s"workingMemory=(${fcus mkString ", "})"
}

object WorkingMemory {
  val ReflexTtlThreshold: Ttl = new Ttl(3)
  val ReflexLevelThreshold: Volume = new Volume((Quale.VolumeLow.toByte + 1).toByte)

  private def isReflexEligible(f: Fcu): Boolean =
    f.quale.volume > WorkingMemory.ReflexLevelThreshold && f.quale.ttl >= WorkingMemory.ReflexTtlThreshold

  private def deriveQuickAssociations(f: Fcu): Fcu =
    f match {
      case Fcu(_, Idea.ENTITY, Quale(_, _, _, _, "hej", SensoryType.TextPseudoVision), _, _) => Fcu(1, Idea.GREETINGS)
      case _ => Fcu.Empty.copy()
    }

  private def reflex(fcus: List[Fcu]): List[Fcu] =
    if (fcus.filter(_.ttl >= ReflexTtlThreshold).map(_.idea).contains(Idea.GREETINGS))
      Fcu(new Sequence(Clock.get()), Idea.GREETINGS) :: degrade(fcus)
    else
      degrade(fcus)

  def isAvailableForReasoning(f: Fcu): Boolean = f.ttl > Fcu.TtlLow

  // proximity at the same variance means a complex
  private def reason(fcus: List[Fcu]): List[Fcu] = {
    var complexes = List[Fcu]()
    var complex: Fcu = Fcu.Empty.copy()
    for (i <- fcus.indices) {
      if (fcus(i).quale.variance > Quale.VarianceLow)
        if (complex.empty) {
          complex = Fcu(new Sequence(Clock.get()), Idea.ENTITY, fcus(i).quale.copy(ttl = ReflexTtlThreshold), HashSet(fcus(i)))
        } else {
          complex = complex + fcus(i)
        }
      else
        if (complex.nonEmpty) {
          complexes = complex.copy(seq = new Sequence) :: complexes
          complex = Fcu.Empty.copy()
        }
    }

    if (complex.nonEmpty) complexes ::= complex.copy(seq = new Sequence)
    complexes ::: degrade(fcus)
  }

  private def degrade(fcus: List[Fcu]): List[Fcu] = fcus.map(_.degrade()).filter(_.ttl > 0)
}