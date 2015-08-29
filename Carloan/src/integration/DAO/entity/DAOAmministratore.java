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
import business.entity.Gestori.Amministratore;
import business.model.Exception.CommonException;

public class DAOAmministratore implements DAO{
	private  DaoFactory daofactory;
	
	
	public DAOAmministratore(DaoFactory dao){
		this.daofactory = dao;		
	}
	

	@Override
	public ResultSet creazione(Entity x) throws CommonException {
		String query="insert into Amministratore(Nome,Cognome,Sesso,datanascita,indirizzo,codicefiscale,numcell,numfisso,assunto,idditta)"+
	     " values('?','?','?','?','?','?','?','?','V',?)";
		Amministratore a=(Amministratore)x;
		query=queryReplaceFirst(query, a.getNome());
		query=queryReplaceFirst(query, a.getCognome());
		query=queryReplaceFirst(query, a.getSesso());
		query=queryReplaceFirst(query, a.getDataNascita().toString());
		query=queryReplaceFirst(query, a.getIndirizzo());
		query=queryReplaceFirst(query, a.getCodiceFiscale());
		query=queryReplaceFirst(query, a.getNumCell());
		query=queryReplaceFirst(query, a.getNumFisso());
		query=queryReplaceFirst(query, String.valueOf(a.getIDDitta()));
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet s=null;
		try {
			s=c.executeUpdate(query);
			if(s!=null)
				AlertView.getAlertView("Amministratore inserito con successo", AlertType.INFORMATION);
				else
					throw new CommonException("L'amministratore non � stato inserito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
		
	}

	@Override
	public void aggiornamento(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Entity lettura(int id){
	String QUERY= "Select * from Amministratore where idAmministratore='?' ";
	 Connection connection= Connection.getConnection(daofactory);
	 
	 String readQuery = QUERY;
	 		 
	 readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
	 
     ResultSet readQueryResultSet = null;
     Amministratore  risultato = null;
     try {
		readQueryResultSet = connection.executeRead(readQuery);	
		if(readQueryResultSet.next()){
			risultato= ottieniAmministratore(readQueryResultSet);
		}
	 } catch (SQLException e) {
		e.printStackTrace();
		AlertView.getAlertView("Non � stato possibile leggere l'amministratore" , AlertType.ERROR);
	 }
	 finally{
		try {
			readQueryResultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
		}
	}
    return risultato;
	}
	
	private Amministratore ottieniAmministratore(ResultSet resultset){
		 try {
			return new Amministratore(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getDate(5).toLocalDate(),resultset.getString(6),
					 	resultset.getString(7),resultset.getString(8),resultset.getString(9),resultset.getBoolean(10),resultset.getInt(11));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}


	public List<Amministratore> getAll() {
		String query="Select * from Amministratore";
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet s=null;
		try {
			 s=c.executeRead(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return creaElencoAmministratori(s);
	}
private List<Amministratore> creaElencoAmministratori(ResultSet r){
	List<Amministratore> l=new LinkedList<Amministratore>();
	if(r!=null){
		try {
			while(r.next()){
				l.add(ottieniAmministratore(r));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return l;
}


public Amministratore leggiAmministratoreByCodiceFiscale(String c) {
	String query="Select * from amministratore where codicefiscale='?'";
	query=queryReplaceFirst(query, c);
	Connection co=Connection.getConnection(this.daofactory);
	ResultSet s=null;
	try {
		 s=co.executeRead(query);
		 s.next();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
		return ottieniAmministratore(s);
}
}