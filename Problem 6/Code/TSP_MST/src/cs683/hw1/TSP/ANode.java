/* The code is for demonstrating A* search with MST heuristics on the
 * travelling salesman problem.
 * The MST(Minimum Spanning Tree) is implemented using Prim's algorithm.
 * CS683 Artificial Intelligence
 * Author: Wei Hong, Liu Yang
 * UMass Amherst, School of Computer Science
 * Copyright 2014
 */

package cs683.hw1.TSP;

import java.util.PriorityQueue;

//A* node
public class ANode implements Comparable<ANode> {
  public int[] path;
  public double fValue;
  public double gValue;
  public Node node;
  public int index;
  public ANode(int[] path, Node node, int index, double fValue, double gValue){
	  this.path = path;
	  this.fValue = fValue;
	  this.gValue = gValue;
	  this.node = node;
	  this.index = index;
  }
  
	@Override
	public int compareTo(ANode other) {
		// TODO Auto-generated method stub
		return Double.compare(this.fValue,other.fValue);
	}
	//test
	public static void main(String[] args){
		ANode n1 = new ANode(null, null, 0, 1.9461534855690281, 2.0);
		ANode n2 = new ANode(null, null, 0, 1.6838629479139304, 2.0);
		ANode n3 = new ANode(null, null, 0, 2.0625768572022416, 2.0);
		PriorityQueue<ANode> s = new PriorityQueue<ANode>();
		s.add(n1);
		s.add(n2);
		s.add(n3);
		while(s.size()!=0){
			System.out.println(s.remove().fValue);
		}	
	}

}
