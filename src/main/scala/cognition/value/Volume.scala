package cognition.value

import scala.language.implicitConversions

class Volume(val value: Byte) extends AnyVal

object Volume {
  implicit def Volume2Byte(x: Volume): Byte = x.value
}