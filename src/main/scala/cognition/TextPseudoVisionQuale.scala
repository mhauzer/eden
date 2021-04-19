package cognition

class TextPseudoVisionQuale(
                             val content: String,
                             streamId: Byte,
                             volume: Byte,
                             variance: Byte,
                             ttl: Byte) extends Quale(streamId, volume, variance, ttl) {
  def degrade: TextPseudoVisionQuale =
    new TextPseudoVisionQuale(
      content,
      streamId,
      volume,
      variance,
      if (ttl > Quale.Low) (ttl - 1).toByte else ttl
    )

  override def toString: String = s"textVisionQuale(${super.toString}, ${'"'}$content${'"'})"
}