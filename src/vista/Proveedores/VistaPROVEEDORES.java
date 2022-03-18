package vista.Proveedores;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.ItemSelectable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.util.SystemOutLogger;

import modelo.BaseDatos;
import modelo.Categoria;
import modelo.Conexion;
import modelo.Producto;
import modelo.Proveedor;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import singleton.Singleton;
import vista.Inventario.VistaINVENTARIO;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.awt.event.ItemEvent;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class VistaPROVEEDORES extends JDialog {
	private DefaultComboBoxModel modeloProveedor;
	private BaseDatos base = new BaseDatos();
	private JButton btnAgregar, btnModificar, btnEliminar;
	private JTable tablaProveedores;
	private JTextField txtDesc;
	private JPanel panel, panel_4;
	private JLabel label;
	private DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JPanel panel_1;
	private JLabel label_1;
	private JButton btnSalir;
	private JLabel lblProveedore;

	public VistaPROVEEDORES(JFrame parent, boolean modal) {
		super(parent, modal);
		initComponent();
		encabezadoTabla();
		modelTabla();
		eventJTextField();
		eventJButton();

	}

	public VistaPROVEEDORES() {
		initComponent();
		encabezadoTabla();
		modelTabla();
		eventJTextField();
		eventJButton();

	}

	public void initComponent() {

		setBounds(100, 100, 700, 520);
		getContentPane().setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setBounds(12, 56, 676, 361);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, BorderLayout.CENTER);
		tablaProveedores = new JTable();
		tablaProveedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					seleccionarProveedorActionPerformed(e);
				}
			}
		});

		scrollPane_1.setViewportView(tablaProveedores);
		tablaProveedores.setModel(modeloTabla);

		// BOTONES
		btnModificar = new JButton("");
		btnAgregar = new JButton("");
		btnEliminar = new JButton("");

		panel_4 = new JPanel();
		panel_4.setBounds(209, 429, 243, 49);
		getContentPane().add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_4.add(btnAgregar);
		btnAgregar.setIcon(new ImageIcon(VistaPROVEEDORES.class.getResource("/iconos_1/Agregar.png")));

		panel_4.add(btnModificar);
		btnModificar.setIcon(new ImageIcon(VistaPROVEEDORES.class.getResource("/iconos_1/Editar.png")));

		panel_4.add(btnEliminar);
		btnEliminar.setIcon(new ImageIcon(VistaPROVEEDORES.class.getResource("/iconos_1/Eliminar.png")));
		
		btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(VistaPROVEEDORES.class.getResource("/iconos_1/ReHacer.png")));
		panel_4.add(btnSalir);

		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		lblProveedore = new JLabel("Proveedores");
		lblProveedore.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
		lblProveedore.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblProveedore);

		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(VistaPROVEEDORES.class.getResource("/iconos_1/zoom_iwwn.png")));
		panel_1.add(label_1);

		txtDesc = new JTextField();
		panel_1.add(txtDesc);
		txtDesc.setBounds(249, 12, 214, 32);
		txtDesc.setColumns(10);

		label = new JLabel("");
		panel_1.add(label);
		label.setBounds(267, 334, 46, 41);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/Iconos_Imagenes/zoom_iwwn.png"));
	}

	private void encabezadoTabla() {
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Descripcion");
		modeloTabla.addColumn("Direccion");
		modeloTabla.addColumn("Correo");
		modeloTabla.addColumn("Tel");
		TableColumn id = tablaProveedores.getColumn("ID");
		TableColumn direccion = tablaProveedores.getColumn("Direccion");
		TableColumn descripcion = tablaProveedores.getColumn("Descripcion");

		TableColumn ColProve = tablaProveedores.getColumn("Correo");
		TableColumn ColVenta = tablaProveedores.getColumn("Tel");
		descripcion.setMinWidth(300);
		id.setMinWidth(30);
		id.setMaxWidth(50);
		

	}

	private void eventJButton() {

		// AÑADIR BOTON
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaActionPerformed(e);
			}
		});
		// EDITAR PROVEEDOR
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarVentanaActionPerformed(e);
			}
		});
		// ELIMINAR PROVEEDOR
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProveedorActionPerformed(e);
			}
		});

	}

	private void eventJTextField() {
		txtDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtDescKeyReleased(e);
			}
		});

	}

	private void modelTabla() {

		ArrayList<Proveedor> listaProveedores = base.dameProveedoresTabla();
		int numeroProducto = listaProveedores.size();
		modeloTabla.setNumRows(numeroProducto);

		for (int i = 0; i < numeroProducto; i++) {

			Proveedor proveedor = listaProveedores.get(i);
			int id = proveedor.getIdProveedor();
			String nombre = proveedor.getNomProveedor();
			String dir = proveedor.getDirProveedor();
			String tel = proveedor.getTelProveedor();
			String correo = proveedor.getMailProveedor();

			modeloTabla.setValueAt(id, i, 0);
			modeloTabla.setValueAt(proveedor, i, 1);
			modeloTabla.setValueAt(dir, i, 2);
			modeloTabla.setValueAt(correo, i, 3);
			modeloTabla.setValueAt(tel, i, 4);

		}

	}


	private void abrirVentanaActionPerformed(ActionEvent e) {

		VistaADD_PROVEEDOR vistaD = new VistaADD_PROVEEDOR(new JFrame(), true);
		vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		vistaD.setVisible(true);
		vistaD.setAlwaysOnTop(false);
		vistaD.setLocationRelativeTo(null);
		modelTabla();

	}

	private void eliminarProveedorActionPerformed(ActionEvent e) {

		Proveedor proveedor = (Proveedor) modeloTabla.getValueAt(tablaProveedores.getSelectedRow(), 1);

		int d = JOptionPane.showConfirmDialog(null, "¿Desea eliminar un proveedor?");

		if (d == 0) {
			base.borrarProveedor(proveedor);
			LimpiarLista();
			modelTabla();
		}

	}

	public void seleccionarProveedorActionPerformed(MouseEvent e) {
		this.setVisible(false);
	}

	public Proveedor getSeleccion() {
		Proveedor proveedor = (Proveedor) modeloTabla.getValueAt(tablaProveedores.getSelectedRow(), 1);		
		return proveedor;
	}

	private void editarVentanaActionPerformed(ActionEvent e) {

		Proveedor proveedor = (Proveedor) modeloTabla.getValueAt(tablaProveedores.getSelectedRow(), 1);

		VistaEDITAR_PROVEEDOR vistaD = new Singleton().getVistaEDITAR_PROVEEDORES();

		vistaD.lblId.setText(String.valueOf(proveedor.getIdProveedor()));
		vistaD.txtTel.setText(proveedor.getTelProveedor());
		vistaD.txtEmail.setText(proveedor.getMailProveedor());
		vistaD.txtDesc.setText(proveedor.getNomProveedor());
		vistaD.txtDir.setText(proveedor.getDirProveedor());
		vistaD.setVisible(true);
		vistaD.setAlwaysOnTop(false);

	}

	private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtDescKeyReleased

		LimpiarLista();

		String cadena = txtDesc.getText();

		ArrayList<Proveedor> listaProveedor = base.obtenerProveedoresPorCriterio(cadena);

		int numeroProducto = listaProveedor.size();
		modeloTabla.setNumRows(numeroProducto);
		for (int i = 0; i < numeroProducto; i++) {

			Proveedor proveedor = listaProveedor.get(i);
			int clave = proveedor.getIdProveedor();
			String nomBre = proveedor.getNomProveedor();
			String dir = proveedor.getDirProveedor();
			String tel = proveedor.getTelProveedor();
			String mail = proveedor.getMailProveedor();

			modeloTabla.setValueAt(clave, i, 0);
			modeloTabla.setValueAt(proveedor, i, 1);
			modeloTabla.setValueAt(dir, i, 2);
			modeloTabla.setValueAt(tel, i, 3);
			modeloTabla.setValueAt(mail, i, 4);

		}
	}

	private void LimpiarLista() {
		int numFilas = modeloTabla.getRowCount();
		if (numFilas > 0) {
			for (int i = numFilas - 1; i < 0; i--) {
				modeloTabla.removeRow(i);
			}
		}
	}
}
