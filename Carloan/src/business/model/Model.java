package business.model;

import business.entity.Entity;

public interface Model{
	public void Inserimento(Entity parameter);
	public void aggiornamento(Entity parameter);
	public void lettura();
	public void ricerca();
}
