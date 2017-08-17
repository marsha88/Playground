sumThree :: Int -> Int -> Int -> Int
sumThree x y z = x + y + z

factorial :: Integer -> Integer
factorial x = product [1..x]

factorial' :: Integer -> Integer
factorial' x = if x == 1 then 1 else x * factorial' (x-1)

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
