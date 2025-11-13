/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete1;

/**
 *
 * @author Shily
 */
public class PagoEfectivo extends Pago {
    public PagoEfectivo(int idPago, double monto){super (idPago, "EFECTIVO", monto);}
    @Override
    public boolean procesarPago(){return validarPago();}
}
