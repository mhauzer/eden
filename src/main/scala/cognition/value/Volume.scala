package cognition.value

import scala.language.implicitConversions

class Volume(val value: Byte) extends AnyVal {
  override def toString: String = value.toString
}

object Volume {
  implicit def Volume2Byte(x: Volume): Byte = x.value
}