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
		JLabel ipLabel = new JLabel("IP Adress : ");
		JLabel portLabel = new JLabel("Port : ");
		JTextField ipField = new JTextField(16);
		JTextField portField = new JTextField(5);
		JButton parametersButton = new JButton("Save Parameters");
		parametersButton.addActionListener(new ActionListenerParametersButton());
		
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
}
