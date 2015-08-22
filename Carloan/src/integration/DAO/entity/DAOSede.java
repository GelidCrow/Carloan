package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;

public class DAOSede implements DAO {

	private DaoFactory dao;
	
	public DAOSede(DaoFactory dao){
		this.dao=dao;
	}
	
	@Override
	public void creazione(Entity x) {
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
	public ArrayList<Sede> getAll(){
		String readQuery = "Select * from Sede";

		 Connection connection= Connection.getConnection(dao);
	        
	     ResultSet readQueryResultSet = null;
	     ArrayList<Sede> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoSedi(readQueryResultSet);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le sedi" , AlertType.ERROR);
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

	private ArrayList<Sede> creaElencoSedi(ResultSet r) {
		ArrayList<Sede> lesedi=new ArrayList<Sede>();
		if(r!=null){
				try {
					while(r.next()){
						lesedi.add(new Sede(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getInt(5)));	
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return lesedi;
	}
}
