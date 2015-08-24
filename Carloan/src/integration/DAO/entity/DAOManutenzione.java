package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import business.entity.Entity;
import business.entity.Auto.manutenzione.*;
import business.model.Exception.CommonException;
import static utility.QueryStringReplacer.queryReplaceFirst;
public class DAOManutenzione implements DAO{

	private  DaoFactory daofactory;
	
	
	public DAOManutenzione(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	
	@Override
	public ResultSet creazione(Entity x) throws CommonException {
		Manutenzione m = null;
		String query="Insert into ? (DataInizio,Note,IDAuto) values('?','?',?)";
		if(x instanceof ManutenzioneOrdinaria){
			query=queryReplaceFirst(query, "manutenzioneordinaria");
			m=(ManutenzioneOrdinaria)x;
		}
		else if(x instanceof ManutenzioneStraordinaria){
			query=queryReplaceFirst(query, "manutenzionestraordinaria");
			m=(ManutenzioneStraordinaria)x;
		}
		
		query=queryReplaceFirst(query, m.getDatainizio().toString());
		query=queryReplaceFirst(query, m.getNote());
		query=queryReplaceFirst(query, String.valueOf(m.getIDAuto()));
		Connection conn=Connection.getConnection(daofactory);
		ResultSet r=null;
		try {
			r=conn.executeUpdate(query);
			if(r!=null)
				throw new CommonException("Manutenzione inserita con successo");
			else
				throw new CommonException("Manutenzione non inserita");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public void aggiornamento(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}

}
