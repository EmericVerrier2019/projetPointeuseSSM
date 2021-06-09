package projetPointeuseSSM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.ArrayList;

import javax.swing.JButton;

import view.timeClockView.JPanelParametersView;
import view.timeClockView.JPanelTimeClockView;

public class TimeClockController {
	private TimeClockSender sender;
	public static ActionListenerParametersButton parametersButtonManager;
	public static ActionListenerTimeClockCheckInButton timeClockButtonManager;
	
	public TimeClockController()
	{
		try 
		{
			sender = new TimeClockSender(new InetSocketAddress(InetAddress.getLocalHost(),3125));
			parametersButtonManager = new ActionListenerParametersButton();
			timeClockButtonManager = new ActionListenerTimeClockCheckInButton();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void setSender(InetAddress address, int port) 
	{
		sender = new TimeClockSender(new InetSocketAddress(address, port));
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
					System.out.println("La socket n'est pas liée à  un couple port, adresse");
				}
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
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}

			

		}
	}
}
