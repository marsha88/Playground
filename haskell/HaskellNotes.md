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

#### Expressions in Haskell
5
5 + 4
if x < 100 then x else x * 2
"hello, world"
