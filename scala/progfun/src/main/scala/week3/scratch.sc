import scala.annotation.tailrec


def nth[T](n: Int, list: List[T]): T = {

  @tailrec
  def getNth(i: Int, xs: List[T]): T = {
    if (i < 0 || xs.isEmpty)
      throw new IndexOutOfBoundsException(s"$n")
    else if (i == 0)
      xs.head
    else
      getNth(i - 1, xs.tail)
  }

  getNth(n, list)
}

val l = List(1,2,3,4,5)

println(nth(3, l))
println(nth(-1, l))




