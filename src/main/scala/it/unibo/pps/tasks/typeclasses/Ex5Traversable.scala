package it.unibo.pps.tasks.typeclasses

import it.unibo.pps.u03.Sequences.Sequence, Sequence.*
import it.unibo.pps.u03.Optionals.Optional, Optional.*

/*  Exercise 5:
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others...
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A]
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  trait Traversable[T[_]]:
    def forEach[A](t: T[A])(using consume: A => Unit): Unit

  given Traversable[Sequence] with
    def forEach[A](t: Sequence[A])(using consume: A => Unit): Unit = t match
      case Cons(h, t) => consume(h); forEach(t)
      case _ => ()

  given Traversable[Optional] with
    def forEach[A](t: Optional[A])(using consume: A => Unit): Unit = t match
      case Optional.Just(a) => consume(a)
      case _ => ()

  trait Logger:
    def log[A](a: A): Unit

  object BasicLogger extends Logger:
    def log[A](a: A): Unit = println(a)

  object AdvancedLogger extends Logger:
    def log[A](a: A): Unit = println("The next element is: " + a)

  def logAll[T[_] : Traversable, A](t: T[A])(using logger: Logger): Unit = {
    summon[Traversable[T]].forEach(t)(using logger.log)
  }

  @main def testTraversable(): Unit =
    val s: Sequence[Int] = Cons(1, Cons(2, Nil()))
    given Logger = AdvancedLogger
    logAll(s)
    val opt: Optional[Int] = Optional.Just(1)
    logAll(opt)
    logAll(opt)(using logger = BasicLogger)
