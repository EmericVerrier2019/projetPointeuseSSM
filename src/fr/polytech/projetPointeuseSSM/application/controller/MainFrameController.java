package fr.polytech.projetPointeuseSSM.application.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import fr.polytech.projetPointeuseSSM.application.TicketReceiver;
import fr.polytech.projetPointeuseSSM.application.model.Company;
import fr.polytech.projetPointeuseSSM.application.model.Employee;
import fr.polytech.projetPointeuseSSM.application.view.MainFrame;
import fr.polytech.projetPointeuseSSM.timeClocking.Ticket;

public class MainFrameController{

	private Company company ;
	private ArrayList<Ticket> ticketList;
	private MainFrame mainFrame;
	private TicketReceiver ticketReceiver;
	public MainFrameController(Company company)
	{
		this.company = company;
		ticketList = new ArrayList<Ticket>();
		setMainFrame(new MainFrame(company));
		ticketReceiver = new TicketReceiver(new InetSocketAddress(3125),mainFrame);
		
		
	}
	public void setMainFrame(MainFrame mainFrame) 
	{
		this.mainFrame = mainFrame;
		this.mainFrame.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowOpened(WindowEvent e) 
			{
				try {
					FileInputStream readSavedEmployeeFile = new FileInputStream("employeeList.ser");
					ObjectInputStream readSavedEmployeeInputStream = new ObjectInputStream(readSavedEmployeeFile);
					readSavedEmployeeInputStream.close();
					readSavedEmployeeFile.close();
					
					

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				TicketReceiver receiver = new TicketReceiver(new InetSocketAddress(3125), mainFrame);
				receiver.setTicketStorage(ticketList);
				receiver.start();
			}
		});
		this.mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				try {
					
					FileOutputStream saveEmployeeListFile = new FileOutputStream("employeeList.ser");
					ObjectOutputStream saveEmployeeOutputStream = new ObjectOutputStream(saveEmployeeListFile);
					saveEmployeeOutputStream.close();
					saveEmployeeListFile.close();
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	            System.exit(0);
			}			
			
		});
	}
	public MainFrame getMainFrame() 
	{
		return this.mainFrame;
	}

	
	
}
