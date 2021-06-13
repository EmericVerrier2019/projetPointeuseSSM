package fr.polytech.projetPointeuseSSM.timeClocking.view;
import java.awt.GridLayout;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import fr.polytech.projetPointeuseSSM.application.model.Department;
import fr.polytech.projetPointeuseSSM.application.model.Employee;
import fr.polytech.projetPointeuseSSM.application.model.Planning;
import fr.polytech.projetPointeuseSSM.timeClocking.controller.TimeClockController;

public class TimeClockFrame extends JFrame{

	public TimeClockFrame() throws IOException
	{
		super();
		build();
	}
	private void build() throws IOException
	{
		// define the organization of JFrame : one JTabbedPane which embed two JPanel
		JTabbedPane timeClockPane = new JTabbedPane();
		JPanelTimeClockView timeClockTab = new JPanelTimeClockView();
		JPanelParametersView parametersTab = new JPanelParametersView();
		TimeClockController.setFrameTimeClock(this);
		
		
		// define general informations for the jFrame as its title, its dimensions...
		setTitle("TimeClock Java -- SSMV");
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//adding the two Panes to the JTabbedPane and setting the content pane of the JFrame with the JTabbedPane
		timeClockPane.addTab("timeClock", timeClockTab);
		timeClockPane.addTab("Parameters", parametersTab);
		setContentPane(timeClockPane);
		timeClockPane.setSelectedComponent(timeClockTab);
	}
	

}
