package factory;

public class Item
{
	private int item_number;
	private int number_of_times_handled;
	private String handled_by;
	
	public Item()
	{
		item_number = 0;
		number_of_times_handled = 0;
		handled_by = "";
	}
	
	public void workOn()
	{
		number_of_times_handled++;
		
		if(getTimesHandled() == 1)
		{
			handled_by = "A";
		}
		else if(getTimesHandled() == 2)
		{
			handled_by = "A,B";
		}
		else if(getTimesHandled() == 3)
		{
			handled_by = "A,B,C";
		}
		else if(getTimesHandled() == 4)
		{
			handled_by = "A,B,C,D";
		}
	}
	
	public int getItemNumber()
	{
		return item_number;
	}
	
	public void setItemNumber(int number)
	{
		item_number = number;
	}
	
	public int getTimesHandled()
	{
		return number_of_times_handled;
	}
	
	public String getHandledBy()
	{
		return handled_by;
	}
}