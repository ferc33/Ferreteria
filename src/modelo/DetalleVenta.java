
package modelo;


public class DetalleVenta {
    
    private int idDetalleVenta;
    private Long idVenta;
    private int idProducto;
    private String cantidadVendidad;

    public DetalleVenta(Long idVenta, int idProducto, String cantidadVendidad) {
        //this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadVendidad = cantidadVendidad;
    }

    
    
    public int getIdProducto() {
		return idProducto;
	}



	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}



	public String getCantidadVendidad() {
        return cantidadVendidad;
    }

    public void setCantidadVendidad(String cantidadVendidad) {
        this.cantidadVendidad = cantidadVendidad;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }


    
}
