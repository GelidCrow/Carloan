package integration.DAO.entity;

import integration.DAO.DaoFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;



import business.entity.Entity;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Auto.Fascia.Lusso;
import business.entity.Auto.Fascia.Suv;
import business.entity.Auto.Fascia.Utlitaria;
import business.model.Exception.CommonException;
import integration.DAO.connection.Connection;
public class DAOFascia implements DAO {
	DaoFactory daofactory;
	public DAOFascia(DaoFactory dao){
		this.daofactory = dao;		
	}
	@Override
	public ResultSet creazione(Entity x) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public LinkedList<Fascia> getAll(){
		String query="Select * from Fascia";
		Connection c=Connection.getConnection(daofactory);
		LinkedList<Fascia> fasce = null;
		try {
			ResultSet r=c.executeRead(query);
			if(r!=null){
				while(r.next()){
					fasce=new LinkedList<Fascia>();
					switch(r.getString(4)){
					case "Suv":
						fasce.add(new Suv(r.getInt(1),r.getFloat(2),r.getString(3),r.getString(4)));
						break;
					case "Lusso":
						fasce.add(new Lusso(r.getInt(1),r.getFloat(2),r.getString(3),r.getString(4)));
						break;
					case "Utilitaria":
						fasce.add(new Utlitaria(r.getInt(1),r.getFloat(2),r.getString(3),r.getString(4)));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fasce;
	}
}
