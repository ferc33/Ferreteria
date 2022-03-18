package vista.Ordendepedido;

import static net.sf.jasperreports.engine.JasperFillManager.fillReport;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.util.SystemOutLogger;

import controlador.Controlador;
import modelo.BaseDatos;
import modelo.Categoria;
import modelo.Clientes;
import modelo.Conexion;
import modelo.Pedido;
import modelo.Presupuesto;
import modelo.Producto;
import modelo.Proveedor;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import paneles.LaminaBotones;
import singleton.Singleton;

import utilidades.Utilidades;
import utilidades.Focus;

import utilidades.Utilidades;
import vistasProductos.VistaBuscarProductoOP;
import vistasProductos.VistaPrincipal;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import vista.*;
import vista.Proveedores.VistaPROVEEDORES;

public class VistaORDEN_PEDIDO extends JInternalFrame implements Utilidades {

	public void initComponent() {

		setResizable(true);
		setRootPaneCheckingEnabled(false);
		setIconifiable(true);
		setMaximizable(true);
		setBackground(Color.WHITE);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		grupo_botones = new ButtonGroup();
		getContentPane().setLayout(null);

		laminaSup = new JPanel();
		laminaSup.setBounds(0, 0, 1049, 550);
		getContentPane().add(laminaSup);
		laminaSup.setBackground(Color.WHITE);
		laminaSup.setLayout(null);
		laminaBotones = new LaminaBotones();
		laminaBotones.setLocation(796, 0);
		laminaSup.add(laminaBotones);
		

		lblBuscarPedido_1 = new JLabel("Buscar pedido");
		lblBuscarPedido_1.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblBuscarPedido_1.setBounds(29, 11, 100, 21);
		laminaSup.add(lblBuscarPedido_1);

		txtPedido = new JTextField();
		txtPedido.setBounds(125, 8, 34, 23);
		laminaSup.add(txtPedido);
		txtPedido.setColumns(10);

		lblOrdenDePedido = new JLabel("Orden de pedido N\u00BA :");
		lblOrdenDePedido.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblOrdenDePedido.setBounds(276, 35, 125, 17);
		laminaSup.add(lblOrdenDePedido);

		nOrden = new JLabel("0");
		nOrden.setBounds(384, 35, 36, 17);
		laminaSup.add(nOrden);

		lblId = new JLabel("");
		lblId.setBounds(309, 9, 23, 17);
		laminaSup.add(lblId);

		button_1 = new JButton("");
		button_1.setBounds(519, 19, 30, 33);
		laminaSup.add(button_1);
		button_1.setIcon(new ImageIcon(VistaORDEN_PEDIDO.class.getResource("/iconos_1/photo_2021-04-11_16-30-29.jpg")));

		btnImprimir = new JButton("");
		btnImprimir.setBounds(483, 19, 30, 33);
		laminaSup.add(btnImprimir);
		btnImprimir.setIcon(new ImageIcon(VistaORDEN_PEDIDO.class.getResource("/iconos_1/printesr.png")));

		btnBuscarPedido = new JButton("");
		btnBuscarPedido.setBounds(447, 19, 30, 33);
		laminaSup.add(btnBuscarPedido);

		btnBuscarPedido.setIcon(new ImageIcon(VistaORDEN_PEDIDO.class.getResource("/iconos_1/zoom_iwwn.png")));

		lblTotalPresupuesto = new JLabel("Total orden de pedido    ");
		lblTotalPresupuesto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPresupuesto.setBounds(624, 23, 227, 19);
		laminaSup.add(lblTotalPresupuesto);
		lblTotalPresupuesto.setFont(new Font("Roboto Light", Font.PLAIN, 21));

		lblSuma = new JLabel("0");
		lblSuma.setBounds(861, 16, 104, 26);
		laminaSup.add(lblSuma);
		lblSuma.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuma.setForeground(new Color(0, 255, 127));
		lblSuma.setFont(new Font("Dialog", Font.BOLD, 18));
		
		lblProveedores = new JLabel("Proveedor");
		lblProveedores.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblProveedores.setBounds(33, 39, 60, 23);
		lblSuma.setBounds(861, 16, 73, 26);
		laminaSup.add(lblProveedores);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 105, 990, 332);

