---
title: "->slos;"
subtitle: // Encapsulation
---

## Conceptual Understanding

1. Define **encapsulation** and **data hiding**, and explain why both are necessary for proper abstract data typing.
2. Distinguish between a *type*, an *abstract data type (ADT)*, and an *encapsulation mechanism*.
3. Explain the two problems that arise when ADT implementation details are exposed (interface bypass and unauthorized operations), and describe why they increase maintenance costs.

## Encapsulation Mechanisms

4. Compare and contrast the three encapsulation mechanisms — **module/package**, **class**, and **functional closure** — including their respective trade-offs in flexibility, reusability, and performance.
5. Explain the historical progression from structured programming → modular programming → object-oriented programming and the motivation behind each transition.

## Modules and Packages

6. Describe the role of Ada's `private` keyword in separating public interface from private implementation in a package specification.
7. Differentiate between using a package as an ADT container (where the ADT is passed as a parameter) versus using it as a singleton "object."
8. Explain how Ada's `generic` packages solve the "one object" limitation of non-generic modules.

## Classes

9. Trace the lineage of the class construct from **Simula-67** through **Smalltalk**, **C++**, and **Java**, noting what each language added or changed.
10. Explain the difference between *instance methods* and *class methods* in Smalltalk/Java.
11. Compare C++ templates and Ada generic packages as mechanisms for building strongly typed containers, including why C++ templates are considered more cumbersome.
12. Explain Java's **auto-boxing/unboxing** and why it is needed when using generic containers with primitive types.

## Functional Encapsulation

13. Explain how closures and local function definitions achieve encapsulation in functional languages like Clojure.