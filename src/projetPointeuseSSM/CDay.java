package projetPointeuseSSM;

import java.time.LocalTime;

public class CDay {

	/**
	 * the three attributes of the CDayClass correspond to the moment in a day where an employee begin
	 * to work, when he ends his day and finally, the name of the day in the week. The goal of this class is to represent a day of work in
	 * an employee timetable
	 */
	private String sDAYdayName;
	private LocalTime lDAYtimeStart;
	private LocalTime lDAYtimeEnd;
	
	
	/**
	 * the default constructor of the CDay class, by default, the current time is used to define the beginning and the end of the employee day of work
	 * and the name of the day is willingly empty.
	 * @author Emeric Verrier
	 * @see LocalTime
	 */
	public CDay() 
	{
		lDAYtimeStart = LocalTime.now();
		lDAYtimeEnd = LocalTime.now();
		sDAYdayName = new String("");
		
	}
	/**
	 * @author Emeric Verrier
	 * @param timeStart
	 * @param timeEnd
	 * @param dayName
	 */
	public CDay(LocalTime timeStart, LocalTime timeEnd, String dayName) 
	{
		this.lDAYtimeStart = timeStart;
		this.lDAYtimeEnd = timeEnd;
		this.sDAYdayName = dayName;
	}
	/**
	 * basic setter to set the moment when the employee starts to work
	 * @author Emeric Verrier
	 * @param timeStart
	 */
	public void setlDAYTimeStart(LocalTime timeStart) 
	{
		this.lDAYtimeStart = timeStart;
	}
	/**
	 * basic setter to set the moment when the employee ends his day of work
	 * @author Emeric Verrier
	 * @param timeEnd
	 */
	public void setlDAYTimeEnd(LocalTime timeEnd) 
	{
		this.lDAYtimeEnd = timeEnd;
	}
	/**
	 * getter method to get the moment when the employee ends his day of work
	 * @author Emeric Verrier
	 * @return lDAYTimeEnd
	 */
	public LocalTime getlDAYTimeEnd()
	{
		return  this.lDAYtimeEnd;
	}
	/**
	 * @author Emeric Verrier
	 * @return getter method to get the moment when the employee begins his work.
	 */
	public LocalTime getlDAYTimeStart() 
	{
		return this.lDAYtimeStart;
	}
}
