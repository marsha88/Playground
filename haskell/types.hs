sumThree :: Int -> Int -> Int -> Int
sumThree x y z = x + y + z

factorial :: Integer -> Integer
factorial x = product [1..x]

factorial' :: Integer -> Integer
factorial' x = if x == 1 then 1 else x * factorial' (x-1)

factorial'' :: Integer -> Integer
factorial'' 0 = 1
factorial'' x = x * (factorial'' (pred x))

-- how to memoize this in haskell?
-- naive recursive fib solution
fib :: Int -> Int
fib x = if x == 0 || x == 1 then 1 else fib (x-1) + fib (x-2)

-- iterative solution
fibIter' :: Int -> Int -> Int -> Int -> Int
fibIter' cur ith x y = if cur == ith
                      then y
                      else fibIter' (succ cur) ith y (x+y)

fib' :: Int -> Int
fib' x = fibIter' 0 x 0 1

-- must exhaust all cases when pattern matching.
sayMe :: (Integral a) => a -> String
sayMe 1 = "One!"
sayMe 2 = "Two!"
sayMe 3 = "Three!"
sayMe 4 = "Four!"
sayMe 5 = "Five!"
sayMe _ = "Not between 1 and 5"

addVectors :: (Num a) => (a, a) -> (a, a) -> (a, a)
addVectors a b = (fst a + fst b, snd a + snd b)

dotProduct :: (Num a) => (a,a) -> (a,a) -> a
dotProduct x y = (fst x * fst y) + (snd x * snd y)

-- defining pair operations for triples
first :: (a, b, c) -> a
first (x, _, _) = x

second :: (a, b, c) -> b
second (_, y, _) = y

third :: (a, b, c) -> c
third (_, _, z) = z

-- pattern matching inside of list comprehension
sumOfPairs :: (Num a) => [(a,a)] -> [a]
sumOfPairs pList = [a+b | (a,b) <- pList]

head' :: [a] -> a
head' [] = error "Attempted to get head of list with length = 0"
head' (x:_) = x

-- matching against elements with 3 or more items in the list
sumOfFirst3 (x:y:z:zs) = x + y + z
sumOfFirst2 [x,y] = x + y

-- writing length using recursion and pattern matching
length' :: (Num b) => [a] -> b
length' [] = 0
length' (_:xs) = 1 + length' xs

-- sum implementation
sum' [] = 0
sum' (x:xs) = x + sum' xs

-- maintaining a reference to an un-destructured version of a list/tuple with @
capital :: String -> String
capital "" = "Empty String!"
capital all@(x:xs) = "The first letter in " ++ all ++ " is " ++ [x]

-- guards
-- otherwise is the same as True so it is the 'catch all'
bmiTell :: (RealFloat a) => a -> String
bmiTell bmi
    | bmi <= 18.5 = "underweight"
    | bmi <= 25.0 = "normal"
    | bmi <= 30.0 = "overweight"
    | otherwise   = "...."

-- making use of the where block
initials :: String -> String -> String
initials firstname lastname = [f] ++ "." ++ [l]
  where (f:_) = firstname
        (l:_) = lastname

-- another guards example that also shows how to write infix functions.
myCompare :: (Ord a) => a -> a -> Ordering
a `myCompare` b
    | a > b     = GT
    | a == b    = EQ
    | otherwise = LT

-- given a list calculates bmi of each given height and weight pair. Produces a list of insults as given by bmiTell.
calcBmis :: (RealFloat a) => [(a,a)] -> [String]
calcBmis hw = [bmiTell (bmi x) | x <- hw]
  where bmi (weight, height) = weight / height ^ 2
