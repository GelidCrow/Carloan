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
import business.entity.Gestori.SupervisoreSede;


public class DAOSupervisoreS implements DAO{
	private  DaoFactory daofactory;
	
	
	public DAOSupervisoreS(DaoFactory dao){
		this.daofactory = dao;		
	}

	@Override
	public ResultSet creazione(Entity x) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Entity lettura(int id){
	String QUERY= "Select * from SupervisoreSede where idSupervisoreSede='?' ";
	 Connection connection= Connection.getConnection(daofactory);
	 
	 String readQuery = QUERY;
	 		 
	 readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
	 
     ResultSet readQueryResultSet = null;
     SupervisoreSede  risultato = null;
     try {
		readQueryResultSet = connection.executeRead(readQuery);	
		if(readQueryResultSet!=null &&readQueryResultSet.next()){
			risultato= ottieniSupS(readQueryResultSet);
		}
	 } catch (SQLException e) {
		e.printStackTrace();
		AlertView.getAlertView("Non è stato possibile leggere il supervisore della sede " , AlertType.ERROR);
	 }
	     return risultato;
	}
	
	private SupervisoreSede ottieniSupS(ResultSet resultset) throws SQLException{
		 return new SupervisoreSede(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getDate(5),resultset.getString(6),
				 	resultset.getString(7),resultset.getString(8),resultset.getString(9),resultset.getBoolean(10),resultset.getInt(11));
	}

	public List<SupervisoreSede> getAll_bySede(int idSede){
		String query="Select * from supervisoresede where idsede="+String.valueOf(idSede);
		Connection c=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		try {
			r=c.executeRead(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return creaElencoSupervisoriSede(r);
	}

	private List<SupervisoreSede> creaElencoSupervisoriSede(ResultSet r) {
		List<SupervisoreSede> l=new LinkedList<SupervisoreSede>();
		if(r!=null){
		try {
			while(r.next()){
			l.add(ottieniSupS(r));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return l;
	}
}
