
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Categoria {
    
        private int idCategoria;
        private String nomCategoria;
      
        
    public Categoria(String nomCategoria, int idProveedor) {
			super();
			this.nomCategoria = nomCategoria;
			
		}

	public Categoria(int idCategoria, String nomCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nomCategoria = nomCategoria;
	
	}



	public Categoria() {
			super();
		}



	public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomCategoria() {
        return nomCategoria;
    }

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }
  



	@Override
   public String toString(){
       return this.nomCategoria;
   }
    //metodo que devuelve el vector con las categorias
   
        
}
