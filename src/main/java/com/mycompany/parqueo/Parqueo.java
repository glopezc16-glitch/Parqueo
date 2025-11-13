/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.parqueo;

/**
 *
 * @author Shily
 */
import paquete1.*;
import java.util.*;

public class Parqueo {

    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        new Interfaz().setVisible(true);
    });
}
       
        private List<Area> listaAreas = new ArrayList<>();
    private List<Vehiculo> listaVehiculos = new ArrayList<>();
        private List<Ticket> listaTickets = new ArrayList<>();
        private String modoTarifa = "Variable";
        
        public Parqueo(){
            
            listaAreas.add(new Area("A", "Area A",5));
            listaAreas.add(new Area("B", "Area B", 3));
        }
        
        public boolean registrarEntrada(String placa, String tipoVehiculo, Persona propietario,Tarifa tarifa){
            
            Vehiculo v = findVehiculoByPlaca(placa);
            if (v == null){
                v = new Vehiculo(placa, tipoVehiculo, propietario.getTipo());
                listaVehiculos.add(v);
                propietario.asociarVehiculo(v);
            }
            
            for (Area a : listaAreas) {
            if (a.hayEspacioDisponible()) {
                Spot s = a.asignarEspacio(tipoVehiculo);
                if (s != null) {
                    Ticket t = new Ticket(v, a, s, tarifa);
                    listaTickets.add(t);
                    return true;
                }
        } 
        
    }
            return false;
        }    
        public boolean registrarSalida(String placa) {
        Ticket t = findActiveTicketByPlaca(placa);
        if (t != null) {
            t.cerrarTicket();
            return true;
        }
        return false;
    }
        public List<Area> getListaAreas(){ return listaAreas; }
    public List<Ticket> getListaTickets(){ return listaTickets; }

    private Vehiculo findVehiculoByPlaca(String placa) {
        return listaVehiculos.stream().filter(v -> v.getPlaca().equalsIgnoreCase(placa)).findFirst().orElse(null);
    }

    private Ticket findActiveTicketByPlaca(String placa) {
        return listaTickets.stream().filter(tt -> tt.getVehiculo().getPlaca().equalsIgnoreCase(placa) && "ACTIVO".equals(tt.getEstado())).findFirst().orElse(null);
    }
            
}
