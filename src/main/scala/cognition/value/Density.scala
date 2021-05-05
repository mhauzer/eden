package cognition.value

import scala.language.implicitConversions

class Density(val value: Byte) extends AnyVal {
  override def toString: String = value.toString
}

object Density {
  def Density(v: Byte): Density = new Density(v)
  implicit def Density2Byte(x: Density): Byte = x.value
  implicit def Int2Density(x: Int): Density = Density(x.toByte)
}