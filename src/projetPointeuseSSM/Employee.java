/**
 * The class used to model an employee
 * 
 * This class is composed of 7 attributes :
 * -a identifier which is composed of type int
 * -a last name which is composed of type String
 * -a first name which is composed of type String
 * -a department identifier which is composed of type int
 * -a planning which is composed of type CPlanning
 * -a number of theoretical hours that the employee is supposed to do. Which is composed of type LocalTime
 * -a number of hours completed that the employee have done. Which is composed of type LocalTime 
 * 
 * The class has all methods setters and getters, which allow to update and get the different attributes of this one
 * 
 * @author Quentin
 * @version 1.0
 * 
 */

package projetPointeuseSSM;


import java.time.*;

public class Employee {

	private int idEmployee;
	private String lastNameEmployee;
	private String firstNameEmployee;
	private int idDepartment;
	private Planning planningEmployee;
	private LocalTime hoursTheory;
	private LocalTime hoursDo;
	
	/***********************/
	/***** Constructor *****/
	/***********************/
	
	
	
	/******************/
	/***** Setter *****/
	/******************/
	
	
	/**
	 * Update the attribute idEmployee
	 * @param int newId, the new employee's id we want to set
	 */
	public void setIdEmployee(int newId) {
		this.idEmployee = newId;
	}
	
	/**
	 * Update the attribute lastNameEmployee
	 * @param String newLastName, the new employee's last name we want to set
	 */
	public void setLastNameEmployee(String newLastName) {
		this.lastNameEmployee = newLastName;
	}
	
	/**
	 * Update the attribute firstNameEmployee
	 * @param String newFirstName, the new employee's first name we want to set
	 */
	public void setFirstNameEmployee(String newFirstName) {
		this.firstNameEmployee = newFirstName;
	}
	
	/**
	 * Update the attribute idDepartment
	 * @param int newNumber, the new employee's id of department we want to set
	 */
	public void setIdDepartment(int newNumber) {
		this.idDepartment = newNumber;
	}
	
	/**
	 * Update the attribute planningEmployee
	 * @param Planning newPlanning, the new employee's planning we want to set
	 * @see Planning
	 */
	public void setPlanning(Planning newPlanning) {
		this.planningEmployee = newPlanning;
	}
	
	/**
	 * Update the attribute hoursTheory
	 * @param LocalTime newHour, the new employee's number of theoretical hours that we want to set
	 */
	public void setHoursTheory(LocalTime newHour) {
		this.hoursTheory = newHour;
	}
	
	/**
	 * Update the attribute hoursDo
	 * @param LocalTime newHour, the new employee's number of hours completed that we want to set
	 * 
	 */
	public void setHoursDo(LocalTime newHour) {
		this.hoursDo = newHour;
	}
	
	/******************/
	/***** Getter *****/
	/******************/
	
	/**
	 * Return the attribute idEmployee
	 * @return int idEmployee
	 */
	public int getIdEmployee() {
		return this.idEmployee;
	}
	
	/**
	 * Return the attribute lastNameEmployee
	 * @return String lastNameEmployee
	 */
	public String getLastName() {
		return this.lastNameEmployee;
	}
	
	/**
	 * Return the attribute firstNameEmployee
	 * @return String firstNameEmployee
	 */
	public String getFirstName() {
		return this.firstNameEmployee;
	}
	
	/**
	 * Return the attribute idDepartment
	 * @return int idDepartment
	 */
	public int getIdDepartment() {
		return this.idDepartment;
	}
	
	/**
	 * Return the attribute planningEmployee
	 * @return CPlanning planningEmployee
	 */
	public Planning getPlanning() {
		return this.planningEmployee;
	}
	
	/**
	 * Return the attribute hoursTheory
	 * @return LocalTime hoursTheory;
	 */
	public LocalTime getHoursTheory() {
		return this.hoursTheory;
	}
	
	/**
	 * Return the attribute hoursDo
	 * @return LocalTime hoursDo
	 */
	public LocalTime getHoursDo() {
		return this.hoursDo;
	}
}
