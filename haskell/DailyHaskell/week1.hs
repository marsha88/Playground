-- Problem 1: find last element in list

last' :: [a] -> a
last' [] = error "Empty List"
last' [x] = x
last' (_:xs) = last' xs

-- Problem 2: find second to last element
/* TODO: review last . init solution */
almostLast' :: [a] -> a
almostLast' [] = error "Empty List"
almostLast' [_] = error "List only contains one item"
almostLast' [x,_] = x
almostLast' (_:xs) = almostLast' xs


