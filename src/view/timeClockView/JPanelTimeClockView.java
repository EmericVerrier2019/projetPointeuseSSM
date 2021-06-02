package view.timeClockView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projetPointeuseSSM.TimeClock;

public final class JPanelTimeClockView extends JPanel {
	private JTextField userIdField;
	private JLabel userIdLabel;
	private JButton checkInButton;
	private JLabel textDate;
	private String dateElem;
	private Timer timerUpdateDisplayedHeure;
	
	private class timerTaskUpdateHour extends TimerTask
	{

		@Override
		public void run() {
			JPanelTimeClockView.this.dateElem =  "<html>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd EEEE kk:mm:ss")).toString();
			dateElem = dateElem +  ("<br>" + " Okay let's say : " +TimeClock.RoundTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)).toString());
			textDate.setText(dateElem);
		}
		
	}

	public JPanelTimeClockView()
	{
		this.dateElem= new String();
		timerUpdateDisplayedHeure = new Timer();
		userIdField = new JTextField(20);
		userIdLabel = new JLabel("User Id :");
		checkInButton = new JButton("Check In/Out");
		this. dateElem = "<html>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd EEEE kk:mm:ss")).toString();
		this.dateElem = dateElem + ("<br>" + " Okay let's say : " +TimeClock.RoundTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)).toString());
		textDate = new JLabel(dateElem);

		userIdField.setEditable(true);
		userIdLabel.setLabelFor(userIdField);
		userIdLabel.setDisplayedMnemonic('i');
		checkInButton.addActionListener(new TimeClock.ActionListenerTimeClockCheckInButton());
		timerUpdateDisplayedHeure.schedule(new timerTaskUpdateHour(),0,1000);
		this.add(textDate);
		this.add(userIdLabel);
		this.add(userIdField);
		this.add(checkInButton);

	}
	public String getUserIdFieldContent() 
	{
		return userIdField.getText();
	}
}
