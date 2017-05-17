package io.tabmo.fp.monoid

object Monoid {
  //summon
  def apply[A](implicit monoid: Monoid[A]): Monoid[A] = monoid

  def combineAll[A](seq: Seq[A])(implicit monoid: Monoid[A]) = seq.foldLeft(monoid.zero) { case (acc, x) => monoid.append(acc, x) }
}

trait Monoid[A] {
  def zero: A

  def append(x: A, y: A): A
}
