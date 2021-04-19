package cognition

import cognition.Idea._

// https://link.springer.com/article/10.1007/s12559-017-9538-5
// todo: I want to have "quale: Quale[String]" here
case class Fcu(seq: Integer, idea: Idea, quale: TextPseudoVisionQuale = null, associations: List[Fcu] = Nil, ttl: Byte = Fcu.Medium) {
  def degrade(): Fcu = Fcu(
      seq,
      idea,
      if (quale != null) quale.degrade else quale,
      associations,
      if (ttl > Fcu.Low) (ttl - 1).toByte else Fcu.Low
    )

  override def toString: String = s"(seq=$seq, $idea, $quale, associations=[${associations mkString ", "}], ttl=$ttl)"
}

object Fcu {
  val Low: Byte = 0
  val Medium: Byte = 3
  val High: Byte = 6
}