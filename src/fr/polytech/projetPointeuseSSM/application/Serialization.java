package fr.polytech.projetPointeuseSSM.application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import fr.polytech.projetPointeuseSSM.application.model.Company;
import fr.polytech.projetPointeuseSSM.timeClocking.Ticket;
import fr.polytech.projetPointeuseSSM.timeClocking.controller.TimeClockController;

/**
 * Class for saving (serializing and deserializing) company and employee information
 * @author Quentin
 *
 */
public class Serialization {
	
	/**
	 * file containing the clocking queue
	 */
	private static File FILE_TICKETS = new File("tickets.ser");

	/**
	 * a file with informations about the company
	 */
	private static File FILE_COMPANY = new File("company.ser");
	
	/**
	 * Return the file with informations about the company
	 * @return the file with informations about the company
	 */
	public static File getFILE_COMPANY() {
		return FILE_COMPANY;
	}

	/**
	 * Set the file with informations about the company
	 * @param fILE_COMPANY a file with informations about the company
	 */
	public static void setFILE_COMPANY(File fILE_COMPANY) {
		FILE_COMPANY = fILE_COMPANY;
	}

	/**
	 * Return a file with informations about tickets
	 * @return a file with informations about tickets
	 */
	public static File getFILE_TICKETS() {
		return FILE_TICKETS;
	}


	/**
	 * set the file with informations about tickets
	 * @param fILE_TICKETS a file with informations about tickets
	 */
	public static void setFILE_TICKETS(File fILE_TICKETS) {
		FILE_TICKETS = fILE_TICKETS;
	}

	/**
	 * Redefinition of the function of reading a file
	 * @param file - a file to read
	 * @param argObject - the object used for reading in case of an empty object
	 * @return the object read
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unused")
	private static Object readObject(File file) throws IOException, ClassNotFoundException {
		if(file.exists()) {
			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
				Object res_object = input.readObject();
				input.close();
				
				
			} catch(FileNotFoundException ex) {
				System.out.println("Fichier inexistant");
			}
			catch(ClassNotFoundException ex) {
				System.out.println("Le fichier n'est pas bon");
			}
		}
		return null;
	}
	
	/**
	 * Redefinition of the writing of a time clock object
	 * @param file - often it will be an an attribute of this class
	 * @param object 
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private static void writeObject(File file, Object object) throws IOException {
		// we verify if the file is a file
		if(file.isFile()) {
			try {
				ObjectOutputStream outcomes = new ObjectOutputStream(new FileOutputStream(file));
				outcomes.writeObject(object);
				outcomes.close();
				
			} catch(FileNotFoundException ex) {
				System.out.println("Fichier inexistant");
			}
		}
	}
	
	/**
	 * to read
	 * @param input a inputStream
	 * @return a timeclock 
	 * @throws IOException
	 */
	public static TimeClockController readClocking(InputStream input) throws IOException {
		TimeClockController timeClock = null;
		ObjectInputStream objInput = new ObjectInputStream(input);
		try {
			timeClock = (TimeClockController) objInput.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Objet non trouve dans le fichier");
		}
		return timeClock;
	}
	/**
	 * Writes in a flow a data from the time clock
	 * @param output a outputStream
	 * @param ticket a ticket
	 * @throws IOException
	 */
	public static void writeTicket(OutputStream output, Ticket ticket) throws IOException {
		//we save the ticket in the file of tickets
		writeObject(FILE_TICKETS,ticket);
		//we empty the buffer passed in argument
		output.flush();
	}
	
	
	/**
	 * Method of castering the input file in the enterprise class
	 * @return a company
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Company readCompany() throws IOException, ClassNotFoundException {
		//we simply cast the file into a company because readObject returns a objet
		return (Company) readObject(FILE_COMPANY);
	}
	
	/**
	 * Writing informations about the company in the file which contains all informations about the company
	 * @param company the company you want to serialize 
	 * @throws IOException
	 */
	public static void writeCompany(Company company) throws IOException {
		writeObject(FILE_COMPANY, company);
	}
	
	
}