package cognition

class WorkingMemory(val fcus: List[Fcu]) {
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

//  println(toString)

  def process: WorkingMemory = {
    // interpretation of strings as words that have meaning - for optimisation the parser generates complete morpholigic information
    val greetings = new TextPseudoVisionQuale("hej", 0, Quale.Medium, Quale.Medium, 3)
    val wordSimpleMeanings = fcus
        .filter(
          f => f.idea == Idea.ENTITY
            && f.quale.content == greetings.content
            && f.quale.level > Quale.Low
            && f.quale.ttl >= WorkingMemory.ReflexTreshold
        ).map(f => Fcu(f.quale match { case greetings => Idea.GREETINGS }, null, Nil))
    new WorkingMemory(WorkingMemory.reflex(WorkingMemory.degrade(fcus) ::: wordSimpleMeanings))
  }

  override def toString: String = s"workingMemory=(${fcus mkString ", "})"
}

object WorkingMemory {
  val ReflexTreshold = 3

  def reflex(fcus: List[Fcu]): List[Fcu] =
    if (fcus.filter(_.ttl >= ReflexTreshold).map(_.idea).contains(Idea.GREETINGS)) Fcu(Idea.GREETINGS) :: degrade(fcus) else degrade(fcus)

  def degrade(fcus: List[Fcu]): List[Fcu] = fcus.map(_.degrade()).filter(_.ttl > 0)
}