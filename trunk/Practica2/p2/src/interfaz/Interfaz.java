package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class Interfaz extends JFrame {

	/***********************
	 * ATRIBUTOS PRIVADOS
	 * 
	 ***********************/

	
	int restricciones_filas[][];
	int restricciones_col[][];
	
	TableroCanvas canvas;
	JFrame f;
	
	
	
	//REUTILIZABLE
	private JPanel panelPrincipal;

	private JTabbedPane panelTabulado;

	private int filas;
	private int columnas;

	private JTextField botonFil;
	private JTextField botonCol;


	private JFrame ventanaFijarTamanyo;
	
	
	private JMenu directoMenu;
	private JMenuItem inversoMenuItem;
	private JMenuItem guardarMenuItem;
	private JMenuItem abrirMenuItem;
	private JMenuItem nuevoMenuItem;
	private JMenuItem ejemploMenuItem;
	

	/******************************************************************************************************/

	public Interfaz() {
		
		inicializarInterfaz();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Para que se termine el hilo al cerrar la ventana

	}

	/******************************************************************************************************/
	public void inicializarInterfaz() {

		f=this;
		JPanel pt = new JPanel();
		canvas = new TableroCanvas();
		pt.add(canvas);
		this.setContentPane(pt);
		
		this.setTitle("NONOGRAMA");
		this.setVisible(true);
		this.setEnabled(true);
		this.setSize(750, 750);
		this.setJMenuBar(dameBarraMenu()); // crea la barra de menu y la adjunta al jframe
		this.validate();

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
		nonogramaMenu.setVisible(true);
		nonogramaMenu.setEnabled(true);
		return nonogramaMenu;
	}

	/******************************************************************************************************/
	private JMenu getMenuAyuda() {

		JMenu ayudaMenu = new JMenu("Ayuda");
		ayudaMenu.setVisible(true);
		ayudaMenu.setEnabled(true);
		
		return ayudaMenu;
	}
	/******************************************************************************************************/
	private JMenuItem getGuardarMenuItem() {

		guardarMenuItem = new JMenuItem("Guardar");
		guardarMenuItem.setEnabled(true);
		guardarMenuItem.setVisible(true);
		
		/*guardarMenu.add(dameGuardarMatrizA());
		guardarMenu.add(dameGuardarMatrizB());
		guardarMenu.add(dameGuardarResultado());
		*/
		
		return guardarMenuItem;
	}

	/******************************************************************************************************/
	private JMenuItem getAbrirMenuItem() {

		abrirMenuItem = new JMenuItem("Abrir");
		abrirMenuItem.setEnabled(true);
		abrirMenuItem.setVisible(true);
	
		/*	abrirMenu.add(dameCargarMatrizA());
		abrirMenu.add(dameCargarMatrizB());
		*/
		return abrirMenuItem;
	}
	
	/******************************************************************************************************/
	private JMenuItem getNuevoMenuItem() {

		nuevoMenuItem = new JMenuItem("Nuevo");
		nuevoMenuItem.setEnabled(true);
		nuevoMenuItem.setVisible(true);
	
		/*	abrirMenu.add(dameCargarMatrizA());
		abrirMenu.add(dameCargarMatrizB());
		*/
		return nuevoMenuItem;
	}

	private JMenuItem getEjemplosMenuItem() {

		ejemploMenuItem = new JMenuItem("Ejemplos");
		ejemploMenuItem.setEnabled(true);
		ejemploMenuItem.setVisible(true);
	
		/*	abrirMenu.add(dameCargarMatrizA());
		abrirMenu.add(dameCargarMatrizB());
		*/
		return ejemploMenuItem;
	}

	/******************************************************************************************************/
	/*private JMenuItem dameGuardarResultado() {
		JMenuItem guardarRMenuItem = new JMenuItem("Guardar Matriz Resultado");

		guardarRMenuItem.setVisible(true);
		guardarRMenuItem.addActionListener(new ActionListener() {
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
	                    
	                    for (int i = 0; i < filas; i++){
	                    	for (int j = 0; j < columnas; j++){
	                    		printwriter.print((Integer) tm3.getValueAt(i, j));
	                    		printwriter.print(" ");
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

		return guardarRMenuItem;
	}*/

	/******************************************************************************************************/
	/*private JMenuItem dameGuardarMatrizB() {
		JMenuItem guardarBMenuItem = new JMenuItem("Guardar Matriz B");
		guardarBMenuItem.setVisible(true);
		guardarBMenuItem.addActionListener(new ActionListener() {
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
	                    
	                    for (int i = 0; i < filas; i++){
	                    	for (int j = 0; j < columnas; j++){
	                    		printwriter.print((Integer) tm2.getValueAt(i, j));
	                    		printwriter.print(" ");
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

		return guardarBMenuItem;
	}*/

	/******************************************************************************************************/
	/*private JMenuItem dameGuardarMatrizA() {
		JMenuItem guardarAMenuItem = new JMenuItem("Guardar Matriz A");
		guardarAMenuItem.setVisible(true);
		guardarAMenuItem.addActionListener(new ActionListener() {
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
	                    
	                    for (int i = 0; i < filas; i++){
	                    	for (int j = 0; j < columnas; j++){
	                    		printwriter.print((Integer) tm1.getValueAt(i, j));
	                    		printwriter.print(" ");
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

		return guardarAMenuItem;
	}
*/
	/******************************************************************************************************/
	/* Leer de Ficheros */

	/*private JMenuItem dameCargarMatrizB() {
		JMenuItem cargaBMenuItem = new JMenuItem("Cargar Matriz B");
		cargaBMenuItem.setEnabled(true);
		cargaBMenuItem.setVisible(true);
		cargaBMenuItem.addActionListener(new ActionListener() {
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
		                   int linea = 0;
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
		        						m1 = new Matriz(filas, columnas);
		        						m2 = new Matriz(filas, columnas);
		        						resultado = new Matriz(filas, columnas);
		        					}
		                        	
		                        	if (tm2 != null){
		    							int filAux=(tm2.getRowCount())-1;
		    							for (int k=filAux; k >= 0 ; k--)
		    								tm2.removeRow(k);
		    							int filAux2=(tm3.getRowCount())-1;
		    							for (int k=filAux2; k >= 0 ; k--)
		    								tm3.removeRow(k);
		    							damePanelTabla(tm2);
		    							damePanelTabla(tm3);
		    						}
		    						else{
		    							tm2=new DefaultTableModel();
		    							panelTabulado.add("Matriz 2", damePanelTabla(tm2));
		    							tm3=new DefaultTableModel();
		    							panelTabulado.add("Resultado", damePanelTabla(tm3));
		    						}
		    						panelTabulado.validate();

		                        	activarMenus();	                     	
		                        	while(sc.hasNext()){
		                        		s = sc.nextLine();
		                        		procesalinea(s,linea,tm2);
		                        		linea++;
		                        	}
		                        	
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

		return cargaBMenuItem;
	}*/

	/******************************************************************************************************/
/*	private JMenuItem dameCargarMatrizA() {
		JMenuItem cargaAMenuItem = new JMenuItem("Cargar Matriz A");
		cargaAMenuItem.setEnabled(true);
		cargaAMenuItem.setVisible(true);
		cargaAMenuItem.addActionListener(new ActionListener() {
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
		                   int linea = 0;
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
		        						m1 = new Matriz(filas, columnas);
		        						m2 = new Matriz(filas, columnas);
		        						resultado = new Matriz(filas, columnas);
		        					}
		                        	
		                        	if (tm1 != null){
		    							int filAux=(tm1.getRowCount())-1;
		    							for (int k=filAux; k >= 0 ; k--)
		    								tm1.removeRow(k);
		    							damePanelTabla(tm1);
		    						}
		    						else{
		    							tm1 = new DefaultTableModel();
		    							panelTabulado.add("Matriz 1", damePanelTabla(tm1));
		    						}
		    						panelTabulado.validate();
		                        			                        	
		                        	activarMenus();
		                        	while(sc.hasNext()){
		                        		s = sc.nextLine();
		                        		procesalinea(s,linea,tm1);
		                        		linea++;
		                        	}
		                        	
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

		return cargaAMenuItem;
	}*/
	
	/******************************************************************************************************/
	/**
	 * 
	 * @Matriz
	 */

/*	private JMenuItem getDefaultMenuItem() {
		JMenuItem defaultSMenuItem = new JMenuItem("Matrices por defecto");
		defaultSMenuItem.setEnabled(true);
		defaultSMenuItem.setVisible(true);

		defaultSMenuItem.addActionListener(new ActionListener() { // Implementamos el oyente
					public void actionPerformed(ActionEvent e) {
						
						if ((tm1 != null) || (tm2 != null) || (tm3 != null)) 
							panelTabulado.removeAll();
						
						filas = 5;
						columnas = 5;
						
						m1 = new Matriz(1);
						m2 = new Matriz(2);
						resultado = new Matriz(filas,columnas);
						
						tm1 = new DefaultTableModel();
						tm2 = new DefaultTableModel();
						tm3 = new DefaultTableModel();
						
						panelTabulado.add("Matriz1", damePanelTabla(tm1));
						panelTabulado.add("Matriz2", damePanelTabla(tm2));
						panelTabulado.add("Resultado", damePanelTabla(tm3));
						panelTabulado.validate();
						
						escribirMatriz(m1, tm1);
						escribirMatriz(m2, tm2);
						
						activarMenus();
						
					}
				});

		defaultSMenuItem.validate();
		return defaultSMenuItem;
	}
*/
	/******************************************************************************************************/
	/*private void escribirMatriz(Matriz m, DefaultTableModel tm) {

		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++) {
				String valorS = String.valueOf(m.getIJ(i, j));
				tm.setValueAt(valorS, i, j);
			}
	}*/

	/******************************************************************************************************/
	private JMenu getDirectoMenu() {

		directoMenu = new JMenu("Directo");
		directoMenu.setEnabled(true);
		directoMenu.setVisible(true);
		directoMenu.add(damePrimera());
		directoMenu.add(dameSiguiente());
		return directoMenu;
	}

	/******************************************************************************************************/
	private JMenuItem getInversoMenuItem() {

		inversoMenuItem = new JMenuItem("Inverso");
		inversoMenuItem.setEnabled(true);
		inversoMenuItem.setVisible(true);
		inversoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			//LISTENER!!!!
				
			}
		});
	
		return inversoMenuItem;
	}
	
	
	/******************************************************************************************************/
	private JMenuItem dameSiguiente() {
		JMenuItem siguienteMenuItem = new JMenuItem("Siguiente");
		siguienteMenuItem.setVisible(true);
		siguienteMenuItem.setEnabled(true);
		siguienteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			//LISTENER!!!!
				
			}
		});

		return siguienteMenuItem;
	}

	/******************************************************************************************************/
	private JMenuItem damePrimera() {
		JMenuItem primeraMenuItem = new JMenuItem("Primera");
		primeraMenuItem.setVisible(true);
		primeraMenuItem.setEnabled(true);
		primeraMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 	
			
				
				

			}
		});

		return primeraMenuItem;
	}

	/******************************************************************************************************/
