package projetPointeuseSSM;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * class Planning is designed to represent a week of labor of an employee.
 * It's basically a set of Day elements, which is accessible by specific getter and setter
 * @author Emeric Verrier
 *  
 */
public class Planning {
	private ArrayList<Day> dayList;

	/**
	 * default constructor of the Planning class. by default 5 days are added to the Planning of the employee
	 * with start and end time and the name of the day
	 */
	public Planning() 
	{
		dayList = new ArrayList<Day>();
		Day monday = new Day(LocalTime.of(8, 0, 0),LocalTime.of(17, 0, 0),"monday");
		Day tuesday = new Day(LocalTime.of(8, 0, 0),LocalTime.of(17, 0, 0),"tuesday");
		Day wednesday = new Day(LocalTime.of(8, 0, 0),LocalTime.of(17, 0, 0),"wednesday");
		Day thursday = new Day(LocalTime.of(8, 0, 0),LocalTime.of(17, 0, 0),"thursday");
		Day friday = new Day(LocalTime.of(8, 0, 0),LocalTime.of(17, 0, 0),"friday");
		
		dayList.add(monday);
		dayList.add(tuesday);
		dayList.add(wednesday);
		dayList.add(thursday);
		dayList.add(friday);
	}
	/**
	 * public getter to get the dayList
	 * @return dayList
	 */
	public ArrayList<Day> getDayList() 
	{
		return this.dayList;
	}
}
