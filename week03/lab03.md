---
title: "->lab03;"
subtitle: // Controlling Behavior through Repetition
---

## Overview

Today's exercise involves using BNF to examine the syntax of the construct that provides repetitive execution in each of our four languages. Like last week, we will use BNF to solve the same problem in each of our languages, to compare their repetition constructs.

This week's problem is this: given three real values `start`, `stop` and `increment`, display a table of base-10 logarithms beginning at start, ending at stop at intervals of increment. For example, if start is 1, stop is 2, and increment is 0.5, then our program should display something like this:

:::{code-block} bash
The logarithm of 1 is 0
The logarithm of 1.5 is 0.176091
The logarithm of 2 is 0.30103
:::

Begin by accepting the invitation to join the [GitHub Classroom assignment](https://classroom.github.com/a/6vdaUjqo) for this project. Then, open VS Code through Coder and clone your repository.

In the repository you will see the code skeletons:

- LogTable.java
- log_table.adb, logTable.clj, and logTable.rb.
- logTable.clj
- logTable.rb

Each of these skeletons provides a partial "program" to solve this problem, using the following simple algorithm:

:::{code-block} text
:linenos:
Get start, stop, and increment.
For count = start to stop by increment:
      Display count and the logarithm of count
:::

We will be implementing this algorithm in each of the four languages. Since Step 2 of this algorithm uses a counting loop, our "program" will need to use whatever repetition construct is available for a counting loop in a given language.

As before, we will lead you through the process using Java, after which you will apply the same techiques to solve the problem in the other three languages. As usual, you should begin with the Java introduction, but the order in which you do the three exercises does not matter.

## Java

Begin by opening the file *LogTable.java* in your VS Code. Take a moment to study it, to see how it implements our basic algorithm.

To generate our table of logarithms, we need to use a Java repetition statement, of which there are three:

:::{code-block} yaml
<Statement>   ::=   <WhileLoop> | <DoLoop> | <ForLoop> ;
<WhileLoop>   ::=   'while' '(' <Expression> ')' <Statement> ;
<DoLoop>      ::=   'do' <Statement> 'while' '(' <Condition> ');' ;
<ForLoop>     ::=   'for' '(' <Expression> ';' <Expression> ';' <Expression> ')' <Statement> ;
:::

Our particular problem is an example of a counting problem, in which we want to count from `start` to `stop` by intervals of `increment`, displaying the value of `count` and its logarithm. For counting problems, the Java `for` loop provides the most convenient notation, but you'll practice that in this week's project so we'll use a `while` loop today.

When execution reaches a `for` loop, the first `<Expression>` is evaluated, usually providing for initialization of a loop-control variable (i.e., count). If the second `<Expression>` evaluates to true (non-zero), then `<Statement>` is executed, after which the third `<Expression>` is executed. The second `<Expression>` thus serves as a continuation-condition for the loop, with the third `<Expression>` serving as an increment-expression. (Any or all of these `<Expression>`s can be omitted, if the second `<Expression>` is omitted, true is taken as a default, providing an indeterminate loop construct.)

Output in Java is accomplished via the usual output expression, while to compute the base-10 logarithm of count, we can use the `log10()` method. This method is part of the `Math` class, which is included in the *java.lang* package. The *java.lang* package is included implicitly in all Java programs, so you do not need to used the `import` command to make the class or method available.

Drawing on these observations, write a `while` loop that generates the required table.

Compile and test your code for correctness. Verify that it works correctly for the input values 1, 10, 0.5.

That concludes the Java part of the lab.

## Ada

Begin by opening the file *log_table.adb*. Take a moment to study it, to see how it implements the first part of our algorithm.

To complete the program, we must understand the Ada repetition statements. Ada provides one `loop` statement, which has three different forms:

:::{code-block} yaml
<Statement>      ::=   <LoopStatement> ;
<LoopStatement>  ::=   <LoopClause>
                        'loop'
                           <StatementList>
                        'end' 'loop' ;
<StatementList>  ::=   <Statement> ';' <MoreStatements> ;
<MoreStatements> ::=   <Statement> ';' <MoreStatements> | Ø ;
<LoopClause>     ::=   Ø | <ForPrefix> | <WhilePrefix>  ;
<ForPrefix>      ::=   'for' <identifier> 'in' <ReverseOption> <Range> ;
<ReverseOption>  ::=   'reverse' | Ø ;
<Range>          ::=   <Expression> '..' <Expression> ;
<WhilePrefix>    ::=   'while' <BooleanExpression> ;
:::

The Ada `loop` statement thus has three forms:

- A general purpose *indeterminate* loop, generated by the case where `<LoopClause>` is replaced by `Ø`.
- A counting loop, generated by the case where `<LoopClause>` is replaced by the `<ForPrefix>` production.
- A pretest general loop, generated by the case where `<LoopClause>` is replaced by the `<WhilePrefix>` production.

### The First Form

In the first form, control must be explicitly provided to allow the loop to terminate. This is usually accomplished with an `exit-when` statement:

:::{code-block} yaml
<ExitWhenStmt>  ::=   'exit' 'when' '(' <TerminationCondition> ')' ;
:::

When such a statement executes, it transfers control to the first statement following the loop statement containing it.

### The Second Form

In the second form, the loop's identifier serves as a loop-control variable. This variable need not be declared -- it's type is determined by the types of the `<Expressions>` that appear in `<Range>`. This counting loop has two sub-forms: a count-up form, and a count-down form. The count-up form is the default, the count-down form is achieved by using the keyword `reverse`.

When control reaches a count-up loop, the following actions occur:

1. Its `identifier` is initialized to the first `<Expression>`.
2. Its `identifier` is then compared to the value of the second `<Expression>` using the $<=$ relation.
3. If this relation is true, then the loop's `<StatementList>` is executed.
4. The integer 1 is added to the value of `identifier`.
5. Control then returns to (2).

When control reaches a count-down loop, the behavior differs in 4 ways:

1. Its `identifier` is initialized to the second `<Expression>`.
2. Its `identifier` is then compared to the value of the **first** `<Expression>` using the `>=` relation.
3. If this relation is true, then the loop's `<StatementList>` is executed.
4. The integer 1 is **subtracted** from the value of `identifier`.
5. Control then returns to (2).

Note that a counting loop's `<Range>` is always given in ascending form, regardless of whether it is a count-up loop or a count-down loop.

### The Third Form

The third form is a traditional while loop, that exhibits pretest behavior and continues repetition so long as its `<BooleanExpression>` evaluates to true.

### Back To Our Problem

While our problem is a counting problem, the Ada counting loop is limited to counting by ones, so it will not serve our purpose. Instead, we will need to use one of the other loops, and manage the repetition ourselves. Choose either one, and add the necessary code to your program to elicit the same behavior as *log_table.cpp*.

To output the values in our table, we can use Ada output statements:

:::{code-block} yaml
<OutputStmt>     ::=   <PutStmt> | <New_Line> | ... ;
<PutStmt>        ::=   'Put' '(' <Expression> ')' ;
<Expression>     ::=   <StringExpression> | <FloatExpression> | <IntExpression> | ... ;
:::

provided our program has the `with` and `uses` directives for the packages to perform I/O with a given type. (E.g., `Ada.Text_IO` for the `String` type, `Ada.Float_Text_IO` for the `Float` type, `Ada.Integer_Text_IO` for the `Integer` type, and so on.)

To compute the logarithm of our `count`, we can use the Ada `log()` function. The `log()` function is actually overloaded with two definitions:

1. `log(n)` computes the natural log of `n`; and
2. `log(n, base)` computes the log of `n` in base `base`. (Both arguments must be real values.)

Since we are computing base-10 logarithms, you will need to use the second form.

To use this function (or the other basic mathematical functions), our program must have the `with` and `uses` directives for the package `Ada.Numerics.Elementary_Functions`, so you will need to modify the beginning of the program accordingly.

Using this information, add the statements to *log_table.adb* to make it display a table of logarithms similar to that of *log_table.cpp*. Compile and test your code for correctness; show that it works correctly for the input values 1, 10, 0.5.

That concludes the Ada part of this lab.

## Clojure

Move your Clojure files to the appropriate directories. Begin by creating `clojure/` and `clojure/src/` folders. Move `logTable.clj` to your `clojure/src/` folder. Then open `logTable.clj` to edit. Take a moment to study it, to see how it implements our basic algorithm, and compare it to the other "programs" you've seen. Make sure you are in the right directory when compiling and running your program, so that the Clojure compiler can find your source file.

Since all computing is accomplished via functions in LISP-family languages, we will define a `displayLogTable()` function whose specification is as follows:

:::{code-block} text
:linenos:
Receive: start, stop, step, three numbers.
Output: A table of logarithms,
      beginning with start, ending at stop,
      and with an interval of step.
:::

As we have seen before, a Clojure function has the following structure:

:::{code-block} yaml
<FunctionDef>     ::=   '(' 'defn' <identifier> '[' <Parameters> ']'
                        <Documentation>
                        <ExpressionList> ')' ;
<Parameters>      ::=    <identifier> <Parameters> | Ø ;
<Documentation>   ::=    '"' <Characters> '"' | Ø ;
<ExpressionList>  ::=    <Expression> <ExpressionList> | Ø ;
:::

Using these productions, define a "stub" function named `displayLogTable`, with the indicated parameters, above the `-main()` function.

Our function need **not** return anything; instead, it needs to implement the second step of our algorithm:

:::{code-block} text
:linenos:
For count = start to stop by increment:
      Display count and the logarithm of count.
:::

To complete the stub, we need the syntax of the Clojure repetition construct. Clojure offers us several options:

- Pure LISP-family languages have no repetition constructs, and instead rely upon recursion for repetition.
- As a hybrid LISP dialect, Clojure provides a loop function that can be used to solve problems involving tail recursion.
- To support compiler-optimization of tail-recursive functions, Clojure provides a recur function that can be used with a loop or a normal function.

In the rest of this exercise, we will explore each of these options.

### 1. A Pure Recursive Solution

We can derive a recursive solution to our problem as follows:

:::{code-block} text
:linenos:
Basis: There are no values to output (start > stop).
      -> Do nothing.
Induction Step: There are values to output (start <= stop).
      -> Display start and its logarithm;
      -> Recursively display the log table
            from start + step to stop using step.
:::

Since the function may be calling itself many times, we can use an `if` function to distinquish which of these cases the function is processing. Since our base-case does nothing, we can ignore it, making our recursive algorithm the following;

:::{code-block} text
:linenos:
if ( start <= stop )
      a. Display start and its logarithm.
      b. displayLogTable( start + step, stop, step ).
:::

Recall that the pattern for LISP's `if` function is given by

:::{code-block} yaml
<IfExpr>          ::=   '(' 'if' <CondExpr> <ThenExpr> <ElseExpr> ')' ;
<CondExpr>        ::=   <Expression> ;
<ThenExpr>        ::=   <Expression> ;
<ElseExpr>        ::=   <Expression> | Ø ;
:::

where the first `<CondExpr>` is the condition of the `if`, the value of the `<ThenExpr>` is returned if the first one is not `nil`, and the value of the `<ElseExpr>` is returned otherwise. Since our algorithm has two steps instead of one, we need a Clojure function to "convert" multiple expressions into a single `<Expression>` (much like a C-family Block "converts" a sequence of statements into a single statement). This is provided in Clojure by the do function:

:::{code-block} yaml
<DoExpr>        ::=   '(' 'do' <ExpressionList> ')' ;
:::

The effect of this statement is to combine the sequence of expressions that make up our algorithm into a single Lisp Expression. The `do()` function returns the value of the last `<Expression>` in the `<ExpressionList>`.

Using this information, add an `if` function to your function, containing a `do` function for the `<Expression>` to be performed if its condition evaluates to true (i.e., non-`nil`).

To perform step (a) of our recursive algorithm, we will use the Clojure `printf` (print-formatted) function:

:::{code-block} yaml
<Expression>      ::=   '(' 'printf' <FormatString> <Expressions> ')' ;
<Expressions>     ::=   <Expression> <Expressions> | Ø ;
:::

which behaves similarly to Java's `printf()` function.
To compute the base-10 logarithm of start, Clojure lets us use Java's Math/log10() function.

Using this information, add a `printf()` function to the `do()` function to perform step (a) of our recursive algorithm.

Performing step (b) simply requires a recursive call to our algorithm, so add an appropriate recursive call to `displayLogTable()` to your `do()` function that implements step (b).

That's it! Save your work and then compile and run your program. The provided `-main()` function will prompt you to enter the input values, and then invoke `displayLogTable()` with those values, letting you test that it works correctly for different inputs. Test your function using the values 1, 10 and 0.5. Then try other values; make certain this version works correctly before proceeding.

### 2. Using the loop() function

As mentioned previously, Clojure also provides a `loop()` function. This can only be used in situations in which tail-end recursion (i.e., the recursive call is the last thing in the function) can be used to solve the problem at hand. As we will see later in the course, Clojure's `loop()` function can be significantly more efficient than a normal recursive function.

If you examine your definition of `displayLogTable()`, you'll see that it employs tail-end-recursion, so we can use the `loop()` function to solve our problem. The syntax for this function is as follows:

:::{code-block} yaml
<LoopFunction>   ::=   '(' 'loop' '[' <Bindings> ']' <CondExpr> <Expr> '(' 'recur' <Expr> ')' ')' ;
<Bindings>       ::=   <identifier> <initialValue> <MoreBindings> ;
<MoreBindings>   ::=   <identifier> <initialValue> <MoreBindings> | Ø ;
<LoopExpr>       ::=   'when' <CondExpr> | 'if' <CondExpr> | ... ;
:::

Getting these details correct can be challenging (and time-consuming), especially when you haven't seen or used this function before, so here is a definition of `displayLogTable2()` that uses the `loop()` function:

:::{code-block} clojure
:linenos:
;; loop (indirect-recursion) version
(defn displayLogTable2 [start stop step]
  (loop [i start]                           ; set i to start
    (when (<= i stop)                       ; if i <= stop:
      (printf "The logarithm of %f is %f\n" ;  print one line
                  i (Math/log10 i)
      )
      (recur (+ i step))                    ; recurse, i += step
    )
  )
)
:::

Let's go through this definition line by line, to understand how it works:

- Line 3 invokes the `loop()` function, and declares $i$ as a parameter with an initial value of start. The overall effect is similar to writing `for (i = start; ....`
- Line 4 invokes the `when()` function, which acts like a single-branch `if` function for our `loop()` function. Combined with the first line, the effect is similar to writing `for (i = start; i <= stop; ....`
- Lines 5-7 invoke `printf()` to display the logarithm of `start`, so there is nothing new there.
- Line 8 triggers an indirect-recursive invocation of the loop function, passing `(+ i step)` as the new value for parameter `i` in the recursive call. Combined with the first two lines, the effect is similar to writing: `for (i = start; i <= stop; ++i) {....`.
Since the `loop()` function can only be used for tail-recursion, this call to the `recur()` function must be the final expression of the `when()` function's `<LoopExprList>`.
- The `loop` function returns the result of the final expression executed.

Add this definition of `displayLogTable2()` to your source file, immediately above the `-main()` function. Then uncomment the calls to `displayLogTable2()` and `println` at the end of the `-main()` function. Save your changes and verify that this version produces the same output as `displayLogTable()`.

### 3. More on Function recur

It is worth mentioning that the `recur` function is not tied to the `loop` function; it can be used to trigger a recursive call of any function. To illustrate, here is yet another version of `displayLogTable()` that solves our problem without a `loop`:

:::{code-block} clojure
:linenos:
;; indirect/tail-recursive version (just using recur)
(defn displayLogTable3 [start stop step]
  (if (<= start stop)                     ; if start <= stop:
    (do                                      ; do these two things:
      (printf "The logarithm of %f is %f\n"  ; - print one line
                  start (Math/log10 start)
      )
      (recur (+ start step) stop step)       ; - recurse, start+=step
    )
  )
)
:::

As with `displayLogTable2()`, this version will allow the compiler to perform tail-recursion optimizations that it cannot otherwise perform.

Take a moment to type this into your source file; then uncomment the final two commented-out lines in `-main()`, and verify that this achieves the same effect as the previous versions.

When `recur` is invoked within a function that contains no loop, it triggers a recursive call to that function. When `recur` is invoked within a loop, it triggers a recursive call to that loop.

Note that the number of arguments passed with `recur` must match the number of parameters in the function being "recursed": Our loop has a single parameter (`i`) and so the call to `recur` in `displayLogTable2()` has a single argument; our `displayLogTable3()` function has three parameters (`start`, `stop`, and `step`), and so our call to `recur` in that function has three arguments.

That concludes the Clojure part of this lab.

## Ruby

Open *log_table.rb*. Take a moment to study it, to see how it implements our basic algorithm, and compare it to the other "programs" you've seen.

Today we'll be looking at repetition. Ruby provides a `for` loop for solving counting problems, along with a `while` and `until` loop. The `until` loop works like a `while` loop, except that it runs until the condition is true (syntactical sugar really). Let's look at a BNF for these three loop types:

:::{code-block} yaml
<LoopStatement> ::= <LoopClause> <StatementList> 'end' ;
<LoopClause>    ::= 'for' <identifier> 'in' <Range> | 'while' <Expression> | 'until' <Expression> ;
<Range>         ::= <Integer> '..' <Integer> | <Integer> '...' <Integer> ;
:::

These kinds of loops work as you would expect in other languages. The `for` loop iterates over a range of values. There are two distinct types of ranges in Ruby, a two dot range and a three dot range. The two dot form is inclusive, while the three dot form is exclusive. For example: `0..4` will count from 0 to 4, while `0...4` will count from 0 to 3.

Since this is a counting problem, the `for` loop would seem like the natural choice. However, if we take that route we'll run into a problem: Ruby's `for` loop will only increment by 1, and we need to be able to increment by arbitrary step-values.

Our solution then is to use a `while` loop. Our `while` should run so long as our start variable is less than or equal to our stop variable.

In order to actually compute the logarithms, we'll use Ruby's `log10()` function. The `log10()` is a member of the **Math** class, so you'll have to invoke it by sending a message to **Math**, using `Math.log10()`.

We can use Ruby's `puts` statement to print a row of our table. But `puts` will only display a single string. We could write out each piece of the row with separate `puts` (or `puts`) statements, or we could use the Ruby string concatenation operator `>>` to build up an output string and then display that string.

But a better approach is to use Ruby's **string inlining mechanism**: by surrounding a numeric `Value` with `#{ Value }` within a string literal, Ruby will replace this reference with the `Value`, effectively inserting the numeric value into the string.

Using string inlining, write a single statement to print out a row.

Then, all that's left to do is to increment our counting variable by the increment variable the user provided.

Be sure to test your program on a variety of input values.

When you are certain it is correct, commit and push your changes to GitHub.

That concludes the Ruby part of this lab.

## Rubric

| Task | Points |
|:---|:---:|
| Java: Implement the required `while` loop to generate the logarithm table. |  | 10 |
| Ada: Implement the required loop to generate the logarithm table. | |  | 10 |
| Clojure: Implement the required `displayLogTable()` function using recursion. | 10 |
| Clojure: Implement the required `displayLogTable2()` function using the `loop()` function. | 10 |
| Clojure: Implement the required `displayLogTable3()` function using the `recur` function. | 10 |
| Ruby: Implement the required `while` loop to generate the logarithm table. | 10 |

**Ways to lose points:**

- Failure to implement the required loop in any of the four languages.
- Failure to test your code for correctness in any of the four languages.
- Failure to commit and push your changes to GitHub.
