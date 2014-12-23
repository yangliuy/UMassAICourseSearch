/* The code is for demonstrating A* search with MST heuristics on the
 * travelling salesman problem.
 * The MST(Minimum Spanning Tree) is implemented using Prim's algorithm.
 * CS683 Artificial Intelligence
 * Author: Wei Hong, Liu Yang
 * UMass Amherst, School of Computer Science
 * Copyright 2014
 */

package cs683.hw1.TSP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ShowMST {	
	static double[][] distanceMap;
	//problem generator
	//Randomly generate a couple of unique (x,y) coordinates, each is in between (0, 1)
	public static ArrayList<Node> probGenerator(int sizeOfProblem){
		//use a hashmap as a buffer to store randomly generated nodes
		HashMap<Node,Integer> nodeCache = new HashMap<Node,Integer>();
		//terminate when the number of nodes meets demand
	    int count = 0;
		while(nodeCache.size()<sizeOfProblem){
			double x = Math.random();
			double y = Math.random();
			nodeCache.put(new Node(x,y), 1);
		}
		//convert the keyset into an arraylist
	    ArrayList<Node> nodes = new ArrayList<Node>(nodeCache.keySet());
		return nodes;		
	}
	//Generator an Minimum Spanning Tree (MST) over a set of nodes
	//Use Prim's algorithm
	
	public static int[] genMST(ArrayList<Node> nodes){
		//adjacent matrix to implement the fully connected graph
		int size = nodes.size();
		//initialize 
		final double[] dist = new double[size];
		final int[] pred = new int[size];
		final boolean[] visited = new boolean[size];
		//set distance to infinity
		for(int i = 0; i < dist.length; i++){
			dist[i] = Double.MAX_VALUE;
		}
		//starting from the first node, can be changed.
		dist[0] = 0;
		
		for(int i = 0; i < dist.length; i++){			
			final int next = minDist(dist, visited);
		//	System.out.println(nodes.get(next).x + " " + nodes.get(next).y);
			visited[next] = true;
			//find all unvisited neighbors of the current node
			for(int j = 0; j < size; j++){
				if(j != next && !visited[j]){
					double d = distanceMap[next][j];
					//update distance and pred array
					if(dist[j] > d ){
						dist[j] = d;
						pred[j] = next;
					}							
				}
			}
		}
		
	
		//return pred;	
		
		return pred;
	}
	//Traverse the distance array to obtain the nearest node to the explored set.
	//Can be optimized, e.g. use heap. 
	private static int minDist(double[] dist, boolean[] v){
		double x = Double.MAX_VALUE;
		int y = -1;
		for(int i = 0; i < dist.length; i++){
			if(!v[i] && dist[i] < x){
				y = i;
				x = dist[i];
			}
		}
		return y;
	}
	
	//calculate the Euclidean distance between two nodes
	private static double distance(Node n1, Node n2){
		return Math.sqrt(Math.pow((n1.x - n2.x),2) + Math.pow((n1.y - n2.y),2));		
	}
	

	
	public static void main(String[] args) throws IOException{

	    ArrayList<Node> nodes = probGenerator(100);
	    for(Node n:nodes){
          	System.out.println(n.x + " " + n.y);	    	
	    }
		distanceMap = new double[nodes.size()][nodes.size()];
		for(int i = 0; i < nodes.size(); i++ ){
			for(int j = 0; j < i; j++){
				distanceMap[i][j] = distance(nodes.get(i), nodes.get(j));
				distanceMap[j][i] = distanceMap[i][j];
			}
		}
		for(int i = 0; i < nodes.size(); i++ ){
			distanceMap[i][i] = 0 ;			
		}
		int[] edges = genMST(nodes);

		

	    
		// Done with the file

		try {
	          File file = new File("pred.txt");
	          BufferedWriter output = new BufferedWriter(new FileWriter(file));
	          for(int i : edges){
	        	  output.write(i +"\n");
	          }	          
	          output.close();
	        } catch ( IOException e ) {
	           e.printStackTrace();
	        }		
	}
}
