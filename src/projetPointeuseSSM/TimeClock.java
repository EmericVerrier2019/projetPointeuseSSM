package projetPointeuseSSM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.time.LocalTime;
import java.util.ArrayList;

public class TimeClock {
	//variable
	private int PTSNombreEmployeeWork;
	private ArrayList<Employee> asDEPEmployeeWork;
	private int PTSNombreEmployeeHome;
	private ArrayList<Employee> asDEPEmployeeHome;
	private int tcpPort;
	private InetAddress ipAddress;
	private Socket clientSocket;


	public static class ActionListenerParametersButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("ça marche");

		}
	}

	/*****************
	 * Constructors
	 ****************/
	public TimeClock() {
		PTSNombreEmployeeWork = 0;
		asDEPEmployeeWork = new ArrayList<Employee>();
		PTSNombreEmployeeHome = 0;
		asDEPEmployeeHome = new ArrayList<Employee>();
		try {
			ipAddress = Inet4Address.getLocalHost();
			clientSocket = new Socket();
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}

	}
	public TimeClock(int ArgPort, byte[] ipAddr) {
		PTSNombreEmployeeWork = 0;
		asDEPEmployeeWork = new ArrayList<Employee>();
		PTSNombreEmployeeHome = 0;
		asDEPEmployeeHome = new ArrayList<Employee>();
		tcpPort = ArgPort;
		try 
		{
			ipAddress = Inet4Address.getByAddress(ipAddr);
			clientSocket = new Socket(Inet4Address.getByAddress(ipAddr), tcpPort);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
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

	/*
	 * @brief Get the port of the Time clock
	 * @return int
	 */
	public int getTimeClockPort() {
		return this.tcpPort;
	}
	static public LocalTime RoundTime() 
	{
		return LocalTime.now().minusMinutes(LocalTime.now().getMinute()%15);
	}
	public void setClientSocket(int ArgPort, byte[] addressToSet) 
	{
		try {
			this.clientSocket = new Socket(InetAddress.getByAddress(addressToSet), ArgPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setClientSocket(Socket socketParam) 
	{
		this.clientSocket = socketParam;
		this.ipAddress = socketParam.getInetAddress();
		this.tcpPort = socketParam.getPort();
	}
}
