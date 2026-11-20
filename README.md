# Graph Search and Processing System

[![Java CI](https://github.com/justaformality/GraphSearchProcessingSystem-SoftwareTesting/actions/workflows/ci.yml/badge.svg)](https://github.com/justaformality/GraphSearchProcessingSystem-SoftwareTesting/actions/workflows/ci.yml)

A Java framework that parses Graphviz DOT files into a graph model, searches it with interchangeable BFS, DFS, and Random Walk strategies, and exports the result back to DOT and PNG. It is built around the Strategy and Template Method patterns and covered by JUnit 5 tests that run in GitHub Actions CI.

This project implements graph parsing, graph construction, graph visualization, graph search algorithms, software refactoring techniques, and design pattern-based architecture improvements.

The system supports:

* DOT graph parsing
* Graph construction and visualization
* Breadth First Search (BFS)
* Depth First Search (DFS)
* Random Walk Search
* Unit testing
* Continuous integration
* Software refactoring and design patterns

---

# Project Overview

This project was built for CSE 464 Software Quality Assurance and Testing at Arizona State University.

The goal of this project was to build a flexible graph-processing framework while improving software quality through testing, refactoring, and maintainable architecture design.

The project evolved across multiple phases:

* Graph parsing and graph generation
* Unit testing and validation
* Graph search algorithms
* Refactoring and design pattern implementation
* Random Walk search support
* CI workflow integration

---

# Features

## Graph Processing

* Parse DOT graph files
* Create graph structures dynamically
* Add nodes and edges
* Export graphs as DOT files
* Generate graph visualizations using Graphviz

## Graph Search Algorithms

* Breadth First Search (BFS)
* Depth First Search (DFS)
* Random Walk Search

## Software Engineering Features

* Refactoring improvements
* Template Method design pattern
* Strategy design pattern
* Modular architecture
* Continuous integration workflow
* Automated unit testing

---

# Tech Stack

* Java 11+
* Maven
* JUnit 5
* Graphviz
* GitHub Actions
* Object-Oriented Programming

---

# Design Patterns Used

## Template Method Pattern

The graph search workflow was refactored into a reusable abstract search template to reduce duplicated logic between BFS and DFS.

This improved:

* code reuse
* maintainability
* scalability

## Strategy Pattern

Different graph search strategies were implemented as interchangeable components.

Supported strategies:

* BFS Strategy
* DFS Strategy
* Random Walk Strategy

This made the search system more modular and extensible.

---

# Project Structure

```text
src/
├── main/
│   └── java/
│       └── edu/asu/cse464/
│           ├── Graph.java                       # graph model + GraphSearch entry point
│           ├── Node.java
│           ├── Edge.java
│           ├── Path.java
│           ├── DotGraphParser.java              # DOT file -> Graph
│           ├── GraphFileManager.java            # Graph -> summary / DOT / PNG
│           ├── Algorithm.java                   # BFS, DFS, RANDOM_WALK
│           ├── GraphSearchStrategy.java         # Strategy interface
│           ├── GraphSearchStrategyFactory.java  # Algorithm -> strategy
│           ├── GraphSearchAbstract.java         # Template Method base for searches
│           ├── BFS.java
│           ├── DFS.java
│           ├── RandomWalk.java
│           └── Main.java
│
└── test/
    ├── java/
    │   └── edu/asu/cse464/
    │       ├── GraphBasicTest.java
    │       ├── DotGraphParserTest.java
    │       ├── GraphFileManagerTest.java
    │       ├── GraphSearchBFSTest.java
    │       ├── GraphSearchDFSTest.java
    │       └── GraphSearchMergedTest.java
    └── resources/
        ├── input.dot
        ├── input1.dot
        ├── empty.dot
        └── invalid.dot
```

---

# Setup

## Requirements

* Java 11 or later (the project compiles to Java 11, and CI runs on JDK 11)
* Maven
* Graphviz (the `dot` command is used to render PNGs)

Check installations:

```bash
java -version
mvn -version
dot -V
```

---

# Build the Project

From the project root:

```bash
mvn clean package
```

This compiles the code into `target/classes/` and runs the full test suite. It also produces a JAR in `target/`, but that JAR has no `Main-Class` entry, so run the program from the compiled classes as shown below.

---

# Run the Program

From the project root (the program reads `src/test/resources/input.dot` relative to the current directory):

```bash
java -cp target/classes edu.asu.cse464.Main
```

It writes `graph_summary.txt`, `graph_output.dot`, and `graph_output.png` to the project root. All three are gitignored.

The program will:

1. Parse a DOT graph file
2. Construct the graph object
3. Run graph search algorithms
4. Output graph information
5. Generate DOT output
6. Generate graph visualizations

---

# Example Algorithms

## Breadth First Search (BFS)

Explores neighboring nodes level by level.

## Depth First Search (DFS)

Explores graph paths deeply before backtracking.

## Random Walk Search

Traverses the graph by randomly selecting neighboring nodes at each step.

---

# Testing

The project includes automated unit tests for:

* graph parsing
* graph creation
* graph output generation
* BFS traversal
* DFS traversal
* Random Walk functionality
* invalid input handling

Run tests with:

```bash
mvn test
```

---

# Continuous Integration

The project includes a GitHub Actions CI workflow ([`.github/workflows/ci.yml`](.github/workflows/ci.yml)). On pushes to `main` and on pull requests into `main`, it sets up JDK 11 and Graphviz on Ubuntu, then runs `mvn package`, which:

* builds the project
* runs tests
* validates Maven compilation

The badge at the top of this README shows the latest result.

---

# Refactoring Improvements

Several refactoring techniques were applied throughout the project, including:

* Extract Method
* Rename Method and Variables
* Remove Duplicate Code
* Introduce Abstract Search Template
* Improve Search Strategy Separation

These refactorings improved:

* readability
* maintainability
* code reuse
* extensibility

---

# What I Learned

This project helped me gain experience with:

* graph algorithms
* software architecture
* object-oriented design
* design patterns
* software refactoring
* unit testing
* CI workflows
* modular Java development
* Maven project management

It also helped me better understand how software quality practices can improve maintainability and scalability in larger systems.

---

# Author

Arshia Rajeshnarayanan
Arizona State University
CSE 464 – Software Quality Assurance and Testing
