package vista.Proveedores;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.BaseDatos;
import modelo.Proveedor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class VistaEDITAR_PROVEEDOR extends JDialog {
	public static JTextField txtDesc;
	public static JTextField txtTel;
	public static JTextField txtEmail;
	public static JTextField txtDir;
	public BaseDatos base;
	public JLabel lblId, lblNombre, lblTel, lblEmail, lblDireccin;
	public JButton btnUpdate;

	public VistaEDITAR_PROVEEDOR(JFrame parent, boolean modal) {
		super(parent, modal);
		base = new BaseDatos();
		initComponent();
		eventosBOTONES();

	}

	/**
	 * @wbp.parser.constructor
	 */
	public VistaEDITAR_PROVEEDOR(VistaPROVEEDORES parent, boolean modal) {
		super(parent, modal);
		base = new BaseDatos();
		initComponent();
		eventosBOTONES();

	}

	public void eventosBOTONES() {
		// Guardar PROVEEDOR
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(lblId.getText());
				String nombre = txtDesc.getText();
				String dir = txtDir.getText();
				String mail = txtEmail.getText();
				String tel = txtTel.getText();

				Proveedor proveedor = new Proveedor(id, nombre, dir, mail, tel);

				base.updateProveedor(proveedor);
				
				dispose();
				
			}
		});

	}

	public void initComponent() {
		setBounds(100, 100, 360, 268);
		getContentPane().setLayout(null);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 22, 60, 17);
		getContentPane().add(lblNombre);

		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(91, 8, 214, 40);
		getContentPane().add(txtDesc);

		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(91, 45, 214, 35);
		getContentPane().add(txtTel);

		lblTel = new JLabel("Tel:");
		lblTel.setBounds(12, 51, 60, 17);
		getContentPane().add(lblTel);

		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 84, 60, 17);
		getContentPane().add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(91, 78, 214, 29);
		getContentPane().add(txtEmail);

		lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(12, 113, 60, 17);
		getContentPane().add(lblDireccin);

		txtDir = new JTextField();
		txtDir.setColumns(10);
		txtDir.setBounds(91, 105, 214, 34);
		getContentPane().add(txtDir);

		lblId = new JLabel("");
		lblId.setBounds(337, 31, 21, 17);
		getContentPane().add(lblId);
		
		JPanel panel = new JPanel();
		panel.setBounds(210, 166, 117, 46);
		getContentPane().add(panel);
				panel.setLayout(new GridLayout(0, 2, 0, 0));
		
				btnUpdate = new JButton("");
				panel.add(btnUpdate);
				btnUpdate.setIcon(new ImageIcon(VistaEDITAR_PROVEEDOR.class.getResource("/iconos_1/Aceptar.png")));
				
				JButton button = new JButton("");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				button.setIcon(new ImageIcon(VistaEDITAR_PROVEEDOR.class.getResource("/iconos_1/ReHacer.png")));
				panel.add(button);
	}
}
