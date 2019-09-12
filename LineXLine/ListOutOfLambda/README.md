# Impractical, Functional Lists (List out of Lambda)
For my first post, I wanted to do something fun and entertaining. I don't want to bore you with super serious facts, or even cover anything practical for that matter. Instead, we'll be walking through a programming exercise of sorts.

I got the idea for this post from another article called [List out of Lambda](http://stevelosh.com/blog/2013/03/list-out-of-lambda/) by [Steve Losh](https://github.com/sjl). I highly recommend taking the time to read the article. I'm going to expand on what List out of Lambda covers by inexplicitly introducing a topic called Church encodings, but I'll be leaving out some additional talking points presented in List Out of Lambda (again, go read it - it's great).

## Goals:
The goal of today is to rebuild language functionality using a VERY limited tool set. Sort of a "build a lot with a little" puzzle. We'll be creating a Javascript list data structure using **nothing** but functions.

## Part 1: Closures
An important prerequisite to understanding the contents of this post is knowing a bit about Javascript functions. More specifically, you will need to understand how closures work. If you feel comfortable with closures go ahead and move on to Part 2! If not, no worries! We'll cover them in good detail, and if you're still confused by the end of my explanation, I'll link you to resources that explain things better than I can.

I won't beat around the bush anymore. Let's answer the question: What is a closure?

In developer speak, a closure is the combination of a function and the lexical environment within which that function was declared. 

You can think about it this way; all Javascript functions are closures. Javascript functions all contain a reference to their outer environment.

For example:

```Javascript
let bar = 1
function foo() {
	console.log("bar = ", bar)
}
foo() // "bar = 1"
```
In this snippet of code, we have a function `foo` that logs out some value `bar`. Because functions in Javascript have a reference to their outer scope, we can access the variable `bar` from inside our function `foo`.

This functionality is pretty standard though, right? Functions having access to the global scope is supported in almost every programming language. 

Closures get much more interesting when our language supports first-class functions. First-class meaning that our functions can be treated as values. For example, in Javascript, we can pass functions to other functions and return functions from functions.

Consider the case when a function returns a function. As we've discussed, all Javascript functions are closures. So, the returned function is a closure which means it has access to the environment scope in which it was declared. That means our returned function has access to everything that is declared inside the outer function!

```Javascript
function lineByLine() {
	const name = "LineXLine"
	const authors = ["ben", "jon", "clayton"]
	return function() {
		return name + " team: " + authors.join()
	}
}

const getTeamInfo = lineByLine()

getTeamInfo()
=> "linexline team: ben,jon,clayton"
```


By returning a function from within a function, you not only get the returned function when called, but the returned function also gets access to its outer environment at the time it was created. The function "closes" in on it's outer environment, hence closures :)


We'll look at one more example for good measure.

```Javascript
function idGenerator(idPrefix) {
	let suffix = 0
	return function() {
		const id = idPrefix + suffix
		suffix += 1
		return id
	}
}

const getCatId = idGenerator("cat")
getCatId() // => "cat0"
getCatId() // => "cat1"
getCatId() // => "cat2"
```

Here we're writing an ID generator that takes some prefix and returns a function
that can return to us an incremented ID upon each call. 
Let's walk through it in some detail. I set `getCatId` to `idGenerator("cat")` which returns a function. When I call the `getCatId` function, it generates a new id by appending the `idPrefix` with the `suffix` that is assigned in `idGenerator` function. After it generates a new code, we've incremented the `suffix` count so the next time we invoke the `getCatId` function, we'll get a different cat id.

This example shows off another cool feature of closures. Not only can you read from
the returned function's outer environment, but you can mutate it. If you come from a background of object oriented programming, you can think of `suffix` as a private variable. I can't access `suffix` outside the `idGenerator` function, but I was able to return a function that can!

I actually got this as an interview question a couple years ago. Very easy to solve if you understand closures - quite complex if you rely on global shared state.

If these examples sufficed and you feel comfortable with closures in Javascript, let's move on! If not feel free to take a look at the resources below. It's worth taking your time to learn this thoroughly especially if you use Javascript on a regular basis.

Closures in Javascript (More Resources):

https://developer.mozilla.org/en-US/docs/Web/Javascript/Closures

https://blog.bitsrc.io/a-beginners-guide-to-closures-in-Javascript-97d372284dda

https://www.youtube.com/watch?v=F3EsDDp4VXg


## Part 2: Let's build a list
___

What if Javascript didn't provide us with a list-like structure? No arrays, no objects. Is it possible to build our own?

First, let's build some criteria of what this list structure should provide.

Wish List (pun intended):

1) We need some concept of an empty list.
```javascript
isEmpty([]) // true
```

