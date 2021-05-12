package projetPointeuseSSM;

import java.time.LocalTime;

public class CDay {

	private String dayName;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	
	public CDay() 
	{
		timeStart = LocalTime.now();
		timeEnd = LocalTime.now();
		dayName = new String("");
		
	}
	public CDay(LocalTime timeStart, LocalTime timeEnd, String dayName) 
	{
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.dayName = dayName;
	}
	
}
