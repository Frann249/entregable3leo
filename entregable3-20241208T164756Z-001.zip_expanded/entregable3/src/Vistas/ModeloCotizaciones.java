package Vistas;

import java.util.List;


import javax.swing.table.DefaultTableModel;

public class ModeloCotizaciones extends DefaultTableModel{
	List<String> titulos;
	public ModeloCotizaciones(final Object [][] datos, final String [] titulos) {
		super(datos, titulos);
		
	}
	public Class getColumnClass(final int column) {
		return this.getValueAt(0, column).getClass();
 }
}
