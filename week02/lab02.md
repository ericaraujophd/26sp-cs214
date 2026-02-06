---
title: "->lab02;"
subtitle: // Controlling Behavior through Selection
---

Today's exercise involves using BNF to examine the syntax of the construct that provides **selective execution** in each of our four languages. More precisely, we will use BNF to study the `if` construct in each of our languages.

## The Problem

The problem we will study this week is this: given the name of an academic year (i.e., freshman, sophomore, junior, senior) what is the corresponding integer code (i.e., 1, 2, 3, or 4)?

Find a partner. Then accept the assignment to join the [GitHub Classroom assignment](https://classroom.github.com/a/C0I1pA-f) for this lab. Create a new Team and ask your partner to join it. Then, clone the repository to VS Code as usual.

The repository contains the following code skeletons:

- YearCodes.java
- year_codes.adb
- year_codes.clj
- year_codes.rb

Each of these skeletons provides a "driver" program to test a function that solves this problem, using the following simple algorithm:

```{code}
:linenos:
Get year (a string) from the user
Display the code corresponding to year.
```

To perform step 2 of this algorithm above, each "driver" displays the integer returned by a function `yearCode()` that, given `year`, returns the corresponding integer. The algorithm that we will use for this function is as follows:

:::{code-block} python
if year is "freshman", then
    return 1;
else if year is "sophomore", then
    return 2;
else if year is "junior", then
    return 3;
else if year is "senior", then
    return 4;
else
    return 0;
:::

Your task is to implement this algorithm in each of the four languages, using the appropriate `if` construct for that language.

Like last week, we will lead you through the process using Java, after which you will apply the same techiques to solve the problem in the other three languages. You should begin with the Java Introduction; the order in which you do the other three does not matter.

## 1. Java Introduction

Begin by using a text editor to edit the file *YearCodes.java*. Take a moment to study it, to see how it implements our basic algorithm.

To build method `yearCode()`, we will begin by reviewing the (simplified) BNF for a Java method definition:

:::{code-block} yaml
<MethodDef>       ::=   <MethodHeading> <MethodBody> ;
<MethodHeading>   ::=   <Type> <identifier> '(' <ParameterDecs> ')' ;
<ParameterDecs>   ::=   <ParameterDec> <MoreParams> | 𝜖 ;
<ParameterDec>    ::=   <Type> <identifier> <DefaultArg> ;
<DefaultArg>      ::=   = <Expression> | 𝜖 ;
<MoreParams>      ::=   ',' <ParameterDec> <MoreParams> | 𝜖 ;
<MethodBody>      ::=   '{' <StatementList> '}' ;
:::

Method declarations alone are not used in Java. The body of the method is coded when the declaration is made, creating the entire method definition. Java programmers differentiate between classes and methods by capitalizing the first letter of class names and not capitalizing the first letter of method names.

From a procedural point of view, our `yearCode()` method has this specification:

- **Receive:** `year`, a string. 
  - **Precondition:** year is one of {freshman, sophomore, junior, senior}. 
- **Return:** The integer code corresponding to year (1, 2, 3 or 4).

We can place the following stub (method declaration) for our method after our `main` method but still within the `YearCodes` class:

:::{code-block} java
public static int yearCode(String year){

}
:::

To fill in the stub, we need the syntax of the Java `if` construct, which is a statement:

:::{code-block} yaml
<IfStmt>      ::=   'if' '(' <Expression> ')' <Statement> <ElsePart> ;
<ElsePart>    ::=   'else' <Statement> | 𝜖 ;
:::

where `<Statement>` is any valid Java statement (including another `if` statement), and `<Expression>` is usually (but not limited to) a boolean expression:

:::{code-block} yaml
<Expression>      ::=   <BoolExpr> ;
<BoolExpr>        ::=   <RelationalExpr> <BoolCondition> ;
<BoolCondition>   ::=   <BoolOp> <RelationalExpr> | 𝜖 ;
<BoolOp>          ::=   '&&' | '||' | '!' ;
<RelationalExpr>  ::=   <Expression> <RelationalCond> ;
<RelationalCond>  ::=   <RelationalOp> <Expression> | 𝜖 ;
<RelationalOp>    ::=   '==' | '!=' | '<'; | '>' | '<=' | '>=' ;
:::

Our algorithm requires the equality relation, but the `==` operator applied to Strings only compares if the two strings are the same object. We need to use the `equals()` method of the String class to check whether or not the values of two String objects are the same. So we can use the rest of the preceding BNF productions to fill in our method stub:

:::{code-block} java
public static int yearCode(String year){
    if (year.equals("freshman"))

    else if (year.equals("sophomore"))

    else if (year.equals("junior"))

    else if (year.equals("senior"))

    else

}
:::

To complete the stub, we must understand how Java methods return a value to their caller. This is accomplished by a `return` statement:

:::{code-block} yaml
<ReturnStmt>     ::=   'return' <Expression> ';' ;
:::

When executed, such a statement evaluates `<Expression>` and returns control to the caller of the method, with

:::{code-block} java
public static int yearCode(String year) {
    if (year.equals("freshman"))
        return 1;
    else if (year.equals("sophomore"))
        return 2;
    else if (year.equals("junior"))
        return 3;
    else if (year.equals("senior"))
        return 4;
    else
        return 0;
}
:::

Note that while this `if-else-if` structure is very readable, it is actually a bit misleading, since the structure makes it appear as if we have written a single statement. In actuality, each `if` after the first one is the separate `<Statement>` given in the `<ElsePart>` BNF production. A definition like the following would be less readable:

:::{code-block} java
public static int YearCode(String year) {
   if (year.equals("freshman"))
      return 1;
   else
      if (year.equals("sophomore"))
         return 2;
      else
         if (year.equals("junior"))
            return 3;
         else
            if (year.equals("senior"))
               return 4;
            else
               return 0;
}
:::

but it more accurately reflects the grammar rules of the Java `if`.

Compile and test your code for correctness. That concludes the Java part of the lab.

## 2. Ada Introduction

Begin by opening the file *year_codes.adb*. Take a moment to study it, to see how it implements our basic algorithm, paying special attention to the extra code needed to perform string I/O.

Our program will use a slightly expanded program structure from what we have seen previously:

:::{code-block} yaml
<Program>         ::=  <Packages>
                    'procedure' <identifier>
                    <Declarations>
                    'begin'
                        <Statements>
                    'end' <identifier>;
<Declarations>    ::=  <Declaration> ; <Declarations> | 𝜖 ;
<Declaration>     ::=  <ConstantDec> | <VariableDec> | <SubprogramDec> | ... ;
<SubprogramDec>   ::=  <ProcedureDec> | <FunctionDec> ;
<Statements>      ::=  <Statement>; <MoreStatements> ;
<MoreStatements>  ::=  <Statement>; <MoreStatements> | 𝜖 ;
:::

Here, we see that functions and procedures can be defined within the `<Declarations>` of an Ada program, prior to the `begin` that marks the start of the main procedure's body.

To build function `YearCode()`, we will begin by giving a possible BNF for an Ada function definition:

:::{code-block} yaml
<FunctionDec>     ::=   <FunctionHeading>
                        <Declarations>
                        'begin'
                            <Statements>
                        'end' <identifier> ;
<FunctionHeading> ::=   'function' <identifier> <Parameters> 'return' <Type> 'is' ;
<Parameters>      ::=   ( <ParamDecList> ) | 𝜖 ;
:::

Note that the first `<identifier>` in the function's heading must match the `<identifier>` that appears in the function's `end` statement. Note also that if an Ada function requires no parameters, then no parentheses are needed in its declaration. Note finally that an Ada function's return-type is given near the end of the function's heading. Using this information, add a function stub for `YearCode()` (with parentheses) in the appropriate place within program `YearCodes`.

In an Ada declaration (variable, parameter, etc.), the identifiers being declared must precede the type. A simplified BNF for a sequence of Ada parameter declarations is as follows:

:::{code-block} yaml
<ParamDecList>    ::=   <ParamDec> <MoreParamDecs> ;
<MoreParamDecs>   ::=   ; <ParamDec> <MoreParamDecs> | 𝜖 ;
<ParamDec>        ::=   <identifier> <IdentifierList> : <Type> ;
<IdentifierList>  ::=   , <identifier> <IdentifierList> | 𝜖 ;
<Type>            ::=   'integer' | 'float' | 'character' | 'string' | 'boolean' | ... ;
:::

From a procedural point of view, our `YearCode()` function has this specification:

- **Receive:** `year`, a string.
  - **Precondition:** `year` is one of {freshman, sophomore, junior, senior}.
- **Return:** The integer code corresponding to year (1, 2, 3 or 4).

The Ada type `string` can be used to declare a parameter whose value will be a character string. Using this information, declare parameter `year` within function `YearCode()`.

To complete the stub, we need the syntax of the Ada `if` construct, which has a single form:

:::{code-block} yaml
<IfStmt>    ::=  'if' <LogicalExpr> 'then' <Statements> <ElsifPart> <ElsePart> 'end' 'if' ;
<ElsifPart> ::=  𝜖 | 'elsif' <LogicalExpr> 'then' <Statements> <ElsifPart> ;
<ElsePart>  ::=   𝜖 | 'else' <Statements> ;
:::

The Ada `if` statement differs from those in the C-family in several ways. One is that the Ada `if` statement does not require parentheses, because the keyword `if` marks the beginning of the `<LogicalExpr>` and the keyword `then` marks its end.

Another difference is that multibranch selection can be accomplished in Ada using a single `if` statement, compared to multiple if statements in C-family languages. Ada provides a special keyword `elsif` for this purpose. A common "Gotcha" is mistakenly typing `elseif` which produces a syntax error!

To fill in the `<LogicalExpression>` of each `if`, we need to know its syntax, which is similar to those in C-family languages:

:::{code-block} yaml
<Expression>   ::=   <LogicalExpr> | <Variable> | <Literal> | ... ; //various other kinds of expressions
<LogicalExpr>  ::=   <RelationalExpr> <BoolCondition> ;
<BoolCondition> ::=   <BoolOp> <RelationalExpr> | 𝜖 ;
<BoolOp>       ::=   'and' | 'or' | 'xor' | 'not' | 'and then' | 'or else' ;
<RelationalExpr>  ::=   <Expression> <RelationalCond> ;
<RelationalCond>  ::=   <RelationalOp> <Expression> | 𝜖 ;
<RelationalOp>    ::=   '=' | '/=' | '<' | '>' | '<=' | '>=' ;
:::

Our algorithm requires the *equality* relation, so use these productions to add an `if` statement to function `YearCodes()`.

A common "Gotcha" is that Ada's `string` type is a good bit more restrictive than those in C-family languages: When performing an equality comparison (`=`) on fixed-length strings, the lengths of the strings being compared must match exactly in order for `=` to return true! Practically speaking, when you compare `year` to a string-literal, you may find it necessary to "pad" the string-literal with blanks to make it the same length as `year`.

To complete the function, it is necessary to know how to return a value from an Ada function. As in the C-family, Ada uses a `return` statement to accomplish this:

:::{code-block} yaml
<ReturnStmt>   :   'return' <Expression>;
:::

where the type produced by `<Expression>` must match the return-type of the function. Using this information, add the necessary return statement(s) to our function so that when it terminates, it will return the appropriate code for a given value of `year`.

Note finally that the productions to generate an Ada multi-branch if statement are quite different from those that generate a C-family `if-else-if`; where a C-family `if-else-if` is one if statement with a second if statement in its `<ElsePart>`, an Ada `if-elsif` is just a single if statement, whose `<ElsifPart>` is not empty. This difference stems from the different way Ada and C-family languages terminate their if constructs: an Ada `if` terminates at its `end if`, but C++/Java if have no special terminator.

:::{admonition} 🧠 Thinking about Syntax
:icon: false
Think carefully about how C++/Java end their `if` statements vs. how Ada ends its `if` statements. What advantages can you see to the Ada approach (i.e., using `end if`)? What disadvantages?
:::

Compile and test your code for correctness:

:::{code-block} bash
gnatmake year_codes.adb
:::

That concludes the Ada part of this lab.

## 3. Clojure Introductions

You will find the clojure file `year_codes.clj` in the directory `clojure/src/`. Take a moment to study it, to see how it implements our basic algorithm, and compare it to the other "programs" you've seen.

### Defining `yearCode()`

An Clojure function has the following structure:

:::{code-block} yaml
<FunctionDef>     ::=   '(' 'defn' <identifier> '[' <Parameters>> ']'
                        <Documentation>
                        <ExpressionList> ')' ;
<Parameters>      ::=    <identifier> <Parameters> | Ø ;
<Documentation>   ::=    "Characters" | Ø ;
<ExpressionList>  ::=    <Expression> <ExpressionList> | Ø ;
:::

Note that unlike C-family parameters, Clojure parameters are merely *listed* within the square brackets following the identifier that names the function; parameters' types are determined at run-time, by the arguments passed (and the operations applied) to them.

From a functional point of view, our `yearCode()` function has this specification:

- **Receive:** `year`, a string.
  - **Precondition:** `year` is one of {freshman, sophomore, junior, senior}.
- **Return:** The integer code corresponding to year (1, 2, 3 or 4).

Using this specification and the preceding BNF, define a stub for function `yearCode()` above the function named `-main`.

Like the parameter types, a Clojure function's return-type is determined dynamically, at *run-time*, by whatever value the function returns (which is the final value the function computes). For this reason, the function's return-type is not declared.

### Using an `if` Function

To complete the stub and implement our algorithm, we need the syntax of the Clojure `if` construct. As we have seen, **everything** in Clojure is either a function or an argument to a function, so where most other languages have an `if` statement, Clojure has an `if` function:

:::{code-block} yaml
<IfFunction>      ::=   '(' 'if' <CondExpr> <ThenExpr> <ElseExpr> ')' ;
<CondExpr>        ::=   <Expression> ;
<ThenExpr>        ::=   <Expression> ;
<ElseExpr>        ::=   <Expression> | Ø ;
:::

When the `if` function is called, `<CondExpr>` is evaluated. If its value is not `nil`, then `<ThenExpr>` is evaluated and its value is the return-value of the `if` function; otherwise, if `<ElseExpr>` is present, that expression is evaluated and its value is returned. The `if` function thus returns the value of the last `<Expression>` it evaluated. LISP-family languages use `nil` as the boolean value "false" and non-`nil` as the boolean value "true".

An `if-else-if` form can thus be built simply by making `<ElseExpr>` another `if` function. The structure of an `if-else-if` will thus be similar to that of the C-family `if`, but without any `else` keyword.

To fill in the `<CondExpr>` of each `if`, we need to know how to compare two strings in Clojure.

:::{code-block} yaml
<CondExpr>        ::=   <BoolExpr> | <Predicate> | <RelationalExpr> | <number> | ... ;
<BoolExpr>        ::=   '(' <BoolFunction> <Expression> <Expression> ')' ;
<BoolFunction>    ::=   'and' | 'or' ;
<Predicate>       ::=   '(' <UnaryOp> <Expression> ')' ;
<UnaryOp>         ::=   'not' | <Predicate> ;
<RelationalExpr>  ::=   '(' <RelationalOp> <Expression> <Expression> ')' ;
<RelationalOp>    ::=   '=' | 'not=' | '<' | '>' | '<=' | '>=' | ... ;
<Predicate>       ::=   'identical?' | 'integer?' | 'double?' | 'number?' | 'string?' | ... ;
:::

The `=` operator returns "true" if its arguments are equal, so we can use it here. (The `identical?` predicate returns "true" if its two arguments are references to the same object.)
Using the preceding information, add the necessary `if` functions to function `yearCode()` to implement our algorithm.

To complete the function, we need to know how to return a value from a Clojure function. The LISP-family function-return-value mechanism is quite simple: the return-value of a function is the value of the final expression it executes. That is, if the last function executed by `yearCode()` is our `if` function, then `yearCode()`'s return value is the value returned by the `if` function (whose return-value is the value of the final `<Expression>` it executes). Of course, an `<Expression>` can be a simple number:

:::{code-block} yaml
<Expression>      ::=   <number> ;
:::

Using this information, add the necessary expressions to the function, so that when it terminates, it will return the appropriate code for a given value of `year`.

Save your program, compile, and test it, as we did in lab01. Make sure that `yearCode()` works correctly for all valid and an invalid input before continuing.

### Using the `cond` Function

LISP-family languages also have a function named `cond` for multi-branch selective execution. This function looks something like the multibranch `switch` statement in C-family languages, or the `case` statement in Algol-family languages, but where those statements are limited to comparing integer-based values, the LISP-family `cond` function can be used to compare arbitrary values: integer-based, real numbers, strings, etc. Since our problem involves comparing string values, the `cond` function provides an alternate way to solve our problem. Its syntax can be specified as follows:

:::{code-block} yaml
<CondFunction>   ::=    '(' 'cond' <CondClauses> <ElseClause> ) ;
<CondClauses>    ::=    <CondClause> <CondClauses> | Ø ;
<CondClause>     ::=    <BoolExpr> Expr ;
<ElseClause>     ::=    ':else' <Expr> | Ø ;
:::

