package cognition

import cognition.SensoryType.{SensoryType, Unknown}
import cognition.value.{Ttl, Volume}

// https://plato.stanford.edu/entries/qualia/
case class Quale(
                  streamId: Int = 0,
                  volume: Volume = Quale.VolumeLow,
                  variance: Byte = Quale.VarianceLow,
                  ttl: Ttl = Quale.TtlHigh,
                  value: String = "",
                  sense: SensoryType
                ) {
  def degrade: Quale =
    copy(ttl = if (ttl > Quale.TtlLow) new Ttl((ttl.value - 1).toByte) else ttl, value = value, sense = sense)

  def + (q: Quale): Quale = copy(value = value + q.value)

  override def toString: String = s"Quale(streamId=$streamId, volume=$volume, variance=$variance, ttl=$ttl, value=$value, sense=$sense)"
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

  val Empty: Quale = Quale(sense = Unknown)
}