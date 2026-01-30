---
title: ->lab01;
subtitle: // Introduction to ANTLR and Basic Parsing
---

## Objectives

At the end of this lab, students will be able to...

- Set up the ANTLR tool and environment for parsing tasks.
- Write simple ANTLR grammar files to define the syntax of a programming language.
- Generate lexer and parser code using ANTLR from grammar files.
- Implement a basic parser in a programming language of choice (e.g., Java, Python) using the generated ANTLR code.
- Test and debug the parser to ensure it correctly recognizes valid input and handles syntax errors appropriately.
- Understand the structure and components of ANTLR grammar files, including rules for tokens and parser rules.
- Use ANTLR's built-in tools to visualize parse trees and understand the parsing process.
- Apply basic concepts of lexical analysis and parsing in the context of programming languages.

The main goal of this lab is to use EBNF expressions for language design via ANTLR.

---

## Introduction

We have seen in class that BNF grammars can be useful in parsing a programming language, which is a first step towards implementing a language. Given that any new language will first need to be parsed, and that BNF expressions are a useful notation for representing a programming language syntax, there are several tools that have been built to automatically construct a lexical analyzer for a given language, using special-purpose notations.

Today we will be using ANTLR to create the **Thermostat Script** language (used to tell thermostats what to do) by creating a program that "tokenizes" an input stream and then parses that to create a syntax tree. [ANTLR (ANother Tool for Language Recognition)](https://www.antlr.org/) is a powerful parser generator for reading, processing, executing, or translating structured text or binary files. It's widely used to build languages, tools, and frameworks. From a grammar, ANTLR generates a parser that can build and walk parse trees.

This means that the output from ANTLR will be code, which will tokenize syntax, and be a basis to be able to write a compiler, transpiler, interpreter, or anything else that needs to be able to comprehend context free languages.

## Setup

As usual, we will use VS Code and the [Coder](https://coder.cs.calvin.edu) environment for this lab. Make sure you have the [ANTLR extension](https://marketplace.visualstudio.com/items?itemName=mike-lischke.vscode-antlr4) installed in VS Code for syntax highlighting and other features.

Find a partner before accepting and creating your team on GitHub Classroom for this lab. You then may [accept the assignment and create your repository](https://classroom.github.com/a/sHOdFEuY).

Remember to clone your repository to your Coder workspace. That's the place where you will do your work.

We will build our **Thermostat Script** language using 5 files:

1. **Lexer:** Breaks down input text into tokens based on rules.
2. **Parser:** Assembles tokens into a tree structure based on grammar rules.
3. **Listener:** Provides hooks to add custom behavior during tree traversal.
4. **Host Program:** Uses the lexer, parser, and listener to run the grammar on input text.
5. **Test File:** A file containing a program in our **Thermostat Script** language

---

## 1. Create the Lexer

Our lexer definition file will contain definitions of terminal symbols in a language very similar to BNF. ANTLR v4 files all usually have the extension `g4`. Also, the file needs to have a header to identify to ANTLR that this is indeed a Lexer definition, and that header needs to match the filename. 

Create a file called `MyLexer.g4` and make its first line:

:::{code} yaml
:filename: MyLexer.g4
lexer grammar MyLexer;
:::

We are also going to define 2 commands: `start` and `stop`. As these are terminal definitions, their names will be in all caps, and for simplicity, will be the same as their keywords:

:::{code} yaml
:filename: MyLexer.g4
START: 'start';
STOP: 'stop';
:::

This tells ANTLR to tokenize the string literals "start" and "stop" as `START` and `STOP` respectively. We could technically move forward with just this, however depending on your text editor, you might get complaints about additional whitespace in your files. For this reason we will also include:

:::{code} yaml
:filename: MyLexer.g4
WS: [ \t\n\r\f]+ -> skip;
:::

This tells ANTLR to tokenize all whitespace characters the same, and to not worry about how they fit in to our language.

Your complete `MyLexer.g4` file should look like this:

:::{code} yaml
:filename: MyLexer.g4
lexer grammar MyLexer;
START: 'start';
STOP: 'stop';
WS: [ \t\n\r\f]+ -> skip;
:::

:::{admonition} Important
:class: warning
Remember that typing these commands will help you understand how to create lexers for more complex languages in the future. So don't just copy and paste!
:::

## 2. Create the Parser

Our parser definition file will detail how our tokens (from the Lexer) need to be arranged to form understandable syntax. Lets create a file called `MyParser.g4`, and just like before, start it with a description of what is in the file, and this time, we should include the fact that it works along with our lexer file:

:::{code} yaml
:filename: MyParser.g4
parser grammar MyParser;
options { tokenVocab=MyLexer; }
:::

Now we should set about defining our syntax. The first rule we define will be the start rule, the first rule that the Parser will base everything else out of, and will constitute the root of any parse tree that is created. For now, lets call our start rule `program`, and say that a program consists of a single command, which can in turn be either a `start` command or a `stop` command.

:::{code} yaml
:filename: MyParser.g4

program: 
    start | stop
    ;
start: 
    START 
    ;
stop: 
    STOP 
    ;
:::

With just these two files, we can test ANTLR to see if it will generate code for us, and start to get a feel for what that code contains.

Lets test our ANTLR installation by using it to generate the code for a parser. Lets start by creating a folder for ANTLR to output to, so we can keep our workspace orderly. Go on your terminal, making sure you are in your lab01 repository folder, and create a folder called `parser`:

```bash
mkdir parser
```

now that we have that, our ANTLR command will look something like this:

```bash
antlr4 MyParser.g4 MyLexer.g4 -package parser -o parser
```

:::{admonition} Don't Ignore This!
:class: warning
Later in this Lab you will have to compile the Java generated files. That will require you to update your terminal `CLASSPATH` variable to include a path to the ANTLR library. To add `antlr` to your class path, execute the following line in your terminal, or add it to `.bash_rc` in your home directory:

```bash
export CLASSPATH=".:/usr/share/java/antlr4.jar:/usr/share/java/antlr4-runtime.jar:$CLASSPATH"
```
:::

When ANTLR has finished we should have the following files in the parser directory:

1. **MyLexer.java:** contains the code that breaks up the stream of text in to tokens and associates them with terminal symbols.
2. **MyParser.java:** contains the code that takes the tokenized input along with terminal symbols, and figures out which non-terminals those tokens create, building up a parse tree from its roots up to the start rule.
3. **MyParserBaseListener.java** and **MyParserListener.java:** These files define methods that will be called as syntax is recognized by the Parser, allowing you, the language developer, to do things like store variable names, allocate memory, and whatever else you will do in order to react to the syntax. 
   
Spend some time peeking through these files. What relationships do you see between the input files (*.g4) and the output files (*.java)?

:::{admonition} Note
:class: tip
By default ANTLR will write code in Java, however, ANTLR can generate Parsers in many different languages including:

- C++
- C\#
- Dart
- Java
- JavaScript
- PHP
- Python3
- Swift
- TypeScript
- Go

So feel free to generate parsers in any of these languages and poke around. You can alter the output language for Antlr using the `-Dlanguage` command line option with the above languages. (i.e. `antlr4 -Dlanguage=Cpp` )
:::

One thing we can do within our Parser definition file is add code that we want executed when a particular part of our syntax is identified. Lets modify `MyParser.g4` so that it prints out something when it identifies a start or stop command:

:::{code} yaml
:filename: MyParser.g4
parser grammar MyParser;
options { tokenVocab=MyLexer; }

program: 
    start | stop
    ;
start:
    START {System.out.println("*** Start command received ***");}
    ;
stop:
    STOP {System.out.println("*** Stop command received ***");}
    ;
:::

Make sure to delete the previously generated files in the `parser` directory, and then regenerate the parser code using the same ANTLR command as before. Notice that now, when the Parser recognizes a `start` or `stop` command, it will print out a message to the terminal.

```bash
antlr4 MyParser.g4 MyLexer.g4 -package parser -o parser
```

Now that ANTLR has recreated the files in the parser directory, lets take a look at `parser/MyParser.java` specifically the functions `start` and `stop` that starts on or around lines 170 and 210. What do you see?

While this is a way to inject code in to the code generated by ANTLR, its not the most elegant solution. Instead consider the classes `MyParserBaseListener` and `MyParserListener`, which provide a convenient means to integrate with the parser through inheritance. In this way you don't modify the code that the parser generates directly, but rather inherit from it, and only override the methods your ready to implement. This way, you keep your code and the generated code completely separate, so that your code changes cannot be lost when/if changes are made to the `.g4` files requiring regeneration of the `.java` files.

## 3. Host Program

Given that ANTLR's output is a parsing library customized to your syntax, its not very useful on its own. We need to host it in a simple program that will take input either from files or directly from the user, and provide that text to the code that ANTLR generated. Lets start with a program that will read code from files.

Create a file named `ParserTest.java` in the root directory of your project. In order to make this work, we are going to need to import libraries from ANTLR, as well as our parser.

:::{code} java
:filename: ParserTest.java
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import parser.*;
:::

We are also going to need to declare a Class, called `ParserTest`, with a `public static void main` method, to use as an entry point in to our program.

:::{code} java
:filename: ParserTest.java
public class ParserTest {
    public static void main(String[] args) throws Exception {
        ...
    }
}
:::

Within this class we need to:

1. Acquire input
2. Initialize the lexer
3. Get the token stream from the lexer
4. Initialize the parser
5. Give the token stream to the parser
6. Get feedback about our code

To **acquire input**, we will create a `CharStream` from a filename passed in from the command line:

:::{code} java
:filename: ParserTest.java
CharStream input = CharStreams.fromFileName(args[0]);
:::

Now that we have input, we can initialize the lexer, and give it the input

:::{code} java
:filename: ParserTest.java
MyLexer lexer = new MyLexer(input);
:::

This will cause the lexer to generate tokens (and/or errors) for the code we have given it. For now lets just worry about the tokens (unhandled errors will simply bubble up to the command line)

:::{code} java
:filename: ParserTest.java
CommonTokenStream tokens = new CommonTokenStream(lexer);
:::

Now lets Initialize our parser, and hand it the token stream:

:::{code} java
:filename: ParserTest.java
MyParser parser = new MyParser(tokens);
:::

The parser has now analyzed the syntax and created a parse tree, which we can retrieve, using a method named for our start rule, and display to the user:

:::{code} java
:filename: ParserTest.java
ParseTree tree = parser.program();
System.out.println(tree.toStringTree(parser));
:::

Once we have put all this together, we can compile our host program, using

```bash
javac ParserTest.java parser/*.java
```

but we don't have anything to test it with yet...

## 4. Test File

We need to create a file that contains code that we will feed to the lexer and parser. Recall that, at the moment, our entire language consists of a "command" that can be either `start` or `stop`. Lets create a file called `program.thermostat`. In that file we will put a single word: `start` (with no newline, spaces etc.)

:::{code} text
:filename: program.thermostat
start
:::

Now we are ready to test our system. Run your "Thermostat Script" parser using the command:

```bash
java ParserTest program.thermostat
```

and take a look at the output. What did it output and why?

One of the things that was output was a parse tree. ANTLR will output a parse tree in a format much like clojure, whereby each level of the tree is formatted as rule: ( rule name (rule) ) where "\" means 0 or more of the previous item. So the line

```bash
(program (start start))
```

tells us that:

1. Input file (`program.thermostat`): Contains the word start
2. Lexer (`MyLexer.g4`): The lexer sees start and produces a START token based on this rule:

:::{code} yaml
START: 'start';
:::

3. Parser (`MyParser.g4`): The parser applies these grammar rules:

:::{code} yaml
program: start | stop ;
start: START ;
:::

The two instances of "start" can be confusing because they represent different things:

- The parser rule named start (defined as `start: START;`)
- The terminal symbol value, which ANTLR displays in lowercase (the actual text from your input file)

If you changed your test file to contain stop instead, you'd see `(program (stop stop))`.

## 5. Listener

While code that is embedded in the parser definition is easy to write, it ties logic to grammar, making your code harder to maintain. We should make the transition from injecting code via the parser file, to using inheritance from generated code. Using Inheritance to tie in to generated code like that produced by ANTLR provides a clean separation of grammar and logic and is more scalable for larger projects. Go ahead and remove the embedded code lines from `MyParser.g4` so it looks like it does below:

:::{code} yaml
:filename: MyParser.g4
parser grammar MyParser;
options { tokenVocab=MyLexer; }
program: 
    start | stop
    ;
start: 
    START 
    ;
stop: 
    STOP 
    ;
:::

and lets instead create a class that can interact with the parse tree. Create a new file called `MyListener.java`. Within `MyListener.java`, we need to import classes from ANTLR and our parser:

:::{code} java
:filename: MyListener.java
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import parser.*;
:::

We need to declare our class, and have it inherit from the generated file `MyParserBaseListener` so that the parser can call our code as it walks the parse tree:

:::{code} java
:filename: MyListener.java
public class MyListener extends MyParserBaseListener {
    ...
}
:::

Our code is ready to compile, but won't do anything. We will need to override child methods and refer to this class from our `ParserTest` class. One nice thing about overriding methods is that the method definition provides the declaration we need. 

Open up `parser/MyParserListener.java` and copy out the method declarations for `enterStart` and `enterStop` along with their documentation, and past them in our `MyListener` class, and mark them as `public`.

Now that we have the method declarations in our `MyListener` class, we can give them an implementation. Remove the semicolon after the method declarations in `MyListener` class and replace them with the code below:

:::{code} java
:filename: MyListener.java
{
    System.out.println("Start command received");
}
:::

and

:::{code} java
:filename: MyListener.java
{
    System.out.println("Stop command received");
}
:::

Finally, one thing to note is that we are copying these function definitions out of an interface definition. Interfaces, as we will cover later in the semester, can only declare public members, as private members would not be accessable even if defined by the interface. As such, while the method definitions in the interface lack a specification of Public, your methods in your `MyParserListener` class must still be marked as Public, so ensure you do that.

Now that we have our listener implemented, we can tie it in to our test program.

In `ParserTest.java`, after we have created the parse tree, we need to create an instance of our listener, and then walk the parse tree with it. To do so, add the following code to the main method of `ParserTest.java` after the line that creates the parse tree (`ParseTree tree = parser.program();`):

:::{code} java
:filename: ParserTest.java
MyListener listener = new MyListener();
ParseTreeWalker walker = new ParseTreeWalker();
walker.walk(listener, tree);
:::

This creates and initializes our listener, and creates a parse tree walker, that will go through the parse tree and call the appropriate methods in our listener. 

Go ahead and re-generate your ANTLR code, and re-compile your test program, run it and see what you get. To do so, run the following commands in your terminal:

```bash
rm *.class parser/*.class
antlr4 MyParser.g4 MyLexer.g4 -package parser -o parser
javac ParserTest.java MyListener.java parser/*.java
java ParserTest program.thermostat
```

You should see the same output as before, but now the code that produces that output is in your listener class, rather than embedded in the parser definition.

You will notice that there is a bash code in your repository called `run.sh`. This is a convenience script that will run all the commands you need to re-generate your parser, re-compile your code, and run your test program. Feel free to use it to make your life easier!

That's the code you might find in it:

```bash
#!/bin/bash
set -v
export CLASSPATH=".:/usr/share/java/antlr4.jar:/usr/share/java/antlr4-runtime.jar:$CLASSPATH"
rm *.class parser/*.class
antlr4 MyParser.g4 MyLexer.g4 -package parser -o parser
javac ParserTest.java MyListener.java parser/*.java
java ParserTest program.thermostat
```

From now on, whenever you want to test your code, just run:

```bash
./run.sh
```

---

From here on out in the lab, we are going to focus on building up our thermostat so it can do more than just start and stop.

Lets start by tracking the state of the the system as a whole by adding a boolean variable to our `MyListener` class called `is_started`, and setting its state in our `start` and `stop` handlers.

:::{code} java
:filename: MyListener.java
boolean is_started = false; // declaration goes in MyListener class
...
is_started=true; // set is_started to true in enterStart method
...
is_started=false; // set is_started to false in enterStop method
:::

As part of building out our "Thermostat Script" language we should make it be able to run a sequence of commands from our test file. To do this we will need to update our `MyParser.g4` file to recognize multiple actions in a row. At present we don't really have a good, clean way to do that directly, so lets create a new parser rule called action, and redefine program to be a sequence of 1 or more actions.

:::{code} yaml
:filename: MyParser.g4
parser grammar MyParser;
options { tokenVocab=MyLexer; }

program:
    action action* EOF;
action:
    start | stop
    ;
start:
    START
    ;
stop:
    STOP
    ;
:::

Finally, we need to update our `program.thermostat` file to test our new code. lets test it by putting an additional "start" keyword in to our test code:

:::{code} text
:filename: program.thermostat
start
start
:::

Now run your `run.sh` script again to see the results of your changes. What do you see?

---

It seems odd to allow a system that is already started to be started. Put some code in the start and stop methods to guard against redundant actions. Here is some sample code to get you started:

:::{code} java
:filename: MyListener.java
public void enterStop(MyParser.StopContext ctx){
    if (!is_started){
        System.out.println("Error: System already stopped");
        return;
    }
is_started=false;
...
}
:::

Well done! You have completed Lab 01. Make sure to commit and push your changes to your GitHub repository.

## Rubric

| Task                                      | Points |
|-------------------------------------------|--------|
| Create Lexer with START and STOP tokens   | 10     |
| Create Parser with program, start, stop rules | 15     |
| Generate ANTLR code and compile host program | 15     |
| Create test file with valid commands      | 10     |
| Implement Listener with enterStart and enterStop methods | 20     |
| Update Parser to handle multiple actions  | 15     |
| Implement state tracking and error handling in Listener | 15     |

Ways students can lose points:

- Failing to create the lexer or parser correctly.
- Errors in generating or compiling ANTLR code.
- Incorrect implementation of the listener methods.
- Not updating the parser to handle multiple actions.
- Lack of state tracking or error handling in the listener.
