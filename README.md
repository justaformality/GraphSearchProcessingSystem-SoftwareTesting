# CSE4642026-arajeshn
CSE 464: Software Quality Assurance and Testing - Course Project Part 1

**GitHub REPOSITORY**
https://github.com/justaformality/CSE4642026-arajeshn

**AUTHOR:**
Arshia Rajeshnarayanan
1225058575
Arizona State University
CSE464: Software QA and Testing

**OVERVIEW**
This project implements a graph processing system in Java that can:
Feature 1 – Parse a DOT graph file to create a graph
Feature 2 – Add nodes to a graph
Feature 3 – Add edges to a graph
Feature 4 – Output the graph as a DOT file or graphics

Unit tests were also implemented to validate each feature.

**PROJECT STRUCTURE**
src
 ├── main
 │   └── java
 │       └── edu/asu/cse464
 │           ├── Edge.java
 │           ├── Graph.java
 │           ├── DotGraphParser.java
 │           ├── GraphFileManager.java
 │           └── Main.java
 │
 └── test
     └── java
         └── edu/asu/cse464
             ├── GraphBasicTest.java
             ├── DotGraphParserTest.java
             └── GraphFileManagerTest.java

src/test/resources
 ├── input1.dot
 ├── empty.dot
 └── invalid.dot

 **ENVIRONMENT SETUP**
 This project requires the following software:
 **Java**
 Java 21 or later
 java - version

 **Maven**
 mvn -version

 **Graphviz**
 dot -V

 **BUILDING THE PROJECT**
 Navigate to the project root directory.
 cd CSE4642026-arajeshn

 Build the project using Maven.
** mvn package**

<img width="890" height="695" alt="Screenshot 2026-03-06 at 10 37 48 PM" src="https://github.com/user-attachments/assets/8ec77e21-5ee0-4599-b2df-eae1e313d875" />

 if successful, Maven will produce a compiled JAR file in 
 target/

**RUNNING THE PROGRAM**

Run the program using:

**java -cp target/CSE4642026-arajeshn-1.0-SNAPSHOT.jar edu.asu.cse464.Main**

The program will:
1. Parse a DOT graph file
2. Construct a graph object
3. Output graph information
4. Generate a DOT file
5. Generate a PNG visualization

**FEATURE 1 PARSE DOT GRAPH**

<img width="965" height="188" alt="Screenshot 2026-03-06 at 10 39 19 PM" src="https://github.com/user-attachments/assets/28ece3ab-c171-4641-9ef4-be244c90d037" />

parseGraph(String filepath)
This method reads a DOT file and creates a directed graph

**Example Input**
digraph {
a -> b
b -> c
c -> d
}

The graph structure is printed using:
toString()

**Example Output**
Nodes: a, b, c, d
Edges:
a -> b
b -> c
c -> d


**FEATURE 2 ADDING NODES**

addNodes(String label)
addNodes(String[] label)
These APIs allow nodes to be added to the graph while checking for duplicate labels.

**Example**
graph.addNode("x");
graph.addNodes(new String[]{"y", "z"});

**FEATURE 3 ADDING EDGES**
addEdge(String srcLabel, String dstLabel)
This method adds an edge between two nodes and prevents duplicate edges.

**Example**
graph.addEdge("a", "b")



**FEATURE 4 OUTPUT GRAPH**
outputDOTGraph(String path)
outputGraphics(String path, String format)
the first method outputs the graph in DOT format.
the second method generates a graphical representation using Graphviz

DOT File Output
<img width="602" height="123" alt="Screenshot 2026-03-06 at 10 40 55 PM" src="https://github.com/user-attachments/assets/774c5771-a7ed-42e2-99d0-a5e3bc8d862e" />

Graph Visualization
<img width="709" height="300" alt="Screenshot 2026-03-06 at 10 42 01 PM" src="https://github.com/user-attachments/assets/ebc393e7-fd61-49b2-ba2a-3deaadd2bcb3" />

**UNIT TESTS**
JUnit tests were written for each feature.

Test classes:
GraphBasicTest.java
DotGraphParserTest.java
GraphFileManagerTest.java

Run tests using:
**mvn test**


<img width="1256" height="828" alt="Screenshot 2026-04-03 at 5 10 39 PM" src="https://github.com/user-attachments/assets/8a17dfb0-4233-4dd2-b714-2e334d1af6de" />

**PROJECT PART 2**
This phase extends the graph system with additional functionality:

Feature 5 – Remove nodes and edges  
Feature 6 – Graph search using BFS  
Feature 7 – Graph search using DFS  
Feature 8 – Merge conflict resolution using Algorithm enum  
Feature 9 – Continuous Integration using GitHub Actions  

All features are tested using JUnit and integrated into a CI workflow.

**MAIN PROGRAM OUTPUT**
The Main.java class has been updated to demonstrate all features from Project Part 1 and Project Part 2.

Please run the project using:
**mvn test**
**mvn package**
**java -cp target/CSE4642026-arajeshn-1.0-SNAPSHOT.jar edu.asu.cse464.Main**

Output Screenshots:
<img width="1132" height="749" alt="Screenshot 2026-04-03 at 5 09 37 PM" src="https://github.com/user-attachments/assets/9ea3f9ac-87eb-4763-94c6-8f91435f018a" />
<img width="672" height="420" alt="Screenshot 2026-04-03 at 5 10 00 PM" src="https://github.com/user-attachments/assets/4526f29b-3b26-4673-81c0-c6aeedc90f6a" />