The `cond` function starts at the top and goes through its `<CondClauses>` until it finds one that evaluates to true; it then evaluates and returns the `<Expr>` associated with that `<CondClause>`, skipping the remaining `<CondClauses>`. If an `<ElseClause>` is provided and none of the preceding `<CondClauses>` were true, then the `<Expr>` of that `<ElseClause>` is returned; otherwise the `cond` function returns `nil`.

To illustrate its use, here is a `yearCode2()` function definition that solves our problem using the `cond` function:

:::{code-block} clojure
;; solution using the cond function
(defn yearCode2 [year]
  (cond
    (= year "freshman")  1
    (= year "sophomore") 2
    (= year "junior")    3
    (= year "senior")    4
    :else                0
  )
)
:::

Note that unlike the `switch` or `case` statements of other languages, the LISP-family `cond` function does not provide any performance-advantage over a multi-branch `if` function. This is because the `cond` function:

1. Accepts clauses consisting of arbitrary boolean expressions on arbitrary types, rather than integer-based expressions; and
2. Proceeds linearly through its clauses until it finds one whose boolean expression is true.

However, for problems whose solutions require multibranch selective behavior, the `cond` function can provide a more readable, succinct, and elegant solution than a series of nested `if` functions, as can be seen by comparing `yearCode()` and `yearCode2()`.

