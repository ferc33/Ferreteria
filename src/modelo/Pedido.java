package modelo;

public class Pedido {

		@Override
	public String toString() {
		return descripcion ;
	}
		private int id;
		private int num_pedido;
		private String id_proveedor_prod;
		private String descripcion;
		private double precio_costo;
		private String fecha;
		private String id_prod;
		private int id_proveedor;
		private double precio_total;
		private String cantidad_producto;
		private int idProducto;
		
		public Pedido(int id, int num_pedido, String id_proveedor_prod, String descripcion, double precio_costo,
				String fecha, String id_prod, int id_proveedor,double precio_total,String cantidad_producto,int id1) {
	
			this.id = id;
			this.num_pedido = num_pedido;
			this.id_proveedor_prod = id_proveedor_prod;
			this.descripcion = descripcion;
			this.precio_costo = precio_costo;
			this.fecha = fecha;
			this.id_prod = id_prod;
			this.id_proveedor = id_proveedor;
			this.precio_total=precio_total;
			this.cantidad_producto=cantidad_producto;
			this.idProducto=id1;
		}
		
		
		public Pedido() {}

	
		public Pedido(int num_pedido, String id_proveedor_prod, String descripcion, double precio_costo, String fecha,
				String id_prod, int id_proveedor, double precio_total, String cantidad_producto) {
			super();
			this.num_pedido = num_pedido;
			this.id_proveedor_prod = id_proveedor_prod;
			this.descripcion = descripcion;
			this.precio_costo = precio_costo;
			this.fecha = fecha;
			this.id_prod = id_prod;
			this.id_proveedor = id_proveedor;
			this.precio_total = precio_total;
			this.cantidad_producto = cantidad_producto;
		}


		
		
		public int getIdProducto() {
			return idProducto;
		}


		public void setIdProducto(int idProducto) {
			this.idProducto = idProducto;
		}


		public double getPrecio_total() {
			return precio_total;
		}


		public void setPrecio_total(double precio_total) {
			this.precio_total = precio_total;
		}


		public String getCantidad_producto() {
			return cantidad_producto;
		}


		public void setCantidad_producto(String cantidad_producto) {
			this.cantidad_producto = cantidad_producto;
		}


		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getNum_pedido() {
			return num_pedido;
		}
		public void setNum_pedido(int num_pedido) {
			this.num_pedido = num_pedido;
		}
		public String getId_proveedor_prod() {
			return id_proveedor_prod;
		}
		public void setId_proveedor_prod(String id_proveedor_prod) {
			this.id_proveedor_prod = id_proveedor_prod;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public double getPrecio_costo() {
			return precio_costo;
		}
		public void setPrecio_costo(double precio_costo) {
			this.precio_costo = precio_costo;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getId_producto() {
			return id_prod;
		}
		public void setId_producto(String id_prod) {
			this.id_prod = id_prod;
		}
		public int getId_proveedor() {
			return id_proveedor;
		}
		public void setId_proveedor(int id_proveedor) {
			this.id_proveedor = id_proveedor;
		}
		
	
}