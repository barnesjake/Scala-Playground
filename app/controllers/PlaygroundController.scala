package controllers

import javax.inject._
import models.{Odds, PalindromeChecker, VowelCounter}
import play.api._
import play.api.mvc._

@Singleton
class PlaygroundController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def oddsCalc(): Action[AnyContent] = Action {
    Ok(views.html.oddsCalculator())
  }

  def calculateAccumulator(): Action[Map[String, Seq[String]]] = Action(parse.formUrlEncoded) {
    implicit request =>
      val listOfFractions = request.body("numerator").zip(request.body("denominator"))
      val asOdds = listOfFractions.map(x => Odds(x._1.toInt,x._2.toInt)).toList
      Ok(Odds.calculateOdds(asOdds).toString)
  }

  def palindromeChecker(): Action[AnyContent] = Action {
    Ok(views.html.exercises.palindromeChecker())
  }

  def palindromeCheck(): Action[AnyContent] = Action {
    implicit request =>
      Ok({PalindromeChecker.printIsPalindrome(request.body.asText.toString)})
  }

  def vowelCounterPage(): Action[AnyContent] = Action {
    Ok(views.html.exercises.vowelCounter())
  }

  def countVowels(): Action[Map[String, Seq[String]]] = Action(parse.formUrlEncoded) {
    implicit request =>
      val body: String = request.body("word").mkString
      Ok(s"Word: $body\n" + VowelCounter.countVowels(body))
  }

}
