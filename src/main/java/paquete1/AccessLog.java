/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete1;

/**
 *
 * @author Shily
 */
import java.time.LocalDateTime;

public class AccessLog {
    private int idLog;
    private String placa;
    private String accion; 
    private String resultado; 
    private String razon;
    private LocalDateTime fecha;

    public AccessLog(int idLog, String placa, String accion, String resultado, String razon) {
        this.idLog = idLog;
        this.placa = placa;
        this.accion = accion;
        this.resultado = resultado;
        this.razon = razon;
        this.fecha = LocalDateTime.now();
    }

    public void registrarEvento() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format("[%s] Log:%d placa=%s accion=%s resultado=%s razon=%s",
                fecha, idLog, placa, accion, resultado, razon);
    }
}
