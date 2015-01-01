/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package tsp;

import java.util.LinkedList;

class NearestNeighborStrategy extends Strategy {

	private String start;
	
	protected NearestNeighborStrategy(RoadMap rm, String start) {
		super(rm);
		this.rm.checkCity(start);
		this.start = start;
	}
	
	private String findNearestNeighbor(String city, LinkedList<String> unvisited){
		double shortest = Double.MAX_VALUE;
		String nearest = null;
		for(String s : unvisited){
			double current = this.rm.getDistance(city, s);
			if(Double.compare(current, shortest) < 0){
				nearest = s;
				shortest = current;
			}
		}
		return nearest;
	}

	@Override
	public Tour solve() {
		Tour.Builder tb = new Tour.Builder(this.rm);
		String current = this.start;
		LinkedList<String> unvisited = new LinkedList<String>(this.rm.getCitySet());
		unvisited.remove(current);
		while(!unvisited.isEmpty()){
			String nearest = this.findNearestNeighbor(current, unvisited);
			tb.addPair(current, nearest);
			current = nearest;
			unvisited.remove(current);
		}
		tb.addPair(current, start);
		return tb.build();
	}

}
