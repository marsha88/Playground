-- Problem 1: find last element in list

last' :: [a] -> a
last' [] = error "Empty List"
last' [x] = x
last' (_:xs) = last' xs

-- Problem 2: find second to last element
-- TODO: review last . init solution

almostLast' :: [a] -> a
almostLast' [] = error "Empty List"
almostLast' [_] = error "List only contains one item"
almostLast' [x,_] = x
almostLast' (_:xs) = almostLast' xs

-- Problem 3: return ith element of list where indexing starts at 1
-- solution attempt #1
elementAt' :: [a] -> Int -> a
elementAt' elms ith
  | length elms < ith = error "Not enough items"
  | ith < 1 = error "invalid index requested"
  | otherwise = elementAtIter elms ith 1

elementAtIter :: [a] -> Int -> Int -> a
elementAtIter (x:xs) ith current
  | ith == current = x
  | otherwise = elementAtIter xs ith (current+1)

-- solution attempt #2
elementAt'' :: [a] -> Int -> a
elementAt'' (x:_) 1 = x
elementAt'' [] _ = error "No items"
elementAt'' (_:xs) ith
  | ith < 1 = error "Invalid Index"
  | otherwise = elementAt'' xs (ith-1)

-- Problem 4: return length of list
length' :: [a] -> Int
length' [] = 0
length' (x:xs) = 1 + length' xs

-- Problem 5: reverse the given list
reverse' :: [a] -> [a]
reverse' [] = []
reverse' (x:xs) = reverse' xs ++ [x]

-- Problem 6: check for palindrome
palindrome' :: (Eq a) => [a] -> Bool
palindrome' x = x == (reverse' x)


-- Problem 7: flatten-list
flattenList' :: [[a]] -> [a]
flattenList' []     = []
flattenList' [[]]   = []
flattenList' (x:xs) = x ++ flattenList' xs

-- Problem 8: compress list

-- solution #1
compress' :: (Eq a) => [a] -> [a]
compress' [] = []
compress' x = compressIter [] x

compressIter :: (Eq a) => [a] -> [a] -> [a]
compressIter clist [] = clist
compressIter clist (x:xs)
  | elem x clist = compressIter clist xs
  | otherwise = compressIter  (clist ++ [x]) xs

-- solution #2
compress'' :: (Eq a) => [a] -> [a]
compress'' [] = []
compress'' (x:xs) = x:(compress'' (filter (/=x) xs))

-- Problem 9: pack List
pack' :: (Eq a) => [a] -> [[a]]
pack' [] = [[]]
pack' [x] = [[x]]
pack' full@(x:xs) = [[y | y <- full, x == y]] ++ pack' [y | y <- full, x /= y]
