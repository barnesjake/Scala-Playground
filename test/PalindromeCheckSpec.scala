import org.scalatest.{BeforeAndAfterEach, FreeSpecLike, Matchers}
import org.scalatestplus.play.guice.GuiceOneServerPerTest
import support.RichMatchers
import models.PalindromeChecker._

class PalindromeCheckSpec extends FreeSpecLike
  with RichMatchers
  with BeforeAndAfterEach
  with GuiceOneServerPerTest
  //  with WireMockSupport
  with Matchers {

  val listOfPalindromes =
    List(
      "ALULA",
      "ANANA",
      "CIVIC",
      "LEVEL",
      "MADAM",
      "RADAR",
      "ROTOR",
      "ROTATOR"
    )

  "palindrome check should return correct boolean" in {
    listOfPalindromes.foreach { x =>
      isPalindrome(x) should be(true)
    }
  }

}
