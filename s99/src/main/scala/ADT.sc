sealed trait Maybe[A]
case class Just[A](x: A) extends Maybe[A]
object None extends Maybe[Nothing]

def head[A](list: List[A]): Maybe[A] = {
  list match {
    case x :: xs => Just(x)
    case Nil => None[Nothing]
  }
}