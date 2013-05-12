package Algorithm;

public class Grupo {
    private Grupo padre;
    private int profundidad;
    
    public Grupo(){
        profundidad=0;
        padre=null;
    }
    public Grupo(Grupo p, int pro){
        this.padre=p;
        this.profundidad=pro;
    }
    
    public Grupo dameGrupo(){
        if(this.padre == null)
        	{
            	return this;
        	}
        else
        	{
            return padre.dameGrupo();
        	}

    }
    public void union(Grupo g){
        Grupo g1 = dameGrupo(); //nos da el padre del grupo
        Grupo g2 = g.dameGrupo();
        if (g1.getprofundidad() > g2.getprofundidad()) 
        	{
        		g2.setPadre(g1);
        	} 
        else if (g1.getprofundidad()<g2.getprofundidad()) 
        	{
        		g1.setPadre(g2);
        	}
        else
        	{ //Si es la misma profundidadundidad
            	g1.setPadre(g2);
            	g2.setprofundidad(g2.getprofundidad()+1);
        	}
        
    }

    private void setprofundidad(int i) {
        profundidad=i;
    }

    private void setPadre(Grupo g1) {
        padre=g1;
    }

    private int getprofundidad() {
        return profundidad;
    }
}

