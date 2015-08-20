package business.model;

import business.entity.Entity;
import business.model.Exception.CommonException;

public interface Model{
	public void Inserimento(Entity parameter);
	public void aggiornamento(Entity parameter) throws CommonException;
	public void lettura();
	public void ricerca();
}
