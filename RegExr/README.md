# Project 1 Part 3: Regular Expressions 

* Team: Maddie Ross & Ravi Shankar
* Class: CS 361 
* Date: Fall 2018

### Overview
A java project that constructs an NFA for a given regular expression (RegEx) 

### Contents
```bash
├── CS361FA.jar
├── CS361FAdocs.zip
├── Makefile
├── README.md
├── re
│   ├── RE.java
│   ├── REDriver.java
│   └── REInterface.java
└── tests
    ├── p3tc1.txt
    ├── p3tc2.txt
    └── p3tc3.txt
```

### Compiling and Using
- `make all` - compiles all the files
- `java -cp ".:./CS361FA.jar" re.REDriver <input file>` - Run the RE driver


### Discussion
The article on Parsing Regular Expressions helped a lot in this project. This wasn't as complicated as we thought it would be either. We did initially have trouble with the concatentation and union methods though.


### Testing
Tested project code using test files located in the `/tests` folder


### Sources used
- [Parsing regular expressions with recursive descent](http://matt.might.net/articles/parsing-regex-with-recursive-descent/)
- [Compiler Design - Top-Down Parser](https://www.tutorialspoint.com/compiler_design/compiler_design_top_down_parser.htm)