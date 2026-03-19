---
title: ->lab07;
subtitle: // Arrays
---

## Overview

When representing *sequences of values*, most languages provide two fundamental data structures:

- The **array** (or **vector**), which stores values in adjacent memory locations, offering O(1) access time and O(N) insertion time.
- The **list**, which stores values in linked nodes, offering O(N) access time and O(1) insertion time.

This lab focuses on the first of these: **arrays**. We will study arrays across all four of our languages, comparing how each one handles:

1. Array declarations
2. Accessing array elements
3. Defining array parameters
4. Processing array contents

To make the comparison concrete, we will solve the same problem in each language: write a function that, given an array of numbers, computes and returns the **average** of its values.

Begin by accepting the project invitation from [GitHub Classroom: here](https://classroom.github.com/a/_QaTWm8A). Then, open VS Code through Coder and clone your repository.

Your repository contains starter files for all four languages: `Average.java`, `average.adb`, `average.clj`, and `average.rb`. Each file provides a program skeleton that you will complete. Take a moment to open each file and study what is already implemented before you begin.

The program skeleton for each language implements the following top-level algorithm:

:::{code-block} text
1. Define theArray as an array containing 9.0, 8.0, 7.0, 6.0.
2. Call average() to compute the average of theArray, and display the result.
:::

The `average()` subprogram has this specification:

:::{code-block} text
Receive: anArray, an array of numbers;
         itsSize, an integer equal to the size of anArray (if needed).
Precondition: itsSize >= 0.
Return: the average of the values in anArray.
:::

Note that `itsSize` may be unnecessary in languages whose arrays are "smart" (i.e., know their own size).

The algorithm for `average()` is:

:::{code-block} text
If the number of values in anArray <= 0,
   Return 0.0.
Otherwise,
   Return the sum of the values in anArray / the number of values in anArray.
:::

We will test using two arrays:

- `array0` -- an "empty" array
- `array1` -- an array of four values: 9.0, 8.0, 7.0, and 6.0

The order in which you complete the four language sections does not matter.

---

## Java

Open `Average.java` from your repository and take a moment to study it, noting how much of the algorithm is already in place. In this section you will fill in the array declarations and complete the `avg()` method.

Java offers two kinds of arrays -- those whose size is determined at *compile-time* and those determined at *run-time*. In this exercise we will work with compile-time-sized arrays only.

### Array Declarations

Java arrays can be defined using the following grammar:

:::{code-block} yaml
<ArrayDec>        ::=  <Type> '[]' <identifier> <Initializer> ';' ;
<Initializer>     ::=  ∅
                     | '=' '{' <ExpressionList> '}'
                     | '=' 'new' <Type> '[' <Expr> ']'
                     | '=' 'null' ;
<ExpressionList>  ::=  ∅ | <Expression> <MoreExpressions> ;
<MoreExpressions> ::=  ∅ | ',' <Expression> <MoreExpressions> ;
:::

Using this grammar, define `array1` as an array of four `double` values: 9.0, 8.0, 7.0, and 6.0. When that compiles correctly, use the grammar to define `array0` as an array containing no values.

Note that arrays are **objects** in Java -- `array0` and `array1` are handles that store references to array objects. An array variable's value can therefore be `null`, as shown in the fourth `Initializer` production.

Continue when both declarations compile correctly.

### Subprograms with Array Parameters

To write method `avg()`, we must declare an array parameter, check its length: if its length is zero, return 0.0; otherwise compute the sum of the values in that array and return that sum divided by the number of values in the array. Since the method returns a single numeric value, we can return that value through the normal function-return mechanism.

To sum the values in an array, we will need to implement our own `sum()` function. This function will need a local variable `total` in which to accumulate the sum of the array's values, and will use a `for` loop to iterate through the array's index values. Recall that the syntax of the Java `for` loop is just like that of C++:

:::{code-block} yaml
<ForLoop>  ::=  'for' '(' <Expression> ';' <Expression> ';' <Expression> ')' <Statement> ;
:::

An array parameter can be declared using syntax similar to that of a normal array variable:

:::{code-block} yaml
<ArrayParam>  ::=  <Type> '[' <Expr> ']' <identifier> ;
<Expr>        ::=  ∅ | <Expression> ;
:::

If `Expr` is omitted, an array of any size can be passed to the parameter.

Using this information, `sum()` can be implemented as follows:

:::{code-block} java
public static double sum(double[] theArray) {
    double total = 0.0;
    for (int i = 0; i < theArray.length; i++) {
        total += theArray[i];
    }
    return total;
}
:::

Using the preceding information, we can also define a stub for our `avg()` function:

:::{code-block} java
public static double avg(double anArray[]) {
}
:::

To fill in the body of the function, we can use Java's `if` statement to check that the array is not `null` and that its length (which is stored in the array's `length` data field) is not zero. To sum the values in `anArray`, we can use the `sum()` method we just wrote. Drawing on these observations, we can complete our stub:

