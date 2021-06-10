package projetPointeuseSSM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.ArrayList;

import javax.swing.JButton;

import view.timeClockView.JPanelParametersView;
import view.timeClockView.JPanelTimeClockView;
import view.timeClockView.TimeClockFrame;

public class TimeClockController {
	private TimeClockSender sender;
	public static ActionListenerParametersButton parametersButtonManager;
	public static ActionListenerTimeClockCheckInButton timeClockButtonManager;
	public static ActionListenerConnectButton connectButtonManager;
	private InetSocketAddress ipParameters;
	private TimeClockFrame frameTimeClock;

	public TimeClockController()
	{	
		//frameTimeClock = new TimeClockFrame();
		ipParameters = new InetSocketAddress(3125);
		sender = new TimeClockSender(new InetSocketAddress(ipParameters.getPort()));
		parametersButtonManager = new ActionListenerParametersButton();
		timeClockButtonManager = new ActionListenerTimeClockCheckInButton();
		connectButtonManager = new ActionListenerConnectButton();
	}
	public void setFrameTimeClock(TimeClockFrame tf) 
	{
		//frameTimeClock = tf;
		//tf.addWindowListener(new WindowComponent);
	}
	public TimeClockFrame getFrameTimeClock() 
	{
		return frameTimeClock;
	}
	public void setSender(InetAddress address, int port) 
	{
		ipParameters = new InetSocketAddress(address,port);
		sender = new TimeClockSender(ipParameters);
	}
	public TimeClockSender getSender() 
	{
		return sender;
	}
	public ActionListenerParametersButton getParametersButtonManager() {
		return parametersButtonManager;
	}
	public ActionListenerTimeClockCheckInButton getTimeClockButtonManager() {
		return timeClockButtonManager;
	}
	public class ActionListenerTimeClockCheckInButton implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton sentTicketButton = (JButton) e.getSource();
			JPanelTimeClockView panelTimeClock = (JPanelTimeClockView) sentTicketButton.getParent();
			if(!panelTimeClock.getUserIdFieldContent().isEmpty()) 
			{
				int parsedValue = Integer.parseInt(panelTimeClock.getUserIdFieldContent());
				Ticket ticketToSend = new Ticket(Day.roundTime(LocalDateTime.now()),parsedValue);
				if(sender.getClientSocket() != null) 
				{
					sender.sentTicket(ticketToSend);
				}
				else 
				{
					System.out.println("La socket n'est pas li�e � un couple port, adresse");
				}
			}	
		}
	}
	public class ActionListenerConnectButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton buttonConnect = (JButton) e.getSource();
			JPanelTimeClockView jPanelTimeClockView = (JPanelTimeClockView) buttonConnect.getParent();
			try 
			{
				TimeClockController.this.sender.getClientSocket().connect(ipParameters);
			}
			catch(IOException e2) 
			{
				e2.printStackTrace();
			}
			
		}
	}
	public class ActionListenerParametersButton implements ActionListener{
		public ActionListenerParametersButton()
		{
			super();
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton saveParametersButton = (JButton)e.getSource();
			JPanelParametersView panelParameters = (JPanelParametersView) saveParametersButton.getParent();
			try {
				sender = new TimeClockSender(new InetSocketAddress(InetAddress.getByName(panelParameters.getIpFieldContent()),Integer.parseInt(panelParameters.getPortFieldContent())));
				ipParameters = new InetSocketAddress(getSender().getClientSocket().getInetAddress(),getSender().getClientSocket().getPort());
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}



		}
	}
}
