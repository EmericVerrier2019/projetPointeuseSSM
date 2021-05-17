package projetPointeuseSSM;
import java.util.ArrayList;

public class TimeClock {
	//variable 
	private ArrayList<Employee> asDEPEmployeeWork;
	private ArrayList<Employee> asDEPEmployeeHome;
	
	/*****************
	 * Constructors
	 ****************/
	
	/*****************
	 * Methods
	 ****************/
	
	/*
	 * @brief Get the list of workers 
	 * @return ArrayList<Employee>
	 */
	public ArrayList<Employee> GetListofEmployeeWork(){
		return asDEPEmployeeWork;
	}
	/*
	 * @brief Get the list of workers at home
	 * @return ArrayList<Employee>
	 */
	public ArrayList<Employee> GetListofEmployeeHome(){
		return asDEPEmployeeHome;
	}
	/*
	 * @brief Set the list of workers 
	 * @param ArgEmployeeWork : A list of workers
	 */
	public void SetListofEmployeeWork(ArrayList<Employee> ArgEmployeeWork){
		this.asDEPEmployeeWork = ArgEmployeeWork;
	}
	/*
	 * @brief Set the list of workers at home
	 * @param ArgEmployeeHome : A list of workers at home
	 */
	public void SetListofEmployeeHome(ArrayList<Employee> ArgEmployeeHome){
		this.asDEPEmployeeHome = ArgEmployeeHome;
	}
}
