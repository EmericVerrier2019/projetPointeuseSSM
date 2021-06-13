package fr.polytech.projetPointeuseSSM.application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import fr.polytech.projetPointeuseSSM.application.model.Employee;
import fr.polytech.projetPointeuseSSM.application.view.MainFrame;
import fr.polytech.projetPointeuseSSM.timeClocking.Ticket;

/**
 * 
 * This class is used by the application's controller to receive the tickets sent by the timeclock
 * It's attributes are a server socket listening a specified tcp port on the localhost address,
 * an object input stream which takes the role of an input buffer in which objects could be readen
 * and the sending socket. This class specializes the Thread class because the server sockets needs to turn as background task
 * and would block the application if it was not managed on another thread. The last attribute is an arrayList of ticket
 * initialized later by the mainframeController
 *
 */
public class TicketReceiver extends Thread implements Runnable
{
    /*
     * view attached to the application
     */
    private MainFrame mainFrame; 
    /*
     * socket that has just been sent 
     */
    private Socket sendingSocket;
    /*
     * socket that has just been received
     */
    private ServerSocket receivingSocket;
    /*
     * stream to retrieve tickets 
     */
    private ObjectInputStream objectInputStream;
    /*
     * Array for storing tickets
     */
    private ArrayList<Ticket> ticketStorage;
    
    /**
     * allows to instantiate
     * @param address
     * @param mainFrame
     */
    public TicketReceiver(InetSocketAddress address, MainFrame mainFrame) 
    {
        super();
        try {
            receivingSocket = new ServerSocket(address.getPort());
            if(receivingSocket.isBound() == false) 
            {
                receivingSocket.bind(new InetSocketAddress(address.getPort()));
                
            }
            ticketStorage = new ArrayList<Ticket>();
            this.mainFrame = mainFrame;
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }
    
    /**
     * set the array of ticket for the storing
     * @param storage a ArrayList of Ticket
     */
    public void setTicketStorage(ArrayList<Ticket> storage) 
    {
        this.ticketStorage = storage;
    }
    
    
    public void run() 
    {            
        try {
                sendingSocket = receivingSocket.accept();
                try 
                {        
                        objectInputStream = new ObjectInputStream(sendingSocket.getInputStream());
                        while(true) 
                        {
                            Ticket receivedTicket = (Ticket) objectInputStream.readObject();
                            System.out.println(receivedTicket);
                            ticketStorage.add(receivedTicket);
                            addPointingEmployee(receivedTicket);
                            System.out.println("ReÃ§u appli");
                            mainFrame.updateEmployeeTable();
                            
                        }
                } 
                catch (ClassNotFoundException e) 
                {
                    e.printStackTrace();
                }
                
            } catch (IOException e) {
                e.printStackTrace();
            }
    }    
    
    /**
     * allows to point an employee from a received ticket.
     * @param receiveTicket a ticket which been receive
     */
    private void addPointingEmployee(Ticket receiveTicket) {
        int indiceEmployee = Main.company.existEmployee(receiveTicket.getIdEmployee());
        //The employee exist in the company
        if( indiceEmployee >= 0) {
            Employee employee = Main.company.getListEmployees().get(indiceEmployee);
            employee.getReportingOfDayWorked().updateReporting(receiveTicket.getTicketDateTime());
        }else {
            String message = "L'identifiant saisie ne correspond ï¿½ aucun employï¿½";
            JOptionPane.showMessageDialog(Main.mainFrame,message,"Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}