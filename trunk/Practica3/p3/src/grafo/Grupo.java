package grafo;

public class Grupo {
	private Grupo padre;
	private int profundidad;
	
	public Grupo(){
		padre = null;
		profundidad = 0;
	}
	
	public Grupo dameGrupo(){
		if (this.padre == null) return this;
		else return padre = padre.dameGrupo();
	}
	
	/*public void union(Grupo g){
		this.padre = g.dameGrupo();
	}*/
	
	 public void union(Grupo g)
	{
		if(this.dameGrupo() != g.dameGrupo()){//si no tienen el mismo padre (son arboles distintos)
			if (this.profundidad <= g.profundidad){
				 this.dameGrupo().padre = g.dameGrupo();
				 this.profundidad = Math.max(this.profundidad, g.profundidad+1);
				}
			else if (this.profundidad > g.profundidad){
				g.dameGrupo().padre = this.dameGrupo();
				g.profundidad = Math.max(this.profundidad+1, g.profundidad);
				}
			
		}	
	}
	 
	public boolean equals(Object o){
		if (dameGrupo() == ((Grupo) o).dameGrupo()) return true;
		else return false;
	}
}

