package fr.polytech.projetPointeuseSSM.timeClocking.view;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.polytech.projetPointeuseSSM.timeClocking.controller.TimeClockController;
import fr.polytech.projetPointeuseSSM.timeClocking.controller.TimeClockController.ActionListenerParametersButton;
/**
 * second panel attached to the TimeClockFrame, it contains some components to set ip parameters concerning
 * the remote application, to determine what is the distant ip adress  and the distant port of the machine receiving the Tickets sent by the timeclock
 * this class extends JPanel class. 
 * ipLabel represents the label next to ipField
 * portLabel represents the label next to portField.
 * parametersButton represents the button to save entered values
 * ipField represents the area where the user can enter the remote ip adress of the listening and receiving machine
 * portField represents the area where the user can enter the remote tcp port of the listening and receiving machine
 */
public final class JPanelParametersView extends JPanel
{
	private JLabel ipLabel;
	private JLabel portLabel;
	private JTextField ipField;
	private JTextField portField;
	private JButton parametersButton;
	
	/*
	 * constructor of the Class
	 */
	public JPanelParametersView() throws IOException
	{
		ipLabel = new JLabel("IP Adress : ");
		portLabel = new JLabel("Port : ");
		ipField = new JTextField(16);
		portField = new JTextField(5);
		parametersButton = new JButton("Save Parameters");
		
		parametersButton.addActionListener(new TimeClockController().getParametersButtonManager());
		ipLabel.setLabelFor(ipField);
		portLabel.setLabelFor(portField);
		ipLabel.setDisplayedMnemonic('I');
		portLabel.setDisplayedMnemonic('P');
		
		this.add(ipLabel);
		this.add(ipField);
		this.add(portLabel);
		this.add(portField);
		this.add(parametersButton);
		
	}
	/**
	 * @return the content of the JTextField ipField, this JTextField is used to enter ip adress of the remote machine hosting the application
	 */
	public String getIpFieldContent() 
	{
		return ipField.getText();
	}
	/**
	 * @return the content of the JTextField portField, in order to initialize later a socket to exchange and to send data to the remote application
	 */
	public String getPortFieldContent() 
	{
		return portField.getText();
	}
	/**
	 * @return the JTextField binded to the ip label, this JTextField is used to receive the value of the ip address of the remote machine
	 */
	public JTextField getIpField() 
	{
		return this.ipField;
	}
}
