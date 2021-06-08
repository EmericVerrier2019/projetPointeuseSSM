package projetPointeuseSSM;

import java.io.Serializable;
import java.time.Duration;

import java.time.LocalDateTime;
import java.util.HashMap;





public class Reporting implements Serializable { 
	/**
	 * Numéro de version de la classe, nécessaire pour l'interface Serializable
	 */
	private static final long serialVersionUID = 1L;

	
	private Day currentDay; //jour actuel 
	private Duration overTimeHour; //heure supplémentaire
	private HashMap<Day,Duration> workedDays; //liste des jours travaillés
	private Employee employee; //employé rattaché à ce reporting
	
	/**
	 * Constructeur de la classe Reporting
	 * @param employee L'employé rattaché à ce reporting
	 */
	public Reporting(Employee employee) {
		this.employee = employee;
		workedDays = new HashMap<Day,Duration>();
		overTimeHour = Duration.ofHours(0);
		currentDay = new Day();
	}

	/**
	 * Retourne la journée de travail actuelle de l'employé
	 * @return L'objet Day du jour actuel
	 */
	public Day getCurrentDay() {
		return currentDay;
	}

	/**
	 * Retourne le nombre d'heure supplémentaire réalisé
	 * @return Les heures supplémentaires faites par l'employé
	 */
	public Duration getOverTimeHour() {
		return overTimeHour;
	}

	/**
	 * Retourne la liste des jours travaillés
	 * @return La liste des jours travaillé par l'employé
	 */
	public HashMap<Day, Duration> getWorkedDays() {
		return workedDays;
	}

	/**
	 * Retourne l'employé rattaché à ce reporting
	 * @return L'employé rattaché à ce reporting
	 */
	public Employee getEmployee() {
		return employee;
	}
	
	/**
	 * Fonction qui permet de mettre à jour le reporting à partir d'une date.
	 * @param dateFromClocking date provenant de la pointeuse (envoyé lors du pointage de l'employé)
	 */
	public void updateReporting(LocalDateTime dateFromClocking) {
		
		//si le jour en cour à déjà c'est heures de début et de fin initialisé, on créer un nouveau jour
		if(currentDay.getTimeStart() != null && currentDay.getTimeEnd() != null) {
			currentDay = new Day(null,null);
		}
		
		//si l'heure de début n'est pas initialisé
		if(currentDay.getTimeStart() == null) {
			currentDay.setTimeStart(dateFromClocking);
		}
		//sinon on vient de finir la journée
		else {
		currentDay.setTimeEnd(dateFromClocking);
		Duration overTimeCurrentDayHour = getOverTimeCurrentDayHour(currentDay); //heure supplémentaire réalisé dans le currentDay
		workedDays.put(currentDay, overTimeCurrentDayHour);
		overTimeHour = overTimeHour.plus(overTimeCurrentDayHour);
		}
			
	}
	
	/**
	 * Permet de calculer les heures supplémentaire du jour passé en paramètre
	 * @return
	 */
	public Duration getOverTimeCurrentDayHour(Day day) {
		//On regard par rapport au jour, si l'employé à réaliser des heures supplémentaires
		Planning planning = employee.getPlanning();
		for (Day dayPlanning : planning.getDayList()) {
			//S'il s'agit du même jour on récupère l'horaire prévu par le planning
			if (day.getDayName().compareToIgnoreCase(dayPlanning.getDayName()) == 0) {
				//On fait la différence entre les heures réalisé et celles prévues par le planning
				Duration overTimeWorkedCurrentDay = day.getDailyWorkedHours().minus(dayPlanning.getDailyWorkedHours());
				return overTimeWorkedCurrentDay;
			}
		}
		//si on sort de la boucle le nom du currentDay n'est pas bien définie
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
