package cognition.value

import cognition.Clock

import scala.language.implicitConversions

case class Sequence(value: Integer = Clock.get()) extends AnyVal {
  def nextTo(s: Sequence): Boolean = value + 1 == s.value
  override def toString: String = value.toString
}

object Sequence {
  implicit def Sequence2Integer(x: Sequence): Integer = x.value
  implicit def Integer2Sequence(i: Int): Sequence = new Sequence(i)
}
