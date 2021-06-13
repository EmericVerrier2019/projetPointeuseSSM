package fr.polytech.projetPointeuseSSM.timeClocking.view;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.polytech.projetPointeuseSSM.application.model.Day;
import fr.polytech.projetPointeuseSSM.timeClocking.controller.TimeClockController;
/**
 * 
 * a class representing the main tab of the timeclock, it's the view for an average user whose the only preoccupation is to go to work
 * the attributes are the following :
 * userIdField : a JTextField in which the id of the employee is filled, an integer value
 * userIdLabel : a JLabel next to the userIdField to indicate the role of the userIdField attribute
 * checkInButton : a JButton attached to an Action Listener defined in the TimeClockController class, when the user click, the value filled in the userIdField and the rounded time are sent to the remote application
 * textDate : a JLabel containing the current time render with a string and the rounded time
 * dateElem : the string in the textDate
 * timerUpdateDisplayedHour : a timer used to refresh the displayed hour every second, thereby, the displayed time is not frozen
 * connectButton : a JButton which is linked to an ActionListener defined in the TimeClockController class. This button must be clicked before the checkInButton when the timeclock is launched otherwise the ticket wont be sent
 */
public final class JPanelTimeClockView extends JPanel {
	private JTextField userIdField;
	private JLabel userIdLabel;
	private JButton checkInButton;
	private JLabel textDate;
	private String dateElem;
	private Timer timerUpdateDisplayedHour;
	private JButton connectButton;
	
	private class timerTaskUpdateHour extends TimerTask
	{

		@Override
		public void run() {
			JPanelTimeClockView.this.dateElem =  "<html>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd EEEE kk:mm:ss")).toString();
			dateElem = dateElem +  ("<br>" + " Okay let's say : " +Day.roundTime(LocalDateTime.now()).format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)).toString());
			textDate.setText(dateElem);
		}
		
	}

	public JPanelTimeClockView() throws IOException
	{
		this.connectButton = new JButton("connecter");
		this.dateElem= new String();
		timerUpdateDisplayedHour = new Timer();
		userIdField = new JTextField(20);
		userIdLabel = new JLabel("User Id :");
		checkInButton = new JButton("Check In/Out");
		this. dateElem = "<html>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd EEEE kk:mm:ss")).toString();
		this.dateElem = dateElem + ("<br>" + " Okay let's say : " +Day.roundTime(LocalDateTime.now()).format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)).toString());
		textDate = new JLabel(dateElem);

		userIdField.setEditable(true);
		userIdLabel.setLabelFor(userIdField);
		userIdLabel.setDisplayedMnemonic('i');
		checkInButton.addActionListener(new TimeClockController().timeClockButtonManager);
		timerUpdateDisplayedHour.schedule(new timerTaskUpdateHour(),0,1000);
		connectButton.addActionListener(new TimeClockController().connectButtonManager);
		this.add(textDate);
		this.add(userIdLabel);
		this.add(userIdField);
		this.add(checkInButton);
		this.add(connectButton);

	}
	/** 
	 * A Mutator to get the content of the userIdField 
	 * @return userIdField.getText(); - the content of the userIdField
	 */
	public String getUserIdFieldContent() 
	{
		return userIdField.getText();
	}
}
