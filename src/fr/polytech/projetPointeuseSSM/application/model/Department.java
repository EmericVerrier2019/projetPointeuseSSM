/**
 * The class used to model a Depratment
 * 
 * This class is composed of 4 attributes :
 * -a Department name which is composed of type String
 * -a Department Number name which is composed of type int
 * -a EmployeeNumber which is composed of type int. That correspond to the number of employee for one department
 * -a List of Employee, that contain all the Employee of one department, which is composed of type Employee
 * 
 * The class has all methods setters and getters, which allow to update and get the different attributes of this one
 * 
 * @author Matthis
 * @version 1.0
 * 
 */



package fr.polytech.projetPointeuseSSM.application.model;
import java.util.ArrayList;


public class Department {

	
	//variables//
	
	private String DepartmentName;
	private int DepartmentNumber;
	private int EmployeeNumber; // number of employee in each department
	private ArrayList<Employee> EmployeeList;
	
	
	/***********************/
	/***** Constructor *****/
	/***********************/
	
	public Department() {
		this.DepartmentName = null;
		this.DepartmentNumber = 0;
		this.EmployeeNumber = 0;
		this.EmployeeList = new ArrayList<Employee>();
		}
	
	public Department(String DepartmentName, int DepartmentNumber, int EmployeeNumber, ArrayList<Employee> EmployeeList) {
		this.DepartmentName = DepartmentName;
		this.DepartmentNumber = DepartmentNumber;
		this.EmployeeNumber = EmployeeNumber;
		this.EmployeeList = new ArrayList<Employee>(EmployeeList);

	}
	
	/******************/
	/***** Setter *****/
	/******************/
	
	/**
	 * set the department name
	 * @param sDEPDepartementName 
	 */
	public void setDepartmentName(String Name) {
		this.DepartmentName = Name;
	}
	
	/**
	 * set the department name
	 * @param sDEPDepartementName 
	 */
	public void setDepartmentNumber(int Number) {
		this.DepartmentNumber = Number;
	}
	
	/**
	 * set the number of employee in a department
	 * @param EmployeeNumber 
	 */
	public void setEmployeeNumber(int Number) {
		this.EmployeeNumber = Number;
	}
	
	/**
	 * set the list of employee in a department
	 * @param Employee 
	 */
	public void setEmployee(ArrayList<Employee> Employee) {
		this.EmployeeList = Employee;
		this.EmployeeNumber = Employee.size();
	}
	
	
	/******************/
	/***** Getter *****/
	/******************/
	
	
	/**
	 * Get the department name
	 * @return sDEPDepartementName
	 */
	public String getDepartmentName() {
		return this.DepartmentName;
	}
	
	
	/**
	 * Get the department Number
	 * @return sDEPDepartementNumber
	 */
	public int getDepartmentNumber() {
		return this.DepartmentNumber;
	}
	
	/**
	 * get the number of employee in a department
	 * @return EmployeeNumber
	 */
	public int getEmployeeNumber() {
		return this.EmployeeNumber;
	}
	
	/**
	 * get the list of employee in a department
	 * @return Employee
	 */
	public ArrayList<Employee> getEmployee() {
		return this.EmployeeList;
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
		if(this.EmployeeList.contains(EmployeeToAdd)) {
			return false;
		}
		else {
			this.EmployeeList.add(EmployeeToAdd);
			this.EmployeeNumber += 1;
			return true;
		}
	}
	
	/**
	 * Delete an employee in the list 
	 * @param id of the employee
	 * @return true if the employee was delete, false if the employee wasn't in the list
	 */
	
	public boolean deleteEmployee (Employee EmployeeToDelete) {
		if(this.EmployeeList.remove(EmployeeToDelete)) {
			this.EmployeeNumber -=1 ;
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
	
	
}
