package modelo;

import java.io.File;

public class Producto {

	private int idProducto;
	private String codigoArticulo;
	private String nomProducto;
	private double stockProducto;
	private String idProveedorProducto;
	private File fotoProducto;
	private double precioCompraProducto;
	private double precioVentaProducto;
	private String bon1;
	private String bon2;
	private String bon3;
	private String bon4;	
	private double existenciasProducto;
	private int idCategoria;
	private int idProveedor;
	private String nomProveedor;
	private String nomCategoria;
	private double dolar;
	private double iva;
	private double ganancia;
	private double flete;

	public String getNomProveedor() {
		return nomProveedor;
	}

	public void setNomProveedor(String nomProveedor) {
		this.nomProveedor = nomProveedor;
	}

	public String getNomCategoria() {
		return nomCategoria;
	}

	public void setNomCategoria(String nomCategoria) {
		this.nomCategoria = nomCategoria;
	}
	
	public String getBon1() {
		return bon1;
	}

	public void setBon1(String bon1) {
		this.bon1 = bon1;
	}

	public String getBon2() {
		return bon2;
	}

	public void setBon2(String bon2) {
		this.bon2 = bon2;
	}

	public String getBon3() {
		return bon3;
	}

	public void setBon3(String bon3) {
		this.bon3 = bon3;
	}

	public String getBon4() {
		return bon4;
	}

	public void setBon4(String bon4) {
		this.bon4 = bon4;
	}

	public int getIdPedido() {
		return idProducto;
	}

	public void setIdPedido(int idPedido) {
		this.idProducto = idPedido;
	}

	
	public void setIva(double iva) {
		this.iva = iva;
	}




	public Producto(String codigoArticulo, String nombre, double stock, String codigoProveedor, File fotoProducto, double pCosto,
			double pVenta,String bon1,String bon2, String bon3, String bon4,double dolar, int existenciasProducto, int idCategoria, int idProveedor, double iva,
			double flete, double ganancia) {

		this.codigoArticulo = codigoArticulo;
		this.nomProducto = nombre;
		this.stockProducto = stock;
		this.idProveedorProducto = codigoProveedor;
		this.fotoProducto = fotoProducto;
		this.precioCompraProducto = pCosto;
		this.precioVentaProducto = pVenta;
		this.bon1=bon1;
		this.bon2=bon2;
		this.bon3=bon3;
		this.bon4=bon4;
		this.existenciasProducto = existenciasProducto;
		this.idCategoria = idCategoria;
		this.idProveedor = idProveedor;
		this.iva = iva;
		this.ganancia = ganancia;		
		this.flete = flete;
	}
public Producto (int idProducto, String codigoArticulo, String nombre) {
	this.idProducto=idProducto;
	this.codigoArticulo=codigoArticulo;
	this.nomProducto=nombre;
	
}
	// UPDATE
	public Producto(int idProducto, String codigoArticulo, String nombre, double stock, String codigoProveedor, File fotoProducto,
			double pCosto, double pVenta, String bon1,String bon2,String bon3,String bon4,double dolar, double existenciasProducto, int idCategoria, int idProveedor,
			double iva, double flete, double ganancia) {

		this.idProducto = idProducto;
		this.codigoArticulo = codigoArticulo;
		this.nomProducto = nombre;
		this.stockProducto = stock;
		this.idProveedorProducto = codigoProveedor;
		this.fotoProducto = fotoProducto;
		this.precioCompraProducto = pCosto;
		this.precioVentaProducto = pVenta;
		this.bon1=bon1;
		this.bon2=bon2;
		this.bon3=bon3;
		this.bon4=bon4;
		this.dolar = dolar;
		this.existenciasProducto = existenciasProducto;
		this.idCategoria = idCategoria;
		this.idProveedor = idProveedor;
		this.iva = iva;
		this.ganancia = ganancia;		
		this.flete = flete;
	}

	public Producto() {
	}
	
	

	public Producto(String codigoArticulo, String nombre, String nomCat, String nomProve, Double stock) {
	this.codigoArticulo=codigoArticulo;
	this.nomProducto=nombre;
	this.nomCategoria=nomCat;
	this.nomProveedor=nomProve;
	this.stockProducto=stock;
	}

	/**
	 * @return the nomProducto
	 */
	public String getNomProducto() {
		return nomProducto;
	}

	/**
	 * @param nomProducto the nomProducto to set
	 */
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}

	public File getFotoProducto() {
		return fotoProducto;
	}

	public void setFotoProducto(File fotoProducto) {
		this.fotoProducto = fotoProducto;
	}

	public double getDolar() {
		return dolar;
	}

	public void setDolar(double dolar) {
		this.dolar = dolar;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia2) {
		this.ganancia = ganancia2;
	}


	public double getFlete() {
		return flete;
	}

	public void setFlete(double flete2) {
		this.flete = flete2;
	}




	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public double getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(double stockProducto) {
		this.stockProducto = stockProducto;
	}

	public String getIdProveedorProducto() {
		return idProveedorProducto;
	}

	public void setIdProveedorProducto(String idProveedorProducto) {
		this.idProveedorProducto = idProveedorProducto;
	}

	public int getIdProducto1() {
		return idProducto;
	}

	public void setIdProducto1(int idProducto1) {
		this.idProducto = idProducto1;
	}

	public double getPrecioCompraProducto() {
		return precioCompraProducto;
	}

	public void setPrecioCompraProducto(double precioCompraProducto) {
		this.precioCompraProducto = precioCompraProducto;
	}

	public double getPrecioVentaProducto() {
		return precioVentaProducto;
	}

	public void setPrecioVentaProducto(double precioVentaProducto) {
		this.precioVentaProducto = precioVentaProducto;
	}

	public double getExistenciasProducto() {
		return existenciasProducto;
	}

	public void setExistenciasProducto(double existenciasProducto) {
		this.existenciasProducto = existenciasProducto;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Override
	public String toString() {
		return nomProducto;
	}




}
