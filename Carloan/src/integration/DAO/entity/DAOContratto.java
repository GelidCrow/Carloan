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
import business.entity.Noleggio.Contratto;


public class DAOContratto implements DAO{

	private  DaoFactory daofactory;

	public DAOContratto(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public void creazione(Entity x) {
		String INSERT = "INSERT INTO Contratto "
				+ "(Stato,dataCreazione,note,dataChiusura,idsupervisoreSede,idSupervisoreAgenzia,idAmministratore,idOperatore,idcliente) "
				+ "values('?','?','?','?','?','?','?','?','?');";
		
		String insertQuery = INSERT;
				
		Contratto contratto= (Contratto)x;
		
        insertQuery = queryReplaceFirst(insertQuery, contratto.getStato().toString());
        
        insertQuery= queryReplaceFirst(insertQuery,contratto.getDataCreazione().toString());

        insertQuery= queryReplaceFirst(insertQuery,contratto.getNote());

        insertQuery= queryReplaceFirst(insertQuery,contratto.getDataChiusura().toString());

        insertQuery= queryReplaceFirst(insertQuery,contratto.getIDSupervisoreSede().toString());

        insertQuery= queryReplaceFirst(insertQuery,contratto.getIDSupervisoreAgenzia().toString());
        
        insertQuery= queryReplaceFirst(insertQuery,contratto.getIDAmministratore().toString());

        insertQuery= queryReplaceFirst(insertQuery,contratto.getIDOperatore().toString());

        insertQuery= queryReplaceFirst(insertQuery,contratto.getIDCliente().toString());
        
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

	public List<Contratto> getAll(){		
		 String readQuery = "Select idContratto, Stato , dataCreazione,note,datachiusura,idcliente,idoperatore,"
		 					+ "idSupervisoreAgenzia, idsupervisoresede, idamministratore from contratto";

		 Connection connection= Connection.getConnection(daofactory);
	        
	     ResultSet readQueryResultSet = null;
	     List<Contratto> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoContratti(readQueryResultSet);
		 } catch (SQLException e) {
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
	
	public List<Contratto> creaElencoContratti(ResultSet resultset){
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
                
                iParam= resultset.getInt("idCliente");
                contratto.setIDCliente(iParam);
                
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
