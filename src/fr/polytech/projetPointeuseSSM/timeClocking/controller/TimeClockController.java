package fr.polytech.projetPointeuseSSM.timeClocking.controller;
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

import fr.polytech.projetPointeuseSSM.application.model.Day;
import fr.polytech.projetPointeuseSSM.timeClocking.Ticket;
import fr.polytech.projetPointeuseSSM.timeClocking.TimeClockSender;
import fr.polytech.projetPointeuseSSM.timeClocking.view.JPanelParametersView;
import fr.polytech.projetPointeuseSSM.timeClocking.view.JPanelTimeClockView;
import fr.polytech.projetPointeuseSSM.timeClocking.view.TimeClockFrame;

/**
 * 
 * The TimeClockController is a class used to establish  the link between the model classes and the view classes.
 * The controller calls some models class when the user interact by some actions with the view objects
 * sender is a TimeClockSender object used to send tickets to the remote application
 * ipParameters is a InetSocketAddress containing the address saved in the parameters file or entered by the user
 * parametersButtonManager the Action listener binded with the save parameters button, when the button is clicked the parametersButtonManager attributes is called to save parameters in the ipParameters attribute
 * timeClockButtonManager the Action listener binded with the check in/out button, which is invoked when the user clicks on the check/in out button
 * connectButtonManager the Action listener binded with the connect button, which is invoked when the user clicks the connect button
 * frameTimeClock is a TimeClockFrame object, the view object linked to the controller. With the view in attributes, the controller can access to the view elements
 *
 */
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
	/**
	 * 
	 * @param tf - the view attached to the controller, a TimeClockFrame object. Two windows listener are also defined to manage the
	 * opening and the closing of the window, to serialize (when the timeClock is exited) and deserialize (when the timeClock is launched) ipParameters.
	 * ip parameters are saved in a file, in the Data_TimeClock folder, parametersTimeClock.ser.
	 */
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
	/**
	 * A mutator to get the associated view object binded to this instance of the controller
	 * @return frameTimeClock
	 */
	public TimeClockFrame getFrameTimeClock() 
	{
		return frameTimeClock;
	}
	/**
	 * @param address - the InetAddress of the sender socket, so this address represents also the remote application host machine
	 * @param port - the TCP port of the sender socket, this port represent the remote port of the application host machine
	 * @throws IOException - in case where the distant machine is not accessible
	 */
	public void setSender(InetAddress address, int port) throws IOException 
	{
		ipParameters = new InetSocketAddress(address,port);
		sender = new TimeClockSender(ipParameters);
	}
	/**
	 * @param socketAddressToSet - the InetSocket address used to initialize the sender socket, this socket address packs a TCP port and an Ip address
	 * @throws IOException - in case where the distant machine isn't accessible
	 */
	public void setSender(InetSocketAddress socketAddressToSet)throws IOException
	{
		ipParameters = socketAddressToSet;
		sender = new TimeClockSender(ipParameters);
	}
	/**
	 * A mutator to get the sender object. The sender can contain an uninitialized Socket.
	 * @return sender
	 */
	public TimeClockSender getSender() 
	{
		return sender;
	}
	/**
	 * A mutator to get the InetSocketAddress object ipParameters which is readen from an external file or set by the user in parameters tab
	 * @return ipParameters
	 */
	public InetSocketAddress getIpParameters() 
	{
		return ipParameters;
	}
	/**
	 * a mutator to set the ipParameters to select the remote machine hosting the main application
	 * @param address
	 */
	public static void setIpParameters(InetSocketAddress address) 
	{
		ipParameters = address;
	}
	/**
	 * a mutator to set the ipParameters with a given InetAddress object and an integer value for the port
	 * @param addr
	 * @param port
	 */
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
						//ticketToSend = new Ticket(LocalDateTime.now().minusDays(2), 2);
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
	/**
	 * An internal class allowing us to manage events on the JButton connect embedded in the view. When this element is clicked, the sender socket 
	 * of the current TimeClockController is constructed and initialized, results appear in JOptionPane to notify the user of the result of the connection
	 */
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
	/**
	 * 
	 * intern class implementing ActionListener interface to control the behavior linked to the Parameters button, in the parameters panel, when you click on it, it
	 * saves the TCP/IP parameters to select the remote machine hosting the application, in an InetSocketAddress attributes ipParameters.
	 *
	 */
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
