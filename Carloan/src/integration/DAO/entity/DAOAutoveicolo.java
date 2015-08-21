package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import Errori.AlertView;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Noleggio.Contratto;


public class DAOAutoveicolo implements DAO{
private DaoFactory dao;
public DAOAutoveicolo(DaoFactory dao) {
	this.dao=dao;
}

	@Override
	public void creazione(Entity x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Autoveicolo> getAll(){
		
			 String readQuery = "Select * from Autoveicolo";

			 Connection connection= Connection.getConnection(dao);
		        
		     ResultSet readQueryResultSet = null;
		     List<Contratto> risultato = null;
		     try {
				readQueryResultSet = connection.executeRead(readQuery);	
				risultato= creaElencoContratti(readQueryResultSet);
			 } catch (SQLException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				AlertView.getAlertView("Non è stato possibile leggere i contratti" , AlertType.ERROR);
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

}
