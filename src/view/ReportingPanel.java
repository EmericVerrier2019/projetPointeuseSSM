package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import projetPointeuseSSM.Company;
import projetPointeuseSSM.TableModel.IsWorkingEmployeeCellRenderer;
import projetPointeuseSSM.TableModel.ManagementTableModel;
import projetPointeuseSSM.TableModel.ReportingTableModel;
import projetPointeuseSSM.TableModel.TimeEndReportingTableCellRenderer;

public class ReportingPanel extends JPanel{

	/**
	 * Numero de version imposé par JPanel
	 */
	private static final long serialVersionUID = 1L;

	private ReportingTableModel tableModel;
	
	private Company company;
	
	public ReportingPanel(Company company) {
		super();
		setName("Historique");
		this.company = company;
		this.tableModel = new ReportingTableModel(company);
		IHMSetUp();
	}
	
	private void IHMSetUp() {
		JTable tb = new JTable(tableModel);
		tb.getTableHeader().setReorderingAllowed(false);
		tb.setModel(tableModel);
		tb.getColumnModel().getColumn(3).setCellRenderer(new TimeEndReportingTableCellRenderer());
		tb.getColumnModel().getColumn(4).setCellRenderer(new TimeEndReportingTableCellRenderer());
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tb),BorderLayout.CENTER);
	}
}
