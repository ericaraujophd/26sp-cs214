---
title: ->lab10;
subtitle: // Inheritance and Polymorphism
---

## Overview

Last week, we examined modular programming — organizing an ADT and its operations into a separately compiled module for reuse and information hiding. This week, we examine two key object-oriented features found in many of our languages: **inheritance** and **polymorphism**.

**Inheritance** is a language mechanism by which a new type can be declared that *extends* the capabilities of an existing type, reusing its data and operations without redundancy.

**Polymorphism** is a language mechanism that allows the same function call to be associated with *different* definitions during the same program's execution, by delaying the binding of the call to run-time.

To illustrate these concepts, we will implement a simple bird hierarchy in Java, Ada, Clojure, and Ruby:

:::{mermaid}
classDiagram
    class Bird {
        +Bird(itsName)
        +name()
        +className()
        +call()
    }
    class Duck {
        +Duck(itsName)
        +className()
        +call()
    }
    class Goose {
        +Goose(itsName)
        +className()
        +call()
    }
    class Owl {
        +Owl(itsName)
        +className()
        +call()
    }
    Bird <|-- Duck
    Bird <|-- Goose
    Bird <|-- Owl
:::

The hierarchy contains a `Bird` base class with three derived classes: `Duck`, `Goose`, and `Owl`.

- Every bird has a **name** (e.g., Daffy, Mother Goose, Woodsey) — a value that varies per instance and must be stored.
- Every bird has a **call** (e.g., Quack!, Honk!, Whoo-hoo) — a value that is the same for all instances of a given class and needs no storage, only a method.
- Every bird can **display** its name, class, and call — logic shared by all birds, defined once in the parent class.

Since the `print()` method in `Bird` invokes `call()` and `className()`, and since these are overridden in each subclass, calling `print()` on a `Duck` object should correctly say "Quack!" — this is polymorphic behavior.

