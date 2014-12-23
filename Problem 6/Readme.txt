
/**Code for problem 6
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

