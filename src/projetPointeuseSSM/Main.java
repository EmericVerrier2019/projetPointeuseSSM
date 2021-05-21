package projetPointeuseSSM;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import javax.swing.SwingUtilities;
import view.*;
public class Main {

	public static void main(String[] args)
	{
	
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une nouvelle instance de notre JDialog
				MainFrame fenetre = new MainFrame();
				fenetre.setVisible(true);//On la rend visible
			}
		});

	}
	
	private void GestionTempsUtilisation() {
		/* 
		Lien pour gerer le temps avec tous les classes du package : 
		-https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html#package.description
		
		lien pour gerer le format des dates avec les classes du package:
		-https://docs.oracle.com/javase/8/docs/api/java/time/format/package-summary.html
		
		*/
		
		
		
		/****************************************/
		/****	PARTIE GESTION DES DATES	*****/
		/****************************************/
		
		
		/*
		lien pour gerer la classe spécifique à une date : 
		-https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#format-java.time.format.DateTimeFormatter-
		*/
		
		LocalDate dateCourante = LocalDate.now();
		LocalDate date2 = dateCourante.plusWeeks(26).plusDays(19);
		
		System.out.println("Nous somme le " + dateCourante);
		System.out.println("Dans 26 semaines et 19 jours nous serons le "+date2 + System.lineSeparator());
		System.out.println("Nous somme le " + dateCourante.getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.FRANCE));
		System.out.println("Nous serons le "+date2.getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.FRANCE)+ System.lineSeparator());
		
		//date de naissance si dessous forme année,mois,jours
		LocalDate date3 = LocalDate.of(2000, 2, 3);
		System.out.println("Nombre de mois séparant ma naissance "+ChronoUnit.MONTHS.between(date3,dateCourante)+ System.lineSeparator());
		
		/* 
		lien pour la classe spécifique au format :
		-https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
		*/
		
		LocalDate date4 = date3.withMonth(1);
		System.out.println("Nous sommes le " +  dateCourante.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy",Locale.FRANCE)) + "." 
							+ System.lineSeparator()
							+ "Je suis né le " + date3.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy",Locale.FRANCE)) + "." + 
							System.lineSeparator()
							+ "Je suis quasiment né le " + date4.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy",Locale.FRANCE)) + "." 
							+ System.lineSeparator() + System.lineSeparator());
		
		
		
		/****************************************/
		/****	PARTIE GESTION DES HEURES	*****/
		/****************************************/
		
		/*
		lien pour gerer la classe spécifique à une heure : 
		-https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html
		*/
		
		LocalTime time1 = LocalTime.now();
		LocalTime time2 = time1.minusMinutes(19749);
		
		System.out.println("L'heure actuel est "+time1 + System.lineSeparator()
							+"Il y a 19749 minutes l'heure était "+time2 
							+ System.lineSeparator()+ System.lineSeparator());
		
		/************************************************/
		/****	PARTIE GESTION DES DATES ET HEURE	*****/
		/************************************************/
		
		/*
		lien pour gerer la classe spécifique à date et heure : 
		-https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html
		*/
		
		LocalDateTime ldt1 = LocalDateTime.now();
		LocalDateTime ldt2 = LocalDateTime.of(date3, LocalTime.of(5,40));

		System.out.println("La date et l'heure actuel est "+ldt1+ System.lineSeparator()
							+"je suis née le "+ldt2);
		
		System.out.println("la durée de ma vie en heures est de : "+ldt2.until(ldt1,ChronoUnit.HOURS)+" heures.");
		
		
		//tutoriel pour montrer un peu l'utilisation : https://www.vogella.com/tutorials/JavaDateTimeAPI/article.html
	}
}
