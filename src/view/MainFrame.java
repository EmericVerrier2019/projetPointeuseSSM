package view;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	public MainFrame(){
		super();
 
		build();//On initialise notre fen�tre
	}
 
	private void build(){
		setTitle("Gestion des employ�s"); //On donne un titre � l'application
		setSize(320,240); //On donne une taille � notre fen�tre
		setLocationRelativeTo(null); //On centre la fen�tre sur l'�cran
		setResizable(true); //On interdit la redimensionnement de la fen�tre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit � l'application de se fermer lors du clic sur la croix
	}
}
