package business.entity.pagamento;

import java.util.Date;

public class CartaDiCredito extends Pagamento{
	private String IDCliente ; 
	private Date dataScadenza;
	
	public CartaDiCredito(float depositoCauzinale, float importo,
		float detrazioneAggiuntiva) {
	super(depositoCauzinale, importo, detrazioneAggiuntiva);
	}
	
	public String getIDCliente() {
		return IDCliente;
	}
	public void setIDCliente(String iDCliente) {
		IDCliente = iDCliente;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
		
}
