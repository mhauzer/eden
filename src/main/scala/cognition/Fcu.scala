package cognition

import cognition.Idea._

// todo: I want to have "quale: Quale[String]" here
case class Fcu(idea: Idea, quale: TextPseudoVisionQuale = null, associations: List[Fcu] = Nil, ttl: Byte = Fcu.Medium) {
  def degrade(): Fcu = Fcu(
      idea,
      if (quale != null) quale.degrade else quale,
      associations,
      if (ttl > Fcu.Low) (ttl - 1).toByte else Fcu.Low
    )

  override def toString: String = s"($idea, $quale, associations=[${associations mkString ", "}], ttl=$ttl)"
}

object Fcu {
  val Low: Byte = 0
  val Medium: Byte = 3
  val High: Byte = 6
}