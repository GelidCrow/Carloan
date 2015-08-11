package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import business.entity.Entity;
import business.entity.Luoghi.Agenzia;


import static utility.QueryStringReplacer.queryReplaceFirst;

public class DAOAgenzia implements DAO{

	
	private  DaoFactory daofactory;
	
	
	DAOAgenzia(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public void creazione(Entity x) {

		String INSERT = "INSERT INTO Agenzia values('?','?','?','?');";
		String insertQuery = INSERT;
				
		Agenzia agenzia= (Agenzia)x;

        insertQuery = queryReplaceFirst(insertQuery, agenzia.getIDAgenzia());
        
        insertQuery= queryReplaceFirst(insertQuery,agenzia.getNumTelefono());
        
        insertQuery= queryReplaceFirst(insertQuery,agenzia.getNome());
        
        insertQuery= queryReplaceFirst(insertQuery,agenzia.getIDDitta());
        
        Connection connection= Connection.getConnection(daofactory);
        
		try {
			ResultSet idList = connection.executeUpdate(insertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Entity lettura() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException{
		Agenzia a= new Agenzia("A5","1234567891","SedeOtr","Crln");
		DAO x = new DAOAgenzia(DaoFactory.getDaoFactory(1));
		x.creazione(a);
	}

}
