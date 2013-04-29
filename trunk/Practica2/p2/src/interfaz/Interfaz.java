package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import nonograma.Nonograma;


public class Interfaz extends JFrame {

	/***********************
	 * ATRIBUTOS PRIVADOS
	 * 
	 ***********************/

	
	private int restricciones_filas[][];
	private int restricciones_cols[][];
	private int miCont = 0;
	
	private Nonograma n;
	
	private TableroCanvas canvas;
	private JFrame f;

	private int filas;
	private int columnas;

	private JTextField botonFil;
	private JTextField botonCol;
	private String ejemElegido ="prueba";

	private JFrame ventanaFijarTamanyo;
	
	
	private JMenu directoMenu;
	private JMenuItem inversoMenuItem;
	private JMenuItem guardarMenuItem;
	private JMenuItem abrirMenuItem;
	private JMenuItem nuevoMenuItem;
	private JMenuItem ejemploMenuItem;
	private JMenuItem infoNonograma;
	private JFrame ventanaElegirEjemplo;
	private JComboBox ejemplos;
	private JMenuItem conForzado;
	private JMenuItem sinForzado;
	protected boolean forzado=false;
	private JMenuItem primeraMenuItem;
	private JMenuItem siguienteMenuItem;
	

	/******************************************************************************************************/

	public Interfaz() {
		
		inicializarInterfaz();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Para que se termine el hilo al cerrar la ventana

	}

	/******************************************************************************************************/
	public void inicializarInterfaz() {

		f=this;
		f.setTitle("NONOGRAMA");
		f.setVisible(true);
		f.setEnabled(true);
		f.setSize(600, 600);
		f.setJMenuBar(dameBarraMenu()); // crea la barra de menu y la adjunta al jframe
		f.validate();

	}
	

	/******************************************************************************************************/
	private JMenuBar dameBarraMenu() {

		JMenuBar barraPrincipal = new JMenuBar();
		barraPrincipal.add(getMenuFichero());
		barraPrincipal.add(getMenuNonograma());
		barraPrincipal.add(getMenuAyuda());
		barraPrincipal.setVisible(true);
		barraPrincipal.setEnabled(true);
		return barraPrincipal;

	}

	/******************************************************************************************************/
	private JMenu getMenuFichero() {

		JMenu ficheroMenu = new JMenu("Fichero");
		ficheroMenu.add(getEjemplosMenuItem());
		ficheroMenu.add(getNuevoMenuItem());
		ficheroMenu.add(getAbrirMenuItem());
		ficheroMenu.add(getGuardarMenuItem());
		ficheroMenu.setVisible(true);
		ficheroMenu.setEnabled(true);
		return ficheroMenu;
	}

