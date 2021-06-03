package view;

import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import projetPointeuseSSM.Ticket;

public class TicketPanel extends JPanel 
{

	private JTable tb;
	private ArrayList<Ticket> ticketList;
	private JButton addTicketButton;
	
	public TicketPanel()
	{
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ticketList = new ArrayList<Ticket>();
		Ticket ticketTest = new Ticket(LocalDateTime.of(2020, 4, 20, 12, 20), 15);
		ticketList.add(ticketTest);
		addTicketButton = new JButton("ajouter");
		TicketTableModel tableModel = new TicketTableModel(ticketList, new String[]{"Id Employé","Arrivée","Départ","Département"});
		tb = new JTable(tableModel);
		
		add(new JScrollPane(tb),BorderLayout.CENTER);
		add(addTicketButton);
		
		setName("tickets history");
	}
}
