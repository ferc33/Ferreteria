package vista.Login;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import modelo.BaseDatos;
import modelo.Hash;
import modelo.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import modelo.Validaciones;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class VistaREGISTRO_USUARIO extends JFrame {

	private JPanel contentPane;
	public  JTextField txtUsuario;
	public JTextField txtCorreo;
	public JTextField txtNombre;
	public JPasswordField txtPassword;
	public  JPasswordField txtPasswordConfirm;	
	public JComboBox comboBox;
	private Usuario usr;

	private BaseDatos bd;

	private VistaREGISTRO_USUARIO v_registro;
	public VistaREGISTRO_USUARIO() {
		initComponent();

	}
	public VistaREGISTRO_USUARIO(Usuario usr) {
		initComponent();	
		this.usr=usr;
		v_registro = new VistaREGISTRO_USUARIO();
		bd = new BaseDatos();
	}
	public void initComponent() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(5, 5, 390, 222);
		contentPane.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel_3.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(5, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(106, 12, 209, 198);
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(6, 0, 6, 0));

		txtUsuario = new JTextField();
		panel_4.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtPassword = new JPasswordField();
		panel_4.add(txtPassword);

		txtPasswordConfirm = new JPasswordField();
		panel_4.add(txtPasswordConfirm);

		txtNombre = new JTextField();
		panel_4.add(txtNombre);
		txtNombre.setColumns(10);

		txtCorreo = new JTextField();
		panel_4.add(txtCorreo);
		txtCorreo.setColumns(10);

		comboBox = new JComboBox();
		panel_4.add(comboBox);
		comboBox.addItem("Administrador");
		comboBox.addItem("Usuario");

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(12, 12, 95, 198);
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(6, 0, 0, 0));

		JLabel lblNewLabel = new JLabel("Usuario");
		panel_5.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		panel_5.add(lblPassword);

		JLabel lblConfirmar = new JLabel("Confirmar");
		panel_5.add(lblConfirmar);

		JLabel lblNombre = new JLabel("Nombre");
		panel_5.add(lblNombre);

		JLabel lblCorreo = new JLabel("Correo");
		panel_5.add(lblCorreo);

		JLabel lblPrivilegio = new JLabel("Privilegio");
		panel_5.add(lblPrivilegio);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(327, 12, 51, 198);
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(6, 1, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(
				new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_3/administrator.png"));
		panel_6.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_3/key.png"));
		panel_6.add(lblNewLabel_2);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_3/encrypt2.png"));
		panel_6.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(
				"/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_3/lessons_learned_database.png"));
		panel_6.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(
				new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_3/infected_mail.png"));
		panel_6.add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_3/encrypt3.png"));
		panel_6.add(label_3);

		JButton btnRegistrar = new JButton("Registrar");	
		btnRegistrar.setIcon(
				new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_2/diskette.png"));
		btnRegistrar.setBounds(47, 231, 125, 37);
		contentPane.add(btnRegistrar);

		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setIcon(
				new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_2/sign_out.png"));
		btnNewButton.setBounds(235, 231, 115, 37);
		contentPane.add(btnNewButton);
		
		//EVENTOS
		//REGISTRAR USUARIO
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					bd = new BaseDatos();
					Usuario usuario = new Usuario();
					String pass = new String(txtPassword.getPassword());
					String conf = new String(txtPasswordConfirm.getPassword());
					
					if (txtNombre.equals("") || txtPassword.equals("")
							|| txtPasswordConfirm.equals("") || txtUsuario.equals("")
							|| txtCorreo.equals("")) {
						JOptionPane.showMessageDialog(null, "Hay campos vacios");
					} else {
						if (pass.equals(conf)) {						
								if (new Validaciones().ValidarEmail(txtCorreo.getText())) {
									String nuevoPass = Hash.sha1(pass);
									
									usuario.setUsuario(txtUsuario.getText());
									usuario.setPassword(nuevoPass);
									usuario.setNombre(txtNombre.getText());
									usuario.setCorreo(txtCorreo.getText());
									usuario.setId_tipo(comboBox.getSelectedIndex());

									if (bd.registrar(usuario)) {
										JOptionPane.showMessageDialog(null, "Usuario Registrado");
										limpiar();
									} else {// PERTENECE A VALIDACION DE USUARIO
										JOptionPane.showMessageDialog(null, "Error al registrar");
									}
								} else {// PERTENECE A VALIDACION DE CORREO
									JOptionPane.showMessageDialog(null, "El correo es incorreto");
								}
							 

						} else {
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
						}

					}
				}

					});
	
	}
	
	// LIMPIA LOS CAMPOS
	public void limpiar() {
		txtNombre.setText("");
		txtPassword.setText("");
		txtPasswordConfirm.setText("");
		txtCorreo.setText("");
		txtUsuario.setText("");

	}


	
		}
				
	
