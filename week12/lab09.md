---
title: ->lab09;
subtitle: // Modularity and Separate Compilation
---

## Overview

Last week, we examined the facilities in each of our languages for creating an aggregate object — an object made up of multiple values of arbitrary types. This week, we examine the facilities of each of our languages for making such objects and their operations reusable by storing them in a separately compiled module.

Modular programming is an extension of abstract data type (ADT) programming, in which a programmer decides what ADTs they need to solve their problem, builds them (usually using an aggregate), and encapsulates the ADT and its operations in a separately compiled module.

The module concept has two basic goals:

1. **Reusability** — make an ADT reusable by storing it and its operations in a separately compiled module (a.k.a. encapsulation).
2. **Information hiding** — make an ADT easier to maintain by preventing programs from accessing its data directly.

Some languages provide a separate module language construct; others have no construct but provide a mechanism for achieving similar results.

We will look at the module facility in each of our four languages. As in past weeks, we will do the same thing in each language, to compare their particular features. Our exercise will be to take our `Name` type from last week, and its operations, and store them in the "module" for each language. (You may want to review your text editor's cut-and-paste skills, since we will be cutting work from last week's program and pasting that work into a module.)

As usual, we will provide program "skeletons" that provide a framework for testing your work. Since the sole use of these programs will be to test our modules, they have no algorithms.

We will lead you through the process using Java, after which you will apply similar techniques to solve the problem in the other three languages. The order in which you do the three exercises does not matter.

## Getting Started

Begin by accepting the project invitation from [GitHub Classroom: here](LINK_PLACEHOLDER). Clone the repository and open it in your editor.

A `Makefile` is provided in the repository root. You can build and run any single language with `make java`, `make ada`, `make clojure`, or `make ruby`. Use `make all` to run all four, and `make clean` to remove compiled artifacts.

---

## Java

### Modules in Java

Java was designed around the object-oriented programming paradigm. Modules are essential to object-oriented programming and thus to Java. The `import` directive is used in Java for importing packages not natively imported by the Java compiler. Any user-defined object declared in a program is either referred to by the `import` directive or is a compiled class somewhere in the classpath environment variable.

### Creating a Java Module

Using your text editor's cut-and-paste capabilities, cut the entire definition of class `Name` from the file `NameTester.java` and paste it into the file `Name.java`.

Remember that method declarations and definitions are not separated into two different files in Java as they are in C++.

### Using a Java Module

To use a Java module, the compiled module (`.class` file) must be in the current directory or in a directory included by the classpath environment variable.

Java source files (`.java` extension) are compiled using the `javac` byte-compiler:

```console
javac -deprecation SourceFile.java
```

This generates bytecode (in a `.class` file) as opposed to object files generated in most languages. This bytecode may be used by a Java interpreter on any platform. The Java interpreter may be invoked by entering:

```console
java SourceFile
```

:::{note}
The `.class` extension should **not** be used when running with the `java` command.
:::

By compiling a source file using `javac`, each supporting class will be compiled as well — no special linking commands are needed in Java. Compile `NameTester.java`, then look in your directory to see both `NameTester.class` and `Name.class`.

### Testing

Run your `NameTester` program with assertions enabled:

```console
javac NameTester.java
java -ea NameTester
```

Or simply run `make java` from the repository root.

Check that it runs correctly and all tests pass. Ensure all functions are well documented.

---

## Ada

### Ada Modules

Unlike C-family languages, Ada provides a distinct construct to represent a module. Ada calls its construct the **package**. An Ada package consists of two parts:

- A **package specification** (`.ads` file), which declares public and private items (constants, types, and subprograms). This is the Ada counterpart to a C/C++ header file.
- A **package body** (`.adb` file), which provides the definitions (usually of subprograms). This is the Ada counterpart to a C/C++ implementation file.

The general form of an Ada package specification is:

:::{code-block} bnf
<PackageSpec>      ::=   'package' <identifier> 'is'
                           <PublicSection>
                           <PrivateSection>
                         'end' <identifier> ';' ;
<PublicSection>    ::=   <DeclarationList> ;
<PrivateSection>   ::=   'private' <DeclarationList> | Ø ;
<DeclarationList>  ::=   <Declaration> ';' <DeclarationList> | Ø ;
<Declaration>      ::=   <ConstantDec> | <VariableDec> | <TypeDec> | <SubprogDec> ;
<SubprogDec>       ::=   <FunctionDec> | <ProcedureDec> ;
<FunctionDec>      ::=   'function' <identifier> '(' <ParameterDecs> ')' 'return' <Type> ;
<ProcedureDec>     ::=   'procedure' <identifier> '(' <ParameterDecs> ')' ;
:::

