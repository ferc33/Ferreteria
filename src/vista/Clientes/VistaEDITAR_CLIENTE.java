package vista.Clientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.BaseDatos;
import modelo.Clientes;

import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class VistaEDITAR_CLIENTE extends JDialog {

	public JPanel contentPanel, buttonPane, panel;
	public JTextField txtCuit, txtTelefono, txtEmail, txtDir, txtNombre;
	public JLabel label2, label1, label4, label3, lblCuit, lblID;
	private JButton btnEditar, btnSalir;
	private BaseDatos base;

	public VistaEDITAR_CLIENTE() {
		initComponent();
	}

	public VistaEDITAR_CLIENTE(VistaCLIENTES parent, boolean modal) {
		super(parent, modal);
		initComponent();
		eventButton();

	}

	public void initComponent() {
		base = new BaseDatos();
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().setLayout(null);
		buttonPane = new JPanel();
		buttonPane.setBounds(0, 260, 440, 10);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		txtCuit = new JTextField();
		txtCuit.setColumns(10);
		txtCuit.setBounds(102, 179, 257, 29);
		getContentPane().add(txtCuit);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(102, 141, 257, 29);
		getContentPane().add(txtTelefono);

		txtEmail = new JTextField();
		txtEmail.setBounds(102, 107, 257, 26);
		getContentPane().add(txtEmail);

		txtDir = new JTextField();
		txtDir.setBounds(102, 69, 257, 29);
		getContentPane().add(txtDir);

		txtNombre = new JTextField();
		txtNombre.setBounds(102, 36, 257, 26);
		getContentPane().add(txtNombre);

		label2 = new JLabel();
		label2.setText("Nombre:");
		label2.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		label2.setBounds(19, 36, 54, 24);
		getContentPane().add(label2);

		label4 = new JLabel();
		label4.setText("Dirección:");
		label4.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		label4.setBounds(10, 68, 65, 24);
		getContentPane().add(label4);

		label1 = new JLabel();
		label1.setText("Email:");
		label1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		label1.setBounds(22, 106, 42, 24);
		getContentPane().add(label1);

		label3 = new JLabel();
		label3.setText("Teléfono:");
		label3.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		label3.setBounds(14, 144, 60, 24);
		getContentPane().add(label3);

		lblCuit = new JLabel("Dni/Cuit :");
		lblCuit.setVerticalAlignment(SwingConstants.TOP);
		lblCuit.setHorizontalAlignment(SwingConstants.LEFT);
		lblCuit.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblCuit.setBounds(14, 188, 72, 18);
		getContentPane().add(lblCuit);

		panel = new JPanel();
		panel.setBounds(289, 213, 148, 46);
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		btnEditar = new JButton("");
		panel.add(btnEditar);
		btnEditar.setIcon(new ImageIcon(VistaEDITAR_CLIENTE.class.getResource("/iconos_1/Aceptar.png")));

		btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(VistaEDITAR_CLIENTE.class.getResource("/iconos_1/ReHacer.png")));
		panel.add(btnSalir);

		lblID = new JLabel("");
		lblID.setBounds(377, 40, 60, 17);
		getContentPane().add(lblID);

	}

	private void eventButton() {
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(lblID.getText());
				String nombre = txtNombre.getText();
				String direccion = txtDir.getText();
				String tel = txtTelefono.getText();
				String mail = txtEmail.getText();
				String cuit = txtCuit.getText();
				Clientes cliente = new Clientes(id,nombre,direccion,tel,mail,cuit);

				base.actualizarCliente(cliente);

				dispose();

			}
		});
	}
}
