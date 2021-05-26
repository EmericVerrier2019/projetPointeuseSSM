package view.timeClockView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projetPointeuseSSM.TimeClock;
import projetPointeuseSSM.TimeClock.ActionListenerParametersButton;

public final class JPanelParametersView extends JPanel
{
	private JLabel ipLabel;
	private JLabel portLabel;
	private JTextField ipField;
	private JTextField portField;
	private JButton parametersButton;
	

	public JPanelParametersView() 
	{
		ipLabel = new JLabel("IP Adress : ");
		portLabel = new JLabel("Port : ");
		ipField = new JTextField(16);
		portField = new JTextField(5);
		
		parametersButton = new JButton("Save Parameters");
		parametersButton.addActionListener(new TimeClock.ActionListenerParametersButton());
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