2) We need to be able to prepend items to an existing list.
```javascript
prepend(1, [2, 3]) // [1, 2, 3]
```

3) We need to be able to get the head and tail of a list. (*Head being the first element of a list and tail being a list of everything but the first element*)
```javascript
head([1, 2, 3]) //1
tail([1, 2, 3]) // [2, 3]
```



Here is some blueprinting of the functions that we'll be implementing, and some examples of how they'll be used.

```Javascript
const empty = //TODO

function isEmpty(list) {
  //TODO
}

function prepend(head, tail) {
  //TODO
}

function head(list) {
  //TODO
}

function tail(list) {
  //TODO
}

/** Examples */

const fruitBasket = prepend("apple", prepend("orange", prepend("banana", empty)));

const isEmptyFruitBasket = isEmpty(fruitBasket) // false

const firstFruit = head(fruitBasket)	// "apple"

const leftovers = tail(fruitBasket) // prepend("orange", prepend("banana", empty))

```

Our end goal is to implement all of this with nothing but functions, but for now, let's try implementing this with all tools at our disposal (aside from arrays & objects) and try removing them one by one.

We'll start with the basics: the empty list. How will we define the empty list? `null` seems appropriate for the time being. With that in mind, we can implement some of our `TODO`s

```Javascript
const empty = null

function isEmpty(list) {
  return list === empty
}

function prepend(head, tail) {
  //TODO
}

function head(list) {
  //TODO
}

function tail(list) {
  //TODO
}
```

Now, let's implement `prepend`. This is the hardest function to wrap your head around. Take a minute to read the `prepend`implementation below, and think of how you might write `head` and `tail`. The goal of `prepend` is to add an element to the beginning of an existing list and return it. If you get stuck trying to understand `prepend` move on to the next part - it may help to give some context of how it's used.

```Javascript
const empty = null

function isEmpty(list) {
  return list === empty
}

// head will be the element to prepend to another list, tail.
function prepend(head, tail) {
  return function(option) {
    if(option === "head") {
      return head
    }
    else if (option === "tail") {
      return tail
    }
  }
}

function head(list) {
  //TODO
}

function tail(list) {
  //TODO
}
```

The key take away from this is that `prepend` returns a function. Earlier, I said that `prepend` should return a list. So where is the list? The function returned IS our list. In reality, `prepend` isn't actually creating a list. Instead, it's just gluing two things together and storing them in a closure. BUT, we can still treat this as a list.

Let's think back to the criteria for our list - the only operations we really need are grabbing the head, tail, and prepending a new element to our list. We can do that with this returned function!

Wanna see how?

```Javascript
// create a single element list
const farm_animal_sounds = prepend("moo", prepend("oink", empty))

// grab the first element
const head_element = farm_animal_sounds("head") // "moo"

// grab everything after the first element
const tail_elements = farm_animal_sounds("tail") // prepend("oink", empty)

// create a new list by prepending "woof" to our old list
const more_animal_sounds = prepend("woof", farm_animal_sounds)
```

A little confused? Remember that our `prepend` function returns a function. The returned function accepts an option argument of either `"head"` or `"tail"` and returns the `head` or `tail` as they were specified in the outer `prepend` function's arguments. It's a simple idea, but very unfamiliar and difficult to wrap your head around, so don't worry if it isn't crystal clear yet. 

It's time to move on and implement our `head` and `tail` functions. This should be straight forward now because we've basically wrote the code for them already.

For now, we won't worry about error cases (calling head and tail on the empty list). So, assuming our head and tail functions receive an unempty list, let's return the head and tail respectively.

```Javascript
function head(list) {
	return list("head")
}

function tail(list) {
  return list("tail")
}
```

That's it! We just invoke the list function with `"head"` and `"tail"`. We have all the functions we set out to create! We'll call this our version 1 list implementation.

So, we have a list, but where is the data stored? The values are actually being stored inside nested closures. Each call to prepend returns a closure that has a `head` and `tail` value captured in its scope.


To get a little more familiar with how our lists work, let's write some helper functions for them.

*Quick Aside:
If you'd like to do some playing around with these lists but don't want to keep typing all the prepend calls, just use this function for some quick list creation.*

```Javascript
const createList = (...args) => args.reverse().reduce((acc, el) => prepend(el, acc), empty)

// Usage:
const disappointing_movies = createList("Shrek 3",
                                        "Mighty Ducks 3",
                                        "The Last Airbender",
                                        "Eragon",
                                        "Man of Steel")
```

*ok, back to the helper functions...*

