package model.action

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateAction extends Action {
  override def perform(input: String): String = LocalDateTime.now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}
