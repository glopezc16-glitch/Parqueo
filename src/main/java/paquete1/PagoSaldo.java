
package paquete1;

/**
 *
 * @author Shily
 */
public class PagoSaldo extends Pago {
    private double saldoDisponible;
    public PagoSaldo(int idPago, double monto, double saldoDisponible) {
        super(idPago, "SALDO", monto);
        this.saldoDisponible = saldoDisponible;
    }
    @Override
    public boolean procesarPago() {
        if (!validarPago()) return false;
        if (saldoDisponible >= monto) {
            saldoDisponible -= monto;
            return true;
        }
        return false;
    }
}