The syntax of the package body:

:::{code-block} bnf
<PackageBody>      ::=   'package' 'body' <identifier> 'is'
                           <DefinitionList>
                         'end' <identifier> ';' ;
<DefinitionList>   ::=   <Definition> ';' <DefinitionList> | Ø ;
<Definition>       ::=   <ConstantDec> | <VariableDec> | <TypeDec> | <SubprogramDef> ;
:::

### Building the Package

1. **Stub specification:** Using the BNF above as a pattern, add a stub package specification named `Name_Package` to `name_package.ads`, and add a stub package body named `Name_Package` to `name_package.adb`.

2. **Public section:** Copy-and-paste the documentation and headings of each subprogram — `Init()`, `getFirst()`, `getMiddle()`, `getLast()`, `getFullName()`, and `Put()` — from `name_tester.adb` into the public section of the `Name_Package` specification. Modify them as necessary to convert those headings into subprogram *declarations* (don't forget the semicolon following each declaration).

3. **Private section:** Cut the declarations of `NAME_SIZE` and `Name` from `name_tester.adb` and paste them into the **private** section of the `Name_Package` specification.

4. **Resolving the "Catch-22":** When `Name` is placed in the private section, it becomes inaccessible to users. But placing it in the public section exposes implementation details. Ada resolves this by allowing a type identifier to be declared in one place and its internal details specified in another:

   ```ada
   type Name is private;
   ```

   Place this as the **first** declaration in the public section. Because `Name`'s internal structure is specified in the private section, the compiler will hide its internals from users. This declaration must appear before the subprogram declarations, since Ada requires an identifier to be declared before it is used.

5. **Package body:** Cut the definitions of the `Name` operations and their documentation from `name_tester.adb` and paste them into the `DefinitionList` of the package body in `name_package.adb`. You will need to add the following before the package body:

   ```ada
   with Ada.Text_IO; use Ada.Text_IO;
   ```

### Using the Package

To use a package, a program must supply the `with` and `use` directives:

:::{code-block} bnf
<Program>        ::=    <WithDirectives>
                        <UseDirectives>
                            'procedure' <identifier> 'is'
                                ...
                            'end' <identifier> ';' ;
<WithDirectives> ::=   <WithDirective> ';' <WithDirectives> | Ø ;
<WithDirective>  ::=   'with' <IdentifierList> ;
<UseDirectives>  ::=   <UseDirective> ';' <UseDirectives> | Ø ;
<UseDirective>   ::=   'use' <IdentifierList> ;
:::

Add `Name_Package` to the `with` and `use` directives at the beginning of `name_tester.adb`.

- The `with` directive tells the compiler the program will access a given package.
- The `use` directive allows public identifiers from the package to be used without qualification.

### Name Ambiguity

The declaration `aName : Name;` will generate a compilation error because the identifier `Name` is publicly declared in both `Name_Package` and `Ada.Text_IO`. To resolve this, qualify the type:

```ada
aName : Name_Package.Name;
```

The calls to Name operations do not need qualification thanks to the `use` directive.

### Separate Compilation

GNAT Ada source files can be separately compiled using the `-c` switch:

```console
gcc -c name_tester.adb
gcc -c name_package.adb
```

Each produces an object file (`.o`) and a symbol file (`.ali`). Once both are compiled, bind and link:

```console
gnatbind name_tester.ali
gnatlink name_tester.ali
```

### Testing

Compile and link your files, then run `name_tester`:

```console
gnatmake name_tester
./name_tester
```

Or simply run `make ada` from the repository root.

Ensure all tests pass and all functions are well documented.

---

## Clojure

### Modularity in Clojure

Clojure provides a record-type as an aggregate data structure. Like C, Clojure does not provide a distinct module construct, but relies upon the file system for modularity.

**Code Reusability:** To make Clojure code reusable, we can simply place it in a separate file. Any other file that wants to use that code can do so.

**Information Hiding:** Clojure provides no mechanism for keeping information private — everything declared globally in a separate file is public, and any field of a record can be accessed via the field-accessor mechanism:

```clojure
(:fieldName objectName)
```

However, because Clojure objects are **immutable**, once an object has been constructed, its state cannot be changed. This prevents programmers from changing a valid object to an invalid state.

Clojure's module mechanism thus only partially achieves the information-hiding goal of modularity.

### Creating a Clojure Module

To make our `Name` type and its operations reusable, cut them from `nameTester.clj` and paste them into `Name.clj` (in the `src` directory). Leave the `-main` function in `nameTester.clj` to serve as a driver program.

That's it — `Name.clj` is now the Clojure equivalent of a module!

### Using a Clojure Module

There are two ways to load a module:

#### Using `load`

Given the name of a module (without extension), `load()` looks in the `src` directory for that module:

:::{code-block} bnf
<Expression>   ::=   '(' 'load' <ModuleName> ')' ;
<ModuleName>   ::=   '"' <identifier> '"' ;
:::

```clojure
(load "Name")
```

Add a call to `load` in `nameTester.clj` to load the `Name` module.

#### Using `load-file`

The `load-file` function requires the explicit path (relative to the project folder) and the full filename including extension:

:::{code-block} bnf
<Expression>   ::=   '(' 'load-file' <PathToFile> ')' ;
<PathToFile>   ::=   '"' <Characters> '"' ;
:::

```clojure
(load-file "src/Name.clj")
```

Comment out the `load` call and add a `load-file` call beneath it.

### Summary

- `load` is simpler (only requires the module name), but the module must be in the `src` folder.
- `load-file` is more powerful (can load a file from anywhere in the file system), but slightly more complicated to use.

### Testing

Build and run with both `load` and `load-file`:

```console
clojure -M -m nameTester
```

Or simply run `make clojure` from the repository root.

Ensure all tests pass and all functions are well documented.

---

## Ruby

As a modern object-oriented language, Ruby supports the goals of modularity in multiple ways, including its **class** mechanism and its **module** mechanism.

### Classes

In Ruby, instance variables and class variables are **private** by default. You access them through accessor methods. All instance variables are (by default) private and all methods are (by default) public.

Separate compilation is not generally an option for Ruby, because Ruby is an interpreted language. However, we can still make classes reusable.

#### Step 1 — The `require` Approach

Cut the `Name` class from `nameTester.rb` and paste it into a new file called `Name.rb`. Then, in `nameTester.rb`, replace the class with:

```ruby
require './Name'
```

The `require` method loads and executes all statements in the file. It also tracks which files have been previously imported and will not import the same file twice.

Save your changes and run `ruby nameTester.rb` to verify everything still works.

#### Step 2 — The `load` Approach

The `load` method works similarly to `require`, with differences:

- `load` requires the **full** filename (including the `.rb` extension).
- `load` does **not** track whether the file has been previously loaded — it always executes the statements.

In `nameTester.rb`, comment out the `require` line and replace it with:

```ruby
load './Name.rb'
```

Save and run again to verify it still works.

#### Step 3 — Modules

In Ruby, modules are a way of grouping together methods, classes, and constants (like the C++ namespace and the Java package). One major benefit is that a module defines a **namespace** and can help prevent name clashes.

```ruby
module Trig
  PI = 3.141592654
  def Trig.sin(x)
    # ..
  end
  def Trig.cos(x)
    # ..
  end
end
```

```ruby
module Action
  VERY_BAD = 0
  BAD      = 1
  def Action.sin(badness)
    # ...
  end
end
```

A third program can use these modules:

```ruby
require "trig"
require "action"

y = Trig.sin(Trig::PI/4)
wrongdoing = Action.sin(Action::VERY_BAD)
```

You call a module method by preceding its name with the module's name and a period. You reference a constant using the module name and two colons.

Refactor your code: in `Name.rb`, wrap the `Name` class inside a `module Names ... end` block. Then update `nameTester.rb` to reference `Names::Name` instead of `Name`.

### Testing

Test your changes:

```console
ruby nameTester.rb
```

Or simply run `make ruby` from the repository root.

Ensure all tests pass and all functions are well documented.

---

## Submission

Commit and push your work to your repository.

## Rubric

| Criterion | Points |
|-----------|--------|
| Java: `Name` class extracted to `Name.java`; `NameTester.java` compiles and passes all assertions | 3 |
| Ada: `Name_Package` specification and body correct; `name_tester` compiles, links, and passes all assertions | 3 |
| Clojure: `Name.clj` module created; `nameTester.clj` loads it (via both `load` and `load-file`) and passes all assertions | 3 |
| Ruby: `Name` class extracted to `Name.rb` with `Names` module wrapper; `nameTester.rb` uses `require`/`load` and passes all tests | 3 |
| Code quality: clear documentation, proper formatting | 1 |
| **Total** | **13** |
