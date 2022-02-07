package factory;

public class WorkerTypeD extends Thread
{
	private ConveyorBelt conveyor_belt;
	private String worker_name;
	private int number_of_items;
	
	public WorkerTypeD(ConveyorBelt cb, int num_of_items)
	{
		conveyor_belt = cb;
		worker_name = "D";
		number_of_items = num_of_items;
	}
	
	public void run()
	{
		Item item;
		
		while(number_of_items > 0)
		{
			item = (Item)conveyor_belt.remove(worker_name);
			item.workOn();
			System.out.println("Worker " + worker_name + " is working on item " + item.getItemNumber() 
			+ " <handled by " + item.getHandledBy() + ">");
			ConveyorBelt.takeYourTime();
			System.out.println("***************\nItem " + item.getItemNumber() + " is done\n***************");
			number_of_items--;
		}
	}
}