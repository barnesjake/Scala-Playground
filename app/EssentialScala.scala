import EssentialScala.Film

object EssentialScala {

  /**
    *   3.1.6.3 Directorial Debut
    */
  class Director(val firstName: String, val lastName: String, val yearOfBirth: Int) {
    def name: String = s"$firstName $lastName"

    def copy(
              firstName: String = this.firstName,
              lastName: String = this.lastName,
              yearOfBirth: Int = this.yearOfBirth): Director = new Director(firstName, lastName, yearOfBirth)
  }

  // 3.3.2.2
  object Director {
    def apply(firstName: String, lastName: String, yearOfBirth: Int): Director =
      new Director(firstName, lastName, yearOfBirth)

    def older(director1: Director, director2: Director): Director =
      if (director1.yearOfBirth < director2.yearOfBirth) director2
      else director1
  }

  class Film(val name: String, val yearOfRelease: Int, val imdbRating: Double, val director: Director) {
    def directorsAge: Int = 2020 - director.yearOfBirth

    def isDirectedBy(director: Director): Boolean = this.director == director

    def copy(name: String = this.name,
             yearOfRelease: Int = this.yearOfRelease,
             imdbRating: Double = this.imdbRating,
             director: Director = this.director): Film = new Film(name, yearOfRelease, imdbRating, director)
  }

  // 3.3.2.2
  object Film {
    def apply(name: String, yearOfRelease: Int, imdbRating: Double, director: Director): Film =
      new Film(name,yearOfRelease, imdbRating, director)

    def highestRating(film1: Film, film2: Film): Film =
      if (film1.imdbRating < film2.imdbRating) film2
      else film1

    def oldestDirectorAtTheTime(film1: Film, film2: Film): Director = {
      val ageAtTime1 = film1.yearOfRelease - film1.director.yearOfBirth
      val ageAtTime2 = film2.yearOfRelease - film2.director.yearOfBirth
      if (ageAtTime1 < ageAtTime2) film2.director
      else film1.director
    }
  }


  val eastwood = new Director(firstName = "Clint", lastName = "Eastwood", yearOfBirth = 1930)
  val mcTiernan = new Director(firstName = "John", lastName = "McTiernan", yearOfBirth = 1951)
  val nolan = new Director(firstName = "Christopher", lastName = "Nolan", yearOfBirth = 1970)
  val someBody = new Director(firstName = "Just", lastName = "Some Body", yearOfBirth = 1990)

  val memento = new Film(name = "Memento", yearOfRelease = 2000, imdbRating = 8.5, director = nolan)
  val darkKnight = new Film(name = "Dark Knight", yearOfRelease = 2008, imdbRating = 9.0, director = nolan)
  val inception = new Film(name = "Inception", yearOfRelease = 2010, imdbRating = 8.8, director = nolan)

  val highPlainsDrifter = new Film(name = "High Plains Drifter", yearOfRelease = 1973, imdbRating = 7.7, director = eastwood)
  val outlawJoseyWales = new Film(name = "The Outlaw Josey Wales", yearOfRelease = 1976, imdbRating = 7.9, director = eastwood)
  val unforgiven = new Film(name = "Unforgiven", yearOfRelease = 1992, imdbRating = 8.3, director = eastwood)
  val granTorino = new Film(name = "Gran Torino", yearOfRelease = 2008, imdbRating = 8.2, director = eastwood)
  val invictus = new Film(name = "Invictus", yearOfRelease = 2009, imdbRating = 7.4, director = eastwood)

  val predator = new Film(name = "Predator", yearOfRelease = 1987, imdbRating = 7.9, director = mcTiernan)
  val dieHard = new Film(name = "Die Hard", yearOfRelease = 1988, imdbRating = 8.3, director = mcTiernan)
  val huntForRedOctober = new Film(name = "The Hunt for Red October", yearOfRelease = 1990, imdbRating = 7.6, director = mcTiernan)
  val thomasCrownAffair = new Film(name = "The Thomas Crown Affair", yearOfRelease = 1999, imdbRating = 6.8, director = mcTiernan)


  /**
    *   3.1.6.4 Directorial Debut
    */
  class Counter(val count: Int) {
    def inc: Counter = new Counter(count + 1)

    def dec: Counter = new Counter(count - 1)

    //3.1.6.6 Additional Counting
    def adjust(add: Adder): Counter = new Counter(add(count))

    //3.1.6.5 Counting Faster
    def inc(n: Int = 1): Counter = new Counter(count + n)

    def dec(n: Int = 1): Counter = new Counter(count - n)
  }

  class Adder(amount: Int) {
    def apply(in: Int): Int = in + amount
  }


  /**
    *   3.3.2 Exercises
    */
//  case class Person(val firstName: String, val lastName: String) {
//    def name: String =
//      s"$firstName $lastName"
//  }
//
//  object Person {
//    def apply(wholeName: String): Person = {
//      val names = wholeName.split(" ")
//      new Person(names(0), names(1))
//    }
//  }

