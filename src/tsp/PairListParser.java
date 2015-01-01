/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package tsp;

import java.util.HashMap;
import java.util.Vector;

class PairListParser implements TextParser{
	
	protected static final TextParser INSTANCE = new PairListParser();

	private PairListParser() {
		
	}

	@Override
	public String[] constructText(RoadMap rm) {
		Vector<String> lines = new Vector<String>();
		String[] cities = rm.getCities();
		StringBuilder sb = new StringBuilder(cities.length * cities[0].length() * 2);
		for(String s : cities){
			sb.append(s);
			sb.append(SEPARATOR);
		}
		sb.deleteCharAt(sb.length() - 1);
		lines.add(sb.toString());
		for(int i = 0; i < cities.length - 1; ++i){
			for(int j = i + 1; j < cities.length; ++j){
				lines.add(cities[i] + SEPARATOR + cities[j] + SEPARATOR + rm.getDistance(cities[i], cities[j]));
			}
		}
		return lines.toArray(new String[lines.size()]);
	}

	@Override
	public RoadMap parseText(String[] lines) {
		String[] cities = lines[0].split(SEPARATOR);
		HashMap<Pair, Double> map = new HashMap<Pair, Double>();
		for(int i = 1; i < lines.length; ++i){
			String[] segs = lines[i].split(SEPARATOR);
			map.put(new Pair(segs[0], segs[1]), Double.parseDouble(segs[2]));
		}
		return new RoadMap(cities, map);
	}

}
