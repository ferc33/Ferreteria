package utilidades;

import java.awt.Component;
import java.awt.event.KeyEvent;

public class Focus {

	
	// CAMBIAR DE COMPONENTE
	public static void hacerFoco(KeyEvent e, Component antecesor, Component sucesor) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {

			sucesor.requestFocus();
		} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ALT) {
			antecesor.requestFocus();

		}
	}	
	
}