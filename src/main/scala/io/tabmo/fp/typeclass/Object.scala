package io.tabmo.fp.typeclass

object Object extends App {

  trait PersonStringifyJson extends Showable {
    self: Person =>
    def stringify(): String = {
      s"""
         |{
         | "firstname": "$firstname",
         | "lastname": "$lastname",
         | "age": $age
         |}
      """.stripMargin
    }
  }

  trait PersonStringifyXml extends Showable {
    self: Person =>
    def stringify(): String = {
      s"""
         |<Person>
         |  <firstname>$firstname</firstname>
         |  <lastname>$lastname</lastname>
         |  <age>$age</age>
         |</Person>
      """.stripMargin
    }
  }

  def jsonPerson(firstname: String, lastname: String, age: Int) = new Person(firstname, lastname, age) with PersonStringifyJson

  def xmlPerson(firstname: String, lastname: String, age: Int) = new Person(firstname, lastname, age) with PersonStringifyXml

  Show.show(jsonPerson("Romain", "Lecomte", 27))
  Show.show(xmlPerson("Romain", "Lecomte", 27))
}

object Show {

  /*
    The show method take an instance of showable to print it in console
   */
  def show(showable: Showable) = println(showable.stringify())
}

trait Showable {
  def stringify(): String
}



