---
title: ->proj08;
subtitle: // Aggregate Operations
---

## Overview

In Lab 08 you built a `Name` aggregate type in all four languages, along with accessor methods to read its fields. In this project you will extend that `Name` type with three new categories of operations:

1. **Mutators** — change the value of one field of a `Name`
2. **`lfmi()`** — return a `Name` as a formatted string in
   Last, First M. order
3. **`read()`** — fill a `Name` with values read from the keyboard

For reference, the equivalent C++ implementations are:

:::{code-block} cpp
void Name::setFirst(const string & aFirst)  { myFirst  = aFirst;  }
void Name::setMiddle(const string & aMiddle){ myMiddle = aMiddle; }
void Name::setLast(const string & aLast)    { myLast   = aLast;   }

string Name::lfmi() const {
    return myLast + ", " + myFirst + " " + myMiddle[0] + ".";
}

void Name::read(istream & in = cin) {
    in >> myFirst >> myMiddle >> myLast;
}
:::

So for a `Name` initialized as `("John", "Paul", "Jones")`, `lfmi()` should return the string `"Jones, John P."`.

Begin by accepting the project invitation from [GitHub Classroom: here](https://classroom.github.com/a/eTNkOBWn). Then open VS Code through Coder and clone your repository.

**Your first step** is to copy your completed Lab 08 files into the appropriate folders in your repository:

| File | Destination |
|------|-------------|
| `NameTester.java` and `Name.java` | `java/` |
| `name_tester.adb` | `ada/` |
| `nameTester.clj` | `clojure/src/` |
| `NameTester.rb` | `ruby/` |

Verify that each file still builds and all Lab 08 assertions still pass before adding any new code.

The order in which you complete the four language sections does not matter.

---

(testing-requirements)=
## Testing Requirements

Each tester must be extended with `assert` statements that verify every new operation. The required test cases are:

### Mutators

For each of `setFirst()`, `setMiddle()`, and `setLast()`, assert that:

- After calling the mutator, the corresponding getter returns the new value
- The other two fields are unchanged

For example, in pseudocode:

:::{code-block} text
name = Name("John", "Paul", "Jones")
setFirst(name, "Jane")
assert getFirst(name)  == "Jane"    -- updated
assert getMiddle(name) == "Paul"    -- unchanged
assert getLast(name)   == "Jones"   -- unchanged
:::

### `lfmi()`

Assert that:

- `lfmi()` on `Name("John", "Paul", "Jones")` returns `"Jones, John P."`
- `lfmi()` on `Name("Jane", "Penelope", "Jones")` returns `"Jones, Jane P."`

### `read()`

Call `read()` and enter `John Paul Jones` at the keyboard when prompted.
Then assert that `getFirst()` returns `"John"`, `getMiddle()` returns
`"Paul"`, and `getLast()` returns `"Jones"`.

---

## Java

Open `NameTester.java` and `Name.java` from your repository.

### Mutators

Add three mutator methods to the `Name` class. Each receives a `String` and assigns it to the corresponding private field. Using `setFirst()` as a model:

:::{code-block} java
public void setFirst(String aFirst) {
    myFirst = aFirst;
}
:::

Add `setMiddle()` and `setLast()` following the same pattern.

### `lfmi()`

Add a method `lfmi()` that returns a `String` in Last, First M. format. Recall that individual characters of a Java `String` can be accessed using `charAt(index)`, where index 0 is the first character.

### `read()`

Add a method `read()` that reads three whitespace-separated strings from standard input and assigns them to `myFirst`, `myMiddle`, and `myLast` respectively. Use a `Scanner` object constructed from `System.in`.

### Tests

Extend the `main()` method of `NameTester.java` with assertions covering all the cases listed in the [Testing Requirements](#testing-requirements) section above. Run your program with:

:::{code-block} bash
java -ea NameTester
:::

---

## Ada

Open `name_tester.adb` from your repository.

### Mutators

Add three procedures `setFirst()`, `setMiddle()`, and `setLast()` to the declaration section of `name_tester.adb`. Each receives a `Name` with mode `in out` and a `String` with mode `in`, and assigns the string to the appropriate field using the dot operator.

Note that the parameter mode for the `Name` argument must be `in out` rather than `out` — we want to update one field while leaving the others intact.

### `lfmi()`

Add a function `lfmi()` that receives a `Name` with mode `in` and returns a `String`. Use the `&` concatenation operator to assemble the result.

To extract the middle initial, recall that Ada strings are arrays of characters indexed from 1, so `TheName.MyMiddle(1)` gives the first character. You will need to convert this `Character` to a one-character `String` using the aggregate expression `(1 => TheName.MyMiddle(1))`.

### `read()`

Add a procedure `read()` that reads three strings from standard input into the fields of a `Name` parameter (mode `in out`). Use the `Get()` procedure from `Ada.Text_IO` to read each field. Recall that Ada string variables are fixed-width arrays, so each value read must exactly fill the field width (`NAME_SIZE` characters).

### Tests

Extend the `begin` section of `name_tester.adb` with `pragma Assert` statements covering all the cases in the [Testing Requirements](#testing-requirements) section. Run your program with:

:::{code-block} bash
make ada
:::

---

## Clojure

Open `nameTester.clj` from your repository.

Because Clojure records are **immutable**, none of these operations can modify an existing `Name` object in place. Instead:

- Mutators must receive a `Name` and return a **new** `Name` with the changed field
- `readName()` must construct and return a fresh `Name` from input

### Mutators

Add three functions `setFirst()`, `setMiddle()`, and `setLast()`. Each receives a `Name` and a new string value, and returns a new `Name` record with that field updated and all other fields unchanged. Recall the immutable mutator pattern from Lab 08:

:::{code-block} clojure
(defn setFirst [^Name aName newFirst]
    (->Name newFirst (:middleName aName) (:lastName aName))
)
:::

Add `setMiddle()` and `setLast()` following the same pattern.

### `lfmi()`

Add a function `lfmi()` that receives a `Name` and returns a string in Last, First M. format. Use the `str` function to concatenate the parts.

To extract the middle initial as a single-character string, use `subs`:

:::{code-block} yaml
<Expression>  ::=  '(' 'subs' <String> <StartIndex> <EndIndex> ')' ;
:::

So `(subs (:middleName aName) 0 1)` returns the first character of the middle name.

### `readName()`

Add a function `readName()` that reads one line from standard input, splits it on whitespace, and returns the three parts as a new `Name` record. Use `read-line` to read the line and `clojure.string/split` to split it:

:::{code-block} clojure
(defn readName []
    (let [parts (clojure.string/split (read-line) #"\s+")]
        (->Name (parts 0) (parts 1) (parts 2))
    )
)
:::

Note that unlike the other languages, `readName()` takes no parameters — it constructs and returns a fresh `Name` from input.

### Tests

Extend `-main()` with `assert` statements covering all the cases in the [Testing Requirements](#testing-requirements) section. Because mutators return new records rather than modifying in place, bind the result to a new identifier before asserting:

:::{code-block} clojure
(let [name1  (make-Name "John" "Paul" "Jones")
      name1b (setFirst name1 "Jane")]
    (assert (= (getFirst  name1b) "Jane")  "setFirst failed")
    (assert (= (getMiddle name1b) "Paul")  "setFirst changed middle")
    (assert (= (getLast   name1b) "Jones") "setFirst changed last")
    ...
)
:::

Run your program with:

:::{code-block} bash
make clojure
:::

---

## Ruby

Open `NameTester.rb` from your repository.

### Mutators

Define explicit setter methods in the `Name` class for consistency with the other languages:

:::{code-block} ruby
def setFirst(aFirst)    @first  = aFirst  end
def setMiddle(aMiddle)  @middle = aMiddle end
def setLast(aLast)      @last   = aLast   end
:::

### `lfmi()`

Add a method `lfmi()` that returns the name in Last, First M. format.

In Ruby, individual characters of a string can be accessed using the `[]` operator with an integer index, so `@middle[0]` gives the first character of the middle name.

### `read()`

Add a method `read()` that reads a line from standard input, splits it on whitespace, and assigns the three parts to `@first`, `@middle`, and `@last`. Use `gets.chomp` to read the line and `split` to separate it.

### Tests

Extend the `testName` method of `NameTester.rb` with `assert` statements covering all the cases in the [Testing Requirements](#testing-requirements) section. Run your program with:

:::{code-block} bash
ruby NameTester.rb
:::

---

## Submission

When all four language files are complete and all assertions pass, commit and push your work to your repository.

Before submitting, verify that each of the four language directories contains your completed Lab 08 tester **plus** working implementations of all three new operations and the required test assertions.

## Rubric

| Task | Points |
|------|--------|
| Java: `setFirst()`, `setMiddle()`, `setLast()`, `lfmi()`, `read()` implemented correctly | 15 |
| Java: assertions cover all required test cases | 10 |
| Ada: `setFirst()`, `setMiddle()`, `setLast()`, `lfmi()`, `read()` implemented correctly | 15 |
| Ada: assertions cover all required test cases | 10 |
| Clojure: `setFirst()`, `setMiddle()`, `setLast()`, `lfmi()`, `readName()` implemented correctly | 15 |
| Clojure: assertions cover all required test cases | 10 |
| Ruby: `setFirst()`, `setMiddle()`, `setLast()`, `lfmi()`, `read()` implemented correctly | 15 |
| Ruby: assertions cover all required test cases | 10 |
| **Total** | **100** |