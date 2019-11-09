def sqrt(x: Double) = {

  def abs(v: Double) =
    if (v < 0) { v * -1 } else v

  def isGoodEnough(guess: Double) = {
    val diff = abs((guess * guess) - x)
    (diff / x) < 0.001
  }

  def improve(guess: Double): Double = {
    (guess + (x / guess)) / 2
  }

  @scala.annotation.tailrec
  def sqrtIter(guess: Double):Double = {
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))
  }

  sqrtIter(1d)
}


def factorial(x: Int): Int = {

  @scala.annotation.tailrec
  def factorialIter(acc: Int, x: Int): Int = {
    if(x <= 1) acc
    else factorialIter(acc * x, x - 1)
  }

  factorialIter(1, x)
}

factorial(5)



def pascal(r: Int, c: Int): Int = {
  (r, c) match {
    case (_, 0) => 1
    case (r, c) if (r == c) => 1
    case (r, c) => pascal(r - 1, c - 1) + pascal(r - 1, c)
  }
}

def balance(chars: List[Char]): Boolean = {

  def isOpenParen(ch: Char) = ch == '('
  def isClosedParen(ch: Char) = ch == ')'

  def balancedCount(chars: List[Char], diff: Int = 0): Int = {
    chars match {
      case Nil => diff
      case ch :: _  if isOpenParen(ch) => balancedCount(chars.tail, diff + 1)
      case ch :: _ if isClosedParen(ch) => balancedCount(chars.tail, diff - 1)
      case _ => balancedCount(chars.tail, diff)
    }
  }

  balancedCount(chars, 0) match {
    case 0 => true
    case _ => false
  }
}

def countChange(money: Int, coins: List[Int]): Int = {
  if (money < 0 || coins == Nil)
    0
  else if (money == 0)
    1
  else
    countChange(money - coins.head, coins) + countChange(money, coins.tail)
}




