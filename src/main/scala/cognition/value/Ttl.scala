package cognition.value

import scala.language.implicitConversions

class Ttl(val value: Byte) extends AnyVal {
  override def toString: String = value.toString
}

object Ttl {
  def Ttl(v: Byte): Ttl = new Ttl(v)
  implicit def Ttl2Byte(x: Ttl): Byte = x.value
  implicit def Integer2Ttl(x: Int): Ttl = Ttl(x.toByte)
}