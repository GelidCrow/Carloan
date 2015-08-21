package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import Errori.AlertView;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.OptionalAuto;
import business.model.Exception.CommonException;

public class DAOOptional implements DAO{
	private  DaoFactory daofactory;

	public DAOOptional(DaoFactory dao){
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
	
	public List<Optional> getAll(){
		 String readQuery = "Select DISTINCT Nome,Descrizione,prezzo from Optional";
		 List<Optional> optional;
		 
		 
		 
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
	
	public Optional creaElencoOptional(ResultSet resultset) throws InstantiationException, IllegalAccessException{
	
		
        String sParam= null;
        Date dParam = null;
        int iParam;
        
        try {
         if(resultset!=null){
            while (resultset.next()) {
                Optional optional = new OptionalAuto();
                
                iParam= resultset.getInt("idContratto");
                contratto.setIDContratto(iParam);
                
                iParam= resultset.getInt("idOperatore");
                contratto.setIDOperatore(iParam);
                
                iParam= resultset.getInt("idSupervisoreAgenzia");
                contratto.setIDSupervisoreAgenzia(iParam);  
                
                iParam= resultset.getInt("idSupervisoreSede");
                contratto.setIDSupervisoreSede(iParam);
                
                iParam= resultset.getInt("idAmministratore");
                contratto.setIDAmministratore(iParam);
                
                iParam= resultset.getInt("idCliente");//leggo l'id del cliente e ora prendo la sua istanza.facendo una specie di join
        		Cliente cliente = (Cliente) daoCliente.lettura(iParam);
                contratto.setCliente(cliente);
                
        		
                sParam= resultset.getString("stato");
                contratto.setStato(sParam);
                
                sParam= resultset.getString("note");
                contratto.setNote(sParam);
                
                dParam= resultset.getDate("DataCreazione");
                contratto.setDataCreazione(dParam);
                
                dParam= resultset.getDate("DataChiusura");
                contratto.setDataChiusura(dParam);
                
                risultato.add(contratto);
            	
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return risultato;
	}

}
