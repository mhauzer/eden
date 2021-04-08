package service

import model.Lexicon
import model.person.Person

class PersonService(val gender: String) {
  private val person: Person = new Person(Lexicon.getRandomName(gender), gender)
  def getPerson: Person = person
}
