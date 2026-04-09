---
title: ->lab08;
subtitle: // Aggregate Types
---

## Overview

Last lab we examined the facilities in each of our languages for creating an object capable of storing multiple values of the *same type* — usually called an *array*. In this lab we examine the facilities each language provides for creating an object capable of storing multiple values of *arbitrary types*, which is generically known as an **aggregate**. Along the way, we will also encounter each language's `assert()` mechanism for automated unit testing.

We will look at the aggregate construct in each of our four languages:

- **Java**, where it is called the *class*
- **Ada**, where it is called a *record*
- **Clojure**, where it is also called a *record* (but compiles into a Java class)
- **Ruby**, where it is also called a *class*

As in past labs, we will solve the same problem in all four languages to compare their constructs. This week's problem is: create an aggregate type called `Name` to store a person's name, along with the following `Name` operations:

1. Initialize a `Name`, given three strings.
2. Access the first name of a `Name`.
3. Access the middle name of a `Name`.
4. Access the last name of a `Name`.
5. Convert a `Name` to a string.
6. Display a `Name` on the screen.

Since the sole purpose of each program is to test our `Name` operations, the program skeletons have no top-level algorithms — they consist entirely of test code.

Begin by accepting the project invitation from [GitHub Classroom: here](https://classroom.github.com/a/PLACEHOLDER). Then open VS Code through Coder and clone your repository.

Your repository contains starter files for all four languages: `NameTester.java`, `name_tester.adb`, `nameTester.clj`, and `NameTester.rb`. Each file provides a testing skeleton that exercises the `Name` operations you will implement. Take a moment to open each file and study it before you begin.

The order in which you complete the four language sections does not matter.

---

## Java

Open `NameTester.java` from your repository and take a moment to study it, noting how the test code is structured. In this section you will build a complete `Name` class alongside the tester.

### Building a `Name` Class

A Java class can be defined using the form:

:::{code-block} yaml
<ClassDec>  ::=  <Modifier> 'class' <identifier> '{' <Block> '}' ;
<Modifier>  ::=  'public' | 'private' | ∅ ;
:::

where `identifier` is the name of the new class. To support object-oriented programming, a Java aggregate can encapsulate both data components (called *data fields*) and function components (called *methods*).

For our `Name` class, we need three private `String` data fields — one for each part of the name — and methods that perform the operations listed in the introduction. Using this information, we can write the shell of a `Name` class:

:::{code-block} java
class Name
{
    private String myFirst,
                   myMiddle,
                   myLast;
}
:::

Add this declaration to `NameTester.java`. Then uncomment the following line in the `main()` method:

:::{code-block} java
Name aName = new Name("John", "Paul", "Jones");
:::

Save and rebuild your program. What happens? Continue when you understand the error.

The portions of the class that are `public` define its *interface*, while the portions that are `private` define its *implementation*.

### Initialization

Java objects are initialized by a *constructor method*: a method with no return type whose name matches the class name. Since the task of a constructor is to initialize the three data fields from the values it receives via its parameters, we can write:

:::{code-block} java
public Name(String first, String middle, String last)
{
    myFirst = first;
    myMiddle = middle;
    myLast = last;
}
:::

Add this constructor definition inside the `Name` class and rebuild your program. The declaration:

:::{code-block} java
Name aName = new Name("John", "Paul", "Jones");
:::

should now construct and initialize `aName` as the name *John Paul Jones*. Rebuild and run your program; continue when it builds and runs without errors.

### Accessor Methods

Uncomment the following line in `NameTester.java`:

:::{code-block} java
assert aName.getFirst().equals("John");
:::

Try to build your program. What happens?

Since a `Name` is an aggregate of three components, our class needs three *accessor* methods (also called "getters") to retrieve the value of each component. We can write an accessor for the first name as follows:

:::{code-block} java
public String getFirst()
{
    return myFirst;
}
:::

Add this to your `Name` class and verify that the program now builds and runs correctly.

**Note:** By default, assertions are disabled in Java. To enable them, run your program with the `-ea` (enable assertions) switch:

:::{code-block} bash
java -ea NameTester
:::

Using `getFirst()` as a model, define the other two accessor methods for the `Name` class; then uncomment the next two assertions. Your program should now be able to execute and pass all three:

:::{code-block} java
assert aName.getFirst().equals("John");
assert aName.getMiddle().equals("Paul");
assert aName.getLast().equals("Jones");
:::

Continue when all three accessors are correct.

We now have accessor methods (or "getters") that let us retrieve the value of a component. By contrast, operations that change the value of a component are called *mutators* (or "setters"), since they cause an object's state to change or mutate. Implementing `Name` mutators is a part of this week's project.

### Output and String Conversion

In `NameTester.java`, uncomment the line:

:::{code-block} java
System.out.println(aName);
:::

Rebuild your program. Do you get any errors? Run your program — what is displayed?

To fix this, we can write a `toString()` method that returns a `String` representation of a `Name` object. If we name this method `toString()`, Java will automatically use it whenever we print a `Name` object using a standard `print()` method. We might define it as follows:

:::{code-block} java
public String toString()
{
    return myFirst + ' ' + myMiddle + ' ' + myLast;
}
:::

After adding this method to the `Name` class, rebuild and rerun your program. The statement:

:::{code-block} java
System.out.println(aName);
:::

should now cause `John Paul Jones` to appear on the screen. Verify that this works correctly.

Thanks to the power of `toString()`, there is no need to create separate methods for printing and getting the full name. Either is technically possible — for example:

:::{code-block} java
public void print() {
    System.out.println(toString());
}
:::

While this definition is valid, you can see that it is superfluous since we can already print a `Name` object using Java's standard `print()` method. Similarly, `toString()` already returns the person's full name. Verify this by uncommenting the final assertion in `NameTester.java`, rebuilding, and rerunning your program.

Continue when all assertions pass.

---

## Ada

Open `name_tester.adb` from your repository and take a moment to study it before proceeding.

In keeping with the Algol/Pascal family of languages, Ada's aggregate type is called the **record**. Unlike a class, an Ada record stores data but not operations. Our `Name` type will therefore be a simple "wrapper" that encapsulates three string fields, with all operations defined as separate subprograms.

### Declaring a `Name` Type

Recall that Ada's `String` type is really an array of characters, so its size must be specified. To specify a uniform size for each part of a name, we will first declare a named constant. Recalling that an Ada constant can be declared using:

:::{code-block} yaml
<ConstantDec>  ::=  <identifier> ':' 'constant' <Type> ':=' <Expression> ';' ;
:::

begin by declaring a named constant `NAME_SIZE` equal to `8` as the character length of each name component. Rebuild and rerun your program to verify this much is correct before continuing.

Then use the following BNF to declare a record type named `Name` (after the declaration of `NAME_SIZE`):

:::{code-block} yaml
<RecordDec>   ::=  'type' <identifier> 'is'
                       'record'
                           <FieldList>
                       'end' 'record' ';' ;
<FieldList>   ::=  <VariableDeclaration> <MoreFields> ;
<MoreFields>  ::=  ∅ | <Declaration> <MoreFields> ;
:::

where each `VariableDeclaration` can be any Ada variable declaration. To supply the data fields of `Name`, we need to declare three string fields using:

:::{code-block} yaml
<StringDec>  ::=  <IdList> ':' 'String' '(' <Range> ')' ';' ;
<IdList>     ::=  <identifier> <MoreIds> ;
<MoreIds>    ::=  ',' <identifier> <MoreIds> | ∅ ;
:::

Use this information to declare three string fields `MyFirst`, `MyMiddle`, and `MyLast` within `Name`, each a `String` indexed using the range `1..NAME_SIZE`. Rebuild your program and make certain this much is correct before continuing.

### Defining `Name` Operations

Unlike a class, an Ada record can only store data — not operations. Because of this, each `Name` operation must be implemented as an "external" subprogram that receives a `Name` object via its parameters.

### Initialization

When declared within a program (as opposed to a library), Ada provides no constructor mechanism to automatically initialize a record's fields. Instead, we define a subprogram that explicitly performs initialization. Since such a subprogram changes the value of its argument rather than returning a value, it should be defined as a *procedure*, not a function.

Uncomment the call to `Init()` in the `name_tester` procedure, then rebuild your program. What happens?

To fix the problem, we need to define an `Init()` subprogram. Recall that Ada subprograms can be declared in a procedure's declaration section, alongside constants, types, and variables:

:::{code-block} yaml
<AdaProgram>  ::=  'procedure' <identifier> 'is'
                       <DeclarationSection>
                   'begin'
                       <StatementSection>
                   'end' <identifier> ';' ;
:::

We can begin by defining a stub procedure prior to the `begin` in `name_tester.adb`:

:::{code-block} ada
procedure Init (TheName : out Name; First, Middle, Last : in String) is
begin

end Init;
:::

Note that information flows *out* of the procedure through parameter `TheName`, and *into* the procedure through parameters `First`, `Middle`, and `Last`. The modes of these parameters are declared accordingly.

To fill in the body of our stub, we must be able to access the fields of an aggregate object. Like most languages, Ada uses the dot (`.`) operator for this:

:::{code-block} yaml
<Expression>  ::=  <identifier> '.' <identifier> ;
:::

where the left `identifier` is the aggregate object and the right `identifier` is the field within it. Using these observations, we can complete our `Init()` procedure:

:::{code-block} ada
procedure Init(TheName : out Name;
               First, Middle, Last : in String) is
begin
    TheName.MyFirst  := First;
    TheName.MyMiddle := Middle;
    TheName.MyLast   := Last;
end Init;
:::

Given such a procedure, our program can now execute:

:::{code-block} ada
Init(aName, "John    ", "Paul    ", "Jones   ");
:::

Note that the size of each string literal passed as an argument must match the declared field width (`NAME_SIZE`), or a compilation error will result — Ada's string variables are strongly typed arrays. Check what you have written and continue when it builds and runs without errors.

### Accessor Functions

To verify that our `Init()` procedure is working correctly, we need to be able to read the fields of a `Name` aggregate. Uncomment the first `pragma Assert` directive in `name_tester`, then rebuild. What happens?

That first assertion tries to access the first-name field using a function called `getFirst()`. To pass this test, we can write a simple function that, given a `Name` object, returns its `MyFirst` field:

:::{code-block} ada
function getFirst(TheName : in Name) return String is
begin
    return TheName.MyFirst;
end getFirst;
:::

Add this definition at the appropriate place in the program; then rebuild and rerun to test its correctness.

Then uncomment the next two `pragma Assert` statements and add similar definitions for `getMiddle()` and `getLast()`.

Note that Ada's `Assert()` requires two arguments: (1) the boolean expression expected to be true, and (2) a diagnostic string to be displayed if the first argument is false. The Ada compiler normally ignores such `pragma` statements; to enable them, programs must be compiled with the `-gnata` switch, which the provided `Makefile` already supplies:

:::{code-block} bash
gnatmake name_tester.adb -gnata
:::

Continue when your program compiles and runs correctly.

### String Conversion

Uncomment the final `pragma Assert` statement and rebuild your program. What happens?

To pass this test, we need to define a `getFullName()` function that, given a `Name` object, returns a corresponding `String`. Recall that Ada uses the `&` symbol as a string concatenation operator:

:::{code-block} yaml
<Expression>  ::=  <StringExpr> '&' <StringExpr> ;
:::

Create a stub for `getFullName()`, then use the `&` operator to concatenate the three fields of the `Name` parameter — separated by spaces — and return the result. Rebuild and rerun your program; continue when it passes this test.

### Output

For debugging and other purposes, an output subprogram is useful. Ada output subprograms are conventionally named `Put()`. Uncomment the `Put()` call and rebuild. What happens?

To pass this test, define a `Put()` procedure that receives a `Name` object and displays each of its fields on the same line, with a space separating each field, using the `Put()` command for string values:

:::{code-block} yaml
<OutputStatement>  ::=  'Put' '(' <String> ')' ';' ;
:::

Since information flows *into* the procedure via the `Name` parameter (and not back out), the parameter should be declared with mode `in`.

When your program passes all of the tests and `Put()` correctly displays a name, continue.

---

## Clojure

Open `nameTester.clj` from your repository and take a moment to study its structure. Note that the `-main()` function uses `assert()` calls to automate the testing of the functions you will write.

One of the differences between Clojure and traditional LISP is that Clojure offers modern mechanisms for creating named aggregate types. For situations where we want to aggregate different data values, Clojure lets us create a **record** type and then define operations on it. Importantly, because Clojure is a *functional* language rather than an object-oriented one, we will write "external" functions that receive a record and operate on it, rather than methods encapsulated inside the type.

Run `nameTester.clj` and observe what happens before continuing.

### Defining a Record Type

To represent 3-part names, we need to define a record type named `Name`. Clojure provides the `defrecord` function for this purpose:

:::{code-block} yaml
<DefRecFunction>  ::=  '(' 'defrecord' <identifier> '[' <IdList> ']' ')' ;
<IdList>          ::=  <identifier> <IdList> | ∅ ;
:::

When `defrecord` is executed, it creates a new type whose name is the `identifier` and whose fields have the names listed in `IdList`.

For example, to create a record type named `Point` with fields `x` and `y`:

:::{code-block} clojure
(defrecord Point [ x y ] )
:::

Note that no type information is specified — only field names.

Using this as a model, find the line in `nameTester.clj` that reads:

:::{code-block} clojure
; Replace this line with the definition of record-type Name
:::

and replace it with a definition of a record type named `Name` with fields `firstName`, `middleName`, and `lastName`. Run your program again and verify that this much is correct before proceeding.

Note that the Clojure compiler actually compiles such record definitions into Java classes — we will not use the full power this offers, but it is worth keeping in mind.

### Initialization

In LISP-family languages, the tradition is to perform initialization using a "make-X" function, where X is the type of the thing being initialized. For example, to initialize a `Point` object in Clojure, we might define:

:::{code-block} clojure
(defn make-Point [xVal yVal]
    (->Point xVal yVal)
)
:::

When executed, this function accepts two arguments and passes them on to a `->Point()` "factory function" that the Clojure compiler creates when it processes the `defrecord`. This factory function constructs a `Point` object, initializes its fields, and returns the resulting object.

In the `-main()` function, find and uncomment the following line:

:::{code-block} clojure
name1 (make-Name "John" "Paul" "Jones")  ; using our "make-" constructor
:::

Then run `nameTester.clj` again. What happens?

To make this work, use the preceding information to write a function named `make-Name` with three appropriately named parameters (e.g., `first`, `middle`, and `last`) that initializes the three fields of a `Name` object. Save your changes, rebuild, and run your program. Continue when no errors are produced.

**Using the Factory Function Directly.** Since our `make-Name()` function internally uses the `->Name()` factory function, we can also call the factory function directly. In the `-main()` function, find and uncomment the line:

:::{code-block} clojure
name2 (->Name "Jane" "Penelope" "Jones") ; invoking constructor directly
:::

Save, rebuild, and run your program. If all is well, this line should work correctly without any further changes.

**Using `map->Name`.** When `defrecord` is executed, the Clojure compiler also creates a third factory function that lets us initialize fields in any order by mapping field names to values:

:::{code-block} clojure
(let
    [ p1 (map->Point {:x 0.0 :y 0.0})
      p2 (map->Point {:y 3.45 :x 1.2})
    ]
    ...
:::

In the `-main()` function, find and uncomment the line:

:::{code-block} clojure
name3 (map->Name {:lastName "Jones" :firstName "Jinx" :middleName "Joy"})
:::

Save, rebuild, and run your program. If all is well, this line should work correctly without any further changes. Continue when it does.

### Output, v1

In `nameTester.clj`, the rest of `-main()` consists of three sections: one for `name1`, one for `name2`, and one for `name3`. In each section, uncomment the `println()` and `print()` calls at the beginning of the section.

Save, build, and run your program. Clojure will display its representation of each `Name` object. Because Clojure treats records as immutable data structures that *map* field names to values, the default output format displays the namespace, the record-type name, and each field name followed by its value. This is useful for debugging since it shows all the information in an object.

Compare the displayed information against the values you specified when initializing `name1`, `name2`, and `name3`. Verify that each constructor is working correctly before continuing.

### Accessors

Since our `Name` record stores values in named fields, it is useful to have accessor functions to retrieve those values. Uncomment the first `assert()` call in each of the three sections, save, rebuild, and run your program. What happens?

**Defining `getFirst()`.** To fix this, we need to define a `getFirst()` accessor function. Find the following line in `nameTester.clj`:

:::{code-block} clojure
; Replace this line with the definition of getFirst()
:::

and replace it with a stub for `getFirst()` that has a single parameter `aName`.

When we only want `Name` objects to be passable to our function, we can provide a **compiler hint**. Using our `Point` class as an illustration:

:::{code-block} clojure
(defn getX [^Point aPoint]
    ; ToDo: complete this function
)
:::

Placing `^Point` before parameter `aPoint` tells the compiler to reject non-`Point` arguments. Add a similar compiler hint to your `getFirst()` stub.

To complete the stub, we need to retrieve the value of a field from a record. Clojure supports the syntax:

:::{code-block} clojure
(:fieldName objectName)
:::

to retrieve the value stored in `fieldName` within `objectName`. For example, the `getX()` function for a `Point` can be completed as:

:::{code-block} clojure
(defn getX [^Point aPoint]
    (:x aPoint)
)
:::

Using this as a model, complete your definition of `getFirst()`. Save, rebuild, and run your program. Continue when `getFirst()` passes all three tests in `-main()`.

**Defining `getMiddle()`.** Uncomment the second `assert()` in each of the three sections. Save, rebuild, and run your program. What happens?

Find the line:

:::{code-block} clojure
; Replace this line with the definition of getMiddle()
:::

and use what you wrote for `getFirst()` as a model to implement `getMiddle()`. Save, rebuild, and run your program. Continue when your function passes all three tests.

**Defining `getLast()`.** Uncomment the third `assert()` in each of the three sections. Save, rebuild, and run your program. What happens?

Using what you have learned, add a `getLast()` function that retrieves the value of the `lastName` field. Continue when all three accessors pass their tests.

### Mutators

For future reference, it is worth noting that unlike our other languages, Clojure records are *immutable* data structures. This means we *cannot* define mutators that change the value of an existing field in the usual sense. Instead, a "mutator" function must build and return a new copy of the record in which all fields are the same *except* for the one being mutated. To illustrate using our `Point` class:

:::{code-block} clojure
(defn setY [aPoint newY]
    (->Point (:x aPoint) newY)
)
:::

This function builds and returns a new `Point` whose `x` field matches `aPoint` but whose `y` field is `newY`. A `let()` might use such a mutator like this:

:::{code-block} clojure
(let
    [ p1 (->Point 0.0 0.0)
      p2 (setY p1 1.5)
    ]
    ...
:::

The object `p2` will be a mutated version of `p1` in which `y` has the value `1.5` instead of `0.0`.

### String Conversion

Being able to convert an object to a string representation is useful for a variety of purposes. Uncomment the fourth `assert()` in each section, save, rebuild, and run your program to verify that the code fails these tests.

To pass the tests, we must define a `toString()` function that converts a `Name` object into a string. Find the following line in `nameTester.clj` and replace it with a stub for `toString()`:

:::{code-block} clojure
; Replace this line with a definition of toString()
:::

(Don't forget the compiler hint!)

Since the fields of our `Name` type are all strings, completing `toString()` requires concatenating and returning the three fields, separated by spaces. We can use the `str` function for concatenation:

:::{code-block} yaml
<Expression>     ::=  '(' 'str' <ExpressionList> ')' ;
<ExpressionList> ::=  <Expression> <MoreExprs> ;
<MoreExprs>      ::=  <Expression> <MoreExprs> | ∅ ;
:::

Given a sequence of expressions, `str` concatenates them into a single string and returns it.

Using `str` and your three accessor functions, complete the definition of `toString()` so that it returns the three fields of a `Name` object — separated by spaces — as a single string. Save, rebuild, run, and verify that `toString()` passes the tests. Continue when it does.

### Output, v2

As we have seen, Clojure's default output format for a record provides lots of debugging information. For nicer human-readable output, we can write a function `printName()` that, given a `Name` object, prints the result of calling `toString()` on it.

In each of the three sections, uncomment the call to `printName()` at the end of the section. Above the `-main()` function, find the line:

:::{code-block} clojure
; Replace this line with a definition of printName()
:::

and replace it with a definition of `printName()` that, given a `Name` object, uses `print()` and `toString()` to display the string representation of that object. (Don't forget the compiler hint!)

At this point, all key lines in `-main()` should be uncommented. Double-check that this is the case — we want to be able to compare the output of the standard `print()` function and our `printName()` function for each of our three `Name` objects.

Save any changes, rebuild, and run the program. Continue when everything works correctly.

---

## Ruby

Open `NameTester.rb` from your repository and take a few minutes to study its contents. Then run it and verify that it runs correctly before continuing.

Classes are one of the major strengths of Ruby. Ruby's `Array`, `String`, `Hash`, and user-defined types are all first-class objects with built-in methods, and the same will be true of our `Name` class.

### The `Name` Class

Uncomment the line:

:::{code-block} ruby
name = Name.new("John", "Paul", "Jones")
:::

Then re-run your program. What happens?

To fix the problem, we need to define a Ruby class named `Name`. Here is the basic BNF:

:::{code-block} yaml
<ClassDec>        ::=  'class' <identifier> <SectionList> 'end' ;
<SectionList>     ::=  ∅ | <Specifier> <DeclarationList> <SectionList> ;
<Specifier>       ::=  'public' | 'private' | 'protected' ;
<DeclarationList> ::=  ∅ | <Declaration> <DeclarationList> ;
:::

Note that the `identifier` for Ruby classes must always begin with a capital letter, because Ruby requires that constants begin with a capital letter — and a class is effectively a constant (a static blueprint that does not change).

Using the BNF above, create the following skeleton in `NameTester.rb`:

:::{code-block} ruby
class Name

end
:::

Add this to `NameTester.rb`. Now we just have to fill in the operations!

### Initialization

To initialize the members of a Ruby class, we define a method named `initialize()`:

:::{code-block} ruby
class Name

    def initialize(first, middle, last)
        @first, @middle, @last = first, middle, last
    end

end
:::

The `initialize()` method serves as the class constructor and is called automatically when you send the class the `new` message, as in `Name.new`.

The technique used here is called *parallel assignment* — it allows us to assign `@first`, `@middle`, and `@last` all on one line. Note also that identifiers beginning with `@` are *instance variables* (or *attributes*) in Ruby. Unlike other languages, Ruby does not require instance variables to be declared before use — they come into existence simply by being assigned inside a method.

Add this to your `Name` class and test what you have done so far. If you have not made any mistakes, your program should pass the initial test.

### Accessors

Uncomment the first `assert` line in the driver and re-run your program. What happens?

To fix this, we could write separate accessor methods for each instance variable — as we have done in our other languages. However, Ruby provides a way to create all our accessor methods in a single line:

:::{code-block} ruby
attr_reader :first, :middle, :last
:::

Add this line to your `Name` class. To understand what it does, consider that Ruby provides the following special shortcut commands:

| Shortcut | Equivalent Code |
|----------|-----------------|
| `attr_reader :member` | `def member; @member; end` |
| `attr_writer :member` | `def member=(newMember); @member = newMember; end` |
| `attr_accessor :member` | `attr_reader :member; attr_writer :member` |

The `attr_reader` command conveniently defines "getters", `attr_writer` defines "setters", and `attr_accessor` defines both. We use `attr_reader` here because we only need to retrieve values — not change them.

To specify which attributes to set up, we pass their names as **symbols** — a Ruby construct for lightweight, immutable labels, created by prepending a colon to a name (e.g., `:first`, `:middle`, `:last`). The single line:

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

Uncomment the assertions for all three "getters", re-run your program, and verify that your class passes all three tests before continuing.

### The `fullName` Method

Uncomment the next-to-last assertion, run your program, and verify that it fails.

To pass the test, define a `fullName` method that converts a `Name` into a string. In Ruby, the `+` operator performs string concatenation. Using this information, define `fullName` to return the concatenated full name. Re-run your program and verify that the method passes the test before continuing.

### The `print` Method

Uncomment the final assertion in the program and re-run it to verify that it fails.

To pass this test, write a method that prints the full name to the screen and returns that name to the caller. Since we have already defined `fullName`, this is straightforward. One important note: use `puts` rather than `print` inside this method, because calling `print` within a method also named `print` can cause a conflict.

Note that the test expects this method to return the name being printed — be sure to make it do so. (Remember, Ruby methods return the last expression they evaluate.) Make sure that all of the test code is uncommented and that your class passes every test before continuing.

---

## Submission

When all four language files are complete and producing correct output, commit and push your work to your repository.

Make sure each of the four files — `NameTester.java`, `name_tester.adb`, `nameTester.clj`, and `NameTester.rb` — contains a working `Name` type with all required operations before submitting.

## Rubric

| Task | Points |
|------|--------|
| Java: `Name` class with three private fields and constructor | 10 |
| Java: `getFirst()`, `getMiddle()`, `getLast()` accessor methods | 10 |
| Java: `toString()` returns correctly formatted full name | 5 |
| Ada: `NAME_SIZE` constant and `Name` record type declared correctly | 5 |
| Ada: `Init()` procedure initializes all three fields correctly | 10 |
| Ada: `getFirst()`, `getMiddle()`, `getLast()` functions | 10 |
| Ada: `getFullName()` function and `Put()` procedure | 5 |
| Clojure: `defrecord Name` with correct field names | 5 |
| Clojure: `make-Name` function initializes all three fields | 5 |
| Clojure: `getFirst()`, `getMiddle()`, `getLast()` accessor functions | 10 |
| Clojure: `toString()` and `printName()` implemented correctly | 5 |
| Ruby: `Name` class with `initialize` and `attr_reader` | 10 |
| Ruby: `fullName` method returns correctly formatted string | 5 |
| Ruby: `print` method displays and returns the full name | 5 |
| **Total** | **100** |
