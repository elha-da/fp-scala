package io.tabmo.fp.functor

object Functor extends App {

  //Variant
  trait Decoder[A] { self =>
    def decode(arr: Array[Char]): A

    def map[B](f: A => B) = new Decoder[B] {
      override def decode(arr: Array[Char]): B = {
        f(self.decode(arr))
      }
    }
  }

  //Contravariant
  trait Encoder[A] { self =>
    def encode(instance: A): Array[Char]

    def contramap[B](f: B => A) = new Encoder[B] {
      override def encode(instance: B): Array[Char] = {
        self.encode(f(instance))
      }
    }
  }

  //Invariant
  case class EncoderDecoder[A](decoder: Decoder[A], encoder: Encoder[A]) {
    def imap[B](fm: A => B)(fc: B => A) = new EncoderDecoder[B](decoder.map(fm), encoder.contramap(fc))
  }

  val charArray = "Red".toCharArray

  val stringEncoder = new Encoder[String] {
    override def encode(instance: String): Array[Char] = instance.toCharArray
  }

  val stringDecoder = new Decoder[String] {
    override def decode(arr: Array[Char]): String = new String(arr)
  }

  case class Person(name: String)

  implicit val personEncoder = stringEncoder.contramap[Person](_.name)
  implicit val personDecoder = stringDecoder.map(Person)

  import syntax._
  //println(personEncoder.encode(Person("Romain")).mkString("Array(", ",", ")"))
  println(Person("Romain").encode().mkString("Array(", ",", ")"))
  //println(personDecoder.decode("Romain".toCharArray))
  println("Romain".toCharArray.as[Person])
}
