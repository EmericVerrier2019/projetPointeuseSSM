package fr.polytech.projetPointeuseSSM.application.model;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Day {

	/**
	 * the three attributes of the CDayClass correspond to the moment in a day where an employee begin
	 * to work, when he ends his day and finally, the name of the day in the week. The goal of this class is to represent a day of work in
	 * an employee timetable. 
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
	 * @param timeStart heure de d�but
	 * @param timeEnd heure de fin
	 */
	public Day(LocalDateTime timeStart, LocalDateTime timeEnd) 
	{

		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		if(timeEnd != null) {
			setDayName(timeEnd);
		}	}
	
	/**
	 * Constructeur 
	 * @param timeStart heure de d�but
	 * @param timeEnd heure de fin
	 * @param dayName nom du jour
	 */
	public Day(LocalDateTime timeStart, LocalDateTime timeEnd,String dayName) 
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
		setDayName(timeStart);
	}
	/**
	 * basic setter to set the moment when the employee ends his day of work
	 * @param timeEnd
	 */
	public void setTimeEnd(LocalDateTime timeEnd) 
	{
		if(timeStart.getDayOfWeek().equals(timeEnd.getDayOfWeek())) {
			this.timeEnd = roundTime(timeEnd);
			setDayName(timeEnd);
		}else {
			throw new IllegalArgumentException("Les deux attributs timeEnd et timeStart "
					+ "doivent avoir la m�me date (jour)");
		}
		
	}
	
	private void setDayName(LocalDateTime time) {
		this.dayName = time.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE);
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
	

	static public LocalDateTime roundTime(LocalDateTime timeToRound) 
	{
		return timeToRound.minusMinutes(timeToRound.getMinute()%15);
	}
	/**
	 * Permet de recup�rer le bon format d'une partie de l'heure (exemple : 1 devient 01)
	 * @param unit Unit� � convertir
	 * @return L'unit� sous forme de texte
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
