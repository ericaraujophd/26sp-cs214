---
title: ->proj02;
subtitle: Using Selection
---

## Overview

This week's project is to use the equivalent of a C++ `switch` construct in each of our languages. More precisely, here is a C++ function that, given an integer average, returns the corresponding letter grade (assuming 90-100 is an A, 80-89 is a B, etc.):

:::{code} cpp
char letterGrade(int average)
   {
      switch (average / 10)
      {
         case 10: case 9:
            return 'A';
         case 8:
            return 'B';
         case 7:
            return 'C';
         case 6:
            return 'D';
         default:
            return 'F';
      }
   }
:::

Your project is to implement the equivalent function in Java, Ada, Clojure, and Ruby. You must also write a simple "driver program" to test your function/method in each language.

Accept the invitation to join the [GitHub Classroom assignment](https://classroom.github.com/a/kqg1GZZ3) for this project. Then, open VS Code through Coder and clone your repository.

## Java

Java has a `switch` statement, governed by these (simplified) productions:

:::{code-block} yaml
<switchStmt>              ::= 'switch' '(' <expression> ')' switchBlock ;
<switchBlock>             ::= '{' <switchBlockStmtGroups> <switchLabels> '}' ;
<switchBlockStmtGroups>   ::= <switchBlockStmtGroup> | <switchBlockStmtGroups> <switchBlockStmtGroup> | Ø ;
<switchBlockStmtGroup>    ::= <switchLabels> <blockStmts> ;
<switchLabels>            ::= <switchLabel> | <switchLabels> <switchLabel> | Ø ;
<switchLabel>             ::= 'case' <constantExpression> ':' | 'default' ':' ;
:::

## Ada

Where C++ and Java have a `switch` statement, Ada has a `case` statement, governed by these (simplified) productions:

:::{code-block} yaml
<CaseStmt>         ::=  'case' <Expression> 'is'
                               <CaseAlternative> ';'
                               <CaseAlternatives> 
                               <OthersAlternative> ';'
                           'end case' ;
<CaseAlternative>  ::=  'when' <ChoiceList> '=>' <StmtList> ;
<CaseAlternatives> ::=  <CaseAlternative> ';' <CaseAlternatives> | Ø ;
<OthersAlternative>::=  'when others =>' <StmtList> ';' ;
<ChoiceList>       ::=  <Choice> <MoreChoices> ;
<MoreChoices>      ::=  '|' <Choice> <MoreChoices> | Ø ;
<Choice>           ::=  <Expression> | <Range>  ;
<Range>            ::=  <SimpleExpression> '..' <SimpleExpression> ;
:::

The effect is that when the `<Expression>` matches a `<Choice>` in a `<CaseAlternative>`, then the `<StmtList>` associated with that `<Choice>` is executed. If it matches no `<Choice>` and an others choice is present, then the `<StmtList>` associated with others is executed. Note that the others choice must be the final choice. Note also that all Choice values must be known at compile-time.

## Clojure

In addition to its if function, Clojure has a `cond` function that offers a more convenient multi-branch behavior. This function is much more general than a `switch` or `SELECT` statement, allowing arbitrarily complex conditions to be tested in each "case". Its syntax is as follows:

:::{code-block} yaml
 <CondFunction>     ::=   '(' 'cond' <PairList> <FinalPair> ')' ;
<PairList>         ::=   <Condition> <Expression> <MorePairs> ;
<MorePairs>        ::=   Ø | <Condition> <Expression> <MorePairs> ;
<FinalPair>        ::=   ':else' <Expression> | Ø ;
:::

where `<Condition>` is an `<Expression>` that evaluates to `nil` or non-`nil`. The effect is that when the function is called, the `<Conditions>` in each `<PairList>` are evaluated sequentially until one evaluates to non-`nil`. That `<Condition>`'s associated `<Expression>` is then evaluated, and the value it produces is the return-value of the `cond` function. If present, the `<FinalPair>` beginning with `:else` provides the equivalent of a C-family `default:` case.

## Ruby

:::{code-block} yaml
<CaseStmt>	    ::= 'case' <Expression>
                      <WhenStmt>
                      <ElseStmt>
		            'end'
<WhenStmt>     ::= 'when' <When_args> <Then> <Statement> <WhenStmt> | Ø		 
<ElseStmt>     ::= 'else' <Else_args> <Then> <Statement> | Ø	         
<Then>         ::=  '\n' | 'then' | Ø
:::

where `<Expression>` will be tested for a form of equality with each of `<When_args>`, and `<Statement>` is the return value when that branch is taken.

A Ruby `case` statement is powerful in ways that this simple exercise will not show you. For some great examples, see this post at [Skorks.com](http://www.skorks.com/2009/08/how-a-ruby-case-statement-works-and-what-you-can-do-with-it/).

## Turning In Your Project

Turn in by commiting and pushing the last changes in your project. Make sure to include your test driver program in each language.
