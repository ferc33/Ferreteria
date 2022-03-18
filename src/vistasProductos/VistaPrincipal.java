package vistasProductos;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.BaseDatos;
import modelo.Producto;
import modelo.Proveedor;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;

public class VistaPrincipal extends JDialog {

	public VistaPrincipal(JFrame parent,boolean modal) {
		super(parent,modal);
		initComponent();
		base = new BaseDatos();

	}

	public void initComponent() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(900, 400);

		// Le dice que la lamina va a estar posicionada en el norte de la ventana
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[40px][][165.00px][][][][][][][][][][][][][]", "[21px]"));

		lblBuscar = new JLabel("");
		lblBuscar.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos_1/Knob-Search-icon.png")));
		panel.add(lblBuscar, "cell 0 0,alignx left,aligny center");//

		txtBuscar = new JTextField();
		panel.add(txtBuscar, "flowx,cell 1 0 2 1,alignx left,aligny center");
		txtBuscar.setColumns(16);

		lblCodigoFabrica = new JLabel("Codigo Fabrica");
		panel.add(lblCodigoFabrica, "cell 3 0,alignx trailing");

		lblNombre = new JLabel("");
		panel.add(lblNombre, "flowx,cell 9 0");

		btnFiltro = new JButton("Filtro");
		btnFiltro.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos_1/Knob-Valid-Green-icon.png")));
		panel.add(btnFiltro, "cell 13 0");

		txtCodigoFabrica = new JTextField();
		panel.add(txtCodigoFabrica, "cell 5 0 2 1");
		txtCodigoFabrica.setColumns(10);

		lblIdProveedor = new JLabel("1");
		panel.add(lblIdProveedor, "cell 9 0");

		btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/iconos_1/ReHacer.png")));
		panel.add(btnSalir, "cell 15 0");
		// le dice que la lamina va a estar posicionada en el centro
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);

		tabla = new JTable();
		model = new DefaultTableModel();
		panel_1.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane);

		tabla.setColumnSelectionAllowed(true);
		tabla.setCellSelectionEnabled(true);
		scrollPane.setViewportView(tabla);
		tabla.setModel(model);
		// le dice a la lamina que va a ir al SUR de la ventana principal
		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 6, 0, 0));

	}


	public Producto getSeleccion() {
		Producto producto= new Producto();
		 producto = (Producto) model.getValueAt(tabla.getSelectedRow(), 1);		
		return producto;

	}

	public void seleccionarProductoActionPerformed(MouseEvent e) {
		this.setVisible(false);
	}
	
	public JLabel getLblIdProveedor() {
		return lblIdProveedor;
	}

	public void setLblIdProveedor(JLabel lblIdProveedor) {
		this.lblIdProveedor = lblIdProveedor;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTextField getTxtBuscar() {
		return txtBuscar;
	}

	public void setTxtBuscar(JTextField txtBuscar) {
		this.txtBuscar = txtBuscar;
	}

	public JButton getButton() {
		return btnSalir;
	}

	public void setButton(JButton button) {
		this.btnSalir = button;
	}

	public JButton getBtnFiltro() {
		return btnFiltro;
	}

	public void setBtnFiltro(JButton btnFiltro) {
		this.btnFiltro = btnFiltro;
	}

	public JTextField getTextField_1() {
		return txtCodigoFabrica;
	}

	public void setTextField_1(JTextField textField_1) {
		this.txtCodigoFabrica = textField_1;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	public JPanel getPanel_2() {
		return panel_2;
	}

	public void setPanel_2(JPanel panel_2) {
		this.panel_2 = panel_2;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getLblBuscar() {
		return lblBuscar;
	}

	public void setLblBuscar(JLabel lblBuscar) {
		this.lblBuscar = lblBuscar;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JPanel getPanel_3() {
		return panel_3;
	}

	public void setPanel_3(JPanel panel_3) {
		this.panel_3 = panel_3;
	}

	public JLabel getLblCodigoFabrica() {
		return lblCodigoFabrica;
	}

	public void setLblCodigoFabrica(JLabel lblCodigoFabrica) {
		this.lblCodigoFabrica = lblCodigoFabrica;
	}

	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public void limpiarTabla() {
		model = new DefaultTableModel();
		int cantidadFilas = model.getRowCount();
		if (cantidadFilas > 0) {
			for (int i = cantidadFilas - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
	}

	private int filaSeleccionada;
	private BaseDatos base;
	private DefaultTableModel model = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTextField txtBuscar;
	private JPanel panel_1, panel_2, panel;
	private JLabel lblBuscar;
	private JScrollPane scrollPane;
	private JTable tabla;
	private JPanel panel_3;
	private JButton btnFiltro;
	private JTextField txtCodigoFabrica;
	private JLabel lblCodigoFabrica;
	private JLabel lblNombre;
	private JLabel lblIdProveedor;
	private JButton btnSalir;
}
