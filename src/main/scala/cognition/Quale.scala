package cognition

// https://plato.stanford.edu/entries/qualia/
abstract class Quale(
                      val streamId: Int = 0,
                      val level: Byte = Quale.Low,
                      val variance: Byte = Quale.Low,
                      val ttl: Byte = Quale.High
                    ) {
  override def toString: String = s"streamId=$streamId, level=$level, variance=$variance, ttl=$ttl"
}

object Quale {
  val Low: Byte = -0x80
  val Medium: Byte = 0
  val High: Byte = 0x7F
}