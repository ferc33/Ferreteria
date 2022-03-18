package vista.Login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.BaseDatos;
import modelo.Hash;
import modelo.Usuario;
import vista.Principal.VistaPRINCIPAL;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.KeyAdapter;

public class VistaLOGIN extends JFrame {

	private JPanel contentPane;
	public JTextField txtUsuario;
	public JButton btnRegistrarse = new JButton("Registrarse");
	public JPasswordField txtPassword;
	public JButton btnIniciarSesion;


	public VistaLOGIN() {

		initComponent();

	}

	public void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 382, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		txtUsuario = new JTextField();
		txtUsuario.setBounds(125, 85, 141, 27);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(46, 90, 60, 17);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(46, 133, 60, 17);
		contentPane.add(lblNewLabel_1);

		btnIniciarSesion = new JButton("Iniciar sesion");
		btnIniciarSesion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					iniciarVista();
				}
			}
		});

		btnIniciarSesion
				.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/Cliente.png"));

		btnIniciarSesion.setBounds(32, 179, 159, 41);
		contentPane.add(btnIniciarSesion);
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarRegistro();
			}
		});

		btnRegistrarse.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/couple.png"));

		btnRegistrarse.setBounds(203, 179, 141, 41);
		contentPane.add(btnRegistrarse);

		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					iniciarVista();
				}
			}
		});
		txtPassword.setBounds(125, 128, 141, 27);
		contentPane.add(txtPassword);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("Dunkin", Font.BOLD, 18));
		lblNewLabel_2.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/lock.png"));
		lblNewLabel_2.setBounds(122, 0, 203, 73);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(284, 90, 60, 17);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4
				.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/password2.png"));
		lblNewLabel_4.setBounds(284, 133, 41, 17);
		contentPane.add(lblNewLabel_4);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/mail_encryption2.png"));
		label.setBounds(284, 85, 44, 22);
		contentPane.add(label);
		setLocationRelativeTo(null);
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarVista();				
			}		
		});

	}
	
	//INICIA SESSION
	public void iniciarVista() {
		BaseDatos sql = new BaseDatos();
		Usuario usr = new Usuario();
		Date date = new Date();
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String pass = new String(txtPassword.getPassword());
		VistaPRINCIPAL vista = null;

		if (!txtUsuario.getText().equals("") && !pass.equals("")) {
			String nuevoPass = Hash.sha1(pass);
			usr.setUsuario(txtUsuario.getText());
			usr.setPassword(nuevoPass);
			usr.setLast_session(fechaHora.format(date));

			if (sql.login(usr)) {
				vista = new VistaPRINCIPAL(usr);
				vista.setVisible(true);
				dispose();

			} else {

				JOptionPane.showMessageDialog(null, "Datos Incorrectos");

			}
		}
	}
	
	//ABRE FRAME DE REGISTRO
	public void iniciarRegistro() {
		VistaREGISTRO_USUARIO v_registro = new VistaREGISTRO_USUARIO();
		v_registro.setVisible(true);
	}
	


}
