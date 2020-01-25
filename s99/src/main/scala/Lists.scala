object Lists {
  def last[A](list: List[A]): Option[A] = {
    list match {
      case x :: Nil => Some(x)
      case Nil => None
      case _ => last(list.tail)
    }
  }

  def penultimate[A](list: List[A]): Option[A] =  {
    list match {
      case _ :: Nil => None
      case Nil => None
      case x :: y :: Nil => Some(x)
      case x :: xs  => penultimate(xs)
    }
  }

  def nth[A](n: Int, list: List[A]): Option[A] = {
    if (n < 0)
      None
    else if (n == 0)
      list match {
        case x :: xs => Some(x)
        case Nil => None
      }
    else
      list match {
        case x :: xs => nth (n - 1, xs)
        case Nil => None
      }
  }

  def count[A](list: List[A]): Int = {
    def countIter(list:List [A], count: Int = 0): Int = {
      list match {
        case x :: xs => countIter(xs, count + 1)
        case Nil => count
      }
    }
    countIter(list, 0)
  }

  def reverse() = {

  }

  def palidrome() = {

  }

  /** Tests */
  def main(args: Array[String]) = {
    val intList = List(1,2,3,4,5)
    val stringList = List("hello")
    val nilList = Nil

    println("=> last")
    println(last(intList))
    println(last(stringList))
    println(last(nilList))

    println("\n=> penultimate")
    println(penultimate(intList))
    println(penultimate(stringList))
    println(penultimate(nilList))

    println("\n=> nth")
    println(nth(3, intList))
    println(nth(2, stringList))
    println(nth(0, nilList))
  }
}