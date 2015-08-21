package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;
import Errori.AlertView;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoContratto;
import business.model.Exception.CommonException;


public class DAOContratto implements DAO{

	private  DaoFactory daofactory;

	public DAOContratto(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public void creazione(Entity x) {
		String INSERT = "INSERT INTO Contratto "
				+ "( ?,Stato,dataCreazione,Note,idcliente) "
				+ "values ('?','?','?','?','?');";
		
		String insertQuery = INSERT;
				
		Contratto contratto= (Contratto)x;	
		
		if(UtenteCorrente.getUtente() instanceof Operatore){
        	insertQuery= queryReplaceFirst(insertQuery,"idOperatore");
        	insertQuery= queryReplaceFirst(insertQuery,contratto.getIDOperatore().toString());
       
        }
        else if(UtenteCorrente.getUtente() instanceof Amministratore){
        	insertQuery= queryReplaceFirst(insertQuery,"idAmministratore");
        	insertQuery= queryReplaceFirst(insertQuery,contratto.getIDAmministratore().toString());
        	
        }
        else if(UtenteCorrente.getUtente() instanceof SupervisoreSede){
        	insertQuery= queryReplaceFirst(insertQuery,"idSupervisoreSede");
        	insertQuery= queryReplaceFirst(insertQuery,contratto.getIDSupervisoreSede().toString());
        }
        else if(UtenteCorrente.getUtente() instanceof SupervisoreAgenzia){
        	insertQuery= queryReplaceFirst(insertQuery,"idSupervisoreAgenzia");
			insertQuery= queryReplaceFirst(insertQuery,contratto.getIDSupervisoreAgenzia().toString());
		}
        	
        insertQuery = queryReplaceFirst(insertQuery, contratto.getStato().toString());
        
        insertQuery= queryReplaceFirst(insertQuery,contratto.getDataCreazione().toString());
        
        insertQuery= queryReplaceFirst(insertQuery,contratto.getNote());

        insertQuery= queryReplaceFirst(insertQuery,contratto.getCliente().getId().toString());
        
        
        
        Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        
		try {
			 idList = connection.executeUpdate(insertQuery);
			 AlertView.getAlertView("contratto inserito con successo",AlertType.INFORMATION);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile inserire il contratto" , AlertType.ERROR);
		}
		finally{
			try {
				idList.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
     }

	@Override
	public void aggiornamento(Entity entity) throws CommonException{
		DAONoleggio daoNoleggio ;
		daoNoleggio= (DAONoleggio) daofactory.getDao("DAONoleggio");
		List<Noleggio> noleggiAperti= null;
		
	
		Contratto contratto= (Contratto) entity;
		String UPDATE = "UPDATE  Contratto  SET "
							+ "stato= '?', note = '?' ";
		String 	WHERE= " WHERE IDContratto = '?'";
		String updateQuery = UPDATE;
		
		//controllo che i noleggi aperti non ce ne siano
		if(contratto.getStato().toString().equals(StatoContratto.Annullato)){
        	noleggiAperti=  daoNoleggio.getNoleggiAperti(contratto.getIDContratto());
        }
		
		if(noleggiAperti==null){

				updateQuery = queryReplaceFirst(updateQuery, contratto.getStato().toString());
				
		        updateQuery= queryReplaceFirst(updateQuery,contratto.getNote());
		
		        if(!contratto.getStato().equals(StatoContratto.Aperto.toString())){
		    		updateQuery+=", dataChiusura='?'";
		    		updateQuery= queryReplaceFirst(updateQuery,contratto.getDataChiusura().toString());
		        }        	
		        updateQuery+=WHERE;
		        updateQuery= queryReplaceFirst(updateQuery,contratto.getIDContratto().toString());
		     
		     
		        Connection connection= Connection.getConnection(daofactory);
		        
		        ResultSet idList = null;
		        
				try {
					 idList = connection.executeUpdate(updateQuery);
					 AlertView.getAlertView("Contratto aggiornato con successo",AlertType.INFORMATION);
				} catch (SQLException e) {
					e.printStackTrace();
					 AlertView.getAlertView("Non è stato possibile aggiornare il contratto" , AlertType.ERROR);
				}
				finally{
					try {
						idList.close();
						//connection.chiudiConnessione();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		}
		else 
			throw new CommonException("Ci sono dei noleggi aperti, non è  possibile annullare il contratto!");
	}

	
	public List<Contratto> getAll(){		
		 String readQuery = "Select idContratto, Stato , dataCreazione,note,datachiusura,idcliente,idoperatore,"
		 					+ "idSupervisoreAgenzia, idsupervisoresede, idamministratore from contratto";

		 Connection connection= Connection.getConnection(daofactory);
	        
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
	
	public List<Contratto> creaElencoContratti(ResultSet resultset) throws InstantiationException, IllegalAccessException{
		DAOCliente daoCliente ;
		daoCliente= (DAOCliente) daofactory.getDao("DAOCliente");
	
		 
		List<Contratto> risultato = new LinkedList<>();
		
        String sParam= null;
        Date dParam = null;
        int iParam;
        
        try {
         if(resultset!=null){
            while (resultset.next()) {
                Contratto contratto = new Contratto();
                
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
	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
