package vista.Ventas;

import static net.sf.jasperreports.engine.JasperFillManager.fillReport;
import org.apache.commons.lang3.StringUtils;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import modelo.BaseDatos;
import modelo.Categoria;
import modelo.Clientes;
import modelo.Conexion;
import modelo.DetalleVenta;
import modelo.Presupuesto;
import modelo.Producto;
import modelo.Proveedor;
import modelo.Ventas;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import singleton.Singleton;
import vista.Clientes.VistaCLIENTES;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VistaVENTAS extends JInternalFrame {

	DefaultTableModel modeloTablaProductosArriba=new DefaultTableModel(){

	@Override public boolean isCellEditable(int row,int column){return false;

	}};

	DefaultTableModel modeloTablaProductosAbajo=new DefaultTableModel(){

	@Override public boolean isCellEditable(int row,int column){return false;

	}};

	public VistaVENTAS() {

		initComponents();
		Conexion = new Conexion();
		cargarColumnasTablaAbajo();
		cargarColumnasTablaArriba();
		ModelTablaArriba();
		cargarClientesEnComboBox();
		centrarTablaAbajo();
		centrarTablaArriba();

		txtPresupuesto.setText(String.valueOf(base.damePosicion(PRESUPUESTO)));
		jPanel3.setLayout(null);
		jPanel3.add(jPanel1);
		jPanel3.add(jPanel7);
		jPanel3.add(jPanel6);
		jPanel3.add(jScrollPane1);

		tablaArriba.setModel(modeloTablaProductosArriba);
		tablaArriba.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14)); // NOI18N
		tablaArriba.setModel(modeloTablaProductosArriba);
		tablaArriba.setToolTipText("");
		tablaArriba.setGridColor(new java.awt.Color(204, 204, 204));
		tablaArriba.setRowHeight(25);
		tablaArriba.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tablaArriba.setShowGrid(false);

		jPanel1.setLayout(null);
		jPanel1.add(jScrollPane2);
		jPanel1.add(lblIMAGEN);
		lblSumatoria = new javax.swing.JTextField();
		lblSumatoria.setBounds(911, 240, 226, 52);
		jPanel1.add(lblSumatoria);
		lblSumatoria.setForeground(new Color(0, 255, 127));
		lblSumatoria.setFont(new Font("Dunkin", Font.PLAIN, 16));
		lblSumatoria.setHorizontalAlignment(SwingConstants.CENTER);

		panel = new JPanel();
		panel.setBounds(12, 522, 145, 120);
		jPanel3.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		jLabel5 = new javax.swing.JLabel();
		panel.add(jLabel5);

		jLabel5.setFont(new Font("Fira Mono", Font.BOLD, 12)); // NOI18N
		jLabel5.setText("Código:");
		txtCodigo = new javax.swing.JTextField();
		txtCodigo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		txtCodigo.setColumns(2);
		panel.add(txtCodigo);

		jLabel6 = new javax.swing.JLabel();
		panel.add(jLabel6);

		jLabel6.setFont(new Font("Fira Mono", Font.BOLD, 12)); // NOI18N
		jLabel6.setText("Cantidad:");
		txtCantidad = new javax.swing.JTextField();
		txtCantidad.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel.add(txtCantidad);
		jLabel7 = new javax.swing.JLabel();
		panel.add(jLabel7);

		jLabel7.setFont(new Font("Fira Mono", Font.BOLD, 12)); // NOI18N
		jLabel7.setText("Desc:%");
		txtDescuento = new javax.swing.JTextField();
		txtDescuento.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel.add(txtDescuento);
		jLabel8 = new javax.swing.JLabel();
		jLabel8.setBounds(178, 523, 446, 41);
		jPanel3.add(jLabel8);
		jLabel8.setIcon(new ImageIcon(VistaVENTAS.class.getResource("/iconos_1/zoom_iwwn.png")));

		jLabel8.setFont(new Font("Segoe UI", Font.BOLD, 16));
		txtDesc = new javax.swing.JTextField();
		txtDesc.setBounds(206, 523, 418, 41);

		jPanel3.add(txtDesc);

		panel_1 = new JPanel();
		panel_1.setBounds(929, 529, 220, 46);
		jPanel3.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		btnAceptar = new javax.swing.JButton();
		panel_1.add(btnAceptar);

		btnAceptar.setIcon(new ImageIcon(VistaVENTAS.class.getResource("/iconos_1/Aceptar.png")));
		btnAceptar.setBorder(null);
		btnQuitarProd = new javax.swing.JButton();
		panel_1.add(btnQuitarProd);

		btnQuitarProd.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
		btnQuitarProd.setIcon(new ImageIcon(VistaVENTAS.class.getResource("/iconos_1/Eliminar.png")));

		btnCancelar = new javax.swing.JButton();
		panel_1.add(btnCancelar);

		btnCancelar.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
		btnCancelar.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/recycle.png"));

		btnNewButton = new JButton("");
		panel_1.add(btnNewButton);

		btnNewButton
				.setIcon(new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/Actualizar.png"));

		btnNewButton_1 = new JButton("");
		panel_1.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(VistaVENTAS.class.getResource("/iconos_1/ReHacer.png")));
		
		JPanel jPanel10 = new JPanel();
		jPanel10.setBounds(206, 576, 310, 33);
		jPanel3.add(jPanel10);
		jPanel10.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel jLabel1 = new JLabel();
		jLabel1.setText("C. Provee");
		jLabel1.setFont(new Font("Fira Mono for Powerline", Font.BOLD, 12));
		jPanel10.add(jLabel1);
		
		txt_codigo_prove = new JTextField();
		txt_codigo_prove.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_codigo_prove.setFont(new Font("Segoe UI", Font.BOLD, 16));
		jPanel10.add(txt_codigo_prove);
		
		JLabel jLabel9 = new JLabel();
		jLabel9.setText("Iva");
		jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel9.setFont(new Font("Fira Mono for Powerline", Font.BOLD, 12));
		jPanel10.add(jLabel9);
		
		txtIva = new JTextField();
		txtIva.setHorizontalAlignment(SwingConstants.CENTER);
		txtIva.setFont(new Font("Segoe UI", Font.BOLD, 16));
		jPanel10.add(txtIva);

		btnAceptar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAceptarActionPerformed(evt);
			}
		});

		btnCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnCancelarActionPerformed(evt);
			}
		});

		btnQuitarProd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnQuitarProdActionPerformed(evt);
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModelTablaArriba();
			}
		});

		txtDesc.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtDescKeyReleased(evt);
			}
		});

		txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txtDescuentoKeyPressed(evt);
			}
		});

		txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txtCantidadKeyPressed(evt);
			}
		});

		tablaArriba.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && (tablaArriba.getSelectedRow() >= 0)) {
					Producto producto = (Producto) modeloTablaProductosArriba.getValueAt(tablaArriba.getSelectedRow(),
							1);
					desplegarFoto(producto);
					productoSeleccionado = producto;
				}
			}
		});

		txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txtCodigoKeyPressed(evt);
			}
		});

	}

	private double getImporteTotal(DefaultTableModel model, int columna) {

		int numFilas = model.getRowCount();
		double sumatoria = 0;
		for (int i = 0; i < numFilas; i++) {
			double importe = (double) model.getValueAt(i, columna);
			sumatoria += (importe);

		}

		return sumatoria;

	}

	// CARGAR PRESUPUESTO EN TABLA DE ABAJO
	private void cargarPresupuesto() {

		int presupuesto = Integer.parseInt(txtPresupuesto.getText());

		ArrayList<Presupuesto> listaProductoAbajo = base.getPresupuestoPorID(presupuesto);
		int numeroProducto = listaProductoAbajo.size();
		importeTotal = 0;
		for (int i = 0; i < numeroProducto; i++) {

			Presupuesto presupuestos = listaProductoAbajo.get(i);

			int idClave = presupuestos.getId_prod();
			int cant = presupuestos.getCant_productos();
			String descripcion = presupuestos.getDescripcion();
			Double importe = presupuestos.getpNeto();
			double desc = presupuestos.getDescuento();
			int idCliente = presupuestos.getId_cliente();
			double precioVenta = presupuestos.getPrecio_unitario();
			// INSERTA FILAS Y NO BORRA LAS ANTERIORES
			importeTotal = getImporteTotal(modeloTablaProductosAbajo, 5);
			modeloTablaProductosAbajo.insertRow(i,
					new Object[] { idClave, descripcion, cant, desc, precioVenta, importe });

			lblSumatoria.setText(String.valueOf(importeTotal));

		}

	}

	// GEN-LAST:event_tablaArribaKeyReleased

	private void ModelTablaArriba() {

		ArrayList<Producto> listaProductoAbajo = base.getProductos();
		int numeroProducto = listaProductoAbajo.size();
		modeloTablaProductosArriba.setNumRows(numeroProducto);

		for (int i = 0; i < numeroProducto; i++) {

			Producto producto = listaProductoAbajo.get(i);
			String nomBre = producto.getNomProducto();
			String codigoArticulo = producto.getCodigoArticulo();
			// int idClave = producto.getIdProducto1();
			String idFabricaProd = producto.getIdProveedorProducto();
			Double pventa = producto.getPrecioVentaProducto();
			double stock = producto.getStockProducto();

			modeloTablaProductosArriba.setValueAt(codigoArticulo, i, 0);
			modeloTablaProductosArriba.setValueAt(producto, i, 1);
			modeloTablaProductosArriba.setValueAt(idFabricaProd, i, 2);
			modeloTablaProductosArriba.setValueAt(stock, i, 3);
			modeloTablaProductosArriba.setValueAt(pventa, i, 4);

		}

	}

	private void cargarColumnasTablaAbajo() {
		modeloTablaProductosAbajo.addColumn("Codigo");
		modeloTablaProductosAbajo.addColumn("Nombre");
		modeloTablaProductosAbajo.addColumn("Cant");
		modeloTablaProductosAbajo.addColumn("Desc%");
		modeloTablaProductosAbajo.addColumn("P.Venta");
		modeloTablaProductosAbajo.addColumn("Importe");

		TableColumn ColCodigo = tablaAbajo.getColumn("Codigo");
		TableColumn ColNombre = tablaAbajo.getColumn("Nombre");
		TableColumn Colcant = tablaAbajo.getColumn("Cant");
		TableColumn ColProve = tablaAbajo.getColumn("Desc%");
		TableColumn ColVen = tablaAbajo.getColumn("P.Venta");
		TableColumn Colimport = tablaAbajo.getColumn("Importe");

		// ColCodigo.setPreferredWidth(1);
		ColCodigo.setMaxWidth(80);
		ColCodigo.setMinWidth(50);
		ColNombre.setMaxWidth(300);
		ColNombre.setMinWidth(500);

	}

	private void cargarClientesEnComboBox() {

		modeloComboClientes.removeAllElements();
		ArrayList<Clientes> listaReservas = base.getClientes();

		for (Clientes r : listaReservas) {
			modeloComboClientes.addElement(r);

		}
	}

	private void cargarColumnasTablaArriba() {
		modeloTablaProductosArriba.addColumn("codigo");
		modeloTablaProductosArriba.addColumn("nombre");
		modeloTablaProductosArriba.addColumn("c.prov");
		modeloTablaProductosArriba.addColumn("stock");
		modeloTablaProductosArriba.addColumn("p.venta");

		TableColumn ColCodigo = tablaArriba.getColumn("codigo");
		TableColumn ColNombre = tablaArriba.getColumn("nombre");
		TableColumn ColProve = tablaArriba.getColumn("c.prov");
		TableColumn Colcant = tablaArriba.getColumn("stock");
		TableColumn ColVen = tablaArriba.getColumn("p.venta");

		ColCodigo.setMinWidth(80);
		ColNombre.setMinWidth(500);

	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(200, 80);
		grupo_boletas = new javax.swing.ButtonGroup();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBounds(12, 366, 1164, 144);
		tablaAbajo = new javax.swing.JTable();
		jPanel6 = new javax.swing.JPanel();
		jPanel6.setBounds(12, 12, 1164, 40);
		radioCtaCorriente = new javax.swing.JRadioButton();
		radioCtaCorriente.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		radioPresupuesto = new javax.swing.JRadioButton();
		radioPresupuesto.setSelected(true);
		radioPresupuesto.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		radioFactura = new javax.swing.JRadioButton();
		radioFactura.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		jLabel10 = new javax.swing.JLabel();
		jLabel10.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		txtPresupuesto = new javax.swing.JTextField();
		btnCliente = new javax.swing.JButton();
		btnCliente.setIcon(new ImageIcon(VistaVENTAS.class.getResource("/iconos_2/customer.png")));
		jPanel7 = new javax.swing.JPanel();
		jPanel7.setBounds(1297, 383, 0, 0);
		jPanel1 = new javax.swing.JPanel();
		jPanel1.setBounds(12, 58, 1164, 304);
		lblIMAGEN = new javax.swing.JLabel();
		lblIMAGEN.setBounds(892, 6, 265, 230);
		lblIMAGEN.setForeground(Color.DARK_GRAY);

		setTitle("V_ventas");
		setPreferredSize(new Dimension(1200, 950));

		getContentPane().setLayout(null);

		tablaAbajo.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14)); // NOI18N
		tablaAbajo.setRowHeight(25);
		jScrollPane1.setViewportView(tablaAbajo);
		tablaAbajo.setModel(modeloTablaProductosAbajo);

		grupo_boletas.add(radioCtaCorriente);
		radioCtaCorriente.setText("C.Corriente");

		grupo_boletas.add(radioPresupuesto);
		radioPresupuesto.setText("Presupuesto");

		grupo_boletas.add(radioFactura);
		radioFactura.setText("Factura");

		jLabel10.setText("Num Presupuesto");

		txtNombreCliente = new JTextField();
		txtNombreCliente.setForeground(Color.RED);
		txtNombreCliente.setFont(new Font("Segoe UI Black", Font.PLAIN, 16));
		txtNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreCliente.setEnabled(false);
		txtNombreCliente.setText("CONSUMIDOR FINAL");
		txtNombreCliente.setColumns(10);

		lblIdCliente = new JLabel("");
		lblIdCliente.setVisible(false);
		lblIdCliente.setEnabled(false);

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel6Layout.createSequentialGroup().addGap(5)
						.addComponent(btnCliente, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtNombreCliente, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblIdCliente, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addGap(74).addComponent(jLabel10).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtPresupuesto, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addGap(123).addComponent(radioPresupuesto).addGap(18)
						.addComponent(radioCtaCorriente, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(radioFactura).addGap(29)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel6Layout
				.createSequentialGroup()
				.addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel6Layout.createSequentialGroup().addGap(10)
								.addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnCliente, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtNombreCliente, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addGroup(jPanel6Layout
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(radioFactura, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(radioCtaCorriente, GroupLayout.PREFERRED_SIZE, 28,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(radioPresupuesto)))
						.addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addGroup(jPanel6Layout
								.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 18,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblIdCliente, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(txtPresupuesto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel6.setLayout(jPanel6Layout);

		jPanel7.setLayout(new java.awt.BorderLayout());

		lblIMAGEN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lblIMAGEN.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));

		jScrollPane2 = new JScrollPane();
		jScrollPane2.setEnabled(false);
		jScrollPane2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dameArticuloMouseClicker(e);
			}

			private void dameArticuloMouseClicker(MouseEvent e) {

				if (e.getClickCount() == MouseEvent.BUTTON1) {

					int filaSelec = modeloTablaProductosArriba.getRowCount();
					if (filaSelec <= 0) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un articulo");

					} else {

						filaSeleccionada = tablaArriba.getSelectedRow();
						modeloTablaProductosArriba = (DefaultTableModel) tablaArriba.getModel();
						String id_prodString = (String) tablaArriba.getValueAt(filaSeleccionada, 0);
						Producto p = (Producto) tablaArriba.getValueAt(filaSeleccionada, 1);
						// String codigoProve = (String) tablaArriba.getValueAt(filaSeleccionada, 2);
						String iva = String.valueOf(p.getIva());
						txtDesc.setText(p.getNomProducto());
						txtCodigo.setText(String.valueOf(id_prodString));
						// txt_codigo_prove.setText(codigoProve);
						txtIva.setText(iva);
						txtIva.setEnabled(false);
						txtCantidad.requestFocus();

					}

				}

			}
		});
		jScrollPane2.setBounds(0, 12, 881, 290);

		tablaArriba = new JTable();
		tablaArriba.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				obtenerProductoKeyEvent(e);
			}

			private void obtenerProductoKeyEvent(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

					int filaSelec = modeloTablaProductosArriba.getRowCount();

					if (filaSelec <= 0) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un articulo");

					} else {

						filaSeleccionada = tablaArriba.getSelectedRow();
						modeloTablaProductosArriba = (DefaultTableModel) tablaArriba.getModel();
						String codigo = (String) tablaArriba.getValueAt(filaSeleccionada, 0);
						Producto p = (Producto) tablaArriba.getValueAt(filaSeleccionada, 1);
						// String codigoProve = (String) tablaArriba.getValueAt(filaSeleccionada, 2);
						String iva = String.valueOf(p.getIva());
						txtDesc.setText(p.getNomProducto());
						txtCodigo.setText(String.valueOf(codigo));
						// txt_codigo_prove.setText(codigoProve);
						txtIva.setText(iva);
						txtIva.setEnabled(false);
						txtCantidad.requestFocus();

					}

				}

			}
		});

		txtPresupuesto.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				txtPresupuestoKeyReleased(evt);
			}
		});
		btnCliente.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		tablaArriba.setRowHeight(25);
		// tablaArriba.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		jScrollPane2.setViewportView(tablaArriba);
		tablaArriba.setModel(modeloTablaProductosArriba);

		getContentPane().add(jPanel3);
		jPanel3.setBounds(0, -11, 1202, 650);

		getAccessibleContext().setAccessibleDescription("");

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public void LimpiarListaProductos() {
		modeloListaProductos.clear();
	}

	private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelarActionPerformed
		int cantidadFilas = modeloTablaProductosAbajo.getRowCount();
		if (cantidadFilas > 0) {
			int quitar = JOptionPane.showConfirmDialog(this, "¿ Desea borrar la venta ?");
			if (quitar == 0) {
				for (int i = cantidadFilas - 1; i >= 0; i--) {
					modeloTablaProductosAbajo.removeRow(i);

				}

			}
			double sumatoria = getImporteTotal(modeloTablaProductosAbajo, 5);
			lblSumatoria.setText(String.valueOf(sumatoria));
		}

	}// GEN-LAST:event_btnCancelarActionPerformed

	public void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAceptarActionPerformed

		if (radioFactura.isSelected()) {
			imprimirFactura();
		}

		if (radioPresupuesto.isSelected()) {
			insertarPresupuesto();
			limpiarTabla();
			lblSumatoria.setText("0.00");

		}

	}// GEN-LAST:event_btnAceptarActionPerformed

	private void limpiarTabla() {
		int cantidadFilas = modeloTablaProductosAbajo.getRowCount();
		if (cantidadFilas > 0) {
			for (int i = cantidadFilas - 1; i >= 0; i--) {
				modeloTablaProductosAbajo.removeRow(i);
			}

		}

	}

	public void insertarPresupuesto() {
		String posicion = txtPresupuesto.getText();
		Map parametros = new HashMap();
		conn = Conexion.getConexion();
		JasperReport reporte = null;
		String path = "/home/ferc/eclipse-workspace/SistemaSeminario/src/reportes/Presupuesto.jasper";
		JasperPrint jprint = null;
		JasperViewer view = null;
		Clientes cliente;
		Producto producto;
		double total = 0;
		int pres = 0;
		if (posicion.isEmpty()) {// INSERTA UN PRESUPUESTO CUANDO NO EXISTE NINGUNO
			int rows = modeloTablaProductosAbajo.getRowCount();
			Calendar calendarioLocal = Calendar.getInstance();
			java.util.Date fechaActual = calendarioLocal.getTime();
			long fechaMailSegun = fechaActual.getTime();
			java.util.Date fecha = new Date(fechaMailSegun);
			String fecha1 = String.valueOf(fecha);
			ArrayList<Presupuesto> presupuesto = base.damePresupuesto();
			int numPresu = 0;
			Presupuesto p = new Presupuesto();
			for (int j = 0; j < presupuesto.size(); j++) {
				p = presupuesto.get(j);
			}
			numPresu = p.getPosicion();

			for (int i = 0; i < rows; i++) {

				producto = new Producto();
				int codigoProd = (int) modeloTablaProductosAbajo.getValueAt(i, 0);
				producto = (Producto) modeloTablaProductosAbajo.getValueAt(i, 1);
				int cantidadProd = (int) modeloTablaProductosAbajo.getValueAt(i, 2);
				double desc = (double) modeloTablaProductosAbajo.getValueAt(i, 3);
				double impDesc = (double) modeloTablaProductosAbajo.getValueAt(i, 5);
				int id = Integer.parseInt(lblIdCliente.getText());
				total = Double.parseDouble(lblSumatoria.getText());

				Presupuesto presu = new Presupuesto(numPresu, fecha1, cantidadProd, id, codigoProd, desc, total,
						impDesc);

				if (lblIdCliente.equals("")) {
					JOptionPane.showMessageDialog(null, "INGRESE UN CLIENTE");
				}

				base.insertPresupuesto(presu);

			}

			int seleccion = JOptionPane.showConfirmDialog(new VistaVENTAS(), "¿Desea imprimir el presupuesto?", null,
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			txtPresupuesto.setText(String.valueOf(base.damePosicion("PRESUPUESTO")));

			if (seleccion == 0) {// YES

				parametros.put("numPresupuesto", numPresu);
				try {

					reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
					jprint = JasperFillManager.fillReport(reporte, parametros, conn);
					view = new JasperViewer(jprint, false);
					view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					view.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		} else {// INSERTA UN PRESUPUESTO NO
			System.out.println("EN EL ELSE");
			int rows = modeloTablaProductosAbajo.getRowCount();
			Calendar calendarioLocal = Calendar.getInstance();
			java.util.Date fechaActual = calendarioLocal.getTime();
			long fechaMailSegun = fechaActual.getTime();
			java.util.Date fecha = new Date(fechaMailSegun);
			String fecha1 = String.valueOf(fecha);
			ArrayList<Presupuesto> presupuesto = base.damePresupuesto();
			int numPresu = 0;
			Presupuesto p = new Presupuesto();
			for (int j = 0; j < presupuesto.size(); j++) {
				p = presupuesto.get(j);
			}
			numPresu = p.getPosicion() + 1;

			for (int i = 0; i < rows; i++) {
				cliente = new Clientes();
				producto = new Producto();

				producto = (Producto) modeloTablaProductosAbajo.getValueAt(i, 1);
				int cantidadProd = (int) modeloTablaProductosAbajo.getValueAt(i, 2);
				double desc = (double) modeloTablaProductosAbajo.getValueAt(i, 3);
				double impDesc = (double) modeloTablaProductosAbajo.getValueAt(i, 5);
				int id = Integer.parseInt(lblIdCliente.getText());
				total = Double.parseDouble(lblSumatoria.getText());
				Presupuesto presu = new Presupuesto(numPresu, fecha1, cantidadProd, id, producto.getIdProducto1(), desc,
						total, impDesc);
				if (id == 0) {
					JOptionPane.showMessageDialog(null, "INGRESE UN CLIENTE");
				}
				base.insertPresupuesto(presu);

			}

			txtPresupuesto.setText(String.valueOf(base.damePosicion("presupuesto")));

			int seleccion = JOptionPane.showConfirmDialog(null, "¿Desea imprimir el presupuesto?", null,
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			pres = Integer.parseInt(txtPresupuesto.getText());

			if (seleccion == 0) {
				parametros.put("numPresupuesto", pres);
				try {
					reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
					jprint = fillReport(reporte, parametros, conn);
					view = new JasperViewer(jprint, false);
					view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void btnQuitarProdActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnQuitarProdActionPerformed
		int numFilas = tablaAbajo.getSelectedRow();
		int filaSelec = modeloTablaProductosAbajo.getRowCount();

		if (filaSelec > 0) {
			int quitar = JOptionPane.showConfirmDialog(this, "¿ Desea eliminar el articulo seleccionado ?");
			if (quitar == 0) {
				if (numFilas == -1) {
					JOptionPane.showMessageDialog(this, "Debe seleccionar el articulo que desea quitar", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					modeloTablaProductosAbajo.removeRow(numFilas);
					double sumatoria = getImporteTotal(modeloTablaProductosAbajo, 5);

					lblSumatoria.setText(String.valueOf(sumatoria));
				}
			}
		}

	}

	private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtCodigoKeyPressed

		if (evt.getKeyCode() == KeyEvent.VK_UP) {
			txtDesc.requestFocus();
		}

		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

			String codigo = txtCodigo.getText();

			try {

				ArrayList<Producto> listaProductos = base.selectProductoCriterioID((codigo));

				int numeroProducto = listaProductos.size();
				modeloTablaProductosArriba.setNumRows(numeroProducto);
				for (int i = 0; i < numeroProducto; i++) {
					Producto producto = listaProductos.get(i);
					String codigoArticulo = producto.getCodigoArticulo();
					String nomBre = producto.getNomProducto();
					String idFabricaProd = producto.getIdProveedorProducto();
					Double pventa = producto.getPrecioVentaProducto();
					double stock = producto.getStockProducto();

					modeloTablaProductosArriba.setValueAt(codigoArticulo, i, 0);
					modeloTablaProductosArriba.setValueAt(producto, i, 1);
					modeloTablaProductosArriba.setValueAt(idFabricaProd, i, 2);
					modeloTablaProductosArriba.setValueAt(stock, i, 3);
					modeloTablaProductosArriba.setValueAt(pventa, i, 4);

				}
				if (tablaArriba.getRowCount() < 1) {
					txtCodigo.requestFocus();
				} else {
					tablaArriba.requestFocus();
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Carácter inválido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}// GEN-LAST:event_txtCodigoKeyPressed
//BUSCAR POR DESCRIPCION DE PRODUCTO 

	private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtDescKeyReleased

		if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
			txtCodigo.requestFocus();
		}

		String cadena = txtDesc.getText();

		ArrayList<Producto> listaProductos = base.selectProductosCriterioDesc(cadena);

		int numeroProducto = listaProductos.size();
		modeloTablaProductosArriba.setNumRows(numeroProducto);
		for (int i = 0; i < numeroProducto; i++) {

			Producto producto = listaProductos.get(i);
			String codigoArticulo = producto.getCodigoArticulo();
			String nomBre = producto.getNomProducto();
			String idFabricaProd = producto.getIdProveedorProducto();
			Double pventa = producto.getPrecioVentaProducto();
			double stock = producto.getStockProducto();

			modeloTablaProductosArriba.setValueAt(codigoArticulo, i, 0);
			modeloTablaProductosArriba.setValueAt(producto, i, 1);
			modeloTablaProductosArriba.setValueAt(idFabricaProd, i, 2);
			modeloTablaProductosArriba.setValueAt(stock, i, 3);
			modeloTablaProductosArriba.setValueAt(pventa, i, 4);

		}

		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

			tablaArriba.requestFocus();

		}
	}// GEN-LAST:event_txtDescKeyReleased

	public static boolean validarNumero(String dato) {
		return dato.matches("[0-9]*");
	}

	private void txtDescuentoKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtDescuentoKeyPressed
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			// DATOS OBTENIDOS DE LOS CAMPOS DE TEXTO
			String descripcion = txtDesc.getText();
			String codigoProd = txtCodigo.getText();
			String cp = txt_codigo_prove.getText();
			String cantidad = txtCantidad.getText();

			if (descripcion.isEmpty() || codigoProd.isEmpty() || cantidad.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Los campos no deben estar vacios");
				if (!codigoProd.isEmpty() && cantidad.isEmpty()) {
					txtCantidad.requestFocus();
				}
			} else {

				String codigo = (String) tablaArriba.getValueAt(filaSeleccionada, 0);
				Producto p = (Producto) tablaArriba.getValueAt(filaSeleccionada, 1);
				String Pventa = tablaArriba.getValueAt(filaSeleccionada, 4).toString();
				String iva = String.valueOf(p.getIva());
				String descuento = txtDescuento.getText();
				int cant = Integer.parseInt(txtCantidad.getText());

				if (descuento.isEmpty()) {

					double importe = Double.parseDouble(Pventa) * cant;// GUARDO IMPORTE
					modeloTablaProductosAbajo = (DefaultTableModel) tablaAbajo.getModel();

					Object ListaProductos[] = { codigo, p, cant, 0.0, Pventa, importe };
					modeloTablaProductosAbajo.addRow(ListaProductos);// LE ESTABLECEMOS LOS DATOS AL MODELO CREADO PARA
																		// VISUALIZAR LOS DATOS
					double sumatoria = getImporteTotal(modeloTablaProductosAbajo, 5);

					lblSumatoria.setText(String.valueOf(sumatoria));
					txtDesc.setText("");
					txtCodigo.setText("");
					txt_codigo_prove.setText("");
					txtCantidad.setText("");
					txtIva.setText("");
					txtIva.setEnabled(false);

				}

				else {

					double Precioventa = Double.parseDouble(Pventa);
					double desc = Double.parseDouble(txtDescuento.getText());
					double descuentaso = (Precioventa / 100) * Double.parseDouble(descuento);
					double PrecioConDescuento = Precioventa - descuentaso;
					double importe = PrecioConDescuento * cant;

					modeloTablaProductosAbajo = (DefaultTableModel) tablaAbajo.getModel();

					Object ListaProductos[] = { codigo, p, cant, desc, Pventa, importe };

					modeloTablaProductosAbajo.addRow(ListaProductos);

					double sumatoria = getImporteTotal(modeloTablaProductosAbajo, 5);

					lblSumatoria.setText(String.valueOf(sumatoria));
					txtDesc.setText("");
					txtCodigo.setText("");
					txt_codigo_prove.setText("");
					txtCantidad.setText("");
					txtDescuento.setText("");
					txtIva.setText("");
					txtIva.setEnabled(false);

				}

			}

			txtCodigo.requestFocus();

		}
	}

	private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtCantidadKeyPressed

		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			txtDescuento.requestFocus();
		}

	}// GEN-LAST:event_txtCantidadKeyPressed

	private void txtPresupuestoKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtPresupuestoKeyReleased
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			String txtPres = txtPresupuesto.getText();
			if (!txtPres.isEmpty()) {
				cargarPresupuesto();
			} else {
				JOptionPane.showMessageDialog(null, "Tiene que insertar un numero de presupuesto");
			}

		}
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed

		VistaCLIENTES cliente = Singleton.getVistaCLIENTES();
		EventQueue eq = new EventQueue();
		cliente.setLocationRelativeTo(null);
		cliente.setVisible(true);

		Clientes client = cliente.getSeleccion();
		lblIdCliente.setText(String.valueOf(client.getIdCliente()));
		txtNombreCliente.setText(client.getNombre());

	

	

	}

	// GEN-LAST:event_jButton1ActionPerformed

	public void desplegarFoto(Producto prod) {

		ImageIcon ImagenProducto = null;

		try {
			InputStream is = base.buscarFoto(prod);

			BufferedImage bi = ImageIO.read(is);
			ImagenProducto = new ImageIcon(bi);

			ImageIcon icono2 = new ImageIcon(ImagenProducto.getImage().getScaledInstance(lblIMAGEN.getWidth(),
					lblIMAGEN.getHeight(), Image.SCALE_DEFAULT));

			lblIMAGEN.setIcon(icono2);

		} catch (Exception e) {

		}

	}

	public void limpiarCampoSiguiente(JComponent Actual, JComponent Sucesor) {

		if (Actual == txtCantidad) {
			txtCodigo.setText("");
			txt_codigo_prove.setText("");
			txtCantidad.setText("");
			txtIva.setText("");
			Sucesor.requestFocus();

		}
		if (Actual == txtCodigo) {
			txt_codigo_prove.setText("");
			txtIva.setText("");
			Sucesor.requestFocus();
		}

	}

	// CAMBIAR DE COMPONENTE
	public void hacerFoco(KeyEvent e, Component antecesor, Component sucesor) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN) {

			sucesor.requestFocus();
		} else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_ALT) {
			antecesor.requestFocus();

		}
	}

	public void hacerFoco(Component campoDeTexto) {
		campoDeTexto.setFocusable(true);
	}

	public void LimpiarLista() {
		int numFilas = modeloTablaProductosArriba.getRowCount();
		if (numFilas > 0) {
			for (int i = numFilas - 1; i < 0; i--) {
				modeloTablaProductosArriba.removeRow(i);

			}
		}
	}

	// Imprimir factura

	public void imprimirFactura() {

		ArrayList<DetalleVenta> detalles = new ArrayList<DetalleVenta>();

		String sumatoriaStr = lblSumatoria.getText();
		double montoVenta = Double.parseDouble(sumatoriaStr);

		double cambio = 0;

		Calendar calendarioLocal = Calendar.getInstance();
		java.util.Date fechaActual = calendarioLocal.getTime();
		long fechaMailSegun = fechaActual.getTime();
		java.util.Date fecha = new Date(fechaMailSegun);

		Ventas venta = new Ventas(montoVenta, fecha);
		Long idVenta = base.insertarVenta(venta);

		int numRows = modeloTablaProductosAbajo.getRowCount();

		for (int i = 0; i < numRows; i++) {
			int idProducto = (int) modeloTablaProductosAbajo.getValueAt(i, 1);
			String idCantidad = (String) modeloTablaProductosAbajo.getValueAt(i, 3);
			double cantidad = Double.parseDouble(idCantidad);
			DetalleVenta detalle = new DetalleVenta(idVenta, idProducto, idCantidad);

			detalles.add(detalle);
		}

		for (int i = numRows - 1; i >= 0; i--) {
			modeloTablaProductosAbajo.removeRow(i);

		}

		lblSumatoria.setText("0.00");

	}

	public void restarStock() {

		int rows = modeloTablaProductosAbajo.getRowCount();
		for (int i = 0; i < rows; i++) {
			Producto p = new Producto();
			p = (Producto) modeloTablaProductosAbajo.getValueAt(i, 1);
			int idProd = (int) modeloTablaProductosAbajo.getValueAt(i, 0);
			int stock = (int) p.getStockProducto();
			int cant = (int) modeloTablaProductosAbajo.getValueAt(i, 2);
			int stockActualizado = stock - cant;

			p.setIdProducto1(idProd);
			p.setStockProducto(stockActualizado);

			base.actualizarInventario(p);

		}
		ModelTablaArriba();
	}

	public void centrarTablaArriba() {
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		DefaultTableCellRenderer modeloLeft = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		modeloLeft.setHorizontalAlignment(SwingConstants.LEFT);
		tablaArriba.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tablaArriba.getColumnModel().getColumn(1).setCellRenderer(modeloLeft);
		tablaArriba.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		tablaArriba.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		tablaArriba.getColumnModel().getColumn(4).setCellRenderer(modelocentrar);

	}

	public void centrarTablaAbajo() {
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		DefaultTableCellRenderer modeloLeft = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		modeloLeft.setHorizontalAlignment(SwingConstants.LEFT);
		tablaAbajo.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tablaAbajo.getColumnModel().getColumn(1).setCellRenderer(modeloLeft);
		tablaAbajo.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		tablaAbajo.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		tablaAbajo.getColumnModel().getColumn(4).setCellRenderer(modelocentrar);
		tablaAbajo.getColumnModel().getColumn(5).setCellRenderer(modelocentrar);

	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnAceptar, btnNewButton_1, btnNewButton;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JButton btnQuitarProd;
	private javax.swing.ButtonGroup grupo_boletas;
	private javax.swing.JButton btnCliente;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7, panel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblIMAGEN;
	private javax.swing.JTextField lblSumatoria;
	public javax.swing.JRadioButton radioCtaCorriente;
	public javax.swing.JRadioButton radioFactura;
	private javax.swing.JRadioButton radioPresupuesto;
	private javax.swing.JTable tablaAbajo;
	private javax.swing.JTextField txtCantidad;
	private javax.swing.JTextField txtCodigo;
	private javax.swing.JTextField txtDesc;
	private javax.swing.JTextField txtDescuento;
	private javax.swing.JTextField txtPresupuesto;
	private JScrollPane jScrollPane2;
	private JTable tablaArriba;
	private JTextField txtNombreCliente;
	private JLabel lblIdCliente;
	private JPanel panel_1;
	private VistaVENTAS vista;
	Producto productoSeleccionado = null;
	Categoria categoriaSeleccionada = null;
	Proveedor proveedorSeleccionado = null;
	String categoria = "";
	String proveedor = "";
	Connection conn = null;
	Conexion Conexion = null;
	private static final double IVA = 0.21;
	DefaultComboBoxModel modeloComboClientes = new DefaultComboBoxModel();
	DefaultListModel<Producto> modeloListaProductos = new DefaultListModel<Producto>();
	BaseDatos base = new BaseDatos();
	int filaSeleccionada;
	boolean Seleccion = false;
	private double sumatoria = 0;
	private double importeTotal = 0;
	private DecimalFormat df = new DecimalFormat("0.00");
	public static final String PRESUPUESTO = "presupuesto";
	private JTextField txt_codigo_prove;
	private JTextField txtIva;
}
