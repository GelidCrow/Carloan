package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import Errori.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.model.Exception.CommonException;


public class DAONoleggio implements DAO{
	
	
	private  DaoFactory daofactory;

	public DAONoleggio(DaoFactory dao){
		this.daofactory = dao;		
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

	public List<Noleggio> getNoleggiAperti(int idContratto){
		 String readQuery = "Select * from noleggio where idcontratto='?' and (stato='Aperto' or stato='rientro' or stato='uscita')";

		 
		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Contratto> risultato = null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoNoleggi(readQueryResultSet);
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
