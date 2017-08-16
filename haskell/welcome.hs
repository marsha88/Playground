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

-- List Comprehensions
[x * 2 | x <- [1..10]]
-- adding a predicate. You can add multiple predicates to a list comprehension by separating the predicates with a comma.
[ x * 2 | x <- [1..10], x*2 >= 12, x*2 < 100]

boomBangs xs = [ if x < 10 then "BOOM!" else "BANG!" | x <- xs, odd x]

-- using underscore as a variable name says we'll never use a variable name so why add one?
length' lst = sum [1 | _ <- lst]

-- using multiple lists within a comprehension results in each element being used with each element in the other list. This allows for using lists of varying sizes without any trouble
let adjectives = ["fuzzy", "red", "angry", "fat"]
let nouns = ["dog", "truck", "stove", "cookie", "coffee"]
[adj ++ " " ++ noun | adj <- adjectives, noun <- nouns]

let sentence = "Hello, World"
--removes all lowercase letters and leaves everything else.
removeLowers str = [ c | c <- str, not (elem c ['a'..'z'])]


-- using comprehensions on nested lists
let nestedList = [[1,2,3,4], [5,6,7,8]]
removeOdds nested = [[ x | x <- y, even x] | y <- nested]

let clayton = ("clayton", "marshall", 20)
let connor = ("connor", "marshall", 17)
-- can't compare tuples of varying lengths

-- you can have lists of tuples as long as the tuples contain the same types
let listOfTuples = [clayton, connor]

let my_pair = (1,2)
--pair functions
fst my_pair -- returns 1
snd my_pair -- returns 2

-- zip returns a list of pairs where corresponding elements in each list are returned inside a tuple
zip ["one", "two"] [1,2]  --returns [("one",1),("two",2)]
-- if one list is longer than the other it just performs the operations to the length of the smallest list.

-- all right triangles whose perimeter is == 24 and whose sides are <= 10
let triangles = [(a,b,c) | c <- [1..10], b <- [1..c], a <- [1..b], a^2 + b^2 == c^2, a+b+c == 24]
