# Project 1 Part 2: Non-Deterministic Finite Automata

* Team: Maddie Ross & Ravi Shankar
* Class: CS 361 
* Date: Fall 2018

### Overview
A java project models a non-deterministic finite automaton. Packages were used to organize the classes and data structures from the java collections framework was used to store and represent states, symbols and the automaton. 

### Contents
```bash
├── Makefile
├── README.md
├── fa
│   ├── FAInterface.java
│   ├── State.java
│   ├── dfa
│   │   ├── DFA.java
│   │   ├── DFAInterface.java
│   │   └── DFAState.java
│   └── nfa
│       ├── NFA.java
│       ├── NFADriver.java
│       ├── NFAInterface.java
│       └── NFAState.java
├── p2.pdf
└── tests
    ├── p2tc0.txt
    ├── p2tc1.txt
    ├── p2tc2.txt
    └── p2tc3.txt
```

### Compiling and Using
- `make all` - compiles all the files
- `java fa.nfa.NFADriver <input file>` - Run the NFA driver


### Discussion
This was a significantly more complex project. All the methods except eClosure and getDFA were fairly simple to implement. Graph traversal took some reading and tutorials before we could implement it. We used a recursive DFS traversal to get the eClosure. The most significant amount of time was spent implementing the getDFA method. When creating this we had to create a function containsFinalState() that checks whether a set of states contained a final state.


### Testing
Tested project code using test files located in the `/tests` folder


### Sources used
- [Depth First Search or DFS for a Graph](https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/)
- [Data Structures and Depth First Traversal](https://www.tutorialspoint.com/data_structures_algorithms/depth_first_traversal.htm)
- [Depth First Search in Java](https://java2blog.com/depth-first-search-in-java/)
- [Map (Java Platform SE 8)](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)
- [Hash Set (Java SE 10 & JDK 10)](https://docs.oracle.com/javase/10/docs/api/java/util/HashSet.html)