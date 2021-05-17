package projetPointeuseSSM;

import java.time.LocalTime;

public class Day {

	private String dayName;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	
	public Day() 
	{
		timeStart = LocalTime.now();
		timeEnd = LocalTime.now();
		dayName = new String("");
		
	}
	public Day(LocalTime timeStart, LocalTime timeEnd, String dayName) 
	{
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.dayName = dayName;
	}
	
}
