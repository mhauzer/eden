package cognition

import cognition.Idea._
import cognition.value.{Sequence, Ttl}

// https://link.springer.com/article/10.1007/s12559-017-9538-5
// todo: I want to have "quale: Quale[String]" here
case class Fcu(seq: Sequence, idea: Idea, quale: TextPseudoVisionQuale = null, associations: List[Fcu] = Nil, ttl: Ttl = Fcu.TtlMedium) {
  def degrade(): Fcu = Fcu(
      seq,
      idea,
      if (quale != null) quale.degrade else quale,
      associations,
      if (ttl > Fcu.TtlLow) new Ttl((ttl - 1).toByte) else Fcu.TtlLow
    )

  override def toString: String = s"(seq=$seq, $idea, $quale, associations=[${associations mkString ", "}], ttl=$ttl)"
}

object Fcu {
  val TtlLow: Ttl = new Ttl(0)
  val TtlMedium: Ttl = new Ttl(3)
  val TtlHigh: Ttl = new Ttl(6)
}