package test;

import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.time.LocalTime;
import org.junit.*;
import org.junit.rules.*;
import org.w3c.dom.events.EventException;

import projetPointeuseSSM.Employee;
import projetPointeuseSSM.Planning;

public class EmployeeSimpleTest {

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
	
	/*
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testUpdateWithoutPlanning(){
		System.out.println("Test update without planning");
	    Employee test = new Employee();
		thrown.expect(EventException.class);
	    thrown.expectMessage("The employe's planning is not set");
	    test.updateHoursToDo();
	}
	
	@Test
	public void testUpdateWithPlanning() {
		System.out.println("Test update with planning");
		Employee test = new Employee();
		Planning planning = new Planning();
		planning.PlanningStub();
		test.setPlanning(planning);
		test.updateHoursToDo();
		assertThat(test.getHoursToDo(),is(equalTo(Duration.ofHours(45))));
	}
	*/
}
