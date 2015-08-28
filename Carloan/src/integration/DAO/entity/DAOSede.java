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
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;

public class DAOSede implements DAO {

	private DaoFactory dao;
	
	public DAOSede(DaoFactory dao){
		this.dao=dao;
	}
	
	@Override
	public ResultSet creazione(Entity x) throws CommonException {
	Sede s=(Sede)x;
	String query="Insert into Sede(Indirizzo,NumeroTelefono,Nome,IDAgenzia) values('?','?','?',?)";
	query=queryReplaceFirst(query, s.getIndirizzo());
	query=queryReplaceFirst(query, s.getNumeroTelefono());
	query=queryReplaceFirst(query, s.getNome());
	query=queryReplaceFirst(query, String.valueOf(s.getIDAgenzia()));
	Connection c=Connection.getConnection(this.dao);
	ResultSet r=null;
	try {
		r=c.executeUpdate(query);
		if(r!=null)
			AlertView.getAlertView("Sede inserita con successo", AlertType.INFORMATION);
		else
			throw new CommonException("Non e' stato possibile inserire la sede");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public void aggiornamento(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		String query="Select * from sede where idsede="+String.valueOf(id);
		Connection conn=Connection.getConnection(this.dao);
		try {
			ResultSet r=conn.executeRead(query);
			return creaSede(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	private Entity creaSede(ResultSet r) {
		Sede s = null;
		try {
			if(r!=null && r.next()){
				 s=new Sede(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	public ArrayList<Sede> getAll(){
		String readQuery = "Select * from Sede";

		 Connection connection= Connection.getConnection(dao);
	        
	     ResultSet readQueryResultSet = null;
	     ArrayList<Sede> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoSedi(readQueryResultSet);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le sedi" , AlertType.ERROR);
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

	private ArrayList<Sede> creaElencoSedi(ResultSet r) {
		ArrayList<Sede> lesedi=new ArrayList<Sede>();
		if(r!=null){
				try {
					while(r.next()){
						lesedi.add(new Sede(r.getInt(1),r.getString(2),r.getString(3),r.getString(4),r.getInt(5)));	
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return lesedi;
	}
	
	
	public List<Sede> 	getAllSediByAgenzia(int id){
		String readQuery = "Select * from Sede where idAgenzia='?'";
		 readQuery = queryReplaceFirst(readQuery, String.valueOf(id));

		 Connection connection= Connection.getConnection(dao);
	        
	     ResultSet readQueryResultSet = null;
	     List<Sede> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoSedi(readQueryResultSet);
		 } catch (SQLException  e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le sedi" , AlertType.ERROR);
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

}
