package cognition

import cognition.SensoryType.{SensoryType, Unknown}
import cognition.value.{Density, Volume}
import cognition.value.Volume.Volume
import cognition.value.Density.Density

// https://plato.stanford.edu/entries/qualia/
sealed case class Quale(
                  streamId: Int = 0,
                  volume: Volume = Quale.VolumeLow,
                  density: Density = Quale.DensityLow, // ~ frequency, entropy?
                  variance: Byte = Quale.VarianceLow,
                  value: String = "",
                  sense: SensoryType = SensoryType.Unknown
                ) {
  def + (q: Quale): Quale = copy(value = value + q.value)

  override def toString: String = s"\n\tQuale(streamId=$streamId, volume=$volume, variance=$variance, value=$value, sense=$sense)\n"
}

object Quale {
  val VolumeLow: Volume = -0x80
  val VolumeMedium: Volume = 0
  val VolumeHigh: Volume = 0x7F

  val DensityLow: Density = -0x80
  val DensityMedium: Density = 0
  val DensityHigh: Density = 0x7F

  val VarianceLow: Byte = -0x80
  val VarianceMedium: Byte = 0
  val VarianceHigh: Byte = 0x7F

  val Empty: Quale = Quale(sense = Unknown)
}