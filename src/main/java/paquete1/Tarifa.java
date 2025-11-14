
package paquete1;

/**
 *
 * @author Shily
 */
public class Tarifa {
    private int idTarifa;
    private String modo; //no debo olvidar que esto es lo de flat y variable 
    private double montoFijo; 
    private double montoPorHora;
    private double montoPorFraccion; //me refiero a fraccion de tiempo
    
    public Tarifa(int idTarifa, String modo, double montoFijo, double montoPorHora , double montoPorFraccion){
        this.idTarifa = idTarifa; 
        this.modo = modo;
        this.montoFijo = montoFijo;
        this.montoPorHora = montoPorHora;
        this.montoPorFraccion = montoPorFraccion;
         
    }
    
    public double calcularMonto(Ticket t){
        if (t.getFechaSalida()== null) return 0.0;
        long minutos = t.calcularTiempo();
        if("FLAT".equalsIgnoreCase(modo)){
            return montoFijo;
        } else{
            double horas = minutos / 60.0;
            return montoPorHora * horas;
        }
        
        
    }
    
    public String getModo(){ return modo; }
    public int getIdTarifa(){ return idTarifa; }
    
    
}