:::{code-block} java
public static double avg(double anArray[]) {
    if (anArray == null || anArray.length <= 0)
        return 0.0;
    else
        return sum(anArray) / anArray.length;
}
:::

Uncomment the lines in `main()` that invoke `avg()` and verify that it works correctly with both `array0` and `array1`.

---

## Ada

Open `average.adb` from your repository and take a moment to study it before proceeding.

Ada is far more concerned with type-safety than C++, and so a bit more care is required to use its arrays. More precisely, if we wish to pass an array to a subprogram, Ada requires the argument array and the parameter array to be declared using *the same type-name*. We will therefore begin by declaring an array type named `Vector`, capable of storing an arbitrary number of `Float` values.

### Array Declarations

In Ada, a new type can be declared using the form:

:::{code-block} yaml
<TypeDec>  ::=  'type' <identifier> 'is' <TypeStructure> ';' ;
:::

where `identifier` is the name of the type being declared, and `TypeStructure` defines the structure of the new type. For a 1-dimensional array type, Ada permits a variety of structures fitting the following pattern:

:::{code-block} yaml
<TypeStructure>      ::=  'array' '(' <IndexDef> ')' 'of' <ElementType> ;
<IndexDef>           ::=  <ConstrainedRange> | <UnconstrainedRange> ;
<ConstrainedRange>   ::=  <ConstantExpression> '..' <ConstantExpression>
                        | <DiscreteTypeId> ;
<UnconstrainedRange> ::=  <DiscreteTypeId> 'range' '<>' ;
:::

Since we want an unconstrained array type (one that can be any size), we apply these rules and add the following type declaration to `average.adb`:

:::{code-block} ada
type Vector is array (Positive range <>) of Float;
:::

With this statement, we are declaring `Vector` as the name of a new type, whose structure is an array indexed by `Positive` (but unspecified) integers, and whose elements are `Float` values. Note that unlike C++, Ada uses **parentheses** for its arrays.

Now that we have our `Vector` array type declared, we can proceed to use this type to declare our array variables. Recall that Ada variable declarations have the form:

:::{code-block} yaml
<VariableDec>      ::=  <IdentifierList> ':' <Type> <Initializer> ';' ;
<IdentifierList>   ::=  <identifier> <MoreIds> ;
<MoreIds>          ::=  ',' <identifier> <MoreIds> | ∅ ;
<Initializer>      ::=  ':=' <ConstantExpression> ;
:::

To initialize the elements of an array variable to specific values, we need to know Ada's syntax for an array literal:

:::{code-block} yaml
<ArrayLiteral>    ::=  '(' <Expression> ',' <Expression> <MoreExpressions> ')'
                     | '(' <Expression> <MoreExpressions> ',' 'others' '=>' <Expression> ')' ;
<MoreExpressions> ::=  ',' <Expression> <MoreExpressions> | ∅ ;
:::

Ada will not allow us to create an array whose size is zero (or even one), so use this information to define `array0` as a `Vector` containing two zero values, and define `array1` as a `Vector` initialized to 9.0, 8.0, 7.0, 6.0. Note that when an unconstrained array-type variable is initialized using an array literal, the size of the array is the number of values in the literal.

### Subprograms with Array Parameters

To write subprogram `average()`, we must declare an array parameter, and check its length: if its length is zero, return 0.0; otherwise compute the sum of the values in that array and return that sum divided by the number of values in the array. Since the subprogram returns a single value, we define it as a function subprogram.

