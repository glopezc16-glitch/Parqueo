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

import java.time.Duration;
import java.time.LocalDateTime;

public class Parqueo {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Interfaz().setVisible(true);
        });
    }

    
    private List<Area> listaAreas = new ArrayList<>();
    private List<Vehiculo> listaVehiculos = new ArrayList<>();
    private List<Ticket> listaTickets = new ArrayList<>();
    private List<Persona> listaPersonas = new ArrayList<>();
    private String modoTarifa = "Variable"; 

    
    public Parqueo() {
        listaAreas.add(new Area("A", "Area A", 100));
        listaAreas.add(new Area("B", "Area B", 100));
    }

    public boolean registrarEntrada(String placa, String tipoVehiculo, Persona propietario, Tarifa tarifa) {

        if (propietario != null && propietario.getCarnet() != null && !propietario.getCarnet().isEmpty()) {
            Persona yaRegistrada = findPersonaByCarnet(propietario.getCarnet());
            if (yaRegistrada != null) {
                propietario = yaRegistrada;
            } else {
                listaPersonas.add(propietario);
            }
        }

        Vehiculo v = findVehiculoByPlaca(placa);
        if (v == null) {
            v = new Vehiculo(placa, tipoVehiculo, propietario != null ? propietario.getTipo() : "DESCONOCIDO");
            listaVehiculos.add(v);
            if (propietario != null) {
                propietario.asociarVehiculo(v);
            }
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
    
    private void limpiarReservasExpiradas() {
    LocalDateTime now = LocalDateTime.now();
    for (Ticket t : listaTickets) {
        if ("FLAT_RESERVADO".equalsIgnoreCase(t.getEstado()) && t.getFechaSalida() != null) {
            long minutos = Duration.between(t.getFechaSalida(), now).toMinutes();
            if (minutos > 120) {
                Spot s = t.getSpot();
                Area a = t.getArea();
                if (s != null && a != null) {
                    a.liberarEspacio(s);
                }
                t.setEstado("CERRADO");
            }
        }
    }
}

    public boolean registrarEntradaRecurrentePorPlaca(String placa, Tarifa tarifa) {

        Vehiculo v = findVehiculoByPlaca(placa);
        if (v == null) {
            return false; 
        }

        String tipoVehiculo = v.getTipoVehiculo();

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

    public boolean intentarReingresoFlat(String placa) {
        limpiarReservasExpiradas();           // por si ya se vencieron reservas
        LocalDateTime now = LocalDateTime.now();

        for (int i = listaTickets.size() - 1; i >= 0; i--) {
            Ticket t = listaTickets.get(i);

            if (t.getVehiculo().getPlaca().equalsIgnoreCase(placa)
                && "FLAT".equalsIgnoreCase(t.getTarifa().getModo())
                && "FLAT_RESERVADO".equalsIgnoreCase(t.getEstado())
                && t.getFechaSalida() != null) {

                long mins = Duration.between(t.getFechaSalida(), now).toMinutes();

                if (mins <= 120) { // dentro de las 2 horas
                    t.setEstado("ACTIVO");
                    Spot s = t.getSpot();
                    if (s != null) {
                        s.ocupar();  // de RESERVADO a OCUPADO
                    }
                    return true;     // reingreso SIN cobro
                }
            }
        }

        return false; // no aplica reingreso gratis
    }
    
    public List<Area> getListaAreas() {
        return listaAreas;
    }

    public List<Ticket> getListaTickets() {
        return listaTickets;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public int getTotalSpotsDisponibles() {
    int total = 0;
    for (Area a : listaAreas) {
        total += a.getCapacidadMaxima() - a.getOcupacionActual();
    }
    return total;
}

    private Persona findPersonaByCarnet(String carnet) {
        if (carnet == null) return null;
        return listaPersonas.stream()
                .filter(p -> carnet.equalsIgnoreCase(p.getCarnet()))
                .findFirst()
                .orElse(null);
    }

    private Vehiculo findVehiculoByPlaca(String placa) {
        if (placa == null) return null;
        return listaVehiculos.stream()
                .filter(v -> placa.equalsIgnoreCase(v.getPlaca()))
                .findFirst()
                .orElse(null);
    }

    private Ticket findActiveTicketByPlaca(String placa) {
        if (placa == null) return null;
        return listaTickets.stream()
                .filter(tt -> placa.equalsIgnoreCase(tt.getVehiculo().getPlaca())
                           && "ACTIVO".equalsIgnoreCase(tt.getEstado()))
                .findFirst()
                .orElse(null);
    }
    
}
