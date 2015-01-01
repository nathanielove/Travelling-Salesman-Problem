/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package tsp;

/**
 * Any class that implements the {@code TextParser} interface is able to 
 * convert/restore a {@code RoadMap} object to/from String[] objects in a specific format,
 * which can be further written to/read from text files
 * @author Nathaniel
 */


public interface TextParser {
	
		
	/**
	 * The default separator that 
	 */
	public static final String SEPARATOR = " ";
	
	/**
	 * A method to convert a {@code RoadMap} object to a String[] object
	 * which is ready to be written to a text file, as each String in the String[]
	 * object represents a line in the text file
	 * @param rm The {@code RoadMap} object to be converted
	 * @return The String[] object
	 */
	public String[] constructText(RoadMap rm);
	
	/**
	 * A method to read a {@code RoadMap} object from a String[] object,
	 * which is probably read from a text file, as each line in the text file
	 * is stored sequentially in the String[] object
	 * @param lines The String[] object to be processed
	 * @return A {@code RoadMap} object as represented in the String[] object
	 */
	public RoadMap parseText(String[] lines);

}
