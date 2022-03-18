package vista.Clientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import modelo.BaseDatos;
import modelo.Categoria;
import modelo.Clientes;
import modelo.Producto;
import modelo.Proveedor;
import singleton.Singleton;
import vista.Categorias.VistaCATEGORIA;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Label;
import java.awt.Shape;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import modelo.Clientes;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaCLIENTES extends JDialog {
	private JTable tablaClientes;
	private JPanel panel;
	private JLabel label, lblBuscar;
	private JButton btnEditar, btnEliminar, btnAgregar;
	private BaseDatos base = new BaseDatos();
	private Clientes cliente;
	private JScrollPane jScrollPane1;
	private JTextField txtBuscar;

	public VistaCLIENTES(JFrame parent, boolean modal) {
		super(parent, modal);
		setAlwaysOnTop(true);
		initComponent();
		eventButton();
		encabezadoTabla();
		modelTabla();
		
		
	}

	public VistaCLIENTES() {
		setAlwaysOnTop(true);
		initComponent();
		eventButton();
		encabezadoTabla();
		modelTabla();
	}

	private DefaultTableModel modelTabla = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton button;
	private JLabel lblClientes;

	public void initComponent() {

		setSize(899, 569);
		getContentPane().setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setBounds(12, 97, 885, 267);
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		jScrollPane1 = new JScrollPane();
		jScrollPane1.setAlignmentY(Component.TOP_ALIGNMENT);
		tablaClientes = new JTable();
		jScrollPane1.setViewportView(tablaClientes);
		tablaClientes.setModel(modelTabla);
		panel.add(jScrollPane1);

		panel_1 = new JPanel();
		panel_1.setBounds(12, 376, 860, 73);
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnAgregar = new JButton("");
		panel_1.add(btnAgregar);
		btnAgregar.setIcon(new ImageIcon(VistaCLIENTES.class.getResource("/iconos_1/Agregar.png")));

		btnEditar = new JButton("");
	
		panel_1.add(btnEditar);
		btnEditar.setIcon(new ImageIcon(VistaCLIENTES.class.getResource("/iconos_1/Editar.png")));

		btnEliminar = new JButton();
		panel_1.add(btnEliminar);
		btnEliminar.setIcon(new ImageIcon(VistaCLIENTES.class.getResource("/iconos_1/Eliminar.png")));
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		button.setIcon(new ImageIcon(VistaCLIENTES.class.getResource("/iconos_1/ReHacer.png")));
		panel_1.add(button);

		panel_2 = new JPanel();
		panel_2.setBounds(12, 12, 613, 50);
		getContentPane().add(panel_2, BorderLayout.NORTH);
		
		lblClientes = new JLabel("Clientes");
		lblClientes.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblClientes.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(lblClientes);
		lblBuscar = new JLabel("");
		panel_2.add(lblBuscar);
		lblBuscar.setIcon(new ImageIcon(VistaCLIENTES.class.getResource("/iconos_1/zoom_iwwn.png")));

		txtBuscar = new JTextField();
		panel_2.add(txtBuscar);
		txtBuscar.setColumns(10);
		label = new JLabel("");
		panel_2.add(label);
		label.setBounds(646, 155, 60, 17);

	}

	// CIERRA LA VENTANA
	public void seleccionarCliente(MouseEvent e) {
		this.setVisible(false);
	}

	// OBTIENE EL CLIENTE SELECCIONADO
	public Clientes getSeleccion() {

		Clientes cliente = (Clientes) modelTabla.getValueAt(tablaClientes.getSelectedRow(), 1);

		return cliente;
	}

	// DEFINIFIMOS LOS EVENTOS
	public void eventButton() {

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirEditarClientes(e);
			}

		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes cliente = (Clientes) modelTabla.getValueAt(tablaClientes.getSelectedRow(), 1);
				base.eliminarCliente(cliente.getIdCliente());
				modelTabla();
			}
		});
		// BOTON QUE ABRE JDIALOG DE AÑADIR CLIENTE
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaADD_CLIENTE vista = new Singleton().getVistaAÑADIR_CLIENTE();
				vista.setLocationRelativeTo(null);
				vista.setVisible(true);
				modelTabla();
			}
		});

		// CLICK SOBRE LA TABLA
		tablaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					seleccionarCliente(e);

				}

			}

		});
		//BUSCA EL CLIENTE
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscaCliente(e);
			}
		});
	}
	
	private void LimpiarLista() {
		int numFilas = modelTabla.getRowCount();
		if (numFilas > 0) {
			for (int i = numFilas - 1; i < 0; i--) {
				modelTabla.removeRow(i);
			}
		}
	}


	private void abrirEditarClientes(ActionEvent e) {
		Clientes clientes = (Clientes) modelTabla.getValueAt(tablaClientes.getSelectedRow(), 1);
		VistaEDITAR_CLIENTE vistaD = new VistaEDITAR_CLIENTE(new VistaCLIENTES(), true);
		
		vistaD.setLocationRelativeTo(new VistaCATEGORIA(new JFrame(), true));
		vistaD.lblID.setText(String.valueOf(clientes.getIdCliente()));
		vistaD.txtNombre.setText(clientes.getNombre());
		vistaD.txtDir.setText(clientes.getDireccion());
		vistaD.txtEmail.setText(clientes.getMail());
		vistaD.txtTelefono.setText(clientes.getTel());
		vistaD.txtCuit.setText(clientes.getCuit());
		
		vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		vistaD.setVisible(true);
		modelTabla();
	
		
	}
	
	private void buscaCliente(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtDescKeyReleased

		LimpiarLista();

		String cadena = txtBuscar.getText();

		ArrayList<Clientes> listaProducto = base.getClientes(cadena);
		int numeroProducto = listaProducto.size();

		modelTabla.setNumRows(numeroProducto);
		Producto producto = new Producto();
		for (int i = 0; i < numeroProducto; i++) {

			cliente = listaProducto.get(i);
			int id = cliente.getIdCliente();
			String nombre = cliente.getNombre();
			String correo = cliente.getMail();
			String dir = cliente.getDireccion();
			String tel = cliente.getTel();
			String dni = cliente.getCuit();

			modelTabla.setValueAt(id, i, 0);
			modelTabla.setValueAt(cliente, i, 1);
			modelTabla.setValueAt(correo, i, 2);
			modelTabla.setValueAt(tel, i, 3);
			modelTabla.setValueAt(dni, i, 4);
			modelTabla.setValueAt(dir, i, 5);
		}
	}
	
	public void modelTabla() {

		ArrayList<Clientes> listaProducto = base.getClientes();
		int numeroProducto = listaProducto.size();

		modelTabla.setNumRows(numeroProducto);
		Producto producto = new Producto();
		for (int i = 0; i < numeroProducto; i++) {

			cliente = listaProducto.get(i);
			int id = cliente.getIdCliente();
			String nombre = cliente.getNombre();
			String correo = cliente.getMail();
			String dir = cliente.getDireccion();
			String tel = cliente.getTel();
			String dni = cliente.getCuit();

			modelTabla.setValueAt(id, i, 0);
			modelTabla.setValueAt(cliente, i, 1);
			modelTabla.setValueAt(tel	, i, 2);
			modelTabla.setValueAt(dir, i, 3);
			modelTabla.setValueAt(dni, i, 4);
			modelTabla.setValueAt(correo, i, 5);
		}

	}

	public void encabezadoTabla() {

		modelTabla.addColumn("Id");
		modelTabla.addColumn("Nombre");
		modelTabla.addColumn("Telefono");
		modelTabla.addColumn("Direccion");
		modelTabla.addColumn("DNI/CUIT");
		modelTabla.addColumn("Correo");

		TableColumn id = tablaClientes.getColumn("Id");
		TableColumn Nombre = tablaClientes.getColumn("Nombre");
		TableColumn Dir = tablaClientes.getColumn("Direccion");
		TableColumn Cuit = tablaClientes.getColumn("DNI/CUIT");
		TableColumn Tel = tablaClientes.getColumn("Telefono");
		TableColumn mail = tablaClientes.getColumn("Correo");
		id.setMaxWidth(50);
		
		
	}
}
