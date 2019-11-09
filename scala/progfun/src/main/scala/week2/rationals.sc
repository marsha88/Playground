class Rational(x: Int, y: Int) {
  require(y != 0, "Denominator must be nonzero number")

  def numer = x
  def denom = y

  override def toString = s"$numer / $denom"

  def +(that: Rational) =
    Rational(numer * that.denom + that.numer * denom, denom * that.denom)

  def -(that: Rational) =
    this + -that

  def unary_- : Rational = Rational(-numer, denom)
}

object Rational {
  def apply(x: Int, y: Int) = new Rational(x, y)
}


Rational(1, 2).add(Rational(1, 2))