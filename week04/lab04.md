---
title: "->lab04;"
subtitle: // Aggregate Types
---

## Overview

Before, we examined the facilities in each of our languages for creating an object capable of storing multiple values of the same type, which is usually called an array.

This week, we examine the facilities of each of our languages for creating an object capable of storing multiple values of arbitrary types: which is generically known as an **aggregate**. Along the way, we will see each language's `assert()` mechanism that supports automated unit-testing.

We will look at the aggregate construct in each of the languages:

- Java, where it is called the class;
- Ada, where it is called a record;
- Clojure, where it is called a record (but compiles into a Java class); and
- Ruby, where it is also called a class.

As in past weeks, we will solve the same problem in each of our four languages, to compare their particular constructs. This week's problem is this: create an aggregate type called `Name` to store a person's name, plus the following `Name` operations:

- Initialize a `Name`, given three strings.
- Access the first name of a `Name`.
- Access the middle name of a `Name`.
- Access the last name of a `Name`.
- Convert the `Name` to a string.
- Display a `Name` on the screen.

As before, we will provide program "skeletons" that provide a framework for calling these functions. Since the sole use of these programs will be to test our `Name` operations, they have no algorithms.

As usual, we will lead you through the process using Java, after which you will apply similar techiques to solve the problem in the other three languages. As usual, the order in which you do the three exercises does not matter.

Begin by accepting the project invitation from GitHub Classroom: [here](). Then, open VS Code through Coder and clone your repository. You will need to create all the files for this project yourself, so be sure to create a Java file, an Ada file, a Clojure file, and a Ruby file.

## Java

Begin by taking a few moments to view and get a sense of the contents of the files *NameTester.java* and *Makefile* in your repository.

Thanks to the Makefile, you can enter this command:

:::{code-block} bash
make java
:::

and you should see *NameTester.java* compile. Verify that the file compiles correctly before continuing.

If you use the `ls` command, you should see a new file named *NameTester.class* that the Java compiler produced.

To run the program in *NameTester.class*, enter:

:::{code-block} bash
java NameTester
:::

Verify that the program runs initially without any errors, then continue.

Open *NameTester.java* and take a moment to study it, to see what it is doing. Uncomment the line:

:::{code-block} java
Name aName = new Name("John", "Paul", "Jones");
:::

Then use the same make command to rebuild your program. **What happens?**

To fix this problem, we must build a `Name` class.

### Building a `Name` Class

A Java class can be defined using the form:

:::{code-block} yaml
<ClassDec>        ::=   <modifier> 'class' <identifier> '{' <Block> '}' ;
<Modifier>        ::=   'public' | 'private' | ε ;
:::

where identifier is the name of the new class. To support object-oriented programming, a Java aggregate can encapsulate both data components (called data fields) and function components (called methods).

For operations, our class needs to encapsulate methods that perform the operations described in the introduction. One way to facilitate these operations is for our `Name` class to contain three data members, one of the first name, one for the middle name, and one for the last name, all of type String. Using this information, we can declare the shell of a `Name` class by writing

:::{code-block} java
class Name{
    private String myFirst,
                    myMiddle,
                    myLast;
};
:::

Add this declaration to *NameTester.java*.

The portions of the class that are public defines its interface, while the portions of the class that are private defines its implementation.

Save and rebuild your program. **Is this sufficient for your program to build and run without errors?**

### Initialization

Java objects are initialized by a constructor method: a method with no return type whose name is the name of the class (i.e., `Name` in this case). Since the task of this method is simply to initialize the three data members to the values the method receives via its parameters, we can write:

:::{code-block} java
public Name(String first, String middle, String last){
    myFirst = first;
    myMiddle = middle;
    myLast = last;
}
:::

Add this constructor definition to your `Name` class (inside the class) and continue.

Given this constructor, our program's declaration

:::{code-block} java
Name aName = new Name( "John", "Paul", "Jones" );
:::

should now construct and initialize the object `aName` as the name "John Paul Jones".

Rebuild your program; then run/test this much of your class. Continue when it builds and runs without any errors.

### Accessor Methods

Uncomment the following line in *NameTester.java*:

:::{code-block} java
assert aName.getFirst().equals("John");
:::

Try to build your program. **What happens?**

Since a name is an aggregate of three components, our `Name` class needs three operations to access the values of those components. Such operations are called accessor methods, (or "getters").

Since *NameTester.java* is trying to access the first name of the object `aName`, we can write an accessor method to return this quantity:

:::{code-block} java
public String getFirst(){
    return myFirst;
}
:::

Add this to your `Name` class, and verify that the program now builds and runs correctly.

Note that by default, assertions are **disabled** in Java. To enable them, run your program using the `-ea` (enable assertions) switch:

:::{code-block} bash
java -ea NameTester
:::

Using this as a model, define the other two accessor methods, for the `Name` class; then uncomment the next two assertions. 

Your program should now be able to execute

:::{code-block} java
Name aName = new Name( "John", "Paul", "Jones" );
...
assert aName.getFirst().equals( "John" );
assert aName.getMiddle().equals( "Paul" );
assert aName.getLast().equals( "Jones" );
...
:::

