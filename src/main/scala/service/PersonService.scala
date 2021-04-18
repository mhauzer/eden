package service

import model.person.Person
import nlp.Lexicon

class PersonService(val gender: String) {
  val person: Person = new Person(Lexicon.getRandomName(gender), gender)
}
