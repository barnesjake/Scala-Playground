package controllers

import javax.inject._
import models.Odds
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

}
