fact 0 = 1
fact n = n * fact(n-1)

inc x = x + 1

dec x = x - 1

greaterThan3 x = if x > 3
                  then x
                  else x + 3

doubleMe x = x + x
doubleUs x y = doubleMe x + doubleMe y

doubleSmallNumber x = if x > 100
  then x
  else x * 2

getFirstXMultiplesOfY x y = take x [y, (y+y)..]

giveXRepeatsOfY x y = take x (repeat y)

giveXCyclesOfY x y = take x (cycle y)
