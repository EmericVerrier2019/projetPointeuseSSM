package test;

import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;
import org.w3c.dom.events.EventException;
import static org.hamcrest.core.StringContains.containsString;
import org.junit.Test;

import projetPointeuseSSM.Employee;

@RunWith(value = Parameterized.class)
public class EmployeeParametreTest {

	@BeforeClass
	public static void ShowBeforeRunTestClass(){
	    System.out.println("Start tests in EmployeeParametreTest");
	}
	@AfterClass
	public static void ShowAfterAllTestsRunnedInClass(){
	    System.out.println("All tests have been runned in EmployeeParametreTest");
	}
	@Before
	public void ShowBeforeRunEachTest(){
	    System.out.println("Start new test");
	}
	@After
	public void ShowAfterRunnedEachClass(){
	    System.out.println("End of the started test");
	}
	    
	
	//Test with few parameters
	@Parameter(value = 0)
	public Duration hourAdded;
	
	@Parameter(value = 1)
	public Duration expected;
	
	@Parameter(value = 2)
	public Duration initialHourDo;
	@Parameters(name = "{index}: testAddHourDo(addHoursDo({2}); addHoursDo({0});"+
						"  => employee.getHoursDo: {1}")
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Duration.ofMinutes(20),Duration.ofMinutes(90),Duration.ofMinutes(70)},
                {Duration.ofHours(2),Duration.ofMinutes((3*60+10)),Duration.ofMinutes(70)},
                {Duration.ofSeconds((50*60)),Duration.ofHours(2),Duration.ofMinutes(70)},
        });
    }
	
	/*
	@Test
    public void testAddHourDo() {
		System.out.println("Test add a hour that the employee has done");
		Employee employee = new Employee();
		employee.addHourWorked(initialHourDo);
        employee.addHourWorked(hourAdded);
        assertEquals(expected, employee.getHoursWorked());
		
    }
    */
	
}

