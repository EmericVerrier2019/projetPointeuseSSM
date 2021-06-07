package projetPointeuseSSM;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	}
	/**
	 * a stub method which affect some days with hours to the current planning object
	 */
	public void PlanningStub() 
	{
		dayList = new ArrayList<Day>();
		Day monday = new Day(LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0)),LocalDateTime.of(LocalDate.now(),LocalTime.of(17, 0)),"Lundi");
		Day tuesday = new Day(LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0)),LocalDateTime.of(LocalDate.now(),LocalTime.of(17, 0)),"Mardi");
		Day wednesday = new Day(LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0)),LocalDateTime.of(LocalDate.now(),LocalTime.of(17, 0)),"Mercredi");
		Day thursday = new Day(LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0)),LocalDateTime.of(LocalDate.now(),LocalTime.of(17, 0)),"Jeudi");
		Day friday = new Day(LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0)),LocalDateTime.of(LocalDate.now(),LocalTime.of(17, 0)),"Vendredi");
		
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
	/**
	 * an accessor to add a new day by extending the DayList attribute
	 * @param dayToAdd
	 */
	public void addDay(Day dayToAdd) 
	{
		this.dayList.add(dayToAdd);
	}
	/**
	 * a method to set new day with a specified index, the new day replacing the old existing one
	 * @param indexDay
	 * @param dayNew
	 */
	public void setDay(int indexDay, Day dayNew) 
	{
		this.dayList.set(indexDay, dayNew);
	}
	/**
	 * override of the toString Method to get a string representing a Planning object
	 * @return String
	 */
	public String toString()
	{
		String resultString = new String("");
		for (Day day : dayList) {
			resultString = resultString + System.lineSeparator() + day.toString();
		}
		return resultString;
	}
	public Duration getWeeklyWorkedHours() 
	{
		Duration weeklyWorkedHours = Duration.ofMinutes(0);
		for (Day day : dayList) {
			weeklyWorkedHours = weeklyWorkedHours.plus(day.getDailyWorkedHours());
		}
		return weeklyWorkedHours;
	}
}
