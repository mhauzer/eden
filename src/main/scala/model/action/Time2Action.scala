package model.action

import nlp.PhraseGenerator

import java.text.SimpleDateFormat
import java.time.{LocalDateTime, ZoneId}
import java.util.Date

class Time2Action extends DateAction {

//  override def perform(date: LocalDateTime): String = {
//    PhraseGenerator.sayHowLongAgoItWas(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()), new Date())
//  }

  override def perform(): LocalDateTime = LocalDateTime.now

  override def perform(date: LocalDateTime): LocalDateTime = date
}