and pass all three assertions. When all three accessors are correct, continue.

We now have accessor methods (or "getters") that let us retrieve the value of a component. By contrast, operations that change the value of a component are called mutators (or "setters"), since they cause an object's state to change or mutate. Implementing the `Name` mutators is a part of this week's project.

### Output and String Conversion

In *NameTester.java*, uncomment the line:

:::{code-block} java
System.out.println( aName );
:::

Rebuild your program. **Do you get any errors?**

Run your program. **What is displayed?**

To fix this, let's write a method that will aid in displaying a `Name`. If we call this method `toString()`, and have the method return a String representation of a `Name` object, then Java will automatically use this method when we attempt to print a `Name` object using one of the standard `print()` methods. We might define our new method as follows:

:::{code-block} java
public String toString(){
    return myFirst + ' ' + myMiddle + ' ' + myLast;
}
:::

After adding this method to the `Name` class, rebuild and rerun your program. The statement:

:::{code-block} java
System.out.println( aName );
:::

should now cause the name "John Paul Jones" to appear on our screen. Verify that this works correctly before continuing.
Thanks to the power of the `toString()` method in Java, there is no need to create separate methods for printing and getting the full name. However, either is possible. Consider the following `print()` method:

:::{code-block} java
public void print() {
    System.out.println( toString() );
}
:::

While this definition is valid, you can see that it is superfluous since we can print a `Name` object using Java's standard `print()` method.

Likewise, our `toString()` method returns a person's full name. Verify this by uncommenting the final assertion in *NameTester.java*, rebuilding, and rerunning your program. Since `toString()` kills two birds with one stone, we will not implement separate methods for either of these operations.

If you enter the command `ls`, you should see *NameTester.java*, *NameTester.class*, and *Name.class*. Note that the Java run-time environment finds and combines all of these files as necessary, provided they are in the same directory. Enter:

:::{code-block} bash
make clean
:::

Then enter the `ls` command again. **What is gone?** Enter the command

:::{code-block} bash
make java
:::

and enter the `ls` command again. **What is back?**

The **make** utility is thus very useful for distributing source code, since `make clean` can be used to remove everything except the source, and the `Makefile` is all that is needed to turn that source code into a working program.

That concludes the Java part of this lab exercise.

## Ada

Open the skeleton *name_tester.adb* from repository and take a moment to view its contents. The Makefile will also be part of this step. Note that it includes `ada` as a target, the `ada` target depends on `name_tester`, `name_tester` depends on `name_tester.adb`, and the command following that tells make how to create `name_tester` from `name_tester.adb`. Note also that it has a clean target so that the command:

:::{code-block} bash
make clean
:::

will clean out all the extraneous files you create during this project, leaving on the source files and the *Makefile*, which can be used to rebuild the binary program files any time they are needed.

From a command-terminal in your project directory, enter:

:::{code-block} bash
make ada
:::

and you should see *name_tester.adb* build. Then run the program and verify that it runs correctly.

In keeping with the (imperative) Algol/Pascal family languages, Ada's aggregate type is called the **record**. Unlike a class, an Ada record can store data, but **not** operations. Our `Name` type will thus be a simple "wrapper" that encapsulates three string members.

### Declaring a Name Type

Recall that the Ada String type is really an array of characters. As a result, its size must be specified at some point. To specify the size of each part of a name, we will declare a constant. Recalling that an Ada constant can be declared using

:::{code-block} yaml
<ConstantDec>      ::=   <identifier> ':' 'constant' <Type> ':=' <Expression> ;
:::

begin by declaring a named constant `NAME_SIZE` equal to 8 as the size of a string to store a name. Rebuild and rerun your program to make certain this much is correct before continuing.

Then use the following BNF to declare a record type named `Name` (following the declaration of `NAME_SIZE`). The general pattern for an Ada record-type declaration is

:::{code-block} yaml
<RecordDec>        ::=   'type' <identifier> 'is'
                            'record'
                                <FieldList>
                            'end' 'record' ';' ;
<FieldList>        ::=   <VariableDeclaration> <MoreFields> ;
<MoreFields>       ::=   Ø | <Declaration> <MoreFields> ;
:::

where the `<VariableDeclaration>` can be any Ada variable declaration. To supply the data members (aka "fields") of `Name`, we need to declare three "string" fields:

:::{code-block} yaml
<StringDec>        ::=   <IdList> : 'String' '(' <Range> ')' ';' ;
<IdList>           ::=   <identifier> <MoreIds> ;
<MoreIds>          ::=   ',' <identifier> <MoreIds> | Ø ;
:::

use this information to declare three string components `MyFirst`, `MyMiddle` and `MyLast` within `Name`, each a String indexed using the range `1..NAME_SIZE`. Again, rebuild your program and make certain this much is correct before continuing.

### Defining Name Operations

Unlike a class, an Ada record can only store data -- not operations. Because of this, we must implement each `Name` operation as an "external" subprogram that operates on a `Name` object it receives via its parameters. We now consider each operation in turn.

