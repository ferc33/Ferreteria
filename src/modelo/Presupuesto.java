/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

public class Presupuesto {

 
    private int id_presupuesto;
    private double impTotal;
    private int posicion;
    private String fecha;
    private double precio_unitario;
    private double total;
    private double iva;
    private double iva_r;
    private int cant_productos;
    private int id_cliente;
    private int id_prod;
    private String id_prodString;
    private double descuento;
    private String descripcion;
    private int stock;
  
  

	//SELECT
      public Presupuesto( int idPresupuesto,int posicion, String fecha, int cant_productos, int  id_cliente, int id_prod, double descuento,double impTotal) {
        
    	  this.posicion = posicion;
          this.id_presupuesto=idPresupuesto;        
          this.fecha = fecha;
          this.cant_productos=cant_productos;
          this.id_cliente=id_cliente;
          this.id_prod=id_prod;
          this.descuento=descuento;
          this.impTotal=impTotal;
      }
      
      //INSERT
      public Presupuesto( int posicion, String fecha, int cant_prod, int id_cliente, int id_prod, double descuento, double total,double impTotal) {
          this.posicion = posicion;     
          this.fecha = fecha;
          this.cant_productos=cant_prod;
          this.id_cliente=id_cliente;
          this.id_prod=id_prod;
          this.descuento=descuento;
          this.total = total;
          this.impTotal=impTotal;
      }
      
    //INNER JOIN DE PRESUPUESTO REPORTE    
  	public Presupuesto(int id_prod, int posicion,int cant, String descripcion, double precio_unitario, double total) {
  	
  		this.posicion=posicion;
  		this.id_prod=id_prod;
  		this.cant_productos=cant;
  		this.descripcion=descripcion;
  		this.precio_unitario=precio_unitario;
  		this.total= total;
  		
  		
  	}
  //INNER JOIN CON ID_PROD
  	public Presupuesto(String id_prodString, int posicion,int cant, String descripcion, double precioVenta, double importe, int stock2) {
  		
  		this.posicion=posicion;
  		this.id_prodString=id_prodString;
  		this.cant_productos=cant;
  		this.descripcion=descripcion;
  		this.precio_unitario=precioVenta;
  		this.total= importe;
  		
  		
  	}

      public Presupuesto(int posicion, int codigoProd, String fecha, int cant_productos, int id_cliente, int id_prod) {
  		
      	this.posicion = posicion;
      	this.id_prod=id_prod;
      	this.fecha=fecha;
      	this.cant_productos=cant_productos;
      	this.id_cliente=id_cliente;
      	this.id_prod=id_prod;
  	}
      
    
    public String getId_prodString() {
		return id_prodString;
	}

	public void setId_prodString(String id_prodString) {
		this.id_prodString = id_prodString;
	}

	public double getImpTotal() {
		return impTotal;
	}

	public void setImpTotal(double impTotal) {
		this.impTotal = impTotal;
	}

	public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

    @Override
    public String toString() {
        return this.descripcion;
    }
    

    public String getNomProd() {
        return descripcion;
    }

    public void setNomProd(String nomProd) {
        this.descripcion = nomProd;
    }

   

    public Presupuesto() {
    }




	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId_presupuesto() {
        return id_presupuesto;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void setId_presupuesto(int id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getpNeto() {
        return total;
    }

    public void setpNeto(double pNeto) {
        this.total = pNeto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getIva_r() {
        return iva_r;
    }

    public void setIva_r(double iva_r) {
        this.iva_r = iva_r;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }



    public int getCant_productos() {
        return cant_productos;
    }

    public void setCant_productos(int cant_productos) {
        this.cant_productos = cant_productos;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
    
    

}
