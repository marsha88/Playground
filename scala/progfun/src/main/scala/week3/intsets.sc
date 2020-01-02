

// cannot be instantiated
abstract class IntSet {
  def incl(x:Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class TreeNode(value: Int, left: IntSet, right: IntSet) extends IntSet {
  def incl(x: Int): IntSet = {
    if(x > value)
      new TreeNode(value, left, right.incl(x))
    else if(x < value)
      new TreeNode(value, left.incl(x), right)
    else
      this
  }

  def contains(x: Int): Boolean = {
    if(value == x)
      true
    else
      left.contains(x) || right.contains(x)
  }

  def union(other: IntSet) =
    left.union(right).union(other).incl(value)

  override def toString = s"{ left: $left $value $right }"
}

object Empty extends IntSet {
  def incl(x: Int): IntSet = new TreeNode(x, Empty, Empty)
  def contains(x: Int): Boolean = false
  def union(other: IntSet) = other
  override def toString = "."
}


val tree = new TreeNode(5, Empty, Empty)
  .incl(4)
  .incl(6)

abstract class Base {
  def foo = 1
  def bar: Int
}

class Sub extends Base {
  override def foo = 2
  def bar = 3
}




