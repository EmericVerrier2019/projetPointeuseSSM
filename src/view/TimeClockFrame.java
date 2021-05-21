package view;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.swing.*;

public class TimeClockFrame extends JFrame{
	public TimeClockFrame() 
	{
		super();
		build();
	}
	private void build() 
	{
		JPanel timeClockPannel = new JPanel();
		add(timeClockPannel);
		setContentPane(timeClockPannel);
		displayDate();
		setTitle("TimeClock Java -- SSMV");
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void displayDate() 
	{
		JTextArea textDate = new JTextArea(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd EEEE kk:mm:ss")).toString());
		textDate.setEditable(false);
		getContentPane().add(textDate);
		
	}

}
