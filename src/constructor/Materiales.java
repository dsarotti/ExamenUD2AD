package constructor;

/**
 * Clase que representa los materiales de una construcción
 * @author Adrián
 */
public class Materiales {
    
    /** Número de palés de ladrillos */
    private int ladrillos;
    /** Número de palés de acero */
    private int acero;
    /** Número de palés de cemento */
    private int cemento;
    /** Número de palés de cristal */
    private int cristal;
    
    /**
     * Constructor básico
     * @param ladrillos palés de ladrillos
     * @param acero palés de acero
     * @param cemento palés de cemento
     * @param cristal palés de cristal
     */
    public Materiales(int ladrillos, int acero, int cemento, int cristal)
    {
        this.ladrillos = ladrillos;
        this.acero = acero;
        this.cemento = cemento;
        this.cristal = cristal;
    }
    
    /**
     * Constructor por defecto
     */
    public Materiales()
    {
        this(0,0,0,0);
    }
    
    public int getLadrillos() {
        return ladrillos;
    }

    public void setLadrillos(int ladrillos) {
        this.ladrillos = ladrillos;
    }

    public int getAcero() {
        return acero;
    }

    public void setAcero(int acero) {
        this.acero = acero;
    }

    public int getCemento() {
        return cemento;
    }

    public void setCemento(int cemento) {
        this.cemento = cemento;
    }

    public int getCristal() {
        return cristal;
    }

    public void setCristal(int cristal) {
        this.cristal = cristal;
    }
}