Begin by accepting the project invitation from [GitHub Classroom: here](https://classroom.github.com/a/ICizBPtL). Clone the repository and open it in VS Code.

A `Makefile` is provided in the repository root. You can build and run any single language with `make java`, `make ada`, `make clojure`, or `make ruby`. Use `make all` to run all four, and `make clean` to remove compiled artifacts.

The order in which you complete the four language sections does not matter.

---

## Java

Open `Birds.java` from the `java/` directory and take a moment to study it. Then study `Bird.java`, `Duck.java`, `Goose.java`, and `Owl.java`.

### A Bird Class

In `Bird.java`, you will find a skeleton for class `Bird`:

:::{code-block} java
public class Bird {
    private String myName;
}
:::

Given this much, `Birds.java` can declare:

:::{code-block} java
Bird bird0 = new Bird();
:::

and compile correctly (default `String` initialization to `""`).

#### Constructors

Add two constructors to `Bird.java`:

- A **default constructor** that sets `myName` to `""`.
- An **explicit constructor** that accepts a `String name` parameter and assigns it to `myName`.

:::{code-block} java
public Bird() {
    myName = "";
}

public Bird(String name) {
    myName = name;
}
:::

Uncomment the first `Bird` declaration in `Birds.java` and verify the program builds and runs correctly before proceeding.

#### The `getName()` Method

Add a `getName()` accessor method to `Bird`:

:::{code-block} java
public String getName() {
    return myName;
}
:::

#### The `call()` Method

Every bird should be able to produce a call. Add a default `call()` method:

:::{code-block} java
public String call() {
    return "Squaaaaaaawk!";
}
:::

#### The `print()` Method

Add a `print()` method that displays the bird's name, class name, and call:

:::{code-block} java
public void print() {
    System.out.println(getName() + " " + getClass().getName() + " says " + call());
}
:::

Note that `getClass().getName()` returns the runtime class of the object — this is Java's built-in reflection. Uncomment the first `print()` call in `Birds.java` and verify everything works before continuing.

---

### The Duck Class

Since `Duck` extends `Bird`, we simply declare:

:::{code-block} java
public class Duck extends Bird {
}
:::

`Duck` inherits all of `Bird`'s data and methods except its constructor.

#### Duck Constructors

Add the following constructors to `Duck.java`:

:::{code-block} java
public Duck() {
    super();
}

public Duck(String name) {
    super(name);
}
:::

Uncomment the Duck lines in `Birds.java`. Compile and run. What do you observe about the call?

#### The `call()` Method

Override `call()` in `Duck` to return `"Quack!"`:

:::{code-block} java
public String call() {
    return "Quack!";
}
:::

Recompile and run. Trace through what happens when `bird2.print()` is called — `print()` is defined in `Bird`, but `call()` resolves to `Duck.call()`. This is **polymorphic behavior**.

---

### The Goose Class

`Goose` is similar to `Duck`. Derive it from `Bird`, add constructors that call `super()`, and override `call()` to return `"Honk!"`. Uncomment the Goose lines in `Birds.java` and verify.

---

### The Owl Class

Using `Duck` and `Goose` as models, implement the `Owl` class. Its `call()` should return `"Whoo-hoo"`. Uncomment the Owl lines in `Birds.java` and verify.

---

### Testing

Run `make java` from the repository root and verify all assertions pass. Ensure all methods are documented.

---

## Ada

Open `birds.adb` from the `ada/` directory. Also open `bird_package.ads`, `bird_package.adb`, and the duck, goose, and owl package files.

### A Bird Type

Ada uses the **tagged** keyword to declare a type that supports inheritance. In `bird_package.ads`, declare the `Bird_Type` as a tagged private type:

:::{code-block} ada
package Bird_Package is

    type Bird_Type is tagged private;

    procedure Init(A_Bird : out Bird_Type; Name : in String);
    function  Name(A_Bird : in Bird_Type) return String;
    function  Call(A_Bird : in Bird_Type) return String;
    function  Type_Name(A_Bird : in Bird_Type) return String;
    procedure Put(A_Bird : in Bird_Type'Class);

private
    type Bird_Type is tagged record
        My_Name : String(1..6);
    end record;

end Bird_Package;
:::

:::{note}
The `'Class` attribute on the `Put()` parameter is what enables polymorphism in Ada. Without it, Ada would bind `Call()` and `Type_Name()` statically.
:::

In `bird_package.adb`, implement the operations:

:::{code-block} ada
procedure Init(A_Bird : out Bird_Type; Name : in String) is
begin
    A_Bird.My_Name := Name;
end Init;

function Name(A_Bird : in Bird_Type) return String is
begin
    return A_Bird.My_Name;
end Name;

function Call(A_Bird : in Bird_Type) return String is
begin
    return "Squawwwwwwk!";
end Call;

function Type_Name(A_Bird : in Bird_Type) return String is
begin
    return "Bird";
end Type_Name;

procedure Put(A_Bird : in Bird_Type'Class) is
begin
    Put(Name(A_Bird));
    Put(' ');
    Put(Type_Name(A_Bird));
    Put(" says ");
    Put(Call(A_Bird));
end Put;
:::

Compile with `gnatmake birds.adb` and verify before proceeding.

---

### The Duck_Type Type

In `duck_package.ads`, declare `Duck_Type` as derived from `Bird_Type`:

:::{code-block} ada
with Bird_Package; use Bird_Package;

package Duck_Package is

    type Duck_Type is new Bird_Type with private;

    function Call(A_Duck : in Duck_Type) return String;
    function Type_Name(A_Duck : in Duck_Type) return String;

private
    type Duck_Type is new Bird_Type with record
        null;
    end record;

end Duck_Package;
:::

In `duck_package.adb`, implement:

:::{code-block} ada
function Call(A_Duck : in Duck_Type) return String is
begin
    return "Quack!";
end Call;

function Type_Name(A_Duck : in Duck_Type) return String is
begin
    return "Duck";
end Type_Name;
:::

Uncomment the `Duck_Type` lines in `birds.adb` and verify.

---

### The Goose_Type

Implement `Goose_Type` similarly, with `Call()` returning `"Honk!"` and `Type_Name()` returning `"Goose"`. Verify.

---

### The Owl_Type

Using `Duck_Type` and `Goose_Type` as models, implement `Owl_Type`. Its `Call()` should return `"Whoo-hoo"` and `Type_Name()` should return `"Owl"`. Verify.

---

### Testing

Run `make ada` from the repository root and verify all tests pass.

### Polymorphism in Ada

When `Put(Bird3)` is called, Ada binds the call to `Put(Bird_Type'Class)`. Inside `Put()`, the calls to `Call()` and `Type_Name()` are dispatched based on the *runtime type* of `A_Bird`. This is Ada's approach to polymorphism: **classwide** types (`'Class`) trigger dynamic dispatch.

---

## Clojure

Open `src/birds.clj`, `src/Bird.clj`, `src/Duck.clj`, `src/Goose.clj`, and `src/Owl.clj`.

### Before We Get Started

Clojure is a functional language that supports some OOP-like features. It does not have a true class hierarchy in the OO sense, but it provides **multimethods** for polymorphic dispatch based on type.

### Representing Bird Objects

In `Bird.clj`, a `Bird` record type is declared:

:::{code-block} clojure
(defrecord Bird [name])
:::

#### The `make-Bird` Function

Add a constructor function to `Bird.clj`:

:::{code-block} clojure
(defn make-Bird
  ([]        (->Bird "Ann Onymous"))
  ([itsName] (->Bird itsName))
)
:::

Uncomment the `make-Bird` calls in `birds.clj` and verify.

#### Accessor and Polymorphic Functions

Add an accessor and declare the multimethods:

:::{code-block} clojure
(defn getName [^Bird this]
  (:name this)
)

(defmulti getClass class)
(defmulti getCall  class)
(defmulti toString class)
:::

Then define their default implementations:

:::{code-block} clojure
(defmethod getClass Bird [ _ ]
  "Bird"
)

(defmethod getCall :default [ _ ]
  "Squaaaaawk!"
)

(defmethod toString :default [aBird]
  (str (getName aBird) " " (getClass aBird) " says, \"" (getCall aBird) "\"")
)
:::

Uncomment the `println` calls for `bird0` and `bird1` in `birds.clj` and verify.

---

### Duck Objects

`Duck.clj` is fully provided. Review it:

:::{code-block} clojure
(load "Bird")

(defrecord Duck [^Bird name])

(defn make-Duck
  ([]                (->Duck "Ann Onymous"))
  ([^String itsName] (->Duck itsName))
)

(defmethod getClass Duck [ _ ]
  "Duck"
)

(defmethod getCall Duck [ _ ]
  "Quack!"
)
:::

Uncomment the Duck-related lines in `birds.clj` (including the `:require`). Verify.

---

### Goose Objects

In `Goose.clj`, add:

- `(load "Bird")`
- `(defrecord Goose [^Bird name])`
- `make-Goose` constructor
- `getClass` and `getCall` multimethods returning `"Goose"` and `"Honk!"`

Uncomment the Goose lines in `birds.clj` and verify.

---

### Owl Objects

Using `Duck` and `Goose` as models, implement `Owl.clj`. Its `getCall` should return `"Whoo-hoo"`. Uncomment Owl lines in `birds.clj` and verify.

---

### Testing

Run `make clojure` from the repository root and verify the program runs and produces the expected output.

---

## Ruby

Open the files in `ruby/`: `Bird.rb`, `Duck.rb`, `Goose.rb`, `Owl.rb`, and `birds.rb`.

### A Bird Superclass

In `Bird.rb`, build the `Bird` superclass:

:::{code-block} ruby
class Bird

  attr_reader :name

  def initialize(name)
    @name = name
  end

  def call
    'Squaaaaaaawk!'
  end

  def className
    self.class.to_s
  end

  def print
    puts name + " " + className + " says " + call
  end

end
:::

Ruby's `self.class.to_s` returns the runtime class name as a string — no hard-coding needed.

---

### The Duck Subclass

In `Duck.rb`, derive `Duck` from `Bird` and override `call`:

:::{code-block} ruby
require './Bird.rb'

class Duck < Bird

  def call
    'Quack!'
  end

end
:::

The `<` symbol declares inheritance in Ruby.

---

### The Goose Subclass

In `Goose.rb`, derive `Goose` from `Bird` and override `call` to return `'Honk!'`.

---

### The Owl Subclass

In `Owl.rb`, derive `Owl` from `Bird` and override `call` to return `'Whoo-hoo'`.

---

### Testing

In `birds.rb`, test the hierarchy:

:::{code-block} ruby
require './Bird.rb'
require './Duck.rb'
require './Goose.rb'
require './Owl.rb'

puts "\nWelcome to the Bird Park!\n\n"

bird0 = Bird.new "Hawkeye"
bird0.print

bird1 = Duck.new "Donald"
bird1.print

bird2 = Goose.new "Mother"
bird2.print

bird3 = Owl.new "Woodsey"
bird3.print

puts "\n"
:::

Run `make ruby` from the repository root and verify all birds display their correct calls.

---

## Submission

Commit and push your work to your repository.

## Rubric

| Criterion | Points |
|-----------|--------|
| Java: `Bird` class with constructors, `getName()`, `call()`, `print()` | 10 |
| Java: `Duck`, `Goose`, `Owl` derived correctly; `call()` overridden in each | 10 |
| Java: Polymorphism demonstrated (correct output for all birds) | 5 |
| Ada: `Bird_Type` declared as `tagged`; `Put()` uses `'Class` parameter | 10 |
| Ada: `Duck_Type`, `Goose_Type`, `Owl_Type` derived; `Call()` and `Type_Name()` overridden | 10 |
| Ada: Polymorphism demonstrated (correct dynamic dispatch) | 5 |
| Clojure: `Bird` record with `make-Bird`, multimethods declared and defaulted | 10 |
| Clojure: `Duck`, `Goose`, `Owl` multimethods override `getClass` and `getCall` | 10 |
| Clojure: Polymorphism demonstrated via `toString` dispatch | 5 |
| Ruby: `Bird` superclass with `call`, `className`, `print` | 10 |
| Ruby: `Duck`, `Goose`, `Owl` derived using `<`; `call` overridden in each | 10 |
| Ruby: Polymorphism demonstrated (correct output for all birds) | 5 |
| Code quality: clear documentation, proper formatting | 0 |
| **Total** | **100** |
