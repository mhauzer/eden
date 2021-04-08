package model.action

trait Action {
  def perform(input: String): String
}
