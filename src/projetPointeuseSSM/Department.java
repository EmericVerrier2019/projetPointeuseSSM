package projetPointeuseSSM;
import java.util.ArrayList;


public class Department {

	
	//variables
	
	private String sDEPDepartementName;
	private int iDEPDepartementNumber;
	private int iDEPEmployeeNumber;
	private ArrayList<Employee> asDEPEmployee;
	
	//setters - getters
	
	/**
	 * Set the departement name
	 * @author Matthis
	 * @param sDEPDepartementName 
	 * @return void 
	 */
	public void setDepartementName(String Name) {
		sDEPDepartementName = Name;
	}
	
	/**
	 * Get the departement name
	 * @author Matthis
	 * @param /
	 * @return sDEPDepartementName
	 */
	public String getDepartementName() {
		return sDEPDepartementName;
	}
	
	
	
	
}