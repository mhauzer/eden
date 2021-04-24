package cognition.value

import scala.language.implicitConversions

case class Sequence(value: Integer) extends AnyVal {
  override def toString: String = value.toString
}

object Sequence {
  implicit def Sequence2Integer(x: Sequence): Integer = x.value
  implicit def Integer2Sequence(i: Int): Sequence = Sequence(i)
}
