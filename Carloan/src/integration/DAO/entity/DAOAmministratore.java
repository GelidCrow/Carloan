package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.model.Exception.CommonException;

public class DAOAmministratore implements DAO{
	private  DaoFactory daofactory;
	
	
	public DAOAmministratore(DaoFactory dao){
		this.daofactory = dao;		
	}
	

	@Override
	public ResultSet creazione(Entity x) {
		return null;
		
	}

	@Override
	public void aggiornamento(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Entity lettura(int id){
	String QUERY= "Select * from SupervisoreAgenzia where idSupervisoreAgenzia='?' ";
	 Connection connection= Connection.getConnection(daofactory);
	 
	 String readQuery = QUERY;
	 		 
	 readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
	 
     ResultSet readQueryResultSet = null;
     Amministratore  risultato = null;
     try {
		readQueryResultSet = connection.executeRead(readQuery);	
		if(readQueryResultSet.next()){
			risultato= ottieniAmministratore(readQueryResultSet);
		}
	 } catch (SQLException e) {
		e.printStackTrace();
		AlertView.getAlertView("Non è stato possibile leggere l'amministratore" , AlertType.ERROR);
	 }
	 finally{
		try {
			readQueryResultSet.close();
			//connection.chiudiConnessione();
			} catch (SQLException e) {
				e.printStackTrace();
		}
	}
    return risultato;
	}
	
	private Amministratore ottieniAmministratore(ResultSet resultset) throws SQLException{
		 return new Amministratore(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getDate(5),resultset.getString(6),
				 	resultset.getString(7),resultset.getString(8),resultset.getString(9),resultset.getBoolean(10),resultset.getInt(11));
	}

}
