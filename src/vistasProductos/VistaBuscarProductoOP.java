package vistasProductos;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.util.SystemOutLogger;

import modelo.BaseDatos;
import modelo.Conexion;
import modelo.Producto;
import modelo.Proveedor;
import singleton.Singleton;
import vista.Ordendepedido.VistaORDEN_PEDIDO;
import vista.Proveedores.VistaPROVEEDORES;

import java.awt.event.ActionListener;
import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaBuscarProductoOP extends VistaPrincipal {

	public VistaBuscarProductoOP(JFrame parent,boolean modal) {

		super(parent,modal);
		base = new BaseDatos();
		cargarColumnas();
		modeloTabla();

		getTabla().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {

					seleccionarProductoActionPerformed(e);

				}
			}
		});
		getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		getBtnFiltro().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VistaPROVEEDORES vistaP = Singleton.getVistaPROVEEDORES();
				vistaP.setLocationRelativeTo(null);

				try {

					vistaP.setVisible(true);
					Proveedor prov = vistaP.getSeleccion();
					getLblIdProveedor().setText(String.valueOf(prov.getIdProveedor()));
					getLblNombre().setText(prov.getNomProveedor());
					modeloTabla(prov.getIdProveedor());

				} catch (Exception f) {
					f.printStackTrace();
				}

			}
		});

	}

	public Producto getSeleccion() {

		Producto producto = (Producto) getModel().getValueAt(getTabla().getSelectedRow(), 1);
		return producto;

	}

	public void seleccionarProductoActionPerformed(MouseEvent e) {
		this.setVisible(false);
	}

	private void cargarColumnas() {

		getModel().addColumn("Codigo");
		getModel().addColumn("Descripcion");
		getModel().addColumn("C.Proveedor");

		TableColumn ColCodigo = getTabla().getColumn("Codigo");
		TableColumn ColNombre = getTabla().getColumn("Descripcion");
		TableColumn ColProve = getTabla().getColumn("C.Proveedor");

		ColCodigo.setMaxWidth(80);
		ColNombre.setMinWidth(300);

	}

	public void modeloTabla() {

		ArrayList<Producto> listaProducto = base.getProductos();
		int numeroProducto = listaProducto.size();
		getModel().setNumRows(numeroProducto);

		for (int i = 0; i < numeroProducto; i++) {
			Producto producto = new Producto();
			producto = listaProducto.get(i);

			int idProducto = producto.getIdProducto();
			String codigoArticulo = producto.getCodigoArticulo();
			String idFabricaProd = producto.getIdProveedorProducto();

			getModel().setValueAt(codigoArticulo, i, 0);
			getModel().setValueAt(producto, i, 1);
			getModel().setValueAt(idFabricaProd, i, 2);

		}

	}

	public void modeloTabla(int proveedor) {

		ArrayList<Producto> listaProducto = base.getProductos(proveedor);
		int numeroProducto = listaProducto.size();
		getModel().setNumRows(numeroProducto);

		for (int i = 0; i < numeroProducto; i++) {
			Producto producto = new Producto();
			producto = listaProducto.get(i);

			int idProducto = producto.getIdProducto();
			String codigoArticulo = producto.getCodigoArticulo();
			String idFabricaProd = producto.getIdProveedorProducto();

			getModel().setValueAt(codigoArticulo, i, 0);
			getModel().setValueAt(producto, i, 1);
			getModel().setValueAt(idFabricaProd, i, 2);

		}

	}

	private BaseDatos base;

}