```Javascript
function length(list) {
  //TODO
}

function map(f, list) {
  //TODO
}

function filter(predicate, list) {
  //TODO
}

/** Example Usage */

const nums = createList(1, 2, 3, 4, 5)
// (1, 2, 3, 4, 5)

const len = length(nums) 
// 5

const doubled_nums = map(function(x) { return x * 2 }, nums)
// (2, 4, 6, 8, 10)

const big_nums = filter(function(x) { return x > 3 }, nums)
// (4, 5)
```

Implementations:

```Javascript

function length(list) {
  if(isEmpty(list)) {
    return 0;
  }
  else {
    return 1 + length(tail(list))
  }
}

function map(f, list) {
  if(isEmpty(list)) {
    return list;
  }

  const h = head(list)
  const t = tail(list)

  return prepend(f(h), map(f, t));
}

function filter(predicate, list) {
	if(isEmpty(list)) {
		return list;
	}
	const h = head(list)
	const t = tail(list)
	
	if(predicate(h)) {
		return prepend(h, filter(predicate, t))
	}
	else {
		return filter(predicate, t)
	}
}
```

Take time to use some of these list helpers or maybe write a few new list helpers to get you more comfortable with the lists we've made.

List Helper Suggestions:

`printList` - takes a list and prints each item

`sum` - takes a list of numbers and returns their sum

`reverse` - takes a list and returns a reversed version of the list


## Part 3: Removing Strings

Our current list structure works, but we're using a bit of unneeded language functionality.

We're using:

- functions
- strings
- booleans/equality expressions
- if/else statements

Let's start by removing the usage of strings inside our prepend.

```Javascript
const empty = null

function isEmpty(list) {
  return list === empty
}

function prepend(head, tail) {
  return function(select) {
    return select(head, tail)
  }
}

function head(list) {
  return list(function(head, tail) {
    return head
  })
}

function tail(list) {
  return list(function(head, tail) {
    return tail
  })
}
```

No more strings! So, how does this work?

All head and tail need are access to the actual head and tail of a list. In our case, lists are just functions, so we can give our list a select function that we can call with the values of head and tail.

If this isn't making any sense, I would suggest stepping through an example to see how it works. It's very helpful to write this out on paper for yourself, but I'll try my best to go step by step through an example...

```Javascript
const tear_worthy_movies = prepend("Cast Away", empty)
head(tear_worthy_movies)

/*
Step by step:
1.) // Line 1
	const myList = prepend("Cast Away", empty)

2.) // Reduction of the prepend call in Line 1
	const myList = function(op) {		
      return op("Cast Away", empty)
    }

3.) // Line 2
	head(myList)

4.) // Expanding the head function from Line 2
	(function(list){
      return list(function(head, tail) {
        return head;
      })
    })(myList)

5.) // Reduction of Step 4
	myList(function(head, tail) {
       return head
    })

6.) // Expanding the myList function 
	(function(op) {
      return op("Cast Away", empty)
    })(function(head, tail) {
       return head
    })

 7.) // Reduction of Step 6
 	 (function(head, tail) { return head })("Cast Away", empty)

 8.) // Final Reduction
     "Cast Away"
*/
```

That was a lot of logic to trace through. If you're still confused about how this works, take your time reading over the steps again, or try your own example on paper. You will need a good understanding of this section before moving on to the next one.

## Part 4: Removing If/Else

We are now down to using:

- functions
- booleans/equality expressions
- if/else statements

Let's remove those if/else statements.

Currently if/else statements are only being used to test if a list is empty. One way to avoid this is to tag each list as being empty or not being empty. To do this, we have to change the value of the empty list from null to a function that assigns an empty tag to the list. The empty function will look similar to the prepend function, but doesn't need any reference to a head or tail.

We will add an additional parameter to our `selector` function that will either be true or false depending on whether or not the list is empty.

```Javascript
const empty = function(select) {
  return select(null, null, true)
}

function isEmpty(list) {
  return list(function(head, tail, isempty) {
    return isempty
  })
}

function prepend(head, tail) {
  return function(select) {
    return select(head, tail, false)
  }
}

function head(list) {
  return list(function(head, tail, isempty) {
    return head
  })
}

function tail(list) {
  return list(function(head, tail, isempty) {
    return tail
  })
}
```

Our list structure is looking great! We've cut down our language usage to `functions` and `booleans` to build our data structure.

## Part 5: Removing Booleans

We've come quite a long way since version 1, but more work can still be done. We need to remove those booleans. How can we do this though? We need some way to determine if a list is empty, which means we need a way to run conditional expressions. Let's try rewriting conditionals using functions. Sounds a bit daunting, but it's doable.


Let's take a second to review our criteria for the structure we want to replace:
1.) Need to provide constants for both true and false
2.) Need to use these constants to choose between two things (essentially `if/else` statements)

We're going to write our true and false values as functions that choose between two things.

