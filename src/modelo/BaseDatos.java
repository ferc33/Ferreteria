package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;

import net.sf.jasperreports.*;
import vista.Importador.VistaIMPORTADOR_EXCEL;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

public class BaseDatos {

	Connection connection = null;
	PreparedStatement prep = null;
	Statement st = null;
	ResultSet rs = null;
	Conexion conexion = null;
	
	public BaseDatos() {

		conexion = new Conexion();
		connection = conexion.getConexion();

	}

	// SELECT DE DOLAR
	public ArrayList<Dolar> obtenerValoresDolar() {

		ArrayList<Dolar> listaDolar = new ArrayList<Dolar>();

		try {

			prep = connection.prepareStatement("SELECT * FROM  `db-sistema`.dolar ");

			rs = prep.executeQuery();

			while (rs.next()) {

				int idDolar = rs.getInt("ID_DOLAR");
				double valor = rs.getDouble("VALOR");
				String fecha = rs.getString("FECHA");

				Dolar d = new Dolar(idDolar, valor, fecha);

				listaDolar.add(d);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listaDolar;

	}

	// UPDATE DE DOLAR
	public void actualizarDolar(Dolar d) {

		try {

			prep = connection.prepareStatement("UPDATE `db-sistema`.dolar SET VALOR = ?,FECHA = ? WHERE ID_DOLAR = ?");

			prep.setDouble(1, d.getValor());
			prep.setString(2, d.getFecha());
			prep.setInt(3, d.getIdDolar());

			prep.executeUpdate();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Precios Modificado");

		}
	}

//DOLAR INSERT
	public void insertDolar(Dolar d) {
		try {

			String SentenciaSql = "INSERT INTO `db-sistema`.dolar (VALOR,FECHA) VALUES(?,?)";

			prep = connection.prepareStatement(SentenciaSql);

			prep.setDouble(1, d.getValor());
			prep.setString(2, d.getFecha());

			prep.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//METODO PARA INSERTAR CATEGORIA
	public void insertCategoria(String categoria) {

		try {

			String SentenciaSql = "INSERT INTO `db-sistema`.cat_categoria (NOM_CATEGORIA) VALUES(?) ";

			prep = connection.prepareStatement(SentenciaSql);
			prep.setString(1, categoria);

			prep.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public void insertDetalleVenta(DetalleVenta detalle) {

		try {

			String sql = "INSERT INTO `db-sistema`.detalle_venta (ID_VENTA, ID_PROD, CANTIDAD_VENDIDA) VALUES (?,?,?)";

			prep = connection.prepareStatement(sql);
			prep.setLong(1, detalle.getIdVenta());
			prep.setInt(2, detalle.getIdProducto());
			prep.setString(3, detalle.getCantidadVendidad());

			prep.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
////////////////////////////////////ACTUALIZAR PROVEEDORES

	public void updateProveedor(Proveedor proveedor) {

		try {

			prep = connection.prepareStatement(
					"UPDATE `db-sistema`.cat_proveedores SET NOM_PROVEEDOR = ?, DIR_PROVEEDOR=?,TEL_PROVEEDOR=?,"
							+ "EMAIL_PROVEEDOR = ? WHERE ID_PROVEEDOR = ?");

			prep.setString(1, proveedor.getNomProveedor());
			prep.setString(2, proveedor.getDirProveedor());
			prep.setString(3, proveedor.getTelProveedor());
			prep.setString(4, proveedor.getMailProveedor());
			prep.setInt(5, proveedor.getIdProveedor());

			prep.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

/////////////////////////////////// ACTUALIZADOR PRODUCTO
	public void updateProducto(Producto producto, boolean cambiarFoto) {

		try {

			if (cambiarFoto == true) {

				File fileFoto = producto.getFotoProducto();

				FileInputStream fis = new FileInputStream(fileFoto);

				prep = connection.prepareStatement(
						"UPDATE `db-sistema`.cat_productos SET  ID_PROD = ?,NOM_PROD = ?, ID_PROVEEDOR_PROD = ?"
								+ ", STOCK_PROD= ?,FOTO_PROD= ?,PRECIO_COMPRA_PROD= ?, PRECIO_VENTA_PROD= ?,BON1=?,BON2=?,BON3=?,BON4=?"
								+ ",ID_PROVEEDOR=?,ID_CATEGORIA=?,IVA = ?"
								+ ",FLETE = ?,GANANCIA = ? WHERE ID_PRODUCTO= ?");
				prep.setString(1, producto.getCodigoArticulo());
				prep.setString(2, producto.getNomProducto());
				prep.setString(3, producto.getIdProveedorProducto());
				prep.setDouble(4, producto.getStockProducto());
				prep.setBlob(5, fis);
				prep.setDouble(6, producto.getPrecioCompraProducto());
				prep.setDouble(7, producto.getPrecioVentaProducto());
				prep.setString(8, producto.getBon1());
				prep.setString(9, producto.getBon2());
				prep.setString(10, producto.getBon3());
				prep.setString(11, producto.getBon4());
				prep.setInt(12, producto.getIdProveedor());
				prep.setInt(13, producto.getIdCategoria());
				prep.setDouble(14, producto.getIva());			
				prep.setDouble(15, producto.getFlete());
				prep.setDouble(16, producto.getGanancia());
				prep.setInt(17, producto.getIdProducto1());

				prep.execute();
				// CUANDO NO SE MODIFICA LA IMAGEN
			} else {

				//FileInputStream fis = new FileInputStream(imagen);

				prep = connection.prepareStatement(
						"UPDATE `db-sistema`.cat_productos SET  ID_PROD =?,NOM_PROD = ?, ID_PROVEEDOR_PROD = ?, STOCK_PROD= ?,PRECIO_COMPRA_PROD= ?, PRECIO_VENTA_PROD= ?,BON1=?,BON2=?,BON3=?,BON4=?,ID_PROVEEDOR=?,ID_CATEGORIA=?,IVA = ? ,DOLAR=?,FLETE = ?,GANANCIA = ? WHERE ID_PRODUCTO = ?");

				prep.setString(1, producto.getCodigoArticulo());
				prep.setString(2, producto.getNomProducto());
				prep.setString(3, producto.getIdProveedorProducto());
				prep.setDouble(4, producto.getStockProducto());
				// prep.setBlob(5, fis);
				prep.setDouble(5, producto.getPrecioCompraProducto());
				prep.setDouble(6, producto.getPrecioVentaProducto());
				prep.setString(7, producto.getBon1());
				prep.setString(8, producto.getBon2());
				prep.setString(9, producto.getBon3());
				prep.setString(10, producto.getBon4());
				prep.setInt(11, producto.getIdProveedor());
				prep.setInt(12, producto.getIdCategoria());
				prep.setDouble(13, producto.getIva());
				prep.setDouble(14, producto.getDolar());		
				prep.setDouble(15, producto.getFlete());
				prep.setDouble(16, producto.getGanancia());
				prep.setInt(17, producto.getIdProducto1());
				prep.execute();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

////////////////////////////////////////METODO PARA INSERTAR PRODUCTOS
	public void insertProducto(Producto producto) {

		try {

			File fileFoto = producto.getFotoProducto();

			if (fileFoto != null) {

				FileInputStream fis = new FileInputStream(fileFoto);

				String sql = "INSERT INTO `db-sistema`.cat_productos (ID_PROD, NOM_PROD, ID_PROVEEDOR_PROD, STOCK_PROD, FOTO_PROD,PRECIO_COMPRA_PROD, PRECIO_VENTA_PROD,BON1,BON2,BON3,BON4, EXISTENCIA_PROD,ID_PROVEEDOR,ID_CATEGORIA,IVA,FLETE,GANANCIA) "
						+ "VALUES(? ,? ,? ,? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

				prep = connection.prepareStatement(sql);
				prep.setString(1, producto.getCodigoArticulo());
				prep.setString(2, producto.getNomProducto());
				prep.setString(3, producto.getIdProveedorProducto());
				prep.setDouble(4, producto.getStockProducto());
				prep.setBlob(5, fis);
				prep.setDouble(6, producto.getPrecioCompraProducto());
				prep.setDouble(7, producto.getPrecioVentaProducto());
				prep.setString(8, producto.getBon1());
				prep.setString(9, producto.getBon2());
				prep.setString(10, producto.getBon3());
				prep.setString(11, producto.getBon4());
				prep.setDouble(12, producto.getExistenciasProducto());
				prep.setInt(13, producto.getIdProveedor());
				prep.setInt(14, producto.getIdCategoria());
				prep.setDouble(15, producto.getIva());
				prep.setDouble(16, producto.getFlete());
				prep.setDouble(17, producto.getGanancia());

				prep.execute();
				JOptionPane.showMessageDialog(null, "Inserto un articulo");
			} else {

				String sql = "INSERT INTO `db-sistema`.cat_productos (ID_PROD, NOM_PROD, ID_PROVEEDOR_PROD, STOCK_PROD, FOTO_PROD,PRECIO_COMPRA_PROD, PRECIO_VENTA_PROD,BON1,BON2,BON3,BON4, EXISTENCIA_PROD,ID_PROVEEDOR,ID_CATEGORIA,IVA,FLETE,GANANCIA) "
						+ "VALUES(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";

				prep = connection.prepareStatement(sql);
				prep.setString(1, producto.getCodigoArticulo());
				prep.setString(2, producto.getNomProducto());
				prep.setString(3, producto.getIdProveedorProducto());
				prep.setDouble(4, producto.getStockProducto());
				prep.setString(5, null);
				prep.setDouble(6, producto.getPrecioCompraProducto());
				prep.setDouble(7, producto.getPrecioVentaProducto());
				prep.setString(8, producto.getBon1());
				prep.setString(9, producto.getBon2());
				prep.setString(10, producto.getBon3());
				prep.setString(11, producto.getBon4());
				prep.setDouble(12, producto.getExistenciasProducto());
				prep.setInt(13, producto.getIdProveedor());
				prep.setInt(14, producto.getIdCategoria());
				prep.setDouble(15, producto.getIva());				
				prep.setDouble(16, producto.getFlete());
				prep.setDouble(17, producto.getGanancia());

				prep.execute();
			}

		} catch (SQLException | FileNotFoundException ex) {

			ex.printStackTrace();
		}

	}

	public ArrayList<Producto> getProductos(int proveedor) {

		ArrayList<Producto> listaProductosBD = new ArrayList<Producto>();

		try {

			prep = connection.prepareStatement("SELECT * FROM `db-sistema`.cat_productos WHERE ID_PROVEEDOR   =" +proveedor);

			rs = prep.executeQuery();

			while (rs.next()) {

				int idProducto = rs.getInt("ID_PRODUCTO");
				String codigoArticulo = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idProveedorProd = rs.getString("ID_PROVEEDOR_PROD");
				double stock = rs.getDouble("STOCK_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				String bon1 =rs.getString("BON1");
				String bon2 =rs.getString("BON2");
				String bon3 =rs.getString("BON3");
				String bon4=rs.getString("BON4");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int idCategoria = rs.getInt("ID_CATEGORIA");
				int idProveedor = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getDouble("IVA");
				double dolar = rs.getDouble("DOLAR");
			   double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");

				Producto producto = new Producto();
			
				producto.setIdProducto(idProducto);
				producto.setCodigoArticulo(codigoArticulo);
				producto.setNomProducto(nomprod);
				producto.setIdProveedorProducto(idProveedorProd);
				producto.setStockProducto(stock);
				producto.setPrecioCompraProducto(precioCompra);
				producto.setPrecioVentaProducto(precioVenta);
				producto.setBon1(bon1);
				producto.setBon2(bon2);
				producto.setBon3(bon3);
				producto.setBon4(bon4);
				producto.setExistenciasProducto(existencia);
				producto.setIdCategoria(idCategoria);
				producto.setIdProveedor(idProveedor);
				producto.setIva(iva);
				producto.setDolar(dolar);
				producto.setFlete(flete);
				producto.setGanancia(ganancia);

				listaProductosBD.add(producto);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return listaProductosBD;

	}
	
	public ArrayList<Producto> getProducto(int id) {

		ArrayList<Producto> listaProductosBD = new ArrayList<Producto>();

		try {

			prep = connection.prepareStatement("SELECT * FROM `db-sistema`.cat_productos WHERE ID_PRODUCTO ="+id+" ORDER BY NOM_PROD");

			rs = prep.executeQuery();

			while (rs.next()) {

				int idProducto = rs.getInt("ID_PRODUCTO");
				String codigoArticulo = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idProveedorProd = rs.getString("ID_PROVEEDOR_PROD");
				double stock = rs.getDouble("STOCK_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				String bon1 =rs.getString("BON1");
				String bon2 =rs.getString("BON2");
				String bon3 =rs.getString("BON3");
				String bon4 =rs.getString("BON4");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int idCategoria = rs.getInt("ID_CATEGORIA");
				int idProveedor = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getDouble("IVA");
				double dolar = rs.getDouble("DOLAR");
			   double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");

				Producto producto = new Producto();
			
				producto.setIdProducto(idProducto);
				producto.setCodigoArticulo(codigoArticulo);
				producto.setNomProducto(nomprod);
				producto.setIdProveedorProducto(idProveedorProd);
				producto.setStockProducto(stock);
				producto.setPrecioCompraProducto(precioCompra);
				producto.setPrecioVentaProducto(precioVenta);
				producto.setBon1(bon1);
				producto.setBon2(bon2);
				producto.setBon3(bon3);
				producto.setBon4(bon4);
				producto.setExistenciasProducto(existencia);
				producto.setIdCategoria(idCategoria);
				producto.setIdProveedor(idProveedor);
				producto.setIva(iva);
				producto.setDolar(dolar);
				producto.setFlete(flete);
				producto.setGanancia(ganancia);

				listaProductosBD.add(producto);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return listaProductosBD;

	}
	
	
	public ArrayList<Producto> getProductos() {

		ArrayList<Producto> listaProductosBD = new ArrayList<Producto>();

		try {

			prep = connection.prepareStatement("SELECT * FROM `db-sistema`.cat_productos ORDER BY NOM_PROD");

			rs = prep.executeQuery();

			while (rs.next()) {

				int idProducto = rs.getInt("ID_PRODUCTO");
				String codigoArticulo = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idProveedorProd = rs.getString("ID_PROVEEDOR_PROD");
				double stock = rs.getDouble("STOCK_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				String bon1 =rs.getString("BON1");
				String bon2 =rs.getString("BON2");
				String bon3 =rs.getString("BON3");
				String bon4 =rs.getString("BON4");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int idCategoria = rs.getInt("ID_CATEGORIA");
				int idProveedor = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getDouble("IVA");
				double dolar = rs.getDouble("DOLAR");
			   double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");

				Producto producto = new Producto();
			
				producto.setIdProducto(idProducto);
				producto.setCodigoArticulo(codigoArticulo);
				producto.setNomProducto(nomprod);
				producto.setIdProveedorProducto(idProveedorProd);
				producto.setStockProducto(stock);
				producto.setPrecioCompraProducto(precioCompra);
				producto.setPrecioVentaProducto(precioVenta);
				producto.setBon1(bon1);
				producto.setBon2(bon2);
				producto.setBon3(bon3);
				producto.setBon4(bon4);
				producto.setExistenciasProducto(existencia);
				producto.setIdCategoria(idCategoria);
				producto.setIdProveedor(idProveedor);
				producto.setIva(iva);
				producto.setDolar(dolar);
				producto.setFlete(flete);
				producto.setGanancia(ganancia);

				listaProductosBD.add(producto);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return listaProductosBD;

	}

	public ArrayList<Presupuesto> getPresupuestoPorID(int numPresupuesto) {

		ArrayList<Presupuesto> listarPresupuesto = new ArrayList<Presupuesto>();

		try {
			prep = connection.prepareStatement(
					"SELECT p.ID_PRODUCTO,  p.POSICION, p.CANTIDAD_PRODUCTOS AS cantidad ,c.NOM_PROD as descripcion ,c.PRECIO_VENTA_PROD as precio ,c.PRECIO_VENTA_PROD  * p.CANTIDAD_PRODUCTOS as total ,p.ID_PRESUPUESTO as IDPRESUPUESTO, p.FECHA as fecha ,x.ID_CLIENTE as cliente FROM presupuesto p INNER JOIN cat_productos c ON c.ID_PRODUCTO = p.ID_PRODUCTO INNER JOIN  clientes x ON p.ID_CLIENTE = x.ID_CLIENTE WHERE p.POSICION="+numPresupuesto);

			rs = prep.executeQuery();

			while (rs.next()) {

				int idProducto = rs.getInt(1);
				int posicion = rs.getInt(2);
				int cant = rs.getInt(3);
				String descripcion = rs.getString(4);
				double precioVenta = rs.getDouble(5);
				double total = rs.getDouble(6);
				int idPresupuesto =rs.getInt(7);
				String fecha = rs.getString(8);
				int cliente = rs.getInt(9);
				
				Presupuesto p = new Presupuesto();
				
				p.setId_prod(idProducto);
				p.setPosicion(posicion);
				p.setCant_productos(cant);
				p.setDescripcion(descripcion);
				p.setPrecio_unitario(precioVenta);
				p.setImpTotal(total);
				p.setId_presupuesto(idPresupuesto);
				p.setFecha(fecha);
				p.setId_cliente(cliente);
				listarPresupuesto.add(p);
				
				
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listarPresupuesto;
	}
	
	
	

	// SELECT PRESUPUESTO
	public ArrayList<Presupuesto> damePresupuesto() {

		ArrayList<Presupuesto> listarPresupuesto = new ArrayList<Presupuesto>();
		try {
			prep = connection.prepareStatement("SELECT * FROM 	presupuesto");

			rs = prep.executeQuery();

			while (rs.next()) {
				// ( clave, nombre, descripcion, stock, codigoProveedor
				int id = rs.getInt("ID_PRESUPUESTO");
				int posicion = rs.getInt("POSICION");
				String fecha = rs.getString("FECHA");
				int cant_prod = rs.getInt("CANTIDAD_PRODUCTOS");
				int idC = rs.getInt("ID_CLIENTE");
				int idP = rs.getInt("ID_PRODUCTO");
				double desc = rs.getDouble("DESCUENTO");
				double impDesc = rs.getDouble("IMP_DESC");
				// nt numPresu, String fecha, int cant_prod, int idC, int idP, double desc
				Presupuesto presu = new Presupuesto(id, posicion, fecha, cant_prod, idC, idP, desc, impDesc);

				listarPresupuesto.add(presu);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return listarPresupuesto;

	}

//ULTIMA POSCICION DE LA TABLA
	public int damePosicion(String nombreTabla) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		int id = 0;
		String presupuesto = "SELECT MAX(POSICION) AS POSICION FROM " + nombreTabla;
		String pedido = "SELECT MAX(NUM_PEDIDO) AS POSICION FROM " + nombreTabla;
		String producto = "SELECT MAX(ID_PRODUCTO)AS PRODUCTO FROM " + nombreTabla;
		if (nombreTabla.equals("presupuesto")) {
			try {
				ps = connection.prepareStatement(presupuesto);
				rs = ps.executeQuery();

				while (rs.next()) {
					id = rs.getInt(1);// COINCIDE CON LA COLUMNA DEL PEDIDO Y DEL PRESUPUESTO
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (nombreTabla.equals("pedido")) {
			try {

				ps = connection.prepareStatement(pedido);
				rs = ps.executeQuery();

				while (rs.next()) {
					id = rs.getInt(1);// COINCIDE CON LA COLUMNA DEL PEDIDO Y DEL PRESUPUESTO
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {

				ps = connection.prepareStatement(producto);
				rs = ps.executeQuery();

				while (rs.next()) {
					id = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return id;

	}

	public void insertPresupuesto(Presupuesto presu) {

		try {

			prep = connection.prepareStatement(
					"INSERT INTO `db-sistema`.presupuesto ( POSICION,FECHA, CANTIDAD_PRODUCTOS,ID_CLIENTE,ID_PRODUCTO,DESCUENTO,TOTAL,IMP_DESC)"
							+ " VALUES (?,?,?,?,?,?,?,?)");
			prep.setInt(1, presu.getPosicion());
			prep.setString(2, presu.getFecha());
			prep.setInt(3, presu.getCant_productos());
			prep.setInt(4, presu.getId_cliente());
			prep.setInt(5, presu.getId_prod());
			prep.setDouble(6, presu.getDescuento());
			prep.setDouble(7, presu.getTotal());
			prep.setDouble(8, presu.getImpTotal());
			prep.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	public ArrayList<Clientes> getClientes() {

		ArrayList<Clientes> listarCliente = new ArrayList<Clientes>();

		try {
			prep = connection.prepareStatement(" SELECT * FROM `db-sistema`.clientes");
			rs = prep.executeQuery();

			while (rs.next()) {
				// ( clave, nombre, descripcion, stock, codigoProveedor
				int idCliente = rs.getInt("ID_CLIENTE");
				String nombre = rs.getString("NOMBRE");
				String direccion = rs.getString("DIRECCION");
				String mail = rs.getString("MAIL");
				String telefono = rs.getString("TELEFONO");
				String cuit = rs.getString("CUIT");

				Clientes cliente = new Clientes();
				cliente.setIdCliente(idCliente);
				cliente.setNombre(nombre);
				cliente.setDireccion(direccion);
				cliente.setMail(mail);
				cliente.setTel(telefono);
				cliente.setCuit(cuit);

				listarCliente.add(cliente);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return listarCliente;

	}

	public void insertCliente(Clientes c) {

		try {

			prep = connection.prepareStatement(
					"INSERT INTO `db-sistema`.clientes (NOMBRE,DIRECCION,MAIL,TELEFONO) VALUES (?,?,?,?)");

			prep.setString(1, c.getNombre().toUpperCase());
			prep.setString(2, c.getDireccion());
			prep.setString(3, c.getMail());
			prep.setString(4, c.getDireccion());
			prep.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	public void insertarProveedor(Proveedor prov) {

		try {

			prep = connection
					.prepareStatement("INSERT INTO `db-sistema`.cat_proveedores (NOM_PROVEEDOR, DIR_PROVEEDOR, "
							+ "EMAIL_PROVEEDOR, TEL_PROVEEDOR) VALUES (?,?,?,?)");
			// prep.setInt(1, prov.getIdProveedor());
			prep.setString(1, prov.getNomProveedor().toUpperCase());
			prep.setString(2, prov.getDirProveedor().toUpperCase());
			prep.setString(3, prov.getMailProveedor().toUpperCase());
			prep.setString(4, prov.getTelProveedor());

			prep.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

////////////////////////////////METODO PARA INSERTAR LA VENTA
	public Long insertarVenta(Ventas venta) {
		Long LastVal = 0l;
		try {
			prep = connection.prepareStatement("INSERT INTO `db-sistema`.venta (MONTO_VENTA, FECHA_VENTA)VALUES (?,?)");
			prep.setDouble(1, venta.getMontoVenta());
			prep.setDate(2, (Date) venta.getFechaVenta());
			prep.executeUpdate();
			prep.close();
			prep = this.connection.prepareStatement("SELECT last_insert_id()");
			rs = prep.executeQuery();

			while (rs.next()) {
				LastVal = rs.getLong("last_insert_id()");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return LastVal;

	}

//METODO QUE SELECCIONA LOS PROVEEDORES DE LA BASE DE DATOS.
	public Vector<Proveedor> dameProveedores() {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Conexion conn = new Conexion();

		Vector<Proveedor> proveedor = new Vector<Proveedor>();

		Proveedor prov = null;

		try {

			String sql = "SELECT * FROM `db-sistema`.cat_proveedores";

			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			prov = new Proveedor();
			prov.setIdProveedor(0);
			prov.setNomProveedor("Selecciona Proveedor");

			proveedor.add(prov);
			while (rs.next()) {
				prov = new Proveedor();
				prov.setIdProveedor(rs.getInt("ID_PROVEEDOR"));
				prov.setNomProveedor(rs.getString("NOM_PROVEEDOR"));
				prov.setDirProveedor(rs.getString("DIR_PROVEEDOR"));
				prov.setTelProveedor(rs.getString("TEL_PROVEEDOR"));
				prov.setMailProveedor(rs.getString("EMAIL_PROVEEDOR"));
				proveedor.add(prov);
			}

			rs.close();

		} catch (Exception e) {
			System.err.print(e.toString());
		}
		// devuelve el vector

		return proveedor;

	}

	public String dameProveedor(Integer idProveedor) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();

		Proveedor prov = null;

		try {

			String sql = "SELECT * FROM `db-sistema`.cat_proveedores WHERE ID_PROVEEDOR =" + idProveedor;

			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				prov = new Proveedor();
				prov.setIdProveedor(rs.getInt("ID_PROVEEDOR"));
				prov.setNomProveedor(rs.getString("NOM_PROVEEDOR"));

				proveedor.add(prov);
			}

			rs.close();

		} catch (Exception e) {
			System.err.print(e.toString());
		}
		// devuelve el vector

		return prov.getNomProveedor();

	}

	public ArrayList<Proveedor> dameProveedoresTabla() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();

		Proveedor prov = null;

		try {

			String sql = "SELECT * FROM `db-sistema`.cat_proveedores";

			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				prov = new Proveedor();
				prov.setIdProveedor(rs.getInt("ID_PROVEEDOR"));
				prov.setNomProveedor(rs.getString("NOM_PROVEEDOR"));
				prov.setDirProveedor(rs.getString("DIR_PROVEEDOR"));
				prov.setTelProveedor(rs.getString("TEL_PROVEEDOR"));
				prov.setMailProveedor(rs.getString("EMAIL_PROVEEDOR"));
				proveedor.add(prov);
			}

			rs.close();

		} catch (Exception e) {
			System.err.print(e.toString());
		}
		// devuelve el vector

		return proveedor;

	}

//METODO QUE SELECCIONA LOS PROVEEDORES DE LA BASE DE DATOS.
	public Vector<Categoria> dameCategorias(Integer IdCategoria) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Vector<Categoria> categoria = new Vector<Categoria>();

		Categoria cat = null;

		try {

			String sql = "SELECT * FROM cat_categoria WHERE ID_CATEGORIA =" + IdCategoria;
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			// Añade primer item al comboboxCategoria
			cat = new Categoria();
			cat.setIdCategoria(0);
			cat.setNomCategoria("Selecciona Categoria");
			categoria.add(cat);
			// llena el combobox de categorias
			while (rs.next()) {
				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("ID_CATEGORIA"));
				cat.setNomCategoria(rs.getString("NOM_CATEGORIA"));
				categoria.add(cat);
			}

			rs.close();

		} catch (Exception e) {
			// System.err.print(e.toString());
		}

		return categoria;

	}

	public String dameCategoria(Integer idCategoria) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Categoria cat = null;
		ArrayList<Categoria> categoria = new ArrayList<Categoria>();



		try {

			String sql = "SELECT * FROM cat_categoria WHERE ID_CATEGORIA =" + idCategoria;
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("ID_CATEGORIA"));
				cat.setNomCategoria(rs.getString("NOM_CATEGORIA"));
				categoria.add(cat);
			}

			rs.close();

		} catch (Exception e) {
			System.err.print(e.toString());
		}

		return cat.getNomCategoria();

	}

	// LLENA EL COMBOBOX
	public Vector<Categoria> dameCategorias() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		Vector<Categoria> categoria = new Vector<Categoria>();

		Categoria cat = null;

		try {

			String sql = "SELECT * FROM cat_categoria";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			// Añade primer item al comboboxCategoria
			cat = new Categoria();
			cat.setIdCategoria(0);
			cat.setNomCategoria("Selecciona Categoria");
			categoria.add(cat);
			// llena el combobox de categorias
			while (rs.next()) {

				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("ID_CATEGORIA"));
				cat.setNomCategoria(rs.getString("NOM_CATEGORIA"));
				categoria.add(cat);
			}

			rs.close();

		} catch (Exception e) {

		}

		return categoria;

	}

	public ArrayList<Categoria> dameCategoriasTabla() {

		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Categoria> categoria = new ArrayList<Categoria>();
		Categoria cat = null;
		try {
			String sql = "SELECT * FROM cat_categoria";
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			// llena el combobox de categorias
			while (rs.next()) {
				cat = new Categoria();
				cat.setIdCategoria(rs.getInt("ID_CATEGORIA"));
				cat.setNomCategoria(rs.getString("NOM_CATEGORIA"));
				categoria.add(cat);
			}

			rs.close();

		} catch (Exception e) {

		}

		return categoria;

	}

	public ArrayList<Proveedor> obtenerProveedoresPorCriterio(String criterio) {

		ArrayList<Proveedor> listaProveedor = new ArrayList<Proveedor>();
		Proveedor proveedor;
		try {

			String sql = "SELECT * FROM `db-sistema`.cat_proveedores WHERE NOM_PROVEEDOR  LIKE  '" + criterio
					+ "%' ORDER BY NOM_PROVEEDOR";
			st = connection.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("ID_PROVEEDOR");
				String nomP = rs.getString("NOM_PROVEEDOR");
				String dir = rs.getString("DIR_PROVEEDOR");
				String tel = rs.getString("TEL_PROVEEDOR");
				String email = rs.getString("EMAIL_PROVEEDOR");

				proveedor = new Proveedor(id, nomP, dir, email, tel);
				listaProveedor.add(proveedor);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return listaProveedor;

	}

	public ArrayList<Categoria> selectCategoriaCriterio(String criterio) {

		ArrayList<Categoria> ListaCategoria = new ArrayList<Categoria>();
		Categoria categoria;
		
		try {

			String sql = "SELECT * FROM `db-sistema`.cat_categoria WHERE NOM_CATEGORIA  LIKE  '" + criterio
					+ "%' ORDER BY NOM_CATEGORIA";
			st = connection.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("ID_CATEGORIA");
				String nomP = rs.getString("NOM_CATEGORIA");

				categoria = new Categoria(id, nomP);
				ListaCategoria.add(categoria);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return ListaCategoria;

	}
	
	public ArrayList<Clientes> getClientes(String criterio) {

		ArrayList<Clientes> listaCliente = new ArrayList<Clientes>();

		
		try {
			
			String sql = "SELECT * FROM `db-sistema`.clientes WHERE NOMBRE  LIKE  '" + criterio+ "%' ";
			st = connection.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				int idCliente = rs.getInt("ID_CLIENTE");
				String nombre = rs.getString("NOMBRE");
				String direccion = rs.getString("DIRECCION");
				String mail = rs.getString("MAIL");
				String telefono = rs.getString("TELEFONO");
				String cuit = rs.getString("CUIT");		
					
				Clientes cliente = new Clientes();
				cliente.setIdCliente(idCliente);
				cliente.setNombre(nombre);
				cliente.setDireccion(direccion);
				cliente.setMail(mail);
				cliente.setTel(telefono);
				cliente.setCuit(cuit);
				
				listaCliente.add(cliente);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return listaCliente;

	}
	

//DEVUELVE LOS PROVEEDORES QUE SELECCIONO DESDE EL COMBOBOX
	public ArrayList<Proveedor> selectProveedorCriterioCodigo(String criterio) {

		ArrayList<Proveedor> listaProductos = new ArrayList<Proveedor>();
		try {

			String sql = "SELECT ID_PROD,NOM_PROD,ID_PROVEEDOR_PROD , PRECIO_VENTA_PROD , STOCK_PROD FROM `db-sistema`.cat_productos WHERE ID_PROVEEDOR ="
					+ criterio;
			st = connection.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {

				String idprod = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				double stock = rs.getDouble("STOCK_PROD");

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return listaProductos;

	}
	

	public ArrayList<Producto> selectProductoCriterioID(String criterio) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {

			String sql = "SELECT * FROM `db-sistema`.cat_productos WHERE ID_PROD =" + criterio;
			st = connection.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {

				int idProducto = rs.getInt("ID_PRODUCTO");
				String codigoArticulo = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				int stock = rs.getInt("STOCK_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				String bon1 = rs.getString("BON1");
				String bon2 = rs.getString("BON2");
				String bon3 = rs.getString("BON3");
				String bon4= rs.getString("BON4");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int categoria = rs.getInt("ID_CATEGORIA");
				int idProvee = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getInt("IVA");
				double dolar = rs.getDouble("DOLAR");		
				double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");

				Producto producto = new Producto();
				producto.setCodigoArticulo(codigoArticulo);
				producto.setIdProducto(idProducto);
				producto.setNomProducto(nomprod);
				producto.setIdProveedorProducto(idCodigoProve);
				producto.setStockProducto(stock);
				producto.setPrecioCompraProducto(precioCompra);
				producto.setPrecioVentaProducto(precioVenta);
				producto.setBon1(bon1);
				producto.setBon2(bon2);
				producto.setBon3(bon3);
				producto.setBon4(bon4);
				producto.setExistenciasProducto(existencia);
				producto.setIdCategoria(categoria);
				producto.setIdProveedor(idProvee);
				producto.setIva(iva);
				producto.setDolar(dolar);
				producto.setFlete(flete);
				producto.setGanancia(ganancia);
				listaProductos.add(producto);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return listaProductos;

	}

/// ////////////////////////////// METODO PARA REALIZAR BUSQUEDA POR CRITERIO NOMBRES
	public ArrayList<Producto> selectProductosCriterioDesc(String criterio) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {

			String sql = "SELECT * FROM `db-sistema`.cat_productos WHERE NOM_PROD LIKE '" + criterio
					+ "%' ORDER BY NOM_PROD";
			st = connection.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {

				int idProducto = rs.getInt("ID_PRODUCTO");
				String codigoArticulo = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				int stock = rs.getInt("STOCK_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				String bon1 = rs.getString("BON1");
				String bon2 = rs.getString("BON2");
				String bon3 = rs.getString("BON3");
				String bon4 = rs.getString("BON4");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int categoria = rs.getInt("ID_CATEGORIA");
				int idProvee = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getInt("IVA");
				double dolar = rs.getDouble("DOLAR");
				double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");

				Producto producto = new Producto();
				producto.setCodigoArticulo(codigoArticulo);
				producto.setIdProducto(idProducto);
				producto.setNomProducto(nomprod);
				producto.setIdProveedorProducto(idCodigoProve);
				producto.setStockProducto(stock);
				producto.setPrecioCompraProducto(precioCompra);
				producto.setPrecioVentaProducto(precioVenta);
				producto.setBon1(bon1);
				producto.setBon2(bon2);
				producto.setBon3(bon3);
				producto.setBon4(bon4);
				producto.setExistenciasProducto(existencia);
				producto.setIdCategoria(categoria);
				producto.setIdProveedor(idProvee);
				producto.setIva(iva);
				producto.setDolar(dolar);
				producto.setFlete(flete);
				producto.setGanancia(ganancia);
				listaProductos.add(producto);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return listaProductos;

	}

//--------------------------------METODO PARA BUSCAR POR CODIGO-----------------------------------------
	public ArrayList<Producto> obtenerProductosPorCodigo(String criterio) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {

			String sql = "SELECT * FROM `db-sistema`.cat_productos WHERE ID_PROD  LIKE '%" + criterio
					+ "%' ORDER BY NOM_PROD";

			st = connection.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				int idProducto = rs.getInt("ID_PRODUCTO");
				String codigoArticulo = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				int stock = rs.getInt("STOCK_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				String bon1 = rs.getString("BON1");
				String bon2 = rs.getString("BON2");
				String bon3 = rs.getString("BON3");
				String bon4 = rs.getString("BON4");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int categoria = rs.getInt("ID_CATEGORIA");
				int idProvee = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getDouble("IVA");
				double dolar = rs.getDouble("DOLAR");
				double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");
				// String codigoArticulo = rs.getString("CODIGO_ARTICULO");

				Producto producto = new Producto();
				producto.setCodigoArticulo(codigoArticulo);
				producto.setIdProducto(idProducto);
				producto.setNomProducto(nomprod);
				producto.setIdProveedorProducto(idCodigoProve);
				producto.setStockProducto(stock);			
				producto.setPrecioCompraProducto(precioCompra);
				producto.setPrecioVentaProducto(precioVenta);
				producto.setBon1(bon1);
				producto.setBon2(bon2);
				producto.setBon3(bon3);
				producto.setBon4(bon4);
				producto.setExistenciasProducto(existencia);
				producto.setDolar(dolar);
				producto.setIdCategoria(categoria);
				producto.setIdProveedor(idProvee);
				producto.setIva(iva);
				producto.setFlete(flete);
				producto.setGanancia(ganancia);
				listaProductos.add(producto);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return listaProductos;

	}

//--------------------------------METODO PARA BUSCAR POR CODIGO PROVEEDOR-----------------------------------------
	public ArrayList<Producto> obtenerProductosPorCodigoProveedor(String criterio) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {

			String sql = "SELECT * FROM `db-sistema`.cat_productos WHERE ID_PROVEEDOR_PROD LIKE '%" + criterio
					+ "%' ORDER BY NOM_PROD";
			st = connection.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				int idProducto = rs.getInt("ID_PRODUCTO");
				String codigoArticulo = rs.getString("ID_PROD");
				String nomprod = rs.getString("NOM_PROD");
				String idCodigoProve = rs.getString("ID_PROVEEDOR_PROD");
				int stock = rs.getInt("STOCK_PROD");
				double precioCompra = rs.getDouble("PRECIO_COMPRA_PROD");
				double precioVenta = rs.getDouble("PRECIO_VENTA_PROD");
				String bon1 = rs.getString("BON1");
				String bon2 = rs.getString("BON2");
				String bon3 = rs.getString("BON3");
				String bon4 = rs.getString("BON4");
				int existencia = rs.getInt("EXISTENCIA_PROD");
				int categoria = rs.getInt("ID_CATEGORIA");
				int idProvee = rs.getInt("ID_PROVEEDOR");
				double iva = rs.getDouble("IVA");
				double dolar = rs.getDouble("DOLAR");
				double flete = rs.getDouble("FLETE");
				double ganancia = rs.getDouble("GANANCIA");

				Producto producto = new Producto();
				
				producto.setCodigoArticulo(codigoArticulo);
				producto.setIdProducto(idProducto);
				producto.setNomProducto(nomprod);
				producto.setIdProveedor(idProvee);
				producto.setStockProducto(stock);			
				producto.setPrecioCompraProducto(precioCompra);
				producto.setPrecioVentaProducto(precioVenta);
				producto.setBon1(bon1);
				producto.setBon2(bon2);
				producto.setBon3(bon3);
				producto.setBon4(bon4);
				producto.setExistenciasProducto(existencia);
				producto.setIdCategoria(categoria);
				producto.setIdProveedor(idProvee);
				producto.setIva(iva);
				producto.setFlete(flete);
				producto.setGanancia(ganancia);
				
				listaProductos.add(producto);

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return listaProductos;

	}

//////////////////////////////////ACTUALIZANDO EL INVENTARIO
	public void actualizarInventario(Producto producto) {

		try {

			prep = connection.prepareStatement("UPDATE `db-sistema`.cat_productos SET STOCK_PROD = ? WHERE ID_PROD=?");

			prep.setDouble(1, producto.getStockProducto());

			prep.setString(2, producto.getCodigoArticulo());

			prep.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}

	}

	public void actualizarCategoria(Categoria categoria) {

		try {

			prep = connection
					.prepareStatement("UPDATE `db-sistema`.cat_categoria SET NOM_CATEGORIA = ? WHERE ID_CATEGORIA=?");

			prep.setString(1, categoria.getNomCategoria());
			prep.setInt(2, categoria.getIdCategoria());

			prep.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void borrarArticulo(int producto) {

		try {

			prep = connection.prepareStatement("DELETE FROM `db-sistema`.cat_productos WHERE ID_PRODUCTO=?");
			prep.setInt(1, producto);
			prep.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	public void eliminarCategoria(Categoria categoria) {

		try {

			prep = connection.prepareStatement("DELETE FROM `db-sistema`.cat_categoria WHERE ID_CATEGORIA=?");
			prep.setInt(1, categoria.getIdCategoria());
			prep.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	public void borrarProveedor(Proveedor proveedor) {

		try {

			prep = connection.prepareStatement("DELETE FROM `db-sistema`.cat_proveedores WHERE ID_PROVEEDOR=?");
			prep.setInt(1, proveedor.getIdProveedor());
			prep.executeQuery();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "No puede eliminar este proveedor, ya que tiene articulos asignados");

		}

	}

	public InputStream buscarFoto(Producto producto) {
		InputStream streamFoto = null;
		try {

			String sql = "SELECT FOTO_PROD FROM `db-sistema`.cat_productos WHERE ID_PRODUCTO=?";

			prep = connection.prepareStatement(sql);
			prep.setInt(1, producto.getIdProducto1());

			rs = prep.executeQuery();

			while (rs.next()) {
				streamFoto = rs.getBinaryStream("FOTO_PROD");

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				rs.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		}
		return streamFoto;

	}

	///////////// OBTENEMOS LA CATEGORIA PARA APLICAR AL JTEXTFIELD
	public void obtenerCategoria(Categoria categoria) {

		try {

			String sql = "SELECT NOM_CATEGORIA_PROD FROM `db-sistema`.cat_categoria;";
			prep = connection.prepareStatement(sql);
			st = connection.createStatement();
			prep.setString(1, categoria.getNomCategoria().toUpperCase());
			prep.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public boolean registrar(Usuario mUsuario) {
		try {
			PreparedStatement prep = null;


			String SentenciaSql = "INSERT INTO usuario (USUARIO,PASSWORD,NOMBRE,CORREO)" + "   VALUES (?,?,?,?)";



			prep = connection.prepareStatement(SentenciaSql);

			prep.setString(1, mUsuario.getUsuario());
			prep.setString(2, mUsuario.getPassword());
			prep.setString(3, mUsuario.getNombre());
			prep.setString(4, mUsuario.getCorreo());


			prep.execute();

			return true;
		} catch (SQLException e) {
			System.err.println(e.toString());
			return false;
		}
	}

	public boolean login(Usuario usuario) {

		PreparedStatement prep = null;
		ResultSet rs = null;
		try {

			String SentenciaSql = "SELECT u.ID, u.USUARIO, u.PASSWORD, u.NOMBRE,u.CORREO,T.NOMBRE FROM usuario AS u "
					+ "INNER JOIN tipo_usuario AS T ON u.ID=T.ID_TIPO WHERE USUARIO =?";

			prep = connection.prepareStatement(SentenciaSql);

			prep.setString(1, usuario.getUsuario());

			rs = prep.executeQuery();

			if (rs.next()) {
				if (usuario.getPassword().equals(rs.getString(3))) {// CAMPO DE PASSWORD DE BD

					String sqlUpdate = "UPDATE  usuario SET  LAST_SESSION  =? WHERE ID = ? ";
					prep = connection.prepareStatement(sqlUpdate);

					prep.setString(1, usuario.getLast_session());
					prep.setInt(2, rs.getInt(1));
					prep.execute();

					usuario.setId(rs.getInt(1));
					usuario.setNombre(rs.getString(4));

					return true;// CUANDO COINCIDA LA CONTRASEÑA DEVUELVE TRUE

				} else {
					return false;
				}

			}

			return false;

		} catch (SQLException e) {

			return false;
		}
	}

	public boolean registro(Usuario usuario) {

		PreparedStatement prep = null;
		ResultSet rs = null;
		try {

			String SentenciaSql = "SELECT u.ID, u.USUARIO, u.PASSWORD, u.NOMBRE,u.CORREO,u.ID_TIPO "
					+ ",T.NOMBRE FROM usuario AS u INNER JOIN tipo_usuario AS T ON u.ID=T.ID_TIPO WHERE USUARIO =?";

			prep = connection.prepareStatement(SentenciaSql);

			prep.setString(1, usuario.getUsuario());

			rs = prep.executeQuery();

			if (rs.next()) {
				if (usuario.getPassword().equals(rs.getString(3))) {// CAMPO DE PASSWORD DE BD

					String sqlUpdate = "UPDATE  usuario SET  LAST_SESSION  =? WHERE ID = ? ";
					prep = connection.prepareStatement(sqlUpdate);

					prep.setString(1, usuario.getLast_session());
					prep.setInt(2, rs.getInt(1));
					prep.execute();

					usuario.setId(rs.getInt(1));
					usuario.setNombre(rs.getString(4));
					usuario.setId_tipo(rs.getInt(6));
					usuario.setNombreTipo(rs.getString(7));

					return true;// CUANDO COINCIDA LA CONTRASEÑA DEVUELVE TRUE

				} else {
					return false;
				}

			}

			return false;

		} catch (SQLException e) {

			return false;
		}
	}

	public int existeUsuario(String usuario) {

		PreparedStatement prep = null;
		ResultSet rs = null;
		try {

			String SentenciaSql = "SELECT count(ID) FROM usuario WHERE USUARIO =?";

			prep = connection.prepareStatement(SentenciaSql);

			prep.setString(1, usuario);
			rs = prep.executeQuery();

			while (rs.next()) {
				return rs.getInt(1);
			}
			return 1;
		} catch (SQLException e) {

			return 1;
		}

	}

	public void actualizarCliente(Clientes cliente) {
		try {

			prep = connection.prepareStatement(
					"UPDATE `db-sistema`.clientes SET NOMBRE = ?,DIRECCION = ? ,MAIL=?, TELEFONO=?, CUIT=? WHERE ID_CLIENTE = ?");

			prep.setString(1, cliente.getNombre());
			prep.setString(2, cliente.getDireccion());
			prep.setString(3, cliente.getMail());
			prep.setString(4, cliente.getTel());
			prep.setString(5, cliente.getCuit());
			prep.setInt(6, cliente.getIdCliente());

			prep.executeUpdate();



		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void eliminarCliente(int id) {

		try {

			prep = connection.prepareStatement("DELETE FROM `db-sistema`.clientes WHERE ID_CLIENTE=" + id);

			prep.executeQuery();

			JOptionPane.showMessageDialog(null, "Cliente eliminado");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Este cliente no puede ser borrado");

		}

	}


	// METODO PARA VER LAS CATEGORIAS EN VISTA PEDIDO
	public ArrayList<Producto> dameProductoPoridCategoria(int idCategoria) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {
			String sql = "SELECT ID_PROD,	NOM_PROD,NOM_CATEGORIA,NOM_PROVEEDOR,STOCK_PROD "
					+ "FROM `cat_productos` INNER JOIN `cat_categoria` "
					+ "ON  `cat_productos`.`ID_CATEGORIA` =`cat_categoria`.`ID_CATEGORIA` "
					+ "INNER JOIN `cat_proveedores` ON	 `cat_productos`.`ID_PROVEEDOR`=`cat_proveedores`.`ID_PROVEEDOR`"
					+ " WHERE  `cat_categoria`.`ID_CATEGORIA` =" + idCategoria;
			prep = connection.prepareStatement(sql);

			rs = prep.executeQuery();

			while (rs.next()) {
				String codigoArticulo = rs.getString("ID_PROD");
				String nom_prod = rs.getString("NOM_PROD");
				String nom_proveedor = rs.getString("NOM_PROVEEDOR");
				String nom_categoria = rs.getString("NOM_CATEGORIA");
				double stock_prod = rs.getDouble("STOCK_PROD");
				Producto producto = new Producto();

				producto.setCodigoArticulo(codigoArticulo);
				producto.setNomProducto(nom_prod);
				producto.setNomProveedor(nom_proveedor);
				producto.setNomCategoria(nom_categoria);
				producto.setStockProducto(stock_prod);

				listaProductos.add(producto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return listaProductos;
	}

//VISTA REALIZAR PEIDIDO MUESTRA LOS DATOS EN TABLA
	public ArrayList<Producto> getCategoria(int idCategoria) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {
			String sql = "SELECT ID_PRODUCTO,ID_PROD ,NOM_PROD,ID_PROVEEDOR_PROD,PRECIO_COMPRA_PROD,STOCK_PROD "
					+ "FROM `cat_productos` INNER JOIN `cat_categoria` "
					+ "ON  `cat_productos`.`ID_CATEGORIA` =`cat_categoria`.`ID_CATEGORIA` "
					+ "INNER JOIN `cat_proveedores` ON	 `cat_productos`.`ID_PROVEEDOR`=`cat_proveedores`.`ID_PROVEEDOR`"
					+ " WHERE  `cat_categoria`.`ID_CATEGORIA` =" + idCategoria;
			prep = connection.prepareStatement(sql);

			rs = prep.executeQuery();

			while (rs.next()) {
				int  idProducto = rs.getInt("ID_PRODUCTO");
				String codigoArticulo = rs.getString("ID_PROD");
				String nom_prod = rs.getString("NOM_PROD");
				String id_proveedor_prod = rs.getString("ID_PROVEEDOR_PROD");
				double precio_compra_prod = rs.getDouble("PRECIO_COMPRA_PROD");
				double stock_prod = rs.getDouble("STOCK_PROD");
				Producto producto = new Producto();

				
				producto.setCodigoArticulo(codigoArticulo);
				producto.setIdProducto(idProducto);
				producto.setNomProducto(nom_prod);
				producto.setIdProveedorProducto(id_proveedor_prod);
				producto.setPrecioCompraProducto(precio_compra_prod);
				producto.setStockProducto(stock_prod);

				listaProductos.add(producto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return listaProductos;
	}

	public ArrayList<Producto> getProveedor(int idProveedor) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {
			String sql = "SELECT ID_PRODUCTO,ID_PROD,NOM_PROD,ID_PROVEEDOR_PROD,PRECIO_COMPRA_PROD,STOCK_PROD "
					+ "FROM `cat_productos` INNER JOIN `cat_categoria` "
					+ "ON  `cat_productos`.`ID_CATEGORIA` =`cat_categoria`.`ID_CATEGORIA` "
					+ "INNER JOIN `cat_proveedores` ON	 `cat_productos`.`ID_PROVEEDOR`=`cat_proveedores`.`ID_PROVEEDOR`"
					+ " WHERE  `cat_proveedores`.`ID_PROVEEDOR` =" + idProveedor ;
			prep = connection.prepareStatement(sql);

			rs = prep.executeQuery();

			while (rs.next()) {
				int idProducto = rs.getInt("ID_PRODUCTO");
				String codigoArticulo = rs.getString("ID_PROD");
				String nom_prod = rs.getString("NOM_PROD");
				String id_proveedor_prod = rs.getString("ID_PROVEEDOR_PROD");
				double precio_compra_prod = rs.getDouble("PRECIO_COMPRA_PROD");
				double stock_prod = rs.getDouble("STOCK_PROD");
				Producto producto = new Producto();

				producto.setCodigoArticulo(codigoArticulo);
				producto.setIdProducto(idProducto);
				producto.setNomProducto(nom_prod);
				producto.setIdProveedorProducto(id_proveedor_prod);
				producto.setPrecioCompraProducto(precio_compra_prod);
				producto.setStockProducto(stock_prod);

				listaProductos.add(producto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return listaProductos;
	}

	public ArrayList<Producto> selectProveedoresTABLAPEDIDO(int idProveedor) {

		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		try {
			String sql = "SELECT ID_PROD,NOM_PROD,NOM_CATEGORIA,NOM_PROVEEDOR,STOCK_PROD "
					+ "FROM `cat_productos`  INNER JOIN `cat_categoria` "
					+ "ON  `cat_productos`.`ID_CATEGORIA` =`cat_categoria`.`ID_CATEGORIA` "
					+ "INNER JOIN `cat_proveedores` ON	 `cat_productos`.`ID_PROVEEDOR`=`cat_proveedores`.`ID_PROVEEDOR`"
					+ " WHERE  `cat_proveedores`.`ID_PROVEEDOR`   =" + idProveedor+" ORDER BY NOM_PROD";

			
			prep = connection.prepareStatement(sql);

			rs = prep.executeQuery();

			while (rs.next()) {
				String codigoArticulo = rs.getString("ID_PROD");
				String nom_prod = rs.getString("NOM_PROD");
				String nom_categoria = rs.getString("NOM_CATEGORIA");
				String nom_proveedor = rs.getString("NOM_PROVEEDOR");
				double stock_prod = rs.getDouble("STOCK_PROD");
				Producto producto = new Producto();


				producto.setCodigoArticulo(codigoArticulo);
				
				producto.setNomProducto(nom_prod);
				producto.setNomCategoria(nom_categoria);
				producto.setNomProveedor(nom_proveedor);
				producto.setStockProducto(stock_prod);

				listaProductos.add(producto);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return listaProductos;
	}

	public void insertarPedido(Pedido pedido) {

		try {

			String SentenciaSql = "INSERT INTO `db-sistema`.pedido (NUM_PEDIDO, ID_PROVEEDOR_PROD,DESCRIPCION,PRECIO_COSTO,FECHA,ID_PROD,ID_PROVEEDOR,PRECIO_TOTAL,CANTIDAD_PRODUCTO,ID_PRODUCTO) VALUES(?,?,?,?,?,?,?,?,?,?)";

			prep = connection.prepareStatement(SentenciaSql);
			prep.setInt(1, pedido.getNum_pedido());
			prep.setString(2, pedido.getId_proveedor_prod());
			prep.setString(3, pedido.getDescripcion());
			prep.setDouble(4, pedido.getPrecio_costo());
			prep.setString(5, pedido.getFecha());
			prep.setString(6, pedido.getId_producto());
			prep.setInt(7, pedido.getId_proveedor());
			prep.setDouble(8, pedido.getPrecio_total());
			prep.setString(9, pedido.getCantidad_producto());
			prep.setInt(10, pedido.getIdProducto());
			prep.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

	}

	// DEVUELVE EL PEDIDO
	public ArrayList<Pedido> damePedido() {
		ArrayList<Pedido> listarPedido = new ArrayList<Pedido>();
		try {
			prep = connection.prepareStatement("SELECT * FROM 	pedido");

			rs = prep.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("ID");
				int num_pedido = rs.getInt("NUM_PEDIDO");
				String id_proveedor_prod = rs.getString("ID_PROVEEDOR_PROD");
				String descripcion = rs.getString("DESCRIPCION");
				double precio_costo = rs.getDouble("PRECIO_COSTO");
				String fecha = rs.getString("FECHA");
				String id_prod = rs.getString("ID_PROD");
				int id_proveedor = rs.getInt("ID_PROVEEDOR");
				double precio_total = rs.getDouble("PRECIO_TOTAL");
				String cantidad_producto = rs.getString("CANTIDAD_PRODUCTO");
				int idProducto =rs.getInt("ID_PRODUCTO");
		
				Pedido pedido = new Pedido();
				
				pedido.setId(id);
				pedido.setNum_pedido(num_pedido);
				pedido.setId_proveedor_prod(id_proveedor_prod);
				pedido.setDescripcion(descripcion);
				pedido.setPrecio_costo(precio_costo);
				pedido.setFecha(fecha);
				pedido.setId_producto(id_prod);
				pedido.setId_proveedor(id_proveedor);
				pedido.setPrecio_total(precio_total);
				pedido.setCantidad_producto(cantidad_producto);
				pedido.setIdProducto(idProducto);
				
				listarPedido.add(pedido);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listarPedido;

	}

	public ArrayList<Pedido> selectPedidoID(int num_pedido) {
		ArrayList<Pedido> listarPedido = new ArrayList<Pedido>();
		try {
			String sql = "SELECT ID_PROD,ID_PROVEEDOR_PROD,ID_PROVEEDOR,DESCRIPCION,PRECIO_COSTO,CANTIDAD_PRODUCTO,"
					+ "PRECIO_TOTAL FROM pedido WHERE NUM_PEDIDO = " + num_pedido;
			prep = connection.prepareStatement(sql);
			rs = prep.executeQuery();

			while (rs.next()) {
				
				String id_prod = rs.getString("ID_PROD");
				String id_proveedor_prod = rs.getString("ID_PROVEEDOR_PROD");
				int id_proveedor=rs.getInt("ID_PROVEEDOR");
				String descripcion = rs.getString("DESCRIPCION");
				double precio_costo = rs.getDouble("PRECIO_COSTO");
				String cantidad_producto = rs.getString("CANTIDAD_PRODUCTO");
				double precio_total = rs.getDouble("PRECIO_TOTAL");

				Pedido pedido = new Pedido();
			
				pedido.setId_producto(id_prod);
				pedido.setId_proveedor_prod(id_proveedor_prod);
				pedido.setId_proveedor(id_proveedor);
				pedido.setDescripcion(descripcion);
				pedido.setPrecio_costo(precio_costo);
				pedido.setCantidad_producto(cantidad_producto);
				pedido.setPrecio_total(precio_total);

				listarPedido.add(pedido);

			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return listarPedido;
	}

	public void updateCantidad(int cantidad,String id) {
		

		try {

			prep = connection.prepareStatement("UPDATE `db-sistema`.pedido SET CANTIDAD_PRODUCTO = ? WHERE ID_PROD = ?");


			prep.setInt(1, cantidad);
			prep.setString(2, id);
			

			prep.executeUpdate();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Precios Modificado");

		}
		
	}

}
