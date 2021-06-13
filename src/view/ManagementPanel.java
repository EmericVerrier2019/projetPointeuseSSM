package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.DetailsEmployeeController;
import controller.addEmployeeController;
import projetPointeuseSSM.Company;
import projetPointeuseSSM.Employee;
import projetPointeuseSSM.Main;
import projetPointeuseSSM.TableModel.IsWorkingEmployeeCellRenderer;
import projetPointeuseSSM.TableModel.ManagementTableModel;


public class ManagementPanel extends JPanel{
	
	/**
	 * Numero de version impos� par JPanel
	 */
	private static final long serialVersionUID = 1L;

	private ManagementTableModel tableModel;
	
	private Company company;
	private JTable tb;
	
	private MainFrame mainFrame;
	
	public ManagementPanel(Company company, MainFrame mainFrame) {
		super();
		setName("Accueil");
		this.company = company;
		this.tableModel = new ManagementTableModel(company);
		this.mainFrame = mainFrame;
		IHMSetUp();
	}
	
		
	private void IHMSetUp() {
			
		tb = new JTable(tableModel);
		tb.getTableHeader().setReorderingAllowed(false);
		tb.setModel(tableModel);
		tb.getColumnModel().getColumn(3).setCellRenderer(new IsWorkingEmployeeCellRenderer()); //on d�finie un rendu pour la derni�re colonne
		tb.addMouseListener(new DetailsEmployeeController(company,mainFrame));
		
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tb),BorderLayout.CENTER);
		JButton addEmployeeButton = new JButton("ajouter");
		addEmployeeButton.addActionListener(
			//cr�ation du formulaire d'inscription
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Pour ajouter un employ� on utilise la vue des details avec null pour le param�tre de l'employ�
					DetailsEmployeeView addEmployeeView = new DetailsEmployeeView(null, company,mainFrame);
					addEmployeeView.setVisible(true);	
				}
			}
		);
		this.add(addEmployeeButton,BorderLayout.SOUTH);
		
	}
	
	
	public void updateEmployeeTableModel(ArrayList<Employee> listEmployee) {
		
		tableModel.setEmployees(listEmployee);
		tableModel.fireTableDataChanged();
		tableModel.fireTableCellUpdated(1,1);
		validate();
		repaint();
	}
	
}
