package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.DetailsDepartmentController;
import projetPointeuseSSM.Company;
import projetPointeuseSSM.Department;
import projetPointeuseSSM.TableModel.ManagementDepartmentTableModel;
import projetPointeuseSSM.TableModel.ManagementTableModel;

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
