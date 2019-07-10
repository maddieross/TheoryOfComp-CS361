# Project 1 Part 1: Deterministic Finite Automata

* Team: Maddie Ross & Ravi Shankar
* Class: CS 361 
* Date: Fall 2018

### Overview
A java project models a deterministic finite automaton. Packages were used to organize the classes and data structures from the java collections framework was used to store and represent states, symbols and the automaton. 

### Contents
```bash
├── Makefile
├── README.md
├── fa
│   ├── FAInterface.java
│   ├── State.java
│   └── dfa
│       ├── DFA.java
│       ├── DFADriver.java
│       ├── DFAInterface.java
│       └── DFAState.java
└── tests
    ├── p1tc1.txt
    ├── p1tc2.txt
    └── p1tc3.txt
```

### Compiling and Using
- `make all` - compiles all the files
- `java fa.dfa.DFADriver <input file>` - Run the DFA driver


### Discussion
One of the main issues we encountered was using the collections framework. Neither of us had used collections before other than a very brief exposure to them in CS321, so a little time was spent going over these data structures and how to use them. After settling on using sets and a hash map, programming them was pretty straight forward. One of the errors that we ended up spending some time on was because we tried to compare two strings using '==' instead of '.equals()'. One of the things we would like to change about the project is the driver. We would make it more robust by printing a helper message instead of just crashing if the required arguments were not present. As a result of this project, We believe that going forward, we would be more comfortable using the collections data structures

### Testing
Tested project code using test files located in the `/tests` folder


### Sources used
- [Set (Java Platform SE 8)](https://docs.oracle.com/javase/8/docs/api/java/util/Set.html)
- [Map (Java Platform SE 8)](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)
- [Hash Set (Java SE 10 & JDK 10)](https://docs.oracle.com/javase/10/docs/api/java/util/HashSet.html)