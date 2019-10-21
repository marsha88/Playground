## A World of Better Type Systems


If there is one thing I hate about software development communities, it's the insistence on arguing about programming tools and styles. Text editor, framework, spaces vs tabs, etc. The answer to all of these is simple: it's a preference. A similar claim can be made about the argument between type systems. Dynamic or static? Which is best? What's easier to learn? Which one costs more developer time? 
I think most would agree that there is no definitive right or wrong answer to this. Ultimately, you're making tradeoffs. Fortunately, this post isn't about heating up the type systems debate. Instead we're going to take a deeper dive into today's static type systems. Are our type systems really that helpful? And if not, could they be?

If you've recently left college/university, or you haven't done much catching up on the programming language scene since you left, you probably have a pretty limited idea of what a static type system is. When most programmers hear, "static type system", they think of Java or some variation of the C language. Those people aren't wrong - these languages definitely have static type systems, but these languages shouldn't be our only understanding of static types. Frankly, Java and C don't have the greatest type systems. They're very often a pain in the ass to use, they don't give many helpful compile time guarentees, and often times they're needlessly complex to work with. In this post, I'd like to show that static type system don't always have to be like this. In this post, I'd like to shine some light on a few languages with richer, more "cutting-edge" type systems, and the powerful features they can offer us as developers. 

 

 ### Type Inference:

 A common complaint about languages that are statically typed is that they are too verbose. You have to write out ALL those type declarations just to get a working program. This hasn't ever been too inconvenient for me personally, but it can definitely clutter up your text editor. Type inference to the rescue! Below is a snippet of code in a language called Scala. Scala is statically typed, and it's compiler supports type inference, meaning it can infer types without being told explicitly what they are.

```scala
val x = 5
val s = "Hello, Scala!"
val evens = List(2, 4, 6, 8, 10)
 ```

If you run this in a Scala repl you'll see all of these type check, and you'll get outputs that show you what types they were inferred as.

```scala
x: Int = 5
s: String = "Hello, Scala"
evens: List[Int] = List(2, 4, 6, 8, 10)
```

This type inference feature can be used for more than just variable declarations. We can also use this for inferring function types.

```scala
def double(x: Int) = x * 2

```

The Scala compiler can infer that our function's return value is the result of running some operation `*` on our input value `x`. So, all the compiler needs to know is the return type of the `*` operation on type `Int` (which also happens to be `Int`).

You may be wondering why we had to explicitly write the type of our input value `x`. If Scala was truly supporting type inference we should NEVER have to write another type declaration again, right? Not really. It's imporant to keep in mind that type checkers aren't magical. In order to safely type check our functions the compiler needs some additional information about our input in many cases. In this particular function, `double`, our function body contains `x * 2`. In order for our function to be totally type safe we MUST enforce that our `x` value supports the `*` operation. Otherwise, we're risking a runtime error. So, a solution to this is to explicitly type our input. Later on in this post I'll explain another, more abstract way to achieve the same thing.



 ### Type Saftey:






 Algebraic Data Types:




 Parametricity and TypeClasses






