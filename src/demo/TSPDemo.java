package demo;

import tsp.FileProcessor;
import tsp.RoadMap;
import tsp.Strategy;

public class TSPDemo {
	
	public static final String bruteforce = "src/files/bruteforce.txt";
	public static final String german = "src/files/german.txt";
	public static final String q1 = "src/files/q1.txt";
	public static final String q1seq = "src/files/q1seq.txt";
	public static final String seq = "src/files/seq.txt";
	public static final String matric = "src/files/matric.txt";
	public static final String tut8q1 = "src/files/tut8q1.txt";
	public static final String tut8q2 = "src/files/tut8q2.txt";
	public static final String tut8q2seq = "src/files/tut8q2seq.txt";
	public static final String q6 = "src/files/q6.txt";
	public static final String q9 = "src/files/q9.txt";


	private TSPDemo() {
		
	}

	public static void main(String[] args) {
		FileProcessor fpdt = FileProcessor.DISTANCE_TABLE;
		RoadMap rm = fpdt.read(german);
		System.out.println(Strategy.bruteForce(rm).solve());
		System.out.println(Strategy.nearestNeighbor(rm, "St").solve());
		System.out.println(Strategy.farthestInsertion(rm, "Mu", "Aa", "Fr").solve());
		//FarthestInsertionStrategyDemo fis = new FarthestInsertionStrategyDemo(rm, "Mu", "Aa", "Fr");
	}
	
}
