package vista.Categorias;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.BaseDatos;
import modelo.Categoria;
import modelo.Proveedor;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VistaEDITAR_CATEGORIA extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField txtDesc;
	protected JLabel lblId, lblCategoria;
	protected BaseDatos base;
	private DefaultComboBoxModel modeloCategoria = null;
	private JButton btnModificar;

	public VistaEDITAR_CATEGORIA(JFrame parent,boolean modal) {	
		super(parent,modal);
		base = new BaseDatos();
		txtDesc = new JTextField();
		btnModificar = new JButton("Modificar");
		lblId = new JLabel("");
		lblCategoria = new JLabel("Categoria");
		
		setBounds(100, 100, 295, 156);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnModificar.setIcon(new ImageIcon(VistaEDITAR_CATEGORIA.class.getResource("/iconos_1/Aceptar.png")));
		btnModificar.setBounds(80, 62, 135, 54);
		contentPanel.add(btnModificar);

		txtDesc.setBounds(80, 12, 144, 27);
		contentPanel.add(txtDesc);
		txtDesc.setColumns(10);

		lblCategoria.setBounds(12, 17, 60, 17);
		contentPanel.add(lblCategoria);

		lblId.setBounds(22, 51, 60, 17);
		contentPanel.add(lblId);

		// BOTON EDITAR CATEGORIA
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(lblId.getText());
				String nombre = txtDesc.getText();
				Categoria categoria = new Categoria(id, nombre);

				base.actualizarCategoria(categoria);
				dispose();
			}
		});

	}

	public void modelProveedores() {

		modeloCategoria = new DefaultComboBoxModel(base.dameProveedores());

	}
}
