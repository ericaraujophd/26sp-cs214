---
title: ->proj;
subtitle: // A Bit More With the Four Languages
---

Project 00 will be quite similar to Lab 00, but this time you will implement **five** geometric shape volume calculations in all **four** programming languages: Java, Ada, Clojure, and Ruby. That is: twenty programs in total!

The problems to be solved are to compute the volume of geometric shapes given their dimensions. The geometric shapes and their volume formulas are:

| Shape | Formula | Notes | File Names |
|-------|---------|-------|------------|
| Cylinder | $V = \pi r^2 h$ | | `Cylinder.java`, `cylinder.adb`, `cylinder.clj`, `cylinder.rb` |
| Cone | $V = \frac{1}{3} \pi r^2 h$ | | `Cone.java`, `cone.adb`, `cone.clj`, `cone.rb` |
| Rectangular Prism | $V = l \times w \times h$ | | `RectangularPrism.java`, `rectangular_prism.adb`, `rectangular_prism.clj`, `rectangular_prism.rb` |
| Pyramid | $V = \frac{1}{3} B h$ | $B$ is the area of the base | `Pyramid.java`, `pyramid.adb`, `pyramid.clj`, `pyramid.rb` |
| Torus | $V = 2 \pi^2 R r^2$ | $R$ is the distance from the center of the tube to the center of the torus, $r$ is the radius of the tube | `Torus.java`, `torus.adb`, `torus.clj`, `torus.rb` |

You must implement **all five shapes** in **all four languages** (20 programs total). Name your files exactly as shown in the table above.

:::{admonition} Warning
:class: warning
If the name of the files do not match the expected names, the automated tests will fail and you will not get credit for your work. The same goes if some of the programs are missing.
:::

To start your project, go to [this link]() and accept the assignment. Once you have accepted and the repository is generated on github, go to Coder, open VSCode, and clone your assignment repository by following these steps:

1. Open VSCode and open the Terminal (``Ctrl + ` ``).
2. On your GitHub classroom assignment page, copy the repository URL by clicking the green "Code" button and then clicking the clipboard icon.
3. In the VSCode terminal, type the following command, replacing `<repository-url>` with the URL you just copied:

```bash
git clone <repository-url>
```

4. Press `Enter`. This will create a local copy of your assignment repository in Coder.

Your project contains four folders: `java`, `ada`, `clojure`, and `ruby`. Create all five volume calculation programs in each folder.

# Rubric

| Criterion | Excellent (100%) | Satisfactory (70%) | Needs Improvement (40%) | Missing (0%) |
|-----------|------------------|--------------------|-------------------------|--------------|
| Correctness | All programs correctly compute the volume for all five shapes in all four languages. | Most programs correctly compute the volume for most shapes in most languages. | Some programs compute the volume correctly, but there are significant errors in others. | No programs compute the volume correctly. |
| Code Quality | Code is well-organized, follows language conventions, and includes meaningful comments. | Code is mostly organized, with some adherence to language conventions and some comments. | Code is poorly organized, lacks adherence to language conventions, and has few comments. | Code is disorganized, does not follow language conventions, and lacks comments. |
| Submission | All files are named correctly and submitted on time. | Most files are named correctly and submitted on time. | Some files are named incorrectly or submitted late. | Files are named incorrectly and/or not submitted. |

Ways students lose points:

- Incorrect file names.
- Missing programs.
- Programs that do not compile or run.
- Incorrect volume calculations.
- Poor code quality (lack of comments, poor organization, not following language conventions).
