package projetPointeuseSSM;

import java.io.Serializable;
import java.time.Duration;

import java.time.LocalDateTime;
import java.util.HashMap;





public class Reporting implements Serializable { 
	/**
	 * Num�ro de version de la classe, n�cessaire pour l'interface Serializable
	 */
	private static final long serialVersionUID = 1L;

	
	private Day currentDay; //jour actuel 
	private Duration overTimeHour; //heure suppl�mentaire
	private HashMap<Day,Duration> workedDays; //liste des jours travaill�s
	private Employee employee; //employ� rattach� � ce reporting
	
	/**
	 * Constructeur de la classe Reporting
	 * @param employee L'employ� rattach� � ce reporting
	 */
	public Reporting(Employee employee) {
		this.employee = employee;
		workedDays = new HashMap<Day,Duration>();
		overTimeHour = Duration.ofHours(0);
		currentDay = new Day();
	}

	/**
	 * Retourne la journ�e de travail actuelle de l'employ�
	 * @return L'objet Day du jour actuel
	 */
	public Day getCurrentDay() {
		return currentDay;
	}

	/**
	 * Retourne le nombre d'heure suppl�mentaire r�alis�
	 * @return Les heures suppl�mentaires faites par l'employ�
	 */
	public Duration getOverTimeHour() {
		return overTimeHour;
	}

	/**
	 * Retourne la liste des jours travaill�s
	 * @return La liste des jours travaill� par l'employ�
	 */
	public HashMap<Day, Duration> getWorkedDays() {
		return workedDays;
	}

	/**
	 * Retourne l'employ� rattach� � ce reporting
	 * @return L'employ� rattach� � ce reporting
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * Fonction qui permet de mettre � jour le reporting � partir d'une date.
	 * @param dateFromClocking date provenant de la pointeuse (envoy� lors du pointage de l'employ�)
	 */
	public void updateReporting(LocalDateTime dateFromClocking) {
		
		//si le jour en cour � d�j� c'est heures de d�but et de fin initialis�, on cr�er un nouveau jour
		if(currentDay.getTimeStart() != null && currentDay.getTimeEnd() != null) {
			currentDay = new Day(null,null);
		}
		
		//si l'heure de d�but n'est pas initialis�
		if(currentDay.getTimeStart() == null) {
			currentDay.setTimeStart(dateFromClocking);
		}
		//sinon on vient de finir la journ�e
		else {
		currentDay.setTimeEnd(dateFromClocking);
		Duration overTimeCurrentDayHour = getOverTimeCurrentDayHour(currentDay); //heure suppl�mentaire r�alis� dans le currentDay
		workedDays.put(currentDay, overTimeCurrentDayHour);
		overTimeHour = overTimeHour.plus(overTimeCurrentDayHour);
		}
			
	}
	
	/**
	 * Permet de calculer les heures suppl�mentaire du jour pass� en param�tre
	 * @return
	 */
	public Duration getOverTimeCurrentDayHour(Day day) {
		//On regard par rapport au jour, si l'employ� � r�aliser des heures suppl�mentaires
		Planning planning = employee.getPlanning();
		for (Day dayPlanning : planning.getDayList()) {
			//S'il s'agit du m�me jour on r�cup�re l'horaire pr�vu par le planning
			if (day.getDayName().compareToIgnoreCase(dayPlanning.getDayName()) == 0) {
				//On fait la diff�rence entre les heures r�alis� et celles pr�vues par le planning
				Duration overTimeWorkedCurrentDay = day.getDailyWorkedHours().minus(dayPlanning.getDailyWorkedHours());
				return overTimeWorkedCurrentDay;
			}
		}
		//si on sort de la boucle le nom du currentDay n'est pas bien d�finie
		throw new IllegalAccessError("le nom du currentDay ne correspond pas avec ceux du planning");
	}

	@Override
	public String toString() {
		return "Reporting [currentDay=" + currentDay + ", overTimeHour=" + overTimeHour + ", workedDays=" + workedDays
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reporting other = (Reporting) obj;
		if (currentDay == null) {
			if (other.currentDay != null)
				return false;
		} else if (!currentDay.equals(other.currentDay))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (overTimeHour == null) {
			if (other.overTimeHour != null)
				return false;
		} else if (!overTimeHour.equals(other.overTimeHour))
			return false;
		if (workedDays == null) {
			if (other.workedDays != null)
				return false;
		} else if (!workedDays.equals(other.workedDays))
			return false;
		return true;
	}
	
	
}
