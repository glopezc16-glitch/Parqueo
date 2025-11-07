
package paquete1;

/**
 *
 * @author Shily
 */

public class Spot {
    private String idSpot;
    private String estado;
    private String tipoVehiculo;
    private Area area;
    
    public Spot(String idSpot, String estado, String tipoVehiculo, Area area){
        this.idSpot = idSpot;
        this.estado = estado;
        this.tipoVehiculo = tipoVehiculo;
        this.area = area;
    }
    public void ocupar(){
        this.estado = "OCUPADO";
        
    }
    public void liberar(){
        this.estado = "FREE";
    }
    public String getIdSpot(){ return idSpot; }
    public String getEstado(){ return estado; }
    public String getTipoVehiculo(){ return tipoVehiculo; }
    public Area getArea(){ return area; }
    
}
