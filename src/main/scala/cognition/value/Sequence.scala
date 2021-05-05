package cognition.value

import cognition.Clock

import scala.language.implicitConversions

class Sequence(val value: Int = Clock.get()) extends AnyVal {
  def nextTo(s: Sequence): Boolean = value + 1 == s.value || value - 1 == s.value
  override def toString: String = value.toString
}

object Sequence {
  def Sequence(): Sequence = new Sequence()
  implicit def Sequence2Int(x: Sequence): Int = x.value
  implicit def Int2Sequence(i: Int): Sequence = new Sequence(i)
}
