package io.tabmo.fp.monoid

object IntMonoid extends App{

  object sum {
    implicit val monoidInt = new Monoid[Int] {
      override def zero: Int = 0

      override def append(x: Int, y: Int): Int = x + y
    }
  }

  object mul {
    implicit val monoidInt = new Monoid[Int] {
      override def zero: Int = 1

      override def append(x: Int, y: Int): Int = x * y
    }
  }

  {
    import sum._
    import syntax._
    println(1 |+| 2)
  }

  {
    import mul._
    import syntax._
    println(2 |+| 2)
  }

  object fun1 {
    implicit def function1[A, B](implicit monoid: Monoid[B]): Monoid[A => B] = new Monoid[(A) => B] {
      override def append(x: (A) => B, y: (A) => B): (A) => B = {
        (a: A) => monoid.append(x(a), y(a))
      }

      override def zero: (A) => B = (a: A) => monoid.zero
    }
  }

  {
    import sum._
    import fun1._
    import syntax._

    val inc = (x: Int) => x + 1
    val double = (x: Int) => x * 2

    val f = inc |+| double
    println(f(1))
  }
}
