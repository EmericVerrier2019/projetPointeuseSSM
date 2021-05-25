package projetPointeuseSSM;

import java.time.*;

import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import javax.swing.SwingUtilities;

import view.timeClockView.*;
public class Main {

	public static void main(String[] args)
	{
		
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On cr�e une nouvelle instance de notre JDialog
				TimeClockFrame fenetrePointeuse = new TimeClockFrame();
				fenetrePointeuse.setVisible(true);
			}
		});

	}
}
