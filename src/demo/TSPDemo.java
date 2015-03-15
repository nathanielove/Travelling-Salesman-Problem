package demo;

import tsp.FileProcessor;
import tsp.RoadMap;
import tsp.Strategy;

public class TSPDemo {
	
	public static final String bruteforce = "src/samples/bruteforce.txt";
	public static final String german = "src/samples/german.txt";
	public static final String q1 = "src/samples/q1.txt";
	public static final String q1seq = "src/samples/q1seq.txt";
	public static final String seq = "src/samples/seq.txt";
	public static final String matric = "src/samples/matric.txt";
	public static final String tut8q1 = "src/samples/tut8q1.txt";
	public static final String tut8q2 = "src/samples/tut8q2.txt";
	public static final String tut8q2seq = "src/samples/tut8q2seq.txt";
	public static final String q6 = "src/samples/q6.txt";
	public static final String q9 = "src/samples/q9.txt";


	private TSPDemo() {
		
	}

	public static void main(String[] args) {
		FileProcessor fpdt = FileProcessor.DISTANCE_MATRIX;
		RoadMap rm = fpdt.read(german);
		System.out.println(Strategy.bruteForce(rm).solve());
		System.out.println(Strategy.nearestNeighbor(rm, "St").solve());
		System.out.println(Strategy.farthestInsertion(rm, "Mu", "Aa", "Fr").solve());
		//FarthestInsertionStrategyDemo fis = new FarthestInsertionStrategyDemo(rm, "Mu", "Aa", "Fr");
	}
	
}
