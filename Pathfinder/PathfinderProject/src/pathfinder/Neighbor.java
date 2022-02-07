package pathfinder;

public class Neighbor <T extends Comparable<T>> implements Comparable<Neighbor<T>>
{
	private Vertex<T> neighborVertex;
	private T neighborEdge;
	
	// constructors
	public Neighbor()
	{
		neighborVertex = null;
		neighborEdge = null;
	}
	
	public Neighbor (Vertex<T> one , T two)
	{
		neighborVertex = one;
		neighborEdge = two;
	}
	
	// getters and setters
	public void setNeighborVertex (Vertex<T> other)
	{
		neighborVertex = other;
	}
	
	public void setNeighborEdge (T other)
	{
		neighborEdge = other;
	}
	
	public Vertex<T> getNeighborVertex ()
	{
		return neighborVertex;
	}
	
	public T getNeighborEdge ()
	{
		return neighborEdge;
	}
	
	// return a string representing a neighbor
	public String toString()
	{
		return "(" + neighborVertex.toString() + "," + neighborEdge.toString() + ")";
	}
	
	// compares two neighbor objects based on the value of their edge
	public int compareTo(Neighbor<T> other)
	{
		if (neighborEdge.compareTo(other.neighborEdge) < 0)
			return -1;
		else if (neighborEdge.compareTo(other.neighborEdge) >0)
			return 1;
		else
			return 0;
	}
}