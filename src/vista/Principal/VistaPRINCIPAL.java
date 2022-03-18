/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.Principal;

import modelo.Usuario;
import modelo.Ventas;
import singleton.Singleton;
import vista.Clientes.VistaCLIENTES;
import vista.Inventario.VistaINVENTARIO;
import vista.Proveedores.VistaPROVEEDORES;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import org.apache.poi.util.SystemOutLogger;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;

public class VistaPRINCIPAL extends javax.swing.JFrame {
	private Usuario usuario;
	private ButtonGroup GrupoBotones;
	private JMenuItem btnDolar;
	private JMenuItem jMenuItem1;

	public VistaINVENTARIO articulos;
	public Ventas ventas;
	private JLabel id, lblCategorias, lblArticulos, lblVentas, lblProveedores, lblOrden, lblSalir, lblReportes,
			lblClientes, lblOrdenPedido, lblActualizacion;
	public JTextField nombre;
	public JDesktopPane escritorio;
	private JPanel btnArticulos, btnSalir;
	private JPanel btnVentas;
	private JPanel btnProveedores;
	private JPanel btnClientes;
	private JPanel btnCategorias;
	private JPanel btnActualizacion;
	private JPanel btnReportes;
	private JPanel btnOrden, laminaSalir;

	public VistaPRINCIPAL(Usuario usuario) {
		setAlwaysOnTop(false);
		initComponents();

		this.usuario = usuario;

		if (usuario.getId() == 1) {

		} else if (usuario.getId() == 2) {

			btnArticulos.setEnabled(false);
			btnProveedores.setEnabled(false);
			btnCategorias.setEnabled(false);

		}
	}

	public VistaPRINCIPAL() {
		setAlwaysOnTop(true);
		initComponents();

	}

	private void initComponents() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		escritorio = new JDesktopPane();
		escritorio.setBackground(new Color(255, 255, 255));
		escritorio.setBounds(180, 36, 1183, 669);
		getContentPane().add(escritorio);
		escritorio.setLayout(new BorderLayout(0, 0));
		
				laminaSalir = new JPanel();
				escritorio.add(laminaSalir, BorderLayout.NORTH);
				laminaSalir.setBackground(new Color(255, 255, 255));
				laminaSalir.setLayout(null);
				
						btnSalir = new JPanel();
						btnSalir.setBounds(59, 28, 42, 38);
						laminaSalir.add(btnSalir);
						btnSalir.setLayout(null);

		JPanel laminaBotones = new JPanel();
		laminaBotones.setBackground(Color.DARK_GRAY);
		laminaBotones.setBounds(0, 36, 175, 669);
		getContentPane().add(laminaBotones);
		laminaBotones.setLayout(new GridLayout(0, 1, 0, 0));

