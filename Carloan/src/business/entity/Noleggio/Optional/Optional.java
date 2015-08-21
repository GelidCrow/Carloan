package business.entity.Noleggio.Optional;

import business.entity.Entity;

public abstract class Optional extends Entity {
	private float prezzo;
	private String descrizione;
	private Integer id;
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Optional(Integer id,float prezzo, String descrizione,String nome) {
		super();
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.id = id;
		this.nome=nome;
	}
	
	public int hashCode(){
		return id;
	}
	 
    public boolean equals(Object x){
    	if((this.id==((Optional)x).getId())){
    		return true;
    	}
    	return false;
    }
  
	
}
