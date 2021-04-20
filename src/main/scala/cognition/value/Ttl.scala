package cognition.value

import scala.language.implicitConversions

class Ttl(val value: Byte) extends AnyVal

object Ttl {
  implicit def Ttl2Byte(x: Ttl): Byte = x.value
}