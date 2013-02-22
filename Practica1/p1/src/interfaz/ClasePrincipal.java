package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import matriz.Matriz;

public class ClasePrincipal extends JFrame {

	/***********************
	 * ATRIBUTOS PRIVADOS
	 * 
	 ***********************/

	JPanel panelPrincipal;

	JTabbedPane panelTabulado;

	int filas;
	int columnas;

	JTextField botonFil;
	JTextField botonCol;

	Matriz m1;
	Matriz m2;
	Matriz resultado;

	DefaultTableModel tm1;
	DefaultTableModel tm2;
	DefaultTableModel tm3;

	JFrame ventanaFijarTamanyo;

	JPanel panelDeMatrices;
	JPanel pAuxA;
	JPanel pAuxB;
	JPanel pAuxR;
	
	JTable tablaA;
	JTable tablaB;
	JTable tablaR;
	
	JMenuBar barraPrincipal;

	boolean operacionesPermitidas;

	/************************************************/

	public ClasePrincipal() {

		// INICIALIZAR VARIABLES
		filas = 5;
		columnas = 5;
		operacionesPermitidas = false;

		
		pAuxA = new JPanel();
		pAuxB = new JPanel();
		pAuxR = new JPanel();
		
		tm1 = new DefaultTableModel();
		tm2 = new DefaultTableModel();
		tm3 = new DefaultTableModel();
		
		tablaA = new JTable(tm1);
		tablaB = new JTable(tm2);
		tablaR = new JTable(tm3);
		

		JOptionPane.showMessageDialog(null,
				"AVISO: TIENE QUE INTRODUCIR EL TAMA�O DE LA MATRIZ!");
		inicializarInterfaz();

	}

	public void inicializarInterfaz() {

		this.setTitle("CALCULADORA DE MATRICES");
		this.setVisible(true);
		this.setEnabled(true);
		this.setSize(500, 500);
		this.setJMenuBar(dameBarraMenu()); // crea la barra de menu y la adjunta
											// al jframe
		this.setContentPane(damePanelPrincipal()); // sirve para el contenido
													// del jframe
		this.validate();

	}

	private JPanel damePanelPrincipal() {

		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		panelTabulado = new JTabbedPane();
		panelPrincipal.add("Center", panelTabulado);
		panelPrincipal.validate();
		return panelPrincipal;
	}

	private JMenuBar dameBarraMenu() {

		barraPrincipal = new JMenuBar();
		barraPrincipal.add(getMenuMatrices());
		barraPrincipal.add(getMenuOperar());
		barraPrincipal.add(getMenuFicheros());
		barraPrincipal.setVisible(true);
		barraPrincipal.setEnabled(true);
		return barraPrincipal;

	}

	private JMenu getMenuMatrices() {

		JMenu matricesMenu = new JMenu("Matrices");
		matricesMenu.add(getTamanyoMenuItem());
		matricesMenu.add(getCargarMenuItem());
		matricesMenu.add(getInicializarMenuItem());
		return matricesMenu;
	}

	private JMenu getMenuOperar() {

		JMenu operarMenu = new JMenu("Operar");
		operarMenu.add(getSumaMenuItem());
		operarMenu.add(getRestaMenuItem());
		operarMenu.add(getMultiplicacionMenuItem());
		return operarMenu;
	}

	private JMenu getMenuFicheros() {

		JMenu ficherosMenu = new JMenu("Ficheros");
		ficherosMenu.add(getLeerMenuItem());
		ficherosMenu.add(getGuardarMenuItem());
		ficherosMenu.add(getDefaultMenuItem());
		return ficherosMenu;
	}

	private JMenu getGuardarMenuItem() {

		JMenu guardarMenu = new JMenu("Guardar");
		guardarMenu.setEnabled(true);
		guardarMenu.setVisible(true);
		guardarMenu.add(dameGuardarMatrizA());
		guardarMenu.add(dameGuardarMatrizB());
		guardarMenu.add(dameGuardarResultado());
		return guardarMenu;
	}

