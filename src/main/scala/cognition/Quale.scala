package cognition

import cognition.value.{Volume, Ttl}

// https://plato.stanford.edu/entries/qualia/
abstract class Quale(
                      val streamId: Int = 0,
                      val volume: Volume = Quale.VolumeLow,
                      val variance: Byte = Quale.VarianceLow,
                      val ttl: Ttl = Quale.TtlHigh
                    ) {
  override def toString: String = s"streamId=$streamId, level=$volume, variance=$variance, ttl=$ttl"
}

object Quale {
  val VolumeLow: Volume = new Volume(-0x80)
  val VolumeMedium: Volume = new Volume(0)
  val VolumeHigh: Volume = new Volume(0x7F)

  val TtlLow: Ttl = new Ttl(-0x80)
  val TtlMedium: Ttl = new Ttl(0)
  val TtlHigh: Ttl = new Ttl(0x7F)

  val VarianceLow: Byte = -0x80
  val VarianceMedium: Byte = 0
  val VarianceHigh: Byte = 0x7F
}