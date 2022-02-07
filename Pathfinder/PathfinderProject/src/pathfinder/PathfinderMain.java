package pathfinder;

import java.io.*;
import java.util.*;

public class PathfinderMain
{
	private static Scanner keyboard;
	private static Scanner locationsFileScanner1;
	private static Scanner matrixFileScanner1;
	private static Scanner matrixFileScanner2;
	private static Scanner matrixFileScanner3;
	
	private static File locationCodesFile;
	private static File distanceMatrixFile;
	
	private static int numOfVertices;
	
	private static String startVStr;
	private static String endVStr;
	
	private static String[] allLocationsArray;
	
	public static Vertex<String>[] allVertices;
	
	private static ArrayList<Vertex<String>> unvisitedArrayList;
	
	private static ArrayList<Neighbor<String>> currentVertexNeighborsArrayList;
	
	private static void intro()
	{
		//inputs, initialization, and intro
		locationsFileScanner1 = null;
		matrixFileScanner1 = null;
		matrixFileScanner2 = null;
		matrixFileScanner3 = null;
		
		locationCodesFile = new File("location_codes");
		distanceMatrixFile = new File("distance_matrix");
		
		try
		{
			locationsFileScanner1 = new Scanner (new FileInputStream(locationCodesFile));
		}
		catch (IOException e)
		{
			System.out.println ("Locations File Error: " + e.getMessage());
			System.exit(0);
		}
		
		String theLine = locationsFileScanner1.nextLine();
		allLocationsArray = theLine.split(" ");
		numOfVertices = allLocationsArray.length;
		
		try
		{
			matrixFileScanner1 = new Scanner (new FileInputStream(distanceMatrixFile));
		}
		catch (IOException e)
		{
			System.out.println ("Matrix File Error: " + e.getMessage());
			System.exit(0);
		}
		
		//input error checking
		int ind = 0;
		
		while(matrixFileScanner1.hasNextLine())
		{
			String line = matrixFileScanner1.nextLine();
			String[] lineArray = line.split(" ");
			
			if(numOfVertices!=lineArray.length)
			{
				System.out.println("The number of columns in \"distance_matrix\" file on line " + (ind+1) + " does not match the "
						+ "\nnumber of locations in \"location_codes\" file. Please correct the input files and try again. "
						+ "\n\nThank you for using Pathfinder. Goodbye!");
				System.exit(0);
			}
			
			for(int i=0; i<lineArray.length; i++)
			{
				if(!lineArray[i].matches("-?\\d+") || Integer.parseInt(lineArray[i])<0 || Integer.parseInt(lineArray[i])>999999)
				{
					System.out.println("The input in \"distance_matrix\" file on line " + (ind+1) + ", column " + (i+1) + " is not "
							+ "\nvalid. Please correct the input files and try again. "
							+ "\n\nThank you for using Pathfinder. Goodbye!");
					System.exit(0);
				}
			}
			ind++;
		}
		
		if(numOfVertices!=ind)
		{
			System.out.println("The number of lines in \"distance_matrix\" file does not match the number of locations in "
					+ "\n\"location_codes\" file. Please correct the input files and try again. "
					+ "\n\nThank you for using Pathfinder. Goodbye!");
			System.exit(0);
		}
		
		//introduction
		System.out.println("Welcome to Pathfinder!"
				+ "\n\nThe matrix below from the \"distance_matrix\" file represents the distances (in miles) among the set of "
				+ "\nlocations in the \"location_codes\" file. You may modify these input files as you desire. 999999 means a "
				+ "\npath does not exist between the two locations. Any other number means a path exists, and it represents the "
				+ "\ndistance between the two locations.");
		System.out.print("\n       ");
		
		for (int i=0; i<numOfVertices; i++)
		{
			if(i==numOfVertices-1)
			{
				System.out.print(allLocationsArray[i] + "\n\n");
				break;
			}
			System.out.printf("%-7s", allLocationsArray[i]);
		}
		
		try
		{
			matrixFileScanner2 = new Scanner (new FileInputStream(distanceMatrixFile));
		}
		catch (IOException e)
		{
			System.out.println ("Matrix File Error: " + e.getMessage());
			System.exit(0);
		}
		
		int index = 0;
		
		while(matrixFileScanner2.hasNextLine())
		{
			String line = matrixFileScanner2.nextLine();
			String[] lineArray = line.split(" ");
			System.out.printf("%-7s", allLocationsArray[index]);
			
			for(int i=0; i<lineArray.length; i++)
			{
				System.out.printf("%-7s", lineArray[i]);
			}
			System.out.println();
			index++;
		}
		
		System.out.println("\nSelect a start and end location from above, "
				+ "and I will show you the shortest path and its distance.");
	}
	
