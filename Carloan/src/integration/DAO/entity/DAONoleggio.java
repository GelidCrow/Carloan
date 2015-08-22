package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Noleggio;
import business.model.Exception.CommonException;


public class DAONoleggio implements DAO{
	
	
	private  DaoFactory daofactory;

	public DAONoleggio(DaoFactory dao){
		this.daofactory = dao;		
	}
	@Override
	public void creazione(Entity x) {
		String insert= "INSERT INTO Noleggio"
				+ "(InizioNoleggio,FineNoleggio,Ritiro,KmRientro,KmBase,Stato,NumeroSettimane,NumeroGiorni,numero_chilometri,LuogoRestituzione,Note,"
				+ " idContratto, idAuto, idPagamento ) values ('?','?','?','?','?','?','?','?','?','?','?','?','?','?'); ";
	
		
		String   insertQuery =insert;
		Noleggio noleggio= (Noleggio)x;
		
	   insertQuery = queryReplaceFirst(insertQuery, noleggio.getInizioNoleggio().toString());
	   insertQuery = queryReplaceFirst(insertQuery, noleggio.getFineNoleggio().toString());
	   insertQuery= queryReplaceFirst(insertQuery, noleggio.getRitiro().toString());
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getKmRientro()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getKmBase()));
	   insertQuery = queryReplaceFirst(insertQuery, noleggio.getStato().toString());
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNumeroSettimane()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNumeroGiorni()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNumeroChilometri()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNumeroSettimane()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNumeroSettimane()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getLuogoRestituzione()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNote()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getContratto()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getAuto()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getPagamento()));
	    
	  
	   
	   
	   Connection connection= Connection.getConnection(daofactory);
       
       ResultSet idList = null;
       
		try {
			 idList = connection.executeUpdate(insertQuery);
			 //qui assegno l'optional ad un noleggio
			 insertQuery= " Insert into NoleggioOptional('?','?');";
			 insertQuery = queryReplaceFirst(insertQuery, String.valueOf(idList.getInt(1)));
			 String queryPartenza=insertQuery;
			 for(int i=0;i< noleggio.getOptional().size();i++){
				 insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getOptional().get(i).getId()));
				 idList = connection.executeUpdate(insertQuery);
				 insertQuery = queryPartenza;
				 }
			 
			   AlertView.getAlertView("Noleggio inserito con successo",AlertType.INFORMATION);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile inserire il Noleggio" , AlertType.ERROR);
		}
		finally{
			try {
				idList.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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

	
	
	public List<Noleggio> getAll(){
		 String readQuery = "Select * from noleggio";
		 
		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Noleggio> risultato = null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoNoleggi(readQueryResultSet);
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
	
	
	
	public List<Noleggio> getNoleggiAperti(int idContratto){
		 String readQuery = "Select * from noleggio where idcontratto='?' and (stato='Aperto' or stato='rientro' or stato='uscita')";
		 readQuery = queryReplaceFirst(readQuery, String.valueOf(idContratto));
		 
		Connection connection= Connection.getConnection(daofactory);
		 
		ResultSet readQueryResultSet = null;
		List<Noleggio> risultato = null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoNoleggi(readQueryResultSet);
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
	
	private List<Noleggio> creaElencoNoleggi(ResultSet resultset){
		List<Noleggio> noleggi=new LinkedList<Noleggio>();
        try {
         if(resultset!=null){
            while (resultset.next()) {
            	ottieniNoleggio(resultset);
                Noleggio noleggio= ottieniNoleggio(resultset);
                noleggi.add(noleggio);
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return noleggi;
	}
	
	private Noleggio ottieniNoleggio(ResultSet resultset) throws SQLException{
		 Noleggio noleggio = new Noleggio();
			noleggio.setIDNoleggio(resultset.getInt(1));

	    return noleggio;
	}
}
