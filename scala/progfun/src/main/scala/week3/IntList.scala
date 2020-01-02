package week3

trait IntList
class Cons(val head: Int, val tail: IntList) extends IntList
object Nil extends IntList
