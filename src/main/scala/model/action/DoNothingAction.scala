package model.action

class DoNothingAction() extends Action {
  override def perform(input: String): String =
    "..."
}
