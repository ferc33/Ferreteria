
package modelo;

import java.util.Date;


public class Ventas {
    
    private int idVenta;
    private double montoVenta;
   private Date fechaVenta;

    public Ventas(double montoVenta, Date fechaVenta) {
        this.idVenta =  idVenta;
        this.montoVenta = montoVenta;
        this.fechaVenta = fechaVenta;
    }

         

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }
   
    
}
