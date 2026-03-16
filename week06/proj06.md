---
title: ->proj06;
subtitle: // Subprograms
---

---

## Overview

Accept the assignment by clicking the "Accept Assignment". Then, read the instructions below and implement the required subprograms in each of our four languages.

---

This week's project is to write a subprogram to compute the roots of a quadratic equation in each of our four languages. Given an equation of the form:

$$y = ax^2 + bx + c$$

one root of the equation is given by

$$root1 = (-b + sqrt(b^2 - 4ac))/2a$$

while the other root is given by

$$root2 = (-b - sqrt(b^2 - 4ac))/2a$$

Of course, the coefficient a cannot be zero, and the expression $b^2 - 4ac$ must be nonnegative.

A subprogram to compute these roots might be written in C++ as follows:

:::{code-block} cpp
#include <cmath>
using namespace std;

bool quadraticRoots(double a, double b, double c,
                    double & root1, double & root2){
    if (a != 0) {
        double arg = pow(b, 2.0) - 4 * a * c;
        if (arg >= 0) {
        root1 = (-b + sqrt(arg))/(2*a);
        root2 = (-b - sqrt(arg))/(2*a);
        return true;
        } else {
        cerr << "\n*** quadraticRoots(): b^2 - 4ac is negative!" << endl;
        root1 = root2 = 0.0;
        return false;
        }
    } else {
        cerr << "\n*** QuadraticRoots(): a is zero!" << endl;
        root1 = root2 = 0.0;
        return false;
    }
}
:::

Your project is to implement this subprogram or its equivalent (and a main "driver" program to call it) in Java, Ada, Clojure, and Ruby. Your driver program should only display the roots if this method's return-value indicates the roots are valid.

## Pesky Details:

You must use the appropriate form of parameter passing for each language (e.g. in the C++ program above, how are roots 1 and 2 getting out of the function??).
Printing of the roots must occur in the "main" driver program, not in the subprogram.

## Submission

Commit and push your code to your GitHub repository for this course. You should have four subdirectories in your repository, one for each language, and each of those should contain a driver program and a subprogram.
