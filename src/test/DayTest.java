package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import projetPointeuseSSM.Day;

public class DayTest {
	private static Day testDay;
	@BeforeClass
	public static void ShowBeforeRunTestClass()
	{
		 testDay = new Day(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)),LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)),"monday");
		System.out.println("Start tests in DayTest");
	}
	
	@AfterClass
	public static void ShowAfterRunTestClass() 
	{
		System.out.println("All tests have been runned in DayTest");
	}
	@Before
	public void ShowBeforeRunEachTest(){
	    System.out.println("Start new test");
	}
	@After
	public void ShowAfterRunnedEachClass(){
	    System.out.println("End of the started test");
	}
	@Test
	public void testGetsDAYdayName() 
	{
		
		assertEquals("monday", this.testDay.getDayName());
	}
	@Test
	public void testGetsDailyWorkedHours() 
	{
		assertEquals(this.testDay.getDailyWorkedHours().compareTo(Duration.ofHours(7)),0);
		Day testDay = new Day(LocalDateTime.of(LocalDate.now(), LocalTime.of(8,0)),LocalDateTime.of(LocalDate.now(),LocalTime.of(17, 0)),"Lundi");
		assertEquals("Lundi", testDay.getDayName());
	}

}
