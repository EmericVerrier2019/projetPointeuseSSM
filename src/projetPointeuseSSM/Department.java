package projetPointeuseSSM;
import java.util.ArrayList;


public class Department {

	
	//variables
	
	private String sDEPDepartmentName;
	private int iDEPDepartmentNumber;
	private int iDEPEmployeeNumber; // number of employee in each department
	private ArrayList<Employee> asDEPEmployee;
	
	
	/***********************/
	/***** Constructor *****/
	/***********************/
	
	public Department() {
		this.sDEPDepartmentName = null;
		this.iDEPDepartmentNumber = 0;
		this.iDEPEmployeeNumber = 0;
		this.asDEPEmployee = new ArrayList<Employee>();
		}
	
	public Department(String DepartmentName, int DepartmentNumber, int EmployeeNumber, ArrayList<Employee> EmployeeList) {
		this.sDEPDepartmentName = DepartmentName;
		this.iDEPDepartmentNumber = DepartmentNumber;
		this.iDEPEmployeeNumber = EmployeeNumber;
		this.asDEPEmployee = new ArrayList<Employee>(EmployeeList);

	}
	
	/******************/
	/***** Setter *****/
	/******************/
	
	/**
	 * set the department name
	 * @param sDEPDepartementName 
	 */
	public void setDepartmentName(String Name) {
		this.sDEPDepartmentName = Name;
	}
	
	/**
	 * set the department name
	 * @param sDEPDepartementName 
	 */
	public void setDepartmentNumber(int Number) {
		this.iDEPDepartmentNumber = Number;
	}
	
	/**
	 * set the number of employee in a department
	 * @param iDEPEmployeeNumber 
	 */
	public void setEmployeeNumber(int Number) {
		this.iDEPEmployeeNumber = Number;
	}
	
	/**
	 * set the list of employee in a department
	 * @param asDEPEmployee 
	 */
	public void setAsDEPEmployee(ArrayList<Employee> asDEPEmployee) {
		this.asDEPEmployee = asDEPEmployee;
	}
	
	
	/******************/
	/***** Getter *****/
	/******************/
	
	
	/**
	 * Get the department name
	 * @return sDEPDepartementName
	 */
	public String getDepartmentName() {
		return this.sDEPDepartmentName;
	}
	
	
	/**
	 * Get the department Number
	 * @return sDEPDepartementNumber
	 */
	public int getDepartmentNumber() {
		return this.iDEPDepartmentNumber;
	}
	
	/**
	 * get the number of employee in a department
	 * @return iDEPEmployeeNumber
	 */
	public int getEmployeeNumber() {
		return this.iDEPEmployeeNumber;
	}
	
	/**
	 * get the list of employee in a department
	 * @return asDEPEmployee
	 */
	public ArrayList<Employee> getAsDEPEmployee() {
		return this.asDEPEmployee;
	}

	
	/******************/
	/***** Methods *****/
	/******************/
	
	
	/**
	 * Add an employee in the list 
	 * @param id of the employee
	 * @return true if the employee was add, false if the employee was already in the list
	 */
	public boolean addNewEmployee(Employee EmployeeToAdd) {
		if(this.asDEPEmployee.contains(EmployeeToAdd)) {
			return false;
		}
		else {
			this.asDEPEmployee.add(EmployeeToAdd);
			this.iDEPEmployeeNumber += 1;
			return true;
		}
	}
	
	
	
	
	
	
}
