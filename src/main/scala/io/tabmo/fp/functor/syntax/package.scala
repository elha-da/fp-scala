package io.tabmo.fp.functor

import io.tabmo.fp.functor.Functor.{Decoder, Encoder}

package object syntax {

  implicit class EncoderOps[A](instance: A)(implicit en: Encoder[A]) {
    def encode() = en.encode(instance)
  }

  implicit class DecoderOps(arr: Array[Char]) {
    def as[A](implicit de: Decoder[A]): A = de.decode(arr)
  }
}
