package edu.umass.lyang;

import java.util.Comparator;
import java.util.PriorityQueue;

/**Code for problem 5 of Homework One in AI course
 * Find the minimum number of moves of knight from 
 * (0,0) to (x,y)
 * @author Liu Yang & Wei Hong
 * @mail lyang@cs.umass.edu & hwberg@gmail.com
 */

public class ChessboardSearchAstar {
	
	static int[][] actionCosts = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1},
						   {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
	
	private Node startNode;
	private Node goalNode;
	int MAX_X = 1999;
	int MAX_Y = 1999;
	int MIN_X = -2000;
	int MIN_Y = -2000;
	int visitedFlagOffset = 2000; // x + visitedFlagOffset = index of x in visitedFlag
	
	static int generatedNodeCount;
	static int expandedNodeCount;

	private enum flagType{
		INIT, VISITED;
	}
	
	private static flagType[][] visitedFlag;
	
	public int getVisitedFlagOffset() {
		return visitedFlagOffset;
	}

	public void setVisitedFlagOffset(int visitedFlagOffset) {
		this.visitedFlagOffset = visitedFlagOffset;
	}

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public Node getGoalNode() {
		return goalNode;
	}

	public void setGoalNode(Node goalNode) {
		this.goalNode = goalNode;
	}
	
	//return -1 when there is no solution or illegal input
	public int astarSearch(){
		if(startNode.getX() == goalNode.getX() && startNode.getY() == goalNode.getY()){
			return 0;
		}
		
		//since the startNode is fixed, only need to check goalNode
		if(!(goalNode.getX() >= MIN_X && goalNode.getX() <= MAX_X) || !(goalNode.getY() >= MIN_Y && goalNode.getY() <= MAX_Y)){
			System.err.println("illegal input!"); 
			return -1;
		}
		Comparator<Node> comparator = new Comparator<Node>() {

			@Override
			public int compare(Node n1, Node n2) {
				// TODO Auto-generated method stub
				if(n1.getFValue(goalNode) > n2.getFValue(goalNode)){
					return 1;
				} else if(n1.getFValue(goalNode) == n2.getFValue(goalNode)){
					return 0;
				}
				return -1;
			}
		};
		
		
		PriorityQueue<Node> pQueue = new PriorityQueue<Node>(2, comparator);
		//init visitedFlag
		visitedFlag = new flagType[MAX_X - MIN_X + 1][MAX_Y - MIN_Y + 1];
		for(int i = 0; i < visitedFlag.length; i++){
			for(int j = 0; j < visitedFlag[i].length; j++){
				visitedFlag[i][j] = flagType.INIT;
			}
		}
		generatedNodeCount = 1;
		expandedNodeCount = 0;
		Node currentNode = new Node(startNode);
		startNode.setG(0);
		startNode.setStepCount(0);
		startNode.setParentNode(null);
		pQueue.add(startNode);
		visitedFlag[startNode.getX() + visitedFlagOffset][startNode.getY() + visitedFlagOffset] = flagType.VISITED;
		
		while(pQueue.size() != 0 ){
			pQueue.poll();
			expandedNodeCount++;
			visitedFlag[currentNode.getX() + visitedFlagOffset][currentNode.getY() + visitedFlagOffset] = flagType.VISITED;
			for(int i = 0; i < actionCosts.length; i++){
				Node childNode = new Node(currentNode.getX() + actionCosts[i][0], currentNode.getY() + actionCosts[i][1]);
				if((childNode.getX() >= MIN_X) && (childNode.getX() <= MAX_X) && (childNode.getY() >= MIN_Y) && (childNode.getY() <= MAX_Y) &&
						visitedFlag[childNode.getX() + visitedFlagOffset][childNode.getY() + visitedFlagOffset] == flagType.INIT ){
					generatedNodeCount++;
					childNode.setParentNode(currentNode);
					childNode.setG(currentNode.getG() + 1);
					childNode.setStepCount(currentNode.getStepCount() + 1);
					pQueue.add(childNode);
					visitedFlag[childNode.getX() + visitedFlagOffset][childNode.getY() + visitedFlagOffset] = flagType.VISITED;
				}
			}
			currentNode = pQueue.peek();
			if(currentNode.getX() == goalNode.getX() && currentNode.getY() == goalNode.getY()){
				 return currentNode.stepCount;
			}
		}
		System.out.println("There is no solution for the input!");
		return -1;
	}
	
	/**
	 * @param for testing
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ChessboardSearchAstar chessSearch =  new ChessboardSearchAstar();
		
		chessSearch.setStartNode(new Node(0,0));
		chessSearch.setGoalNode(new Node(1,1));
		//tracking the computation time
		long startTime = System.currentTimeMillis();
		chessSearch.astarSearch();
		long endTime = System.currentTimeMillis();
		System.out.println("The computation time is " + (endTime - startTime) + "ms");
		System.out.println("expandedNodeCount: " + expandedNodeCount + " " + "generatedNodeCount: " + generatedNodeCount);
		
//		chessSearch.setStartNode(new Node(0,0));
//		chessSearch.setGoalNode(new Node(-1,-1));
//		chessSearch.astarSearch();
//		System.out.println("expandedNodeCount: " + expandedNodeCount + " " + "generatedNodeCount: " + generatedNodeCount);
//		
//		chessSearch.setStartNode(new Node(0,0));
//		chessSearch.setGoalNode(new Node(7,0));
//		chessSearch.astarSearch();
//		
//		chessSearch.setStartNode(new Node(0,0));
//		chessSearch.setGoalNode(new Node(30,50));
//		chessSearch.astarSearch();
//		
//		chessSearch.setStartNode(new Node(1,1));
//		chessSearch.setGoalNode(new Node(1,1));
//		chessSearch.astarSearch();
		//randomly generate goal node and test
		for(int i = 0; i < 100; i++){
			chessSearch.setStartNode(new Node(0,0));
			chessSearch.setGoalNode(new Node((int)(Math.random()*chessSearch.getVisitedFlagOffset()), (int)(Math.random()*chessSearch.getVisitedFlagOffset())));
			startTime = System.currentTimeMillis();
			int solutionL = chessSearch.astarSearch();
			endTime = System.currentTimeMillis();
			System.out.println(chessSearch.getGoalNode().getX() + "," + chessSearch.getGoalNode().getY() + "\t" + solutionL + "\t" + expandedNodeCount + "\t" + generatedNodeCount + "\t" + (endTime - startTime)); 
		}
		
		for(int i = 0; i < 100; i++){
			chessSearch.setStartNode(new Node(0,0));
			chessSearch.setGoalNode(new Node((int)(-Math.random()*chessSearch.getVisitedFlagOffset()), (int)(-Math.random()*chessSearch.getVisitedFlagOffset())));
			startTime = System.currentTimeMillis();
			int solutionL = chessSearch.astarSearch();
			endTime = System.currentTimeMillis();
			System.out.println(chessSearch.getGoalNode().getX() + "," + chessSearch.getGoalNode().getY() + "\t" + solutionL + "\t" + expandedNodeCount + "\t" + generatedNodeCount + "\t" + (endTime - startTime)); 
		}
	}
	
	static class Node {
		Node parentNode;
		int x, y; //coordinates
		int g; //current cost
		int stepCount;//count of step
		
		public Node(int i, int j) {
			// TODO Auto-generated constructor stub
			this.x = i;
			this.y = j;
		}
		
		public Node(Node startNode) {
			// TODO Auto-generated constructor stub
			this.x = startNode.x;
			this.y = startNode.y;
		}
		
		public Node() {
			// TODO Auto-generated constructor stub
		}

		public int getStepCount() {
			return stepCount;
		}

		public void setStepCount(int stepCount) {
			this.stepCount = stepCount;
		}

		public Node getParentNode() {
			return parentNode;
		}
		
		public void setParentNode(Node parentNode) {
			this.parentNode = parentNode;
		}
		
		public int getX() {
			return x;
		}
		
		public void setX(int x) {
			this.x = x;
		}
		
		public int getY() {
			return y;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		public int getG() {
			return g;
		}
		
		public void setG(int g) {
			this.g = g;
		}
		
		public int getFValue(Node goalNode){
			return g + getHValue(goalNode);
		}
		
		public int getHValue(Node goalNode){
			return (Math.abs(x - goalNode.x) + Math.abs(y - goalNode.y)) / 3;
		}
	}
}
