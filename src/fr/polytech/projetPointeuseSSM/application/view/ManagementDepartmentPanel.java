package fr.polytech.projetPointeuseSSM.application.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.polytech.projetPointeuseSSM.application.controller.DetailsDepartmentController;
import fr.polytech.projetPointeuseSSM.application.model.Company;
import fr.polytech.projetPointeuseSSM.application.model.Department;
import fr.polytech.projetPointeuseSSM.application.model.tableModel.ManagementDepartmentTableModel;
import fr.polytech.projetPointeuseSSM.application.model.tableModel.ManagementTableModel;

public class ManagementDepartmentPanel extends JPanel{
	/**
	 * Numero de version imposé par JPanel
	 */
	private static final long serialVersionUID = 1L;

	private ManagementDepartmentTableModel tableModel;
	
	private Company company;
	
	public ManagementDepartmentPanel(Company company) {
		super();
		setName("Gestion des départements");
		this.company = company;
		this.tableModel = new ManagementDepartmentTableModel(company);
		IHMSetUp();
	}
	
	private void IHMSetUp() {
		JTable tb = new JTable(tableModel);
		tb.getTableHeader().setReorderingAllowed(false);
		tb.setModel(tableModel);
		tb.addMouseListener(new DetailsDepartmentController(company));
		
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tb),BorderLayout.CENTER);
		JButton addDepartmentButton = new JButton("ajouter");
		addDepartmentButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DetailsDepartmentView departmentForm = new DetailsDepartmentView(null, company);
				departmentForm.setVisible(true);
			}
		});
		this.add(addDepartmentButton,BorderLayout.SOUTH);
	}
	
	public void updateDepartmentTable() {
		tableModel.setDepartments(company.getListDepartment());
		tableModel.fireTableDataChanged();
	}
}