	private JMenu getLeerMenuItem() {

		JMenu multiplicarMenu = new JMenu("Cargar");
		multiplicarMenu.setEnabled(true);
		multiplicarMenu.setVisible(true);
		multiplicarMenu.add(dameCargarMatrizA());
		multiplicarMenu.add(dameCargarMatrizB());
		return multiplicarMenu;
	}

	private JMenuItem dameGuardarResultado() {
		JMenuItem guardarRMenuItem = new JMenuItem("Guardar Matriz Resultado");

		/*
		 * if(!operacionesPermitidas) { guardarRMenuItem.setEnabled(false); }
		 * else guardarRMenuItem.setEnabled(true);
		 */

		guardarRMenuItem.setVisible(true);
		guardarRMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * 
				 * 
				 * 
				 */
				// cargarMenuItem.setEnabled(true);

				/*
				 * 
				 * 
				 */

			}
		});

		return guardarRMenuItem;
	}

	private JMenuItem dameGuardarMatrizB() {
		JMenuItem guardarBMenuItem = new JMenuItem("Guardar Matriz B");
		// guardarBMenuItem.setEnabled(false);
		guardarBMenuItem.setVisible(true);
		guardarBMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * 
				 * 
				 * 
				 */
				// cargarMenuItem.setEnabled(true);

				/*
				 * 
				 * 
				 */

			}
		});

		return guardarBMenuItem;
	}

	private JMenuItem dameGuardarMatrizA() {
		JMenuItem guardarAMenuItem = new JMenuItem("Guardar Matriz A");
		// guardarAMenuItem.setEnabled(false);
		guardarAMenuItem.setVisible(true);
		guardarAMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * 
				 * 
				 * 
				 */
				// cargarMenuItem.setEnabled(true);

				/*
				 * 
				 * 
				 */

			}
		});

		return guardarAMenuItem;
	}

	/* Leer de Ficheros */

	private JMenuItem dameCargarMatrizB() {
		JMenuItem cargaBMenuItem = new JMenuItem("Cargar Matriz B");
		cargaBMenuItem.setEnabled(true);
		cargaBMenuItem.setVisible(true);
		cargaBMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// cargarMenuItem.setEnabled(true);

			}
		});

		return cargaBMenuItem;
	}

	private JMenuItem dameCargarMatrizA() {
		JMenuItem cargaAMenuItem = new JMenuItem("Cargar Matriz A");
		cargaAMenuItem.setEnabled(true);
		cargaAMenuItem.setVisible(true);
		cargaAMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * 
				 * 
				 * 
				 */
				// cargarMenuItem.setEnabled(true);

				/*
				 * 
				 * 
				 */

			}
		});

		return cargaAMenuItem;
	}

	private JMenuItem getDefaultMenuItem() {
		JMenuItem defaultSMenuItem = new JMenuItem("Matrices por defecto");
		defaultSMenuItem.setEnabled(true);
		defaultSMenuItem.setVisible(true);

		defaultSMenuItem.addActionListener(new ActionListener() { // Implementamos
																	// el oyente
					public void actionPerformed(ActionEvent e) {

						panelTabulado.add("Matriz1", damePanelTabla(tm1, pAuxA,tablaA));
						panelTabulado.add("Matriz2", damePanelTabla(tm2, pAuxB,tablaB));
						panelTabulado.add("Resultado", damePanelTabla(tm3, pAuxR,tablaR));

						m1 = new Matriz(1);
						m2 = new Matriz(2);
						escribirMatriz(m1, tm1);
						escribirMatriz(m2, tm2);
						// escribirMatrizB(m2);
						operacionesPermitidas = true;

						barraPrincipal.validate();

					}
				});

		defaultSMenuItem.validate();
		return defaultSMenuItem;
	}

	private void escribirMatriz(Matriz m, DefaultTableModel tm) {

		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				tm.setValueAt(m.getIJ(i, j), i, j);

	}

	/*
	 * private void escribirMatrizB(Matriz m){
	 * 
	 * for (int i = 0; i < filas; i++) for (int j = 0; j < columnas; j++)
	 * tm2.setValueAt(m.getIJ(i, j), i, j);
	 * 
	 * }
	 */

	private JMenu getMultiplicacionMenuItem() {

		JMenu multiplicarMenu = new JMenu("Multiplicar");
		multiplicarMenu.setEnabled(true);
		multiplicarMenu.setVisible(true);
		multiplicarMenu.add(dameMultiplicaNormal());
		multiplicarMenu.add(dameMultiplicaStrassen());
		return multiplicarMenu;
	}

	private JMenuItem dameMultiplicaStrassen() {
		JMenuItem multiplicaSMenuItem = new JMenuItem("Multiplicacion Strassen");
		multiplicaSMenuItem.setEnabled(false);
		multiplicaSMenuItem.setVisible(true);
		multiplicaSMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * 
				 * 
				 * 
				 */
				// cargarMenuItem.setEnabled(true);

				/*
				 * 
				 * 
				 */

			}
		});

		return multiplicaSMenuItem;
	}

	private JMenuItem dameMultiplicaNormal() {
		JMenuItem multiplicaMenuItem = new JMenuItem("Multiplicacion Normal");
		// multiplicaMenuItem.setEnabled(false);
		multiplicaMenuItem.setVisible(true);
		multiplicaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Matriz m1 = convertirEnMatriz(tm1);
				Matriz m2 = convertirEnMatriz(tm2);
				Matriz m3 = new Matriz(filas, columnas);
				resultado = m3.multiplica(m1, m2);
				muestraResultado(resultado);

			}
		});

		return multiplicaMenuItem;
	}

	private JMenuItem getRestaMenuItem() {

		JMenuItem restarMenuItem = new JMenuItem("Restar");
		// restarMenuItem.setEnabled(false);
		restarMenuItem.setVisible(true);
		restarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Matriz m1 = convertirEnMatriz(tm1);
				Matriz m2 = convertirEnMatriz(tm2);
				Matriz m3 = new Matriz(filas, columnas);
				resultado = m3.resta(m1, m2);
				muestraResultado(resultado);

			}
		});
		return restarMenuItem;
	}

	private JMenuItem getSumaMenuItem() {

		JMenuItem sumarMenuItem = new JMenuItem("Sumar");
		// sumarMenuItem.setEnabled(false);
		sumarMenuItem.setVisible(true);
		sumarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Matriz m1 = convertirEnMatriz(tm1);
				Matriz m2 = convertirEnMatriz(tm2);
				Matriz m3 = new Matriz(filas, columnas);
				resultado = m3.suma(m1, m2);
				muestraResultado(resultado);

			}
		});
		return sumarMenuItem;
	}

	private void muestraResultado(Matriz m) {
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				tm3.setValueAt(m.getIJ(i, j), i, j);
	}

	private Matriz convertirEnMatriz(DefaultTableModel tm) {
		Matriz resultado = new Matriz(filas, columnas);
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				resultado.setIJ(i, j, (Integer) tm.getValueAt(i, j));

		return resultado;
	}

	private JMenuItem getInicializarMenuItem() {

		JMenuItem inicializarMenuItem = new JMenuItem("Inicializar");
		// inicializarMenuItem.setEnabled(false);
		inicializarMenuItem.setVisible(true);
		inicializarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				inicializarMatrices(tm1);
				inicializarMatrices(tm2);
				inicializarMatrices(tm3);

			}
		});
		return inicializarMenuItem;
	}

	private JMenuItem getCargarMenuItem() {

		JMenuItem cargarMenuItem = new JMenuItem("Cargar Tablas");
		cargarMenuItem.setEnabled(false);
		cargarMenuItem.setVisible(true);
		cargarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * 
				 * 
				 * 
				 */
				// cargarMenuItem.setEnabled(true);

				/*
				 * 
				 * 
				 */

			}
		});
		return cargarMenuItem;
	}

	private JMenuItem getTamanyoMenuItem() {
		JMenuItem tamanyoItem = new JMenuItem("Fijar Tama�o");
		tamanyoItem.setEnabled(true);
		tamanyoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (panelDeMatrices != null) {
					ventanaFijarTamanyo.remove(panelDeMatrices);
					ventanaFijarTamanyo.validate();
				}
				ventanaFijarTamanyo = new JFrame();
				ventanaFijarTamanyo.setVisible(true);
				ventanaFijarTamanyo.setEnabled(true);
				ventanaFijarTamanyo.setSize(300, 100);

				panelDeMatrices = damePanelTamanyo();
				ventanaFijarTamanyo.setContentPane(panelDeMatrices);
				/*
				 * 
				 * 
				 * 
				 */
				// tamanyoItem.setEnabled(true);

				/*
				 * 
				 * crear el panel tabulado
				 */

			}
		});
		return tamanyoItem;
	}

	private JPanel damePanelTamanyo() {
		JPanel panelTamanyo = new JPanel();
		panelTamanyo.setLayout(new BorderLayout());
		panelTamanyo.add("North", new JLabel(
				"Introduce las filas y las columnas"));
		JPanel panelCentro = new JPanel();
		panelTamanyo.add("Center", panelCentro);

		JLabel lab1 = new JLabel("Filas");
		panelCentro.add(lab1);
		botonFil = new JTextField();
		// f.setSize(50, 10);
		botonFil.setEnabled(true);
		botonFil.setVisible(true);
		// f.setEditable(true);
		botonFil.setColumns(2);
		botonFil.validate();
		panelCentro.add(botonFil);

		JLabel lab2 = new JLabel("Columnas");
		panelCentro.add(lab2);
		botonCol = new JTextField();
		// f.setSize(50, 10);
		botonCol.setEnabled(true);
		botonCol.setVisible(true);
		// f.setEditable(true);
		botonCol.setColumns(2);
		botonCol.validate();
		panelCentro.add(botonCol);

		JButton botonAceptar = new JButton("Aceptar");
		panelCentro.add(botonAceptar);
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = botonFil.getText();
				String s2 = botonCol.getText();

				// Recordar validar los enteros!!!!

				if ((s1 != "") && (s2 != "")) {
					try {
						filas = Integer.parseInt(s1);
						columnas = Integer.parseInt(s2);
					} catch (NumberFormatException ex) {
						if (ex != null) {
							JOptionPane.showMessageDialog(null,
									"AVISO: TIENES QUE INTRODUCIR NUMEROS!");
						}
					}

					if (filas > 0 && filas <= 20 && columnas > 0
							&& columnas <= 20) {
						m1 = new Matriz(filas, columnas);
						m2 = new Matriz(filas, columnas);
						resultado = new Matriz(filas, columnas);


						panelTabulado.add("Matriz1", damePanelTabla(tm1, pAuxA, tablaA));
						panelTabulado.add("Matriz2", damePanelTabla(tm2, pAuxB, tablaB));
						panelTabulado.add("Resultado", damePanelTabla(tm3, pAuxR, tablaR));
						
					} else
						JOptionPane
								.showMessageDialog(null,
										"AVISO: Filas o columnas fuera de rango, solo entre 1 y 20!");

				} else
					JOptionPane.showMessageDialog(null,
							"AVISO: Introducir filas y columnas!");

			}

		});

		panelTamanyo.validate();
		return panelTamanyo;

	}

	private JPanel damePanelTabla(DefaultTableModel tm, JPanel p, JTable t) {
		
		p.setLayout(new BorderLayout());
		
		for (int i=0; i<tm.getRowCount();i++)
			tm.removeRow(i);
		
		for (int i = 0; i < columnas; i++)
			tm.addColumn("i");
		
		for (int i = 0; i < filas; i++)
			tm.addRow(new String[columnas]);
		
	

	    //tabla = new JTable(tm);
		p.add("Center", t);
		inicializarMatrices(tm);
		p.validate();
		return p;
	}

	private void inicializarMatrices(DefaultTableModel tm) {
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
				tm.setValueAt(0, i, j);
	}

	public static void main(String[] args) {

		ClasePrincipal f = new ClasePrincipal();

	}

}