Take a few minutes to copy-paste this definition into your source file below your `yearCode()` function. Then tweak your `-main()` function so that it displays the values returned both `yearCode()` and `yearCode2()`. Verify that `yearCode2()` provides the same behavior as `yearCode()` for the various input values (both valid and invalid). Continue when both functions are working correctly.

### Using the `case` Function

Unlike other LISP-family languages, Clojure also has a function named `case` for multi-branch selective execution. This function looks and behaves similarly to the multibranch `switch` statement in C-family languages, and the `case` statement in Algol-family languages. However, where those statements are limited to comparing integer-based values, Clojure's `case` function can be used to compare arbitrary values: integer-based, real numbers, strings, etc. Since our problem involves comparing string values, the `case` function provides yet another way to solve our problem. Its syntax can be specified as follows:

:::{code-block} yaml
<CondFunction>    ::=    '(' 'case' <MatchExpression> <Cases> <DefaultExpr> ')'
<MatchExpression> ::=    <Expression>
<Cases>           ::=    <Literal> <Expression> <Cases> | <Literal> ... <Literal> <Expression> <Cases> | Ø
<DefaultExpr>     ::=    <Expression> | Ø
:::

The `case` function determines the value of its `<MatchExpression>`; then it goes through its `<Cases>` until it finds a `<Literal>` that matches that value; this `<Literal>` can be any supported type-literal. Once it has matched a `<Literal>`, the case function then evaluates and returns the `<Expression>` associated with that `<Literal>`, skipping the remaining `<Cases>` and `<DefaultExpr>` (if present). If a `<DefaultExpr>` is provided and none of the preceding `<Cases>` were a match, then the `<DefaultExpr>` is evaluated and returned; otherwise the case function throws an `IllegalArgument` exception.

