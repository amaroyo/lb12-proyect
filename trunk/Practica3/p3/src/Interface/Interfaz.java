package Interface;

	import java.awt.BorderLayout;
	import java.awt.Container;
	import java.awt.FlowLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.io.*;
	import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Grafos.Grafo;


public class Interfaz extends JFrame implements ActionListener{


		private Grafo grafo;
		private TableroCanvas TC;
		private JMenuItem AddArista;
		private JMenuItem AddVert;
		private JMenuItem RemoveArist;
		private JMenuItem RemoveVert;
		private JMenuItem Kruskal;
		private JMenuItem Floyd;
		private JMenuItem Dijkstra;
		private JMenuItem Guardar;
		private JMenuItem Abrir;
			
		private boolean esNodo;
		private boolean esArco;
		private boolean esElimNodo;
		private boolean esElimArco;
		private boolean esDijkstra;
		private boolean esFloyd;
		private boolean esKruskal;
		private boolean esPrimClick;
		private String nameVert;
		private int peso;
		
		
		
		
		public Interfaz(){
			esNodo = false;
			esArco = false;
			esPrimClick = false;
			esDijkstra  = false;
			esFloyd  = false;
			esKruskal  = false;
			grafo = new Grafo();
			inicializarInterfaz();
			JOptionPane.showMessageDialog(null, "Aviso: recuerda...");
		}
		
		

		public static void main(String arg[]){
			
			Interfaz cp = new Interfaz();
			cp.setVisible(true);
			cp.setEnabled(true);
			cp.setSize(1000, 600);
			
		}
		
		public void inicializarInterfaz(){
			this.setJMenuBar(getMenuPrincipal());
			this.setContentPane(getPanelCanvas());
			this.setTitle("Aplicaciones con Grafos");
		}
		
		
		JMenuBar getMenuPrincipal(){
			JMenuBar barraMenu = new JMenuBar();
			barraMenu.add(getMenuGrafo());
			barraMenu.add(getMenuOperaciones());
			barraMenu.add(getMenuArchivo());
			return barraMenu;
		}

	// Menus 
		
		JMenu getMenuGrafo(){
			JMenu grafoMenu = new JMenu();
			grafoMenu.setText("Grafo");
			grafoMenu.add(getAddVert());
			grafoMenu.add(getAddArist());
			grafoMenu.add(getRemoveVert());
			grafoMenu.add(getRemoveArist());
			return grafoMenu;
		}
		

		JMenu getMenuOperaciones(){
			JMenu operMenu = new JMenu();
			operMenu.setText("Operaciones");
			operMenu.add(getDijkstra());
			operMenu.add(getFloyd());
			operMenu.add(getKruskal());
			return operMenu;
		}
		


		JMenu getMenuArchivo(){
			JMenu ArchivoMenu = new JMenu();
			ArchivoMenu.setText("Archivo");
			ArchivoMenu.add(getGuardar());		
			ArchivoMenu.add(getAbrir());	
			return ArchivoMenu;
		}
		

		JPanel getPanelCanvas() {
			JPanel p = new JPanel();
			BorderLayout b = new BorderLayout();
			p.setLayout(b);
			TC = new TableroCanvas(this);
			p.add(TC,BorderLayout.CENTER);
			p.validate();
			return p;
		}

	//Clase Oyente
		
		class Oyente implements ActionListener{
			
