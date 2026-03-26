package it.unibo.pps.tasks.adts

/*  Exercise 1: 
 *  Complete the implementation of ComplexADT trait below, so that it passes
 *  the test in ComplexTest.
 */

object Ex1ComplexNumbers:

  trait ComplexADT:
    type Complex

    def complex(re: Double, im: Double): Complex

    extension (complex: Complex)
      def re(): Double
      def im(): Double
      def sum(other: Complex): Complex
      def subtract(other: Complex): Complex
      def asString(): String

  object BasicComplexADT extends ComplexADT:

    // Change assignment below: should probably define a case class and use it?
    opaque type Complex = (Double, Double)

    def complex(re: Double, im: Double): Complex = (re, im)

    extension (c: Complex)
      def re(): Double = c._1
      def im(): Double = c._2
      def sum(other: Complex): Complex = complex(c.re() + other.re(), c.im() + other.im())
      def subtract(other: Complex): Complex = c.sum(-other.re(), -other.im())
      def asString(): String =
        val imFormat: Double => String = im => s"${im}i"
        c match
          case (re, 0) => s"$re"
          case (0, im) => imFormat(im)
          case (re, im) => s"$re ${if im > 0 then "+" else "-"} ${imFormat(math.abs(im))}"
