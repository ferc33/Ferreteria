package vista.ReporteProveedores;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.BaseDatos;
import modelo.Categoria;
import modelo.Conexion;
import modelo.Producto;
import modelo.Proveedor;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import singleton.Singleton;
import vista.Proveedores.VistaPROVEEDORES;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaREPORTE_PROVEEDORES extends JInternalFrame {

	private JPanel contentPane;
	private Connection conn = null;
	private Conexion con;
	private JTable tablaProveedores;
	private BaseDatos base;
	private DefaultComboBoxModel modeloProveedor;
	private DefaultComboBoxModel modeloCategoria;
	private DefaultTableModel modeloTablaProveedores = new DefaultTableModel();
	private JRPdfExporter exporter;
	public JButton btnAgregar;
	private JLabel lblId;
	private DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTextField txtProveedor;

	public VistaREPORTE_PROVEEDORES() {
		// setMaximizable(true);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		initComponent();
		ColumnasDeTablaProveedores();
		modeloComboBoxProveedores();
		modeloComboBoxCategoria();
	}

	private void LimpiarLista() {
		int numFilas = modeloTabla.getRowCount();
		if (numFilas > 0) {
			for (int i = numFilas - 1; i < 0; i--) {
				modeloTabla.removeRow(i);
			}
		}
	}

	public void initComponent() {

		con = new Conexion();
		base = new BaseDatos();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelInferior = new JPanel();
		contentPane.add(panelInferior, BorderLayout.CENTER);
		panelInferior.setLayout(new BorderLayout());

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(12, 0, 853, 77);
		panelInferior.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		 lblId = new JLabel("");
		panelSuperior.add(lblId);
		
		txtProveedor = new JTextField();
		panelSuperior.add(txtProveedor);
		txtProveedor.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 656, 53);
		panelSuperior.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblProveedores = new JLabel("");
		lblProveedores.addMouseListener(new MouseAdapter() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					VistaPROVEEDORES proveedor = Singleton.getVistaPROVEEDORES();
					EventQueue eq = new EventQueue();
					while (proveedor.isShowing()) {
						if (EventQueue.isDispatchThread()) {
							AWTEvent ev = eq.getNextEvent();
							if (ev instanceof ActiveEvent)
								((ActiveEvent) ev).dispatch();
							else if (ev.getSource() instanceof Component)
								((Component) ev.getSource()).dispatchEvent(ev);
						} else
							Thread.yield();
					}
					proveedor.setLocationRelativeTo(null);
					proveedor.setVisible(true);
					Proveedor prov = proveedor.getSeleccion();
					txtProveedor.setText(prov.getNomProveedor());
					lblId.setText(String.valueOf(prov.getIdProveedor()));

				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		panel.add(lblProveedores);
		lblProveedores.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/zoom_iwwn.png"));

		JButton btnImprimir = new JButton("");
		panel.add(btnImprimir);
		btnImprimir.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/Imprimir.png"));

		JButton btnVer = new JButton("");
		panel.add(btnVer);
		btnVer.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/Visualizar.png"));

		JButton btnNewButton = new JButton("");
		panel.add(btnNewButton);
		
		JPanel panelTabla = new JPanel();
		panelTabla.setBounds(0, 89, 853, 363);
		panelInferior.add(panelTabla);
		panelTabla.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);

		tablaProveedores = new JTable();
		scrollPane.setViewportView(tablaProveedores);
		tablaProveedores.setModel(modeloTabla);
		
		btnNewButton.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/ReHacer.png"));

		//BOTON DE CERRAR
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// LISTAR PRODUCTOS POR PROVEEDOR
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idP = Integer.parseInt(lblId.getText());
				if (idP != 0) {
					listarProveedores(e);
				}

			}
		});
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dameProductos(e);
			}
		});

	
	}

	private void dameProductos(ActionEvent e) {
		try {

			String path2 = "/home/ferc/eclipse-workspace/SistemaSeminario/src/reportes/Proveedor.jasper";

			conn = con.getConexion();
			Map parametros = new HashMap();
			
			int p = Integer.parseInt(lblId.getText());
			parametros.put("idProveedor", p);
		
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path2);
			JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, conn);
			JasperViewer view = new JasperViewer(jprint, false);
			view.setVisible(true);

		} catch (Exception es) {
			es.printStackTrace();
		}

	}

	void modeloComboBoxProveedores() {

		modeloProveedor = new DefaultComboBoxModel(new BaseDatos().dameProveedores());

	}

	void modeloComboBoxCategoria() {

		modeloCategoria = new DefaultComboBoxModel(new BaseDatos().dameCategorias());

	}

	private void ColumnasDeTablaProveedores() {

		modeloTabla.addColumn("Codigo");
		modeloTabla.addColumn("Descripcion");
		modeloTabla.addColumn("Proveedor");
		modeloTabla.addColumn("Categoria");
		modeloTabla.addColumn("Stock");

		TableColumn ColCodigo = tablaProveedores.getColumn("Codigo");
		TableColumn ColNombre = tablaProveedores.getColumn("Descripcion");
		TableColumn ColProve = tablaProveedores.getColumn("Proveedor");
		TableColumn ColCat = tablaProveedores.getColumn("Categoria");
		TableColumn ColVenta = tablaProveedores.getColumn("Stock");

		ColCodigo.setMinWidth(90);
		ColNombre.setMinWidth(500);

	}

	private void listarProveedores(ActionEvent e) {

		LimpiarLista();
		
		ArrayList<Producto> productos = base.selectProveedoresTABLAPEDIDO(Integer.parseInt(lblId.getText()));
		int columnas = productos.size();
		modeloTabla.setNumRows(columnas);
		for (int i = 0; i < columnas; i++) {

			Producto producto = productos.get(i);
			String codigoArticulo = producto.getCodigoArticulo();
			String desc = producto.getNomProducto();
			String nom = producto.getNomProveedor();
			String cat = producto.getNomCategoria();
			double stock = producto.getStockProducto();

			modeloTabla.setValueAt(codigoArticulo, i, 0);
			modeloTabla.setValueAt(desc, i, 1);
			modeloTabla.setValueAt(nom, i, 2);
			modeloTabla.setValueAt(cat, i, 3);
			modeloTabla.setValueAt(stock, i, 4);

		}
	}

}
