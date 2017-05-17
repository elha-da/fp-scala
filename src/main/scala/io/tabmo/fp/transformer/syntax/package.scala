package io.tabmo.fp.transformer

import scala.concurrent.Future

import io.tabmo.fp.transformer.FutureEither.FutureEitherT

package object syntax {

  implicit class FutureEitherOps[A](fe: Future[Either[String, A]]) {
    def eitherT() = FutureEitherT(fe)
  }
}
