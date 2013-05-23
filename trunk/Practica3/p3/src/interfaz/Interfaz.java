package interfaz;

import grafo.Grafo;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import grafo.Arco;
import grafo.Nodo;



import interfaz.TableroCanvas;

public class Interfaz extends JFrame implements ActionListener {
	Grafo grafo;
	boolean borrarNodoBool;
	boolean borrarArcoBool;
	boolean crearNodoBool;
	boolean crearArcoBool;
	boolean dijkstra;
	boolean kruskal;
	boolean floyd;
	boolean repintar;
	private String nombreNodo;
	private int pesoArco;
	private TableroCanvas tablero;
	private String fich;
	
//Funciones de inicializacion y creacion de la interfaz	
	public static void main(String args[]){
		Interfaz o = new Interfaz();
		o.setVisible(true);
		o.setEnabled(true);
		o.setSize(1000,800);
	}
	
	public Interfaz(){
		grafo = new Grafo();
		inicializarInterfaz();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Para que se termine el hilo al cerrar la ventana

	}
		
	public void inicializarInterfaz(){		
		this.setTitle("Grafos");
		this.setJMenuBar(getMenuPrincipal()); //funcion que devuelva un JMenuBar
		this.setContentPane(getPanelCanvas());  //devuelve un Canvas
	}

	private JMenuBar getMenuPrincipal(){
		JMenuBar barraMenu = new JMenuBar();
		barraMenu.add(getMenuGrafo());  // Colocar las pestanyas de la ventana
		barraMenu.add(getMenuArchivo());
		barraMenu.add(getMenuOperaciones()); 
		return barraMenu;
	}
	
	public JPanel getPanelCanvas(){
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		tablero = new TableroCanvas(grafo,this);
		tablero.addMouseListener(tablero);
		panel.add(tablero);
		return panel;
		}
	
	private JMenu getMenuGrafo(){
		JMenu menuGrafo = new JMenu();
		menuGrafo.setText("Grafo");
		menuGrafo.add(getCrearArcoItem());
		menuGrafo.add(getCrearNodoItem());
		menuGrafo.add(getBorrarArcoItem());
		menuGrafo.add(getBorrarNodoItem());
		return menuGrafo;
	}
	
	private JMenu getMenuArchivo(){
		JMenu menuArchivo = new JMenu();
		menuArchivo.setText("Archivo");
		menuArchivo.add(getGuardarItem());
		menuArchivo.add(getCargarItem());
		return menuArchivo;
	}
	
	private JMenu getMenuOperaciones(){
		JMenu menuOperaciones = new JMenu();
		menuOperaciones.setText("Operaciones");
		menuOperaciones.add(getDijkstraItem());
		menuOperaciones.add(getFloydItem());
		menuOperaciones.add(getKruskalItem());
		return menuOperaciones;
	}
	
	private JMenuItem getCrearArcoItem() {
		JMenuItem nuevoArco = new JMenuItem();
		nuevoArco.setText("Introducir arista");
		nuevoArco.setEnabled(true);
		nuevoArco.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				borrarNodoBool = false;
				borrarArcoBool = false;
				crearNodoBool = false;
				crearArcoBool = true;
				repintar = false;
				dijkstra = false;
				floyd = false;
				kruskal = false;
				String i = JOptionPane.showInputDialog(null, "Peso de la arista");
				if (i!= null) pesoArco= Integer.parseInt((String) i);
				else pesoArco = 0;
				JOptionPane.showMessageDialog(null,"Selecciona el vertice origen y el vertice destino para crear la arista");
				}
		});	
		return nuevoArco;
		}
	
	private JMenuItem getCrearNodoItem() {
		JMenuItem nuevoNodo = new JMenuItem();
		nuevoNodo.setText("Introducir vertice");
		nuevoNodo.setEnabled(true);
		nuevoNodo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				borrarNodoBool = false;
				borrarArcoBool = false;
				crearNodoBool = true;
				crearArcoBool = false;
				dijkstra = false;
				floyd = false;
				kruskal = false;
				repintar = false;
				nombreNodo = JOptionPane.showInputDialog(null, "Nombre del vertice");
		}
	});	
	return nuevoNodo;
	}
	
	private JMenuItem getBorrarArcoItem() {
		JMenuItem borrarArco = new JMenuItem();
		borrarArco.setText("Borrar arista");
		borrarArco.setEnabled(true);
		borrarArco.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				borrarNodoBool = false;
				borrarArcoBool = true;
				crearNodoBool = false;
				repintar = false;
				crearArcoBool = false;
				dijkstra = false;
				floyd = false;
				kruskal = false;
				JOptionPane.showMessageDialog(null,"Selecciona el vertice origen y el vertice destino para borrar la arista");
		}
	});	
	return borrarArco;
	}
	
	private JMenuItem getBorrarNodoItem() {
		JMenuItem borrarNodo = new JMenuItem();
		borrarNodo.setText("Borrar vertice");
		borrarNodo.setEnabled(true);
		borrarNodo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				borrarNodoBool = true;
				borrarArcoBool = false;
				crearNodoBool = false;
				crearArcoBool = false;
				dijkstra = false;
				floyd = false;
				kruskal = false;
				repintar = false;
				JOptionPane.showMessageDialog(null,"Selecciona el vertice para eliminarlo");
		}
	});	
	return borrarNodo;
	}
	
	private JMenuItem getGuardarItem() {
		JMenuItem guardar = new JMenuItem();
		guardar.setText("Guardar grafo");
		guardar.setEnabled(true);
		guardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				guardarFichero();
		}
	});	
	return guardar;
	}
	
	private JMenuItem getCargarItem() {
		JMenuItem cargar = new JMenuItem();
		cargar.setText("Cargar grafo");
		cargar.setEnabled(true);
		cargar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
				JFileChooser selectFich = new JFileChooser();
				int i = selectFich.showOpenDialog(Interfaz.this);
				if (i == 0){
				fich = selectFich.getSelectedFile().getAbsolutePath();	
				File f = new File(fich);
				grafo.inicializar(f);
				}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				tablero.pintar();
		}
	});	
	return cargar;
	}
	
	private JMenuItem getDijkstraItem() {
		JMenuItem dijkstras = new JMenuItem();
		dijkstras.setText("Dijkstra");
		dijkstras.setEnabled(true);
		dijkstras.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dijkstra = true;
				borrarNodoBool = false;
				borrarArcoBool = false;
				crearNodoBool = false;
				crearArcoBool = false;
				floyd = false;
				kruskal = false;
				repintar = false;
				JOptionPane.showMessageDialog(null,"Selecciona el vertice origen y destino");
		}
	});	
	return dijkstras;
	}
	
	private JMenuItem getFloydItem() {
		JMenuItem floyds = new JMenuItem();
		floyds.setText("Floyd");
		floyds.setEnabled(true);
		floyds.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dijkstra = false;
				borrarNodoBool = false;
				borrarArcoBool = false;
				crearNodoBool = false;
				crearArcoBool = false;
				floyd = true;
				kruskal = false;
				repintar = false;
				JOptionPane.showMessageDialog(null,"Selecciona el vertice origen y destino");
		}
	});	
	return floyds;
	}
	
	private JMenuItem getKruskalItem() {
		JMenuItem kruskals = new JMenuItem();
		kruskals.setText("Kruskal");
		kruskals.setEnabled(true);
		kruskals.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dijkstra = false;
				borrarNodoBool = false;
				borrarArcoBool = false;
				crearNodoBool = false;
				crearArcoBool = false;
				floyd = false;
				kruskal = true;
				repintar = false;
				JOptionPane.showMessageDialog(null,"Toca la pantalla para comenzar");
		}
	});	
	return kruskals;
	}
	