			public void actionPerformed(ActionEvent e) throws NumberFormatException{
				if (AddArista == e.getSource()){
				    if (grafo.getNumNodos() > 1){
				    	final JFrame framePeso = new JFrame("Peso Arista");
				    	framePeso.setLayout(null);
				    	JLabel Peso = new JLabel("Peso:");
				    	Peso.setBounds(50, 20, 70, 30);
				    	final JTextField PesoTF = new JTextField("");
				    	PesoTF.setBounds(150, 20, 70, 30);
				    	JButton button = new JButton("OK");
				    	button.addActionListener(new ActionListener(){
				    		public void actionPerformed(ActionEvent e) {
				    			boolean salir = true;
				    			try{
				    				peso = Integer.parseInt(PesoTF.getText());
				    			}
				    			catch (NumberFormatException a){
				    				JOptionPane.showMessageDialog(null, "Por favor ingreso un - Número -");
				    				salir = false;
				    			}	
				    			if (PesoTF.getText() == "") JOptionPane.showMessageDialog(null, "Por Favor ingrese un Peso");
				    			//else if  si no es un número
				    			else if (salir){ 
				    				esPrimClick = true;
				    				esArco = true;
				    				framePeso.dispose();
				    			}
				    		}	
				    	});
				    	button.setBounds(100, 55, 70, 30);
				    	framePeso.add(PesoTF); 
				    	framePeso.add(Peso);
				    	framePeso.add(button);
				    	framePeso.setVisible(true);
				    	framePeso.setEnabled(true);
				    	framePeso.setSize(300, 150);
				    }
				    else JOptionPane.showMessageDialog(null, "No hay suficientes Vértices para introducir una Arista");
				}
				else if (AddVert == e.getSource()){
					final JFrame frameName = new JFrame("Nombre Vértice");
				    frameName.setLayout(null);
				    JLabel Name = new JLabel("NOMBRE:");
				    Name.setBounds(50, 20, 70, 30);
				    final JTextField NameTF = new JTextField("");
				    NameTF.setBounds(150, 20, 70, 30);
					JButton button = new JButton("OK");
				    button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							nameVert = NameTF.getText();
							if (nameVert.isEmpty()) JOptionPane.showMessageDialog(null, "Por Favor ingrese un Nombre");
							//else if nombre ya existe 
							else{
								esNodo = true;
								frameName.dispose();
							}
						}	
				    });
				    button.setBounds(100, 55, 70, 30);
				    frameName.add(NameTF); 
				    frameName.add(Name);
				    frameName.add(button);
					frameName.setVisible(true);
					frameName.setEnabled(true);
					frameName.setSize(300, 150);
					
				}
				else if (RemoveArist == e.getSource()){
					JOptionPane.showMessageDialog(null, "Por Favor elija el Nodo Inicial y Final de la arista que desea eliminar");
					esPrimClick = true;
    				esElimArco = true;
				}
				else if (RemoveVert == e.getSource()){
					JOptionPane.showMessageDialog(null, "Por Favor elija el nodo que desea eliminar");
					esElimNodo = true;
				}
				else if (Kruskal == e.getSource()){
					JOptionPane.showMessageDialog(null, "Haga click en el grafo");
					esKruskal = true;
				}
				else if (Floyd == e.getSource()){
					JOptionPane.showMessageDialog(null, "Por Favor elija un Nodo Inicial y uno Final");
					esFloyd = true;
					esPrimClick = true;
				}
				else if (Dijkstra == e.getSource()){
					JOptionPane.showMessageDialog(null, "Por Favor elija un Nodo Inicial y uno Final");
					esDijkstra = true;
					esPrimClick = true;
				}
				else if (Guardar == e.getSource()){
					JFileChooser selecFich = new JFileChooser();
					int i = selecFich.showSaveDialog(Interfaz.this);
					if (i == 0){
						String nameSave = selecFich.getSelectedFile().getAbsolutePath();
						grafo.guardarGrafo(nameSave);
					}else{
						JOptionPane.showMessageDialog(null, "Aviso: No ha sido posible abrir el archivo"); 
					} 	
				}
				else if (Abrir == e.getSource()){
					JFileChooser selecFich = new JFileChooser();
					int i = selecFich.showOpenDialog(Interfaz.this);
					if (i == 0){
						String nameLoad = selecFich.getSelectedFile().getAbsolutePath();
						File f = new File(nameLoad);
						grafo = new Grafo();
						try {
							grafo.cargarGrafo(f);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Aviso: No ha sido posible cargar el archivo");
							grafo = new Grafo();
						}
						TC.repaint();
					}else{
						JOptionPane.showMessageDialog(null, "Aviso: No ha sido posible abrir el archivo"); 
					} 
				}
				
				}
			}
				

	//get JMenuItems
		
		JMenuItem getAddArist() {
			AddArista = new JMenuItem();
			AddArista.setText("Introducir Arista");
			AddArista.setEnabled(true);
			Oyente o = new Oyente();
			AddArista.addActionListener(o);
			return AddArista;
	}

		JMenuItem getAddVert() {
			AddVert = new JMenuItem();
			AddVert.setText("Introducir Vértice");
			AddVert.setEnabled(true);
			Oyente o = new Oyente();
			AddVert.addActionListener(o);
			return AddVert;
	}
		
		JMenuItem getRemoveArist() {
			RemoveArist = new JMenuItem();
			RemoveArist.setText("Eliminar Arista");
			RemoveArist.setEnabled(true);
			Oyente o = new Oyente();
			RemoveArist.addActionListener(o);
			return RemoveArist;
		}

		JMenuItem getRemoveVert() {
			RemoveVert = new JMenuItem();
			RemoveVert.setText("Eliminar Vértice");
			RemoveVert.setEnabled(true);
			Oyente o = new Oyente();
			RemoveVert.addActionListener(o);
			return RemoveVert;
		}

		JMenuItem getKruskal() {
			Kruskal = new JMenuItem();
			Kruskal.setText("Kruskal");
			Kruskal.setEnabled(true);
			Oyente o = new Oyente();
			Kruskal.addActionListener(o);
			return Kruskal;
		}

		JMenuItem getFloyd() {
			Floyd = new JMenuItem();
			Floyd.setText("Floyd");
			Floyd.setEnabled(true);
			Oyente o = new Oyente();
			Floyd.addActionListener(o);
			return Floyd;
		}

		JMenuItem getDijkstra() {
			Dijkstra = new JMenuItem();
			Dijkstra.setText("Dijkstra");
			Dijkstra.setEnabled(true);
			Oyente o = new Oyente();
			Dijkstra.addActionListener(o);
			return Dijkstra;
		}
		
		JMenuItem getAbrir() {
			Abrir = new JMenuItem();
			Abrir.setText("Abrir");
			Abrir.setEnabled(true);
			Oyente o = new Oyente();
			Abrir.addActionListener(o);
			return Abrir;
		}

		JMenuItem getGuardar() {
			Guardar = new JMenuItem();
			Guardar.setText("Guardar");
			Guardar.setEnabled(true);
			Oyente o = new Oyente();
			Guardar.addActionListener(o);
			return Guardar;
		}

		
		public Grafo getGrafo(){
			return grafo;
		}
		
		public boolean isNodo(){
			return esNodo;
		}
		
		public boolean isArco(){
			return esArco;
		}
		
		public boolean isElimNodo(){
			return esElimNodo;
		}
		
		public boolean isElimArco(){
			return esElimArco;
		}
		
		public boolean isDijkstra(){
			return esDijkstra;
		}
		
		public boolean isFloyd(){
			return esFloyd;
		}
		
		public boolean isKruskal(){
			return esKruskal;
		}
		
		public boolean isPrimerClick(){
			return esPrimClick;
		}
		
		public void setPrimClick(boolean b){
			esPrimClick = b;
		}
		
		public void restartEsNodo(){
			esNodo = false;
		}
		
		public void restartEsArco(){
			esArco = false;
		}
		
		public void restartEsElimNodo(){
			esElimNodo = false;
		}
		
		public void restartEsElimArco(){
			esElimArco = false;
		}
		
		public void restartEsDijkstra(){
			esDijkstra = false;
		}
		
		public void restartEsFloyd(){
			esFloyd = false;
		}
		
		public void restartEsKruskal(){
			esKruskal = false;
		}
		
		public String dameNombre(){
			return nameVert;
		}
		
		public int damePeso(){
			return peso;
		}
		
		public void sendError(int caso){
			if (caso ==1) JOptionPane.showMessageDialog(null, "No puede dibujar un nodo encima de otro");
			else if (caso ==2) JOptionPane.showMessageDialog(null, "Ya existe un arco relacionado con esos nodos");
			else if (caso ==3) JOptionPane.showMessageDialog(null, "Elija un nodo inicial");
			else if (caso ==4) JOptionPane.showMessageDialog(null, "No existe un arco relacionado con esos nodos");
		}

		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}




	
}
