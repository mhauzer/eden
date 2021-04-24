package cognition

import cognition.Idea._
import cognition.value.{Sequence, Ttl}

import scala.collection.immutable.HashSet

// https://link.springer.com/article/10.1007/s12559-017-9538-5
// todo: I want to have "quale: Quale[String]" here
case class Fcu(seq: Sequence = 0, idea: Idea = Idea.NULL, quale: Quale = Quale.Empty, associations: HashSet[Fcu] = HashSet(), ttl: Ttl = Fcu.TtlMedium) {
  def degrade(): Fcu = Fcu(
      seq,
      idea,
      quale.degrade,
      associations,
      if (ttl > Fcu.TtlLow) new Ttl((ttl - 1).toByte) else Fcu.TtlLow
    )

  def empty: Boolean = idea == Idea.NULL
  def nonEmpty: Boolean = !empty
  def + (f: Fcu): Fcu = copy(seq = new Sequence(), quale = quale + f.quale, associations = associations.concat(f.associations))
  def nextTo(f: Fcu): Boolean = seq.nextTo(f.seq)
  override def toString: String = s"Fcu(seq=$seq, $idea, $quale, associations=[${associations mkString ", "}], ttl=$ttl)"
}

object Fcu {
  val TtlLow: Ttl = new Ttl(0)
  val TtlMedium: Ttl = new Ttl(3)
  val TtlHigh: Ttl = new Ttl(6)
  val Empty: Fcu = Fcu()
}