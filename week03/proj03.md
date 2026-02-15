---
title: ->proj03;
subtitle: Using Repetition
---

Accept the project invitation from GitHub Classroom: [here](https://classroom.github.com/a/U-P22vBP). Then, open VS Code through Coder and clone your repository. You will need to create all the files for this project yourself, so be sure to create a Java file, an Ada file, a Clojure file, and a Ruby file.

This week's project is to use the equivalent of a C++ `for` construct in each of our four languages. More precisely, here is a C++ function and program that, given an integer `n`, returns `n!`.

:::{code-block} cpp
#include <iostream>
using namespace std;

double factorial(unsigned n) {
    double answer = 1.0;

    for (int i = 2; i <= n; i++) {
        answer *= i;
    }

    return answer;
}

int main() {
    cout << "\nTo compute n!, enter n: ";
    unsigned n;
    cin >> n;

    cout << n << "! = " << factorial(n) << endl;
}
:::

Your project is to implement the equivalent program in Java, Ada, Clojure, and Ruby. Each program should have a subprogram that computes `n!`, and an interactive "driver" that handles I/O and invokes that subprogram.

:::{admonition}
:class: warning
Don't forget the documentation!
:::

## Java

Write a Java method that computes `n!` using a `for` loop, as described in the lab exercise.

## Ada

Since this is an integer counting problem, you should compute `n!` using the counting version of the `loop` construct, as described in the lab exercise.

## Clojure

Compute `n!` by designing and implementing a recursive function, or a non-recursive function that uses Clojure's recursive `loop()` function.

## Ruby

Write a Ruby method that computes `n!` using a `for` loop, as described in the lab exercise.

Make sure to test your program on a variety of input values, including edge cases (e.g., `0!` and `1!`).

When you are certain it is correct, commit and push your changes to GitHub.