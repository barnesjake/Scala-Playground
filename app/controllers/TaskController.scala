package controllers

import models.Task

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class TaskController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Redirect(routes.TaskController.tasks())
  }

  def tasks: Action[AnyContent] = Action {
    Ok(views.html.taskTracker(Task.all))
  }

  def newTask: Action[Map[String, Seq[String]]] = Action(parse.formUrlEncoded) {
    implicit request =>
      Task.add(request.body("taskName").head)
      Redirect(routes.TaskController.index())
  }

  def deleteTask(id: Int): Action[AnyContent] = Action {
    Task.delete(id)
    Ok(views.html.taskTracker(Task.all))
  }
}
