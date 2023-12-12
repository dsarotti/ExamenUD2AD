package constructor;

import java.util.ArrayList;

/**
 * Clase que representa una obra de un edificio
 * @author Adrián
 */
public class Obra {

    /** El nombre de la construcción */
    private String nombre;
    /** Los materiales necesarios para su construcción */
    private Materiales mat;
    /** Los materiales usados */
    private Materiales cons;
    
    /**
     * Constructor básico
     * @param nombre El nombre de la construcción
     * @param mat Los materiales necesarios para su construcción
     */
    public Obra(String nombre, Materiales mat)
    {
        this.nombre = nombre;
        this.mat = mat;
        this.cons = new Materiales(0, 0, 0, 0);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Materiales getMat() {
        return mat;
    }

    public void setMat(Materiales mat) {
        this.mat = mat;
    }
    
    /**
     * Construye con los materiales facilitados.
     * @param mats Los materiales recibidos 
     */
    public void construir(Materiales mats)
    {
        int cant = mats.getLadrillos() + this.cons.getLadrillos();
        if(cant >= this.mat.getLadrillos())
        {
            this.cons.setLadrillos(this.mat.getLadrillos());
        }else{
            this.cons.setLadrillos(cant);
        }

        cant = mats.getAcero() + this.cons.getAcero();
        if(cant >= this.mat.getAcero())
        {
            this.cons.setAcero(this.mat.getAcero());
        }else{
            this.cons.setAcero(cant);
        }

        cant = mats.getCemento() + this.cons.getCemento();
        if(cant >= this.mat.getCemento())
        {
            this.cons.setCemento(this.mat.getCemento());
        }else{
            this.cons.setCemento(cant);
        }

        cant = mats.getCristal() + this.cons.getCristal();
        if(cant >= this.mat.getCristal())
        {
            this.cons.setCristal(this.mat.getCristal());
        }else{
            this.cons.setCristal(cant);
        }
    }
    
    public void status()
    {
        System.out.println(this.toString());
    }
    
    @Override
    public String toString()
    {
        String str = "=============== " + this.nombre + " ===============\n" +
                    "Ladrillos: " + this.cons.getLadrillos() + "/" + this.mat.getLadrillos() + "\n" +
                    "Acero: " + this.cons.getAcero() + "/" + this.mat.getAcero() + "\n" +
                    "Cemento: " + this.cons.getCemento() + "/" + this.mat.getCemento() + "\n" +
                    "Cristal: " + this.cons.getCristal() + "/" + this.mat.getCristal() + "\n" +
                    "===========================================";
        return str;
    }
}
