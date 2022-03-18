package vista.Inventario;

import java.awt.AWTEvent;
import paneles.LaminaBotones;
import java.awt.ActiveEvent;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.BaseDatos;
import modelo.Categoria;

import modelo.Producto;
import modelo.Proveedor;
import singleton.Singleton;
import utilidades.Utilidades;
import vista.Categorias.VistaCATEGORIA;
import vista.Principal.VistaPRINCIPAL;
import vista.Proveedores.VistaPROVEEDORES;

import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import utilidades.Focus;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;

public class VistaINVENTARIO extends JInternalFrame {

	private JPanel laminaP, btnLimpiar, Lamina;
	public File imgArticleFile;
	private JScrollPane jScrollPane4;
	private ButtonGroup grupo_botones;
	private BaseDatos bd = new BaseDatos();
	private Categoria categoria = null;
	private Proveedor proveedor = null;
	private JLabel lblLimpiar;
	private JTable tablaProductos;
	private JTextField txtBon1;
	private JTextField txtBon2;
	private JTextField txtBon3;
	private JTextField txtBon4;
	private JTextField txtProveedor;
	private JTextField txtCategoria;
	private JLabel btnProveedores;
	private JLabel lblCategoria;
	private JLabel btnCategoria, btnModel, btnSalir, btnActualizar;
	private JTextField txtCosto;
	// BOTONES
	public JButton btnEliminar, LimpiarCampos;
	// CAMPOS DE TEXTO
	public JTextField txtIdProduct, txtNombre, txtClaveProveedor, txtDesc, txtIva, txtGanancia, txtStock, txtIdProducto,
			txtPrecioLista, txtPrecioVenta, txtFlete, txtDolar;

	// ETIQUETAS
	public JLabel proveedores, categorias, lblProveedores, imgLabel, labe, lblCosto, lblCosto_5, lblCosto_6, lblCosto_7,
			lblCosto_8, lblCosto_9, lblCosto_10, lblCosto_6_1, lblCosto_10_1;

	// MODELOS PARA COMBOBOX
	private DefaultComboBoxModel<Categoria> modeloCategoria = new DefaultComboBoxModel<Categoria>();
	private DefaultComboBoxModel<Proveedor> modeloProveedor = new DefaultComboBoxModel<Proveedor>();
	private JRadioButton buttonProve, buttonDesc, buttonCodigo;
	private TitledBorder title2, title;
	private LaminaBotones laminabotones;

	public VistaINVENTARIO(int proveedor) {
		getContentPane().setBackground(Color.LIGHT_GRAY);

		initComponents();
		encabezadoTabla();
		cargarModeloTabla();
		modeloComboBoxProveedor();
		modeloComboBoxCategoria();
		eventKey();
		eventButton();
		centrarTabla();
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

	}

