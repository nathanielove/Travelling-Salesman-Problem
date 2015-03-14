# Travelling Salesman Problem (TSP) Solver
##Introduciton
This is an extensible API to model TSP in Java, and to solve it with build-in strategies, or extended strategies written by users. Currently built-in strategies are:

* Brute force
* Nearest neighbor
* Farthest insertion

##Classes at a Glance

* **`Pair`**
  - A pair of cities
  - The order doesn't matter
* **`RoadMap`**: the logical map in a TSP, including
  - City names 
  - Distances between any two cities
* **`Tour`**: a solution of a TSP
  - Starting from & returning to a same city
  - Traversing other cities exactly once
* **`Strategy`**
  - An abstract class designed to be extended
  - Given a `RoadMap` object, the `solve()` methods returns a `Tour` as solution
* **`TextParser`**
  - An interface
  - Provides methods to convert `RoadMap` to `String[]`, and vice versa
  - Hence, `RoadMap` can be stored into a text file with pre-defined or user-defined format
* **`FileProcessor`**
  - Uses `TextParser` objects
  - Read/write `RoadMap` objects from/to text files at given paths
  
##User Guide
###Create a `Pair` of cities

Step 1: name the two cities with distinct strings

```java
String cityA = "NYC";
String cityB = "LA";
```

Step 2: create a `Pair` using `new`

```java
Pair pair = new Pair(cityA, cityB);
```

###Create a `RoadMap`
There are two approaches available. 

First, suppose you already have
* `String[] cities` - all the  names of the cities
* `double[][] distances` - a distance matrix

Then a `RoadMap` can be created by

```java
RoadMap roadMap = new RoadMap(cities, distances);
```

However, a distance matrix contains redundant data, as it stores twice infomation as needed. To avoid this, sometimes we use a `java.util.Map<Pair, Double> pairs` to store the distances between any two cities. In this case, we can create a `RoadMap` by

```java
RoadMap roadMap = new RoadMap(cities, pairs); //'cities' is same as above
```

###Define a `Strategy`


