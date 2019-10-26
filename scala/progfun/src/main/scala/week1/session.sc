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



