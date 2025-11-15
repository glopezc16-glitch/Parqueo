
package paquete1;

/**
 *
 * @author Shily
 */
import java.util.ArrayList;
import java.util.List;

public class Area {
    
    private String idArea;
    private String nombre;
    private int capacidadMaxima;
    private int ocupacionActual = 0;
    private List<Spot> spots = new ArrayList<>();
    
    public Area(String idArea, String nombre, int capacidadMaxima){
    this.idArea = idArea;
    this.nombre = nombre;
    this.capacidadMaxima = capacidadMaxima;

    
    String tipoPorDefecto;
    if ("Motos".equalsIgnoreCase(nombre)) {
        tipoPorDefecto = "Moto";   // para el cobo box
    } else {
        tipoPorDefecto = "Auto";   
    }

    for (int i = 1; i <= capacidadMaxima; i++){
        spots.add(new Spot(idArea + "-" + i, "FREE", tipoPorDefecto, this));
    }
}
    
    public boolean hayEspacioDisponible(){
        return ocupacionActual < capacidadMaxima;
    }
    
    public Spot asignarEspacio(String tipoVehiculo){
        for (Spot s : spots){
            if ("FREE".equals(s.getEstado()) && (s.getTipoVehiculo().equals(tipoVehiculo) || s.getTipoVehiculo().equals("ALL"))){
               s.ocupar();
               ocupacionActual++;
               return s;
            }
        }
        return null;
    }
    public void liberarEspacio(Spot s){
        if (s != null && "OCUPADO".equals(s.getEstado())){
            s.liberar();
            ocupacionActual = Math.max(0, ocupacionActual - 1);
        }
    }
    public String getIdArea(){ return idArea; }
    public String getNombre(){ return nombre; }
    public int getCapacidadMaxima(){ return capacidadMaxima; }
    public int getOcupacionActual(){ return ocupacionActual; }
    public List<Spot> getSpots(){ return spots; }
    
}
