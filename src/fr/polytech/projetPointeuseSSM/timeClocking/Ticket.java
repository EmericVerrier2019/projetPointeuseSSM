package fr.polytech.projetPointeuseSSM.timeClocking;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Emeric Verrier
 * this class Allows you to represent a ticket which is generated when the employee register his/her card in the TimeClock
 * the two attributes in are the dateTime when the employee executes the act to point and the employee's id
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
	/**
	 * @return the DateTimeObject when the ticket has been done
	 */
	public LocalDateTime getTicketDateTime() {
		return ticketDateTime;
	}
	/**
	 * @param ticketDateTime
	 */
	public void setTicketDateTime(LocalDateTime ticketDateTime) {
		this.ticketDateTime = ticketDateTime;
	}
	/**
	 * @return the sent id
	 */
	public int getIdEmployee() {
		return idEmployee;
	}
	/**
	 * @param idEmployee
	 */
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public String toString() 
	{
		return("identifiant employé : "+ this.idEmployee + " heure de pointage : " + this.ticketDateTime.toString());
	}
}
