/* The code is for demonstrating A* search with MST heuristics on the
 * travelling salesman problem.
 * The MST(Minimum Spanning Tree) is implemented using Prim's algorithm.
 * CS683 Artificial Intelligence
 * Author: Wei Hong, Liu Yang
 * UMass Amherst, School of Computer Science
 * Copyright 2014
 */
package cs683.hw1.TSP;

import java.util.HashMap;

//Each node has x, y coordinates in between 0 and 1
public class Node{
	public double x;
	public double y;	
	public Node(double x, double y){
	  super();
	  assert(x < 1 && x > 0);
	  assert(y < 1 && y > 0);
	  this.x = x;
	  this.y = y;		
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Node){
			Node other = (Node) o;
			return(this.x == other.x && this.y == other.y);
		}
		
		return false;		
	}
	public int hashCode(){
		
		return (int)(this.x*this.y*100000);

	}
	//test
	public static void main(String[] args){
		Node n1 = new Node(1.0, 2.0);
		Node n2 = new Node(1.0, 2.0);
		HashMap<Node, Integer> nodeMap = new HashMap<Node, Integer>();
		nodeMap.put(n1, 1);
		nodeMap.put(n2, 2);
		for(Node n: nodeMap.keySet()){
			System.out.println(n.x + " " + n.y);
		}
		
	}

	

}
