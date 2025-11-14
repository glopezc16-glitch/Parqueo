
package paquete1;

/**
 *
 * @author Shily
 */
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    
    private String idTicket;
    private Vehiculo vehiculo;
    private Area area;
    private Spot spot;
    private Tarifa tarifa;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
    private double monto;
    private String estado;
    
    public Ticket(Vehiculo v, Area area, Spot spot, Tarifa tarifa){
        
        this.idTicket = UUID.randomUUID().toString();
        this.vehiculo = v;
        this.area = area;
        this.spot = spot;
        this.tarifa = tarifa;
        this.fechaIngreso = LocalDateTime.now();
        this.estado = "ACTIVO";
        
    }
    
   public void cerrarTicket() {
    this.fechaSalida = LocalDateTime.now();
    this.monto = tarifa.calcularMonto(this);

    if ("FLAT".equalsIgnoreCase(tarifa.getModo())) {
        // paga Q10, pero queda reservado 2 horas
        this.estado = "FLAT_RESERVADO";
        if (spot != null) {
            spot.reservar();   // necesitas este método en Spot
        }
    } else {
        // VARIABLE: se libera normal
        this.estado = "CERRADO";
        if (spot != null && area != null) {
            area.liberarEspacio(spot);
        }
    }
}
    
    public long calcularTiempo(){
        LocalDateTime end = (fechaSalida != null) ? fechaSalida : LocalDateTime.now();
        return Duration.between(fechaIngreso, end).toMinutes();
       
    }
    
    public void aplicarCobro(){
        this.monto = tarifa.calcularMonto(this);
        this.estado = "PAGADO";
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getIdTicket(){return idTicket;}
    public Vehiculo getVehiculo(){return vehiculo;}
    public Area getArea(){return area;}
    public Spot getSpot(){return spot;}
    public Tarifa getTarifa(){return tarifa;}
    public LocalDateTime getFechaIngreso(){return fechaIngreso;}
    public LocalDateTime getFechaSalida(){return fechaSalida;}
    public double getMonto(){return monto;}
    public String getEstado(){return estado;}
    public void setFechaSalida(LocalDateTime dt){this.fechaSalida = dt;}
}
