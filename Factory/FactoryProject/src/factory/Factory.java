package factory;

import java.util.Scanner;

public class Factory
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		
		String number_of_items;
		
		System.out.println("Welcome to the factory! "
				+ "\nPlease enter the number of items you wish to be made (at most 50), "
				+ "\nand our four workers (A, B, C, D) will take it from there. Enjoy!");
		
		System.out.print("\nNumber of Items: ");
		number_of_items = keyboard.next();
		
		while(!number_of_items.matches("-?\\d+") 
				|| Integer.parseInt(number_of_items)<0 
				|| Integer.parseInt(number_of_items)>50)
		{
			System.out.println("\nInput is not valid. Try again!");
			System.out.print("\nNumber of Items: ");
			number_of_items = keyboard.next();
		}
		
		System.out.println("\n----------------------------------------------------------------------"
				+ "\n\nStart of Production!\n");
		
		int number = Integer.parseInt(number_of_items);
		
		ConveyorBelt belt1 = new ConveyorBelt();
		ConveyorBelt belt2 = new ConveyorBelt();
		ConveyorBelt belt3 = new ConveyorBelt();
		
		WorkerTypeA threadA = new WorkerTypeA(belt1,number);
		WorkerTypeBC threadB = new WorkerTypeBC(belt1,belt2,"belt1","belt2",number);
		WorkerTypeBC threadC = new WorkerTypeBC(belt2,belt3,"belt2","belt3",number);
		WorkerTypeD threadD = new WorkerTypeD(belt3,number);
		
		threadA.start();
		threadB.start();
		threadC.start();
		threadD.start();
		
		try
        {
			threadA.join();
			threadB.join();
			threadC.join();
			threadD.join();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
		
		System.out.println("\nEnd of production!"
				+ "\n\n----------------------------------------------------------------------\n"
				+ "\nThank you for using our factory. Enjoy your items. Goodbye!");
		keyboard.close();
		System.exit(0);
	}
}
