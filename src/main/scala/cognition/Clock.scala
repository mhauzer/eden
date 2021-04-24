package cognition

object Clock {
  private var value: Integer = 0

  def reset(): Unit = value = 0
  def get(): Integer = { value += 1; value }
}