		btnArticulos = new JPanel();
		btnArticulos.setForeground(Color.DARK_GRAY);
		btnArticulos.setBorder(null);
		btnArticulos.setBackground(Color.DARK_GRAY);
		laminaBotones.add(btnArticulos);
								btnArticulos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						
								lblArticulos = new JLabel("Articulos");
								lblArticulos.setAlignmentX(Component.RIGHT_ALIGNMENT);
								lblArticulos.setForeground(Color.WHITE);
								lblArticulos.setFont(new Font("Segoe UI", Font.PLAIN, 17));
								lblArticulos.setHorizontalAlignment(SwingConstants.CENTER);
								btnArticulos.add(lblArticulos);
				// BOTON DE ARTICULOS
				lblArticulos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnArticulos.setBackground(new Color(255, 140, 0));
						lblArticulos.setForeground(Color.WHITE);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						btnArticulos.setBackground(Color.DARK_GRAY);
						lblArticulos.setForeground(Color.WHITE);
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						JInternalFrame vista = Singleton.getVistaINVENTARIO();
						if (!vista.isVisible()) {
							escritorio.add(vista);
							escritorio.getDesktopManager().maximizeFrame(vista);
							vista.setVisible(true);
						} else {
							escritorio.getDesktopManager().maximizeFrame(vista);
						}
					}
				});

		btnVentas = new JPanel();
		btnVentas.setBorder(null);
		btnVentas.setBackground(Color.DARK_GRAY);
		laminaBotones.add(btnVentas);
		btnVentas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblVentas = new JLabel("Ventas");
		lblVentas.setForeground(Color.WHITE);
		lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentas.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnVentas.add(lblVentas);

		btnProveedores = new JPanel();
		btnProveedores.setBorder(null);
		btnProveedores.setBackground(Color.DARK_GRAY);
		laminaBotones.add(btnProveedores);
		btnProveedores.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblProveedores = new JLabel("Proveedores");
		lblProveedores.setForeground(Color.WHITE);
		lblProveedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnProveedores.add(lblProveedores);

		btnClientes = new JPanel();
		btnClientes.setBorder(null);
		btnClientes.setBackground(Color.DARK_GRAY);
		laminaBotones.add(btnClientes);
		btnClientes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblClientes = new JLabel("Clientes");
		lblClientes.setForeground(Color.WHITE);

		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnClientes.add(lblClientes);

		btnCategorias = new JPanel();
		btnCategorias.setBorder(null);
		btnCategorias.setBackground(Color.DARK_GRAY);
		laminaBotones.add(btnCategorias);
		btnCategorias.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblCategorias = new JLabel("Categorias");
		lblCategorias.setForeground(Color.WHITE);
		lblCategorias.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategorias.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnCategorias.add(lblCategorias);

		btnActualizacion = new JPanel();
		btnActualizacion.setBorder(null);
		btnActualizacion.setBackground(Color.DARK_GRAY);
		laminaBotones.add(btnActualizacion);
		btnActualizacion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblActualizacion = new JLabel("Actualizacion");
		lblActualizacion.setForeground(Color.WHITE);
		lblActualizacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblActualizacion.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnActualizacion.add(lblActualizacion);

		btnReportes = new JPanel();
		btnReportes.setBorder(null);
		btnReportes.setBackground(Color.DARK_GRAY);
		laminaBotones.add(btnReportes);
		btnReportes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblReportes = new JLabel("Reportes");
		lblReportes.setForeground(Color.WHITE);
		lblReportes.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportes.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnReportes.add(lblReportes);

		btnOrden = new JPanel();
		btnOrden.setBorder(null);
		btnOrden.setBackground(Color.DARK_GRAY);
		laminaBotones.add(btnOrden);
		btnOrden.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblOrdenPedido = new JLabel("Orden de compra");
		lblOrdenPedido.setForeground(Color.WHITE);
		lblOrdenPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdenPedido.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		btnOrden.add(lblOrdenPedido);
		
				lblSalir = new JLabel("X");
				lblSalir.setBounds(1261, 0, 97, 39);
				getContentPane().add(lblSalir);
				lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
				lblSalir.setFont(new Font("Roboto Black", Font.PLAIN, 16));
				lblSalir.setBackground(new Color(0, 0, 0));
				// BOTON DE SALIR
				lblSalir.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnSalir.setBackground(Color.RED);
						lblSalir.setForeground(Color.WHITE);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						btnSalir.setBackground(Color.WHITE);
						lblSalir.setForeground(Color.BLACK);
					}

					@Override
					public void mouseClicked(MouseEvent e) {
						System.exit(0);
					}
				});

		// VENTANA ACTUALIZAR POR EXCEL
		/*
		 * btnActualizar.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { JDialog vista = new
		 * Singleton().getVistaIMPORTADOR_EXCEL(); vista.setLocationRelativeTo(null);
		 * vista.setVisible(true); } }); // ABRIR CLIENTES
		 * btnClientes.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { VistaCLIENTES vista = new
		 * Singleton().getVistaCLIENTES(); vista.setLocationRelativeTo(null);
		 * vista.setVisible(true); }
		 * 
		 * }); // ABRIR CATEGORIAS btnCategorias.addActionListener(new ActionListener()
		 * { public void actionPerformed(ActionEvent e) {
		 *
		 * } }); // ABRIR VISTA PROVEEDORES
		 * 
		 * 
		 * btnProveedores.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { JDialog vista = new
		 * Singleton().getVistaPROVEEDORES(); vista.setLocationRelativeTo(null);
		 * vista.setVisible(true); }
		 * 
		 * });
		 * 
		 * // ABRIR VENTANA DE VENTAS btnVentas.addActionListener(new ActionListener() {
		 * public void actionPerformed(ActionEvent e) { JInternalFrame vista = new
		 * Singleton().getVistaVENTAS(); if (!vista.isVisible()) {
		 * escritorio.add(vista); escritorio.getDesktopManager().maximizeFrame(vista);
		 * vista.setVisible(true); } else {
		 * escritorio.getDesktopManager().maximizeFrame(vista); } } });
		 * 
		 * // ABRIR VENTANA DE INVENTARIO btnArticulos.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) {
		 * JInternalFrame vista = new Singleton().getVistaINVENTARIO();
		 * if(!vista.isVisible()) { escritorio.add(vista);
		 * escritorio.getDesktopManager().maximizeFrame(vista); vista.setVisible(true);
		 * } else { escritorio.getDesktopManager().maximizeFrame(vista); }
		 * 
		 * }
		 * 
		 * });
		 * 
		 * // ABRIR ORDEN DE PEDIDO btnOrdenDePedido.addActionListener(new
		 * ActionListener() {
		 * 
		 * @Override 
		 * public void actionPerformed(ActionEvent e) {
		 *  JInternalFrame vista =Singleton.getVistaORDEN_PEDIDO();
		 *   if (!vista.isVisible()) {
		 * escritorio.add(vista); 
		 * escritorio.getDesktopManager().maximizeFrame(vista);
		 * vista.setVisible(true); 
		 * } else {
		 * escritorio.getDesktopManager().maximizeFrame(vista);
		 *  } 
		 *  }
		 * 
		 * }); // ABRIR REPORTES btnReportes.addActionListener(new ActionListener() {
		 * public void actionPerformed(ActionEvent e) { JInternalFrame vista = new
		 * Singleton().getVistaREPORTE_PROVEEDORES(); if (!vista.isVisible()) {
		 * escritorio.add(vista); escritorio.getDesktopManager().maximizeFrame(vista);
		 * vista.setVisible(true); } else {
		 * escritorio.getDesktopManager().maximizeFrame(vista); } } });
		 */

		GrupoBotones = new javax.swing.ButtonGroup();
		jMenuItem1 = new javax.swing.JMenuItem();
		btnDolar = new javax.swing.JMenuItem();

		jMenuItem1.setText("jMenuItem1");
		setSize(1390, 845);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(0, 0, 0));

		btnDolar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
		btnDolar.setText("Dolar");
		setUndecorated(true);

		// BOTON DE VENTAS
		lblVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnVentas.setBackground(new Color(255, 140, 0));
				lblVentas.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnVentas.setBackground(Color.DARK_GRAY);
				lblVentas.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				 JInternalFrame vista =Singleton.getVistaVENTAS();
				 if (!vista.isVisible()) {
				  escritorio.add(vista); 
				  escritorio.getDesktopManager().maximizeFrame(vista);
				  vista.setVisible(true); 
				  } else {
				  escritorio.getDesktopManager().maximizeFrame(vista);
				   } 
			}
		});

		// BOTON DE PROVEEDORES
		lblProveedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnProveedores.setBackground(new Color(255, 140, 0));
				lblProveedores.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnProveedores.setBackground(Color.DARK_GRAY);
				lblProveedores.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				VistaPROVEEDORES proveedor = Singleton.getVistaPROVEEDORES();
				proveedor.setLocationRelativeTo(null);
				proveedor.setVisible(true);
			}
		});

		// BOTON DE CLIENTES
		lblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnClientes.setBackground(new Color(255, 140, 0));
				lblClientes.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnClientes.setBackground(Color.DARK_GRAY);
				lblClientes.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				VistaCLIENTES vista = Singleton.getVistaCLIENTES();
				vista.setLocationRelativeTo(null);
				vista.setVisible(true);
		
			}
		});

		// BOTON DE CATEGORIAS
		lblCategorias.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnCategorias.setBackground(new Color(255, 140, 0));
				lblCategorias.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCategorias.setBackground(Color.DARK_GRAY);
				lblCategorias.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
				JDialog vista = Singleton.getVistaCATEGORIA();
				vista.setLocationRelativeTo(null);
				vista.setVisible(true);
			}
		});
		// BOTON DE ACTUALIZACION
		lblActualizacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnActualizacion.setBackground(new Color(255, 140, 0));
				lblActualizacion.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnActualizacion.setBackground(Color.DARK_GRAY);
				lblActualizacion.setForeground(Color.white);
			}

			@Override
			public void mousePressed(MouseEvent e) {

				JDialog vista = new Singleton().getVistaIMPORTADOR_EXCEL();
				vista.setLocationRelativeTo(null);
				vista.setVisible(true);
			}

		});
		// BOTON REPORTES
		lblReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnReportes.setBackground(new Color(255, 140, 0));
				lblReportes.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnReportes.setBackground(Color.DARK_GRAY);
				lblReportes.setForeground(Color.white);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				 JInternalFrame vista =Singleton.getVistaREPORTE_PROVEEDORES();
				 if (!vista.isVisible()) {
				  escritorio.add(vista); 
				  escritorio.getDesktopManager().maximizeFrame(vista);
				  vista.setVisible(true); 
				  } else {
				  escritorio.getDesktopManager().maximizeFrame(vista);
				   } 
			}
		});
		// BOTON DE ORDEN DE COMPRA
		lblOrdenPedido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnOrden.setBackground(new Color(255, 140, 0));
				lblOrdenPedido.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnOrden.setBackground(Color.DARK_GRAY);
				lblOrdenPedido.setForeground(Color.white);
			}
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				 JInternalFrame vista =Singleton.getVistaORDEN_PEDIDO();
				 if (!vista.isVisible()) {
				  escritorio.add(vista); 
				  escritorio.getDesktopManager().maximizeFrame(vista);
				  vista.setVisible(true); 
				  } else {
				  escritorio.getDesktopManager().maximizeFrame(vista);
				   } 
			}
		});
	}
}


