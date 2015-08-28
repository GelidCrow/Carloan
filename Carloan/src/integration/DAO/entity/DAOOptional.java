package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Optional.Assicurazione_KASKO;
import business.entity.Noleggio.Optional.CateneNeve;
import business.entity.Noleggio.Optional.ChilometraggioIllimitato;
import business.entity.Noleggio.Optional.Gps;
import business.entity.Noleggio.Optional.Guidatore;
import business.entity.Noleggio.Optional.GuidatoreAggiuntivo;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.Seggiolino;
import business.model.Exception.CommonException;

public class DAOOptional implements DAO{
	private  DaoFactory daofactory;

	public DAOOptional(DaoFactory dao){
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
		 String readQuery = "Select Nome,Descrizione,prezzo,LimiteCopertura,numeroSeggiolini,numeroGuidatoriAggiuntivi from Optional where idOptional= '?'";

		Connection connection= Connection.getConnection(daofactory);
		ResultSet readQueryResultSet = null;
		Optional optional =null;
		try {
				readQueryResultSet = connection.executeRead(readQuery);	
				optional= ottieniOptional(readQueryResultSet);
		} catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere gli Optional" , AlertType.ERROR);
		}
		finally{
			try {
				readQueryResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		
		return optional;
		
	}
	
	public List<Optional> getAll(){
		 String readQuery = "Select * from Optional";

		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Optional> risultato = null;
		try {
				readQueryResultSet = connection.executeRead(readQuery);	
				risultato= creaElencoOptional(readQueryResultSet);
			
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
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
	@SuppressWarnings("null")
	public List<Optional> getAllByNoleggio(int id){
		 String readQuery = "Select idoptional from NoleggioOptional where idnoleggio='?'";
		  readQuery = queryReplaceFirst(readQuery, String.valueOf(id));
		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		ResultSet readSingle  = null;
		List<Optional> risultato = null;
		try {
				readQueryResultSet = connection.executeRead(readQuery);	
				while(readQueryResultSet.next()){
					readQuery="Select * from optional where idoptional='?'";
					readQuery = queryReplaceFirst(readQuery, String.valueOf(readQueryResultSet.getInt(1)));
					readSingle= connection.executeRead(readQuery);	
					risultato.add(ottieniOptional(readSingle));
				}
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
	public List<Optional> creaElencoOptional(ResultSet resultset) throws InstantiationException, IllegalAccessException{
	
		List<Optional> risultato = new ArrayList<Optional>();
        try {
         if(resultset!=null){
            while (resultset.next()) {
                risultato.add(ottieniOptional(resultset));
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return risultato;
	}

  	private Optional ottieniOptional(ResultSet resultset){
        try {
			switch (resultset.getString(5)){
				case "Gps": 
					return new Gps(resultset.getInt(1),resultset.getFloat(2),resultset.getString(3),resultset.getString(5));
				case "assicurazionekasko": 	
					return new Assicurazione_KASKO(resultset.getInt(1),resultset.getFloat(2),resultset.getString(3),resultset.getString(5),resultset.getFloat(4));
				case "guidatoreAggiuntivo": 
					return new GuidatoreAggiuntivo(resultset.getInt(1),resultset.getFloat(2),resultset.getString(3),resultset.getString(5),resultset.getInt(7));
				case "chilometraggioIllimitato": 
					return new ChilometraggioIllimitato(resultset.getInt(1),resultset.getFloat(2),resultset.getString(3),resultset.getString(5),resultset.getFloat(4));
				case "cateneNeve":
					return new CateneNeve(resultset.getInt(1),resultset.getFloat(2),resultset.getString(3),resultset.getString(5));
				case "seggiolino":
					return new Seggiolino(resultset.getInt(1),resultset.getFloat(2),resultset.getString(3),resultset.getString(5), resultset.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
  	}
}