An array parameter can be declared using syntax similar to that of a normal array variable:

:::{code-block} yaml
<FunctionParamDec>  ::=  <IdentifierList> ':' <Type> ;
:::

Ada arrays are "smarter" than C++ arrays (i.e., they know their size), so we need pass no other information beyond the array itself.

Recall that Ada functions have this form:

:::{code-block} yaml
<FunctionDef>  ::=  'function' <identifier> '(' <ParameterDecList> ')' 'return' <Type> 'is'
                        <Declarations>
                    'begin'
                        <Statements>
                    'end' <identifier> ';' ;
:::

Use the preceding information to define a stub for our function, so that it has a single parameter named `anArray` of type `Vector`, and returns a `Float` value.

To fill in the body of the function, we can use the Ada `if` statement to check the value of the size of `anArray`. Ada arrays have a number of *attributes*, some of which are as follows:

:::{code-block} yaml
<ArrayAttributeExpr>  ::=  <identifier> '\'' <Attribute> ;
<Attribute>           ::=  'Length' | 'First' | 'Last' | 'Range' | ... ;
:::

These attributes may be used as follows for an array variable `A`:

- `A'Length` -- gives the number of elements in `A`
- `A'First` -- gives the index of the first element of `A`
- `A'Last` -- gives the index of the last element of `A`
- `A'Range` -- gives the range of index values, equivalent to `A'First..A'Last`

Use this information to implement the `if` statement required by our algorithm for function `average()`.

To sum the values in `anArray`, we will need to implement our own `sum()` function. This function will need a local variable `total` in which to accumulate the sum of the array's values, and will use the Ada `for` loop to iterate through the array's index values. Recall that the syntax of the `for` loop is:

:::{code-block} yaml
<ForLoop>  ::=  'for' <identifier> 'in' <Range> 'loop'
                    <Statements>
                'end' 'loop' ';' ;
:::

where `Range` specifies the range of values through which `identifier` is to iterate. Using this information, we can implement the `sum()` function as follows:

:::{code-block} ada
function sum(A : Vector) return Float is
    Total : Float := 0.0;
begin
    for I in A'Range loop
        Total := Total + A(I);
    end loop;
    return Total;
end sum;
:::

Ada requires that every identifier be declared *before* it is used. Since our `average()` function will use `sum()`, this means that you should define `sum()` *before* the definition of `average()`.

You now have most of the functionality needed to complete the `average()` function in `average.adb`. Do so, including the necessary documentation for the function.

The one remaining problem is that Ada does not permit us to mix integers and reals in an arithmetic expression, so the division of the sum of the values in the array (a real) by the number of values in the array (an integer) generates a compilation error. To resolve this without losing any information, we should convert the (integer) number of values to a real value. This can be accomplished using the `Float()` conversion function:

:::{code-block} ada
Float(IntegerExpression)
:::

Use this within your division subexpression to convert the number of values in the array from an integer to a real.

Compile and test your code for correctness. When this much is correct, continue to the following experiment.

**Experiment.** Because `sum()` uses `A'Range`, it works regardless of how the array is indexed. As an experiment, comment out the `for` loop line that refers to `A'Range` and replace it with an equivalent line that uses a `ConstrainedRange` (see the BNF above), starting with the index of the first element of `anArray` and ending with the index of its last element. Then recompile and retest your code for correctness.

---

## Clojure

Open `average.clj` from your repository and take a few minutes to study it before proceeding.

To run your file and check for errors as you work:

:::{code-block} bash
clj average.clj
:::

The skeleton should run without errors -- take a moment to verify that it executes correctly before you proceed further. Note also that the `-main` function contains lines that are commented out. We will be uncommenting these lines later in the exercise, after we have written the functions they call.

### Array Declarations

Clojure supports two kinds of arrays:

- **Java-style arrays** -- mutable, interoperable with Java
- **Lisp-style arrays called *vectors*** -- immutable, idiomatic in functional Clojure

In this exercise we will be examining only the vector, as the functional programming style is to use immutable objects.

