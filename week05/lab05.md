---
title: ->lab05;
subtitle: Lambda Functions
---

In this exercise, we will look at some unusual ways Lisp-family languages handle functions, and the power this provides.

To begin, accept the project invitation from GitHub Classroom: [here](). Then, open VS Code through Coder and clone your repository.

## 0. Getting Started

To start this exercise, open two terminal windows.

In one of the windows, open a text editor that supports parentheses matching so that you can conveniently enter and edit multiline Clojure functions. We'll call this window your text editor window.

For reasons that will become clear in a moment, we're going to call the other window your REPL window.
In your REPL window, enter:

  script 0.script
As usual, this will start script running and record everything that follows in a file named 0.script.
In that same terminal window, enter:

  clojure
This will launch a Clojure Read-Evaluate-Print-Loop (REPL) environment. When you see the user=> prompt, enter this:
    user=>  (+ 1 2 3 4 5)
Clojure's REPL will read, evaluate, and print the result of this function, and then loop back to let you enter another.	

Then type Ctrl-d twice, once to quit clojure, and again to quit script. This will return you back to the command-line in your terminal window. There, enter:

  cat 0.script
and you should see a recording of what you just did. For each of the sections below, you should repeat this procedure using a different script-file name (e.g., 1.script, 2.script, etc.) so that at the end, this collection of script files will form a record of your activities during this exercise.

