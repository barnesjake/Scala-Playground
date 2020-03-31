package models

object VowelCounter {
  def printMessage(word: String): String = s"Vowels: ${countVowels(word.toLowerCase)}"

  val listOfVowels: Seq[Char] = Seq('a', 'e', 'i', 'o', 'u')

  def countVowels(word: String): Int = {

    val arr: Seq[Char] = word.toCharArray.toSeq

    def count(w: Seq[Char], acc: Int): Int = {
      if (w.isEmpty) acc
      else if (isAVowel(w.head)) count(w.tail, acc + 1)
      else count(w.tail, acc)
    }

    def isAVowel(c: Char): Boolean = listOfVowels.contains(c)

    count(arr, 0)

  }

}
