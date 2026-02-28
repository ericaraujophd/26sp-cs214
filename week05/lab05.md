---
title: ->lab05;
subtitle: // Lambda Functions
---

## Overview

A **lambda function** (or anonymous function) is a function that has no name. While this may seem unusual, anonymous functions are quite useful for situations where we need a short, temporary function -- for example, to pass as an argument to another function. Most modern languages support some form of anonymous functions, including all four of our languages.

This week, we will explore how to create and use lambda functions in Clojure, with a focus on:

- Creating anonymous functions using `fn` and the `#()` shortcut
- Binding anonymous functions to names using `def`
- Using higher-order functions like `map`, `filter`, and `reduce`
- Creating closures (functions that capture their environment)

Begin by accepting the project invitation from [GitHub Classroom: here](https://classroom.github.com/a/FHBLe8-f). Then, open VS Code through Coder and clone your repository.

Your repository contains a starter file called `lab05.clj`. You will write all of your solutions in this single file. Each exercise asks you to create a `def` binding with a specific name -- the starter file has placeholders for each one. When you are finished, the autograder will load your file and test each binding.

To run your file and check for errors as you work:

:::{code-block} bash
clj lab05.clj
:::

The starter file includes a `-main` function at the bottom with commented-out test prints for each exercise. As you complete each exercise, uncomment the corresponding test section to see your results alongside the expected values.

## Clojure

### Anonymous Functions

In Clojure, anonymous functions are created using the `fn` special form:

:::{code-block} yaml
<AnonFunction>  ::=  '(' 'fn' '[' <ParamList> ']' <Body> ')' ;
<ParamList>     ::=  <identifier> <ParamList> | empty ;
:::

For example, we can create a function that doubles a number:

:::{code-block} clojure
(fn [x] (+ x x))
:::

This creates a function, but since it has no name, we cannot call it later unless we either:

1. Call it immediately by wrapping it in parentheses with arguments, or
2. Bind it to a name using `def`.

To call an anonymous function immediately, we simply wrap it in parentheses and provide arguments:

:::{code-block} clojure
((fn [x y] (+ (* x x) (* y y))) 3 4)
; => 25
:::

Clojure also provides a shortcut notation for anonymous functions using `#(...)`, where `%` refers to the first (or only) argument, and `%1`, `%2`, etc. refer to the first, second, etc. arguments:

:::{code-block} clojure
#(+ % %)         ; same as (fn [x] (+ x x))
#(+ %1 %2)       ; same as (fn [x y] (+ x y))
:::

### Exercise 1a: Maximum Absolute Value

Create an anonymous function that, given three numbers, returns the maximum of their absolute values. Clojure provides `Math/abs` for computing absolute values and `max` for finding the maximum.

For example:

:::{code-block} clojure
((fn [x y z] (max (Math/abs x) (Math/abs y) (Math/abs z))) 1 -3 2)
; => 3
:::

Bind this function to the name `maxAbsVal` using `def`:

:::{code-block} clojure
(def maxAbsVal (fn [x y z] ...))
:::

Test your function with positive, negative, and mixed arguments:

:::{code-block} clojure
(maxAbsVal 1 -3 2)    ; => 3
(maxAbsVal -7 4 -2)   ; => 7
(maxAbsVal 0 0 0)     ; => 0
:::

### Exercise 1b: Calling Anonymous Functions

Consider these three anonymous functions:

:::{code-block} clojure
(fn [x y z] (+ x y z))
(fn [x] (nil? x))
(fn [] 17.2)
:::

For each one, write an expression that calls the function with appropriate arguments. Bind the result of each call to a name:

:::{code-block} clojure
(def expr1b-1 ((fn [x y z] (+ x y z)) ...))    ; call with three numbers
(def expr1b-2 ((fn [x] (nil? x)) ...))          ; call with an argument
(def expr1b-3 ((fn [] 17.2)))                    ; call with no arguments
:::

Each expression should execute without errors and produce a reasonable result.

### Higher-Order Functions

A higher-order function is a function that takes another function as an argument or returns a function as its result. Clojure provides several powerful higher-order functions.

**`map`** applies a function to every element of a sequence, returning a new sequence of results:

:::{code-block} clojure
(map (fn [x] (* x x)) [1 2 3 4])
; => (1 4 9 16)
:::

**`filter`** applies a predicate to every element, returning only elements for which the predicate returns true:

:::{code-block} clojure
(filter (fn [x] (even? x)) [1 2 3 4 5 6])
; => (2 4 6)
:::

**`reduce`** combines all elements of a sequence using a binary function:

:::{code-block} clojure
(reduce (fn [a b] (+ a b)) [1 2 3 4])
; => 10
:::

### Functions Returning Functions (Closures)

A function can also *return* another function as its result. The returned function can "capture" values from its parent's environment, forming what is called a **closure**.

For example, suppose we want a function that creates custom incrementers:

:::{code-block} clojure
(defn incMaker [incValue]
  (fn [x] (+ x incValue))
)
:::

`incMaker` takes a number `incValue` and returns a *new* anonymous function that adds `incValue` to its argument. We can use it to create specialized functions:

:::{code-block} clojure
(def inc5 (incMaker 5))
(def inc10 (incMaker 10))

(inc5 3)     ; => 8
(inc10 3)    ; => 13
:::

Notice that `inc5` and `inc10` are both functions, but each "remembers" the value of `incValue` from the call that created it. The returned function is a closure because it *closes over* the variable `incValue` from its enclosing scope.

Here is another example -- a function that builds custom greeting functions:

:::{code-block} clojure
(defn greetingBuilder [greeting]
  (fn [name] (str greeting ", " name "!"))
)

(def sayHello (greetingBuilder "Hello"))
(def sayHowdy (greetingBuilder "Howdy"))

(sayHello "Alice")    ; => "Hello, Alice!"
(sayHowdy "Bob")      ; => "Howdy, Bob!"
:::

This pattern -- a function that manufactures other functions -- is extremely powerful and is one of the key benefits of treating functions as first-class values.

### Exercise 2a: Binding a Lambda to a Name

Use `def` to bind an anonymous function to the name `squareSquare`. This function should take a single number and return the square of the square of that number (i.e., raise it to the 4th power).

:::{code-block} clojure
(def squareSquare (fn [x] ...))
:::

Test your function with these values:

:::{code-block} clojure
(squareSquare 2)     ; => 16
(squareSquare -2)    ; => 16
(squareSquare 3)     ; => 81
(squareSquare -3)    ; => 81
:::

### Exercise 3a: Negate

Define a function `negate` that takes a sequence of numbers and returns a new sequence with each number negated. You must use `map` with an anonymous function -- no recursion, loops, or custom helper functions are allowed.

:::{code-block} clojure
(def negate (fn [seq] ...))
:::

Test your function:

:::{code-block} clojure
(negate [-1 2 -3])     ; => (1 -2 3)
(negate [4 -5 6])      ; => (-4 5 -6)
:::

### Exercise 4a: Sum of Squares

Define a function `sumSquares` that takes a sequence of numbers and returns the sum of the squares of those numbers. Use `map` and `reduce` with anonymous functions -- no recursion, loops, or custom helper functions.

:::{code-block} clojure
(def sumSquares (fn [seq] ...))
:::

Test your function:

:::{code-block} clojure
(sumSquares '(1 2 3))           ; => 14
(sumSquares '(1 2 3 4 5))       ; => 55
(sumSquares [-1 -2 -3 0 1])     ; => 15
:::

### Exercise 5a: Function Factory

Write a function called `multiplierMaker` that takes a single number `n` and returns a *new function* that multiplies its argument by `n`. This exercise puts the closure concept from the teaching section above into practice.

:::{code-block} clojure
(def multiplierMaker (fn [n] ...))
:::

Then use `multiplierMaker` to create two specific multiplier functions and bind them to names:

:::{code-block} clojure
(def doubler  (multiplierMaker 2))
(def tripler  (multiplierMaker 3))
:::

Test your functions:

:::{code-block} clojure
(doubler 5)     ; => 10
(doubler -3)    ; => -6
(tripler 5)     ; => 15
(tripler -3)    ; => -9
((multiplierMaker 10) 4)   ; => 40
:::

### Submission

Your submission file should contain all of your `def` bindings for exercises 1a, 1b, 2a, 3a, 4a, and 5a. Make sure each binding uses the exact names specified in the exercises.

Commit and push your work to your repository when you are done.

## Rubric

| Task | Points |
|------|--------|
| Exercise 1a: `maxAbsVal` works correctly with positive, negative, and zero values | 15 |
| Exercise 1b: Three anonymous function calls bound to `expr1b-1`, `expr1b-2`, `expr1b-3` | 15 |
| Exercise 2a: `squareSquare` works correctly with positive and negative values | 15 |
| Exercise 3a: `negate` correctly negates all elements using `map` | 20 |
| Exercise 4a: `sumSquares` correctly computes sum of squares using `map` and `reduce` | 20 |
| Exercise 5a: `multiplierMaker` returns correct closure; `doubler` and `tripler` work | 15 |
| **Total** | **100** |
