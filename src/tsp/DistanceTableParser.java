/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package tsp;

import java.util.Vector;


class DistanceTableParser implements TextParser {
	
	protected static final TextParser INSTANCE = new DistanceTableParser();

	private DistanceTableParser() {

	}

	@Override
	public String[] constructText(RoadMap rm) {
		String[] cities = rm.getCities();
		Vector<String> lines = new Vector<String>(cities.length + 1);
		{
			StringBuilder sb = new StringBuilder(cities.length * cities[0].length() * 2);
			for(String s : cities){
				sb.append(s);
				sb.append(SEPARATOR);
			}
			sb.deleteCharAt(sb.length() - 1);
			lines.add(sb.toString());
		}
		
		for(int i = 0; i < cities.length; ++i){
			StringBuilder sb = new StringBuilder(cities.length * 25);
			sb.append(cities[i]);
			sb.append(SEPARATOR);
			for(int j = 0; j < cities.length; ++j){
				if(i == j){
					sb.append(0);
				}
				else{
					sb.append(rm.getDistance(cities[i], cities[j]));
				}
				sb.append(SEPARATOR);
			}
			sb.deleteCharAt(sb.length() - 1);
			lines.add(sb.toString());
		}
		return lines.toArray(new String[lines.size()]);
	}

	@Override
	public RoadMap parseText(String[] lines) {
		String[] cities = lines[0].split(SEPARATOR);
		double[][] dists = new double[cities.length][cities.length];
		for(int i = 1; i < lines.length; ++i){
			String[] data = lines[i].split(" ");
			for(int j = 1; j < data.length; ++j){
				dists[i-1][j-1] = Double.parseDouble(data[j]);
			}
		}
		return new RoadMap(cities, dists);
	}

}
