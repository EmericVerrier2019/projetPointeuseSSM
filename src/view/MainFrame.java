package view;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	public MainFrame(){
		super();
 
		build();//On initialise notre fenêtre
	}
 
	private void build(){
		setTitle("Gestion des employés"); //On donne un titre à l'application
		setSize(320,240); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(true); //On interdit la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
	}
}