/*	private JMenuItem getRestaMenuItem() {

		restarMenuItem = new JMenuItem("Restar");
		restarMenuItem.setVisible(true);
		restarMenuItem.setEnabled(false);
		restarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultado.resta(m1, m2);
				muestraResultado(resultado);

			}
		});
		return restarMenuItem;
	}*/

	/******************************************************************************************************/
/*	private JMenuItem getSumaMenuItem() {

		sumarMenuItem = new JMenuItem("Sumar");
		sumarMenuItem.setVisible(true);
		sumarMenuItem.setEnabled(false);
		sumarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultado.suma(m1, m2);
				muestraResultado(resultado);

			}
		});
		return sumarMenuItem;
	}*/

	/******************************************************************************************************/
/*	private void muestraResultado(Matriz m) {
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++){
				String valorS = String.valueOf(m.getIJ(i, j));
				tm3.setValueAt(valorS, i, j);
			}
	}*/

	/******************************************************************************************************/
/*	private void convertirEnMatriz(DefaultTableModel tm, Matriz m) {

		for (int i=0; i < filas; i++)
		  for (int j=0; j < columnas; j++){
			int valor=0;
			String valorS=(String)(tm.getValueAt(i,j));
			if (valorS != null){	
				try {
					valor =Integer.parseInt(valorS);
				} catch (NumberFormatException ex) {
					if (ex != null) 
						JOptionPane.showMessageDialog(null, "AVISO: TIENES QUE INTRODUCIR NUMEROS!");

					}
				m.setIJ(i,j,valor);	
				}
		  }

	}
*/
	/******************************************************************************************************/
