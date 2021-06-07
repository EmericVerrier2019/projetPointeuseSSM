package projetPointeuseSSM;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class Reporting implements Serializable { 
	/**
	 * Numéro de version de la classe, nécessaire pour l'interface Serializable
	 */
	private static final long serialVersionUID = 1L;

	
	private Day currentDay; //jour actuel 
	private Duration overTimeHour; //heure supplémentaire
	private List<Day> workedDays; //liste des jours travaillés
	private Employee employee; //employé rattaché à ce reporting
	
	/**
	 * Constructeur de la classe Reporting
	 * @param employee L'employé rattaché à ce reporting
	 */
	public Reporting(Employee employee) {
		this.employee = employee;
		workedDays = new ArrayList<>();
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
	public List<Day> getWorkedDays() {
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
			currentDay = new Day(null,null,dateFromClocking.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE));
		}
		
		//si l'heure de début n'est pas initialisé
		if(currentDay.getTimeStart() == null) {
			currentDay.setTimeStart(dateFromClocking);
		}
		//sinon on vient de finir la journée
		else {
			currentDay.setTimeEnd(dateFromClocking);
			workedDays.add(currentDay);
			//On regard par rapport au jour, si l'employé à réaliser des heures supplémentaires
			Planning planning = employee.getPlanning();
			for (Day day : planning.getDayList()) {
				//S'il s'agit du même jour on récupère l'horaire prévu par le planning
				if (currentDay.getDayName().compareToIgnoreCase(day.getDayName()) >= 0) {
					//On fait la différence entre les heures réalisé et celles prévues par le planning
					Duration overTimeWorkedCurrentDay = currentDay.getDailyWorkedHours().minus(day.getDailyWorkedHours());
					overTimeHour.plus(overTimeWorkedCurrentDay);
				}
			}
			
		}
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