You may recall that we used a vector back in [lab05](https://cs.calvin.edu/courses/cs/214/meyer/labs/05) as a means of having a function return two values. As we saw there, a vector can be constructed using the `vector()` function, and the `get()` function can be used to retrieve the value at a given index. These are two of many predefined functions Clojure provides for vectors; check out the vector section of [the Clojure Cheatsheet](https://clojure.org/api/cheatsheet).

A vector literal consists of a sequence of values surrounded by square brackets (`[` and `]`). In the `-main()` function of `average.clj`, the following lines use vector literals to define the `emptyVec` and `testVec` variables:

:::{code-block} clojure
(let
  [emptyVec []
   testVec  [9.0 8.0 7.0 6.0]]
  ...)
:::

Perhaps less obviously, the first argument of Clojure's `let` function is itself a vector-literal of *bindings*. Each binding defines a local variable by associating an identifier with a value. Clojure's syntax is defined using Clojure!

Note that Clojure's `print()` and `println()` functions will correctly print a vector for us.

### Subprograms with Array Parameters

One of the subprograms we need to write is `average()`. To write this subprogram, we must pass a vector parameter and check it: if it is empty, return 0.0; otherwise compute the sum of the values in that vector and return that sum divided by the number of values in the vector.

Recall the structure of a Clojure function definition:

:::{code-block} yaml
<FunctionDef>    ::=  '(' 'defn' <identifier> '[' <Parameters> ']'
                       <Documentation>
                       <ExpressionList> ')' ;
<Parameters>     ::=  <identifier> <Parameters> | ∅ ;
<Documentation>  ::=  '"' <Characters> '"' | ∅ ;
<ExpressionList> ::=  <Expression> <ExpressionList> | ∅ ;
:::

Note that unlike our other languages, LISP-family languages do not require any types to be specified, because no compile-time type-checking is performed; instead, types are checked when arguments are passed at run-time. Also, Clojure vectors know their sizes, making it unnecessary to pass the size of the vector to our `average()` function.

Using this information, create a stub for function `average()`, having a single parameter named `aVec`.

To guard against the user passing a non-vector argument to our function, we can adjust our algorithm slightly:

:::{code-block} text
If aVec is a vector:
  If aVec is empty:
    return 0.0.
  Otherwise
    return the sum of the values in aVec /
           the number of values in aVec.
:::

We can check the first condition using the Clojure `if` and `vector?` functions:

:::{code-block} yaml
<Expression>  ::=  '(' 'vector?' <Object> ')' ;
:::

The `vector?` function returns `true` if `Object` is a vector, and returns `nil` otherwise.

Clojure vectors are "smart", in that they know the number of items they contain. In situations like ours, Clojure's `empty?()` function can be used to determine if a vector contains any values:

:::{code-block} yaml
<Expression>  ::=  '(' 'empty?' <Container> ')' ;
:::

When `Container` is any container data structure (i.e., a vector, list, etc.), the `empty?()` function returns `true` if the container contains no values, and returns `false` otherwise.

We can thus encode our second condition using an `if` function and the `empty?()` function. Take a moment to add these `if`, `vector?`, and `empty?` function calls to your `average()` stub.

For the rest of the `average()` function, we can use the `/` operator to perform the division, and we can use the `count()` function to compute its denominator (the number of values in `aVec`):

:::{code-block} yaml
<Expression>  ::=  '(' 'count' <Collection> ')' ;
:::

Given a `Collection` object (i.e., a vector, a list, etc.), the `count()` function returns the number of values in that object.

That leaves us the problem of computing the numerator -- the sum of the values in `aVec`. Clojure has no built-in function to sum the values in a vector, so let's figure that out next.

We can specify the problem as follows:

:::{code-block} text
Receive: aVec, a vector of numbers.
Return: the sum of the numbers in aVec.
:::

Take a moment to use this information to create a stub for function `sum()` at the appropriate spot within `average.clj`.

### Summing the Values in a Vector

There are a variety of ways to solve this problem in Clojure. We are going to look at two of them: a harder way and an easier way.

**The Harder Way.** The harder way is to solve the problem recursively. We would *have* to use this approach if Clojure did not provide an easier way, and since many languages do not provide the easier way, let's practice our recursive thinking skills and work through the logic.

Thinking recursively, we can identify this base case:

:::{code-block} text
Basis: aVec is empty:
      -> return 0.0.
:::

For the induction step, we can solve non-trivial cases this way:

:::{code-block} text
I-Step: aVec is not empty.
      -> return the last value in aVec + sum(aVec without its last item)
:::

For safety, we should check that `aVec` is indeed a vector. We can consolidate all of these thoughts into this algorithm:

:::{code-block} text
If aVec is a vector:
  If aVec is empty:
     return 0.0.
  Otherwise
     return the last value in aVec + sum(aVec without its last value).
:::

As we have seen before, we can use the `if` and `vector?` functions to determine if `aVec` is a vector. Likewise, use another `if` and `empty?()` to implement the nested-if step. Take a moment to add this much logic to your `sum()` stub.

Having that nested `if` return `0.0` is easy, so add that to your stub.

That leaves us with two challenges:

1. Accessing the last value in `aVec`
2. Computing `aVec` without its last value

We can access the last value in a Clojure vector using the `peek()` function:

:::{code-block} yaml
<Expression>  ::=  '(' 'peek' <VectorObject> ')' ;
:::

Given a vector argument, `peek()` returns its final value.

We can compute a vector without its last value using the `pop()` function:

:::{code-block} yaml
<Expression>  ::=  '(' 'pop' <VectorObject> ')' ;
:::

Given a vector argument, `pop()` returns that vector without its final value.

Combining all of these observations, we might define `sum()` as follows:

:::{code-block} clojure
;; harder (recursive) solution
(defn sum [aVec]
  (if (vector? aVec)      ; if aVec is a vector
    (if (empty? aVec)     ;   if aVec is empty:
      0.0                 ;     return 0
      (+ (peek aVec)      ;   else return the last value
         (sum (pop aVec)) ;    + sum(all but the last value)
      )
    )
  )
)
:::

In `average.clj`, make your definition of `sum()` consistent with the one above. Make sure that you understand how it works before continuing.

Finally, use our new `sum()` function to complete function `average()`. Then uncomment the lines in `-main()` that display the results of `sum()` and `average()`. Save your changes, run the program, and verify that it works as expected. Continue when this much is working correctly.

**The Easier Way.** The easier way to define a function to sum the values in a vector is as follows:

:::{code-block} clojure
;; easier (non-recursive) solution
(defn sum2 [aVec]
  (if (vector? aVec)      ; if aVec is a vector:
    (if (empty? aVec)     ;   if aVec is empty:
      0.0                 ;    return 0
      (reduce + aVec)     ;   else reduce aVec using +
    )
  )
)
:::

Aside from its name, this version only differs from our first version in how it sums the numbers in a non-empty vector. It does so using the LISP-family `reduce()` function, which has the following syntax:

:::{code-block} yaml
<Expression>  ::=  '(' 'reduce' <Operator> <Collection> ')' ;
:::

Given a commutative `Operator` and a `Collection` data structure, the `reduce` function returns the result of using `Operator` to combine the values in `Collection`. Thus, for non-empty vector objects, the expression:

:::{code-block} clojure
(reduce + aVec)
:::

will sum all the numbers in `aVec` for us!

Copy-paste the definition of `sum2()` into your source file, below the definition of `sum()`. Then uncomment the lines in the `-main()` function that test `sum2()`. Save your changes and verify that `sum()` and `sum2()` produce the same results before continuing.

---

## Ruby

Open `average.rb` from your repository. Today we'll be looking at one of Ruby's primary data structures: the `Array` class. It is important to remember that `Array` is in fact a **class** -- much like `String` or `Hash` -- and not a simple primitive. This means that like any other Ruby object, it has a host of methods built right in, including several iteration methods that will come in useful when we get around to finding the sum of our array.

To verify your code as you work:

:::{code-block} bash
ruby average.rb
:::

### Array Declarations

Before we get to the methods, we need to declare an array. Ruby provides several ways to instantiate the `Array` class:

:::{code-block} yaml
<ArrayDecClass> ::=  'Array.new'
                   | 'Array.new' '(' <IntegerSize> <FillObject> ')'
                   | 'Array.new' '(' <ArrayObject> ')'
                   | 'Array.new' '(' <IntegerSize> ')' '{' '|' <Index> '|' <StatementList> '}'
                   | '[' <ElementList> ']' ;
<IntegerSize>   ::=  <Integer> ;
<FillObject>    ::=  ',' <Object> | ∅ ;
<Index>         ::=  <identifier> ;
<StatementList> ::=  <Statement> <StatementList> | ∅ ;
<ElementList>   ::=  <Object> | ∅ ;
:::

**Note:** The vertical bars surrounded by single quotes in the BNF above are part of the Ruby array syntax, not BNF metacharacters. They appear in the next BNF as well -- so watch out!

As you can see, there are many ways to create an array, with each suited to a certain situation. Today we'll be using the **array literal** form since it's the quickest way to initialize an array with specific values. You can use it to initialize an array like this: `[1, 2, 3]`.

Using this information, define `array0` as an empty array, and `array1` as an array containing 9.0, 8.0, 7.0, and 6.0. Use Ruby to verify that this much is syntactically correct before proceeding.

### Subprograms with Array Parameters

Now that we have defined an array, let's work on the `average()` method. Since Ruby has "smart" arrays that know their size, the `average()` method needs to receive the array, and nothing else.

Once our method has the array, it needs to see if there's anything in it. This is easy to do with an `if` statement and the `Array` class's `empty?` method (which some will argue is "prettier" than saying `if arr.size > 0`). You'll want to return `0.0` if the array is empty, which can be done with the `return` keyword. Ruby does have a `return` statement; it's just not usually required.

If the array is not empty, then you'll need to return the sum of the array's values divided by the number of values it contains. We can determine the number of values in an array using its `size` attribute:

:::{code-block} ruby
anArray.size
:::

To compute the array's sum, we will define a function `sum()` to go through each element in the array and add it to a `total` variable. We could do this in the usual way: using a `for` loop and the subscript method `[]`. However, Ruby provides a better way that is worth mastering: the `Array` class's `each` method. Since this is a pretty important method of the `Array` class, let's look at it using a BNF:

:::{code-block} yaml
<ArrayEachStmt>  ::=  <identifier>'.each' '{' '|' <Item> '|' <Statement> '}'
                     | <identifier>'.each' 'do' '|' <Item> '|' <StatementList> 'end' ;
<Item>           ::=  <identifier> ;
:::

When an `each` message is sent to an array, the `Statement` or `StatementList` is performed for each item in the array, and those statements can access the item using the `Item` block-parameter.

Use a `total` variable and the `each` method with an `item` parameter to compute the sum of the array. If you first initialize your `total` variable to zero, the body of your `sum()` method can be as short as 3 lines. You can test your method by uncommenting the `puts` statements in the `main()` function that invoke `sum()`.

Once you can compute the sum of the values in an array, finding the average is as simple as implementing this algorithm:

:::{code-block} text
If the number of values in anArray <= 0,
   Return 0.0.
Otherwise,
   Return the sum of the values in anArray / the number of values in anArray.
:::

Then test your function by uncommenting the appropriate statements in the `main()` method.

---

## Submission

When all four language files are complete and producing correct output, commit and push your work to your repository.

Make sure each of the four files -- `Average.java`, `average.adb`, `average.clj`, and `average.rb` -- contains a working implementation of both `sum()` and `average()` before submitting.

## Rubric

| Task | Points |
|------|--------|
| Java: `array0` and `array1` declared correctly | 5 |
| Java: `avg()` returns correct results for empty and non-empty arrays | 20 |
| Ada: `array0` and `array1` declared correctly using `Vector` type | 5 |
| Ada: `average()` returns correct results; `Float()` conversion used appropriately | 20 |
| Clojure: `sum` (recursive) implemented correctly | 10 |
| Clojure: `sum2` (using `reduce`) implemented correctly | 5 |
| Clojure: `average` returns correct results for empty and non-empty vectors | 10 |
| Ruby: `sum()` implemented using `each` | 10 |
| Ruby: `average()` returns correct results for empty and non-empty arrays | 15 |
| **Total** | **100** |