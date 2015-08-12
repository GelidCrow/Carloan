package utility;

public class ParametriFXML {
	private String titolo;
	private boolean ridimensionabile;
	
	public ParametriFXML(String titolo, boolean ridimensionabile) {
		this.titolo = titolo;
		this.ridimensionabile = ridimensionabile;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public boolean isRidimensionabile() {
		return ridimensionabile;
	}

	public void setRidimensionabile(boolean ridimensionabile) {
		this.ridimensionabile = ridimensionabile;
	}	
}
