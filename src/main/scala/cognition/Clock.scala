package cognition

object Clock {
  def synchronize(value: Int): Unit = if (value > this.value + 1) this.value = value

  private var value: Int = 0

  def reset(): Unit = value = 0
  def get(): Int = { value += 1; value }
}
