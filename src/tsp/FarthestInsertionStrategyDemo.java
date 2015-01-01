/*
 * Copyright (c) 2015 SUN XIMENG (Nathaniel). All rights reserved.
 */

package tsp;



class FarthestInsertionStrategyDemo extends Strategy {
	
	private String a;
	private String b;
	private String c;

	public FarthestInsertionStrategyDemo(RoadMap rm, String a, String b, String c) {
		super(rm);
		rm.checkCity(a);
		rm.checkCity(b);
		rm.checkCity(c);
		if(a.equals(b) || a.equals(c) || b.equals(c)){
			throw new RuntimeException(a + ", " + b + ", " + c + " cannot form a triangle");
		}
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	private double distanceFrom(String city, Tour.Builder tb){
		System.out.println("<Start : distanceFrom(" + city + ")>");//TODO
		double max = 0;
		for(String s : tb.getCities()){
			double current = this.rm.getDistance(city, s);
			System.out.println(city + " --> " + s + " = " + current);//TODO
			if(current > max){
				max = current;
			}
		}
		System.out.println("Max dist = " + max);
		System.out.println("<End : distanceFrom(" + city + ")>");//TODO
		return max;
	}
	
	private String findFarthestCity(Tour.Builder tb){
		System.out.println("<<Start : findFarthest()>>"); //TODO
		String farthest = "";
		double maxDist = 0;
		for(String city : this.rm.getCitySet()){
			if(!tb.covers(city)){
				double currentDist = this.distanceFrom(city, tb);
				System.out.println(city + " : " + currentDist);//TODO
				if(currentDist > maxDist){
					maxDist = currentDist;
					farthest = city;
				}
			}
		}
		System.out.println("farthest = " + farthest);//TODO
		System.out.println("<<End : findFarthest()>>"); //TODO
		return farthest;
	}
	
	private void insertCity(String city, Tour.Builder tb){
		System.out.println("<<Start : insertCity(" + city + ")>>"); //TODO
		Pair target = null;
		double minIncr = Double.MAX_VALUE;
		for(Pair p : tb.getPairs()){
			String a = p.getSmaller();
			String b = p.getLarger();
			System.out.println("current pair" + p); //TODO
			System.out.println("current dist = " + this.rm.getDistance(p));//TODO
			System.out.println("after insertion = " + (this.rm.getDistance(city, a) + this.rm.getDistance(city, b)));//TODO
			double incr = this.rm.getDistance(city, a) + this.rm.getDistance(city, b) - this.rm.getDistance(p);
			System.out.println("incr = " + incr);//TODO
			if(Double.compare(incr, minIncr) < 0){
				target = p;
				minIncr = incr;
			}
		}
		System.out.println("city = " + city + "; target = " + target);
		tb.removePair(target);
		tb.addPair(target.getSmaller(), city);
		tb.addPair(target.getLarger(), city);
		System.out.println("<<End : insertCity(" + city + ")>>"); //TODO
	}

	@Override
	public Tour solve() {
		Tour.Builder tb  = new Tour.Builder(this.rm);
		tb.addPair(this.a, this.b);
		tb.addPair(this.a, this.c);
		tb.addPair(this.b, this.c);
		int i = 1;
		while(tb.size() < this.rm.size()){
			System.out.println("<<<Round No. " + i + ">>>");
			System.out.println("current in tour : " + tb.getPairs());
			String farthest = this.findFarthestCity(tb);
			this.insertCity(farthest, tb);
			++i;
		}
		return tb.build();
	}
	


}
