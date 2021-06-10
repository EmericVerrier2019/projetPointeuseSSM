package projetPointeuseSSM;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


/**
 * Classe permettant de sauvegarder (serialiser et deserialiser) les informations sur l'entreprise et sur les employes 
 */
public class Serialization {
	/**
	 * file containing the clocking queue
	 */
	private static File FILE_TICKETS = new File("tickets.ser");

	/**
	 * a file with informations of the company
	 */
	private static File FILE_COMPANY = new File("company.ser");
	
	
	public static File getFILE_COMPANY() {
		return FILE_COMPANY;
	}


	public static void setFILE_COMPANY(File fILE_COMPANY) {
		FILE_COMPANY = fILE_COMPANY;
	}

	public static File getFILE_TICKETS() {
		return FILE_TICKETS;
	}


	public static void setFILE_TICKETS(File fILE_TICKETS) {
		FILE_TICKETS = fILE_TICKETS;
	}

	/**
	 * Redefinition de fonction de lecture d'un fichier
	 * @param file - a file to read
	 * @param argObject - l'objet utilise pour la lecture en cas d'objet vide
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
	 * Redifinition de l'ecriture d'un objet de la pointeuse
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
	 * @param input
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
	 * Ecrit dans un flux une donnee de la pointeuse
	 * @param output
	 * @param clocking
	 * @throws IOException
	 */
	public static void writeTicket(OutputStream output, Ticket ticket) throws IOException {
		//On sauvegrde le 
		writeObject(FILE_TICKETS,ticket);
		//vide le buffer rentre en argument 
		output.flush();
		
	}
	/**
	 * Methode permettant de caster le fichier de l'entrerise en classe entreprise
	 * @return l'entrepise 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Company readCompany() throws IOException, ClassNotFoundException {
		//on caste simplement le fichier en une entreprise car readObject renvoie un objet
		return (Company) readObject(FILE_COMPANY);
	}
	
	/**
	 * Ecriture des informations d'une entreprise dans le fichier
	 * @param company
	 * @throws IOException
	 */
	public static void writeCompany(Company company) throws IOException {
		writeObject(FILE_COMPANY, company);
	}
	
	
}