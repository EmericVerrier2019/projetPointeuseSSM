package fr.polytech.projetPointeuseSSM.timeClocking;


import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import fr.polytech.projetPointeuseSSM.timeClocking.view.TimeClockFrame;

public class MainClocking {
	
	public static TimeClockFrame fenetrePointeuse;
	
	public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() 
			{
				public void run() 
				{
					try 
					{
						TimeClockFrame fenetrePointeuse = new TimeClockFrame();
						fenetrePointeuse.setVisible(true);
					}		
					catch (Exception ex){
						JOptionPane.showMessageDialog(fenetrePointeuse,ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
						ex.printStackTrace();
					}
				}
			});


	}
}