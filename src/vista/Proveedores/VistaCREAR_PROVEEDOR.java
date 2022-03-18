package vista.Proveedores;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.BaseDatos;
import modelo.Proveedor;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

public class VistaCREAR_PROVEEDOR extends JDialog {

	protected final JPanel contentPanel = new JPanel();
	public JTextField txtNombre;
	public JTextField txtTel;
	public JTextField txtEmail;
	public JTextField txtDir;
	public BaseDatos base;

	

	public VistaCREAR_PROVEEDOR(JDialog parent,boolean modal) {
		super(parent,modal);
		base = new BaseDatos();
		setBounds(100, 100, 370, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
			txtNombre.setBounds(90, 12, 214, 35);
			contentPanel.add(txtNombre);
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 21, 60, 17);
			contentPanel.add(lblNombre);
		}
		{
			JLabel lblTel = new JLabel("Tel:");
			lblTel.setBounds(12, 50, 60, 17);
			contentPanel.add(lblTel);
		}
		{
			txtTel = new JTextField();
			txtTel.setColumns(10);
			txtTel.setBounds(90, 48, 214, 29);
			contentPanel.add(txtTel);
		}
		{
			JLabel lblDireccin = new JLabel("Dirección");
			lblDireccin.setBounds(12, 112, 60, 17);
			contentPanel.add(lblDireccin);
		}
		{
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setBounds(12, 83, 60, 17);
			contentPanel.add(lblEmail);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(90, 77, 214, 29);
			contentPanel.add(txtEmail);
		}
		{
			txtDir = new JTextField();
			txtDir.setColumns(10);
			txtDir.setBounds(90, 106, 214, 34);
			contentPanel.add(txtDir);
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(489, 185, 52, 27);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(553, 185, 74, 27);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		JButton btnGuardar = new JButton("Guardar");		
		btnGuardar.setIcon(new ImageIcon(VistaCREAR_PROVEEDOR.class.getResource("/iconos_1/Aceptar.png")));
		btnGuardar.setBounds(130, 152, 125, 42);
		contentPanel.add(btnGuardar);
		
		//INGRESAR PROVEEDOR
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String dir = txtDir.getText();
				String mail = txtEmail.getText();
				String tel = txtTel.getText();

				Proveedor proveedor = new Proveedor(nombre, dir, mail, tel);
				base.insertarProveedor(proveedor);		
				dispose();
				
			}
		});
	}
	
}
