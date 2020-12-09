# Robokieli
A simple virtual machine for processing Robokieli

### What?
Robokieli is the name I've given to a simple, unnamed assembly-like language, originally created by [Tietojenkäsittelytieteen valintakoeyhteistyö](https://tkt-yhteisvalinta.fi/)
for use in the 2018 Finnish computer science entrance exams. It was used as a basis for [question 2](https://tkt-yhteisvalinta.fi/wp-content/uploads/2018/05/Tehtava_2_korjattu_2018.pdf)
of the 2018 exam. This machine for running the Robokieli code has been written entirely based on the question, as no other mentions of it by the authors are known to exist.

### Why?
Dunno, just felt like making something. It was a sunday project written in very poor style with few functions and virtually no input checking, so expect it to crash horribly with the slightest syntax error.
It's pretty error-safe as long as the syntax is correct, though. Programs need to halt in order to produce output, though I might change that in the future.

### Running
The syntax reference is found in the 2018 entrance exams. For running the program you need three files: commands, provided here in comm.txt, number input, provided here in num.txt, and memory patches, provided here in mem.txt
Writing the included program was the final task in the exam, and it takes two numbers and multiplies them. Memory patches from can be left empty, and are only required for programs where spesific numbers are required to be set as quasi-variables before the program is run

The program can be compiled with javac, and run with 
```java Robokieli comm.txt mem.txt num.txt``` with the additional ```-v``` flag to be added to the end for verbosity.

### Thanks
Tkt-yhteisvalinta
