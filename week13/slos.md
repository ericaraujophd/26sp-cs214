---
title: "->slos;"
subtitle: // Concurrent and Parallel Programming, Part II
---

<!-- April 20 -->

## Distributed Synchronization and Message Passing

1. Explain why shared-memory synchronization primitives (semaphores, locks, monitors) are inapplicable on a distributed multiprocessor, and identify message passing as the correct alternative.
2. Distinguish between **synchronous message passing** (no buffer storage: sender and receiver must both be ready) and **asynchronous message passing** (buffered: sender can proceed before receiver is ready), and trace the timing of each scenario.
3. Describe the **Actor model** of message passing and identify at least two industry languages (Erlang, Scala) that support it, including real-world use cases.

## Ada Tasks and Rendezvous

4. List the three characteristics of an Ada **task**: its own thread of control, its own execution state, and mutually exclusive **entry procedures**.
5. Explain Ada's **rendezvous** mechanism: how a calling task and an accepting task synchronize at an entry procedure call, including which task waits when only one is ready.
6. Trace the sequence of events in a rendezvous — argument passing, suspension of the caller, execution of the entry body, return of out-parameters, and resumption of both tasks.
7. Explain why Ada's rendezvous does not require shared memory and can therefore work on uniprocessors, tightly-coupled multiprocessors, and distributed systems alike.

## Concurrency Patterns in Ada

8. Implement a divide-and-conquer parallel pattern in Ada using `task type` with entry procedures, and calculate the expected speedup relative to a sequential solution.
9. Describe the structure of Ada's **capacity-1 bounded buffer** task type and explain how strict alternation between `accept put` and `accept get` enforces mutual exclusion without explicit locks.
10. Explain Ada's **`select`-`when`** construct and demonstrate how it allows a capacity-N buffer task to conditionally accept `put()` or `get()` calls based on buffer state.

## MPI

11. Describe **MPI** (Message Passing Interface) as an industry-standard distributed-memory parallel computing library, identify its origin (1994 consortium), and name at least two languages it supports natively.

## Comparing Concurrency Constructs

12. Compare **monitors**, **tasks/threads**, and **coroutines** along the dimensions of "has its own thread" and "has its own execution state," and give an example language for each.
13. Define a **coroutine** and explain how two coroutines share a single thread while maintaining separate execution states through explicit `resume` calls.
14. Classify the key concurrency entities across languages: processes (Smalltalk, MPI), tasks (Ada), and threads (C/C++, Java, Go, Python, Ruby, Scala), and explain what they have in common.
15. Summarize the progression of shared-memory synchronization constructs — semaphore → lock/condition variable → monitor — in terms of abstraction level and ease of correct use.