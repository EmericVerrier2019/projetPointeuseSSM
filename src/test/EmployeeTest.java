package test;

import projetPointeuseSSM.Employee;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;


import org.junit.*;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


public final class EmployeeTest {

	@BeforeClass
	public static void ShowBeforeRunTestClass(){
	    System.out.println("Start tests in CalculatorTest");
	}
	@AfterClass
	public static void ShowAfterAllTestsRunnedInClass(){
	    System.out.println("All tests have been runned in CalculatorTest");
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
	
	@Parameters(name = "{index}: testAddHourDo({0}) = {1}")
	public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {LocalTime.of(0,0,20),LocalTime.of(1,10,30)},
                {LocalTime.of(0,20,0),LocalTime.of(1,30,10)},
                {LocalTime.of(2,0,0),LocalTime.of(3,10,10)},
                {LocalTime.of(0,20,20),LocalTime.of(1,30,30)},
                {LocalTime.of(2,0,20),LocalTime.of(3,10,30)},
                {LocalTime.of(2,20,0),LocalTime.of(3,30,10)},
                {LocalTime.of(2,20,20),LocalTime.of(3,30,30)}
        });
    }
	
	@Test
    public void testAddHourDo() {
        Employee test = new Employee();
        //Set 1h 10m 10s, the time that employee has done 
        test.setHoursDo(LocalTime.of(1, 10, 10));
        test.addHourDo(hourAdded);
        assertEquals(expected, test.getHoursDo());
		
    }
	
	
	
	
}
