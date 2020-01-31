package models

import scala.annotation.tailrec

case class Odds(numerator: Int, denominator: Int)

object Odds {

  var accumulatorList: List[Odds] = List()

  def addToList(odd: Odds): Unit = {
    accumulatorList = accumulatorList ++ List(odd)
  }

  def calculateOdds(listOfOdds: List[Odds]): Double = {
    @tailrec
    def calc(t: List[Odds], acc: Double): Double = {
      if (t.isEmpty) acc
      else calc(t.tail, roundAt2(calculateAsDecimal(t.head) * acc))
    }

    calc(listOfOdds, 1.0)
  }

  def calculateAsDecimal(f: Odds): Double = (f.numerator.toDouble + f.denominator.toDouble) / f.denominator.toDouble

  def roundAt(p: Int)(n: Double): Double = {
    val s = math pow(10, p)
    (math round n * s) / s
  }

  def roundAt2(n: Double): Double = roundAt(2)(n)

}