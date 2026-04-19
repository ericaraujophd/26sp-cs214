---
title: ->proj09;
subtitle: // Abstract Data Types
---

## Overview

This week's exercise is to create a full-featured `Temperature` type. Such a type has the following attributes:

- **degrees**, a real (floating-point) value; and
- **scale**, a character value, one of `'F'`, `'C'`, `'K'`, `'f'`, `'c'`, or `'k'` (Fahrenheit, Celsius, or Kelvin). The scale is stored internally as uppercase (`'F'`, `'C'`, or `'K'`).

You are to build such a type in each of our four languages: Java, Ada, Clojure, and Ruby. Your type should take full advantage of the modularity features available in each language, as presented in this week's lab exercise.

:::{admonition} Warning
:class: warning
Warning: This project is **much** longer than others have been. Begin immediately!
:::

## Requirements

Your `Temperature` type must provide the following operations:

(initialization-proj09)=
### Initialization

Initialization to a given **degrees** and **scale**. This operation must validate the given values before storing them. A temperature is **valid** if and only if:

1. The **scale** is one of `'F'`, `'C'`, `'K'`, `'f'`, `'c'`, or `'k'`; and
2. The **degrees** value is at or above absolute zero for that scale:
   - Fahrenheit: degrees ≥ −459.67
   - Celsius: degrees ≥ −273.15
   - Kelvin: degrees ≥ 0.0

If either condition is not met, the initialization must report an error and terminate (or throw an exception, depending on the language's conventions).

### Accessor Operations

Accessor operations to retrieve the **degrees** and **scale** attributes. The scale accessor returns the normalized uppercase character.

### Converter Operations

- `toFahrenheit()` — given an arbitrary `Temperature`, returns the equivalent `Temperature` in Fahrenheit (`'F'`).
- `toCelsius()` — given an arbitrary `Temperature`, returns the equivalent `Temperature` in Celsius (`'C'`).
- `toKelvin()` — given an arbitrary `Temperature`, returns the equivalent `Temperature` in Kelvin (`'K'`).

Use these exact formulas:

| From \ To | Fahrenheit | Celsius | Kelvin |
|-----------|-----------|---------|--------|
| °F | — | (F − 32) × 5/9 | (F + 459.67) × 5/9 |
| °C | C × 9/5 + 32 | — | C + 273.15 |
| °K | K × 9/5 − 459.67 | K − 273.15 | — |

### I/O Operations

- **Input**: reads a `Temperature` entered as a **degrees** value followed by a **scale** character on the same line, separated by whitespace (e.g., `98.6 F`). If the values do not constitute a valid temperature, display a descriptive error message and terminate the program.
- **Output**: displays a `Temperature` as `<degrees> <scale>` (e.g., `98.6 F`).

### Adjustment Operations

- `raise(degrees)` — returns a new `Temperature` that is **degrees** higher than the receiver, in the same scale. The result must be validated; if it falls below absolute zero, report an error and terminate.
- `lower(degrees)` — returns a new `Temperature` that is **degrees** lower than the receiver, in the same scale. The result must be validated; if it falls below absolute zero, report an error and terminate.

### Relational Operations

Both relational operations must work correctly regardless of the operands' scales. Convert both operands to a common scale (Kelvin is recommended) before comparing.

- `equals(other)` — returns `true` if and only if this `Temperature` represents the same physical temperature as **other**.
- `lessThan(other)` — returns `true` if and only if this `Temperature` is physically less than **other**.

### Hint

To avoid redundant validation code, write a private utility method `isValid(degrees, scale)` that returns `true` if and only if the given values constitute a valid temperature (see the criteria in [Initialization](#initialization-proj09)). Call it from initialization, input, and the adjustment operations.

## Testing

For each language, show that your `Temperature` type and its operations work correctly by writing a program that prints a temperature table. Your program should satisfy the following specification:

### Input

Your program reads three values from standard input:

- **baseTemp** — a `Temperature` (e.g., `0 F`)
- **limitTemp** — a `Temperature` (e.g., `100 C`)
- **stepValue** — a real number (e.g., `2.5`)

### Output

A formatted table of equivalent Fahrenheit, Celsius, and Kelvin values, starting at **baseTemp** and incrementing by **stepValue** in the scale of **baseTemp**. The table continues while the current temperature is strictly less than **limitTemp** (use your `lessThan()` operation for this comparison, which handles cross-scale comparisons correctly).

**Table format:**

- A header row with column labels: `Fahrenheit`, `Celsius`, `Kelvin`
- A separator line of dashes
- Each data row: three values formatted to **2 decimal places**, right-aligned in columns of width **12**
- No row is printed for a temperature that equals or exceeds **limitTemp**

**Example output** for input `0 F` / `100 C` / `2.5`:

```
  Fahrenheit     Celsius      Kelvin
------------  ----------  ----------
        0.00      -17.78      255.37
        2.50      -16.39      256.76
        5.00      -15.00      258.15
        ...
      205.00       96.11      369.26
      207.50       97.50      370.65
      210.00       98.89      372.04
```

(85 rows total. Note: `212.5 F` would be the next step but exceeds `100 C`, so the table stops at `210.0 F`.)

**Example output** for input `0 K` / `212 F` / `20.0`:

```
  Fahrenheit     Celsius      Kelvin
------------  ----------  ----------
     -459.67     -273.15        0.00
     -423.67     -253.15       20.00
        ...
      152.33       66.85      340.00
      188.33       86.85      360.00
```

(19 rows total. Note: `380 K` would be the next step but exceeds `212 F`, so the table stops at `360 K`.)

## Submission

Accept the project invitation from [GitHub Classroom](https://classroom.github.com/a/wTa_Qobb). Clone the repository and open it in your editor. Implement the full `Temperature` ADT in Java, Ada, Clojure, and Ruby with temperature table generation programs. Commit and push your work to your repository.

Test your program using these input values:

```
0 F
100 C
2.5
```

and

```
0 K
212 F
20.0
```

## Rubric

| Criterion | Points |
|-----------|--------|
| Encapsulation: Java, Ada, Clojure, Ruby (10 pts each) | 40 |
| Data Hiding: Java, Ada, Clojure, Ruby (10 pts each) | 40 |
| ADT Structure: Java, Ada, Clojure, Ruby (10 pts each) | 40 |
| Temperature Table: Java, Ada, Clojure, Ruby (10 pts each) | 40 |
| Style and Documentation: Java, Ada, Clojure, Ruby (10 pts each) | 40 |
| **Total** | **200** |
