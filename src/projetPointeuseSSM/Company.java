package projetPointeuseSSM;
import java.util.List;
import java.util.ArrayList;
import java.lang.NullPointerException;
import java.util.Collections;

public class Company {

	private String nom;
	private ArrayList<Department> listDepartments;
	private ArrayList<Employee> listEmployees;
	
	/***********************/
	/***** Constructor *****/
	/***********************/
	
	//Default constructor
	public Company(String argNom) {
		this.nom = argNom;
		this.listDepartments = new ArrayList<Department>();
		this.listEmployees = new ArrayList<Employee>();
	}
	public Company(String argNom, ArrayList<Department> argListD, ArrayList<Employee> argListE) {
		this.nom = argNom;
		this.listDepartments = argListD;
		this.listEmployees = argListE;
	}
	
	/******************/
	/***** Getter *****/
	/******************/
	
	public String getNom() {
		return nom;
	}
	public ArrayList<Employee> getListEmployees() {
		return listEmployees;
	}
	public ArrayList<Department> getListDepartment() {
		return listDepartments;
	}
	
	/******************/
	/***** Setter *****/
	/******************/
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setListDeprtment(ArrayList<Department> listDepartment) {
		this.listDepartments = listDepartment;
	}
	
	public void setListEmployees(ArrayList<Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}
	
	/*******************/
	/***** Methods *****/
	/*******************/
	
	/**
	 * @brief add a department in the list of departments
	 * @param department
	 */
	public void addDepartment(Department department) {
		try {
			if(department != null && !listDepartments.contains(department)) {
				department.setDepartmentNumber(listDepartments.size() + 1);
				listDepartments.add(department);
			}
		}
		catch(Exception e) {
			System.out.println("Le departement entre en parametre est null");
		}
	}
	
	/**
	 * @brief add an employee in the list of employees
	 * @param employee
	 * @param department
	 */
	public void addEmployee(Employee employee, Department department) {
		int idDepartment = department.getDepartmentNumber();
		for(Employee testEmployee : this.listEmployees)
		{
			if(testEmployee.getIdEmployee() == employee.getIdEmployee())
				throw new IllegalArgumentException("L'identifiant selectionné existe déja");
		}
		if(department != null && employee != null && !this.listEmployees.contains(employee)) {
			employee.setIdDepartment(idDepartment);
			this.listEmployees.add(employee);
		}
	}
	
	/**
	 * @brief remove a department in the list of departments
	 * @param department
	 */
	public void removeDepartment(Department department) {
		try {
			if(department != null) {
				this.listDepartments.remove(department);
			}
		}
		catch(Exception e) {
			System.out.println("Le departement entre en parametre est null");
		}
	}
	
	/**
	 * @brief remove an employee in the list of employees
	 * @param employee
	 */
	public void removeEmployee(Employee employee) {
		try {
			if(this.listEmployees != null) {
				this.listEmployees.remove(employee);
				Collections.sort(this.listEmployees);
			}
		}
		catch(Exception e) {
			System.out.println("Le departement entre en parametre est null");
		}
	}
		
	/**
	 * Permet de savoir si un employé existe et est présent dans l'entreprise
	 * @param idEmployee l'identifiant de l'employé 
	 * @return return l'indice de l'employé s'il existe, sinon on retourne -1
	 */
	public int existEmployee(int idEmployee) {
	
		for (int i = 0; i < listEmployees.size(); i++) {
			if (listEmployees.get(i).getIdEmployee() == idEmployee) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Permet de savoir si un département existe et est présent dans l'entreprise
	 * @param idDepartment l'identifiant du département que l'on veut vérifier
	 * @return return l'indice du departement s'il existe, sinon on retourne -1
	 */
	public int existDepartment(int idDepartment) {
	
		for (int i = 0; i < listDepartments.size(); i++) {
			if (listDepartments.get(i).getDepartmentNumber() == idDepartment) {
				return i;
			}
		}
		return -1;
	}
	
	
	
}
