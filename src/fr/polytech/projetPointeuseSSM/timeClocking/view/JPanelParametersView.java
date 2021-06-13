package fr.polytech.projetPointeuseSSM.timeClocking.view;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.polytech.projetPointeuseSSM.timeClocking.controller.TimeClockController;
import fr.polytech.projetPointeuseSSM.timeClocking.controller.TimeClockController.ActionListenerParametersButton;

public final class JPanelParametersView extends JPanel
{
	private JLabel ipLabel;
	private JLabel portLabel;
	private JTextField ipField;
	private JTextField portField;
	private JButton parametersButton;
	

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
	public String getIpFieldContent() 
	{
		return ipField.getText();
	}
	public String getPortFieldContent() 
	{
		return portField.getText();
	}
	public JTextField getIpField() 
	{
		return this.ipField;
	}
}
