package factory;

public class WorkerTypeA extends Thread
{
	private ConveyorBelt conveyor_belt;
	private String worker_name;
	private int number_of_items;
	
	public WorkerTypeA(ConveyorBelt cb, int num_of_items)
	{
		conveyor_belt = cb;
		worker_name = "A";
		number_of_items = num_of_items;
	}
	
	public void run()
	{
		Item item;
		
		int number = 0;
		
		while(number_of_items > 0)
		{
			item = new Item();
			number++;
			item.setItemNumber(number);
			item.workOn();
			System.out.println("Worker " + worker_name + " is working on item " + item.getItemNumber() 
			+ " <handled by " + item.getHandledBy() + ">");
			ConveyorBelt.takeYourTime();
			conveyor_belt.enter(item, worker_name);
			number_of_items--;
		}
	}
}