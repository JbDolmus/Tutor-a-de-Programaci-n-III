
package domain;


public class Venta {
    private Cliente elCliente;
    private Vendedor elVendedor;
    private Articulo elArticulo;

    public Cliente getElCliente() {
        return elCliente;
    }

    public void setElCliente(Cliente elCliente) {
        this.elCliente = elCliente;
    }

    public Vendedor getElVendedor() {
        return elVendedor;
    }

    public void setElVendedor(Vendedor elVendedor) {
        this.elVendedor = elVendedor;
    }

    public Articulo getElArticulo() {
        return elArticulo;
    }

    public void setElArticulo(Articulo elArticulo) {
        this.elArticulo = elArticulo;
    }
}
