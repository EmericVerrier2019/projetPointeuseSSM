package controller;

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
import java.util.ArrayList;

import projetPointeuseSSM.Company;
import projetPointeuseSSM.Employee;
import projetPointeuseSSM.Ticket;
import view.MainFrame;

public class MainFrameController{

	private Company company ;
	private ArrayList<Ticket> entryTicketList;
	private ArrayList<Ticket> exitTicketList;
	private ArrayList<Employee> employeeList;
	private MainFrame mainFrame;
	public MainFrameController(Company company)
	{
		this.company = company;
		entryTicketList = new ArrayList<Ticket>();
		exitTicketList = new ArrayList<Ticket>();
		employeeList = new ArrayList<Employee>();
		mainFrame = new MainFrame(company);
		
		
	}
	public ArrayList<Ticket> getEntryTicketList()
	{
		return this.entryTicketList;
	}
	public ArrayList<Ticket> getExitTicketList()
	{
		return this.exitTicketList;
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
					MainFrameController.this.employeeList = (ArrayList<Employee>) readSavedEmployeeInputStream.readObject();
					readSavedEmployeeInputStream.close();
					readSavedEmployeeFile.close();
					
					FileInputStream readSavedEntryTicketListFile = new FileInputStream("entryTicketList.ser");
					ObjectInputStream readSavedEntryTicketInputStream = new ObjectInputStream(readSavedEntryTicketListFile);
					MainFrameController.this.entryTicketList = (ArrayList<Ticket>) readSavedEntryTicketInputStream.readObject();
					readSavedEntryTicketInputStream.close();
					readSavedEntryTicketListFile.close();
					
					FileInputStream readSavedExitTicketListFile = new FileInputStream("exitTicketList.ser");
					ObjectInputStream readSavedExitTicketInputStream = new ObjectInputStream(readSavedExitTicketListFile);
					MainFrameController.this.exitTicketList = (ArrayList<Ticket>) readSavedExitTicketInputStream.readObject();
					readSavedExitTicketInputStream.close();
					readSavedExitTicketListFile.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		this.mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				try {
					
					FileOutputStream saveEntryTicketListFile = new FileOutputStream("entryTicketList.ser");
					ObjectOutputStream saveEntryTicketOutputStream = new ObjectOutputStream(saveEntryTicketListFile);
					saveEntryTicketOutputStream.writeObject(entryTicketList);
					saveEntryTicketOutputStream.close();
					saveEntryTicketListFile.close();
					
					FileOutputStream saveExitTicketListFile = new FileOutputStream("exitTicketList.ser");
					ObjectOutputStream saveExitTicketOutputStream = new ObjectOutputStream(saveExitTicketListFile);
					saveExitTicketOutputStream.writeObject(exitTicketList);
					saveExitTicketOutputStream.close();
					saveExitTicketListFile.close();
					
					FileOutputStream saveEmployeeListFile = new FileOutputStream("employeeList.ser");
					ObjectOutputStream saveEmployeeOutputStream = new ObjectOutputStream(saveEmployeeListFile);
					saveEmployeeOutputStream.writeObject(employeeList);
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

	
	
}
