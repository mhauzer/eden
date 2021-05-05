package cognition.value

import scala.language.implicitConversions

class Volume(val value: Byte) extends AnyVal {
  override def toString: String = value.toString
}

object Volume {
  def Volume(v: Byte): Volume = new Volume(v)
  implicit def Volume2Byte(x: Volume): Byte = x.value
  implicit def Int2Volume(x: Int): Volume = Volume(x.toByte)
}