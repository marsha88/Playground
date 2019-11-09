# Saving Engineering Time with Computing Time: An ode to Immutability

Earlier this month I had a quick conversation with Ben Wallace, my friend, roomate, and co-blogger. We have conversations all the time of course, but this one in particular happened to give me an idea for a blog post. We were discussing programming in some way or another, because what else are we gonna do on a sunny Saturday afternoon, and we got on the topic of immutability. Ben had made the point that in all his years of computer science courses, he always attributed immutability as being a bad thing; a way to slow down your programs. Now, I could obviously credit his assumption to the fact that he attended Indiana University, but school rivalries aside, I think this notion of immutability in programming is just commmonplace in the CS community. And for good reason! Because that's what we're TAUGHT! Many of the programming assignments I did while in school had both speed and memory usage requirements. 
The easiest way to accomplish both of these requirements is with mutation, and lots of it. For school assignments, yes mutation is probably still the route to take for its ease and performance benefits. However, for programs that are more than a couple of files (i.e real world software), the performance benefits of mutating state comes at a huge cost: program complexity and engineering time. So, in today's post, I'll be showing what an immutable style of programming looks like, and how it can save you hours of engineering time. 


Before jumping into a bunch of details, let's lay down a baseline understanding of what I mean my immutability and mutability. 

First, mutability...

```scala
var a = 5
a = 6
```

The concept of mutability should look very familiar to you. To say a block of code is mutable is just to say that we have some state in our program and we change/update that state at a later time (we mutate it). In this example we've just changed a variable `a` from 5 to 6. If mutability makes sense, then the idea of immutability should be quite easy to grasp. 

```scala
// the val keyword in scala is synonymous to const in other languages
val old = 5
val new = 6
```

Instead of updating a single value like we did before, we've created an entirely separate value to store the update. Although these code blocks are very simple and don't do much, this is at it's core the difference between mutability and immutability in programming.


Just to be clear, when I mention, immutable programs, I don't mean to say our programs should be totally immutable. To be totally honest, I think we need to a different word for what I'm actually trying to describe which is "immutability...when possible". Programs are absolutely useless without mutating data. Even the most basic "Hello, world" program writes to the console, ergo it mutates a file's data. So, yes, we're going to forever mutate data in our programs, but the amount of mutation in our programs can be reduced.

### Why should we avoid mutating data?

I've promised that immutability will save you engineering time. So, before revealing how we save that precious time, we need to ask an important question. What costs the most engineering time? Where is the bottleneck in the development process? You could probably give multiple answers to this ranging from debugging efforts, to properly understanding the product, or even the designing phase of new features. These are good answers, but I think there is a more general issue that is the root of all time sinkers in software development. Code complexity. Whether it be new development work, debugging, or testing, the bottleneck is performing these tasks in the face of code complexity. When it comes to programming, developers are their own enemy. Our jobs become orders of magnitude more difficult the more we continue adding to a project. The more we do our job, the MORE we have to do our job. Job security is great, but let's make our lives easier if we can. So, our goal is clear - we just need to make our code simpler.

A heard a quote once that was something like, "simple is good, but it isn't that simple". Unfortunately, there isn't a silver bullet solution to make programs simpler. In the current world of programming, the closest thing we'll get to a silver bullet solution is immutability. Reasoning about a program becomes incredibly more difficult when you have to think about mutable state. What variables in my program can change? What's changing them? When are they changed? Each of these questions must be running through your head when reasoning about code with mutable state on top of all the other logic you need to understand. 


Mention isolating state mutation.

Redux and Taskflow



Give examples of how immutability can change your code.

 


=> the huge payoffs
	- clean code
	- code you can reason about
	- concurrency

=> mention local mutation and why it isn't a bad thing. 

### Why waste the space?

So, you're still not super convinced that immutability is the path to take because you don't want to waste memory. That's fine. A concern about memory usage is valid and should be addressed. In 2019 the average laptop computer contains 8GB of memory, but for fun let's assume the customer of your application only has half that. So we're dealing with a computer that has 4GB of memory. To put 4GB of memory in perspective ___________. Unless you're writing software for microcontrollers that have 20Kb of memory, or you're building a massive world video games memory likely isn't a huge issue. So use the machine's memory - that's what it's there for. Switching to an immutable style of programming will also


### Persistent data structures




