package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import Errori.AlertView;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.Optional.Assicurazione_KASKO;
import business.entity.Noleggio.Optional.CateneNeve;
import business.entity.Noleggio.Optional.ChilometraggioIllimitato;
import business.entity.Noleggio.Optional.Gps;
import business.entity.Noleggio.Optional.Guidatore;
import business.entity.Noleggio.Optional.GuidatoreAggiuntivo;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.Seggiolino;
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
				+ " idContratto, idAuto, idPagamento  ";
		String valori= "values('?','?','?','?','?','?','?','?','?','?','?','?','?','?' ";
		
		String chiudi= " ) " ;
		
		String values =valori;
		Noleggio noleggio= (Noleggio)x;
		
	   values = queryReplaceFirst(values, noleggio.getInizioNoleggio().toString());
	   values = queryReplaceFirst(values, noleggio.getFineNoleggio().toString());
	   values = queryReplaceFirst(values, noleggio.getRitiro().toString());
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getKmRientro()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getKmBase()));
	   values = queryReplaceFirst(values, noleggio.getStato().toString());
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getNumeroSettimane()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getNumeroGiorni()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getNumeroChilometri()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getNumeroSettimane()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getNumeroSettimane()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getLuogoRestituzione()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getNote()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getContratto()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getAuto()));
	   values = queryReplaceFirst(values, String.valueOf(noleggio.getPagamento()));
	    
	    List<Optional> optional= noleggio.getOptional();
	    
	    for(Optional op: optional){
	    	if(op instanceof Gps){
	    		insert += ", idGps ";
	    		values += ", '?' ";
	    	   values = queryReplaceFirst(values, op.getId().toString());
	    	}
	    	else if(op instanceof ChilometraggioIllimitato){
	    		insert += ", idChilometraggio ";
	    		values += ", '?' ";
	    	   values = queryReplaceFirst(values, op.getId().toString());
	    	}
	    	else if((op instanceof Assicurazione_KASKO)){
	    		insert += ", idAssicurazione ";
	    		values += ", '?' ";
	    	   values = queryReplaceFirst(values, op.getId().toString());}
    	    else if((op instanceof CateneNeve)){
	    	   insert += ", idCatene ";
	    	   values += ", '?' ";
	    	   values = queryReplaceFirst(values, op.getId().toString());}
    	    else if((op instanceof GuidatoreAggiuntivo)){
	    	   insert += ", idGuidatore ";
	    	   values += ", '?' ";
	    	   values = queryReplaceFirst(values, op.getId().toString());}
    	    else if((op instanceof Seggiolino)){
	    	   insert += ", idSeggiolino ";
	    	   values += ", '?' ";
	    	   values = queryReplaceFirst(values, op.getId().toString());}
	    }    
	   insert+=chiudi;
	   String insertQuery= insert+values+chiudi+';';
	   
	   System.out.println(insertQuery);
	   
	   Connection connection= Connection.getConnection(daofactory);
       
       ResultSet idList = null;
       
		try {
			 idList = connection.executeUpdate(insertQuery);
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
