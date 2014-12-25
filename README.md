UMassAICourseSearch
===================


/**Code for problem 5 of Homework One in AI course
 
* Find the minimum number of moves of knight from 
 
* (0,0) to (x,y)
 
* @author Liu Yang & Wei Hong
 
* @mail lyang@cs.umass.edu & hwberg@gmail.com
 */

1 This is a Eclipse java project. You can import it directly.

2 To run the program, just run ChessboardSearchAstar.java which has included the main function.

3 The data and figures are in /Figures folder.


/**Code for problem 6 (Travelling Salesman Problem)
* @author Liu Yang & Wei Hong
 
* @mail lyang@cs.umass.edu & hwberg@gmail.com
 */

ANode.java and Node.java are classes defining search nodes in the TSP problem.

ShowMST.java generate the Minimum spanning tree of a set of nodes

TSPWithMST.java and TSPWithMST_numOfNodes.java use MST as heuristics to solve the TSP problem.
The difference is the output. The former outputs the route of a realworld problem.
The latter calculate the number of nodes expanded and time used in random generated problems.

TSPWithMaxMST.java and TSPWithMaxMST_numOfNodes.java use the maximum MST as heuristics whichi is not admissible. The difference is the output, which is analogous to the paragraph above.

plotFigures.m is an Matlab script to plot figures for the questions in the homework.
