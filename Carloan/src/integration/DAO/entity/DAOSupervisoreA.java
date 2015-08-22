package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert.AlertType;
import Errori.AlertView;
import business.entity.Entity;
import business.entity.Gestori.SupervisoreAgenzia;

public class DAOSupervisoreA implements DAO{

	
	@Override
	public void creazione(Entity x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}


	private  DaoFactory daofactory;
	
	
	public DAOSupervisoreA(DaoFactory dao){
		this.daofactory = dao;		
	}
	@Override
	public Entity lettura(int id){
	String QUERY= "Select * from SupervisoreAgenzia where idSupervisoreAgenzia='?' ";
	 Connection connection= Connection.getConnection(daofactory);
	 
	 String readQuery = QUERY;
	 		 
	 readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
	 
     ResultSet readQueryResultSet = null;
     SupervisoreAgenzia  risultato = null;
     try {
		readQueryResultSet = connection.executeRead(readQuery);	
		if(readQueryResultSet.next()){
			risultato= ottieniSupA(readQueryResultSet);
		}
	 } catch (SQLException e) {
		e.printStackTrace();
		AlertView.getAlertView("Non è stato possibile leggere il supervisore dell'agenzia " , AlertType.ERROR);
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
	
	private SupervisoreAgenzia ottieniSupA(ResultSet resultset) throws SQLException{
		 return new SupervisoreAgenzia(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getDate(5),resultset.getString(6),
				 	resultset.getString(7),resultset.getString(8),resultset.getString(9),resultset.getBoolean(10),resultset.getInt(11));
	}


}