To illustrate its use, here is a `yearCode3()` function definition that solves our problem using the `cond` function:

:::{code-block} clojure
;; solution using the case function
(defn yearCode3 [year]
  (case year
    "freshman"  1
    "sophomore" 2
    "junior"    3
    "senior"    4
                0 ; default
  )
)
:::

Like the `switch` or `case` statements of other languages, Clojure's `case` function provides a performance-advantage over a multi-branch if function or a LISP `cond` function. It achieves this performance advantage by using a `map` (aka dictionary) data structure behind the scenes that maps each `<Literal>` to its corresponding `<Expression>`. Matching the initial `<MatchExpression>` to a given `<Literal>` just involves searching the underlying map structure for the value produced by `<MatchExpression>`. It follows that the value of each `<Literal>` must be known when the function is compiled.

Take a few minutes to copy-paste this definition into your source file below your `yearCode2()` function. Then tweak your `-main()` function so that it displays the values returned by `yearCode()`, `yearCode2()`, and `yearCode3()`. Verify that `yearCode3()` provides the same behavior as `yearCode()` and `yearCode2()` for the various input values (both valid and invalid). Continue when all three functions are working correctly.

That concludes the Clojure part of this lab.

## 4. Ruby Introduction

Open the file `year_codes.rb`. Take a moment to study it, to see how it implements our basic algorithm, and compare it to the other "programs" you've seen.

