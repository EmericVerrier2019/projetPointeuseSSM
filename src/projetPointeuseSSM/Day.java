package projetPointeuseSSM;


import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class Day {

	/**
	 * the three attributes of the CDayClass correspond to the moment in a day where an employee begin
	 * to work, when he ends his day and finally, the name of the day in the week. The goal of this class is to represent a day of work in
	 * an employee timetable. 
	 * @author Emeric Verrier
	 */
	private String sDAYdayName;
	private LocalTime lDAYtimeStart;
	private LocalTime lDAYtimeEnd;
	
	
	/**
	 * the default constructor of the CDay class, by default, the current time is used to define the beginning and the end of the employee day of work
	 * and the name of the day is willingly empty.
	 * @see LocalTime
	 */
	public Day() 
	{
		lDAYtimeStart = LocalTime.now();
		lDAYtimeEnd = LocalTime.now();
		sDAYdayName = new String("");
		
	}
	/**
	 * @param timeStart
	 * @param timeEnd
	 * @param dayName
	 */
	public Day(LocalTime timeStart, LocalTime timeEnd, String dayName) 
	{
		this.lDAYtimeStart = timeStart;
		this.lDAYtimeEnd = timeEnd;
		this.sDAYdayName = dayName;
	}
	/**
	 * basic setter to set the moment when the employee starts to work
	 * @param timeStart
	 */
	public void setlDAYTimeStart(LocalTime timeStart) 
	{
		this.lDAYtimeStart = roundTime(timeStart);
	}
	/**
	 * basic setter to set the moment when the employee ends his day of work
	 * @param timeEnd
	 */
	public void setlDAYTimeEnd(LocalTime timeEnd) 
	{
		this.lDAYtimeEnd = roundTime(timeEnd);
	}
	/**
	 * getter method to get the moment when the employee ends his day of work
	 * @return lDAYTimeEnd
	 */
	public LocalTime getlDAYTimeEnd()
	{
		return  this.lDAYtimeEnd;
	}
	
	
	/**
	 * getter method to get the moment when the employee begins his work.
	 * @return lDayTimeStart 
	 */
	public LocalTime getlDAYTimeStart() 
	{
		return this.lDAYtimeStart;
	}
	/**
	 * getter method to get the name of the day
	 * @return sDAYdayName
	 * 
	 */
	public String getsDAYdayName() 
	{
		return this.sDAYdayName;
	}
	/**
	 * override of the toString method to get a string representing a day object
	 * @return String
	 */
	public String toString() 
	{
		return(lDAYtimeStart.toString() + " "+ lDAYtimeEnd.toString() + " " + sDAYdayName.toString());
	}
	/**
	 * method to get the number of hours which separate the end of the day from the beginning (end - start);
	 * @return
	 * */
	public Duration getDailyWorkedHours() 
	{
		long dailyWorkedHours = lDAYtimeStart.until(lDAYtimeEnd,ChronoUnit.MINUTES);
		Duration hoursWorked = Duration.of(dailyWorkedHours,ChronoUnit.MINUTES);
		return hoursWorked;
	}
	
	/**
	 * Permet d'arrondir une heure au quart d'heure supérieur
	 * @param time l'heure que l'on veut arrondir
	 * @return l'heure arroudie au quart d'heure supérieur
	 */
	public static LocalTime roundTime(LocalTime time) {
		if (time.getMinute() < 8) {
			time = time.minusMinutes(time.getMinute());
		} else if (time.getMinute() < 16) {
			time = time.plusMinutes(15 - time.getMinute());
		} else if (time.getMinute() < 23) {
			time = time.minusMinutes(time.getMinute() - 15);
		} else if (time.getMinute() < 31) {
			time = time.plusMinutes(30 - time.getMinute());
		} else if (time.getMinute() < 38) {
			time = time.minusMinutes(time.getMinute() - 30);
		} else if (time.getMinute() < 46) {
			time = time.plusMinutes(45 - time.getMinute());
		} else if (time.getMinute() < 53) {
			time = time.minusMinutes(time.getMinute() - 45);
		} else {
			time = time.plusMinutes(60 - time.getMinute());
		}
		return time;
	}
	
	/**
	 * Permet de recupérer le bon format d'une partie de l'heure (exemple : 1 devient 01)
	 * @param unit Unité à convertir
	 * @return L'unité sous forme de texte
	 */
	public static String convertUnit(int unit) {
		
		String r = Integer.toString(unit);
		
		if(unit < 10) {
			r = "0" + r;
		}
		
		return r;
	}


}
