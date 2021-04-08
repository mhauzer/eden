package model.action

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

import model.PhraseGenerator

class Time2Action extends Action {
  override def perform(input: String): String = {
    PhraseGenerator.sayHowLongAgoItWas(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-20"), new Date()) + "\n" +
    PhraseGenerator.sayHowLongAgoItWas(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse("2021-04-06 08:00"), new Date(), "future")
  }

}
