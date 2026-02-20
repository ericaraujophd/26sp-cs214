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

Begin by accepting the project invitation from [GitHub Classroom: here](https://classroom.github.com/a/c54OYrOu). Then, open VS Code through Coder and clone your repository.

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

## Clojure

As usual, begin opening the program skeleton *nameTester.clj* from the directory `clojure/src` in your repository. Take a moment to study its structure. Note that the `-main()` function uses `assert()` function calls to automate the testing of the functions we will be writing.

### Representing a Name

One of the differences between Clojure and traditional LISP is that Clojure offers a variety of modern mechanisms for creating named aggregate types. For situations where we want to aggregate different data values in the type, Clojure lets us create a record type, and then define operations on that type. For example, if we need to model a name, consisting of a `first-name`, `middle-name`, and `last-name`:

:::{code-block} clojure
"John" "Paul" "Jones"
:::

Then we can define a record containing three fields, one for each name-component. However, Clojure is a **functional language**, not an object-oriented language. As such, it doesn't really provide a construct (i.e., class) for explicitly encapsulating both data and functionality in the OO sense. Instead, we will have to write "external" functions that, given a record representing a name, will perform the appropriate operation on that name.

Start by running the program in *nameTester.clj*. **What happens?**

### Defining a Record Type

To represent 3-part names, we need to define a record-type named `Name`. To define a record type, Clojure provides the defrecord function, which has the following form:

:::{code-block} yaml
<DefRecFunction>   ::= '(' 'defrecord' <identifier> '[' IdList ']' ')' ;
<IdList>           ::= <identifier> <IdList> | Ø ;
:::

When the `defrecord()` function is executed, it creates a new type whose name is the identifier, and whose fields have the names listed in the `<IdList>`.

For example, if we wanted to create a new record-type named `Point`, with fields `x` and `y`, then we could write:

:::{code-block} clojure
(defrecord Point [ x y ] )
:::

Note that we do not specify any type information; we simply indicate the names of the fields.

Using this as a model, find the line in *nameTester.clj* that looks like this:

:::{code-block} clojure
; Replace this line with the definition of record-type Name
:::

and replace it with a line that defines a record-type named `Name`, with fields `firstName`, `middleName`, and `lastName`.
Run your program again and make certain the code you have added builds and runs correctly before proceeding.

Before we proceed further, it is worth mentioning that the Clojure compiler actually compiles such record definitions into Java classes. To keep things simple, we are not using the full power this offers, but keep this in mind going forward.

### Initialization

In LISP-family languages, the tradition is to perform initialization using a "make-X" function, where `X` is the type of the thing being initialized. For example, to initialize a `Point` object in Clojure, we might define a `make-Point()` function as follows:

:::{code-block} clojure
(defn make-Point [xVal yVal]
    (->Point xVal yVal)
)
:::

When executed, this function accepts two arguments via parameters `xVal` and `yVal`, and passes them on to a `->Point()` "factory function", the the Clojure compiler creates when it executes a `defrecord()` function. This "factory function":

1. constructs a Point object,
2. initializes its `x` field to `xVal`,
3. initializes its `y` field to `yVal`, and
4. returns the resulting Point object.

Given our `make-Point()` function, a `let()` function could then use it to initialize `Point` objects:

:::{code-block} clojure
(let
    [ p1     (make-Point 0.0 0.0)
      p2     (make-Point 1.2 3.45)
    ]
    ...
:::

In function `-main()`, find and uncomment the following line:

:::{code-block} clojure
name1 (make-Name "John" "Paul" "Jones")  ; -using our "make-" constructor
:::

Then run *nameTester.clj* again. **What happens?**

To make this work, use the preceding information to write a function named `make-Name` with three appropriately-named parameters (e.g., `first`, `middle`, and `last`), and uses those parameters to initialize the three fields of a `Name`.

Save your changes; then rebuild and run your program to test what you have written. Continue when no errors are produced.

### Using the Factory Function

You may be asking, since our `make-Name()` function used the `->Name()` factory function, why can't we just use that factory function directly? The answer is, we can!

To illustrate using our `Point` type, a `let()` function could use the following to initialize `Point` objects:

:::{code-block} clojure
(let
    [ p1     (->Point 0.0 0.0)
      p2     (->Point 1.2 3.45)
    ]
    ...
:::

Our *nameTester.clj* program already contains such an initialization. In the `-main()` function, find and uncomment the line:

:::{code-block} clojure
name2 (->Name "Jane" "Penelope" "Jones") ; -invoking constructor directly
:::

Save your changes; then rebuild and run your program. If all is well, this line should work correctly without any further changes, thanks to `(defrecord Name ...)`.

### Using `map->X`

When it executes a `defrecord()` function, the Clojure compiler also creates another factory function that provides us with a third mechanism for initialization. This mechanism combines the built-in `map()` function with the `->X()` factory function. It lets us map initialization values directly to field-names, and so initialize the fields in whatever order one desires.

To illustrate, a `let()` function could use this form to initialize `Point` objects as follows:

:::{code-block} clojure
(let
    [ p1     (map->Point {:x 0.0 :y 0.0} )
      p2     (map->Point {:y 3.45 :x 1.2} )
    ]
    ...
:::

Our *nameTester.clj* program already contains such an initialization. In the `-main()` function, find and uncomment the line:

:::{code-block} clojure
name3 (map->Name {:lastName "Jones" :firstName "Jinx" :middleName "Joy"})
:::

Save your changes; then rebuild and run your program. If all is well, this line should work correctly without any further changes. Continue when this is the case.

### Output, v1

To test that our `Name` initialization function is working correctly, we can try to output our `Name` objects.

In *nameTester.clj*, the rest of the `-main()` function consists of three sections: one that tests `name1`, one that tests `name2`, and one that tests `name3`. In each of these sections, uncomment the `println()` and `print()` calls at the beginning of the section.

Save these changes, build, and run your program. Clojure will display its representation of each of our three `Name` objects.

Clojure treats records as immutable data structures that map field names to values. As a result, the default format for outputting a record-type is to display the namespace containing that type, the name of the record-type, and for each field within it: the name of the field followed by its value. This is good for debugging since it lets us see all of the information in a given object.

Compare the displayed information against what your specified when initializing `name1`, `name2`, and `name3`. When your have verified that each of your constructors is working correctly, continue.

### Accessors

Since our `Name` record stores values in fields, it would be useful to have accessor functions to retrieve the values of those fields, so let's write them next.

Uncomment the first `assert()` function call in each of these sections. Save your changes and then rebuild and run your program. **What happens?**

### Defining `getFirst()`

To fix this, we need to define a `getFirst()` accessor function for our `Name` record-type.

In *nameTester.clj*, find the following line:

:::{code-block} clojure
; Replace this line with the definition of getFirst()
:::

and replace it with a stub definition for `getFirst()`. Your stub should have a single parameter (i.e., `aName`), as indicated in the function specification.

In this situation, we do not want arbitrary objects to be passable to our `getFirst()` function -- we want to limit things so that only `Name` objects can be passed. In these situations, you can provide a compiler hint that tells the clojure compiler to reject non-`Name` arguments.

To illustrate using our `Point` class, we might create the following stub for a `getX()` accessor function:

:::{code-block} clojure
(defn getX [^Point aPoint]
    ; ToDo: complete this function
)
:::

By placing `^Point` before parameter `aPoint`, we tell the compiler to only accept `Point` arguments for this function.
Using that as a model, add a compiler hint to your `getFirst()` stub, so that the compiler will only accept `Name` arguments in calls to `getFirst()`.

To complete the stub, we need to retrieve the value of a field from a record. To see how to do this, let's return once again to our `Point` example. To complete the `getX()` function for a `Point`, we could write:

:::{code-block} clojure
(defn getX [^Point aPoint]
    (:x aPoint)
)
:::

That is, Clojure supports the syntax

:::{code-block} clojure
(:fieldName objectName)
:::

to retrieve the value stored in `fieldName` within `objectName`.

Using this as a model, complete your definition of your `getFirst()` function. Save your changes, rebuild, and run your program to test what your have written. Continue when your `getFirst()` function passes the three tests in `-main()`.

### Defining `getMiddle()`

Next, uncomment the second `assert()` function in each of the three sections. Save, rebuild, and run your program. **What happens?**

Find the line:

:::{code-block} clojure
; Replace this line with the definition of getMiddle()
:::

Using what you wrote for `getFirst()` as a model, implement the `getMiddle()` accessor function.

As before, save, rebuild, and run your program to test your work. Continue when your function passes all three tests.

### Defining `getLast()`

Next, uncomment the third `assert()` function in each of the three sections; save, rebuild, and run your program. **What happens?**

Using what you have learned so far, add a `getLast()` function that retrieves the value of the `lastName` field. Continue when all three accessors are correctly passing their tests.

### Mutators

For future reference, it is worth mentioning that unlike our other languages, Clojure records are immutable data structures. This means that we cannot define mutators (i.e., functions that change the value of an object's field) in the usual sense. Instead, a "mutator" function must build and return a new copy of the record in which all the fields are the same except for the field being mutated, which gets the new value.

To illustrate using our `Point` class, we might define a `setY()` mutator as follows:

:::{code-block} clojure
(defn setY [aPoint newY]
  (->Point (:x aPoint) newY)
)
:::

This function has two parameters `aPoint` and `newY`, and builds and returns a new `Point` whose `x` field is the same as that of `aPoint` but whose `y` field is `newY`.

A `let()` function might use such a mutator something like the following:

:::{code-block} clojure
(let
    [ p1 (->Point 0.0 0.0) 
        p2 (setY p1 1.5)
    ]
...
:::

The object `p2` will be a mutated version of `p1` in which `y` has the value `1.5` instead of `0.0`.

### String Conversion

Being able to convert an object to a string representation can be useful for a variety of purposes, so let's do that next.

Uncomment the fourth `assert()` function in each section. Save your changes, rebuild, and run your program, to verify that the code fails these tests.

To pass the test, we must define a `toString()` function that converts a `Name` object into a string. Find the following line in `nameTester.clj` and replace it with a stub for `toString()`:

:::{code-block} clojure
; Replace this line with a definition of toString()
:::

(Don't forget the compiler hint!)

Since the fields of our `Name` type are all strings, completing the definition of `toString()` consists of concatenating and returning the three fields of a `Name`, separated by spaces. To perform the concatenation, we can use the `str` function:

:::{code-block} yaml
<Expression>        ::=   '(' 'str' <ExpressionList> ')' ;
<ExpressionList>    ::=   <Expression> <MoreExprs> ;
<MoreExprs>         ::=   <Expression> <MoreExprs> | Ø ;
:::

Given a sequence of expressions, the `str` function concatenates them together into a single string and returns that string.
Using the `str` function and your three accessor functions, complete the definition of `toString()` so that, given a `Name` object, its returns the three fields of that object (separated by spaces) as a single string.

Save your changes, rebuild, run your program, and verify that `toString()` passes the tests. Continue when it does.

### Output, v2

As we have seen, Clojure's default format for outputting a record provides lots of information that is useful for debugging. But what if we just want the values stored in a record's fields, without all of the other information?

To provide nicer output for the average human, we can write a function that, given a `Name` object, prints the result of calling our `toString()` function on that object. Thanks to our `toString()` function, this is quite easy -- we'll call this function `printName()` to keep it distinct from the standard output functions.

In each of the three sections, uncomment the call to `printName()` at the end of the section. Above the `-main()` function, find the line:

:::{code-block} clojure
; Replace this line with a definition of printName()
:::

and replace it with a definition of a `printName()` function that, given a `Name` object, uses `print()` and `toString()` to display our string representation of that object.

(Don't forget the compiler hint!)

At this point, all of the key lines of function `-main()` (i.e., those that test our functions) should be uncommented. Double-check that this is the case; we want to be able to see and compare the calls to the standard `print()` function and our `printName()` function for each of our `Name` objects.

Save any changes; rebuild, and run the program. Continue when everying works correctly. and ensure all functions are well documented with pieroids at the end.

That concludes the Clojure part of this lab.

## Ruby

We'll now be looking at one of the major strengths of the Ruby: classes!

Start by opening the file *NameTester.rb* from the repository. Take a few minutes to look over its contents. Then run it using the command:

:::{code-block} bash
ruby NameTester.rb
:::

and verify that it runs correctly.

### The `Name` class

Uncomment the line:

:::{code-block} ruby
name = Name.new("John", "Paul", "Jones")
:::

Then re-run your program. **What happens?**

To fix the problem, we need to define a Ruby class named `Name`. Here is the basic BNF:

:::{code-block} yaml
<ClassDec>        ::= 'class' <identifier> <SectionList> 'end' ;
<SectionList>     ::= ∅ | <Specifier> <DeclarationList> <SectionList> ;
<Specifier>       ::= 'public' | 'private' | 'protected' ;
<DeclarationList> ::= ∅ | <Declaration> <DeclarationList> ;
:::

### Building on the BNF

The identifier for Ruby classes must always begin with a capital letter. This is because Ruby has a semantic rule that constants must begin with a capital letter. If you think about what a class is, it is a blueprint -- a static structure that doesn't change -- and so this semantic rule makes a lot of sense.

Using the BNF above, we might create this skeleton to work with:

:::{code-block} ruby
class Name

end
:::

Add this to *NameTester.rb*. Now, we just have to fill in the operations!

### Operations: Initialization

To initialize the members of a class in Ruby, we define a method named (surprise!) `initialize()`:

:::{code-block} ruby
class Name

    def initialize(first, middle, last)
    @first, @middle, @last = first, middle, last
    end

end
:::

The `initialize()` method serves as the class constructor. It gets called when you send the class the new message, as in `Name.new`.

Looking at the definition of `initialize()` for `Name`, we can see an interesting bit of Ruby syntax. The technique here is called **parallel assignment**, and it allows us to chain together pairs of assignment statements so that we can assign `@first`, `@middle`, and `@last` all on one line.

Add this to your `Name` class. Then test what you have done so far. If you've not made any mistakes, your program should now pass the test!

:::{admonition} \@ttributes
:class: tip
You'll notice that the variables on the left side of the assignment statement are prepended by `@` symbols. That is because identifiers that begin with `@` are instance variables or attributes in Ruby. Unlike other languages, Ruby does not require instance variables to be declared, aside from using their names in a method.
:::

### Operations: Accessors

Uncomment the first assert line in the driver. Re-run your program. **What happens?**

To fix this, we could write separate accessor methods for each of our instance variables as we have done in our other languages. However Ruby provides a way for us to write all of our accessor methods in a single line of code:

:::{code-block} ruby
class Name

    def initialize(first, middle, last)
    @first, @middle, @last = first, middle, last
    end

    attr_reader :first, :middle, :last

end
:::

Take a moment to add this line to your class.

To better understand what this line does, consider that Ruby provides the following special "shortcut" commands:


| Shortcut | Is A Shortcut For |
|----------|-------------------|
| attr_reader :member | def member; \@member; end |
| attr_writer :member | def member=(newMember); \@member = newMember; end |
| attr_accessor :member | attr_reader :member; attr_writer :member |

The `attr_reader` command thus lets us conveniently define "getters", the `attr_writer` command lets us define "setters", and the `attr_accessor` lets us define both!

We have used `attr_reader` because all we need is the reader-type accessor methods. As indicated, one `attr_reader` can handle multiple members if you separate them with commas.

In order to use any of these shortcuts, you'll need to give them the name of the attributes to set up. As we've shown above, Ruby provides a special format used for labeling and identifying called the symbol. A symbol is like a lightweight string, and it's used extensively in Ruby. The syntax is simple: just prepend a colon to a string of characters. Examples of symbols include `:name`, `:id`, and `:hello`.

In the example above, we pass `attr_reader` a list of our attributes as symbols. It then uses those symbols to generate reader-methods for us. The one line:

:::{code-block} ruby
attr_reader :first, :middle, :last
:::

saves us from having to write:

:::{code-block} ruby
def first
    @first
end

def middle
    @middle
end

def last
    @last
end
:::

We don't need it (so don't do it), but if we were to write

:::{code-block} ruby
class Name

    attr_accessor :first

end
:::

it would be the same as if we had written the following code:

:::{code-block} ruby
class Name

    def first
    @first
    end

    def first=(new_first)
    @first = (new_first)
    end

end
:::

If we wanted to write readers and writers that had special functionality (e.g., writers that validated their parameters), we could use this approach to write them. But since all we need is to retrieve our instance variable values, we will stick to `attr_reader`.

If you have not already done so, use this information to create accessors for the three instance variables; then make certain that the assertions for all three "getters" are uncommented, re-run your program, and verify that your class passes all three of those tests before continuing.

### Operations: The `fullName` Method

Uncomment the next-to-last assertion, run your program, and verify that the assertion fails.

To pass the test, we must define a `fullName` method that converts a `Name` into a string. To do so, we need a way to concatenate strings. In Ruby, we can use the `+` operator to perform string concatenation.

Using this information, define method `fullName`. Then re-run your program and verify that your method now passes the test. Continue when it does.

### Operations: The `print` Method

Uncomment the final assertion in the program. Re-run your program and verify that we fail the test.

To pass this test, we need to write a method that prints the full name to the screen and then returns that name to the caller. Since we have defined the `fullName` method, this is simple. The only "gotcha" is that you'll need to use puts instead of `print` to do the actual output, because calling `print` within a method called `print` can cause a problem...

Note that the test expects this method to return the name being printed, so be sure to make it do so. (Remember, Ruby methods return the last expression they evaluate.) Aside from that, the method should be easy to write.

When your are done, make sure that all of the test-code is uncommented and test your class. When everything works correctly, take a few minutes to document each of your methods. and ensure all functions are well documented and ensure that in at least one comment appears three periods in a row.

That concludes the Ruby part of this lab.

Make sure to commit and push your work to your repository when you are done.

## Rubric

| Task | Points |
|------|--------|
| Java: Define a `Name` class with appropriate fields and methods | 20 |
| Ada: Define a `Name` record with appropriate fields and operations | 20 |
| Clojure: Define a `Name` record with appropriate fields and functions | 20 |
| Ruby: Define a `Name` class with appropriate fields and methods | 20 |
| Documentation: All methods are well documented with comments | 10 |
| Code Style: Code is clean, well-organized, and follows language conventions | 10 |
| **Total** | **100** | 

Ways to lose points include:

- Failing to define the required class/record types and their fields
- Failing to implement the required methods/functions correctly
- Failing to pass the provided tests in the driver code
- Failing to document methods with comments
- Poor code style (e.g., inconsistent indentation, unclear variable names, etc.)
- Not committing and pushing work to the repository
