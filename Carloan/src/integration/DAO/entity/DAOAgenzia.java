package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import business.entity.Entity;
import business.entity.Luoghi.Agenzia;


import static utility.QueryStringReplacer.queryReplaceFirst;

public class DAOAgenzia implements DAO{

	
	private DaoFactory daofactory;
	
	
	DAOAgenzia(DaoFactory dao){
		this.daofactory = daofactory;		
	}
	
	@Override
	public void creazione(Entity x) {

		String INSERT = "insert into Agenzia values(?,?,?,?,?);";
				
		Agenzia agenzia= (Agenzia)x;

        String insertQuery = queryReplaceFirst(INSERT, agenzia.getIDAgenzia());
        
        insertQuery = queryReplaceFirst(INSERT, agenzia.getIndirizzo());
        
        insertQuery= queryReplaceFirst(INSERT,agenzia.getNumTelefono());
        
        insertQuery= queryReplaceFirst(INSERT,agenzia.getNome());
        
        insertQuery= queryReplaceFirst(INSERT,agenzia.getIDDitta());
        
        Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = connection.execute(insertQuery);
    
	}

	@Override
	public void aggiornamento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lettura() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args){
		
	}

}