```Javascript
function truthy(t, f) {
  return t
}

function falsey(t, f) {
  return f
}
```

Our function truthy takes two things and always returns the first and our falsey function takes two things and always returns the latter.

How are these going to be used as true and false values?

Without much context, these don't make a lot of sense, but the implementation of `ifelse` should shine a bit of light on the answer.

```Javascript
function ifelse(conditional, then, else) {
  return conditional(then, else)
}
```
The conditional that is passed into `ifelse` will always be either `truthy` or `falsey`.

A few examples:

```Javascript
function truthy(t, f) {
  return t
}

function falsey(t, f) {
  return f
}

function ifelse(conditional, then, el) {
  return conditional(then, el)
}

// Example usage:
const a = ifelse(truthy, 1, 2)  // 1
const b = ifelse(falsey, "it was TRUE!", "sorry... it was false.") // "sorry.. it was false."
```

We just wrote if/else functionality with nothing but Javascript functions!

Great, but how do we apply this to our lists?
We should just be able to replace the usages of true and false with truthy and falsey.

```Javascript
const empty = function(select) {
  select(null, null, truthy)
}

function isEmpty(list) {
  return list(function(head, tail, isempty) {
    return isempty
  })
}

function prepend(head, tail) {
  return function(op) {
    return op(head, tail, falsey)
  }
}

function head(list) {
  return list(function(head, tail, isempty) {
    return head
  })
}

function tail(list) {
  return list(function(head, tail, isempty) {
    return tail
  })
}
```

This begs the question though: Why did we write `ifelse`?

It isn't super obvious, but we need it for `isEmpty` to be usable inside our other list helper functions. Our old implementations of things like `length`, `printList`, and `map` relied on our old implementation of `isEmpty`, but that doesn't work anymore because Javascript's native if/else statements won't work with our `truthy` and `falsey` values.

Time for a **refactor**!

Let's implement length again using our newly formed lists.

```Javascript
function length(list) {
  return ifelse(isEmpty(list),
              0,
              1 + length(tail(list)))
}
```
Seems like a reasonable and simple solution. Unfortunately, there is a problem. The problem is one we can't avoid because it is engrained at the language level of Javascript.

To find the problem imagine we called length on the empty list.

```Javascript
length(empty)

// The above line will produce the following
ifelse(isEmpty(list),
         0,
         1 + length(tail(list)))

```
Even though our list is empty and the only branch that should execute is the `0`, the `1 + length(tail(list))` will still be run because ifelse is just a function and `1 + length(tail(list))` is an argument we've given to that function. In short, the `1 + length(tail(list))` will be evaluated regardless of the outcome of `isEmpty(list)`, and when this happens we'll be attempting to get the tail of an empty list which is an error.

So, how can we avoid this issue? How do we postpone execution until we must execute a specific branch of the ifelse function? The answer to this question is the same as the answer to almost every question asked so far: functions!

```Javascript
function length(list) {
  const zero = function() {
    return 0
  }

  const tail_length = function() {
    return 1 + length(tail(list))
  }

  return ifelse(isEmpty(list), zero, tail_length)
}
```

and this also requires us to change our original booleans as well from this:

```Javascript
function truthy(t, f) {
  return t
}

function falsey(t, f) {
  return f
}
```

to this:

```Javascript
function truthy(t, f) {
  return t()
}

function falsey(t, f) {
  return f()
}
```

Which leaves us with our final implementation for our functional lists:

```Javascript
function truthy(t, f) {
  return t()
}

function falsey(t, f) {
  return f()
}

function ifelse(conditional, then, else) {
  return conditional(then, else)
}

const nil = function() {} // added this for the sake of completion

const empty = function(select) {
  select(nil, nil, truthy)
}

function isEmpty(list) {
  return list(function(h, t, e) {
    return e
  })
}

function prepend(head, tail) {
  return function(select) {
    return select(head, tail, falsey)
  }
}

function head(list) {
  return list(function(head, tail, e) {
    return head
  })
}

function tail(list) {
  return list(function(head, tail) {
    return tail
  })
}
```

We've finally arrived at our end goal! This is a list data structure using nothing but Javascript functions. Mess around with this more and see what other language functionality you can create by using functions (let expressions, loops, numbers, etc.). Below is a link to a repo that contains all the code I've shown here along with some additional list helpers and an ES6 refactor if you're interested.

Hopefully this exercise forced you to think a little differently. Trying to build something with limited tools often leads to very creative solutions that you wouldn't be exposed to in your day-to-day programming.

Thanks for reading!

Functional List Implementation: https://github.com/claytn/Playground/tree/master/LineXLine/ListOutOfLambda

Follow me on [Github](https://github.com/claytn)

