package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoNoleggio;
import business.model.Exception.CommonException;


public class DAONoleggio implements DAO{
	
	
	private  DaoFactory daofactory;

	public DAONoleggio(DaoFactory dao){
		this.daofactory = dao;		
	}
	@Override
	public ResultSet creazione(Entity x) {
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
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getSedeRestituzione()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getNote()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getIdcontratto()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getIdAuto()));
	   insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getIdPagamento()));
	    
	  
	   
	   
	   Connection connection= Connection.getConnection(daofactory);
       
       ResultSet idList = null;
       
		try {
			 idList = connection.executeUpdate(insertQuery);
			 //qui assegno l'optional ad un noleggio
			 insertQuery= " Insert into NoleggioOptional('?','?');";
			 insertQuery = queryReplaceFirst(insertQuery, String.valueOf(idList.getInt(1)));
			 String queryPartenza=insertQuery;
			 for(int i=0;i< noleggio.getOptional().size();i++){
				 insertQuery = queryReplaceFirst(insertQuery, String.valueOf(noleggio.getOptional().get(i)));
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
		return idList;
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
			risultato= creaElencoNoleggi(readQueryResultSet,connection);
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
			risultato= creaElencoNoleggi(readQueryResultSet,connection);
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
	
	private List<Noleggio> creaElencoNoleggi(ResultSet resultset,Connection connection){
		List<Noleggio> noleggi=new LinkedList<Noleggio>();
        try {
         if(resultset!=null){
            while (resultset.next()) {
                noleggi.add(ottieniNoleggio(resultset,connection));
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return noleggi;
	}
	
	private Noleggio ottieniNoleggio(ResultSet resultset,Connection connection) throws SQLException{
		List<Integer> idOptionals= ottieniIDOptional(resultset,connection);
		List<Integer> idMulta  = ottieniIDMulte(resultset,connection);

		LocalDate data=null;
		if(resultset.getDate(4)!=null){
			data= resultset.getDate(4).toLocalDate();
		}
		return  new Noleggio(resultset.getInt(1),
				resultset.getDate(2).toLocalDate(),
				resultset.getDate(3).toLocalDate(),
				data,
				resultset.getDate(5).toLocalDate(),
				resultset.getInt(6),
				resultset.getInt(7),
				idOptionals,
				idMulta,
				StatoNoleggio.toStatoNoleggio(resultset.getString(8)),
				resultset.getInt(9),
				resultset.getInt(10),
				resultset.getInt(11),
				resultset.getInt(12),
				resultset.getInt(13),
				resultset.getInt(14),
				resultset.getInt(15),
				resultset.getString(16));
	}
	
	private List<Integer> ottieniIDOptional(ResultSet resultset,Connection connection) throws SQLException{
		String readQuery  = "Select idOptional from noleggioOptional where idNoleggio= '?'";
		readQuery = queryReplaceFirst(readQuery, String.valueOf(resultset.getInt(1)));
		
		ResultSet readQueryResultSet = null;
		List<Integer> idOptionals = null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			idOptionals= new ArrayList<Integer>();
			while(readQueryResultSet.next()){
				idOptionals.add(resultset.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere gli optional associati al noleggio" , AlertType.ERROR);
		}
		finally{
			try {
				readQueryResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return idOptionals;
	}
	
	private List<Integer> ottieniIDMulte(ResultSet resultset,Connection connection) throws SQLException{
		String readQuery  = "Select idMulta from Multa where idNoleggio= '?'";
		readQuery = queryReplaceFirst(readQuery, String.valueOf(resultset.getInt(1)));
		ResultSet readQueryResultSet = null;
		List<Integer> idMulta=null;
		try {
			readQueryResultSet = connection.executeRead(readQuery);	
			idMulta= new ArrayList<Integer>();
			while(readQueryResultSet.next()){
				idMulta.add(resultset.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le multe associate al noleggio" , AlertType.ERROR);
		}
		finally{
			try {
				readQueryResultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
		return idMulta;
	}
}
