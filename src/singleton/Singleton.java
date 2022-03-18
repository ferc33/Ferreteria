package singleton;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import modelo.Proveedor;
import vista.Categorias.VistaCATEGORIA;
import vista.Clientes.VistaADD_CLIENTE;
import vista.Clientes.VistaCLIENTES;
import vista.Clientes.VistaEDITAR_CLIENTE;
import vista.Importador.VistaIMPORTADOR_EXCEL;
import vista.Inventario.VistaINVENTARIO;
import vista.Ordendepedido.VistaORDEN_PEDIDO;
import vista.Proveedores.VistaEDITAR_PROVEEDOR;
import vista.Proveedores.VistaPROVEEDORES;
import vista.ReporteProveedores.VistaREPORTE_PROVEEDORES;
import vista.Ventas.VistaVENTAS;
import vistasProductos.VistaBuscarProductoOP;

public class Singleton {

	private static VistaCATEGORIA vistaCATEGORIA;
	private static VistaBuscarProductoOP vistaBuscarProductoOP;
	private static VistaCLIENTES vistaCLIENTES;
	private static JDialog vistaIMPORTADOR_EXCEL;
	private static VistaADD_CLIENTE vistaADD_CLIENTE;
	private static VistaEDITAR_CLIENTE vistaEDITAR_CLIENTE;
	private static VistaPROVEEDORES vistaPROVEEDORES;
	private static VistaEDITAR_PROVEEDOR vistaEDITAR_PROVEEDORES;
	private static VistaVENTAS vistaVENTAS;
	private static JInternalFrame vistaORDEN_PEDIDO;
	private static JInternalFrame vistaREPORTE_PROVEEDORES;
	private static VistaINVENTARIO vistaINVENTARIO;

	public Singleton() {
	}

	//RETURN VISTA CATEGORIA
		public static VistaBuscarProductoOP getVistaBuscarProductoOP() {
			if (vistaBuscarProductoOP == null) {
				vistaBuscarProductoOP = new VistaBuscarProductoOP(new JFrame(), true);
			}
				return vistaBuscarProductoOP;
			
		}
	
//RETURN VISTA CATEGORIA
	public static VistaCATEGORIA getVistaCATEGORIA() {
		if (vistaCATEGORIA == null) {
			vistaCATEGORIA = new VistaCATEGORIA(new javax.swing.JFrame(), true);
		}
		return vistaCATEGORIA;
	}
	
	

	// RETURN VISTA EDITAR PROVEEDOR
	public static VistaEDITAR_PROVEEDOR getVistaEDITAR_PROVEEDORES() {
		if (vistaEDITAR_PROVEEDORES == null) {
			vistaEDITAR_PROVEEDORES = new VistaEDITAR_PROVEEDOR(new VistaPROVEEDORES(), true);
		}
		return vistaEDITAR_PROVEEDORES;
	}

//RETURN CLIENTES
	public static VistaCLIENTES getVistaCLIENTES() {
		if (vistaCLIENTES == null) {
			vistaCLIENTES = new VistaCLIENTES(new JFrame(),true);
		}
		return vistaCLIENTES;
	}
	
	public static VistaEDITAR_CLIENTE getVistaEDITAR_CLIENTE() {
		if(vistaEDITAR_CLIENTE==null) {
			vistaEDITAR_CLIENTE = new VistaEDITAR_CLIENTE(new VistaCLIENTES(),true);
			
		}
		return vistaEDITAR_CLIENTE;
	}
	
	public static VistaADD_CLIENTE getVistaAÑADIR_CLIENTE() {
		if(vistaADD_CLIENTE==null) {
			vistaADD_CLIENTE = new VistaADD_CLIENTE(new VistaCLIENTES(),true);
			
		}
		return vistaADD_CLIENTE;
	}
	

//RETURN IMPORTADOR EXCEL
	public static JDialog getVistaIMPORTADOR_EXCEL() {
		if (vistaIMPORTADOR_EXCEL == null) {
			vistaIMPORTADOR_EXCEL = new VistaIMPORTADOR_EXCEL(new javax.swing.JFrame(), true);
		}
		return vistaIMPORTADOR_EXCEL;
	}

//RETURN VISTA PROVEEDORES
	public static VistaPROVEEDORES getVistaPROVEEDORES() {
		if (vistaPROVEEDORES == null) {
			vistaPROVEEDORES = new VistaPROVEEDORES(new javax.swing.JFrame(), true);
		}
		return vistaPROVEEDORES;
	}

//RETURN VISTA DE INVENTARIO
	public static VistaINVENTARIO getVistaINVENTARIO() {
		if (vistaINVENTARIO == null) {
			vistaINVENTARIO = new VistaINVENTARIO(2);
		}
		return vistaINVENTARIO;
	}

//RETURN VISTA VENTAS
	public static JInternalFrame getVistaVENTAS() {
		if (vistaVENTAS == null) {
			vistaVENTAS = new VistaVENTAS();
		}
		return vistaVENTAS;
	}

//RETURN VISTA ORDEN DE PEDIDO
	public static JInternalFrame getVistaORDEN_PEDIDO() {
		if (vistaORDEN_PEDIDO == null) {
			vistaORDEN_PEDIDO = new VistaORDEN_PEDIDO();
		}
		return vistaORDEN_PEDIDO;
	}

//VISTA REPORTE DE PROVEEDORES
	public static JInternalFrame getVistaREPORTE_PROVEEDORES() {
		if (vistaREPORTE_PROVEEDORES == null) {
			vistaREPORTE_PROVEEDORES = new VistaREPORTE_PROVEEDORES();
		}
		return vistaREPORTE_PROVEEDORES;
	}

}
