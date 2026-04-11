---
title: "->slos;"
subtitle: // Formal Languages and Computational Models
---

## Turing Machines

1. Identify the three components of a **Turing Machine** (I/O tape, tape head, finite-state control) and explain the role each plays during execution.
2. Trace a TM execution step-by-step: given a state, a symbol read, and a transition table, determine the symbol written, the direction of head movement, and the next state.
3. Explain the significance of the **start state** and the **accept state** in TM execution, and describe what the tape contains when a TM halts.
4. Trace the TM addition algorithm on a concrete input (e.g., 3 + 2), producing the full step-by-step execution table and verifying the correct output on the tape.

## Computability

5. State Turing's 1936 theorem: a TM can be built for any computable function, and explain what this implies about the scope of computation.
6. Define a **Universal Turing Machine (UTM)** and explain why a proof about a UTM applies to every computer that will ever be built.
7. Explain the significance of Gödel's 1931 incompleteness result in the context of computability: there exist well-defined functions that no machine can compute.
8. State the **Halting Problem** and explain why Turing proved it is unsolvable by a UTM — and therefore unsolvable by any computer.

## The Chomsky Hierarchy

9. List the four levels of the **Chomsky Hierarchy** (0–3), name the language class and its corresponding automaton/recognizer at each level, and arrange them from least to most expressive.
10. For each level, describe the grammatical rule form that defines it, distinguish terminals from non-terminals, and explain what computational power is required to recognize strings in that language.
11. Explain why **Level 3 (Regular Expressions)** can be recognized by a finite state machine with no memory, and describe the limitation this places on what can be expressed.
12. Explain why **Level 2 (Context Free Languages)** requires a pushdown automaton (a FSM with a stack), and identify that all programming languages and most spoken language fall within this level.
13. Explain why a **FSM cannot parse a BNF/CFL** and trace the basic BNF parsing algorithm as a PDA, including how the stack is used to match terminals and expand non-terminals.
14. Explain why **BNF** is the appropriate tool for specifying programming language syntax (a CFL), and why it would be overkill for regular expressions.

## The Random Access Machine (RAM)

15. Describe the four components of the **RAM** model (memory array, program, input file, output file) and explain why Shepherdson and Sturgis proved it is computationally equivalent to a UTM.
16. Identify and interpret each instruction in the basic RAM instruction set (store, copy, add, subtract, indirection, read, write, goto, conditional branch, halt) and relate the set to RISC assembly language.
17. Trace a given RAM program to determine what computation it performs, identifying patterns such as absolute value computation or character transformation.
18. Explain the advantages of the extended RAM (symbolic names, additional operators) as a "portable assembly language" for studying how compilers translate high-level language constructs, and explain why even the extended RAM produces spaghetti code.

## Closures

19. Define a **closure** in the functional programming sense: a function that retains access to the variables in its enclosing scope at the time of its declaration, and demonstrate this with a Clojure example.
20. Explain how closures invert the normal scope/garbage-collection mechanism to keep captured values alive beyond their original scope.