package integration.DAO.entity;

import business.entity.Entity;

public interface DAO {
		public abstract void creazione(Entity x );
		public abstract void aggiornamento(Entity entity);
		public abstract Entity lettura(int id);
}
