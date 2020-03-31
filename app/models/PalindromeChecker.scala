package models

object PalindromeChecker {

  def isPalindrome(str: String): Boolean = str == str.reverse

  def printIsPalindrome(word: String): String = s"The word $word is a palindrome: ${isPalindrome(word)}"

}
