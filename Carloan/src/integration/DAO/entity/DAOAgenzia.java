package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;

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

		String INSERT = "insert into Agenzia values('?','?','?','?');";
				
		Agenzia agenzia= (Agenzia)x;

        String insertQuery = queryReplaceFirst(INSERT, agenzia.getIDAgenzia());
        
        insertQuery= queryReplaceFirst(INSERT,agenzia.getNumTelefono());
        
        insertQuery= queryReplaceFirst(INSERT,agenzia.getNome());
        
        insertQuery= queryReplaceFirst(INSERT,agenzia.getIDDitta());
        
        Connection connection= Connection.getConnection(daofactory);
        System.out.println(insertQuery);
        ResultSet idList = connection.execute(insertQuery);
    
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
		Agenzia a= new Agenzia("A3","Viale x","1234567891","SedeOtranto","crln");
		
		
		DAO x = new DAOAgenzia(DaoFactory.getDaoFactory(1));
		
		x.creazione(a);
		
		
	}



}