	private void eventButton() {
		// CARGAR MODELO
		btnModel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarModeloTabla();
			}
		});
		// SALIR
		btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (new VistaPRINCIPAL() != null) {
					dispose();

				}
			}

		});
		// INSERTAR
		laminabotones.getBtnInsertar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String codigoArticulo = txtIdProducto.getText();
				String nombre = txtNombre.getText().toUpperCase();
				int stock = Integer.parseInt(txtStock.getText());
				String codigoProveedor = txtClaveProveedor.getText().toUpperCase();
				double pCosto = Double.parseDouble(txtPrecioLista.getText());
				double pVenta = Double.parseDouble(txtPrecioVenta.getText());
				String bon1 = txtBon1.getText();
				String bon2 = txtBon2.getText();
				String bon3 = txtBon3.getText();
				String bon4 = txtBon4.getText();
				double pDolar = Double.parseDouble(txtDolar.getText());
				double iva = Double.parseDouble(txtIva.getText());
				double dolar = Double.parseDouble(txtDolar.getText());
				double flete = Double.parseDouble(txtFlete.getText());
				double ganancia = Double.parseDouble(txtGanancia.getText());
				int cat = Integer.parseInt(txtCategoria.getText());
				int pro = Integer.parseInt(txtProveedor.getText());

				if (imgArticleFile == null) {

					Producto producto = new Producto();
					producto.setCodigoArticulo(codigoArticulo);
					producto.setNomProducto(nombre);
					producto.setStockProducto(stock);
					producto.setIdProveedorProducto(codigoProveedor);
					producto.setFotoProducto(imgArticleFile);
					producto.setPrecioCompraProducto(pCosto);
					producto.setPrecioVentaProducto(pVenta);
					producto.setBon1(bon1);
					producto.setBon2(bon2);
					producto.setBon3(bon3);
					producto.setBon4(bon4);
					producto.setDolar(dolar);
					producto.setExistenciasProducto(0);
					producto.setIdCategoria(cat);
					producto.setIdProveedor(pro);
					producto.setIva(iva);
					producto.setFlete(flete);
					producto.setGanancia(ganancia);

					bd.insertProducto(producto);

					cargarModeloTabla();

				} else {

					Producto producto = new Producto();

					producto.setCodigoArticulo(codigoArticulo);
					producto.setNomProducto(nombre);
					producto.setStockProducto(stock);
					producto.setIdProveedorProducto(codigoProveedor);
					producto.setFotoProducto(imgArticleFile);
					producto.setPrecioCompraProducto(pCosto);
					producto.setPrecioVentaProducto(pVenta);
					producto.setBon1(bon1);
					producto.setBon2(bon2);
					producto.setBon3(bon3);
					producto.setBon4(bon4);
					producto.setDolar(dolar);
					producto.setExistenciasProducto(0);
					producto.setIdCategoria(cat);
					producto.setIdProveedor(pro);
					producto.setIva(iva);
					producto.setFlete(flete);
					producto.setGanancia(ganancia);
					bd.insertProducto(producto);

					cargarModeloTabla();
				}

			}
		});
		// AÑADIR UNA IMAGEN AL PRODUCTO
		imgLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addImagen();
			}
		});
		// BOTON LIMPIAR CAMPOS
		lblLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLimpiar.setBackground(Color.RED);
				lblLimpiar.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpiar.setBackground(Color.white);
				lblLimpiar.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarCampo();
			}
		});
		// ABM DE PROVEEDORES
		btnProveedores.addMouseListener(new MouseAdapter() {

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
					lblProveedores.setText(prov.getNomProveedor());
					txtProveedor.setText(String.valueOf(prov.getIdProveedor()));

				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		// ABM CATEGORIAS
		btnCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					VistaCATEGORIA categoria = new Singleton().getVistaCATEGORIA();
					EventQueue eq = new EventQueue();
					while (categoria.isShowing()) {
						if (EventQueue.isDispatchThread()) {
							AWTEvent ev = eq.getNextEvent();
							if (ev instanceof ActiveEvent)
								((ActiveEvent) ev).dispatch();
							else if (ev.getSource() instanceof Component)
								((Component) ev.getSource()).dispatchEvent(ev);
						} else
							Thread.yield();
					}
					categoria.setLocationRelativeTo(null);
					categoria.setVisible(true);
					Categoria cat = categoria.getSeleccion();
					lblCategoria.setText(cat.getNomCategoria());
					txtCategoria.setText(String.valueOf(cat.getIdCategoria()));
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
	}

	private void eventKey() {
		txtBon1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtPrecioLista, txtBon2);
					// txtCosto.setText(String.valueOf(Utilidades.costo(txtPrecioLista, txtBon2)));
				}

			}
		});

		txtPrecioLista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtCostoKeyPressed(e);
			}
		});

		txtIva.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtIvaKeyPressed(e);
			}
		});

		txtStock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Focus.hacerFoco(e, txtGanancia, btnProveedores);
			}
		});
		txtIdProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtidProductoKeyPressed(e);
			}
		});

		txtFlete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtFleteKeyPressed(e);
			}
		});

		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtNombreKeyPressed(e);
			}
		});

		txtClaveProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtClaveProveedorKeyPressed(e);
			}
		});

		txtDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscarPorDescipcion(e);
			}
		});
	}

	// INICIAR LOS COMPONENTESs
	private void initComponents() {
		// TAMAÑO Y LOCALIZACION
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1280, 1300);
		getContentPane().setLayout(null);
		laminaP = new JPanel();
		laminaP.setBounds(0, 0, 0, 0);
		laminaP.setBorder(new EmptyBorder(5, 5, 5, 5));
		laminaP.setLayout(new BorderLayout(0, 0));
		getContentPane().add(laminaP);
		int id = bd.damePosicion("cat_productos");
		setTitle("Añadir un articulo");
		lblCosto_6_1 = new JLabel("Codigo Art");
		lblCosto_6_1.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto_6_1.setBounds(377, 36, 125, 35);
		txtIdProduct = new JTextField();
		txtIdProduct.setBounds(572, 323, 43, 21);
		lblCosto_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCosto_6_1);

		title = BorderFactory.createTitledBorder("Rubro");
		title2 = BorderFactory.createTitledBorder("Proveedores");
		getContentPane().add(txtIdProduct);
		txtIdProduct.setColumns(10);

		txtIdProduct.setText(String.valueOf(id));

		lblCosto_10_1 = new JLabel("Bonificaciones");
		lblCosto_10_1.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto_10_1.setBounds(0, 103, 113, 35);
		lblCosto_10_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCosto_10_1);
		txtIdProduct.setVisible(false);

		lblProveedores = new JLabel("");
		lblProveedores.setBounds(239, 223, 139, 26);
		getContentPane().add(lblProveedores);

		proveedores = new JLabel("Proveedores");
		proveedores.setFont(new Font("Roboto", Font.BOLD, 13));
		proveedores.setBounds(24, 222, 92, 17);
		getContentPane().add(proveedores);

		categorias = new JLabel("Categorias");
		categorias.setFont(new Font("Roboto", Font.BOLD, 13));
		categorias.setBounds(24, 251, 89, 33);
		getContentPane().add(categorias);
		lblCosto = new JLabel("Precio de lista");
		lblCosto.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto.setBounds(0, 68, 125, 35);
		lblCosto.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCosto);

		txtPrecioLista = new JTextField();
		txtPrecioLista.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtPrecioLista.setBounds(125, 68, 141, 35);
		txtPrecioLista.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecioLista.setText("0.0");
		getContentPane().add(txtPrecioLista);
		txtPrecioLista.setColumns(10);

		lblCosto_6 = new JLabel("Costo Dolar");
		lblCosto_6.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto_6.setBounds(377, 68, 125, 35);
		lblCosto_6.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCosto_6);

		txtDolar = new JTextField();
		txtDolar.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtDolar.setBounds(502, 68, 125, 35);
		txtDolar.setHorizontalAlignment(SwingConstants.CENTER);
		txtDolar.setText("0.0");
		txtDolar.setColumns(10);
		getContentPane().add(txtDolar);

		lblCosto_7 = new JLabel("Iva");
		lblCosto_7.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto_7.setBounds(2, 138, 125, 35);
		lblCosto_7.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCosto_7);

		txtIva = new JTextField();
		txtIva.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtIva.setBounds(125, 138, 141, 35);
		txtIva.setHorizontalAlignment(SwingConstants.CENTER);
		txtIva.setText("0.0");

		txtIva.setColumns(10);
		getContentPane().add(txtIva);

		lblCosto_8 = new JLabel("Ganancia");
		lblCosto_8.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto_8.setBounds(377, 103, 125, 35);
		lblCosto_8.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCosto_8);

		txtGanancia = new JTextField();
		txtGanancia.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtGanancia.setBounds(502, 103, 125, 35);
		txtGanancia.setHorizontalAlignment(SwingConstants.CENTER);
		txtGanancia.setText("0.0");
		txtGanancia.setColumns(10);
		getContentPane().add(txtGanancia);

		lblCosto_9 = new JLabel("Stock");
		lblCosto_9.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto_9.setBounds(377, 138, 125, 35);
		lblCosto_9.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCosto_9);

		txtStock = new JTextField();
		txtStock.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtStock.setBounds(502, 138, 125, 35);

		txtStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtStock.setText("0");
		txtStock.setColumns(10);
		getContentPane().add(txtStock);

		lblCosto_10 = new JLabel("Precio Venta");
		lblCosto_10.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto_10.setBounds(377, 171, 125, 35);
		lblCosto_10.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCosto_10);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setText("0.0");
		txtPrecioVenta.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtPrecioVenta.setBounds(502, 171, 125, 35);
		txtPrecioVenta.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecioVenta.setColumns(10);
		getContentPane().add(txtPrecioVenta);

		lblCosto_5 = new JLabel("Flete%");
		lblCosto_5.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto_5.setBounds(1, 171, 125, 35);
		lblCosto_5.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCosto_5);

		txtFlete = new JTextField();
		txtFlete.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtFlete.setBounds(125, 173, 141, 35);
		txtFlete.setHorizontalAlignment(SwingConstants.CENTER);
		txtFlete.setText("0.0");

		txtFlete.setColumns(10);
		getContentPane().add(txtFlete);

		txtIdProducto = new JTextField();
		txtIdProducto.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtIdProducto.setBounds(502, 36, 125, 35);
		txtIdProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdProducto.setText("0.0");

		txtIdProducto.setColumns(10);
		getContentPane().add(txtIdProducto);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtNombre.setBounds(125, 1, 502, 35);

		txtNombre.setColumns(10);
		getContentPane().add(txtNombre);

		JLabel lblCodigoProveedor = new JLabel("Cod.Proveedor");
		lblCodigoProveedor.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCodigoProveedor.setBounds(0, 36, 125, 35);
		lblCodigoProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblCodigoProveedor);

		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Roboto", Font.BOLD, 13));
		lblProducto.setBounds(0, 1, 125, 35);
		lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblProducto);

		txtClaveProveedor = new JTextField();
		txtClaveProveedor.setBounds(125, 36, 141, 35);

		txtClaveProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		txtClaveProveedor.setText("0.0");

		txtClaveProveedor.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		getContentPane().add(txtClaveProveedor);

		txtDesc = new JTextField();
		txtDesc.setBounds(38, 320, 366, 35);

		txtDesc.setColumns(10);
		getContentPane().add(txtDesc);

		grupo_botones = new ButtonGroup();

		buttonDesc = new JRadioButton();
		buttonDesc.setBackground(Color.WHITE);
		buttonDesc.setFont(new Font("Roboto Condensed", Font.BOLD, 13));
		buttonDesc.setBounds(22, 358, 137, 23);
		buttonDesc.setSelected(true);
		buttonDesc.setText("Descripcion (F1)");
		getContentPane().add(buttonDesc);

		buttonCodigo = new JRadioButton();
		buttonCodigo.setBackground(Color.WHITE);
		buttonCodigo.setFont(new Font("Roboto Condensed", Font.BOLD, 13));
		buttonCodigo.setBounds(174, 358, 104, 23);
		buttonCodigo.setText("Codigo (F2)");
		getContentPane().add(buttonCodigo);

		buttonProve = new JRadioButton();
		buttonProve.setBackground(Color.WHITE);
		buttonProve.setFont(new Font("Roboto Condensed", Font.BOLD, 13));
		buttonProve.setBounds(290, 358, 139, 23);
		buttonProve.setText("Codigo Prov (F3)");
		getContentPane().add(buttonProve);

		grupo_botones.add(buttonDesc);
		grupo_botones.add(buttonCodigo);
		grupo_botones.add(buttonProve);

		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 388, 1082, 322);
		getContentPane().add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));

		jScrollPane4 = new JScrollPane();
		panel2.add(jScrollPane4);

		tablaProductos = new JTable();
		tablaProductos.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
		jScrollPane4.setViewportView(tablaProductos);
		tablaProductos.setGridColor(Color.WHITE);
		Lamina = new JPanel();
		Lamina.setBounds(696, 12, 391, 265);
		Lamina.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(Lamina);
		Lamina.setLayout(null);

		imgLabel = new JLabel("");

		imgLabel.setBounds(1, 1, 384, 276);
		Lamina.add(imgLabel);

		getContentPane().setLayout(null);
		tablaProductos.setModel(modelTabla);

		tablaProductos.setIntercellSpacing(new java.awt.Dimension(4, 4));
		tablaProductos.setRowHeight(25);
		tablaProductos.setModel(modelTabla);

		txtProveedor = new JTextField();
		txtProveedor.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtProveedor.setEnabled(false);
		txtProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		txtProveedor.setText("0");
		txtProveedor.setBounds(126, 217, 65, 27);
		getContentPane().add(txtProveedor);
		txtProveedor.setColumns(10);

		txtCategoria = new JTextField();
		txtCategoria.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtCategoria.setEnabled(false);
		txtCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		txtCategoria.setText("0");
		txtCategoria.setBounds(125, 263, 67, 27);
		getContentPane().add(txtCategoria);
		txtCategoria.setColumns(10);

		btnLimpiar = new JPanel();
		btnLimpiar.setBackground(Color.WHITE);
		btnLimpiar.setBounds(428, 320, 113, 35);
		getContentPane().add(btnLimpiar);

		lblLimpiar = new JLabel("Limpiar");
		lblLimpiar.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimpiar.setFont(new Font("Roboto Black", Font.BOLD, 16));
		lblLimpiar.setBounds(5, 0, 108, 35);

		btnLimpiar.setLayout(null);
		btnLimpiar.add(lblLimpiar);

		btnProveedores = new JLabel("");
		btnProveedores.setBounds(199, 220, 24, 24);
		getContentPane().add(btnProveedores);

		btnProveedores.setIcon(new ImageIcon(VistaINVENTARIO.class.getResource("/iconos_1/zoom_iwwn.png")));

		lblCategoria = new JLabel("");
		lblCategoria.setBounds(239, 263, 152, 26);
		getContentPane().add(lblCategoria);

		btnCategoria = new JLabel("");

		btnCategoria.setIcon(new ImageIcon(VistaINVENTARIO.class.getResource("/iconos_1/zoom_iwwn.png")));
		btnCategoria.setBounds(200, 266, 37, 26);
		getContentPane().add(btnCategoria);

		JLabel lblCosto_10_2 = new JLabel("Precio de costo");
		lblCosto_10_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosto_10_2.setFont(new Font("Roboto", Font.BOLD, 13));
		lblCosto_10_2.setBounds(377, 205, 125, 35);
		getContentPane().add(lblCosto_10_2);

		txtCosto = new JTextField();
		txtCosto.setText("0.0");
		txtCosto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCosto.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtCosto.setColumns(10);
		txtCosto.setBounds(503, 205, 125, 35);
		getContentPane().add(txtCosto);
		laminabotones = new LaminaBotones();
		laminabotones.setBounds(696, 305, 391, 50);
		getContentPane().add(laminabotones);
		laminabotones.getBtnEliminar().setBackground(Color.WHITE);
		laminabotones.getBtnEditar().setBackground(Color.WHITE);
		laminabotones.getBtnInsertar().setBackground(Color.WHITE);
		laminabotones.getBtnEliminar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int id = Integer.parseInt(txtIdProduct.getText());
				int delete = JOptionPane.showConfirmDialog(null, "¿Desea eliminar este articulo");
				if (delete == 0) {
					bd.borrarArticulo(id);
					cargarModeloTabla();
				}
			}
		});
		laminabotones.getBtnEditar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num = JOptionPane.showConfirmDialog(null, "¿Desea modificar un producto?",
						"Modificacion de Articulos", JOptionPane.YES_NO_OPTION);
				if (num == 0) {
					activarCompomponentes();
					setTitle("Modificar Producto seleccionado");
				}
			}
		});

		btnActualizar = new JLabel("");
		btnActualizar.setHorizontalAlignment(SwingConstants.CENTER);
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizarProducto();
			}
		});
		btnActualizar.setIcon(new ImageIcon(VistaINVENTARIO.class.getResource("/iconos_1/Aceptar.png")));
		laminabotones.add(btnActualizar);

		btnModel = new JLabel("");

		btnModel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_btnModel = new GridBagConstraints();
		gbc_btnModel.anchor = GridBagConstraints.EAST;
		gbc_btnModel.insets = new Insets(0, 0, 0, 5);
		gbc_btnModel.gridx = 3;
		gbc_btnModel.gridy = 0;
		laminabotones.add(btnModel, gbc_btnModel);
		btnModel.setBackground(Color.WHITE);
		btnModel.setIcon(new ImageIcon(VistaINVENTARIO.class.getResource("/iconos_1/Visualizar.png")));

		btnSalir = new JLabel("");
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setHorizontalAlignment(SwingConstants.CENTER);

		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalir.gridx = 4;
		gbc_btnSalir.gridy = 0;
		laminabotones.add(btnSalir, gbc_btnSalir);

		btnSalir.setIcon(new ImageIcon(VistaINVENTARIO.class.getResource("/iconos_1/ReHacer.png")));

		JPanel panel = new JPanel();
		panel.setBounds(123, 102, 143, 36);
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		txtBon1 = new JTextField();
		txtBon1.setText("0");
		panel.add(txtBon1);
		txtBon1.setFont(new Font("Roboto Black", Font.PLAIN, 14));

		txtBon1.setHorizontalAlignment(SwingConstants.CENTER);
		txtBon1.setColumns(10);

		txtBon2 = new JTextField();
		txtBon2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtBon2, txtBon3);
					// txtCosto.setText(String.valueOf(Utilidades.costo(txtPrecioLista, txtBon2)));
				}
			}
		});

		txtBon2.setText("0");
		panel.add(txtBon2);
		txtBon2.setHorizontalAlignment(SwingConstants.CENTER);
		txtBon2.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtBon2.setColumns(10);

		txtBon3 = new JTextField();
		txtBon3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtBon3, txtBon4);
					// txtCosto.setText(String.valueOf(Utilidades.costo(txtPrecioLista, txtBon2)));
				}
			}
		});
		txtBon3.setText("0");
		panel.add(txtBon3);
		txtBon3.setHorizontalAlignment(SwingConstants.CENTER);
		txtBon3.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtBon3.setColumns(10);

		txtBon4 = new JTextField();
		txtBon4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Focus.hacerFoco(e, txtBon3, txtIva);
					 txtCosto.setText(String.valueOf(Utilidades.costo(txtPrecioLista, txtBon1,txtBon2,txtBon3,txtBon4)));
				}
			}
		});
		txtBon4.setText("0");
		panel.add(txtBon4);
		txtBon4.setHorizontalAlignment(SwingConstants.CENTER);
		txtBon4.setFont(new Font("Roboto Black", Font.PLAIN, 14));
		txtBon4.setColumns(10);
		// CLICK EN TABLA
		tablaProductos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && (tablaProductos.getSelectedRow() >= 0)) {
					Producto producto = (Producto) modelTabla.getValueAt(tablaProductos.getSelectedRow(), 1);
					txtIdProduct.setText(String.valueOf(producto.getIdProducto()));
					txtIdProducto.setText(String.valueOf(producto.getCodigoArticulo()));
					txtNombre.setText(String.valueOf(producto.getNomProducto().toUpperCase()));
					txtStock.setText(String.valueOf(producto.getStockProducto()));
					txtPrecioVenta.setText(String.valueOf(producto.getPrecioVentaProducto()));
					txtPrecioLista.setText(String.valueOf(producto.getPrecioCompraProducto()));
					txtClaveProveedor.setText(producto.getIdProveedorProducto());
					txtIva.setText(String.valueOf(producto.getIva()));
					txtBon1.setText(producto.getBon1());
					txtBon2.setText(producto.getBon2());
					txtBon3.setText(producto.getBon3());
					txtBon4.setText(producto.getBon4());
					txtFlete.setText(String.valueOf(producto.getFlete()));
					txtGanancia.setText(String.valueOf(producto.getGanancia()));
					txtProveedor.setText(String.valueOf(producto.getIdProveedor()));
					txtCategoria.setText(String.valueOf(producto.getIdCategoria()));
					lblCategoria.setText(bd.dameCategoria(producto.getIdCategoria()));
					lblProveedores.setText(bd.dameProveedor(producto.getIdProveedor()));

					desplegarFoto(producto);
					desactivarComponentes();

				}
			}
		});

		// CAMBIA
		txtGanancia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Double txtLista = Double.parseDouble(txtPrecioLista.getText());
					Double B1 = Double.parseDouble(txtBon1.getText());
					Double B2 = Double.parseDouble(txtBon2.getText());
					Double B3 = Double.parseDouble(txtBon3.getText());
					Double B4 = Double.parseDouble(txtBon4.getText());
					Double Iva = Double.parseDouble(txtIva.getText());
					Double flete = Double.parseDouble(txtFlete.getText());
					Double ganancia = Double.parseDouble(txtGanancia.getText());

					double pVenta = txtLista * (1 - (B1 / 100)) * (1 - (B2 / 100)) * (1 - (B3 / 100)) * (1 - (B4 / 100))
							* (1 + (Iva / 100)) * (1 + (flete / 100)) * (1 + (ganancia / 100));

					txtPrecioVenta.setText(String.valueOf(pVenta));

				}

			}
		});

	}

	public void buscarPorDescipcion(KeyEvent e) {

		if (buttonDesc.isSelected()) {
			articulos(buttonDesc);
		} else if (buttonCodigo.isSelected()) {
			articulos(buttonCodigo);
		} else {
			articulos(buttonProve);
		}

	}

	public ArrayList<Producto> seleccionarBusqueda(String cadena) {
		ArrayList<Producto> articulos = new ArrayList<Producto>();

		if (buttonCodigo.isSelected() && !cadena.equals("")) {

			articulos = bd.obtenerProductosPorCodigo(cadena);

		} else if (buttonDesc.isSelected()) {
			articulos = bd.selectProductosCriterioDesc(cadena);
		} else {
			articulos = bd.obtenerProductosPorCodigoProveedor(cadena);
		}
		return articulos;
	}

	public void articulos(JRadioButton radio) {

		ArrayList<Producto> articulosDesc = seleccionarBusqueda(txtDesc.getText());
		int numeroProducto = articulosDesc.size();
		modelTabla.setNumRows(numeroProducto);
		for (int i = 0; i < numeroProducto; i++) {
			Producto producto = articulosDesc.get(i);
			String codigoArticulo = producto.getCodigoArticulo();
			String nomBre = producto.getNomProducto();
			String idFabricaProd = producto.getIdProveedorProducto();
			Double pventa = producto.getPrecioVentaProducto();
			double stock = producto.getStockProducto();

			modelTabla.setValueAt(codigoArticulo, i, 0);
			modelTabla.setValueAt(producto, i, 1);
			modelTabla.setValueAt(idFabricaProd, i, 2);
			modelTabla.setValueAt(pventa, i, 3);
			modelTabla.setValueAt(stock, i, 4);
		}
	}

	// METODO QUE AÑADE LA IMAGEN
	public void addImagen() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Extension", "jpg", "png", "gif");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			imgArticleFile = chooser.getSelectedFile();

			ImageIcon icono = new ImageIcon(imgArticleFile.getAbsolutePath());

			ImageIcon icono2 = new ImageIcon(
					icono.getImage().getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_DEFAULT));

			imgLabel.setIcon(icono2);
		}

	}

	public void desplegarFoto(Producto prod) {

		ImageIcon ImagenProducto = null;

		try {
			InputStream is = new BaseDatos().buscarFoto(prod);
			BufferedImage bi = ImageIO.read(is);
			ImagenProducto = new ImageIcon(bi);

			Image imgProd = ImagenProducto.getImage();
			int ancho = imgLabel.getWidth();
			int alto = imgLabel.getHeight();

			ImageIcon icono2 = new ImageIcon(
					ImagenProducto.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));

			imgLabel.setIcon(icono2);

		} catch (Exception e) {

		}

	}

	private void encabezadoTabla() {

		modelTabla.addColumn("codigo");
		modelTabla.addColumn("nombre");
		modelTabla.addColumn("c.prov");
		modelTabla.addColumn("venta");
		modelTabla.addColumn("stock");

		TableColumn ColCodigo = tablaProductos.getColumn("codigo");
		TableColumn ColNombre = tablaProductos.getColumn("nombre");
		TableColumn ColProve = tablaProductos.getColumn("c.prov");
		TableColumn ColVent = tablaProductos.getColumn("venta");
		TableColumn ColStock = tablaProductos.getColumn("stock");

		ColCodigo.setMaxWidth(90);
		ColNombre.setMinWidth(200);

	}

	private DefaultTableModel modelTabla = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	public void cargarModeloTabla() {

		ArrayList<Producto> listaProducto = bd.getProductos();
		int numeroProducto = listaProducto.size();
		modelTabla.setNumRows(numeroProducto);
		Producto producto = new Producto();
		for (int i = 0; i < numeroProducto; i++) {
			producto = listaProducto.get(i);
			int id = producto.getIdProducto1();
			String codigoArticulo = producto.getCodigoArticulo();
			String idFabricaProd = producto.getIdProveedorProducto();
			Double pventa = producto.getPrecioVentaProducto();
			double exis = producto.getStockProducto();

			modelTabla.setValueAt(codigoArticulo, i, 0);
			modelTabla.setValueAt(producto, i, 1);
			modelTabla.setValueAt(idFabricaProd, i, 2);
			modelTabla.setValueAt(pventa, i, 3);
			modelTabla.setValueAt(exis, i, 4);

		}

	}

	private void LimpiarLista() {
		int numFilas = modelTabla.getRowCount();
		if (numFilas > 0) {
			for (int i = numFilas - 1; i < 0; i--) {
				modelTabla.removeRow(i);
			}

		}
	}

	public String dameSeleccion() {

		return grupo_botones.getSelection().getActionCommand();
	}

	public void buscarPorDescripcion() {

		txtDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {

				if (ke.getKeyCode() == KeyEvent.VK_END)
					LimpiarLista();
				String cadena = txtDesc.getText();
				ArrayList<Producto> listaProductos = bd.selectProductosCriterioDesc(cadena);
				int numeroProducto = listaProductos.size();
				modelTabla.setNumRows(numeroProducto);
				for (int i = 0; i < numeroProducto; i++) {

					Producto producto = listaProductos.get(i);
					String codigoArticulo = producto.getCodigoArticulo();
					String nomBre = producto.getNomProducto();
					String idFabricaProd = producto.getIdProveedorProducto();
					Double pventa = producto.getPrecioVentaProducto();
					double exis = producto.getStockProducto();

					modelTabla.setValueAt(codigoArticulo, i, 0);
					modelTabla.setValueAt(producto, i, 1);
					modelTabla.setValueAt(idFabricaProd, i, 2);
					modelTabla.setValueAt(pventa, i, 3);
					modelTabla.setValueAt(exis, i, 4);

				}
			}
		});

	}

	private void limpiarCampo() {
		txtNombre.setText("");
		txtPrecioLista.setText("0.00");
		txtPrecioVenta.setText("0.00");
		txtClaveProveedor.setText("0.00");
		txtBon1.setText("0");
		txtBon2.setText("0");
		txtBon3.setText("0");
		txtBon4.setText("0");
		txtFlete.setText("0.00");
		txtIva.setText("0.00");
		txtIdProducto.setText("0.00");
		txtIdProducto.setEnabled(true);
		txtStock.setText("0");
		txtGanancia.setText("0.00");
		int id = bd.damePosicion("cat_productos");
		txtIdProduct.setText(String.valueOf(id));
	}

	// LIMPIAR CAMPOS
	public void DeleteProducto() {
		try {
			int idProducto = Integer.parseInt(txtIdProduct.getText());
			String codigoArticulo = txtIdProducto.getText();
			String nombre = "";
			int stock = 0;
			String codigoProveedor = "";
			double pCosto = 0;
			double precioVenta = 0;
			String descuentos = "0+0+0+0";
			double iva = 0;
			double dolar = 0;
			String bon1 = "0";
			String bon2 = "0";
			String bon3 = "0";
			String bon4 = "0";
			double flete = 0;
			double ganancia = 0;
			int existencia = 0;

			// precioVenta = precioVenta();
			txtPrecioVenta.setText(String.valueOf(precioVenta));

			Producto producto = new Producto();

			producto.setIdProducto(idProducto);
			producto.setCodigoArticulo(codigoArticulo);
			producto.setNomProducto(nombre);
			producto.setStockProducto(stock);
			producto.setIdProveedorProducto(codigoProveedor);
			producto.setFotoProducto(imgArticleFile);
			producto.setPrecioCompraProducto(pCosto);
			producto.setPrecioVentaProducto(precioVenta);
			producto.setBon1(bon1);
			producto.setBon2(bon2);
			producto.setBon3(bon3);
			producto.setBon4(bon4);
			producto.setDolar(dolar);
			producto.setExistenciasProducto(existencia);
			producto.setIdCategoria(categoria.getIdCategoria());
			producto.setIdProveedor(proveedor.getIdProveedor());
			producto.setIva(iva);
			producto.setFlete(flete);
			producto.setGanancia(ganancia);

			bd.updateProducto(producto, false);

			cargarModeloTabla();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "NO ACTUALIZO IMAGEN");
		}
	}

	public void actualizarProducto() {
		try {

			Producto producto = new Producto();

			int idProducto = Integer.parseInt(txtIdProduct.getText());
			String codigoArticulo = txtIdProducto.getText();
			String nombre = txtNombre.getText().toUpperCase();
			double stock = Double.parseDouble(txtStock.getText());
			String codigoProveedor = txtClaveProveedor.getText().toUpperCase();
			double pCosto = Double.parseDouble(txtPrecioLista.getText());
			double pVenta = Double.parseDouble(txtPrecioVenta.getText());
			String bon1 = txtBon1.getText();
			String bon2 = txtBon2.getText();
			String bon3 = txtBon3.getText();
			String bon4 = txtBon4.getText();
			double pDolar = Double.parseDouble(txtDolar.getText());
			double iva = Double.parseDouble(txtIva.getText());
			double dolar = Double.parseDouble(txtDolar.getText());
			double flete = Double.parseDouble(txtFlete.getText());
			double ganancia = Double.parseDouble(txtGanancia.getText());

			int cat = Integer.parseInt(txtCategoria.getText());
			int pro = Integer.parseInt(txtProveedor.getText());

			if (imgArticleFile == null) {

				producto.setIdProducto(idProducto);
				producto.setCodigoArticulo(codigoArticulo);
				producto.setNomProducto(nombre);
				producto.setStockProducto(stock);
				producto.setIdProveedorProducto(codigoProveedor);
				producto.setFotoProducto(null);
				producto.setPrecioCompraProducto(pCosto);
				producto.setPrecioVentaProducto(pVenta);
				producto.setBon1(bon1);
				producto.setBon2(bon2);
				producto.setBon3(bon3);
				producto.setBon4(bon4);
				producto.setDolar(dolar);
				producto.setExistenciasProducto(0);
				producto.setIdCategoria(cat);
				producto.setIdProveedor(pro);
				producto.setIva(iva);
				producto.setFlete(flete);
				producto.setGanancia(ganancia);

				bd.updateProducto(producto, false);
				System.out.println(producto.getBon1());
				System.out.println(producto.getBon2());
				System.out.println(producto.getBon3());
				System.out.println(producto.getBon4());
				cargarModeloTabla();

			} else {

				producto = new Producto();
				producto.setIdProducto(idProducto);
				producto.setCodigoArticulo(codigoArticulo);
				producto.setNomProducto(nombre);
				producto.setStockProducto(stock);
				producto.setIdProveedorProducto(codigoProveedor);
				producto.setFotoProducto(imgArticleFile);
				producto.setPrecioCompraProducto(pCosto);
				producto.setPrecioVentaProducto(pVenta);
				producto.setBon1(bon1);
				producto.setBon2(bon2);
				producto.setBon3(bon3);
				producto.setBon4(bon4);
				producto.setDolar(dolar);
				producto.setExistenciasProducto(0);
				producto.setIdCategoria(cat);
				producto.setIdProveedor(pro);
				producto.setIva(iva);
				producto.setFlete(flete);
				producto.setGanancia(ganancia);

				bd.updateProducto(producto, true);

				System.out.println(producto.getBon1());
				System.out.println(producto.getBon2());
				System.out.println(producto.getBon3());
				System.out.println(producto.getBon4());

				cargarModeloTabla();

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void modeloComboBoxProveedor() {

		modeloProveedor = new DefaultComboBoxModel(new BaseDatos().dameProveedores());

	}

	public void modeloComboBoxCategoria() {

		modeloCategoria = new DefaultComboBoxModel(new BaseDatos().dameCategorias());

	}

	private void desactivarComponentes() {
		txtIdProduct.setEnabled(false);
		txtIdProducto.setEnabled(false);
		txtNombre.setEnabled(false);
		txtStock.setEnabled(false);
		txtPrecioVenta.setEnabled(false);
		txtPrecioLista.setEnabled(false);
		txtClaveProveedor.setEnabled(false);
		txtIva.setEnabled(false);
		txtBon1.setEnabled(false);
		txtBon2.setEnabled(false);
		txtBon3.setEnabled(false);
		txtBon4.setEnabled(false);
		txtFlete.setEnabled(false);
		txtCosto.setEnabled(false);
		txtGanancia.setEnabled(false);
		txtDolar.setEnabled(false);

	}

	private void activarCompomponentes() {
		txtIdProduct.setEnabled(true);
		txtIdProducto.setEnabled(true);
		txtNombre.setEnabled(true);
		txtStock.setEnabled(false);
		txtPrecioVenta.setEnabled(true);
		txtPrecioLista.setEnabled(true);
		txtClaveProveedor.setEnabled(true);
		txtIva.setEnabled(true);
		txtBon1.setEnabled(true);
		txtBon2.setEnabled(true);
		txtBon3.setEnabled(true);
		txtBon4.setEnabled(true);
		txtFlete.setEnabled(true);
		txtGanancia.setEnabled(true);
		txtDolar.setEnabled(true);

	}

	public void centrarTabla() {
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		DefaultTableCellRenderer modeloLeft = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		modeloLeft.setHorizontalAlignment(SwingConstants.LEFT);
		tablaProductos.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		tablaProductos.getColumnModel().getColumn(1).setCellRenderer(modeloLeft);
		tablaProductos.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		tablaProductos.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		tablaProductos.getColumnModel().getColumn(4).setCellRenderer(modelocentrar);

	}

	private void txtCostoKeyPressed(java.awt.event.KeyEvent evt) {

		Focus.hacerFoco(evt, txtClaveProveedor, txtBon1);

	}

	private void txtIvaKeyPressed(java.awt.event.KeyEvent evt) {
		Focus.hacerFoco(evt, txtBon1, txtFlete);
	}

	private void txtidProductoKeyPressed(java.awt.event.KeyEvent evt) {
		Focus.hacerFoco(evt, txtIdProducto, txtClaveProveedor);
	}

	private void txtDolarKeyPressed(java.awt.event.KeyEvent evt) {
		Focus.hacerFoco(evt, txtIva, txtFlete);
	}

	private void txtFleteKeyPressed(java.awt.event.KeyEvent evt) {
		Focus.hacerFoco(evt, txtIva, txtGanancia);
	}

	private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {
		Focus.hacerFoco(evt, txtNombre, txtIdProducto);
	}

	private void campoStockKeyPressed(java.awt.event.KeyEvent evt) {
		Focus.hacerFoco(evt, txtGanancia, txtPrecioVenta);
	}

	private void txtClaveProveedorKeyPressed(KeyEvent e) {
		Focus.hacerFoco(e, txtIdProducto, txtPrecioLista);

	}
}
