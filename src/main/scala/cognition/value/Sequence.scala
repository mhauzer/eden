package cognition.value

import scala.language.implicitConversions

class Sequence(val value: Integer) extends AnyVal

object Sequence {
  implicit def Sequence2Integer(x: Sequence): Integer = x.value
}
