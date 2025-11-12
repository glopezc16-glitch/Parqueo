
package paquete1;

/**
 *
 * @author Shily
 */

    public class PagoTarjeta extends Pago{
        
        private String numeroTarjeta;
        private String nombreTitular;
        public PagoTarjeta(int idPago, double monto, String numeroTarjeta, String nombreTitular){
            super(idPago, "TARJET", monto);
            this.numeroTarjeta = numeroTarjeta;
            this.nombreTitular = nombreTitular;
        }
        @Override
        public boolean procesarPago(){return validarPago() && numeroTarjeta != null;}
    }
            
            

