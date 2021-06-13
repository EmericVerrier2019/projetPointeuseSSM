package fr.polytech.projetPointeuseSSM.timeClocking;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Emeric Verrier
 * this class Allows you to represent a ticket which is generated when the employee register his/her card in the TimeClock
 * the two attributes in are the dateTime when the employee executes the act to point and the employee's id
 * the last attribute is just an identifier because the ticket class implements the serializable interface.
 *
 */
public class Ticket implements Serializable{

	private static final long serialVersionUID = 4200085030654389537L;
	private LocalDateTime ticketDateTime;
	private int idEmployee;
	
	public Ticket() 
	{
		idEmployee = 0;
		ticketDateTime = LocalDateTime.now();
	}
	/**
	 * @param ticketDateTime
	 * @param idEmployee
	 */
	public Ticket(LocalDateTime ticketDateTime, int idEmployee) 
	{
		this.idEmployee = idEmployee;
		this.ticketDateTime = ticketDateTime;
	}
	/**a mutator to get the DateTimeObject contained in the ticket object
	 * @return the DateTimeObject when the ticket has been done
	 */
	public LocalDateTime getTicketDateTime() {
		return ticketDateTime;
	}
	/**
	 * a mutator to set the ticketDateTime contained in a ticket object
	 * @param ticketDateTime
	 */
	public void setTicketDateTime(LocalDateTime ticketDateTime) {
		this.ticketDateTime = ticketDateTime;
	}
	/**
	 * return the id contained in a Ticket Object
	 * @return the sent id
	 */
	public int getIdEmployee() {
		return idEmployee;
	}
	/**
	 * a mutator to set the id of an employee in a ticket object
	 * @param idEmployee
	 */
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	/**
	 * an override of the default toString method to display easily in the standard output
	 *@return String - a string with the two attributes composing a Ticket Object ( idEmployee and ticketDateTime ) 
	 */
	public String toString() 
	{
		return("identifiant employ� : "+ this.idEmployee + " heure de pointage : " + this.ticketDateTime.toString());
	}
}
