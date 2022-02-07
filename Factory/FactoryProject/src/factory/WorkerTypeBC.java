package factory;

public class WorkerTypeBC extends Thread
{
	private ConveyorBelt incoming_conveyor_belt;
	private ConveyorBelt outgoing_conveyor_belt;
	private String worker_name;
	private int number_of_items;
	
	public WorkerTypeBC(ConveyorBelt cb_in, ConveyorBelt cb_out, 
			String belt_in, String belt_out, int num_of_items)
	{
		incoming_conveyor_belt = cb_in;
		outgoing_conveyor_belt = cb_out;
		number_of_items = num_of_items;
		
		if(belt_in.equals("belt1"))
		{
			worker_name = "B";
		}
		else if(belt_in.equals("belt2"))
		{
			worker_name = "C";
		}
	}
	
	public void run()
	{
		Item item;
		
		while(number_of_items > 0)
		{
			item = (Item)incoming_conveyor_belt.remove(worker_name);
			item.workOn();
			System.out.println("Worker " + worker_name + " is working on item " + item.getItemNumber() 
			+ " <handled by " + item.getHandledBy() + ">");
			ConveyorBelt.takeYourTime();
			outgoing_conveyor_belt.enter(item, worker_name);
			number_of_items--;
		}
	}
}