package controllers

import javax.inject._
import models.Artist
import play.api._
import play.api.libs.json.JsValue
import play.api.mvc._

@Singleton
class ActionExerciseController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def listArtist = Action {
    Ok(views.html.artists(Artist.fetch))
  }

  def fetchByName(name: String) = Action {
    Ok(views.html.artists(Artist.fetchByName(name)))
  }

  def fetchByCountry(country: String) = Action {
    Ok(views.html.artists(Artist.fetchByCountry(country)))
  }

  def search(name: String, country: String) = Action {
    val result = Artist.fetchByNameOrCountry(name, country)
    if (result.isEmpty) NoContent
    else Ok(views.html.artists(result))
  }

  def search2(name: Option[String], country: String) = Action {
    val result = name match {
      case Some(n) => Artist.fetchByNameOrCountry(n, country)
      case None => Artist.fetchByCountry(country)
    }
    if (result.isEmpty) NoContent
    else Ok(views.html.artists(result))
  }

  //  def subscribe = Action {
  //    request => Ok("received " + request.body)
  //  }
  def subscribe: Action[AnyContent] = Action {
    request =>
      val requestBody: AnyContent = request.body
      val textContent: Option[String] = requestBody.asText
      textContent.map {
        emailId =>
          Ok("Added " + emailId + " to subscribers list")
      }.getOrElse {
        BadRequest("Improper request body")
      }
  }

  def subscribe2 = Action(parse.text) {
    request => Ok("Added " + request.body + " to subscribers list")
  }

  def subscribe3 = Action(parse.json) {
    request =>
      val reqData: JsValue = request.body
      val emailId = (reqData \ "emailId").as[String]
      val interval = (reqData \ "interval").as[String]
      Ok(s"Added $emailId to subscribers list and will send updates ever $interval")
  }



}
