
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
				
				if(idList.getString(4)!=null){//è un supervisoreAgenzia
					DAOSupervisoreA daoSupervisoreA ;
					daoSupervisoreA= (DAOSupervisoreA) daofactory.getDao("DAOSupervisoreA");
					result= daoSupervisoreA.lettura(idList.getInt(4));
				}
				
				else if(idList.getInt(5)>0){//è un supervisore sede
					DAOSupervisoreS daoSupervisoreS ;
					daoSupervisoreS= (DAOSupervisoreS) daofactory.getDao("DAOSupervisoreS");
					result=daoSupervisoreS.lettura(idList.getInt(5));
				}
				else if(idList.getInt(6)>0){// è un amministratore
					DAOAmministratore daoAmministratore ;
					daoAmministratore= (DAOAmministratore) daofactory.getDao("DAOAmministratore");
					result=daoAmministratore.lettura(idList.getInt(6));
				}
				else if(idList.getString(7)!=null){// è un operatore
					DAOOperatore daoOperatore;
					daoOperatore= (DAOOperatore) daofactory.getDao("DAOOperatore");
					result=daoOperatore.lettura(idList.getInt(7));
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


	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
