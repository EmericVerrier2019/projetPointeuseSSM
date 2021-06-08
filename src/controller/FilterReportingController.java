package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import projetPointeuseSSM.TableModel.ReportingTableModel;


public class FilterReportingController implements ItemListener {
	
	
	/**
	 * Modèle de la table
	 */
	private ReportingTableModel model;
	
	/**
	 * Initialise le constructeur
	 * @param view Vue relié à ce contrôleur
	 * @param model Modèle de la table
	 */
	public FilterReportingController(ReportingTableModel model) {
		this.model = model;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
	          String choice = (String) event.getItem();
	          if (choice == "Aujourd'hui") {
	        	  model.setAllWorkdays(false);
	        	  model.fireTableDataChanged();
	          }else {
	        	  model.setAllWorkdays(true);
	        	  model.fireTableDataChanged();
	          }
	       }
	}
}
