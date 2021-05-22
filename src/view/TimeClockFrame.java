package view;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.swing.*;

import projetPointeuseSSM.TimeClock;

public class TimeClockFrame extends JFrame{
	public TimeClockFrame() 
	{
		super();
		build();
	}
	private void build() 
	{
		// define the organization of JFrame : one JTabbedPane which embed two JPanel
		JTabbedPane timeClockPane = new JTabbedPane();
		JPanel timeClockElem = new JPanel();
		JPanel parametersElem = new JPanel();
		
		// define the String which will be rendered for the date
		String DateElem = "<html>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd EEEE kk:mm:ss")).toString();
		JLabel textDate = new JLabel(DateElem);
		// define the user id zone and the button to send it to the main application
		JTextField UserIdField = new JTextField(20);
		JLabel UserIdLabel = new JLabel("User Id :");
		JButton UserIdButton = new JButton("Check In/Out");
		DateElem = DateElem + ("<br>" + " Okay let's say : " +TimeClock.RoundTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)).toString());
		timeClockElem.add(textDate);
		UserIdField.setEditable(true);
		UserIdLabel.setLabelFor(UserIdField);
		UserIdLabel.setDisplayedMnemonic('i');
		timeClockElem.add(UserIdLabel);
		timeClockElem.add(UserIdField);
		timeClockElem.add(UserIdButton);
		
		// define the content of the parameter Pane
		JLabel IpLabel = new JLabel("IP Adress : ");
		JLabel PortLabel = new JLabel("Port : ");
		JTextField IpField = new JTextField(16);
		JTextField PortField = new JTextField(5);
		JButton ParametersButton = new JButton("Save Parameters");
		IpLabel.setLabelFor(IpField);
		PortLabel.setLabelFor(PortField);
		IpLabel.setDisplayedMnemonic('I');
		PortLabel.setDisplayedMnemonic('P');
		parametersElem.add(IpLabel);
		parametersElem.add(IpField);
		parametersElem.add(PortLabel);
		parametersElem.add(PortField);
		parametersElem.add(ParametersButton);
		
		// define general informations for the jFrame as its title, its dimensions...
		setTitle("TimeClock Java -- SSMV");
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//adding the two Panes to the JTabbedPane and setting the content pane of the JFrame with the JTabbedPane
		timeClockPane.addTab("timeClock", timeClockElem);
		timeClockPane.addTab("Parameters", parametersElem);
		setContentPane(timeClockPane);
		timeClockPane.setSelectedComponent(timeClockElem);
	}

}