**Initialization.** When declared within a program (as opposed to a library), Ada provides no constructor mechanism to automatically initialize the fields of a record. Instead, a subprogram can be defined, and then called to explicitly perform the initialization. Such a subprogram must receive the record-object to be initialized, as well as the initialization values, and then assign the initialization values to the appropriate fields of the object. Since such a subprogram changes the value of its argument (as opposed to returning a value), it should be defined as a procedure and not a function.

Uncomment the call to `Init()` in the `name_tester` procedure. Rebuild the program. **What happens?** To fix the problem, we need to define an `Init()` subprogram.

As we have seen before, Ada subprograms can be declared in a procedure's declaration section, along with constants, types, variables, and so on:

:::{code-block} yaml
<AdaProgram>   ::=  'procedure' <identifier> 'is'
                            <DeclarationSection>
                        'begin'
                            <StatementSection>
                        'end' <identifier> ;
:::

We can thus begin by defining a stub procedure prior to the begin in *name_tester.adb*:

:::{code-block} ada
procedure Init (TheName : out Name; First, Middle, Last : in String)

end Init;
:::

Note that information flows out of the procedure through parameter `TheName`, and into the procedure through parameters `First`, `Middle`, and `Last`. The modes of these parameters are thus declared accordingly.

To fill in the body of our stub, we must be able to access the fields of an aggregate type object. Like most other languages, Ada uses the dot (`.`) operator for this operation:

:::{code-block} yaml
<Expression>        ::=   <identifier> '.' <identifier> ;
:::

where the left identifier is the name of the aggregate object, and the right identifier is the field within the aggregate. Using these observations, we can complete our `Init()` procedure:

:::{code-block} ada
procedure Init(TheName: out Name;
                First, Middle, Last : in String) is

begin
    TheName.MyFirst := First;
    TheName.MyMiddle := Middle;
    TheName.MyLast := Last;
end Init;
:::

Given such a procedure, our program can now execute

:::{code-block} ada
Init(aName, "John    ", "Paul    ", "Jones   ");
:::

and the fields within `aName` will be initialized to the given arguments.

Note that the size of the string literals passed as arguments must match the size of the fields to which they are assigned, or a compilation error will result, because Ada's string variables are (strongly typed) arrays.

Check what you have written, and continue when it builds and runs without any errors.

**Accessors.** To check that our `Init()` procedure is working correctly, we need to be able to access the fields of a `Name` aggregate. Uncomment the first pragma `Assert` directive in procedure `name_tester;` then rebuild your program. **What happens?**

That first pragma `Assert` tries to access the first-name field within a `Name` aggregate using a procedure called `getFirst()`. To "pass this test", we can write a simple function `getFirst()` that, given a `Name` object, returns its `MyFirst` field. Recalling that an Ada function returns a value via a return statement, such a function can be defined by:

:::{code-block} ada
function getFirst(TheName : in Name) return String is
begin
    return TheName.MyFirst;
end getFirst;
:::

Add this definition at the appropriate place in the program; then rebuild and rerun your program to test its correctness.
Then uncomment the next two pragma `Assert` statements and add similar definitions for `getMiddle()` and `getLast()`.

Note that Ada's `Assert()` requires two arguments:

1. The boolean expression that is expected to be true; and
2. A diagnostic string that is to be displayed if the first argument is false.

The Ada compiler normally ignores such pragma statements; to counteract this, programs containing pragmas must be compiled with the `-gnata` switch

:::{code-block} bash
gnatmake name_tester.adb -gnata
:::

(which our *Makefile* provides). Continue when your program compiles and runs correctly.

**String Conversion.** Uncomment the final pragma `Assert` statement, and rebuild your program. **What happens?**

To pass this test, we need to define a `getFullName()` subprogram that, given a `Name` object, returns a corresponding string. Because of this, it should be written as a function whose return-type is a String. Using this information, create a stub for a function named `getFullName()`.

To fill in the body of the stub, we must concatenate together the fields of the `Name` parameter of the function, with intervening spaces. Recalling that Ada uses the `&` symbol as a concatenation operator:

:::{code-block} yaml
<Expression>          ::= <StringExpr> & <StringExpr> ;
:::

use this information to define `getFullName()` to return the string equivalent of the full name of its `Name` parameter. Then rebuild and rerun your program. Continue when it passes this test.

**Output.** For debugging (and other) purposes, an output subprogram is useful. In Ada, output subprograms are usually named `Put()`, so uncomment the `Put()` statement and rebuild the program. **What happens?**

As indicated in this test, a `Put()` subprogram for our `Name` type must receive the `Name` object to be displayed from its caller, and then display each field using the `Put()` command for string values:

:::{code-block} yaml
<OutputStatement> ::=   'Put' '(' <String> ')' ';' ;
:::

Using this information, define a `Put()` procedure that, given a `Name` object, displays each of the fields of that `Name` on the same line, with a space separating each field. Note that since information flows into our procedure via the `Name` parameter but not out (i.e., back to the caller), the procedure's parameter should be defined with mode in.

Make sure your program passes all of the tests and `Put()` correctly displays a name.

That concludes the Ada portion of this lab.