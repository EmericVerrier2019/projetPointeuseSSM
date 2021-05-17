package projetPointeuseSSM;
import java.util.ArrayList;

public class TimeClock {
	//variable
	private int PTSNombreEmployeeWork;
	private ArrayList<Employee> asDEPEmployeeWork;
	private int PTSNombreEmployeeHome;
	private ArrayList<Employee> asDEPEmployeeHome;
	private int TCPort;
	
	
	/*****************
	 * Constructors
	 ****************/
	public TimeClock() {
		PTSNombreEmployeeWork = 0;
		asDEPEmployeeWork = new ArrayList<Employee>();
		PTSNombreEmployeeHome = 0;
		asDEPEmployeeHome = new ArrayList<Employee>();
	}
	public TimeClock(int ArgPort) {
		PTSNombreEmployeeWork = 0;
		asDEPEmployeeWork = new ArrayList<Employee>();
		PTSNombreEmployeeHome = 0;
		asDEPEmployeeHome = new ArrayList<Employee>();
		TCPort = ArgPort;
	}
	public TimeClock(int NombreEmployeeWork, int NombreEmployeeHome) {
		PTSNombreEmployeeWork = NombreEmployeeWork;
		asDEPEmployeeWork = new ArrayList<Employee>(NombreEmployeeWork);
		PTSNombreEmployeeHome = NombreEmployeeHome;
		asDEPEmployeeHome = new ArrayList<Employee>(NombreEmployeeHome);
	}
	public TimeClock(ArrayList<Employee> ArgEmployeeWork, ArrayList<Employee> ArgEmployeeHome) {
		if(this.PTSNombreEmployeeWork>0){
			for(int i = 0;i<=this.PTSNombreEmployeeWork;i++) {
				this.asDEPEmployeeWork.remove(i);
			}
		}
		if(this.PTSNombreEmployeeHome>0){
			for(int i = 0;i<=this.PTSNombreEmployeeHome;i++) {
				this.asDEPEmployeeHome.remove(i);
			}
		}
		PTSNombreEmployeeWork = ArgEmployeeWork.size();
		asDEPEmployeeWork = ArgEmployeeWork;
		PTSNombreEmployeeHome = ArgEmployeeHome.size();
		asDEPEmployeeHome = ArgEmployeeHome;
	}
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
	 * @brief Get the port of the Time clock
	 * @return int
	 */
	public int GetTimeClockPort() {
		return this.TCPort;
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
	/*
	 * @brief Set the port of time clock 
	 * @param ArgPort : a new port 
	 */
	public void SetTimeClockPort(int ArgPort){
		this.TCPort = ArgPort;
	}
}
