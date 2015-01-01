/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package tsp;

/**
 * The {@code FileProcessor} class represent objects used to read/write {@code RoadMap} 
 * objects from/to text files.
 * @author Nathaniel
 *
 */
public class FileProcessor {
	
	private TextParser tfp;

	/**
	 * A built-in FileProcessor to process text files in distance table format
	 */
	public static final FileProcessor DISTANCE_TABLE = new FileProcessor(DistanceTableParser.INSTANCE);
	
	/**
	 * A built-in FileProcessor to process text files in pair list format
	 */
	public static final FileProcessor PAIR_LIST = new FileProcessor(PairListParser.INSTANCE);
	
	/**
	 * The only constructor
	 * @param tp 
	 * A {@code TextParser} object
	 */
	public FileProcessor(TextParser tp) {
		this.tfp = tp;
	}
	
	/**
	 * Read a {@code RoadMap} object from a text file
	 * @param path 
	 * The path of the text file
	 * @return The {@code RoadMap} object represented by the file
	 */
	public RoadMap read(String path){
		return this.tfp.parseText(FileIO.read(path));
	}
	
	/**
	 * Write a {@code RoadMap} object to a text file
	 * @param rm 
	 * A {@code RoadMap} object to write
	 * @param path 
	 * The path of the text file
	 */
	public void write(RoadMap rm, String path){
		FileIO.write(this.tfp.constructText(rm), path);
	}

}
