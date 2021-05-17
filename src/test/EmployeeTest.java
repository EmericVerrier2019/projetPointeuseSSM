package test;

import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;


import projetPointeuseSSM.Employee;

@RunWith(value = Parameterized.class)
public class EmployeeTest {

	@BeforeClass
	public static void ShowBeforeRunTestClass(){
	    System.out.println("Start tests in EmployeeTest");
	}
	@AfterClass
	public static void ShowAfterAllTestsRunnedInClass(){
	    System.out.println("All tests have been runned in EmployeeTest");
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
	public LocalTime hourAdded;
	
	@Parameter(value = 1)
	public LocalTime expected;
	
	@Parameter(value = 2)
	public LocalTime initialHourDo;
	@Parameters(name = "{index}: testAddHourDo(getHoursDo(): {2};addHoursDo({0});"+
						"  => employee.getHoursDo: {1}")
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {LocalTime.of(0,0,20),LocalTime.of(1,10,30),LocalTime.of(1, 10, 10)},
                {LocalTime.of(0,20,0),LocalTime.of(1,30,10),LocalTime.of(1, 10, 10)},
                {LocalTime.of(2,0,0),LocalTime.of(3,10,10),LocalTime.of(1, 10, 10)},
                {LocalTime.of(0,20,20),LocalTime.of(1,30,30),LocalTime.of(1, 10, 10)},
                {LocalTime.of(2,0,20),LocalTime.of(3,10,30),LocalTime.of(1, 10, 10)},
                {LocalTime.of(2,20,0),LocalTime.of(3,30,10),LocalTime.of(1, 10, 10)},
                {LocalTime.of(2,20,20),LocalTime.of(3,30,30),LocalTime.of(1, 10, 10)}
        });
    }
	
	@Test
    public void testAddHourDo() {
        Employee employee = new Employee();
        //Set 1h 10m 10s, the time that employee has done 
        employee.setHoursDo(initialHourDo);
        employee.addHourDo(hourAdded);
        assertEquals(expected, employee.getHoursDo());
		
    }
	

}
