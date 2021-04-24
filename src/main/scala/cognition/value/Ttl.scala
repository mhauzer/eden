package cognition.value

import scala.language.implicitConversions

class Ttl(val value: Byte) extends AnyVal {
  override def toString: String = value.toString
}

object Ttl {
  implicit def Ttl2Byte(x: Ttl): Byte = x.value
}