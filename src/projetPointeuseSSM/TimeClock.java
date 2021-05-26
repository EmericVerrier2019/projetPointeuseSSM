package projetPointeuseSSM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JButton;

import view.timeClockView.JPanelParametersView;

public class TimeClock {
	//variable
	private int PTSNombreEmployeeWork;
	private ArrayList<Employee> asDEPEmployeeWork;
	private int PTSNombreEmployeeHome;
	private ArrayList<Employee> asDEPEmployeeHome;
	private static int tcpPort;
	private static InetAddress ipAddress;
	private static Socket clientSocket;


	static public class ActionListenerParametersButton implements ActionListener{
		public ActionListenerParametersButton()
		{
			super();
			clientSocket = new Socket();
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton saveParametersButton = (JButton)e.getSource();
			JPanelParametersView parentOfButton = (JPanelParametersView) saveParametersButton.getParent();
			try 
			{
				ipAddress = Inet4Address.getByName(parentOfButton.getIpFieldContent());
				tcpPort = Integer.parseInt(parentOfButton.getPortFieldContent());
				if(clientSocket.isBound() == false) 
				{
					TimeClock.clientSocket.bind(new InetSocketAddress(ipAddress, tcpPort));
					System.out.println("la socket est liée à un couple adresse/port mais n'est pas encore connectée pour émettre");
					if(clientSocket.isConnected()) 
					{
						System.out.println("oui je suis connecté");
					}
				}
				else 
				{
					System.out.println("erreur, la socket est déjà attachée à un couple port/adresse");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

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
	public static void setClientSocket(int ArgPort, byte[] addressToSet) 
	{
		try {
			TimeClock.clientSocket = new Socket(InetAddress.getByAddress(addressToSet), ArgPort);
			TimeClock.tcpPort = ArgPort;
			TimeClock.ipAddress = InetAddress.getByAddress(addressToSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setClientSocket(Socket socketParam) 
	{
		TimeClock.clientSocket = socketParam;
		this.ipAddress = socketParam.getInetAddress();
		this.tcpPort = socketParam.getPort();
	}
}
