package model.action

import java.time.LocalDateTime

trait DateAction {
  def perform(): LocalDateTime
  def perform(date: LocalDateTime): LocalDateTime
}