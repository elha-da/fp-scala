package io.tabmo.fp.typeclass

object Typeclass extends App {

  object json {
    implicit val showablePerson = new GenericShowable[Person] {
      override def stringify(instance: Person): String = {
        s"""
           |{
           | "firstname": "${instance.firstname}",
           | "lastname": "${instance.lastname}",
           | "age": ${instance.age}
           |}
        """.stripMargin
      }
    }
  }

  object xml {
    implicit val showablePerson = new GenericShowable[Person] {
      override def stringify(instance: Person): String = {
        s"""
           |<Person>
           |  <firstname>${instance.firstname}</firstname>
           |  <lastname>${instance.lastname}</lastname>
           |  <age>${instance.age}</age>
           |</Person>
        """.stripMargin
      }
    }
  }

  //GenericShow.show(Person("Romain", "Lecomte", 27))(json.showablePerson)
  //GenericShow.show(Person("Romain", "Lecomte", 27))(xml.showablePerson)

  {
    import json._
    import syntax._
    //GenericShow.show(Person("Romain", "Lecomte", 27))
    Person("Romain", "Lecomte", 27).show()
  }

  {
    import xml._
    import syntax._
    //GenericShow.show(Person("Romain", "Lecomte", 27))
    Person("Romain", "Lecomte", 27).show()
  }
}

object GenericShow {
  def show[A](instance: A)(implicit showable: GenericShowable[A]) = println(showable.stringify(instance))
}

trait GenericShowable[A] {
  def stringify(a: A): String
}

