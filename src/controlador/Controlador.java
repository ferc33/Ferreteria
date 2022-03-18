package controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controlador {

	//METODO QUE ELIMINA UNA FILA
		public void eliminarFila(DefaultTableModel model, JTable tabla, JLabel label) {
			int numFilas = tabla.getSelectedRow();
			int filaSelec = model.getRowCount();
			
			if (filaSelec > 0) {
				int quitar = JOptionPane.showConfirmDialog(null, "¿ Desea eliminar el articulo seleccionado ?");
				if (quitar == 0) {
					if (numFilas == -1) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar el articulo que desea quitar", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						model.removeRow(numFilas);
						double sumatoria = getImporteTotal(model,5);				
						label.setText(String.valueOf(sumatoria));
					}
				}
			}
		}
		//METODO QUE DEVUELVE EL IMPORTE TOTAL
		public double getImporteTotal(DefaultTableModel model, int columna) {

			int numFilas = model.getRowCount();
			double sumatoria = 0;
			for (int i = 0; i < numFilas; i++) {
				double importe = (double) model.getValueAt(i, columna);
				sumatoria += (importe);

			}

			return sumatoria;

		}
	
	
}
