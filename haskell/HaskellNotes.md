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
```
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
