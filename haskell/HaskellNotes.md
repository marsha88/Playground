## Haskell Notes

#### GHCi - Glasgow Haskell Compiler Interactive

##### Basic Operations:
```
λ => 3 + 4
7
λ => “clayton”
“clayton”
λ => [1,2,3,4,5]
[1,2,3,4,5]		-- lists must be homogenous
λ => head [1,2,3,4]
1
λ => tail [1,2,3,4]
[2,3,4]
λ => last [1,2,3,4]
4
λ => init [1,2,3,4]
[1,2,3]
λ => [1,2,3,4] !! 1		-- grabs item at index 1
2
λ => let myList = [1,2,3]
λ => 0:myList			-- prepends the element 0 to myList
[0,1,2,3]
λ => myList ++ [4]		-- appends the element 4 to myList
[0,1,2,3,4]
λ => 50 * (-1)			-- you must wrap negatives in parenthesis
-50
λ => 5 == 5
True
λ => 5 == 4
False
λ => 5 /= 4				-- That divide by equals is means not equal
True
```
* Haskell does not coerce types except for Ints and Floats, but they may be a different
    issue. Will update in the future when I know a thing or two.


* Quick Side Note: If you write a function in Haskell that accepts two parameters and  
   you only supply it one it will return a function that accepts the second parameter. Auto
   Curry!!!!

* Inside of an if then else statement the else is required.

* functions in Haskell cannot start with a capital letter. I don't know what that's all about, but I'll update this when I do.

* Strings in haskell are just character arrays.

* Internally, haskell builds lists using cons operators:
```
=> 1:2:3:4:[]
[1,2,3,4]
```
So, you want to concatenate a string for example and you use the ++ operator:
```
=> "hello, " ++ "world"
"hello, world"
```
this has to recreate the entire string character by character, but prepending elements using the `:`
operator is done instantly because wrapping the existing list in another cons is very quick and easy to do
and still remains immutable.

```
=> 'c':"layton"
"clayton"

=> [1,2,3,4] ++ 5     -- this errors because items that are appended need to be lists themselves
=> [1,2,3,4] ++ [5]   -- this fixes the error from above
[1,2,3,4,5]
```

#### More List Operations
```
=> let list = [1,2,3,4]
=> null list
False
=> null []
True
=> take 2 list
[1,2]
=> take 1000 list
[1,2,3,4]
=> take 0 list
[]
=> reverse list
[4,3,2,1]
=> minimum list
1
=> maximum list
4
=> sum list
10
=> product list
24
=> elem 4 list  -- checks if 4 is an element in list
True
=> drop 2 list    -- removes the first two elements from list (does not mutate list)
[2,1]
=> [1,2,3] == [1,2,3] -- regular comparison operators can be used on lists as long as the elements inside them can be compared.
True
=> [1..5]  --ranges
[1,2,3,4,5]
=> [2,4..20]  -- ranges with pattern specifications. Start with first two elements of list and upper bound.
[2,4,6,8,10,12,14,16,18,20]
```
* The functions we have been using can also be used as infix by using backticks
```haskell
=> elem 3 list
True
=> 3 `elem` list
True
```
#### Expressions in Haskell
5
5 + 4
if x < 100 then x else x * 2
"hello, world"

#### Types and Typeclasses
```haskell
ghci> :t (==)  
(==) :: (Eq a) => a -> a -> Bool  
```
* Everything before the `=>` in the definition of the `==` function is a class constraint. In this case it says that
  a must be a member of the Eq class.
