---
title: "->proj05;"
subtitle: // Functional Programming Practice on Clojure
---

This project is to write several functions in Clojure. You may find the functions `list()` and `cons()`, and/or the empty list-literal `'()` to be useful in defining these functions.

Each function should be defined within the same file, and show the results of example executions in the same file.

Accept the project invitation from [GitHub Classroom here](https://classroom.github.com/a/hfWGU__3). Then, open VS Code through Coder and clone your repository. You will write all of your solutions in the provided `lab05.clj` file. Each exercise asks you to create a `def` binding with a specific name -- the starter file has placeholders for each one. When you are finished, the autograder will load your file and test each binding.

## Reversing Functions

The `reverse` function is defined in Clojure, but write your own version using recursion called `my-reverse()`. This function should behave like Clojure's `reverse`, and only reverse top-level elements. For example:

:::{code-block} clojure
(my-reverse '(a b (c d) (e (f g))))
:::

should return:

:::{code-block} clojure
((e (f g)) (c d) b a)
:::

Next, write a function `super-reverse()` that reverses nested sublists as well as the top-level elements. For example:

:::{code-block} clojure
(super-reverse '(a b (c d) (e (f g))))
:::

should return:

:::{code-block} clojure
(((g f) e) (d c) b a)
:::

## Misc and Functions

Define a function `member?()` that behaves for a list like the built-in function `contains?()` behaves for a vector; it should only return **true** or **false**, and only work if the expression we're looking at is a list. Your solution must use recursion. For example,

:::{code-block} clojure
(member? '(1 2) '((1 2) 3 (4 (5 6))))
(member? 3 '((1 2) 3 (4 (5 6))))
(member? '(4 (5 6)) '((1 2) 3 (4 (5 6))))
:::

should all return:

:::{code-block} clojure
true
:::

and

:::{code-block} clojure
(member? 1 '((1 2) 3 (4 (5 6))))
(member? 2 '((1 2) 3 (4 (5 6))))
(member? 4 '((1 2) 3 (4 (5 6))))
:::

should all return:

:::{code-block} clojure
false
:::

Define a function `(subsequence list i n)` that returns the part of the input list starting at position `i` continuing for `n` elements. Your solution must use recursion. For example:

:::{code-block} clojure
(subsequence '(1 2 (3 4) (5 (6 7))) 1 2)
:::

should return:

:::{code-block} clojure
(2 (3 4))
:::

and

:::{code-block} clojure
(subsequence '(1 2 3 4 5 6 7) 2 4)
:::

should return:

:::{code-block} clojure
(3 4 5 6)
:::

If there is any issue with the parameters (e.g., the list is empty, `i` is an invalid position/index, or the list contains fewer than `n` items beyond position `i`), your function should return `nil`.
To test your functions, you should define a `-main()` function that "exercises" each function on lists of varying values and lengths (including empty lists). Make sure to include tests that demonstrate the correctness of your functions, and also include documentation for each function.

:::{admonition} Important!
:class: warning
The lack of good tests and documentation will result in a significant loss of points.
:::

Commit and push your file to GitHub when you are finished.

## Grading Rubric

Each function is worth 25 points, broken down as follows:

15 - Function works correctly and is implemented as specified
5 - Function and file documentation
5 - Tests that demonstrate functional correctness
Turn in. When your functions work and pass all your tests, make a script file proj05-results in which you:

Ways students can lose points:

- Function does not work correctly (e.g., fails some tests, or does not handle edge cases) or is not implemented as specified (e.g., uses loops instead of recursion, or does not return the correct type).
- Lack of documentation for the function and/or the file.
- Lack of tests, or tests that do not demonstrate the correctness of the function (e.g., only test on one input, or do not include edge cases).
- Failure to commit and push the file to GitHub.
