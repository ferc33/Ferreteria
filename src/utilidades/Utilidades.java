package utilidades;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public interface Utilidades {

	// GENERA UN DESCUENTO POR BONIFICACION
	public static double descuento(double bon) {
		double bonificacion = (1 - (bon / 100));
		return bonificacion;
	}

	// GENERA UN AUMENTO POR FLETE U OTRO
	public static double formacionCosto(double f) {
		double formacionCosto = (1 + (f / 100));
		return formacionCosto;
	}

	// DEVUELVE costo
	public static Double costo(JTextField plista, JTextField txtBon1,JTextField txtBon2,JTextField txtBon3,JTextField txtBon4) {
		Double bon1 = Double.parseDouble(txtBon1.getText());
		Double bon2 = Double.parseDouble(txtBon2.getText());
		Double bon3 = Double.parseDouble(txtBon3.getText());
		Double bon4 = Double.parseDouble(txtBon4.getText());
		double pCosto = 0;
		double pLista = Double.parseDouble(plista.getText());
		double pVenta = 0;
	
			pCosto = pLista * (1 - (bon1 / 100)) * (1 - (bon2 / 100)) * (1 - (bon3 / 100)) * (1 - (bon4 / 100)) ;

		return pCosto;
	}

	// DEVUELVE PRECIO DE VENTA
	public static Double precioVenta(JTextField txtC, JTextField txtB1, JTextField txtB2, JTextField txtB3,JTextField txtB4, JTextField txtI, JTextField txtF, JTextField txtG) {
		double bon1 = Double.parseDouble(txtB1.getText());
		double bon2 = Double.parseDouble(txtB2.getText());
		double bon3 = Double.parseDouble(txtB3.getText());
		double bon4 = Double.parseDouble(txtB4.getText());
		double pCosto = Double.parseDouble(txtC.getText());
		double iva = Double.parseDouble(txtI.getText());
		double flete = Double.parseDouble(txtF.getText());
		double gan = Double.parseDouble(txtG.getText());
		double pVenta =pVenta = pCosto * descuento(bon1) * descuento(bon2) * descuento(bon3) * descuento(bon4) * formacionCosto(flete)
				* formacionCosto(iva) * formacionCosto(gan);

		JOptionPane.showMessageDialog(null, pVenta);

		return pVenta;
	}
	
}
