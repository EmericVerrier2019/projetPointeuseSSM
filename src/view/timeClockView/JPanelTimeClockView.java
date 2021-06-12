package view.timeClockView;

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

import projetPointeuseSSM.Day;
import projetPointeuseSSM.TimeClockController;

public final class JPanelTimeClockView extends JPanel {
	private JTextField userIdField;
	private JLabel userIdLabel;
	private JButton checkInButton;
	private JLabel textDate;
	private String dateElem;
	private Timer timerUpdateDisplayedHeure;
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
		timerUpdateDisplayedHeure = new Timer();
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
		timerUpdateDisplayedHeure.schedule(new timerTaskUpdateHour(),0,1000);
		connectButton.addActionListener(new TimeClockController().connectButtonManager);
		this.add(textDate);
		this.add(userIdLabel);
		this.add(userIdField);
		this.add(checkInButton);
		this.add(connectButton);

	}
	public String getUserIdFieldContent() 
	{
		return userIdField.getText();
	}
}
