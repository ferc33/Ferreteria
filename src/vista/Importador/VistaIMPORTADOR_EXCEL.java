package vista.Importador;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import modelo.BaseDatos;
import modelo.Conexion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.beans.Expression;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;

public class VistaIMPORTADOR_EXCEL extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTable tablaImportacion;
	private File archivo;
	private JFileChooser selecArchivo;
	private BaseDatos bd;
	private JPanel panel, buttonPane;
	public DefaultTableModel model = new DefaultTableModel();
	private Workbook book;
	private Conexion conexion = null;
	private Connection connection = null;
	private JButton btnInsertar, btnImportar;

	public VistaIMPORTADOR_EXCEL(JFrame parent, boolean modal) {
		super(parent, modal);
		conexion = new Conexion();
		bd = new BaseDatos();
		selecArchivo = new JFileChooser();

		setBounds(100, 100, 980, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane);
			{
				tablaImportacion = new JTable();
				scrollPane.setViewportView(tablaImportacion);
				tablaImportacion.setModel(model);
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			buttonPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			buttonPane.setAutoscrolls(true);
			buttonPane.setAlignmentY(Component.TOP_ALIGNMENT);
			buttonPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			}
			{
				JButton btnCerrar = new JButton("");
				btnCerrar.setFont(new Font("Bariol Regular", Font.BOLD, 12));
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setIcon(new ImageIcon(
						"/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/Knob-Loop-On-icon.png"));
				buttonPane.add(btnCerrar);
			}
			btnImportar = new JButton("");
			btnImportar.setFont(new Font("Bariol Light", Font.BOLD, 12));
			btnImportar.setIcon(
					new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/Knob-Search-icon.png"));
			btnImportar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					mostrarDatosActionPerformed(e);
				}
			});
			{
				JButton btnActualizar = new JButton("");
				btnActualizar.setFont(new Font("Bariol Light", Font.BOLD, 12));
				btnActualizar.setIcon(new ImageIcon(
						"/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/Knob-Valid-Green-icon.png"));
				btnActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						actualizarProductos();
					}

				});
				btnActualizar.setActionCommand("Cancel");
				buttonPane.add(btnActualizar);
			}
			{
				btnInsertar = new JButton("");
				btnInsertar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// insertarProveedor();
						// insertarCategoria();
						importarProductos();
					}
				});
				btnInsertar.setIcon(
						new ImageIcon("/home/ferc/eclipse-workspace/SistemaSeminario/src/iconos_1/import.png"));
				buttonPane.add(btnInsertar);
			}
			btnImportar.setActionCommand("OK");
			buttonPane.add(btnImportar);
			getRootPane().setDefaultButton(btnImportar);
		}
	}

	public void importarProductos() {

		try {

			int numFila = tablaImportacion.getRowCount();
			connection = conexion.getConexion();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO cat_productos (ID_PROD,NOM_PROD,PRECIO_COMPRA_PROD,PRECIO_VENTA_PROD,ID_PROVEEDOR,ID_CATEGORIA) VALUES (?,"
					+ "?,?,?,(SELECT ID_PROVEEDOR FROM CAT_PROVEEDORES WHERE NOM_PROVEEDOR=? limit 1 ),(SELECT ID_CATEGORIA FROM CAT_CATEGORIA WHERE NOM_CATEGORIA=? limit 1 ))");
			for (int i = 0; i < numFila; i++) {

				ps.setString(1, (String) model.getValueAt(i, 0));
				ps.setString(2, (String) model.getValueAt(i, 1));
				ps.setDouble(3, (Double) model.getValueAt(i, 2));
				ps.setDouble(4, (Double) model.getValueAt(i, 3));				
				ps.setString(5, (String) model.getValueAt(i, 4));
				ps.setString(6, (String) model.getValueAt(i, 5));
			
				System.out.println("FILA " + i);
				ps.execute();
			}


			

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertarProveedor() {

		try {

			int numFila = tablaImportacion.getRowCount();
			connection = conexion.getConexion();

			// PreparedStatement ps = conexion.prepareStatement("UPDATE CAT_PRODUCTOS SET
			// PRECIO=? WHERE CODIGO_ARTICULO=?");
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO `db-sistema`.CAT_PROVEEDORES(NOM_PROVEEDOR) VALUES(?)");
			for (int i = 0; i < numFila; i++) {

				ps.setString(1, (String) model.getValueAt(i, 0));

				System.out.println("FILA " + i);

				ps.execute();

			}
			JOptionPane.showMessageDialog(null, "Completado");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void insertarCategoria() {

		try {

			int numFila = tablaImportacion.getRowCount();
			connection = conexion.getConexion();
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO `db-sistema`.CAT_CATEGORIA(NOM_CATEGORIA) VALUES(?)");
			for (int i = 0; i < numFila; i++) {

				ps.setString(1, (String) model.getValueAt(i, 0));

				ps.execute();

			}
			JOptionPane.showMessageDialog(null, "Completado");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void getFile() {

		if (selecArchivo.showDialog(null, "Seleccionar archivo") == JFileChooser.APPROVE_OPTION) {

			archivo = selecArchivo.getSelectedFile();

			if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {

				JOptionPane.showMessageDialog(null, importar(archivo, tablaImportacion));
			} else {
				JOptionPane.showMessageDialog(null, "Elija un formato válido");
			}
		}

	}

	private void mostrarDatosActionPerformed(ActionEvent e) {
		if (selecArchivo.showDialog(null, "Seleccionar archivo") == JFileChooser.APPROVE_OPTION) {
			archivo = selecArchivo.getSelectedFile();
			if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {
				JOptionPane.showMessageDialog(null, importar(archivo, tablaImportacion));
			} else {
				JOptionPane.showMessageDialog(null, "Elija un formato válido");
			}
		}
	}

	private String importar(File archivo, JTable tabla) {

		String respuesta = "Error en la importacion";
		tabla.setModel(model);
		Workbook wb;

		try {

			wb = WorkbookFactory.create(new FileInputStream(archivo));
			Sheet hoja = wb.getSheetAt(0);
			Iterator filaIterator = hoja.rowIterator();
			int indiceFila = -1;

			while (filaIterator.hasNext()) {
				indiceFila++;
				Row fila = (Row) filaIterator.next();
				Iterator columnIterator = fila.cellIterator();

				Object[] listaColumna = new Object[15];
				int indiceColumna = -1;

				while (columnIterator.hasNext()) {
					indiceColumna++;

					Cell cell = (Cell) columnIterator.next();
					if (indiceFila == 0) {

						model.addColumn(cell.getStringCellValue());
					} else {

						if (cell != null) {

							switch (cell.getCellType()) {

							case Cell.CELL_TYPE_NUMERIC:
								listaColumna[indiceColumna] = (double) cell.getNumericCellValue();
								break;

							case Cell.CELL_TYPE_STRING:
								listaColumna[indiceColumna] = cell.getStringCellValue();
								break;

							case Cell.CELL_TYPE_FORMULA:
								listaColumna[indiceColumna] = cell.getCellFormula();
								break;

							}
						}
					}

				}
				if (indiceFila != 0)
					model.addRow(listaColumna);

			}
			respuesta = "Importación exitosa";

		} catch (Exception e) {

			e.printStackTrace();
		}

		return respuesta;
	}

	public void actualizarProductos() {

		try {

			int numFila = tablaImportacion.getRowCount();

			String sql = "UPDATE `db-sistema`.cat_productos SET PRECIO_COMPRA_PROD= ?"
					+ ",PRECIO_VENTA_PROD = PRECIO_COMPRA_PROD * (1 -(BON1/100)) * (1 -(BON2/100)) * (1 -(BON3/100)) "
					+ "* (1 -(BON4/100)) * (1 +(FLETE/100)) * (1 +(IVA/100)) * (1 +(GANANCIA/100))\n"
					+ " WHERE ID_PROVEEDOR_PROD=?";
			Connection connection = conexion.getConexion();
			PreparedStatement ps = connection.prepareStatement(sql);

			for (int i = 0; i < numFila; i++) {
				System.out.println(numFila);
				ps.setDouble(1, (double) model.getValueAt(i, 1));
				ps.setString(2, (String) model.getValueAt(i, 0));
				ps.execute();

			}
			JOptionPane.showMessageDialog(null, "Completado");

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
