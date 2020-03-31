import models.VowelCounter
import org.scalatest.{BeforeAndAfterEach, FreeSpecLike, Matchers}
import org.scalatestplus.play.guice.GuiceOneServerPerTest
import support.RichMatchers

class VowelCountSpec extends FreeSpecLike
  with RichMatchers
  with BeforeAndAfterEach
  with GuiceOneServerPerTest
  //  with WireMockSupport
  with Matchers {

  val word1 = "a"
  val word2 = "abc"
  val word3 = "aaaa"
  val word4 = "aui"
  "vowel check should count correctly in" in {
    VowelCounter.countVowels(word1) shouldBe 1
    VowelCounter.countVowels(word2) shouldBe 1
    VowelCounter.countVowels(word3) shouldBe 4
    VowelCounter.countVowels(word4) shouldBe 3
  }
}