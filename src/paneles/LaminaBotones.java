package paneles;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class LaminaBotones extends JPanel {

	
	public LaminaBotones() {
		setBackground(Color.WHITE);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		initComponent();

	}
	
	public void initComponent() {
						FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);
						flowLayout.setAlignOnBaseline(true);
						setLayout(flowLayout);
				
						btnInsertar = new JLabel("");
						btnInsertar.setLabelFor(this);
						
						btnInsertar.setIcon(new ImageIcon(LaminaBotones.class.getResource("/iconos_1/Agregar.png")));
						btnInsertar.setHorizontalAlignment(SwingConstants.CENTER);
						add(btnInsertar);
		
				btnEditar = new JLabel("");
				btnEditar.setLabelFor(this);
				btnEditar.setIcon(new ImageIcon(LaminaBotones.class.getResource("/iconos_1/Editar.png")));
				btnEditar.setHorizontalAlignment(SwingConstants.CENTER);
				add(btnEditar);
						
								btnEliminar = new JLabel("");
								btnEliminar.setLabelFor(this);
								btnEliminar.setHorizontalAlignment(SwingConstants.CENTER);
								btnEliminar.setIcon(new ImageIcon(LaminaBotones.class.getResource("/iconos_1/Eliminar.png")));
								add(btnEliminar);
	}
	//EVENTO DE BOTONES
	public void eventos() {
		btnInsertar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
	}

	//POJO BOTONES
	
	public JLabel getBtnInsertar() {
		return btnInsertar;
	}

	public void setBtnInsertar(JLabel btnInsertar) {
		this.btnInsertar = btnInsertar;
	}

	public JLabel getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JLabel btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JLabel getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JLabel btnEliminar) {
		this.btnEliminar = btnEliminar;
	}



	private JLabel btnInsertar, btnEditar, btnEliminar;
}
