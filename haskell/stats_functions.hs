
-- Need to learn typeclasses better....

mean :: (Num a) => [a] -> a
mean obs = (sum obs) / (length obs)

variance :: (Num a) => a -> a
variance obs = sum [ x^2  | x <- [y - avg | y <- obs, let avg = mean obs]]