	/******************************************************************************************************/
	private JMenu getMenuNonograma() {

		JMenu nonogramaMenu = new JMenu("Nonograma");
		nonogramaMenu.add(getDirectoMenu());
		nonogramaMenu.add(getInversoMenuItem());
		nonogramaMenu.add(getSinForzado());
		nonogramaMenu.add(getConForzado());
		nonogramaMenu.setVisible(true);
		nonogramaMenu.setEnabled(true);
		return nonogramaMenu;
	}
	/******************************************************************************************************/
	private JMenuItem getConForzado() {
		conForzado= new JMenuItem("Forzado");
		conForzado.setEnabled(false);
		conForzado.setVisible(true);
		
		conForzado.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				forzado = true;
			}
		});
		return conForzado;
	}
	/******************************************************************************************************/
	private JMenuItem getSinForzado() {
		sinForzado= new JMenuItem("Sin Forzado");
		sinForzado.setEnabled(false);
		sinForzado.setVisible(true);
		
		sinForzado.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				forzado = false;
			}
		});
		return sinForzado;
	}

	/******************************************************************************************************/
	private JMenu getMenuAyuda() {

		JMenu ayudaMenu = new JMenu("Ayuda");
		ayudaMenu.add(getInfoNonograma());
		ayudaMenu.setVisible(true);
		ayudaMenu.setEnabled(true);
		return ayudaMenu;
	}
	
	/******************************************************************************************************/	
	private JMenuItem getInfoNonograma() {		
	
		infoNonograma= new JMenuItem("Acerca de Nonograma");
		infoNonograma.setEnabled(true);
		infoNonograma.setVisible(true);
		
		infoNonograma.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				openURL("http://en.wikipedia.org/wiki/Nonogram");
				//JOptionPane.showMessageDialog(null, "Please open a browser and go to ");
			}
		});
		return infoNonograma;
	}

	/******************************************************************************************************/
		public static  void openURL(String url) {
	        String osName = System.getProperty("os.name");
	        try {
	            if (osName.startsWith("Windows")) {
	                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
	            } else if (osName.startsWith("Mac OS X")) {
	                //Runtime.getRuntime().exec("open -a safari " + url);
	                //Runtime.getRuntime().exec("open " + url + "/index.html");
	                Runtime.getRuntime().exec("open " + url);
	            } else {
	                //System.out.println("Please open a browser and go to "+ url);
	                JOptionPane.showMessageDialog(null, "Please open a browser and go to "+ url);
	            }
	        } catch (IOException e) {
	            //System.out.println("Failed to start a browser to open the url " + url);
	            JOptionPane.showMessageDialog(null, "Failed to start a browser to open the url " + url);
	            e.printStackTrace();
	        }
	    }
		
	/******************************************************************************************************/
	private JMenuItem getGuardarMenuItem() {

		guardarMenuItem = new JMenuItem("Guardar");
		guardarMenuItem.setEnabled(false);
		guardarMenuItem.setVisible(true);
		
		guardarMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
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
	            }
	        		
				
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
			
							String path="";

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
					                    }else{
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
					        						n = new Nonograma(canvas,restricciones_filas,restricciones_cols,forzado);
					        						/*restricciones_filas = new int[filas][3]; 
					        						restricciones_cols = new int[columnas][3];*/
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
					                        	
					                        	 directoMenu.setEnabled(true);
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
					        }


						}
					});

		return abrirMenuItem;
	}
	
	/******************************************************************************************************/
	private JMenuItem getNuevoMenuItem() {

		nuevoMenuItem = new JMenuItem("Nuevo");
		nuevoMenuItem.setEnabled(true);
		nuevoMenuItem.setVisible(true);
		nuevoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ventanaFijarTamanyo = new JFrame();
				ventanaFijarTamanyo.setVisible(true);
				ventanaFijarTamanyo.setEnabled(true);
				ventanaFijarTamanyo.setSize(300, 100);
				ventanaFijarTamanyo.setContentPane(damePanelTamanyo());
			}
		});
		return nuevoMenuItem;
	}

	/******************************************************************************************************/
	private JMenuItem getEjemplosMenuItem() {

		ejemploMenuItem = new JMenuItem("Ejemplos");
		ejemploMenuItem.setEnabled(true);
		ejemploMenuItem.setVisible(true);
		ejemploMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ventanaElegirEjemplo = new JFrame();
				ventanaElegirEjemplo.setVisible(true);
				ventanaElegirEjemplo.setEnabled(true);
				ventanaElegirEjemplo.setSize(300, 150);
				ventanaElegirEjemplo.setContentPane(damePanelEjemplos());
			}
		});
		/*	abrirMenu.add(dameCargarMatrizA());
		abrirMenu.add(dameCargarMatrizB());
		*/
		return ejemploMenuItem;
	}


	/******************************************************************************************************/

	/******************************************************************************************************/
	private JMenu getDirectoMenu() {

		directoMenu = new JMenu("Directo");
		directoMenu.setEnabled(false);
		directoMenu.setVisible(true);
		directoMenu.add(damePrimera());
		directoMenu.add(dameSiguiente());
		return directoMenu;
	}

	/******************************************************************************************************/
	private JMenuItem getInversoMenuItem() {

		inversoMenuItem = new JMenuItem("Inverso");
		inversoMenuItem.setEnabled(false);
		inversoMenuItem.setVisible(true);
		inversoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				restricciones_filas = new int[filas][canvas.getMax_rest_fil()]; 
				restricciones_cols = new int[columnas][canvas.getMax_rest_col()];
				int[][] tableroAux = canvas.getTablero();
				for (int i=0; i<filas;i++){
					procesaFila(i,tableroAux);
					procesaCol(i,tableroAux);
				}
				n = new Nonograma(canvas,restricciones_filas,restricciones_cols,forzado);
				canvas.setRestF(restricciones_filas);
				canvas.setRestC(restricciones_cols);
				canvas.repaint();
			//LISTENER!!!!
				
			}
		});
	
		return inversoMenuItem;
	}
	
	/******************************************************************************************************/
	protected void procesaCol(int i, int[][] t) {
		int j = filas-1;
		int cont =0;
		int indice=canvas.getMax_rest_col()-1;
		
		while (j>=0){
			if (t[j][i]!=1){
				if (cont!=0){
					restricciones_cols[i][indice]= cont;
					indice--;
				}
				cont=0;
			}else{
					if ((t[j][i]==1)){
						cont++;
						if (j==0){//He acabado la fila
							restricciones_cols[i][indice]= cont;
						}
					}
				}
			j--;
		}
		
	}

	/******************************************************************************************************/
	
	protected void procesaFila(int i, int [][] t) {
		int j = columnas-1;
		int cont =0;
		int indice=canvas.getMax_rest_fil()-1;
		
		while (j>=0){
			if (t[i][j]!=1){
				if (cont!=0){
					restricciones_filas[i][indice]= cont;
					indice--;
				}
				cont=0;
			}else{
					if ((t[i][j]==1)){
						cont++;
						if (j==0){//He acabado la fila
							restricciones_filas[i][indice]= cont;
						}
					}
				}
			j--;
		}
		
	}

	/******************************************************************************************************/
	private JMenuItem dameSiguiente() {
		siguienteMenuItem = new JMenuItem("Siguiente");
		siguienteMenuItem.setVisible(true);
		siguienteMenuItem.setEnabled(false);
		siguienteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(n.nonograma() == false){
		 			JOptionPane.showMessageDialog(null, "YA NO HAY MAS SOLUCIONES!");
				}
			}
		});

		return siguienteMenuItem;
	}

	/******************************************************************************************************/
	private JMenuItem damePrimera() {
		primeraMenuItem = new JMenuItem("Primera");
		primeraMenuItem.setVisible(true);
		primeraMenuItem.setEnabled(false);
		
		primeraMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 	if(n.nonograma() == false){
				 		if (miCont == 0)
				 			JOptionPane.showMessageDialog(null, "NO EXISTEN SOLUCIONES!");
				 	}
				 	else {//Ha encontrado una solucion
				 			miCont++;
				 			siguienteMenuItem.setEnabled(true);
				 			primeraMenuItem.setEnabled(true);
				 	}
			}
		});

		return primeraMenuItem;
	}

	/******************************************************************************************************/

	/******************************************************************************************************/	

	private JPanel damePanelTamanyo() {
		
	    JPanel panelTamanyo = new JPanel();
		panelTamanyo.setLayout(new BorderLayout());
		panelTamanyo.add("North", new JLabel("Introduce las filas y las columnas"));
		JPanel panelCentro = new JPanel();
		panelTamanyo.add("Center", panelCentro);

		JLabel lab1 = new JLabel("Filas");
		panelCentro.add(lab1);
		botonFil = new JTextField();
		botonFil.setEnabled(true);
		botonFil.setVisible(true);
		botonFil.setColumns(2);
		botonFil.validate();
		panelCentro.add(botonFil);

		JLabel lab2 = new JLabel("Columnas");
		panelCentro.add(lab2);
		botonCol = new JTextField();
		botonCol.setEnabled(true);
		botonCol.setVisible(true);
		botonCol.setColumns(2);
		botonCol.validate();
		panelCentro.add(botonCol);

		JButton botonAceptar = new JButton("Aceptar");
		panelCentro.add(botonAceptar);
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String s1 = botonFil.getText();
				String s2 = botonCol.getText();

				if ((s1 !="") && (s2 !="")) {
					
					try {
						filas = Integer.parseInt(s1);
						columnas = Integer.parseInt(s2);
						
						
						if (filas > 0 && filas <= 20 && columnas > 0
								&& columnas <= 20) {
							modificarFrame(filas,columnas);
							
						} else
							JOptionPane.showMessageDialog(null,"AVISO: Filas o columnas fuera de rango, solo entre 1 y 20!");
						
						
						ventanaFijarTamanyo.setAlwaysOnTop(false);
						ventanaFijarTamanyo.setVisible(false);
						ventanaFijarTamanyo.setEnabled(false);
						ventanaFijarTamanyo.dispose();
						ventanaFijarTamanyo=null;
						
						guardarMenuItem.setEnabled(true);
						inversoMenuItem.setEnabled(true);
						
						
					} catch (NumberFormatException ex) {
						if (ex != null) {
							JOptionPane.showMessageDialog(null, "AVISO: TIENES QUE INTRODUCIR NUMEROS!");
						}
					}

				} else
					JOptionPane.showMessageDialog(null, "AVISO: Introducir filas y columnas!");
			}
			

		});

		panelTamanyo.validate();
		
		return panelTamanyo;

	}
	
	/******************************************************************************************************/
	private void modificarFrame(int filas, int columnas) {
		
			f.dispose();
			f = new JFrame();
			canvas = new TableroCanvas(filas,columnas);
			JPanel pt = new JPanel();
			pt.setLayout(new BorderLayout());
			if ((filas>10) ||(columnas>10)) {
				canvas.setTamCelda(25);
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
		
		
	}
	
	/******************************************************************************************************/
	private JPanel damePanelEjemplos() {
		
		String[] listaEjem = {"prueba","rombo","cinco","cara","ejem"};
		
		ejemplos = new JComboBox(listaEjem);
		JButton botonAceptar = new JButton("Aceptar");
	    JPanel panelEjem = new JPanel();
	    panelEjem.setLayout(new GridLayout(4,0));
	    panelEjem.add(botonAceptar,1,0);
	    panelEjem.add(new JLabel(""),2,0);
	    panelEjem.add(ejemplos,3,0);
	    panelEjem.add(new JLabel("Selecciona el nonograma a cargar"),4,0);
	    ejemplos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ejemElegido =(String) ejemplos.getSelectedItem();
			
			}
	    	
	    });
	    
		botonAceptar.addActionListener(new ActionListener() {
			File archivo = null;
		    FileReader fr = null;
		    BufferedReader br = null;
		    
			public void actionPerformed(ActionEvent e) {
				ventanaElegirEjemplo.setAlwaysOnTop(false);
				ventanaElegirEjemplo.setVisible(false);
				ventanaElegirEjemplo.setEnabled(false);
				ventanaElegirEjemplo.dispose();
				ventanaElegirEjemplo=null;
				
				try {
			         // Apertura del fichero y creacion de BufferedReader para poder
			         // hacer una lectura comoda (disponer del metodo readLine()).
					
					 archivo = new File("src/ejemplosNonograma/"+ejemElegido+".txt");
			         //archivo = new File ("C:\\Users\\Semiramis\\Documents\\test1.txt");
			         fr = new FileReader (archivo.getAbsoluteFile());
			         BufferedReader br = new BufferedReader(fr);
			 
			         // Lectura del fichero
			         String linea;
			         
			         	 linea=br.readLine();
		        		 if (linea!=null) filas = Integer.parseInt(linea);
		        		 
		        		 linea=br.readLine();
		        		 if (linea!=null)columnas = Integer.parseInt(linea);
		        		 
		        		 if (filas > 0 && filas <= 20 && columnas > 0
		 							&& columnas <= 20) {
		 						modificarFrame(filas,columnas);
		 						restricciones_filas = new int[filas][canvas.getMax_rest_fil()]; 
		 						restricciones_cols = new int[columnas][canvas.getMax_rest_col()];
		 						n = new Nonograma(canvas,restricciones_filas,restricciones_cols,forzado);
		 						
		        	 }
				
		         	boolean primeraVez = false;
		         	boolean esLinea = false;
		         	int posLinea = 0;
		         
			         while((linea=br.readLine())!=null){
			        	 //JOptionPane.showMessageDialog(null, linea);
			        	 
			        	 if(linea.equals("Filas")) {
			        		 	linea=br.readLine();
	                 			esLinea = true;
	                 			primeraVez = true;
	                 			posLinea=0;
	                 		} else if (!primeraVez) throw new NumberFormatException();
	                 		
	                 	if(linea.equals("Columnas")){
	                 			linea=br.readLine();
	                 			posLinea=0;
	                 			esLinea = false;
	                 		}
	                 		procesalinea(linea,posLinea,esLinea);
	                 		posLinea++;
	                 	}	         
                 	
			         directoMenu.setEnabled(true);
			         primeraMenuItem.setEnabled(true);
			         guardarMenuItem.setEnabled(true);
			         conForzado.setEnabled(true);
			         sinForzado.setEnabled(true);
			         
			      }
			      catch(Exception ex){
			         ex.printStackTrace();
			      }finally{
			         // En el finally cerramos el fichero, para asegurarnos
			         // que se cierra tanto si todo va bien como si salta 
			         // una excepcion.
			         try{                    
			            if( null != fr ){   
			               fr.close();     
			            }                  
			         }catch (Exception e2){ 
			            e2.printStackTrace();
			         }
			      }
	           

				}

		});
		
		return panelEjem;
		
	}

	/******************************************************************************************************/

    private void procesalinea(String s, int fila, boolean b){
            
            
            int posicion = canvas.getMax_rest_col()-1;
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
                                            restricciones_filas[fila][posicion] = numero;
                                            canvas.setRestF(restricciones_filas);
                                            posicion--;
                            }
                            else {
                                    restricciones_cols[fila][posicion] = numero;
                                    canvas.setRestC(restricciones_cols);
                                    posicion--;
                            }
                            num = "";
                            
                            i--;
                    }
            }
            
    }
	/******************************************************************************************************/
    /******************************************************************************************************/
    public static void main(String[] args) {

		Interfaz i = new Interfaz();

	}
	

}
