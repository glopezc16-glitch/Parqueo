/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete1;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Shily
 */
public class Persona {
    
    private int idPersona;
    private String nombre; 
    private String carnet;
    private String tipo; //aqui debo asignar si es estudiante o catedratico
    private List<Vehiculo> vehiculos = new ArrayList<>();
    
    
    public Persona (int idPersona, String nombre, String carnet, String tipo){
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.carnet = carnet;
        this.tipo = tipo;
    }
    
    public void asociarVehiculo(Vehiculo v){
    if ( v != null && !vehiculos.contains(v)){
        vehiculos.add(v);
        v.setPropietario(this);
    } 
    }

    public String consultarDatos(){
        return String.format("Persona[id=%d, nombre=%s, carnet=%s, tipo=%s vehiculos=%d]",idPersona, nombre, carnet, tipo, vehiculos.size());
        
    }
    
    public int getIdPersona(){ return idPersona; }
    public String getNombre(){ return nombre; }
    public String getCarnet(){ return carnet; }
    public String getTipo(){ return tipo; }
    public List<Vehiculo> getVehiculos(){ return vehiculos; }
}
