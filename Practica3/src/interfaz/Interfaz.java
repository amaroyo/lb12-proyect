package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class Interfaz extends JFrame {
	private static final long serialVersionUID = 1L;
	/***********************
	 * ATRIBUTOS PRIVADOS
	 * 
	 ***********************/
	
	private JFrame f;
	private JTextField botonNombreVertice;
	private JTextField botonCol;

	private JFrame ventanaNombreVertice;
	
	
	private JMenuItem verticeMenu;
	private JMenuItem aristaMenu;
	private JMenuItem guardarMenuItem;
	private JMenuItem abrirMenuItem;
	private JMenuItem nuevoMenuItem;
	private JMenuItem ejemploMenuItem;
	private JMenuItem dijkstraMenu;
	private JMenuItem kruskalMenu;
	private JMenuItem floydMenu;
	protected JFrame ventanaPesoArista;
	

	/******************************************************************************************************/

	public Interfaz() {
		
		inicializarInterfaz();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Para que se termine el hilo al cerrar la ventana

	}

	/******************************************************************************************************/
	public void inicializarInterfaz() {

		f=this;
		f.setTitle("GRAFOS");
		f.setVisible(true);
		f.setEnabled(true);
		f.setSize(600, 600);
		f.setJMenuBar(dameBarraMenu()); // crea la barra de menu y la adjunta al jframe
		f.validate();

	}
	

	/******************************************************************************************************/
	private JMenuBar dameBarraMenu() {

		JMenuBar barraPrincipal = new JMenuBar();
		barraPrincipal.add(getMenuGrafo());
		barraPrincipal.add(getMenuArchivo());
		barraPrincipal.add(getMenuOperaciones());
		barraPrincipal.setVisible(true);
		barraPrincipal.setEnabled(true);
		return barraPrincipal;

	}

	/******************************************************************************************************/
	private JMenu getMenuArchivo() {

		JMenu archivoMenu = new JMenu("Archivo");
		archivoMenu.add(getAbrirMenuItem());
		archivoMenu.add(getGuardarMenuItem());
		archivoMenu.setVisible(true);
		archivoMenu.setEnabled(true);
		return archivoMenu;
	}

	/******************************************************************************************************/
	private JMenu getMenuGrafo() {

		JMenu grafoMenu = new JMenu("Grafo");
		grafoMenu.add(getVerticeMenu());
		grafoMenu.add(getAristaMenu());
		grafoMenu.setVisible(true);
		grafoMenu.setEnabled(true);
		
		return grafoMenu;
	}
	/******************************************************************************************************/
	private JMenu getMenuOperaciones() {

		JMenu operacionesMenu = new JMenu("Operaciones");
		operacionesMenu.add(getDijkstraMenu());
		operacionesMenu.add(getFloydMenu());
		operacionesMenu.add(getKruskalMenu());
		operacionesMenu.setVisible(true);
		operacionesMenu.setEnabled(true);
		return operacionesMenu;
	}
	
	/******************************************************************************************************/	
	private JMenuItem getDijkstraMenu() {		
	
		dijkstraMenu= new JMenuItem("Dijkstra");
		dijkstraMenu.setEnabled(true);
		dijkstraMenu.setVisible(true);
		
		dijkstraMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Completar Dijkstra
			}
		});
		return dijkstraMenu;
	}
    /******************************************************************************************************/
	private JMenuItem getKruskalMenu() {
		kruskalMenu= new JMenuItem("Kruskal");
		kruskalMenu.setEnabled(true);
		kruskalMenu.setVisible(true);
		
		kruskalMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Completar Kruskal
			}
		});
		return kruskalMenu;
	}

	/******************************************************************************************************/
	private JMenuItem getFloydMenu() {
		floydMenu= new JMenuItem("Floyd");
		floydMenu.setEnabled(true);
		floydMenu.setVisible(true);
		
		floydMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Completar Floyd
			}
		});
		return floydMenu;
	}

	
	/******************************************************************************************************/
	private JMenuItem getVerticeMenu() {

		verticeMenu = new JMenuItem("Vertice");
		verticeMenu.setEnabled(true);
		verticeMenu.setVisible(true);
		verticeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaNombreVertice = new JFrame();
				ventanaNombreVertice.setVisible(true);
				ventanaNombreVertice.setEnabled(true);
				ventanaNombreVertice.setSize(300, 100);
				ventanaNombreVertice.setContentPane(dameNombreVertice());
			}
			
		});
		return verticeMenu;
	}
	
	/******************************************************************************************************/
	protected JPanel dameNombreVertice() {
		JPanel panelVertice = new JPanel();
		panelVertice.setLayout(new BorderLayout());
		panelVertice.add("North", new JLabel("Introduce el nombre para el vértice seleccionado"));
		JPanel panelCentro = new JPanel();
		panelVertice.add("Center", panelCentro);

		JLabel lab1 = new JLabel("Nombre");
		panelCentro.add(lab1);
		botonNombreVertice = new JTextField();
		botonNombreVertice.setEnabled(true);
		botonNombreVertice.setVisible(true);
		botonNombreVertice.setColumns(2);
		botonNombreVertice.validate();
		panelCentro.add(botonNombreVertice);

		JButton botonAceptar = new JButton("Aceptar");
		panelCentro.add(botonAceptar);
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//Completar pilla el vertice
				ventanaNombreVertice.setAlwaysOnTop(false);
				ventanaNombreVertice.setVisible(false);
				ventanaNombreVertice.setEnabled(false);
				ventanaNombreVertice.dispose();
				ventanaNombreVertice=null;
			}
		});
		return panelVertice;
	}
	
	/******************************************************************************************************/
	private JMenuItem getAristaMenu() {

		aristaMenu = new JMenuItem("Arista");
		aristaMenu.setEnabled(true);
		aristaMenu.setVisible(true);
		aristaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaPesoArista = new JFrame();
				ventanaPesoArista.setVisible(true);
				ventanaPesoArista.setEnabled(true);
				ventanaPesoArista.setSize(300, 100);
				ventanaPesoArista.setContentPane(damePesoArista());
			}
		});
	
		return aristaMenu;
	}
	
	/******************************************************************************************************/
	protected JPanel damePesoArista() {
		JPanel panelArista = new JPanel();
		panelArista.setLayout(new BorderLayout());
		panelArista.add("North", new JLabel("Introduce el peso para la arista seleccionada"));
		JPanel panelCentro = new JPanel();
		panelArista.add("Center", panelCentro);

		JLabel lab1 = new JLabel("Nombre");
		panelCentro.add(lab1);
		botonNombreVertice = new JTextField();
		botonNombreVertice.setEnabled(true);
		botonNombreVertice.setVisible(true);
		botonNombreVertice.setColumns(2);
		botonNombreVertice.validate();
		panelCentro.add(botonNombreVertice);

		JButton botonAceptar = new JButton("Aceptar");
		panelCentro.add(botonAceptar);
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//Completar pillar la arista
				ventanaPesoArista.setAlwaysOnTop(false);
				ventanaPesoArista.setVisible(false);
				ventanaPesoArista.setEnabled(false);
				ventanaPesoArista.dispose();
				ventanaPesoArista=null;
			}
		});
		return panelArista;
		
	}
	
	/******************************************************************************************************/
	private void modificarFrame(int filas, int columnas) {
		
		/*	f.dispose();
			f = new JFrame();
			canvas = new TableroCanvas(filas,columnas);
			JPanel pt = new JPanel();
			pt.setLayout(new BorderLayout());
			if ((filas>10) ||(columnas>10)) {
				canvas.setTamCelda(25);
				canvas.setPosStringX(7);
				canvas.setPosStringY(20);
			}
			pt.add(canvas);
			pt.setVisible(true);
			f.setContentPane(pt);
			f.setJMenuBar(dameBarraMenu());
			if (columnas<5){
				f.setSize(500, 60+2*canvas.getK()+canvas.getMax_rest_col()*canvas.getTamCelda()+canvas.getNfilas()*canvas.getTamCelda());
			}
			else{
				f.setSize(15 + 2*canvas.getK() + canvas.getMax_rest_fil()*canvas.getTamCelda() + canvas.getNcols()*canvas.getTamCelda(),
					60 + 2*canvas.getK() + canvas.getMax_rest_col()*canvas.getTamCelda() + canvas.getNfilas()*canvas.getTamCelda());
			}
			f.setVisible(true);
			canvas.addMouseListener(canvas);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		
	}
	
	/******************************************************************************************************/
	
    private void procesalinea(String s, int fila, boolean b){
            
            
    /*        int posicionC = canvas.getMax_rest_col()-1;
            int posicionF = canvas.getMax_rest_fil()-1;
            
            String num = "";
            
            char[] array = s.toCharArray();
            int i = array.length-1;
            
            while (i>=0 ){
                    
                    if(array[i] == '-'){
                            throw new NumberFormatException();
                    }
                    else if((array[i] == ' ')||(array[i]=='0')){
                            i--;
                    }
                    else{
                            num += array[i];
                            int numero = Integer.parseInt(num);
                            
                            if (b) {
                                            restricciones_filas[fila][posicionF] = numero;
                                            canvas.setRestF(restricciones_filas);
                                            posicionF--;
                            }
                            else {
                                    restricciones_cols[fila][posicionC] = numero;
                                    canvas.setRestC(restricciones_cols);
                                    posicionC--;
                            }
                            num = "";
                            
                            i--;
                    }
            }*/
            
    }
    /******************************************************************************************************/
	private JMenuItem getGuardarMenuItem() {

		guardarMenuItem = new JMenuItem("Guardar");
		guardarMenuItem.setEnabled(true);
		guardarMenuItem.setVisible(true);
		
		guardarMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				/*		JFileChooser fileChooser = new JFileChooser();
	            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.TXT", "txt","TXT"));//filtro para ver solo archivos .txt
	            int seleccion = fileChooser.showSaveDialog(null);
	            try{
	                if (seleccion == JFileChooser.APPROVE_OPTION){//comprueba si ha presionado el boton de aceptar
	                    File JFC = fileChooser.getSelectedFile();
	                    String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar
	                    PrintWriter printwriter = new PrintWriter(JFC);
	                    
	                    printwriter.println(filas);
	                    printwriter.println(columnas);
	                    
	                    printwriter.println("Filas");
	                    for (int i = 0; i < filas; i++){
	                    	for (int j = 0; j < canvas.getMax_rest_fil(); j++){
	                    		if (restricciones_filas[i][j]!=0) {
	                    			printwriter.print(restricciones_filas[i][j]);
	                    			printwriter.print(" ");
	                    			}
	                    	}
	                    	printwriter.println();
	                    }
	                    
	                    printwriter.println("Columnas");
	                    for (int i = 0; i < filas; i++){
	                    	for (int j = 0; j < canvas.getMax_rest_col(); j++){
	                    		if (restricciones_cols[i][j]!=0) {
	                    			printwriter.print(restricciones_cols[i][j]);
	                    			printwriter.print(" ");
	                    			}
	                    	}
	                    	printwriter.println();
	                    }
	                    
	                    
	                    printwriter.close();//cierra el archivo
	                    
	                    //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
	                    if(!(PATH.endsWith(".txt"))){
	                        File temp = new File(PATH+".txt");
	                        JFC.renameTo(temp);//renombramos el archivo
	                    }
	                    
	                    JOptionPane.showMessageDialog(null,"Guardado con exito!", "Guardado con exito!", JOptionPane.INFORMATION_MESSAGE);
	                }
	            }catch (Exception ex){//por alguna excepcion salta un mensaje de error
	                JOptionPane.showMessageDialog(null,"Error al guardar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
	            }*/
	        		
				
			}
			
		});

		
		return guardarMenuItem;
	}

	/******************************************************************************************************/
	private JMenuItem getAbrirMenuItem() {

		abrirMenuItem = new JMenuItem("Abrir");
		abrirMenuItem.setEnabled(true);
		abrirMenuItem.setVisible(true);
	
		abrirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				/*			String path="";

							//empezamos implementando la clase JFileChooser para abrir archivos
					        JFileChooser JFC = new JFileChooser();
					        //filtro que muestra solo los archivos con extension *.edu
					        JFC.setFileFilter(new FileNameExtensionFilter("todos los archivos *.TXT", "txt","TXT"));
					        //se comprueba si se ha dado al boton aceptar
					        int abrir = JFC.showDialog(null, "Abrir");
					        if (abrir == JFileChooser.APPROVE_OPTION) {
					            FileReader FR = null;
					            //BufferedReader BR = null;
					            Scanner sc = null;
					           

					            try {
					                //abro el fichero y creo un BufferedReader para hacer
					                //una lectura comoda (tener el metodo readLine();)
					                File archivo = JFC.getSelectedFile();//abre un archivo .lengf
					                
					                //evitar abrir archivo con otra extension que no sea *.LFP
					                String PATH = JFC.getSelectedFile().getAbsolutePath();
					                if(PATH.endsWith(".txt")||PATH.endsWith(".TXT")){
					                    
					                    FR = new FileReader(archivo);
					                   // BR = new BufferedReader(FR);
					                    sc = new Scanner(FR);
					                    
					                    //leyendo el archivo
					                   String s = "";
					                  
					                   int posLinea=0;
					                   boolean esLinea=false;
					                   boolean primeraVez = false;
					                    if(path.compareTo(archivo.getAbsolutePath())==0){
					                        JOptionPane.showMessageDialog(null, "Archivo Abierto","Oops! Error", JOptionPane.ERROR_MESSAGE);
					                    }
					                    else{
					                        path = archivo.getAbsolutePath();
					                        try{
					                        	s = sc.nextLine();
					                        	filas = Integer.parseInt(s);
					                        	s = sc.nextLine();
					                        	columnas = Integer.parseInt(s);
					                        	
					        					if (filas > 0 && filas <= 20 && columnas > 0
					        							&& columnas <= 20) {
					        						modificarFrame(filas,columnas);
					        						restricciones_filas = new int[filas][canvas.getMax_rest_fil()]; 
					        						restricciones_cols = new int[columnas][canvas.getMax_rest_col()];
					        						
					        					}
					                        
					                        	while(sc.hasNext()){
					                        		s = sc.nextLine();
					                        		if(s.equals("Filas")) {
					                        			s=sc.nextLine();
					                        			esLinea = true;
					                        			primeraVez = true;
					                        			posLinea=0;
					                        		}
					                        		else if (!primeraVez) throw new NumberFormatException();
					                        		
					                        		if(s.equals("Columnas")){
					                        			s=sc.nextLine();
					                        			posLinea=0;
					                        			esLinea = false;
					                        		}
					                        		procesalinea(s,posLinea,esLinea);
					                        		posLinea++;
					                        	}	
					                        	
					                        	 verticeMenu.setEnabled(true);
					        			         primeraMenuItem.setEnabled(true);
					        			         guardarMenuItem.setEnabled(true);
					        			         conForzado.setEnabled(true);
					        			         sinForzado.setEnabled(true);
					                        }
					                        
					                       catch (NumberFormatException ex) {
					    						if (ex != null) {
					    							JOptionPane.showMessageDialog(null, "AVISO: LECTURA INCORRECTA!");
					    						}
					    					}
					                    }
					                    
					                }else{
					                    JOptionPane.showMessageDialog(null, "Archivo no soportado","Oops! Error", JOptionPane.ERROR_MESSAGE);
					                    //open();
					                }

					            } catch (FileNotFoundException ex) {
					                ex.printStackTrace();
					                
					            //cerramos el fichero, para asegurar que se cierra tanto
					            // si todo va bien si salta una excepcion
					            } finally {
					                try {
					                    if(null!= FR){
					                        FR.close();
					                    }

					                } catch (IOException ex) {
					                    ex.printStackTrace();
					                
					                }
					            }
					        }*/
						}
					});

		return abrirMenuItem;
	}
	
    /******************************************************************************************************/
    public static void main(String[] args) {

		Interfaz i = new Interfaz();

	}
	

}

