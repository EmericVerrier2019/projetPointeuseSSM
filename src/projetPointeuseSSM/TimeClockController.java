package projetPointeuseSSM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.timeClockView.JPanelParametersView;
import view.timeClockView.JPanelTimeClockView;
import view.timeClockView.TimeClockFrame;

public class TimeClockController {
	private static TimeClockSender sender;
	private static InetSocketAddress ipParameters;
	public static ActionListenerParametersButton parametersButtonManager;
	public static ActionListenerTimeClockCheckInButton timeClockButtonManager;
	public static ActionListenerConnectButton connectButtonManager;
	static private TimeClockFrame frameTimeClock;

	public TimeClockController()throws IOException
	{	
		frameTimeClock = null;
		ipParameters = new InetSocketAddress(InetAddress.getLocalHost(),3125);
		sender = new TimeClockSender(ipParameters);
		parametersButtonManager = new ActionListenerParametersButton();
		timeClockButtonManager = new ActionListenerTimeClockCheckInButton();
		connectButtonManager = new ActionListenerConnectButton();
	}
	public static void setFrameTimeClock(TimeClockFrame tf) 
	{
		frameTimeClock = tf;
		tf.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowOpened(WindowEvent e) {
				super.windowOpened(e);
				try {
					File folderToRead = new File("Data_TimeClock");
					File fileToRead = new File(folderToRead.getName()+java.io.File.separator+"parametersTimeClock.ser");
					if(folderToRead.exists()) 
					{
						if(folderToRead.isDirectory()) 
						{
							if(fileToRead.getParentFile() != null) 
							{
								if(fileToRead.isFile())
								{
									FileInputStream fileInputParameters = new FileInputStream(fileToRead);
									ObjectInputStream parametersReaders = new ObjectInputStream(fileInputParameters);
									TimeClockController.setIpParameters((InetSocketAddress)parametersReaders.readObject());
								}
							}
						}
					}

					FileInputStream fileInputStream = new FileInputStream(fileToRead);
					ObjectInputStream paramReader = new ObjectInputStream(fileInputStream);
					InetSocketAddress  parametersReaden = (InetSocketAddress) paramReader.readObject();

				} catch (IOException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		tf.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosed(WindowEvent e) 
			{
				super.windowClosed(e);
				try 
				{
					File folder = new File("Data_TimeClock");
					File fToWrite;
					if(folder.exists()) 
					{
						if(folder.isDirectory()) 
						{
							fToWrite = new File(folder.getCanonicalFile()+ java.io.File.separator +"parametersTimeClock.ser");

						}
						else 
						{
							folder.mkdir();
							fToWrite = new File(folder.getCanonicalPath()+java.io.File.separator+"parametersTimeClock.ser");
						}
					}
					else 
					{
						folder.mkdir();
						fToWrite = new File(folder.getCanonicalPath()+java.io.File.separator+"parametersTimeClock.ser");


					}
					FileOutputStream outputStreamFile = new FileOutputStream(fToWrite);
					ObjectOutputStream ipParamOutputStream = new ObjectOutputStream(outputStreamFile);
					ipParamOutputStream.writeObject(ipParameters);
					ipParamOutputStream.close();

					tf.dispose();

				}
				catch(IOException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
	}
	public TimeClockFrame getFrameTimeClock() 
	{
		return frameTimeClock;
	}
	public void setSender(InetAddress address, int port) throws IOException 
	{
		ipParameters = new InetSocketAddress(address,port);
		sender = new TimeClockSender(ipParameters);
	}
	public void setSender(InetSocketAddress socketAddressToSet)throws IOException
	{
		ipParameters = socketAddressToSet;
		sender = new TimeClockSender(ipParameters);
	}
	public TimeClockSender getSender() 
	{
		return sender;
	}
	public InetSocketAddress getIpParameters() 
	{
		return ipParameters;
	}
	public static void setIpParameters(InetSocketAddress address) 
	{
		ipParameters = address;
	}
	public  static void setIpParameters(InetAddress addr, int port) {

		ipParameters = new InetSocketAddress(addr, port);
	}
	public ActionListenerParametersButton getParametersButtonManager() {
		return parametersButtonManager;
	}
	public static ActionListenerTimeClockCheckInButton getTimeClockButtonManager() {
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
					if(sender.getClientSocket().isConnected()) 
					{
						sender.sentTicket(ticketToSend);
					}
				}
				else 
				{
					JOptionPane.setRootFrame(frameTimeClock);
					JOptionPane.showMessageDialog(panelTimeClock, "Le serveur n'est pas à l'écoute, veuillez vérifier les paramètres et appuyer ensuite sur connecter 12");
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
			if(sender.getClientSocket() != null) 
			{
				try {
					sender.getClientSocket().connect(ipParameters);
					JOptionPane.setRootFrame(frameTimeClock);
					JOptionPane.showMessageDialog(jPanelTimeClockView, "Connexion réussie");
				} 
				catch (IOException e1) 
				{
					JOptionPane.showMessageDialog(jPanelTimeClockView, "Connexion refusée, veuillez vérifier l'état de la machine distante ou changer l'adresse de contact");
					e1.printStackTrace();
				}
			}
			else 
			{
				if(ipParameters != null) 
				{
					try 
					{
						sender.setClientSocket(ipParameters);
						JOptionPane.setRootFrame(frameTimeClock);
						JOptionPane.showMessageDialog(jPanelTimeClockView, "Connexion réussie");
					} catch (IOException e1) 
					{
						JOptionPane.showMessageDialog(jPanelTimeClockView, "Connexion refusée, veuillez vérifier l'état de la machine distante ou changer l'adresse de contact");
						e1.printStackTrace();
					}
				}
				else 
				{
					System.out.println("non, les paramètres ont un probl");
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
				TimeClockController.this.setIpParameters(InetAddress.getByName(panelParameters.getIpFieldContent()),Integer.parseInt(panelParameters.getPortFieldContent()));

			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}

		}
	}

}
