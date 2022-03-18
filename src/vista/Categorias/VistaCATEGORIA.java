package vista.Categorias;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.BaseDatos;
import modelo.Categoria;
import modelo.Proveedor;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VistaCATEGORIA extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modeloTabla = new DefaultTableModel() {
		@Override
		public final boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private BaseDatos base = new BaseDatos();
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDesc;
	private JTable tablaCategoria;
	private JLabel lblBuscar, lblLupa;
	public static String nombre;
	public static String id;
	private JButton btnGuardar, btnModificar, btnEliminar;
	private JPanel panel, panel_1, panel2;
	private JButton button;
	private JLabel lblCategoria;

	public VistaCATEGORIA(JFrame parent, boolean modal) {
		super(parent, modal);
		initComponent();
		encabezadoTabla();
		modelCategorias();
		eventosBOTONES();
	}

	// INICIAR COMPONENTES
	public void initComponent() {

		setBounds(100, 100, 701, 512);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setBounds(5, 12, 511, 59);
		contentPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblCategoria = new JLabel("Categorias");
		lblCategoria.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
		lblCategoria.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblCategoria);

		lblBuscar = new JLabel("");
		lblBuscar.setIcon(new ImageIcon(VistaCATEGORIA.class.getResource("/iconos_1/zoom_iwwn.png")));
		panel.add(lblBuscar);

		txtDesc = new JTextField();

		panel.add(txtDesc);
		txtDesc.setColumns(10);

		lblLupa = new JLabel("");
		lblLupa.setIcon(new ImageIcon("/home/ferc/git/SistemaFerreteria21/iconos_1/zoom_iwwn.png"));
		panel.add(lblLupa);

		panel2 = new JPanel();
		panel2.setBounds(5, 121, 511, 131);
		contentPanel.add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel2.add(scrollPane);

		tablaCategoria = new JTable();		
		scrollPane.setViewportView(tablaCategoria);
		tablaCategoria.setModel(modeloTabla);

		panel_1 = new JPanel();
		panel_1.setBounds(12, 277, 366, 49);
		contentPanel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setForeground(UIManager.getColor("Button.background"));
		panel_1.setBackground(UIManager.getColor("Button.background"));
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnGuardar = new JButton("");		
		btnGuardar.setForeground(UIManager.getColor("Button.background"));
		panel_1.add(btnGuardar);

		btnGuardar.setBackground(UIManager.getColor("Button.background"));
		btnGuardar.setIcon(new ImageIcon(VistaCATEGORIA.class.getResource("/iconos_1/Agregar.png")));
		btnGuardar.setActionCommand("OK");
		getRootPane().setDefaultButton(btnGuardar);

		btnModificar = new JButton("");
		panel_1.add(btnModificar);

		btnModificar.setBackground(UIManager.getColor("Button.background"));
		btnModificar.setIcon(new ImageIcon(VistaCATEGORIA.class.getResource("/iconos_1/Editar.png")));

		btnEliminar = new JButton("");
		panel_1.add(btnEliminar);

		btnEliminar.setBackground(UIManager.getColor("Button.background"));
		btnEliminar.setIcon(new ImageIcon(VistaCATEGORIA.class.getResource("/iconos_1/Eliminar.png")));

		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setIcon(new ImageIcon(VistaCATEGORIA.class.getResource("/iconos_1/ReHacer.png")));
		panel_1.add(button);

	}

	private void eventosBOTONES() {
		// EDITAR UNA CATEGORIA
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarCategoriaActionPerformed(e);

			}
		});

		tablaCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
					seleccionarCategoria(e);
				}
			}
		});
		
		txtDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buscarCategoria(e);
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoria = (String)JOptionPane.showInputDialog("AGREGAR CATEGORIA");
				base.insertCategoria(categoria);
				modelCategorias();
			}
		});
		
	}

	private void seleccionarCategoria(MouseEvent e) {
		this.setVisible(false);
	}

	public Categoria getSeleccion() {
		Categoria proveedor = (Categoria) modeloTabla.getValueAt(tablaCategoria.getSelectedRow(), 1);
		return proveedor;
	}

	private void encabezadoTabla() {
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Descripcion");

		TableColumn id = tablaCategoria.getColumn("ID");
		TableColumn des = tablaCategoria.getColumn("Descripcion");

		id.setMaxWidth(30);
		id.setMinWidth(30);

		des.setMinWidth(600);
		des.setMaxWidth(200);

	}

	private void actualizarCategoriaActionPerformed(ActionEvent e) {
		Categoria categoria = (Categoria) modeloTabla.getValueAt(tablaCategoria.getSelectedRow(), 1);
		VistaEDITAR_CATEGORIA vistaD = new VistaEDITAR_CATEGORIA(new javax.swing.JFrame(), true);
		vistaD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		vistaD.setVisible(true);
		vistaD.setLocationRelativeTo(new VistaCATEGORIA(new JFrame(), true));
		vistaD.lblId.setText(String.valueOf(categoria.getIdCategoria()));
		vistaD.txtDesc.setText(categoria.getNomCategoria());

	}

	private Categoria seleccionarCategoria() {

		Categoria categoria = (Categoria) modeloTabla.getValueAt(tablaCategoria.getSelectedRow(), 1);

		int id = categoria.getIdCategoria();
		String nom = categoria.getNomCategoria();
		Categoria cat = new Categoria(id, nom);

		return cat;

	}

	private void buscarCategoria(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtDescKeyReleased

		LimpiarLista();

		String cadena = txtDesc.getText();

		ArrayList<Categoria> listaCategoria = base.selectCategoriaCriterio(cadena);

		int numeroProducto = listaCategoria.size();
		modeloTabla.setNumRows(numeroProducto);
		for (int i = 0; i < numeroProducto; i++) {

			Categoria categoria = listaCategoria.get(i);
			int clave = categoria.getIdCategoria();
			String nomBre = categoria.getNomCategoria();

			modeloTabla.setValueAt(clave, i, 0);
			modeloTabla.setValueAt(categoria, i, 1);

		}
	}

	private void modelCategorias() {

		ArrayList<Categoria> listaCategoria = base.dameCategoriasTabla();
		int numeroProducto = listaCategoria.size();
		modeloTabla.setNumRows(numeroProducto);

		for (int i = 0; i < numeroProducto; i++) {

			Categoria categoria = listaCategoria.get(i);
			int id = categoria.getIdCategoria();
			String nombre = categoria.getNomCategoria();

			modeloTabla.setValueAt(id, i, 0);
			modeloTabla.setValueAt(categoria, i, 1);

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
