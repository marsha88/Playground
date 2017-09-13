
-- Need to learn typeclasses better....
{-

mean :: (Num a) => [a] -> a
mean obs = (sum obs) / (length obs)

variance :: (Num a) => a -> a
variance obs = sum [ x^2  | x <- [y - avg | y <- obs, let avg = mean obs]]
-}

isqrt :: Int -> Int
isqrt = floor . sqrt . fromIntegral

isprime :: Int -> Bool
isprime 1 = True
isprime num = null [x | x <- [2..isqrt(num)], num `mod` x == 0]

nprimes :: Int -> [Int]
nprimes n = take n [x | x <- [1..], isprime x]