/*	private JMenuItem getInicializarMenuItem() {

		inicializarMenuItem = new JMenuItem("Inicializar");
		inicializarMenuItem.setVisible(true);
		inicializarMenuItem.setEnabled(false);
		inicializarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ((tm1 != null) && (tm2!= null) && (tm3!= null)){
				inicializarMatrices(tm1);
				inicializarMatrices(tm2);
				inicializarMatrices(tm3);
				
				}
				
				//desactivarMenus();
			}
		});
		return inicializarMenuItem;
	}*/

	/******************************************************************************************************/
/*	private JMenuItem getCargarMenuItem() {

		cargarMenuItem = new JMenuItem("Cargar Tablas");
		cargarMenuItem.setEnabled(false);
		cargarMenuItem.setVisible(true);
		cargarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convertirEnMatriz(tm1,m1);
				convertirEnMatriz(tm2,m2);

					}

	
		});
		return cargarMenuItem;
	}
*/
	/******************************************************************************************************/	
	/*private JMenuItem getTamanyoMenuItem() {
		
		JMenuItem tamanyoItem = new JMenuItem("Fijar Tamanyo");
		tamanyoItem.setEnabled(true);
		tamanyoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ventanaFijarTamanyo = new JFrame();
				ventanaFijarTamanyo.setVisible(true);
				ventanaFijarTamanyo.setEnabled(true);
				ventanaFijarTamanyo.setSize(300, 100);
				ventanaFijarTamanyo.setContentPane(damePanelTamanyo());
				
				 * 
				 * 
				 * 
				 
				// tamanyoItem.setEnabled(true);

				
				 * 
				 * crear el panel tabulado
				 

			}
		});
		return tamanyoItem;
	}
	*/
	/******************************************************************************************************/
	/**
	 * 
	 * @Matriz
	 */

	/*private JPanel damePanelTamanyo() {
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
				
				if ((tm1 != null) || (tm2 != null) || (tm3 != null)) 
					panelTabulado.removeAll();
				
				String s1 = botonFil.getText();
				String s2 = botonCol.getText();

				if ((s1 != "") && (s2 != "")) {
					try {
						filas = Integer.parseInt(s1);
						columnas = Integer.parseInt(s2);
					} catch (NumberFormatException ex) {
						if (ex != null) {
							JOptionPane.showMessageDialog(null, "AVISO: TIENES QUE INTRODUCIR NUMEROS!");
						}
					}

					if (filas > 0 && filas <= 20 && columnas > 0
							&& columnas <= 20) {
						m1 = new Matriz(filas, columnas);
						m2 = new Matriz(filas, columnas);
						resultado = new Matriz(filas, columnas);
						
						tm1 = new DefaultTableModel();
						tm2 = new DefaultTableModel();
						tm3 = new DefaultTableModel();
						
						panelTabulado.add("Matriz1", damePanelTabla(tm1));
						panelTabulado.add("Matriz2", damePanelTabla(tm2));
						panelTabulado.add("Resultado", damePanelTabla(tm3));
						panelTabulado.validate();
						
					} else
						JOptionPane.showMessageDialog(null,"AVISO: Filas o columnas fuera de rango, solo entre 1 y 20!");

				} else
					JOptionPane.showMessageDialog(null, "AVISO: Introducir filas y columnas!");
			
				activarMenus();
				
				ventanaFijarTamanyo.setAlwaysOnTop(false);
				ventanaFijarTamanyo.setVisible(false);
				ventanaFijarTamanyo.setEnabled(false);
				ventanaFijarTamanyo.dispose();
				ventanaFijarTamanyo=null;
			}
			

		});

		panelTamanyo.validate();
		
		return panelTamanyo;

	}
*/
	/******************************************************************************************************/
