---
title: "->slos;"
subtitle: // Concurrent and Parallel Programming, Part I
---

<!-- April 13 -->

## Conceptual Foundations

1. Explain the motivation for concurrent programming using the divide-and-conquer analogy, and describe the potential speedup of splitting independent tasks across multiple workers.
2. Define **speedup** formally as T₁/Tₙ and apply the formula to calculate the speedup achieved by a given concurrent solution.
3. Distinguish between the two primary goals of concurrent programming: **speedup** (doing work faster) and **responsiveness** (keeping applications and servers interactive under load).
4. Identify the two main language-level mechanisms for concurrency covered in the course: Ada's `task` construct and Java's `Thread` class, and contrast how each expresses concurrent execution.

## Terminology

5. Distinguish between a **uniprocessor** and a **multiprocessor**, and explain the difference between **pseudo-parallel execution** (logical concurrency via time-sharing) and **true parallel execution** (physical concurrency on multiple cores).
6. Distinguish between **tightly-coupled** multiprocessors (shared memory, close proximity) and **loosely-coupled** multiprocessors (private memory, message passing).
7. Define **process**, **thread of execution**, **deterministic**, and **non-deterministic** behavior, and explain why concurrent execution across multiple processes is inherently non-deterministic.

## Parallelism and Interleaving

8. Explain how **parallelizing compilers** use dependency graphs to identify statements that can safely be executed in parallel, and give an example of a statement-level dependency.
9. Explain **event interleaving**: given N concurrent threads with known statement counts, describe the number of possible execution orderings using the concept of permutations (n!).
10. Describe the **synchrony continuum** from embarrassingly parallel (no synchronization needed) to lock-step synchronous (synchronization after every step), and explain how position on the continuum affects the benefit of parallelism.

## Communication and Synchronization

11. Compare the two inter-process communication strategies: **shared memory** (tightly-coupled) and **message passing** (loosely-coupled), including the P/V or send/receive primitives each uses.
12. Explain why simultaneous access to shared memory can produce incorrect results, and why accesses must be **synchronized** — distinguishing write-write conflicts from read-write conflicts.
13. Define **mutual exclusion** and explain why mutually-exclusive objects require "one-thing-at-a-time" access, using the travel agent seat-booking scenario as an example.

## Classic Synchronization Problems

14. Describe the **producer-consumer problem** (including the bounded-buffer variant) and explain the synchronization hazards that arise when multiple producers and consumers share a buffer without coordination.
15. Describe the **dining philosophers problem**, explain why the naive greedy solution leads to **deadlock**, and explain why the two-chopstick-check solution leads to **livelock**.

## Synchronization Primitives

16. Define a **semaphore** and its three operations (initialize, P/acquire, V/release), and demonstrate how semaphores can be used for both mutual exclusion and lock-step synchronization.
17. Distinguish between **locks** (for guarding critical sections) and **condition variables** (for suspending threads until a condition holds), and explain why newer languages provide these as separate constructs rather than relying solely on semaphores.
18. Describe the three operations of a **condition variable** — `wait()`, `signal()`/`notify()`, and `broadcast()`/`notifyAll()` — and explain what each does to the waiting thread queue.

## Monitors

19. Define a **monitor** and explain how it provides a higher-level abstraction than raw semaphores by automatically enforcing mutually-exclusive access to a class's methods.
20. Trace the execution of a Java `BoundedBuffer` monitor, explaining how `synchronized`, `wait()`, and `notifyAll()` work together to prevent producers from overwriting unconsumed items and consumers from reading empty slots.
21. Identify the key concurrency features added in `java.util.concurrent` (Java 1.5+), including thread pools, thread-safe data structures, atomic operations, and higher-level constructs like `Future` and `ForkJoinPool`.