  /**
    *   3.4.5.1 Exercises
    */
  case class Cat(name: String, colour: String, food: String)

  case class CounterAlt(count: Int) {
    def inc: CounterAlt = copy(count = count + 1)
    def dec: CounterAlt = copy(count = count - 1)
  }

  /**
    *   3.4.5.4 Application, Application, Application
    */
  case class Person(firstName: String, lastName: String) {
    def name: String = s"$firstName $lastName"
  }
  object Person {
    def apply(wholeName: String): Person = {
      val names = wholeName.split(" ")
      new Person(names(0), names(1))
    }
  }

  object StormTrooper {
    def inspect(person: Person): String =
      person match {
        case Person("Luke", "Skywalker") => "Stop"
        case Person("Han", "Solo") => "Stop"
        case Person(first, last) => s"Move along, ${Person(first, last).name}"
      }
  }

  object ChipShop {
    def willServer(cat: Cat): Boolean =
      cat match {
        case Cat(_, _, "Chips") => true
        case _ => false
      }
  }

  /**
    *   4 Modelling data with traits
    */
  import java.util.Date
  final case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor
  final case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor

  sealed trait Visitor {
    def id: String
    def createdAt: Date
    def age: Long = new Date().getTime - createdAt.getTime
  }

  def older(v1: Visitor, v2: Visitor): Boolean = v1.createdAt.before(v2.createdAt)

}

object TraitsExercise extends App {
  sealed trait Feline {
    def colour: String
    def sound: String = "roar"
  }
  final case class Tiger(colour: String) extends Feline
  final case class Lion(colour: String, maneSize: Int) extends Feline
  final case class Panther(colour: String) extends Feline
  final case class Cat(colour: String, override val sound: String = "meow", food: String) extends Feline


  sealed trait Shape {
    def sides: Int
    def perimeter: Double
    def area: Double
  }

  sealed trait Rectangular extends Shape

  final case class Circle(radius: Double) extends Shape {
    val name = "circle"
    val sides: Int = 1
    val perimeter: Double = 2 * Math.PI * radius
    val area: Double = Math.PI * radius * radius
  }

  final case class Square(length: Double) extends Rectangular {
    val name = "square"
    val sides: Int = 4
    val perimeter: Double = length * sides
    val area: Double = length * length
  }

  final case class Rectangle(width: Double, height: Double) extends Rectangular {
    val name = "rectangle"
    val sides: Int = 4
    val perimeter: Double = width * 2 + height * 2
    val area: Double = width * height
  }

  object Draw {
    def apply(shape: Shape): String = {
       shape match {
         case Circle(r) => s"A circle with a radius of ${r}cm"
         case Square(l) => s"A square with a width of ${l}cm"
         case Rectangle(w, h) => s"A rectangle with a width of ${w}cm and a height of ${h}cm"
       }
    }
  }

  sealed trait Colour {
    def red: Double
    def green: Double
    def blue: Double
  }
  case class Red() extends Colour {
    val red: Double = 1
    val green: Double = 0
    val blue: Double = 0
  }
  case class Yellow() extends Colour {
    val red: Double = 0
    val green: Double = 0.5
    val blue: Double = 0.5
  }
  case class Pink() extends Colour {
    val red: Double = 0.5
    val green: Double = 0
    val blue: Double = 0
  }


  /** 4.5.6 Exercises
    * 4.5.6.1 Traffic lights */

//  sealed trait TrafficLight {
//    def next: TrafficLight
//  }
//  case object Red extends TrafficLight {
//    def next: TrafficLight = Green
//  }
//  case object Green extends TrafficLight {
//    def next: TrafficLight = Yellow
//  }
//  case object Yellow extends TrafficLight {
//    def next: TrafficLight = Red
//  }
  sealed trait TrafficLight {
    def next: TrafficLight = this match {
      case Red => Green
      case Yellow => Red
      case Green => Yellow
    }
  }
  case object Red extends TrafficLight
  case object Green extends TrafficLight
  case object Yellow extends TrafficLight

  object AListOfMethods extends App {
    sealed trait IntList {
      def length: Int =
        this match {
          case End => 0
          case Pair(hd, tl) => 1 + tl.length
      }
      def product: Double =
        this match {
          case End => 1
          case Pair(hd, tl) => hd * tl.product
        }
    }
    case object End extends IntList
    final case class Pair(head: Int, tail: IntList) extends IntList

    val example = Pair(1, Pair(2, Pair(3, End)))

    assert(example.length == 3)
    assert(example.tail.length == 2)
    assert(End.length == 0)

    assert(example.product == 6)
    assert(example.tail.product == 6)
    assert(End.product == 1)
  }



}