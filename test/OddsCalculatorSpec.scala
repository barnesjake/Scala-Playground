import org.scalatest.{BeforeAndAfterEach, FreeSpecLike, Matchers}
import org.scalatestplus.play.guice.GuiceOneServerPerTest
import support.RichMatchers
import controllers.routes
import models.Odds
import models.Odds._

class OddsCalculatorSpec extends FreeSpecLike
  with RichMatchers
  with BeforeAndAfterEach
  with GuiceOneServerPerTest
  //  with WireMockSupport
  with Matchers {


  "controllers.PlaygroundController.oddsCalc() is loaded ok" in {

  }

  "addToList should create a list" in {
    accumulatorList shouldBe empty
    addToList(Odds(1, 1))
    accumulatorList shouldBe List(Odds(1, 1))
  }

  "calculateAsDecimal should convert" - {
    "1/2 to 0.5" in {
      Odds.calculateAsDecimal(Odds(1, 2)) shouldBe 1.5
    }
    "1/9 to 0.1111111111111111" in {
      Odds.calculateAsDecimal(Odds(1, 9)) shouldBe 1.1111111111111112
    }
  }

  "roundAt2 should round to 2 decimal places" - {
    "0.1111111111111111 shouldBe 0.11" in {
      Odds.roundAt2(0.1111111111111111) shouldBe 0.11
    }
    "0.1901 shouldBe 0.19" in {
      Odds.roundAt2(0.1901) shouldBe 0.19
    }
    "0.195 shouldBe 0.2" in {
      Odds.roundAt2(0.195) shouldBe 0.20
    }
    "0.199 shouldBe 0.2" in {
      Odds.roundAt2(0.199) shouldBe 0.20
    }
  }

  "calculateOdds should calculate accumulator odds to two decimal places from a list of Odds" - {
    "1/2, 1/9 = 1.6666666666666667" in {
      val odds = List(Odds(1, 2), Odds(1, 9))
      Odds.calculateOdds(odds) shouldBe 1.67
    }
    "1/2 = 1.5" in {
      Odds.calculateOdds(List(Odds(1, 2))) shouldBe 1.5
    }
    "4/9, 1/2 = 2.7" in {
      val odds = List(Odds(1, 2), Odds(4, 5))
      Odds.calculateOdds(odds) shouldBe 2.7
    }
    "6/4, 1/3, 8/11, 5/4" in {
      val odds = List(
        Odds(6, 4),
        Odds(1, 3),
        Odds(8, 11),
        Odds(5, 4)
      )
      Odds.calculateOdds(odds) shouldBe 12.94
    }
  }

}
