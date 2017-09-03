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




