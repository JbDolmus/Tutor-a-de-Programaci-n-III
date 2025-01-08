
package domain;


public class Cliente extends Persona {
    private String codigoCliente;
    private String direccionCliente;
    private String tipoCliente;

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	@Override
	public String toString() {
		return "Cliente [codigoCliente=" + codigoCliente + ", direccionCliente=" + direccionCliente + ", tipoCliente="
				+ tipoCliente + "]";
	}

	
    
    
}
       