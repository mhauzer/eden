package model.action

import java.time.LocalDateTime

class CurrentDateAction extends DateAction {
  override def perform(): LocalDateTime = LocalDateTime.now
  override def perform(date: LocalDateTime): LocalDateTime = date
}
