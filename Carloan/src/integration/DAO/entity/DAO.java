package integration.DAO.entity;

import business.entity.Entity;

public interface DAO {
		public void creazione(Entity x );
		public void aggiornamento(Entity x);
		public Entity lettura();
}
