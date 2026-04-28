---
title: ->proj10;
subtitle: // Class Hierarchies
---

## Overview

This week's project is to extend your Java, Ada, and Ruby class hierarchies with some new classes:

- A `Penguin` class
- An `Ostrich` class
- A `Kiwi` class

which are described further below. Note that we will not be using Clojure in this project, because as a functional language, it really isn't intended to support object-oriented programming and class hierarchies.

## Class Hierarchy Exercise

As in the lab exercise, each kind of bird should respond appropriately to `name()` and `call()` messages. However, where owls, geese, and ducks are flying birds, penguins, ostriches, and kiwis are non-flying birds, so you are to modify things such that (for example) C++ code like this:

```cpp
Bird * birdPtr0 = new Duck("Donald");
Bird * birdPtr1 = new Penguin("Peter");
Bird * birdPtr2 = new Goose("Mother");
Bird * birdPtr3 = new Ostrich("Orville");
...
birdPtr0->print();
birdPtr1->print();
birdPtr2->print();
birdPtr3->print();
...
```

will produce output like this:

```
Donald Duck just flew past and said, "Quack"
Peter Penguin just walked past and said, "Huh-huh-huh-huuuuh"
Mother Goose just flew past and said, "Honk"
Orville Ostrich just walked past and said, "Snork"
...
```

(You can make up your own call for a kiwi.)

To make all of this happen with a minimal amount of code, you are to rewrite the `print()` method in class `Bird` to be something like the following:

```cpp
inline void Bird::print(ostream & out) const {
   out << name() 
        << ' ' 
       << className() 
       << " just "
       << movement()
       << " and said " 
       << call();
} 
```

In order for this to work, you should:

1. add an abstract `movement()` message to class `Bird`;
2. define two new subclasses of `Bird` named `FlyingBird` and `WalkingBird`;
3. have each of these subclasses define `movement()` appropriately;
4. revise the definitions of classes `Owl`, `Duck`, and `Goose` to make them subclasses of `FlyingBird`; and
5. define classes `Penguin`, `Ostrich`, and `Kiwi` as subclasses of `WalkingBird`.

You will need to research how to go about creating an abstract method in each language.

The resulting class hierarchy should look something like this:

<!-- ```
      Bird (abstract)
        |
        |-- FlyingBird (abstract)
        |     |-- Owl
        |     |-- Duck
        |     |-- Goose
        |
        |-- WalkingBird (abstract)
              |-- Penguin
              |-- Ostrich
              |-- Kiwi
``` -->

```{mermaid}
classDiagram
    class Bird {
        <<abstract>>
        -name : String
        +name() String
        +className() String
        +call() String
        +movement()* String
        +print(out)
    }

    class FlyingBird {
        +movement() String
    }

    class WalkingBird {
        +movement() String
    }

    class Duck {
        +call() String
    }

    class Goose {
        +call() String
    }

    class Owl {
        +call() String
    }

    class Penguin {
        +call() String
    }

    class Ostrich {
        +call() String
    }

    class Kiwi {
        +call() String
    }

    Bird <|-- FlyingBird
    Bird <|-- WalkingBird
    FlyingBird <|-- Duck
    FlyingBird <|-- Goose
    FlyingBird <|-- Owl
    WalkingBird <|-- Penguin
    WalkingBird <|-- Ostrich
    WalkingBird <|-- Kiwi
```

## Testing

In each language, modify the **birds** program so that it demonstrates that your class hierarchy works correctly. Make certain that it creates instances of all six 'leaf' classes in the hierarchy.

## Submission

Accept the project invitation from [GitHub Classroom](https://classroom.github.com/a/PSGFWfpw). Clone the repository and open it in your editor. Extend the bird hierarchy with the new classes in Java, Ada, and Ruby. Commit and push your work to your repository.

## Rubric

| Criterion | Points |
|-----------|--------|
| Java: extended bird hierarchy with abstract methods works correctly | 50 |
| Ada: extended bird hierarchy with abstract methods works correctly | 50 |
| Ruby: extended bird hierarchy with abstract methods works correctly | 50 |
| **Total** | **150** |
