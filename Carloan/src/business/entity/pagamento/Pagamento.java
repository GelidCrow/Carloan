package business.entity.pagamento;

public abstract class Pagamento {
   private float depositoCauzinale;
   private float importo;
   private float detrazioneAggiuntiva;
   
	public float getDepositoCauzinale() {
		return depositoCauzinale;
	}
	public void setDepositoCauzinale(float depositoCauzinale) {
		this.depositoCauzinale = depositoCauzinale;
	}
	public float getImporto() {
		return importo;
	}
	public void setImporto(float importo) {
		this.importo = importo;
	}
	public float getDetrazioneAggiuntiva() {
		return detrazioneAggiuntiva;
	}
	public void setDetrazioneAggiuntiva(float detrazioneAggiuntiva) {
		this.detrazioneAggiuntiva = detrazioneAggiuntiva;
	}
		public Pagamento(float depositoCauzinale, float importo,
				float detrazioneAggiuntiva) {
			super();
			this.depositoCauzinale = depositoCauzinale;
			this.importo = importo;
			this.detrazioneAggiuntiva = detrazioneAggiuntiva;
		}
	   
}
