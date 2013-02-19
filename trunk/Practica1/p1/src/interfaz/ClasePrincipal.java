package interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClasePrincipal extends JFrame {

	/***********************
	 * ATRIBUTOS PRIVADOS
	 * 
	 ***********************/
	
	JPanel panelPrincipal;
	
	JTabbedPane panelTabulado;
	
	int filas;
	int columnas;
	
	DefaultTableModel tm1;
	DefaultTableModel tm2;
	DefaultTableModel tm3;
	
	/************************************************/
	
	public ClasePrincipal(){
		
		//INICIALIZAR VARIABLES
		filas = 0;
		columnas = 0;
		tm1 = new DefaultTableModel();
		tm2 = new DefaultTableModel();
		tm3 = new DefaultTableModel();
		
		
		JOptionPane.showMessageDialog(null,"AVISO: TIENE QUE INTRODUCIR EL TAMANYO DE LA MATRIZ!");
		inicializarInterfaz();
		
	}
	
	
	public void inicializarInterfaz(){
		
		this.setTitle("CALCULADORA DE MATRICES");
		this.setVisible(true);
		this.setEnabled(true);
		this.setSize(500,500);
		this.setJMenuBar(dameBarraMenu()); //crea la barra de menu y la adjunta al jframe
		this.setContentPane(damePanelPrincipal()); //sirve para el contenido del jframe
		this.validate();
			
	}
	
	private JPanel damePanelPrincipal(){
	
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		panelTabulado = new JTabbedPane();
		panelPrincipal.add("Center",panelTabulado);
		panelPrincipal.validate();
		return panelPrincipal;
	}
	
	private JMenuBar dameBarraMenu(){
		
		JMenuBar barra = new JMenuBar();
		barra.add(getMenuMatrices());
		barra.add(getMenuOperar());
		barra.add(getMenuFicheros());
		barra.setVisible(true);
		barra.setEnabled(true);
		return barra;
		
	}
	
	private JMenu getMenuMatrices(){
		
		JMenu matricesMenu = new JMenu("Matrices");
		matricesMenu.add(getTamanyoMenuItem());
		matricesMenu.add(getCargarMenuItem());
		matricesMenu.add(getInicializarMenuItem());
		return matricesMenu;
	}
	
	private JMenu getMenuOperar(){
		
		JMenu operarMenu = new JMenu("Operar");
		operarMenu.add(getSumaMenuItem());
		operarMenu.add(getRestaMenuItem());
		operarMenu.add(getMultiplicacionMenuItem());
		return operarMenu;
	}
	
	
	private JMenu getMenuFicheros(){
		
		JMenu ficherosMenu = new JMenu("Ficheros");
		ficherosMenu.add(getLeerMenuItem());
		ficherosMenu.add(getGuardarMenuItem());
		ficherosMenu.add(getDefaultMenuItem());
		return ficherosMenu;
	}
	
	
	private JMenu getGuardarMenuItem(){
		
		JMenu guardarMenu = new JMenu("Guardar");
		guardarMenu.setEnabled(true);
		guardarMenu.setVisible(true);
		guardarMenu.add(dameGuardarMatrizA());
		guardarMenu.add(dameGuardarMatrizB());
		guardarMenu.add(dameGuardarResultado());
		return guardarMenu;
	}
	
	private JMenu getLeerMenuItem(){
		
		JMenu multiplicarMenu = new JMenu("Cargar");
		multiplicarMenu.setEnabled(true);
		multiplicarMenu.setVisible(true);
		multiplicarMenu.add(dameCargarMatrizA());
		multiplicarMenu.add(dameCargarMatrizB());
		return multiplicarMenu;
	}
	
	
	private JMenuItem dameGuardarResultado(){
		JMenuItem guardarRMenuItem = new JMenuItem("Guardar Matriz Resultado");
		guardarRMenuItem.setEnabled(false);
		guardarRMenuItem.setVisible(true);
		guardarRMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		
		return guardarRMenuItem;
	}
	
	
	private JMenuItem dameGuardarMatrizB(){
		JMenuItem guardarBMenuItem = new JMenuItem("Guardar Matriz B");
		guardarBMenuItem.setEnabled(false);
		guardarBMenuItem.setVisible(true);
		guardarBMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		
		return guardarBMenuItem;
	}
	
	private JMenuItem dameGuardarMatrizA(){
		JMenuItem guardarAMenuItem = new JMenuItem("Guardar Matriz A");
		guardarAMenuItem.setEnabled(false);
		guardarAMenuItem.setVisible(true);
		guardarAMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		
		return guardarAMenuItem;
	}
	
	private JMenuItem dameCargarMatrizB(){
		JMenuItem cargaBMenuItem = new JMenuItem("Cargar Matriz B");
		cargaBMenuItem.setEnabled(false);
		cargaBMenuItem.setVisible(true);
		cargaBMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		
		return cargaBMenuItem;
	}
	
	private JMenuItem dameCargarMatrizA(){
		JMenuItem cargaAMenuItem = new JMenuItem("Cargar Matriz A");
		cargaAMenuItem.setEnabled(false);
		cargaAMenuItem.setVisible(true);
		cargaAMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		
		return cargaAMenuItem;
	}
	
	
	private JMenuItem getDefaultMenuItem(){
		JMenuItem defaultSMenuItem = new JMenuItem("Matrices por defecto");
		defaultSMenuItem.setEnabled(true);
		defaultSMenuItem.setVisible(true);
		defaultSMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		
		return defaultSMenuItem;
	}
	
	private JMenu getMultiplicacionMenuItem(){
		
		JMenu multiplicarMenu = new JMenu("Multiplicar");
		multiplicarMenu.setEnabled(true);
		multiplicarMenu.setVisible(true);
		multiplicarMenu.add(dameMultiplicaNormal());
		multiplicarMenu.add(dameMultiplicaStrassen());
		return multiplicarMenu;
	}
	
	
	private JMenuItem dameMultiplicaStrassen(){
		JMenuItem multiplicaSMenuItem = new JMenuItem("Multiplicacion Strassen");
		multiplicaSMenuItem.setEnabled(false);
		multiplicaSMenuItem.setVisible(true);
		multiplicaSMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		
		return multiplicaSMenuItem;
	}
	
	
	private JMenuItem dameMultiplicaNormal(){
		JMenuItem multiplicaMenuItem = new JMenuItem("Multiplicacion Normal");
		multiplicaMenuItem.setEnabled(false);
		multiplicaMenuItem.setVisible(true);
		multiplicaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		
		return multiplicaMenuItem;
	}
	
	
	private JMenuItem getRestaMenuItem(){
		
		JMenuItem restarMenuItem = new JMenuItem("Restar");
		restarMenuItem.setEnabled(false);
		restarMenuItem.setVisible(true);
		restarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		return restarMenuItem;
	}
	
	
	private JMenuItem getSumaMenuItem(){
		
		JMenuItem sumarMenuItem = new JMenuItem("Sumar");
		sumarMenuItem.setEnabled(false);
		sumarMenuItem.setVisible(true);
		sumarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		return sumarMenuItem;
	}
	
	
	private JMenuItem getInicializarMenuItem(){
		
		JMenuItem inicializarMenuItem = new JMenuItem("Inicializar");
		inicializarMenuItem.setEnabled(false);
		inicializarMenuItem.setVisible(true);
		inicializarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		return inicializarMenuItem;
	}
	
	
	
	private JMenuItem getCargarMenuItem(){
		
		JMenuItem cargarMenuItem = new JMenuItem("Cargar Tablas");
		cargarMenuItem.setEnabled(false);
		cargarMenuItem.setVisible(true);
		cargarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				/*
				 * 
				 * 
				 * 
				 */
			//	cargarMenuItem.setEnabled(true);
				
				/*
				 * 
				 * 
				 */
					
			}
		});
		return cargarMenuItem;
	}
	
	
	private JMenuItem getTamanyoMenuItem(){
		JMenuItem tamanyoItem = new JMenuItem("Fijar Tamanyo");
		tamanyoItem.setEnabled(true);
		tamanyoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JFrame f = new JFrame();
				f.setVisible(true);
				f.setEnabled(true);
				f.setSize(200,200);
		//		f.setContentPane(damePanelTamanyo());
				/*
				 * 
				 * 
				 * 
				 */
			//	tamanyoItem.setEnabled(true);
				
				/*
				 * 
				 * crear el panel tabulado
				 */
					
			}
		});
		return tamanyoItem;
	}
	
	public static void main(String[] args) {
		
		ClasePrincipal f = new ClasePrincipal();

	}

}
