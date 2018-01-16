maximum' :: (Ord a) => [a] -> a
maximum' [] = error "Empty List"
maximum' [x] = x
maximum' (x:xs)
  | x > maxTail = x
  | otherwise = maxTail
 where maxTail = maximum' xs

-- cleaner solution
max' :: (Ord a) => [a] -> a
max' [] = error "Emtpy List"
max' [x] = x
max' (x:xs) = max x (max' xs)

replicate' :: (Num i, Ord i) => i -> b -> [b]
replicate' i elm
    | (i <= 0)  = []
    | otherwise = elm:(replicate' (i-1) elm)

take' :: (Integral a) => a -> [b] -> [b]
take' _ [] = []
take' i (x:xs)
  | i <= 0 = []
  | otherwise = x:(take' (i-1) xs)


reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]

zip' :: [a] -> [b] -> [(a,b)]
zip' _ [] = []
zip' [] _ = []
zip' (x:xs) (y:ys) = (x,y):(zip' xs ys)

elem' :: (Eq a) => a -> [a] -> Bool
elem' _ [] = False
elem' n (x:xs)
    | (x == n) = True
    | otherwise = (elem' n xs)

quicksort :: (Ord a) => [a] -> [a]
quicksort [] = []
quicksort (x:xs) = smallSorted ++ [x] ++ bigSorted
  where smallSorted = quicksort [a | a <- xs, a <= x]
        bigSorted = quicksort [a | a <- xs, a > x]

map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' f (x:xs) = (f x) : (map' f xs)

foldl' :: (b -> a -> b) -> b -> [a] -> b
foldl' _ i []     = i
foldl' f i (x:xs) = foldl' f (f i x) xs

foldr' :: (b -> a -> b) -> b -> [a] -> b
foldr' _ i []     = i
foldr' f i (x:xs) = f (foldr' f i xs)  x

