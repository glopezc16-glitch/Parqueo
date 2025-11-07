
package paquete1;

/**
 *
 * @author Shily
 */
public class Vehiculo {
    
    private String placa;
    private String tipoVehiculo;
    private String tipoUsuario;
    private Persona propietario;
    
    public Vehiculo(String placa, String tipoVehiculo, String tipoUsuario){
      this.placa = placa; 
      this.tipoVehiculo = tipoVehiculo;
      this.tipoUsuario = tipoUsuario;
    }
    
    public void registraVehiculo(){
        
    }
    
    public String consultarVehiculo(){
       return String.format("Vehiculo[placa=%s, tipo=%s, usuario=%s]", placa, tipoVehiculo, tipoUsuario);
       
    }
    
    public String getPlaca(){ return placa; }
    public String getTipoVehiculo(){ return tipoVehiculo;}
    public String getTipoUsuario(){return tipoUsuario;}
    public Persona getPropietario(){return propietario;}
    public void setPropietario(Persona p){this.propietario = p;}
}
