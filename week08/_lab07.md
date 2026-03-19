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

Begin by accepting the project invitation from [GitHub Classroom: here](https://classroom.github.com/a/PLACEHOLDER). Then, open VS Code through Coder and clone your repository.

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

Using this grammar, define `array1` as an array of four `double` values: 9.0, 8.0, 7.0, and 6.0. Then define `array0` as an array containing no values.

Note that arrays are **objects** in Java -- `array0` and `array1` are handles that store references to array objects. An array variable's value can therefore be `null`, as shown in the fourth `Initializer` production.

### Subprograms with Array Parameters

An array parameter is declared using syntax similar to a normal array variable:

:::{code-block} yaml
<ArrayParam>  ::=  <Type> '[' <Expr> ']' <identifier> ;
<Expr>        ::=  ∅ | <Expression> ;
:::

If `Expr` is omitted, an array of any size can be passed to the parameter.

To sum the values in an array, we implement a helper `sum()` function using a local accumulator variable and a `for` loop. Recall that Java's `for` loop syntax (identical to C++) is:

:::{code-block} yaml
<ForLoop>  ::=  'for' '(' <Expression> ';' <Expression> ';' <Expression> ')' <Statement> ;
:::

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

With `sum()` defined, we can write `avg()`. We use Java's `if` statement to check that the array is not `null` and that its `length` field is nonzero:

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

Ada requires that both an array argument and its corresponding array parameter be declared using *the same type name*.

### Array Declarations 

We therefore begin by declaring an array type named `Vector`, capable of storing an arbitrary number of `Float` values.

A new type is declared using the form:

:::{code-block} yaml
<TypeDec>           ::=  'type' <identifier> 'is' <TypeStructure> ';' ;
<TypeStructure>     ::=  'array' '(' <IndexDef> ')' 'of' <ElementType> ;
<IndexDef>          ::=  <ConstrainedRange> | <UnconstrainedRange> ;
<ConstrainedRange>  ::=  <ConstantExpression> '..' <ConstantExpression>
                       | <DiscreteTypeId> ;
<UnconstrainedRange>::=  <DiscreteTypeId> 'range' '<>' ;
:::

Since we want an unconstrained array type (one that can be any size), we add this type declaration:

:::{code-block} ada
type Vector is array (Positive range <>) of Float;
:::

This declares `Vector` as an array indexed by `Positive` (but unspecified) integers, with `Float` elements. Note that Ada uses **parentheses** for array indexing, unlike C++ or Java.

Array variables are declared using the following forms:

:::{code-block} yaml
<VariableDec>      ::=  <IdentifierList> ':' <Type> <Initializer> ';' ;
<IdentifierList>   ::=  <identifier> <MoreIds> ;
<MoreIds>          ::=  ',' <identifier> <MoreIds> | ∅ ;
<Initializer>      ::=  ':=' <ConstantExpression> ;
<ArrayLiteral>     ::=  '(' <Expression> ',' <Expression> <MoreExpressions> ')'
                       | '(' <Expression> <MoreExpressions> ',' 'others' '=>' <Expression> ')' ;
<MoreExpressions>  ::=  ',' <Expression> <MoreExpressions> | ∅ ;
:::

Note that Ada does not permit arrays of size zero or one. Define `array0` as a `Vector` containing two zero values, and define `array1` as a `Vector` initialized to 9.0, 8.0, 7.0, 6.0. When an unconstrained array-type variable is initialized with an array literal, its size is determined by the number of values in the literal.

### Subprograms with Array Parameters

An array parameter is declared using the same form as any other parameter:

:::{code-block} yaml
<FunctionParamDec>  ::=  <IdentifierList> ':' <Type> ;
:::

Ada arrays are "smart" -- they know their own size -- so no size argument is needed.

Ada functions have the form:

:::{code-block} yaml
<FunctionDef>  ::=  'function' <identifier> '(' <ParameterDecList> ')' 'return' <Type> 'is'
                        <Declarations>
                    'begin'
                        <Statements>
                    'end' <identifier> ';' ;
:::

Ada arrays have several built-in **attributes**, accessed using the `'` notation:

:::{code-block} yaml
<ArrayAttributeExpr>  ::=  <identifier> '\'' <Attribute> ;
<Attribute>           ::=  'Length' | 'First' | 'Last' | 'Range' | ... ;
:::

For an array `A`:

- `A'Length` -- the number of elements in `A`
- `A'First` -- the index of the first element
- `A'Last` -- the index of the last element
- `A'Range` -- the full index range, equivalent to `A'First..A'Last`

Ada's `for` loop has this form:

:::{code-block} yaml
<ForLoop>  ::=  'for' <identifier> 'in' <Range> 'loop'
                    <Statements>
                'end' 'loop' ';' ;
:::

Using these constructs, `sum()` can be implemented as follows:

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

Note that Ada requires every identifier to be declared *before* it is used, so `sum()` must be defined *before* `average()`.

Ada does not permit mixing integers and reals in arithmetic expressions. To divide the sum (a real) by the array length (an integer), convert the integer using `Float()`:

:::{code-block} ada
Float(IntegerExpression)
:::

Complete the `average()` function using `sum()`, the array attributes, and the `Float()` conversion where needed.

**Experiment.** Because `sum()` uses `A'Range`, it works regardless of how the array is indexed. As an experiment, comment out the `for` loop line using `A'Range` and replace it with an equivalent line using an explicit `ConstrainedRange` (see the BNF above) that runs from `A'First` to `A'Last`. Recompile and verify that the results are the same.

---

## Clojure

Open `average.clj` from your repository and take a few minutes to study it before proceeding. The skeleton should run without errors -- verify this before making any changes.

To run your file and check for errors as you work:

:::{code-block} bash
clj average.clj
:::

Note that the `-main` function contains several commented-out lines. Uncomment each one after you have written the function it calls.

### Array Declarations

Clojure supports two kinds of sequences:

- **Java-style arrays** -- mutable, interoperable with Java
- **Vectors** -- immutable, idiomatic in functional Clojure

In this exercise we use **vectors**, since functional programming style favors immutable data. A vector literal is written using square brackets:

:::{code-block} clojure
(let
  [emptyVec []
   testVec  [9.0 8.0 7.0 6.0]]
  ...)
:::

The first argument to `let` is itself a vector literal of *bindings*, each associating an identifier with a value. Note that `print` and `println` will correctly display vector values.

### Subprograms with Array Parameters

Recall the structure of a Clojure function definition:

:::{code-block} yaml
<FunctionDef>    ::=  '(' 'defn' <identifier> '[' <Parameters> ']'
                       <Documentation>
                       <ExpressionList> ')' ;
<Parameters>     ::=  <identifier> <Parameters> | ∅ ;
<Documentation>  ::=  '"' <Characters> '"' | ∅ ;
<ExpressionList> ::=  <Expression> <ExpressionList> | ∅ ;
:::

Unlike our other languages, Clojure requires no type annotations -- types are checked at runtime when arguments are passed.

To guard against non-vector arguments, the algorithm for `average()` is:

:::{code-block} text
If aVec is a vector:
  If aVec is empty:
    return 0.0.
  Otherwise:
    return sum(aVec) / count(aVec).
:::

Clojure provides the following predicates and functions for working with vectors:

:::{code-block} yaml
; Check whether an object is a vector
<Expression>  ::=  '(' 'vector?' <Object> ')' ;

; Check whether a container is empty
<Expression>  ::=  '(' 'empty?' <Container> ')' ;

; Return the number of items in a collection
<Expression>  ::=  '(' 'count' <Collection> ')' ;
:::

### Summing the Values in a Vector

There are two approaches to summing a vector's values.

**The Recursive Way.** Thinking recursively:

- *Base case*: the vector is empty → return 0.0
- *Induction step*: return the last value plus the sum of the vector without its last value

Clojure provides `peek` and `pop` to support this pattern:

:::{code-block} yaml
; Return the last value of a vector
<Expression>  ::=  '(' 'peek' <VectorObject> ')' ;

; Return the vector without its last value
<Expression>  ::=  '(' 'pop' <VectorObject> ')' ;
:::

Putting this together:

:::{code-block} clojure
;; recursive solution
(defn sum [aVec]
  (if (vector? aVec)
    (if (empty? aVec)
      0.0
      (+ (peek aVec)
         (sum (pop aVec))))))
:::

**The Easier Way.** Clojure's `reduce` function can collapse a collection to a single value using any binary operator:

:::{code-block} yaml
<Expression>  ::=  '(' 'reduce' <Operator> <Collection> ')' ;
:::

This leads to a much more concise definition:

:::{code-block} clojure
;; non-recursive solution using reduce
(defn sum2 [aVec]
  (if (vector? aVec)
    (if (empty? aVec)
      0.0
      (reduce + aVec))))
:::

Add both `sum` and `sum2` to your source file and verify they produce the same results. Then use `sum` to complete your `average()` function. Uncomment the test lines in `-main` and verify that the program runs correctly.

---

## Ruby

Open `average.rb` from your repository. Today we'll be looking at one of Ruby's primary data structures: the `Array` class. It is important to remember that `Array` is in fact a **class** -- not a primitive -- with a rich set of built-in methods, including several iteration methods that will be useful for computing the sum of an array's values.

To verify your code as you work:

:::{code-block} bash
ruby average.rb
:::

### Array Declarations Arrays can be instantiated in several ways:

:::{code-block} yaml
<ArrayDec>      ::=  'Array.new'
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

**Note:** The vertical bars surrounded by single quotes in the BNF above are part of Ruby's block syntax, not BNF metacharacters.

For this exercise, use the **array literal** form -- the quickest way to initialize an array with specific values. Define `array0` as an empty array and `array1` as an array containing 9.0, 8.0, 7.0, and 6.0.

### Subprograms with Array Parameters

Because Ruby arrays are "smart", `average()` only needs to receive the array itself.

Use the `empty?` method to check whether the array contains any values, and return `0.0` if it does. The number of elements in an array is given by its `size` attribute:

:::{code-block} ruby
anArray.size
:::

To sum the values, define a helper `sum()` method. Rather than using a `for` loop and subscript indexing, use Ruby's `Array#each` method, which is idiomatic Ruby:

:::{code-block} yaml
<ArrayEachStmt>  ::=  <identifier>'.each' '{' '|' <Item> '|' <Statement> '}'
                     | <identifier>'.each' 'do' '|' <Item> '|' <StatementList> 'end' ;
<Item>           ::=  <identifier> ;
:::

When `each` is called on an array, the block body executes once for each element, with the element bound to the block parameter (`Item`). Using a `total` accumulator initialized to zero, the body of `sum()` can be written in just a few lines.

Once `sum()` is working, implement `average()` using the following algorithm:

:::{code-block} text
If anArray is empty,
   Return 0.0.
Otherwise,
   Return sum(anArray) / anArray.size.
:::

Uncomment the test `puts` statements in `main()` and verify that both `sum()` and `average()` produce correct results.

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