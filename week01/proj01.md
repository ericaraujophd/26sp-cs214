---
title: ->proj01;
subtitle: Extending the Thermostat Grammar
---

## Overview

Accept the invitation to join the [GitHub Classroom assignment](https://classroom.github.com/a/HXlgDoZt) for this project.

This weeks project is to extend the Lexer, Parser and Listener to include the following keywords:

- get
- set
- current
- desired
- humidity
- temperature
- status

so that your parser can handle the following commands:

:::{code} plaintext
start
    -- Start command received
set current temperature 63
    -- Current Temperature set to 63
set desired temperature 68
    -- Desired Temperature set to 68
get current temperature
    -- Current Temperature is 63
set current humidity 40
    -- Current Humidity set to 40
set desired humidity 50
    -- Desired Humidity set to 50
get desired humidity
    -- Desired Humidity is 50
status
    -- heating: yes
    -- humidifying: yes
set desired temperature 53
    -- Desired Temperature set to 53
status
    -- heating: no
    -- humidifying: yes
set desired humidity 30
    -- Desired Humidity set to 30
status
    -- heating: no
    -- humidifying: no
stop
    -- Stop command received
set current temperature 63
    -- Error: system is not running
stop
    -- Error: System already stopped
:::

Lines starting with "--" are responses (outputs) your thermostat system should give, not input in to the system.

Below is a copy of the commands from above without the responses, for use in program.thermostat

:::{code} plaintext
start
set current temperature 63
set desired temperature 68
get current temperature
set current humidity 40
set desired humidity 50
get desired humidity
status
set desired temperature 53
status
set desired humidity 30
status
stop
set current temperature 63
stop
:::

In total your thermostat should track 5 different variables:

- `is_started`
- `current_temperature`
- `desired_temperature`
- `current_humidity`
- `desired_humidity`

In addition, based on relative values of temperature and humidity variables, the status command will report if the system should currently be heating and humidifying. (if $current_temperature < desired_temperature$, report that the system is heating, otherwise report that it is not). Similarly for humidity.

Make sure to copy the original files from last weeks lab into your project folder before starting this project.

---

A few helpful tips:

:::{admonition} Defining Number Tokens
:class: tip
A basic number production can be defined in your lexer definition using:

```yaml
NUM: [0-9]+;
```
:::

:::{admonition} Accessing Parse Tree Node Text
:class: tip
In `MyListener`, the variable that is passed in to the overridden methods represents the current parse tree node. (called `ctx` by default) As such, you can get the text of the current parse tree node, as a String using the following syntax:

```java
ctx.getText();
```
:::

:::{admonition} Converting String to Integer
:class: tip
You can convert a String to an integer using the parseInt function:

```java
int value = Integer.parseInt(stringValue);
```
:::

---

## Rubric

| Category               | Points | Description                                                                                     |
|------------------------|--------|-------------------------------------------------------------------------------------------------|
| Lexer Definitions      | 20     | All required tokens are defined in the lexer.                                                  |
| Parser Definitions     | 20     | All required parser rules are defined in the parser.                                                |
| Listener Implementation | 40     | The listener correctly implements the required functionality for each command.                     |
| Code Quality           | 10     | Code is well-organized, readable, and follows standard Java conventions.                           |
| Testing                | 10     | The program has been tested with various inputs and works as expected. |

Ways to lose points:

- Missing or incorrect lexer definitions.
- Missing or incorrect parser rules.
- Incorrect or incomplete listener implementation.
- Poor code organization or readability.
- Lack of testing or failure to handle edge cases.
- Failure to follow Java coding conventions.
