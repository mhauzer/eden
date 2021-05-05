package cognition

import cognition.Idea._
import cognition.value.{Sequence, Ttl}

import scala.collection.immutable.HashSet

// https://link.springer.com/article/10.1007/s12559-017-9538-5
sealed case class Fcu(
                       seq: Sequence = new Sequence(),
                       idea: Idea = Idea.NULL,
                       quale: Quale = Quale.Empty,
                       associations: HashSet[Fcu] = HashSet(),
                       ttl: Ttl = Fcu.TtlMedium,
                       call: Boolean = false
                     ) {
  Clock.synchronize(seq.value)

  def degrade(): Fcu = copy(ttl = if (ttl > Fcu.TtlLow) new Ttl((ttl - 1).toByte) else Fcu.TtlLow)

  def isEmpty: Boolean = idea == Idea.NULL

  def nonEmpty: Boolean = !isEmpty

  def +(f: Fcu): Fcu = copy(seq = new Sequence(), quale = quale + f.quale, associations = associations.concat(f.associations))

  def nextTo(f: Fcu): Boolean = seq.nextTo(f.seq)

  override def toString: String = s"\nFcu(seq=$seq, $idea, $quale, associations=[${associations mkString ", "}], ttl=$ttl, call=$call)\n"
}

object Fcu {
  val TtlLow: Ttl = new Ttl(0)
  val TtlMedium: Ttl = new Ttl(3)
  val TtlHigh: Ttl = new Ttl(6)
  val Empty: Fcu = Fcu()
}