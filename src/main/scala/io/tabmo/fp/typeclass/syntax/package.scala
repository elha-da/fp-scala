package io.tabmo.fp.typeclass

package object syntax {

  implicit class GenericShowOps[A](instance: A)(implicit gs: GenericShowable[A]) {
    def show() = GenericShow.show(instance)
  }
}
