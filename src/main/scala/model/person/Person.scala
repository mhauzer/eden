package model.person

import java.text.SimpleDateFormat
import java.util.Date

import model.{Lexicon, PhraseGenerator, Sentence, SentencePurposes}

class Person(n: String, g: String) {
  val gender: String = g
  val name: String = n
}
