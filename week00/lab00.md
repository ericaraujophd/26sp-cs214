---
title: "->lab;"
subtitle: // Four Different Languages
---

This lab has four different parts: an introductory section using [Java](#lab00-java), an [Ada](#lab00-ada) exercise, a [Clojure](#lab00-clojure) (LISP) exercise, and a [Ruby](#lab00-ruby) exercise. You are to complete all four parts.

While everyone should begin with the Java introduction, the three exercises are independent of one another, so the order in which you do them does not matter.

## Before You Begin

We will be using [Coder](https://coder.cs.calvin.edu/login) for this lab. Make sure you have access to Coder and that you can log in. If you have any issues, please reach out to me as soon as possible.

You want to use VSCode Desktop to connect to Coder. If you haven't installed VSCode yet, please do so by following [these instructions](https://code.visualstudio.com/docs/setup/setup-overview).

Now, you need to [accept the assignment]() by creating a Team (give it a nice name) and ask your partner to join it. I highly recommend you find a partner first, so you don't create two separate teams. Once you have a team, go to Coder, open VSCode, and clone your assignment repository by following these steps:

1. Open VSCode and open the Terminal (``Ctrl + ` ``).
2. On your GitHub classroom assignment page, copy the repository URL by clicking the green "Code" button and then clicking the clipboard icon.
3. In the VSCode terminal, type `git clone <repository-URL>` and press Enter. Replace `<repository-URL>` with the URL you copied from GitHub.
4. After cloning the repository, navigate into the newly created directory by typing `cd <repository-name>` in the terminal. Replace `<repository-name>` with the name of your repository.

Now you can start working on the lab! Remember to commit and push your changes regularly to avoid losing any work. Your last commit before the deadline will be the one graded.

(lab00-java)=
## 1. Our First Java Program

In your repository you will find a file named CircleArea.java inside the `java` folder. This file is an empty text file where you will write a Java program that calculates the area of a circle given its radius.

Java source files should always be named with the name of the class they contain (case-sensitive), followed by the `.java` suffix. By convention, Java class names begin with an uppercase letter and use [camel case](https://en.wikipedia.org/wiki/Camel_case).

In that file, type (do not copy and paste) the following Java source program:

```java
/* CircleArea.java computes the area of a circle.
 *
 * Input: The radius of the circle.
 * Precondition: The radius is a positive number.
 * Output: The area of the circle.
 *
 * Begun by: Dr. Adams, for CS 214, at Calvin College, last century.
 * Completed by:
 * Date:
 **********************************************************/


import java.util.Scanner;  // include class for Scanner

public class CircleArea {

     /* function circleArea() computes a circle's area, given its radius.
      * Parameter: r, a double
      * Precondition: r is not negative.
      * Returns: the area of the circle
      */
     public static double circleArea(double r) {
        return Math.PI * r * r;               // return an expression
     } // circleArea method
	
  // main program
  public static void main(String[] args) {
      // prompt for radius
      System.out.println("\n\nTo compute the area of a circle,");
      System.out.print(" enter its radius: ");

      // Create a connection named keyboard to standard in
      Scanner keyboard = new Scanner(System.in);

      //Get the number from the user
      double radius = keyboard.nextDouble();

      // output area
      System.out.println("\nThe area is " + circleArea(radius) + "\n");
      System.out.printf("The area is %f\n\n", circleArea(radius));
      System.out.printf("The area is %.15f\n\n", circleArea(radius));
  } // main method

} // class CircleArea
```

Customize the opening odcumentation comments with your name and the date.

:::{admonition}
:class: tip
Note that Java uses `/*` and `*/` pairs to mark the beginning and end of multi-line comments, and `//` to indicate the beginning of an inline comment, which ends at the end of that line.
:::

Take a few minutes to study the Java code and see how it implements our algorithm for computing the area of a circle.

When you understand the purpose of each Java statement in performing the algorithm, save the file and open a terminal in VSCode. Navigate to the `java` folder inside your repository and compile your program as explained below.

### 1.1 Compile the Program

To run our Java program we must first compile our source file. Rather than compiling all the way to executable machine code (as happens when you compile Ada, C, or C++), Java code is instead compiled to an intermediate form called Java Virtual Machine (JVM) byte code. The JVM then takes the byte code and generates machine code which is actually run.

To compile our program, we will use the Java compiler `javac`. From the command line, `javac` can be used to compile an arbitrary Java program stored in `SourceFileName.java` and place the resulting byte code in `SourecFileName.class` with the following command:

```bash
javac CircleArea.java
```

Continue when it has compiled succesfully. To assert that the compilation was successful, you can use the `ls` command to list the files in the current directory. You should see a new file named `CircleArea.class` alongside your `CircleArea.java` file. This `.class` file contains the compiled bytecode of your Java program.

### 1.2 Run the Program

To run the compiled Java program, we use the `java` command followed by the name of the class containing the `main` method (without the `.class` extension). From the command line, you can run your program with the following command:

```bash
java CircleArea
```

Use the above command as a template to run your CircleArea program, and continue when it begins running succesfully.

### 1.3 Test the Program

Test your program using the following data values, recording the program's output for these values:

| Radius | Expected Area      |
|--------|--------------------|
| 1      | 3.141592653589793  |
| 2.5    | 19.634954084936208 |
| 4.99999 | 78.53971318605195  |

:::{admonition}
:class: tip
Note that when your program terminates and prints its results, you can use your keyboard's `up-arrow` to repeat the previous command (and so avoid wasting time retyping that command).
:::

### 1.4 Submit Your Work

When you have completed and tested your Java program, commit and push your changes to your GitHub repository. Make sure to include a meaningful commit message, such as "Completed CircleArea.java program".

On your repository page on GitHub, verify that your latest commit is present and that your `CircleArea.java` file contains the complete program you wrote. You can also check if the tests passed by going to the "Actions" tab on your repository page and looking for the latest workflow run. If all Java tests passed, you should see a green check mark next to the workflow run.

(lab00-ada)=
## 2. Ada Exercise

In this part of the exercise, we will write our first Ada program that will solve the same area-of-a-circle problem as before.

### 2.1 Coding in Ada

In your `ada` folder inside the project repository, you will find an empty file named `circle_area.adb`. By convention, the name of our Ada source files will end in the `.adb` suffix.

Spend some time typing the following Ada program into that file (do not copy and paste):

```ada
-- circle_area.adb computes the area of a circle.
--
-- Input: The radius of the circle.
-- Precondition: The radius is a positive number.
-- Output: The area of the circle.
--
-- Begun by: Prof. Adams, CS 214 at Calvin College.
-- Completed by:
-- Date:
----------------------------------------------------

with Ada.Text_IO, Ada.Float_Text_IO;
use  Ada.Text_IO, Ada.Float_Text_IO;

procedure circle_area is

   radius, area : float; 

   -- function circleArea computes a circle's area, given its radius
   -- Parameter: r, a float
   -- Precondition: r >= 0.0
   -- Return: the area of the circle whose radius is r
   ----------------------------------------------------
   function circleArea(r: in float) return float is 
      PI : constant := 3.1415927;
   begin
      return PI * r ** 2;
   end circleArea;

begin                           
   New_Line;
   Put_Line("To compute the area of a circle,");
   Put("enter its radius: ");
   Get(radius);

   area := circleArea(radius);

   New_Line;
   Put("The circle's area is ");
   Put(area);
   New_Line; New_Line; 

   Put("The circle's area is ");
   Put(area, 1, 15, 0);
   New_Line; New_Line; 
end circle_area;
```

Be sure to customize the program's opening documentation with your name, the date, etc.

To get the colored syntax highlighting in VSCode, make sure you have the [Ada Language Support extension](https://marketplace.visualstudio.com/items?itemName=AdaCore.ada) installed.

### 2.2 Compile and Run the Ada Program

To translate our Ada programs into machine language, we will be using the (freeware) **GNU Ada Translator (Gnat)**. Gnat provides a program named `gnatmake` that simplifies the translation process; just enter the following on the command-line:

```bash
gnatmake circle_area.adb
```

This will perform the 3 translation steps on your program:

```bash
gcc -c circle_area.adb       # compilation
gnatbind -x circle_area.ali  # binding calls to defs
gnatlink circle_area.ali     # linking modules together
```

The first command (`gcc`) creates an intermediate file `circle_area.ali`, which is then bound and linked with the command `gnatbind`.

The `gnatlink` command creates the file `circle_area`, which contains your binary executable program.

In general, the command

```bash
gnatmake source_file.adb
```

will create an executable file named `source_file` in the current directory by compiling the program stored in `source_file.adb` (as well as any other library units on which it depends), and then binding and linking the resulting object file to any library units it uses. Ada thus requires that the name of a source file match the name of the program stored within it.

TO run your Ada program, enter the following command on the command-line:

```bash
./circle_area
```

Make sure you are in the `ada` folder when you run this command.

