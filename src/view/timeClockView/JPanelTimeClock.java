package view.timeClockView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projetPointeuseSSM.TimeClock;

public final class JPanelTimeClock extends JPanel {
	private JTextField userIdField;
	private JLabel userIdLabel;
	private JButton userIdButton;
	private JLabel textDate;

public JPanelTimeClock()
{
	userIdField = new JTextField(20);
	userIdLabel = new JLabel("User Id :");
	userIdButton = new JButton("Check In/Out");
	String dateElem = "<html>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd EEEE kk:mm:ss")).toString();
	dateElem = dateElem + ("<br>" + " Okay let's say : " +TimeClock.RoundTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)).toString());
	textDate = new JLabel(dateElem);
	
	userIdField.setEditable(true);
	userIdLabel.setLabelFor(userIdField);
	userIdLabel.setDisplayedMnemonic('i');
	
	this.add(textDate);
	this.add(userIdLabel);
	this.add(userIdField);
	this.add(userIdButton);
	}
}
