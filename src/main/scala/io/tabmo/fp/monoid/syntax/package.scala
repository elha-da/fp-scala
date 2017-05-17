package io.tabmo.fp.monoid

package object syntax {

  implicit class MonoidOps[A](x: A)(implicit m: Monoid[A]) {
    def |+|(y: A) = m.append(x, y)
  }
}
