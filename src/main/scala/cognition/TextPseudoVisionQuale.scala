package cognition

import cognition.value.{Ttl, Volume}

class TextPseudoVisionQuale(
                             val content: String,
                             streamId: Byte,
                             volume: Volume,
                             variance: Byte,
                             ttl: Ttl) extends Quale(streamId, volume, variance, ttl) {
  def degrade: TextPseudoVisionQuale =
    new TextPseudoVisionQuale(
      content,
      streamId,
      volume,
      variance,
      if (ttl > Quale.TtlLow) new Ttl((ttl.value - 1).toByte) else ttl
    )

  override def toString: String = s"textVisionQuale(${super.toString}, ${'"'}$content${'"'})"
}