//Funciones de acceso y modificacion de variables


	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}

	public boolean isBorrarNodoBool() {
		return borrarNodoBool;
	}

	public void setBorrarNodoBool(boolean borrarNodoBool) {
		this.borrarNodoBool = borrarNodoBool;
	}

	public boolean isBorrarArcoBool() {
		return borrarArcoBool;
	}

	public void setBorrarArcoBool(boolean borrarArcoBool) {
		this.borrarArcoBool = borrarArcoBool;
	}

	public boolean isCrearNodoBool() {
		return crearNodoBool;
	}

	public void setCrearNodoBool(boolean crearNodoBool) {
		this.crearNodoBool = crearNodoBool;
	}

	public boolean isCrearArcoBool() {
		return crearArcoBool;
	}

	public void setCrearArcoBool(boolean crearArcoBool) {
		this.crearArcoBool = crearArcoBool;
	}

	public String getNombreNodo() {
		return nombreNodo;
	}

	public void setNombreNodo(String nombreNodo) {
		this.nombreNodo = nombreNodo;
	}

	public int getPesoArco() {
		return pesoArco;
	}

	public void setPesoArco(int pesoArco) {
		this.pesoArco = pesoArco;
	}
	
	public boolean isRepintar() {
		return repintar;
	}

	public void setRepintar(boolean repintar) {
		this.repintar = repintar;
	}

	public boolean isDijkstra() {
		return dijkstra;
	}

	public void setDijkstra(boolean dijkstra) {
		this.dijkstra = dijkstra;
	}

	public boolean isKruskal() {
		return kruskal;
	}

	public void setKruskal(boolean kruskal) {
		this.kruskal = kruskal;
	}

	public boolean isFloyd() {
		return floyd;
	}

	public void setFloyd(boolean floyd) {
		this.floyd = floyd;
	}

	//Funciones de cargar y guardar
	public void nombreFicheroCargar() {
		JFileChooser selectFich = new JFileChooser();
		int i = selectFich.showOpenDialog(Interfaz.this);
		if (i == 0)
			fich = selectFich.getSelectedFile().getAbsolutePath();
	}
	
	public void nombreFicheroGuardar(){
		JFileChooser selectFich = new JFileChooser();
		int i = selectFich.showSaveDialog(Interfaz.this);
		if (i == 0)
			fich = selectFich.getSelectedFile().getAbsolutePath();
	}
	
	public void guardarFichero(){
		JFileChooser selectFich = new JFileChooser();
		int i = selectFich.showSaveDialog(Interfaz.this);
		if (i == 0){
			fich = selectFich.getSelectedFile().getAbsolutePath();
		File file = new File(fich);
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter writer = new PrintWriter(bw);
			Iterator<Nodo> iterator1 = grafo.iteratorNodos();
			Iterator<Arco> iterator2 = grafo.iteratorArcos();
			Nodo aux=null;
			Arco aux2=null;
			while (iterator1.hasNext()){
				aux= iterator1.next();
				writer.print(aux.getNombre()+" ");
				writer.print(Integer.toString(aux.getX())+" ");
				writer.print(Integer.toString(aux.getY())+" ");
			}
			writer.println("");
			while (iterator2.hasNext()){
				aux2= iterator2.next();
				writer.print(aux2.getOrigen().getNombre()+" ");
				writer.print(aux2.getDestino().getNombre()+" ");
				writer.println(Integer.toString(aux2.getPeso()));
			}
			writer.close();
			} catch (IOException e) {
			System.out.println("Error al guardar el dato");
			}		
		}		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