		laminaSup.add(scrollPane_1);
		tabla = new JTable();

		scrollPane_1.setViewportView(tabla);
		tabla.setModel(model);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(67, 452, 73, 25);
		laminaSup.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtDesc = new JTextField();
		txtDesc.setBounds(144, 452, 144, 25);
		laminaSup.add(txtDesc);

		txtDesc.setColumns(10);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblCantidad.setBounds(460, 454, 60, 17);
		laminaSup.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtProveedor, txtLista);
					 
				}
			}
		});
		txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad.setBounds(525, 452, 53, 25);
		laminaSup.add(txtCantidad);

		txtCantidad.setColumns(10);

		lblPrecioLista = new JLabel("Lista");
		lblPrecioLista.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblPrecioLista.setBounds(588, 455, 60, 17);
		laminaSup.add(lblPrecioLista);

		txtLista = new JTextField();
		txtLista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtCantidad, txtB1);
					 
				}
			}
		});
		txtLista.setFont(new Font("Roboto", Font.PLAIN, 11));
		txtLista.setHorizontalAlignment(SwingConstants.CENTER);
		txtLista.setBounds(654, 452, 59, 25);
		laminaSup.add(txtLista);
		txtLista.setColumns(10);

		lblDescuento = new JLabel("Descuento");
		lblDescuento.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescuento.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblDescuento.setBounds(723, 454, 60, 17);
		laminaSup.add(lblDescuento);

	

		lblCosto = new JLabel("Costo");
		lblCosto.setFont(new Font("Roboto", Font.PLAIN, 11));
		lblCosto.setBounds(901, 455, 47, 15);
		laminaSup.add(lblCosto);

		txtCosto = new JTextField();
		txtCosto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				cargarPedido(e);
			}
		});
		txtCosto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCosto.setBounds(939, 452, 53, 25);
		laminaSup.add(txtCosto);
		txtCosto.setColumns(10);

		btnAgregarProducto = new JLabel("");
		btnAgregarProducto.setBounds(796, 493, 51, 41);
		laminaSup.add(btnAgregarProducto);

		btnAgregarProducto.setHorizontalAlignment(SwingConstants.CENTER);
		btnAgregarProducto.setIcon(new ImageIcon(VistaORDEN_PEDIDO.class.getResource("/iconos_1/Agregar.png")));

		btnEditarProducto = new JLabel("");
		btnEditarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rows = model.getRowCount();
				for (int i = 0; i < rows; i++) {				
				
					String idp= (String)model.getValueAt(i, 0);				
					String descripcion = (String)model.getValueAt(i, 2);
					String codigo_proveedor = (String) model.getValueAt(i, 1);
					String cantidadProd = (String) model.getValueAt(i, 4);						
					double total = getImporteTotal(model, 5);
					
					txtCodigo.setText(idp);
					txtDesc.setText(descripcion);
					txtProveedor.setText(codigo_proveedor);
					txtCantidad.setText(cantidadProd);
					
			}
			}
		});
		btnEditarProducto.setBounds(855, 494, 51, 41);
		laminaSup.add(btnEditarProducto);
		btnEditarProducto.setIcon(new ImageIcon(VistaORDEN_PEDIDO.class.getResource("/iconos_1/Editar.png")));
		btnEditarProducto.setHorizontalAlignment(SwingConstants.CENTER);

		btnEliminarProducto = new JLabel("");
		btnEliminarProducto.setBounds(914, 494, 51, 41);
		laminaSup.add(btnEliminarProducto);
		btnEliminarProducto.setIcon(new ImageIcon(VistaORDEN_PEDIDO.class.getResource("/iconos_1/Eliminar.png")));
		btnEliminarProducto.setHorizontalAlignment(SwingConstants.CENTER);

		btnSalir = new JLabel("");
		btnSalir.setBounds(977, 492, 44, 43);
		laminaSup.add(btnSalir);

		btnSalir.setIcon(new ImageIcon(VistaORDEN_PEDIDO.class.getResource("/iconos_1/ReHacer.png")));

		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(VistaORDEN_PEDIDO.class.getResource("/iconos_1/zoom_iwwn.png")));
		label_2.setBounds(24, 444, 36, 33);
		laminaSup.add(label_2);

	}

	public VistaORDEN_PEDIDO() {
		initComponent();
		conexion = new Conexion();
		base = new BaseDatos();
		nOrden.setText(String.valueOf(base.damePosicion(tablaPedido)));

		txtProveedor = new JTextField();
		txtProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtDesc, txtCantidad);
					 
				}
			}
		});
		txtProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		txtProveedor.setColumns(10);
		txtProveedor.setBounds(385, 452, 64, 25);
		laminaSup.add(txtProveedor);

		lblCodigoFabrica = new JLabel("Codigo fabrica");
		lblCodigoFabrica.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodigoFabrica.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblCodigoFabrica.setBounds(294, 455, 81, 17);
		laminaSup.add(lblCodigoFabrica);
		
		txtIdProveedor = new JTextField();
		txtIdProveedor.setColumns(10);
		txtIdProveedor.setBounds(97, 39, 77, 23);
		laminaSup.add(txtIdProveedor);
		
		btnProveedores = new JButton("");		
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
						lblNombreProveedor.setText(prov.getNomProveedor());
						txtIdProveedor.setText(String.valueOf(prov.getIdProveedor()));

					} catch (Exception f) {
						f.printStackTrace();
					}
				}
			
		});
		btnProveedores.setIcon(new ImageIcon(VistaORDEN_PEDIDO.class.getResource("/iconos_1/zoom_in.png")));
		btnProveedores.setBounds(173, 38, 26, 24);
		laminaSup.add(btnProveedores);
		
		lblNombreProveedor = new JLabel("");
		lblNombreProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreProveedor.setFont(new Font("Roboto", Font.PLAIN, 12));
		lblNombreProveedor.setBounds(43, 73, 156, 21);
		laminaSup.add(lblNombreProveedor);
		
		panel = new JPanel();
		panel.setBounds(786, 448, 110, 31);
		laminaSup.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		txtB1 = new JTextField();
		txtB1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtLista, txtB2);
					 
				}
			}
		});
		panel.add(txtB1);
		txtB1.setHorizontalAlignment(SwingConstants.CENTER);
		txtB1.setFont(new Font("Roboto", Font.PLAIN, 11));
		txtB1.setColumns(10);
		
		txtB2 = new JTextField();
		txtB2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtB1, txtB3);
					 
				}
			}
		});
		txtB2.setHorizontalAlignment(SwingConstants.CENTER);
		txtB2.setFont(new Font("Roboto", Font.PLAIN, 11));
		txtB2.setColumns(10);
		panel.add(txtB2);
		
		txtB3 = new JTextField();
		txtB3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtB2, txtB4);
					 
				}
			}
		});
		txtB3.setHorizontalAlignment(SwingConstants.CENTER);
		txtB3.setFont(new Font("Roboto", Font.PLAIN, 11));
		txtB3.setColumns(10);
		panel.add(txtB3);
		
		txtB4 = new JTextField();
		txtB4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtB3, txtCosto);
				 String costo =  String.valueOf(Utilidades.costo(txtLista, txtB1, txtB2, txtB3, txtB4));
					 txtCosto.setText(costo);
				}
			}
		});
		txtB4.setHorizontalAlignment(SwingConstants.CENTER);
		txtB4.setFont(new Font("Roboto", Font.PLAIN, 11));
		txtB4.setColumns(10);
		panel.add(txtB4);
		cargarColumnas();
		eventos();
	

	}



	// CARGA EL PEDIDO EN LA TABLA

	public void cargarPedido(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// DATOS OBTENIDOS DE LOS CAMPOS DE TEXTO
			String descripcion = txtDesc.getText();
			String codigoProd = txtCodigo.getText();
			String cantidad = txtCantidad.getText();
			Double pCosto = Utilidades.costo(txtLista, txtB1,txtB2,txtB3,txtB4);
		
			String codigoProveedor = txtProveedor.getText();
			// VistaPrincipal p = new VistaBuscarProductoOP(new JFrame(),true);

			if (descripcion.isEmpty() || cantidad.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Los campos no deben estar vacios");
				if (!codigoProd.isEmpty() && cantidad.isEmpty()) {
					txtCantidad.requestFocus();
				}
			} else {

				// Producto producto = p.getSeleccion();
				int cant = Integer.parseInt(txtCantidad.getText());
				
				// double importe = Double.parseDouble(pCosto) * cant;// GUARDO IMPORTE
				model = (DefaultTableModel) tabla.getModel();// ESTABLECEMOS EL MODELO DE LA TABLA
				Object ListaProductos[] = { codigoProd, codigoProveedor, descripcion, pCosto, cantidad, pCosto * cant };
				model.addRow(ListaProductos);
				double costo = Utilidades.costo(txtLista, txtB1,txtB2,txtB3,txtB4);

				txtDesc.setText("");
				txtCodigo.setText("");
				txtCantidad.setText("");

				 sumatoria = getImporteTotal(model, 5);
				
				lblSuma.setText(String.valueOf(sumatoria));
				txtCosto.setText(String.valueOf(costo));
			}
			
			txtCosto.requestFocus();
			
			
		}

		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

	public void desactivarComponentes(boolean bool) {
		// comboBoxProveedores.setEnabled(bool);
		txtCantidad.setEnabled(bool);
		txtCodigo.setEnabled(bool);
		txtDesc.setEnabled(bool);

	}

	private void modeloComboBoxProveedores() {

		modeloProveedor = new DefaultComboBoxModel(new BaseDatos().dameProveedores());

	}

	private void modeloComboBoxCategoria() {
		modeloCategoria = new DefaultComboBoxModel(new BaseDatos().dameCategorias());

	}

	private void limpiarTabla() {
		int cantidadFilas = model.getRowCount();
		if (cantidadFilas > 0) {
			for (int i = cantidadFilas - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}

	}

	public void cargarModeloTabla() {

		ArrayList<Producto> listaProducto = base.getProductos();
		int numeroProducto = listaProducto.size();

		modeloTablaArriba.setNumRows(numeroProducto);
		Producto producto = new Producto();
		for (int i = 0; i < numeroProducto; i++) {

			producto = listaProducto.get(i);
			int idProducto = producto.getIdProducto();
			String codigoArticulo = producto.getCodigoArticulo();
			String idFabricaProd = producto.getIdProveedorProducto();
			double pCompra = producto.getPrecioCompraProducto();
			double exis = producto.getStockProducto();

			modeloTablaArriba.setValueAt(codigoArticulo, i, 0);
			modeloTablaArriba.setValueAt(producto, i, 1);
			modeloTablaArriba.setValueAt(idFabricaProd, i, 2);
			modeloTablaArriba.setValueAt(pCompra, i, 3);
			modeloTablaArriba.setValueAt(exis, i, 4);

		}

	}

	// Nota: Alinear los datos de la tabla
	// Centrado
	public void centrar_datos_arriba() {
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		DefaultTableCellRenderer modeloLeft = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		modeloLeft.setHorizontalAlignment(SwingConstants.LEFT);

	}

	public void centrar_datos_abajo() {
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		DefaultTableCellRenderer modeloLeft = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		modeloLeft.setHorizontalAlignment(SwingConstants.LEFT);
		tabla.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tabla.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		tabla.getColumnModel().getColumn(2).setCellRenderer(modeloLeft);
		tabla.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		tabla.getColumnModel().getColumn(4).setCellRenderer(modelocentrar);
		tabla.getColumnModel().getColumn(5).setCellRenderer(modelocentrar);

	}

	// DEVUELVE PRODUCTOS POR ID DE PROVEEDOR
	public void getProducto(int proveedor) {

		ArrayList<Producto> listaProducto = base.getProveedor(proveedor);

		int numeroProducto = listaProducto.size();

		modeloTablaArriba.setNumRows(numeroProducto);
		Producto producto = new Producto();
		for (int i = 0; i < numeroProducto; i++) {

			producto = listaProducto.get(i);

			String codigoArticulo = producto.getCodigoArticulo();
			String idFabricaProd = producto.getIdProveedorProducto();
			double pCompra = producto.getPrecioCompraProducto();
			double exis = producto.getStockProducto();

			modeloTablaArriba.setValueAt(codigoArticulo, i, 0);
			modeloTablaArriba.setValueAt(producto, i, 1);
			modeloTablaArriba.setValueAt(idFabricaProd, i, 2);
			modeloTablaArriba.setValueAt(pCompra, i, 3);
			modeloTablaArriba.setValueAt(exis, i, 4);

		}
	}

	public void eventos() {
		btnBuscarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				damePedido(e);
				desactivarComponentes(false);
				// panel4.setEnabled(false);
			}

			public void damePedido(ActionEvent e) {

				int num_pedido = Integer.parseInt(txtPedido.getText());
				setNumeroPedido(num_pedido);

			}

			void setNumeroPedido(int num_pedido) {

				ArrayList<Pedido> ListaPedido = base.selectPedidoID(num_pedido);
				int numeroProducto = ListaPedido.size();
				importeTotal = 0;

				for (int i = 0; i < numeroProducto; i++) {

					Pedido pedido = ListaPedido.get(i);

					String idClave = pedido.getId_producto();
					int cant = Integer.parseInt(pedido.getCantidad_producto());
					Double importe = pedido.getPrecio_costo();
					double precioVenta = importe * cant;
					String codigo_proveedor = pedido.getId_proveedor_prod();
					int idProveedor = pedido.getId_proveedor();
					// comboBoxProveedores.setSelectedIndex(idProveedor);
					// INSERTA FILAS Y NO BORRA LAS ANTERIORES
					model.insertRow(i, new Object[] { idClave, codigo_proveedor,pedido, importe, cant, precioVenta });
					importeTotal = new Controlador().getImporteTotal(model, 5);
					lblId.setText(pedido.getId_producto());
					lblSuma.setText(String.valueOf(importeTotal));

				}

			}

		});
		// ACTUALIZAR PEDIDO
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					

				}

			}
		});

		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				VistaBuscarProductoOP vistaProducto = Singleton.getVistaBuscarProductoOP();
				vistaProducto.setLocationRelativeTo(null);
				vistaProducto.setVisible(true);
				Producto producto = vistaProducto.getSeleccion();
				txtCodigo.setText(producto.getCodigoArticulo());
				txtDesc.setText(producto.getNomProducto());
				txtB1.setText(producto.getBon1());
				txtB2.setText(producto.getBon2());
				txtB3.setText(producto.getBon3());
				txtB4.setText(producto.getBon4());
				txtProveedor.setText(producto.getIdProveedorProducto());				
				txtLista.setText(String.valueOf(producto.getPrecioCompraProducto()));
				txtProveedor.requestFocus();
			}
		});

		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		// BOTON DE ELIMINAR
		btnEliminarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Controlador().eliminarFila(model, tabla, lblSuma);
			}
		});

		// BOTON DE AGREGAR PEDIDO
		btnAgregarProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int posicion = Integer.parseInt(nOrden.getText());
				Map parametros = new HashMap();
				conn = conexion.getConexion();
				JasperReport reporte = null;
				String path = "/home/ferc/eclipse-workspace/SistemaSeminario/src/reportes/Pedido.jasper";
				JasperPrint jprint = null;
				JasperViewer view = null;
				Proveedor proveedor;
				Producto producto;
				int pres = 0;

				if (posicion == 0) {// INSERTA UN PRESUPUESTO CUANDO NO EXISTE NINGUNO

					int rows = model.getRowCount();
					Calendar calendarioLocal = Calendar.getInstance();
					java.util.Date fechaActual = calendarioLocal.getTime();
					long fechaMailSegun = fechaActual.getTime();
					java.util.Date fecha1 = new Date(fechaMailSegun);
					String fecha = String.valueOf(fecha1);
					ArrayList<Pedido> pedido = base.damePedido();
					int numPedido = 0;
					Pedido p = new Pedido();
					
					for (int j = 0; j < pedido.size(); j++) {
						p = pedido.get(j);
					}
					numPedido = p.getNum_pedido() + 1;

					for (int i = 0; i < rows; i++) {
						p = new Pedido();
						producto = new Producto();
						String idp= (String)model.getValueAt(i, 0);
					
						String descripcion = (String)model.getValueAt(i, 2);
						String codigo_proveedor = (String) model.getValueAt(i, 1);
						String cantidadProd = (String) model.getValueAt(i, 4);						
						double total = getImporteTotal(model, 5);					
						Pedido pd = new Pedido();
						
						pd.setNum_pedido(numPedido);
						pd.setId_proveedor_prod(codigo_proveedor);
						pd.setDescripcion(descripcion);
						pd.setPrecio_costo(producto.getPrecioCompraProducto());
						pd.setFecha(fecha);
						pd.setId_producto(idp);
						pd.setId_proveedor(Integer.parseInt(txtIdProveedor.getText()));
						pd.setPrecio_total(total);
						pd.setCantidad_producto(cantidadProd);
						
						
						base.insertarPedido(pd);
					}

					int seleccion = JOptionPane.showConfirmDialog(null, "�Desea imprimir el pedido?", null,
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					nOrden.setText(String.valueOf(base.damePosicion(tablaPedido)));

					if (seleccion == 0) {// YES

						parametros.put("numPedido", numPedido);
						try {

							reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
							jprint = JasperFillManager.fillReport(reporte, parametros, conn);
							view = new JasperViewer(jprint, false);
							view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							view.setVisible(true);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, e);
						}
					}
				} else {// INSERTA UN PRESUPUESTO OPCION "NO"

					int rows = model.getRowCount();
					Calendar calendarioLocal = Calendar.getInstance();
					java.util.Date fechaActual = calendarioLocal.getTime();
					long fechaMailSegun = fechaActual.getTime();
					java.util.Date fecha1 = new Date(fechaMailSegun);
					String fecha = String.valueOf(fecha1);
					ArrayList<Pedido> pedido = base.damePedido();
					int numPedido = 0;
					Pedido p = new Pedido();
					for (int j = 0; j < pedido.size(); j++) {
						p = pedido.get(j);
					}
					numPedido = p.getNum_pedido() + 1;

					for (int i = 0; i < rows; i++) {
						p = new Pedido();

						producto = new Producto();

						String idp= (String)model.getValueAt(i, 0);					
						String codigo_proveedor = (String) model.getValueAt(i, 1);
						String descripcion = (String)model.getValueAt(i, 2);
						if(descripcion.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No puede esta la descripcion vacia");
						}
						Double costo = (Double)model.getValueAt(i,3);
						String cantidadProd = (String) model.getValueAt(i, 4);						
						double total = getImporteTotal(model, 5);

						// proveedor = (Proveedor) comboBoxProveedores.getSelectedItem();


						Pedido pd = new Pedido();
						

						pd.setNum_pedido(numPedido);
						pd.setId_proveedor_prod(codigo_proveedor);
						pd.setDescripcion(descripcion);
						pd.setFecha(fecha);
						pd.setId_producto(idp);
						pd.setId_proveedor(Integer.parseInt(txtIdProveedor.getText()));
						pd.setPrecio_total(total);
						pd.setCantidad_producto(cantidadProd);
						pd.setPrecio_costo(costo);
						
						base.insertarPedido(pd);

					}

					int seleccion = JOptionPane.showConfirmDialog(null, "Desea imprimir el presupuesto?", null,
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					nOrden.setText(String.valueOf(base.damePosicion(tablaPedido)));
					if (seleccion == 0) {
						parametros.put("numPedido", numPedido);
						try {
							reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
							jprint = fillReport(reporte, parametros, conn);
							view = new JasperViewer(jprint, false);
							view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							view.setVisible(true);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
				limpiarTabla();
				lblSuma.setText("0");
			}

		});
		// IMPRIMIR PEDIDO
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numPedido = Integer.parseInt(txtPedido.getText());
				Map parametros = new HashMap();
				conn = conexion.getConexion();
				JasperReport reporte = null;
				String path = "/home/ferc/eclipse-workspace/SistemaSeminario/src/reportes/Pedido.jasper";
				JasperPrint jprint = null;
				JasperViewer view = null;
				Proveedor proveedor;
				Producto producto;

				int seleccion = JOptionPane.showConfirmDialog(null, "Desea imprimir el pedido?", null,
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (seleccion == 0) {
					parametros.put("numPedido", numPedido);
					try {
						reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
						jprint = fillReport(reporte, parametros, conn);
						view = new JasperViewer(jprint, false);
						view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						view.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		});

	}

	public void cargarProductos(int id) {

		ArrayList<Producto> listaProducto = base.getProducto(id);

		int numeroProducto = listaProducto.size();

		model.setNumRows(numeroProducto);
		Producto producto = new Producto();
		for (int i = 0; i < numeroProducto; i++) {

			producto = listaProducto.get(i);

			String codigoArticulo = producto.getCodigoArticulo();
			String idFabricaProd = producto.getIdProveedorProducto();
			double pCompra = producto.getPrecioCompraProducto();
			double exis = producto.getStockProducto();
			
			model.setValueAt(codigoArticulo, i, 0);
			model.setValueAt(producto, i, 1);
			model.setValueAt(idFabricaProd, i, 2);
			model.setValueAt(pCompra, i, 3);
			// modeloTablaAbajo.setValueAt(cantidad, i, 4);
			model.setValueAt(exis, i, 5);
		}
	}

	/*
	 * private void cargarColumnas() {
	 * 
	 * modeloTablaArriba.addColumn("Codigo");
	 * modeloTablaArriba.addColumn("Descripcion");
	 * modeloTablaArriba.addColumn("C.Proveedor");
	 * modeloTablaArriba.addColumn("Costo"); modeloTablaArriba.addColumn("Stock");
	 * 
	 * ColCodigo.setMaxWidth(80); ColNombre.setMinWidth(300);
	 * 
	 * }
	 */

	private void cargarColumnas() {

		model.addColumn("CODIGO");
		model.addColumn("CODIGO PROVEEDOR");
		model.addColumn("DESCRIPCION");
		model.addColumn("PRECIO DE COSTO");
		model.addColumn("CANTIDAD");
		model.addColumn("IMPORTE");

		TableColumn Coldi = tabla.getColumn("CODIGO");
		TableColumn ColCodigo = tabla.getColumn("CODIGO PROVEEDOR");
		TableColumn ColNombre = tabla.getColumn("DESCRIPCION");
		TableColumn ColProve = tabla.getColumn("PRECIO DE COSTO");
		TableColumn ColVent = tabla.getColumn("CANTIDAD");
		TableColumn ColStock = tabla.getColumn("IMPORTE");

		Coldi.setMaxWidth(80);
		ColNombre.setMinWidth(300);
	}



	private DefaultTableModel modeloTablaArriba = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private DefaultTableModel model = new DefaultTableModel() {

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;

		}
	};
	
	private double getImporteTotal(DefaultTableModel model, int columna) {

		int numFilas = model.getRowCount();
		double sumatoria = 0;
		for (int i = 0; i < numFilas; i++) {
			double importe = (double) model.getValueAt(i, columna);
			sumatoria += (importe);

		}

		return sumatoria;

	}
	// DECLARACION DE VARIABLES
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo, txtCantidad, txtDesc, txtBon1,txtBon2,txtBon3,txtBon4, txtLista, txtCosto, txtPedido;
	private BaseDatos base;
	private JButton btnImprimir, btnBuscarPedido;
	private int filaSeleccionada;
	private ButtonGroup grupo_botones;
	private JTable tabla;
	private DefaultComboBoxModel modeloProveedor, modeloCategoria;
	private JLabel lblSuma, lblBuscarPedido_1, label_2, btnSalir, lblPrecioLista, lblTotalPresupuesto, lblDescuento,
			lblCantidad, lblCosto, btnEditarProducto;
	private JPanel laminaSup;
	private final String tablaPedido = "pedido";
	public JLabel nOrden, lblId, btnAgregarProducto, btnEliminarProducto, lblOrdenDePedido;
	Connection conn;
	Conexion conexion = null;
	private JButton button_1;
	private double importeTotal = 0;
	private double sumatoria = 0;
	private JTextField txtProveedor;
	private JLabel lblCodigoFabrica,lblProveedores;
	private LaminaBotones laminaBotones;
	private JTextField txtIdProveedor;
	private JButton btnProveedores;
	private JLabel lblNombreProveedor;
	private JTextField txtB1;
	private JPanel panel;
	private JTextField txtB2;
	private JTextField txtB3;
	private JTextField txtB4;
}