	private static void pathfinderAlgorithm()
	{
		System.out.print("\nStart Location: ");
		startVStr = keyboard.next();
		
		//input error checking
		while(!Arrays.asList(allLocationsArray).contains(startVStr))
		{
			System.out.println("\n" + startVStr + " was not found in \"location_codes\" file. Try again!");
			System.out.print("\nStart Location: ");
			startVStr = keyboard.next();
		}
		
		System.out.print("\nEnd Location: ");
		endVStr = keyboard.next();
		
		//input error checking
		while(!Arrays.asList(allLocationsArray).contains(endVStr))
		{
			System.out.println("\n" + endVStr + " was not found in \"location_codes\" file. Try again!");
			System.out.print("\nEnd Location: ");
			endVStr = keyboard.next();
		}
		
		//array of all vertices(triplets). Each vertex(triplet) has data members name, distance, and predecessor.
		allVertices = new Vertex[numOfVertices];
		
		//unvisited array list to initialize with allVertices and then process later in the algorithm
		unvisitedArrayList = new ArrayList<>();
		
		//Dijkstra’s algorithm starts
		for(int i=0; i<numOfVertices; i++)
		{
			if(allLocationsArray[i].equals(startVStr))
			{
				allVertices[i] = new Vertex(allLocationsArray[i],0, "null");
			}
			else
			{
				allVertices[i] = new Vertex(allLocationsArray[i],999999, "null");
			}
			unvisitedArrayList.add(allVertices[i]);
		}
		
		while(!unvisitedArrayList.isEmpty())
		{
			//finding and removing the element with the smallest distance in unvisitedArrayList and naming it currentVertex
			unvisitedArrayList.sort((o1, o2)-> Integer.compare(Integer.parseInt(String.valueOf(o1.getDistance()))
					,(Integer.parseInt(String.valueOf(o2.getDistance())))));
			
	        Vertex<String> currentVertex = unvisitedArrayList.remove(0);
			
			//finding the original index of currentVertex from allVertices to use when processing the matrix file
			int matrixIndexOfCurrentVertex = 0;
			
			for (int i=0; i<allVertices.length; i++)
			{
				if (allVertices[i].getName().equals(currentVertex.getName()))
				{
					matrixIndexOfCurrentVertex = i;
				}
			}
			
			//building currentVertexNeighborsArrayList from adj vertices of currentVertex in the matrixFile
			currentVertexNeighborsArrayList = new ArrayList<>();
			
			try
			{
				matrixFileScanner3 = new Scanner (new FileInputStream(distanceMatrixFile));
			}
			catch (IOException e)
			{
				System.out.println ("Matrix File Error: " + e.getMessage());
				System.exit(0);
			}
			
			int row = 0;
			
			while(matrixFileScanner3.hasNextLine())
			{
				String line = matrixFileScanner3.nextLine();
				String[] lineArray = line.split(" ");
				
				if(row == matrixIndexOfCurrentVertex)
				{
					for(int j=0; j<lineArray.length; j++)
					{
						if(Integer.parseInt(lineArray[j])>0 && Integer.parseInt(lineArray[j])<999999)
						{
							for(int y=0; y<unvisitedArrayList.size(); y++)
							{
								if(unvisitedArrayList.get(y).getName().equals(allVertices[j].getName()))
								{
									Neighbor<String> aNeighbor = new Neighbor(allVertices[j],lineArray[j]);
									currentVertexNeighborsArrayList.add(aNeighbor);
								}
							}
						}
					}
					break;
				}
				row++;
			}
			
			//continuation of algorithm
			for(int i=0; i<currentVertexNeighborsArrayList.size(); i++)
			{
				int currentVertexDistance = Integer.parseInt(String.valueOf(currentVertex.getDistance()));
				int neighborEdgeWeight = Integer.parseInt(String.valueOf(currentVertexNeighborsArrayList.get(i).getNeighborEdge()));
				int alternativePathDistance = currentVertexDistance + neighborEdgeWeight;
				
				if(alternativePathDistance<Integer.parseInt(String.valueOf(currentVertexNeighborsArrayList.get(i).getNeighborVertex().getDistance())))
				{
					//wherever we update an element of currentVertexNeighborsArrayList, we have to update that element in allVertices and unvisitedArrayList
					currentVertexNeighborsArrayList.get(i).getNeighborVertex().setDistance(String.valueOf(alternativePathDistance));
					currentVertexNeighborsArrayList.get(i).getNeighborVertex().setPredecessor(currentVertex.getName());
					
					for(int x=0; x<allVertices.length; x++)
					{
						if(allVertices[x].getName().equals(currentVertexNeighborsArrayList.get(i).getNeighborVertex().getName()))
						{
							allVertices[x].setDistance(String.valueOf(alternativePathDistance));
							allVertices[x].setPredecessor(currentVertex.getName());
						}
					}
					
					for(int y=0; y<unvisitedArrayList.size(); y++)
					{
						if(unvisitedArrayList.get(y).getName().equals(currentVertexNeighborsArrayList.get(i).getNeighborVertex().getName()))
						{
							unvisitedArrayList.get(y).setDistance(String.valueOf(alternativePathDistance));
							unvisitedArrayList.get(y).setPredecessor(currentVertex.getName());
						}
					}
				}
			}
			//clearing the currentVertexNeighborsArrayList for the next vertex
			currentVertexNeighborsArrayList.clear();
		}
	}
	
