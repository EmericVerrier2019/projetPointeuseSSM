package fr.polytech.projetPointeuseSSM.application.view;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import fr.polytech.projetPointeuseSSM.application.controller.FilterReportingController;
import fr.polytech.projetPointeuseSSM.application.model.Company;
import fr.polytech.projetPointeuseSSM.application.model.tableModel.IsWorkingEmployeeCellRenderer;
import fr.polytech.projetPointeuseSSM.application.model.tableModel.ManagementTableModel;
import fr.polytech.projetPointeuseSSM.application.model.tableModel.ReportingTableModel;
import fr.polytech.projetPointeuseSSM.application.model.tableModel.TimeEndReportingTableCellRenderer;

public class ReportingPanel extends JPanel{

	/**
	 * Numero de version imposé par JPanel
	 */
	private static final long serialVersionUID = 1L;

	private ReportingTableModel tableModel;
	
	private Company company;
	
	public static final String[] FILTER_COMBO_BOX = new String[] {"Aujourd'hui","Tous les jours"};
	
	public ReportingPanel(Company company) {
		super();
		setName("Historique");
		this.company = company;
		this.tableModel = new ReportingTableModel(company);
		IHMSetUp();
	}
	
	private void IHMSetUp() {
		
		//creation du choix entre tous les pointages et ceux d'aujourd'hui
		
		JPanel choiceDisplayHistoryPanel = new JPanel();
		choiceDisplayHistoryPanel.setLayout(new BorderLayout());
		
		JComboBox<String> choiceFilterDisplay = new JComboBox<String>(FILTER_COMBO_BOX);
		choiceFilterDisplay.addItemListener(new FilterReportingController(tableModel));
		choiceDisplayHistoryPanel.add(choiceFilterDisplay,BorderLayout.WEST);
		//création de la table des historiques
		
		JTable tb = new JTable(tableModel);
		tb.getTableHeader().setReorderingAllowed(false);
		tb.setModel(tableModel);
		tb.getColumnModel().getColumn(3).setCellRenderer(new TimeEndReportingTableCellRenderer());
		tb.getColumnModel().getColumn(4).setCellRenderer(new TimeEndReportingTableCellRenderer());
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tb),BorderLayout.CENTER);
		this.add(choiceDisplayHistoryPanel,BorderLayout.NORTH);
	}
	
	public void updateReportingTable() {
		tableModel.fireTableDataChanged();
	}
}
