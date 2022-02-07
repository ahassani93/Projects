package pathfinder;

public class Vertex <T extends Comparable<T>> implements Comparable<Vertex<T>>
{
	private T name;
	private T distance;
	private T predecessor;
	
	// constructors
	public Vertex()
	{
		name = distance = predecessor = null;
	}
	
	public Vertex (T one , T two, T three)
	{
		name = one;
		distance = two;
		predecessor = three;
	}
	
	// getters and setters
	public void setName (T other)
	{
		name = other;
	}
	
	public void setDistance (T other)
	{
		distance = other;
	}
	
	public void setPredecessor (T other)
	{
		predecessor = other;
	}
	
	public T getName ()
	{
		return name;
	}
	
	public T getDistance ()
	{
		return distance;
	}
	
	public T getPredecessor ()
	{
		return predecessor;
	}
	
	// return a string representing a vertex
	public String toString()
	{
		return "(" + name.toString() + "," + distance.toString() + "," + predecessor.toString() + ")";
	}
	
	// compares two vertices objects based on the value of their distance
	public int compareTo(Vertex<T> other)
	{
		if (distance.compareTo(other.distance) < 0)
			return -1;
		else if (distance.compareTo(other.distance) >0)
			return 1;
		else
			return 0;
	}
}