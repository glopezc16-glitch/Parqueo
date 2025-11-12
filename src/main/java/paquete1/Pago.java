
package paquete1;

/**
 *
 * @author Shily
 */

import java.time.LocalDateTime;

public abstract class Pago {
    protected int idPago;
    protected String tipoPago;
    protected double monto;
    protected LocalDateTime fechaPago;
    
    public Pago(int idPago, String tipoPago, double monto){
        
        this.idPago = idPago ;
        this.tipoPago = tipoPago;
        this.monto = monto;
        this.fechaPago = LocalDateTime.now();
        
    }
    
    public abstract boolean procesarPago();
    public boolean validarPago(){return monto > 0;}
    
}
