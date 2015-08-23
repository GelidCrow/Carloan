package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Optional.Guidatore;
import business.model.Exception.CommonException;

public class DAOGuidatore implements DAO{
	private  DaoFactory daofactory;

	public DAOGuidatore(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public ResultSet creazione(Entity x) {
		return null;
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
	
	
	public List<Guidatore> getAllByOptional(int idOptional){
		 String readQuery = "Select * from Guidatore where idOptional='?'";

		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Guidatore> risultato = null;
		try {
				readQueryResultSet = connection.executeRead(readQuery);	
				risultato= creaElencoGuidatori(readQueryResultSet);
			
		} catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere gli Optional" , AlertType.ERROR);
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
	
	
	private List<Guidatore> creaElencoGuidatori(ResultSet resultset){
		List<Guidatore> risultato = new LinkedList<>();
		Guidatore guidatore;
        try {
         if(resultset!=null){
            while (resultset.next()) {
            	ottieniGuidatore(resultset);
                guidatore= ottieniGuidatore(resultset);
                risultato.add(guidatore);
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return risultato;
	}
	
	private Guidatore ottieniGuidatore(ResultSet resultset){
	    Guidatore guidatore= new Guidatore();
	    try {
			guidatore.setCodFiscale(resultset.getString(5));
			guidatore.setNome(resultset.getString(2));
			guidatore.setCognome(resultset.getString(3));;
			guidatore.setId(resultset.getInt(1));
			guidatore.setIndirizzo(resultset.getString(4));
			guidatore.setIdOptional(resultset.getInt(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guidatore;
	    
	}
}