	private static void outputs()
	{
		//processing and printing the outputs
		for(int z=0; z<allVertices.length; z++)
		{
			if(allVertices[z].getName().equals(endVStr))
			{
				Vertex<String> lastVertex = allVertices[z];
				String shortestPath = lastVertex.getName();
				
				while(!lastVertex.getPredecessor().equals("null"))
				{
					shortestPath = lastVertex.getPredecessor() + " -> " + shortestPath;
					
					for(int i=0; i<allVertices.length; i++)
					{
						if(allVertices[i].getName().equals(lastVertex.getPredecessor()))
						{
							lastVertex = allVertices[i];
							break;
						}
					}
				}
				System.out.println("\nThe shortest path between " + startVStr + " and " + endVStr + " is:");
				System.out.println(shortestPath);
				System.out.println("The distance of this path is " + String.valueOf(allVertices[z].getDistance()) + " miles.");
			}
		}
	}
	
	public static void main(String[] args)
	{
		keyboard = new Scanner(System.in);
		
		intro();
		pathfinderAlgorithm();
		outputs();
		System.out.print("\nWould you like another path (y,n)? ");
		String answer = keyboard.next();
		
		//input error checking
		while(!answer.equals("y") && !answer.equals("n"))
		{
			System.out.println("\nInput is not valid. Select either y or n!");
			System.out.print("\nWould you like another path (y,n)? ");
			answer = keyboard.next();
		}
		
		while(answer.equals("y"))
		{
			pathfinderAlgorithm();
			outputs();
			System.out.print("\nWould you like another path (y,n)? ");
			answer = keyboard.next();
			
			//input error checking
			while(!answer.equals("y") && !answer.equals("n"))
			{
				System.out.println("\nInput is not valid. Select either y or n!");
				System.out.print("\nWould you like another path (y,n)? ");
				answer = keyboard.next();
			}
		}
		System.out.println("\nThank you for using Pathfinder. Goodbye!");
		
		//closing the scanner and ending program
		keyboard.close();
		System.exit(0);
	}
}
