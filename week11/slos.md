---
title: "->slos;"
subtitle: // OOP and Inheritance
---

## Conceptual Foundations

1. Define the **is-a relationship** and explain how it underpins hierarchical class design in object-oriented programming.
2. Explain the purpose of **object-oriented design (OOD)**: consolidating shared attributes into superclasses to eliminate redundant code.
3. Distinguish between **bottom-up design** (starting from leaf classes and extracting superclasses) and top-down design, and explain why OOD typically proceeds bottom-up.
4. Identify the standard set of methods each class in a hierarchy should provide: constructors, accessors, mutators, I/O methods, and domain-specific methods (e.g., `pay()`).

## Inheritance

5. Explain what it means for a subclass to **inherit** attributes (data and operations) from its superclass, and identify what a subclass must add or override versus what it receives for free.
6. Trace attribute inheritance through a multi-level hierarchy (e.g., `FacultyMember` → `SalariedEmployee` → `Employee` → `Person`) and determine which attributes/methods are available at each level.
7. Explain why a subclass constructor must invoke its superclass constructor, and demonstrate the correct syntax for doing so in both C++ (initializer list) and Java (`super()`).

## Virtual Methods and Binding

8. Distinguish between **compile-time (static) binding** and **run-time (dynamic) binding**, and explain the performance and flexibility trade-offs of each.
9. Explain the role of C++'s `virtual` keyword in enabling run-time binding, and contrast this with Java's default behavior and use of `final` to force compile-time binding.
10. Define a **pure virtual function** (C++) / **abstract method** (Java) and explain why `Employee::pay()` is declared this way — including what it means for `Employee` to become an abstract class.

## Polymorphism and Dynamic Dispatch

11. Define **polymorphism** and explain how a single handle/reference of a superclass type can invoke different subclass method definitions depending on the receiver at run-time.
12. Describe the **dynamic dispatch** algorithm: how a message is resolved by first checking the receiver's class, then walking up the hierarchy, and what happens if no definition is found.
13. Explain the concept of a **handle** (pointer/reference to a superclass type) and demonstrate its use in processing heterogeneous collections of objects (e.g., the payroll loop).

## Language Comparisons

14. Compare how C++, Java, Ada, and Clojure each sit on the **OO continuum** with respect to default binding (static vs. dynamic) and the mechanisms used to enable each.
15. Explain Ada's `tagged` type and `'Class` (class-wide type) mechanism, and describe how they enable polymorphism and dynamic dispatch in a language where OO is an add-on.
16. Describe **extension methods** (as in C#) as an alternative to subclassing for adding functionality to existing classes, and articulate their key limitation (no access to private members).