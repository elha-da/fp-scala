package io.tabmo.fp.transformer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FutureEither extends App {

  case class FutureEitherT[A](value: Future[Either[String, A]]) {
    def flatMap[B](f: A => FutureEitherT[B]): FutureEitherT[B] = {
      FutureEitherT(value.flatMap {
        case Left(err) => Future.successful(Left(err))
        case Right(x) => f(x).value
      })
    }

    def map[B](f: A => B): FutureEitherT[B] = {
      FutureEitherT(value.flatMap {
        case Left(err) => Future.successful(Left(err))
        case Right(x) => Future.successful(Right(f(x)))
      })
    }
  }

  //simple either
  {
    val result = for {
      a <- Right(1)
      b <- Right(2)
      //_ <- Left("Oops")
      c <- Right(3)
    } yield a + b + c

    println(result)
  }

  //EitherT reimplemented
  {
    import syntax._

    def futureEither(i: Int) = FutureEitherT(Future.successful(Right(i)))
    def leftFutureEither() = FutureEitherT(Future.successful(Left("Oops")))

    val result = for {
      a <- futureEither(1)
      b <- futureEither(2)
      //_ <- leftFutureEither()
      c <- futureEither(3)
      _ <- Future.successful(Right(3)).eitherT()
    } yield a + b + c

    result.value.map(println)
  }
}