Lets begin by defining a Ruby function with a BNF:

:::{code-block} yaml
<FunctionDef>    ::= 'def' <identifier> <ParameterDecs> <StatementList> 'end' ;
<ParameterDecs>  ::= '(' <ParameterDec> <MoreParameters> ')' | <ParameterDec> <MoreParameters> | ∅ ;
<ParameterDec>   ::= <identifier> <DefaultArg> ;
<DefaultArg>     ::= = <Expression> | ∅ ;
:::

If you study this BNF closely you'll recognize several unusual things about Ruby methods and parameters:

- Ruby methods don't need to have parentheses around its parameters.
- Ruby parameter declarations don't specify types.
- Ruby methods aren't defined by braces, but rather by the keywords `def` and `end`. You'll find Ruby is something like Ada in this respect.
- Ruby allows the use of `?` and `!` in method names. This allows you to write a method like `user.logged_in?` that returns a boolean.

Using this information, write a method stub called `yearCode` with a parameter called `year`.

You can test your stub from the command-line by entering:

:::{code-block} bash
ruby year_codes.rb
:::

If your stub is syntactically correct, it should run and produce behavior something like this:

:::{code-block} bash
Enter the year: freshman
Numeric code is: nil
:::

Make sure your stub is syntactically correct before you continue.

