package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;

import java.sql.ResultSet;
import java.sql.SQLException;


import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;
import business.entity.Entity;
import business.entity.Login;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;


public class DAOLogin implements DAO{
	

	private  DaoFactory daofactory;
	
	
	public DAOLogin(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	
	@Override
	public void creazione(Entity x){
		String INSERT = "INSERT INTO Credenziali values('?','?','?','?','?','?');";
		
		String insertQuery = INSERT;
				
		Login login= (Login)x;

        insertQuery = queryReplaceFirst(insertQuery, login.getUsername());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getPassword());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getSupA());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getSupS());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getAmministratore());
        
        insertQuery= queryReplaceFirst(insertQuery,login.getOperatore());
 
        
        Connection connection= Connection.getConnection(daofactory);
        ResultSet idList = null;
		try {
			 idList = connection.executeUpdate(insertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				idList.close();
				//connection.chiudiConnessione();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	

	@SuppressWarnings("resource")
	public Entity  autenticazione(Entity x){
		Login login= (Login)x;
		
		String Autenticazione = "select IDSupervisoreAgenzia,IDSupervisoreSede,IDAmministratore,IDOperatore from Credenziali where Username='?' and password='?'";
		
		String insertQuery = Autenticazione;
	

        insertQuery = queryReplaceFirst(insertQuery, login.getUsername());
        
        insertQuery = queryReplaceFirst(insertQuery, login.getPassword());
        
		Connection connection= Connection.getConnection(daofactory);
        Entity result = null;
		ResultSet idList = null;
		//se non è vuoto il resultset.
		try {
			idList = connection.executeRead(insertQuery);
			
			if(idList.next()){
				
				if(idList.getString(1)!=null){//è un supervisoreagenzia
					String auth="Select IDSupervisoreAgenzia,Nome,Cognome,IDAgenzia from SupervisoreAgenzia where IDSupervisoreAgenzia='"+idList.getString(1)+"'";
					idList=connection.executeRead(auth);
					idList.next();
					result=new SupervisoreAgenzia(idList.getString(2),idList.getString(3),idList.getString(1));
				}
				
				else if(idList.getString(2)!=null){//è un supervisore sede
					String auth="Select IDSupervisoreSede,Nome,Cognome,IDSede from SupervisoreSede where IDSupervisoreSede='"+idList.getString(2)+"'";
					idList=connection.executeRead(auth);
					idList.next();
					result=new SupervisoreSede(idList.getString(2),idList.getString(3),idList.getString(1));
				}
				else if(idList.getString(3)!=null){// è un amministratore
					String auth="Select IDAmministratore,Nome,Cognome,IDDitta from Amministratore where IDAmministratore='"+idList.getString(3)+"'";
					idList=connection.executeRead(auth);
					idList.next();
					result=new Amministratore(idList.getString(2),idList.getString(3),idList.getString(1));
				}
				else if(idList.getString(4)!=null){// è un operatore
					String auth="Select IDOperatore,Nome,Cognome,IDSede from Operatore where IDOperatore='"+idList.getString(4)+"'";
					idList=connection.executeRead(auth);
					idList.next();
					result=new Operatore(idList.getString(2),idList.getString(3),idList.getString(1));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(idList!=null)
				try {
					idList.close();	
					//connection.chiudiConnessione();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return  result;
	}
}