* Take a look at [this](http://learnyouahaskell.com/types-and-typeclasses) to see a list of built in typeclasses.
* You can use explicit type annotations by using the `::` operator
```haskell
sumThree :: Int -> Int -> Int -> Int
sumThree x y z = x + y + z

=> minBound :: Int
-9223372036854775808

=> maxBound :: Bool
True
```

#### Pattern Matching
* You can use pattern matching on functions to avoid large trees of if else. Similar to rust pattern matching, if you do
  pattern match you must exhaust all cases.
```haskell
sayMe :: (Integral a) => a -> String  
sayMe 1 = "One!"  
sayMe 2 = "Two!"  
sayMe 3 = "Three!"  
sayMe 4 = "Four!"  
sayMe 5 = "Five!"  
sayMe x = "Not between 1 and 5"
```

##### Pattern Matching with Tuples and Lists
```haskell
-- Pattern Matching a tuple to implement fst for tri-tuples
first :: (a, b, c) -> a
first (x, _, _) = x

-- Pattern Matching to implement head function for lists
head :: [a] -> a
head [] = error "Your list has been decapitated..."
--this gives you x being the first element and xs being the rest of the list.
head (x:xs) = x
```
* Read through types.hs to see more examples of pattern matching with lists and tuples

##### Guards
- guards are a nice way to avoid huge if  else trees.
```haskell
bmiTell weight height  
    | weight / height ^ 2  <= 18.5 = "You're underweight, you emo, you!"  
    | weight / height ^ 2  <= 25.0 = "You're supposedly normal. Pffft, I bet you're ugly!"  
    | weight / height ^ 2  <= 30.0 = "You're fat! Lose some weight, fatty!"  
    | otherwise   = "You're a whale, congratulations!"  
```  
- it's important to see that the `=` sign doesn't come until the guard arms. You are not writing out an if else
  within the bmiTell function. It is more accurate to say you are defining many versions of the function given
  the guards you have set up. It's pattern matching with conditionals.
- adding a `where` block to this could clean things up a bit to avoid repetition

```haskell
bmiTell weight height  
    | bmi <= 18.5 = "You're underweight, you emo, you!"  
    | bmi <= 25.0 = "You're supposedly normal. Pffft, I bet you're ugly!"  
    | bmi <= 30.0 = "You're fat! Lose some weight, fatty!"  
    | otherwise   = "You're a whale, congratulations!"
    where bmi = weight / height ^ 2
```
- We can take this even further and add more variables to the where block. If multiple variables do exist within the
  `where` block it does need to be indented properly or haskell will throw a fit.

  ```haskell
  bmiTell weight height  
      | bmi <= skinny = "You're underweight, you emo, you!"  
      | bmi <= normal = "You're supposedly normal. Pffft, I bet you're ugly!"  
      | bmi <= fat = "You're fat! Lose some weight, fatty!"  
      | otherwise   = "You're a whale, congratulations!"
      where bmi = weight / height ^ 2
            skinny = 18.5
            normal = 25.0
            fat = 30.0
  ```
   - keep in mind you can still using all the fun pattern matching techniques within the `where` block.
   - even more impressive is that you can define functions inside the where block as well.

#### Let
```haskell
-- standard let in expression
let a = 5 in a + 3  
```
* let expressions can be used anywhere you've been using expressions in the past.
```haskell
[let square x = x * x in (square 5, square 3, square 2)]
```

 - multiple variable declarations using let
 ```haskell
  let a = 100
      b = 200
      c = 300 in a + b + c
 ```
 - inline multiple variable declarations
 ```haskell
 -- the last semi-colon is optional.
 let a = 100; b = 200; in a + b
 ```
* The names defined in a let inside a list comprehension are visible to the output function (the part before the |) and all predicates and sections that come after of the binding. The variable is bound only to that comprehension so no `in` statement is needed.
```haskell
calcBmis :: (RealFloat a) => [(a, a)] -> [a]  
calcBmis xs = [bmi | (w, h) <- xs, let bmi = w / h ^ 2]
```
* The in part can also be omitted when defining functions and constants directly in GHCi. If we do that, then the names will be visible throughout the entire interactive session.

#### Case Expressions
Remember patten matching functions? Well, that was all syntactic sugar for case expressions.
```haskell
  -- general form of case expression.
  case expression of pattern -> result  
                     pattern -> result  
                     pattern -> result
```
- the below two code blocks compute their solutions in the exact same way.
```haskell
-- syntactic sugar
head' :: [a] -> a  
head' [] = error "No head for empty lists!"  
head' (x:_) = x
```
```haskell
-- case expression
head' :: [a] -> a  
head' xs = case xs of [] -> error "No head for empty lists!"  
                      (x:_) -> x
```
* just like pattern matching functions case expressions must be exhaustive.