### Basics of Selection

Now that you've got a method stub written, let's add the selection statement that should run our algorithm. As you probably expected, selection in Ruby takes the form of an `if` statement. The BNF for a Ruby `if` statement is as follows:

:::{code-block} yaml
<IfStmt>    ::= 'if' <Expression> <ThenStmt> <StatementList>
              <ElsifPart>
              <ElsePart>
              'end' ;
<ElsifPart> ::= 'elsif' <Expression> <ThenStmt> <StatementList> | ∅ ;
<ElsePart>  ::= 'else' <ThenStmt> <StatementList> | ∅ ;
<ThenStmt>  ::= 'then' | ∅ ;
:::

Note: `then` is required when the `if`, `elsif`, or `else` appears on the same line as the `<StatementList>`.

One important thing to note about Ruby `if` statements is that the expression they evaluate is pretty wide open. Similar to the C family of languages, Ruby can evaluate things other than boolean expressions. In Ruby, everything is `true` except for `false` and `nil`. What this means is that any object you throw in there will evaluate to true. Furthermore Ruby has an interesting syntactical twist on the if statement called the `unless` statement. `unless` will execute its `<StatementList>` unless its `<Expression>` is true. The BNF for `unless` looks like this:

:::{code-block} yaml
<UnlessStmt> ::= 'unless' <Expression> <ThenStmt> <StatementList>
               <ElsePart>
               'end' ;
<ElsePart>   ::= 'else' <ThenStmt> <StatementList> | ∅ ;
<ThenStmt>   ::= 'then' | ∅ ;
:::

Note: There is no `elsif` in an `unless` statement.

Another cool feature of selection statements that Ruby borrowed from PERL is the ability to tack them onto the end of an expression. So you could say something like:

:::{code-block} ruby
user.destroy if user.invalid?
:::

### String Comparisons with Regular Expressions

One last feature of Ruby we'll need to cover in order to implement our algorithm is how to do string comparison. Ruby provides a variety of ways, including:

- `s1 == s2`, which returns true if and only if `s1` and `s2` have the same values.
- `s1.equal? s2`, which returns true if and only if `s1` and `s2` refer to the same object (essentially a pointer comparison).
- `s1.eq? s2`, which returns true if and only if `s1` and `s2` have the same hash values.
- [others](https://stackoverflow.com/questions/7156955/whats-the-difference-between-equal-eql-and).

We could solve this problem using the standard `==` operator, though doing so requires us to replace our simple call to `gets` with `(gets).chomp` or `(gets).strip`, because `gets` includes the newline character when it reads an entered string. (If you want, feel free to try this out before continuing.)

Instead, let's explore a different to solve the problem. Like PERL, Ruby has a fully functional built-in regular expression parser. Regular expressions in Ruby are built with the standard PERL syntax: a valid expression, surrounded by `/` characters. In order to compare a string in Ruby to a regular expression, we use the `=~` operator. Put together, this results in the following statement to determine if the input year matches "freshman":

:::{code-block} ruby
if year =~ /freshman/
:::

As you implement your algorithm, remember that Ruby methods return the last expression evaluated, so no return statement is necessary. Use all that you've learned about Ruby functions and selection statements to write the algorithm for our year code program.

Be sure to test your program on both valid and invalid input values.

:::{admonition} 🧠 Thinking about Ruby's `if` and `unless`
:icon: false
What happens if you enter **supersenior** as a test-value? Is this a feature or a bug?
:::

When you are confident that your program is working properly, commit and push your code to GitHub.

That concludes the Ruby part of this lab.