/*	private JPanel damePanelTabla(DefaultTableModel tm) {
		
		JPanel pAux = new JPanel();
		pAux.setLayout(new BorderLayout());
		
		tm.setColumnCount(columnas);
		
		for (int i = 0; i < filas; i++)
			tm.addRow(new String [columnas]);

	    JTable tabla = new JTable(tm);
		pAux.add("Center", tabla);
		pAux.validate();
		return pAux;
	}*/

	/******************************************************************************************************/
/*	private void inicializarMatrices(DefaultTableModel tm) {
		
		panelTabulado.removeAll();
		tm1 = new DefaultTableModel();
		tm2 = new DefaultTableModel();
		tm3 = new DefaultTableModel();
		panelTabulado.add("Matriz1", damePanelTabla(tm1));
		panelTabulado.add("Matriz2", damePanelTabla(tm2));
		panelTabulado.add("Resultado", damePanelTabla(tm3));
		panelTabulado.validate();	
	
	}*/

	/******************************************************************************************************/
	public static void main(String[] args) {

		Interfaz i = new Interfaz();

	}
	
/*	private void activarMenus(){
		if ((tm1 != null) || (tm2 != null) || (tm3 != null)) 
		{
			sumarMenuItem.setEnabled(true);
			restarMenuItem.setEnabled(true);
			multiplicarMenu.setEnabled(true);
			inicializarMenuItem.setEnabled(true);
			cargarMenuItem.setEnabled(true);
			guardarMenu.setEnabled(true);
		}
	}*/
	
/*	private void desactivarMenus(){
		if ((tm1 != null) || (tm2 != null) || (tm3 != null)) 
		{
			sumarMenuItem.setEnabled(false);
			restarMenuItem.setEnabled(false);
			multiplicarMenu.setEnabled(false);
			inicializarMenuItem.setEnabled(false);
		}
	}
	
	private void procesalinea(String s, int fila, DefaultTableModel tm){
		int i = 0;
		int posicion = 0;
		String num="";
		boolean numNeg = false;
		char[] array = s.toCharArray();
		
		while (i!= array.length){
			
			if(array[i] == '-'){
				numNeg = true;
				i++;
			}
			else if(array[i]!= ' '){
				num += array[i];
				i++;
			}
			else{
				int numero = Integer.parseInt(num);
				if (numNeg) numero = -numero;
				String numS = String.valueOf(numero);
				tm.setValueAt(numS, fila, posicion);
				numNeg = false;
				num="";
				posicion++;
				i++;
			}
		}
		
	}*/
	

}
