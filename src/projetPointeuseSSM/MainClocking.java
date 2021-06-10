package projetPointeuseSSM;


import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import view.timeClockView.TimeClockFrame;

public class MainClocking {
	
	public static TimeClockFrame fenetrePointeuse;
	
	public static void main(String[] args) {
		try {
			SwingUtilities.invokeLater(new Runnable() 
			{
				public void run() 
				{
					TimeClockFrame fenetrePointeuse = new TimeClockFrame();
					fenetrePointeuse.setVisible(true);
				}
			});
		}
		catch (Exception ex){
			JOptionPane.showMessageDialog(fenetrePointeuse,ex.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
