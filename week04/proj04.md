---
title: ->proj04;
subtitle: Aggregate Operations
---

First, accept the project invitation from GitHub Classroom: [here](). Then, open VS Code through Coder and clone your repository. You will need to copy the files from your Lab 04 to this repository before starting to code.

This week's project is to write some additional `Name` operations in each of the four languages. More precisely, for each of our four languages, write:

1. Three mutator functions that can be used to change the first, middle or last names in a `Name`.
2. An operation named `lfmi()` that for a given `Name`, returns a string giving its Last-First-MiddleInitial form.
3. An input function that allows a `Name` object to be filled with values from the keyboard.

Each of these functions is quite short and simple.

To illustrate, C++ mutator functions for class `Name` might be defined as follows:

:::{code-block} cpp
void Name::setFirst(const string & aFirst) {
    myFirst = aFirst;
}
void Name::setMiddle(const string & aMiddle) {
    myMiddle = aMiddle;
}
void Name::setLast(const string & aLast) {
    myLast = aLast;
}
:::

Likewise, a C++ `lfmi()` function might be written as follows:

:::{code-block} cpp
string Name::lfmi() const {
    return myLast + ", " + myFirst + " " + myMiddle[0] + ".";
}
:::

Finally, a C++ function to fill a `Name` with keyboard input might be written as follows:

:::{code-block} cpp
void Name::read(istream & in = cin) {
    in >> myFirst >> myMiddle >> myLast;
}
:::

Your project is to implement the equivalent subprograms (and extend the program with additional tests that test them) in Java, Ada, Clojure, and Ruby.

In each of your "tester" programs, you are to extend the tester with additional tests that check the correctness of the corresponding operation.

Note that in Clojure, your input function must return the input name as a record to its caller, which can then bind that name to an identifier. Be careful to name your `readName()` function as indicated. Likewise, the mutators should receive and return a valid `Name`. The `lfmi()` should receive a `Name` but return a string.

## Submission Instructions

When you are certain your program is correct, commit and push your changes to GitHub.
