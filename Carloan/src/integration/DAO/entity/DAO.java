package integration.DAO.entity;

import business.entity.Entity;
import business.model.Exception.CommonException;

public interface DAO {
		public abstract void creazione(Entity x );
		public abstract void aggiornamento(Entity entity) throws CommonException;
		public abstract Entity lettura(int id);
}
