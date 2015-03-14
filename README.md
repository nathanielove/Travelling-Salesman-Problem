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

```java
Pair pair = new Pair("NYC", "LA");
```

Please note that each city in the `Pair` is represented by a unique `String`.

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

###Solve a TSP with a built-in `Strategy`

As mentioned above, there are three built-in strategies - brute force, nearest neighbor, and farthest insertion. 

Supposed that we already have a `RoadMap roadMap` with 10 cities in the US, including `"NYC"`, `"LA"` and "`Chicago"`, the codes below demonstrate how to get an instance of each strategy.

```java
Strategy bruteForceStrategy = Strategy.bruteForce(roadMap);
Strategy nearestNeighborStrategy = Strategy.nearestNeighbor(roadMap, "NYC"); //to start from NYC
Strategy farthestInsertionStrategy = Strategy.farthestInseriton(roadMap, "NYC", "LA", "Chicago"); //to start with a triangle
```

We can get the solutions as

```java
Tour bruteForceSolution = bruteForceStrategy.solve();
Tour nearestNeighborSolution = nearestNeighborStrategy.solve();
Tour farthestInsertionSolution = farthestInsertionStrategy.solve();
```

Then, we can print the result in the console using

```java
System.out.println(bruteForceSolution);
```

###Define your own `Strategy`
The `Strategy` is an abstract class designed to be subclassed. To write your own strategy, simply `extends` this class, then include the following method in your class:

```java
public Tour solve(){
  ...
}
```

In the definiton of your own method, you will need to build a `Tour` object by using the `Tour.Builder` class. A simple example of the builder is,

```java
Tour.Builder builder = new Tour.Builder(roadMap);
builder.addPair("NYC", "LA");
builder.addPair("LA", "Chicago");
builder.addPair("Chicago", "NYC");
Tour tour = builder.build();
```

Of cource you can also concatenate all the `addPair()` and `build()` into one line.

There are a lot of other useful methods in the `Tour.Builder` class, like `public boolean covers(String city)`, `public int size()`, etc. Please refer to the javadoc for more information.

###Write/read your `RoadMap` into/from a text file
A `FileProcessor` can perform file read/write on a given file path. But internally, it relies on a `TextParser`, which provides methods to convert `RoadMap` to `String[]`, and vice versa. There are two built-in `FileProcessor`s, to handle distance matrices and pair lists respectively. The example below shows how to perform a read/write between a `RoadMap roadMap` and a text file at path `String path`.

```java
//text file in "distance matrix" format
RoadMap roadMap = FileProcessor.DISTANCE_MATRIX.read(path);
FileProcessor.DISTANCE_MATRIX.write(roadMap, path);
```
Or, 

```java
//text file in "pair list" format
RoadMap roadMap = FileProcessor.PAIR_LIST.read(path);
FileProcessor.PAIR_LIST.write(roadMap, path);
```

###Define your own file format
Firstly, you need to create a class that `implements` the `TextParser` interface.

Secondly, you need to define the following two methods in your class:

* `public String[] constructText(RoadMap roadMap)`
* `public RoadMap parseText(String[] lines)`
* 
Please note that each line in the text file cooresponds to an element in the `String[]`

Thirdly, create a `FileProcessor` with your own `TextParser myParser`

```java
FileProcessor myProcessor = new FileProcessor(myParser);
```

Finally, you can use the `read()` and `write()` methods in your `myProcessor`.

##More Resources
Please refer to the java doc for more information.
