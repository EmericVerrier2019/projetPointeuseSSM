package projetPointeuseSSM;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class Day {

	/**
	 * the three attributes of the CDayClass correspond to the moment in a day where an employee begin
	 * to work, when he ends his day and finally, the name of the day in the week. The goal of this class is to represent a day of work in
	 * an employee timetable. 
	 * @author Emeric Verrier
	 */
	private String dayName;
	private LocalDateTime timeStart;
	private LocalDateTime timeEnd;
	
	
	/**
	 * the default constructor of the CDay class, by default, the current time is used to define the beginning and the end of the employee day of work
	 * and the name of the day is willingly empty.
	 * @see LocalDateTime
	 */
	public Day() 
	{
		timeStart = null;
		timeEnd = null;
		dayName = new String("");
	}
	
	/**
	 * Constructeur 
	 * @param timeStart heure de début
	 * @param timeEnd heure de fin
	 * @param dayName nom du jour
	 */
	public Day(LocalDateTime timeStart, LocalDateTime timeEnd, String dayName) 
	{
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.dayName = dayName;
	}
	
	/**
	 * Constructeur de recopie
	 * @param otherDay le jour que l'on veut recopie
	 */
	public Day(Day otherDay) {
		this.timeEnd = otherDay.timeEnd;
		this.timeStart = otherDay.timeStart;
		this.dayName = otherDay.dayName;	
	}

	
	/**
	 * basic setter to set the moment when the employee starts to work
	 * @param timeStart
	 */
	public void setTimeStart(LocalDateTime timeStart) 
	{
		this.timeStart = roundTime(timeStart);
	}
	/**
	 * basic setter to set the moment when the employee ends his day of work
	 * @param timeEnd
	 */
	public void setTimeEnd(LocalDateTime timeEnd) 
	{
		this.timeEnd = roundTime(timeEnd);
	}
	/**
	 * getter method to get the moment when the employee ends his day of work
	 * @return timeEnd
	 */
	public LocalDateTime getTimeEnd()
	{
		return  this.timeEnd;
	}
	
	
	/**
	 * getter method to get the moment when the employee begins his work.
	 * @return timeStart 
	 */
	public LocalDateTime getTimeStart() 
	{
		return this.timeStart;
	}
	/**
	 * getter method to get the name of the day
	 * @return dayName
	 * 
	 */
	public String getDayName() 
	{
		return this.dayName;
	}
	/**
	 * override of the toString method to get a string representing a day object
	 * @return String
	 */
	public String toString() 
	{
		return(timeStart.toString() + " "+ timeEnd.toString() + " " + dayName.toString());
	}
	/**
	 * method to get the number of hours which separate the end of the day from the beginning (end - start);
	 * @return
	 * */
	public Duration getDailyWorkedHours() 
	{
		long dailyWorkedHours = timeStart.until(timeEnd,ChronoUnit.MINUTES);
		Duration hoursWorked = Duration.of(dailyWorkedHours,ChronoUnit.MINUTES);
		return hoursWorked;
	}
	
	/**
	 * Permet d'arrondir une heure au quart d'heure supérieur
	 * @param time l'heure que l'on veut arrondir
	 * @return l'heure arroudie au quart d'heure supérieur
	 */
	public static LocalDateTime roundTime(LocalDateTime time) {
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

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Day other = (Day) obj;
		if (dayName == null) {
			if (other.dayName != null)
				return false;
		} else if (!dayName.equals(other.dayName))
			return false;
		if (timeEnd == null) {
			if (other.timeEnd != null)
				return false;
		} else if (!timeEnd.equals(other.timeEnd))
			return false;
		if (timeStart == null) {
			if (other.timeStart != null)
				return false;
		} else if (!timeStart.equals(other.timeStart))
			return false;
		return true;
	}

}
