package factory;

public class ConveyorBelt
{
	public static final int WORK_TIME = 5;
	private static final int BELT_SIZE = 3;
	private int count;
	private int in;
	private int out;
	private Object[] conveyor_belt;
	
	public ConveyorBelt()
	{
		count = 0;
		in = 0;
		out = 0;
		conveyor_belt = new Object[BELT_SIZE];
	}
	
	public static void takeYourTime()
	{
		int sleepTime = (int)(WORK_TIME * Math.random());
		
		try
		{
			Thread.sleep(sleepTime * 1000);
		}
		catch(InterruptedException e)
		{
			System.out.println("Error: " + e);
		}
	}
	
	public synchronized void enter(Object item, String worker)
	{
		Item the_item = (Item) item;
		
		if(count == BELT_SIZE)
		{
			System.out.println("CAUTION: worker " + worker + " is waiting to put item " 
			+ the_item.getItemNumber() + " <handled by " + the_item.getHandledBy() + "> on the belt");
		}
		
		while(count == BELT_SIZE)
		{
			try
			{
				wait();
			}
			catch(InterruptedException e)
			{
				System.out.println("Error: " + e);
			}
		}
		
		System.out.println("Worker " + worker + " is placing item " + the_item.getItemNumber() 
		+ " <handled by " + the_item.getHandledBy() + "> on the belt");
		++count;
		conveyor_belt[in] = item;
		in = (in + 1) % BELT_SIZE;
		notify();
	}
	
	public synchronized Object remove(String worker)
	{
		Object item;
		
		if(count == 0)
		{
			System.out.println("CAUTION: worker " + worker + " is idle!");
		}
		
		while(count == 0)
		{
			try
			{
				wait();
			}
			catch(InterruptedException e)
			{
				System.out.println("Error: " + e);
			}
		}
		
		--count;
		item = conveyor_belt[out];
		Item the_item = (Item) item;
		System.out.println("Worker " + worker + " is retrieving item " + the_item.getItemNumber() 
		+ " <handled by " + the_item.getHandledBy() + "> from the belt");
		out = (out + 1) % BELT_SIZE;
		notify();
		
		return item;
	}
}