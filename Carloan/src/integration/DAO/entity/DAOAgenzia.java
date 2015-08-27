package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Luoghi.Agenzia;


import business.model.Exception.CommonException;
import static utility.QueryStringReplacer.queryReplaceFirst;

public class DAOAgenzia implements DAO{

	
	private  DaoFactory daofactory;
	
	
	public DAOAgenzia(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public ResultSet creazione(Entity x) throws CommonException {

		String INSERT = "INSERT INTO Agenzia(NumeroTelefono,Nome,IDDitta) values('?','?',?);";
		String insertQuery = INSERT;
				
		Agenzia agenzia= (Agenzia)x;
		
        insertQuery= queryReplaceFirst(insertQuery,agenzia.getNumTelefono());
        insertQuery= queryReplaceFirst(insertQuery,agenzia.getNome());
        insertQuery= queryReplaceFirst(insertQuery,String.valueOf(agenzia.getIDDitta()));
        
        Connection connection= Connection.getConnection(daofactory);
        ResultSet idList=null;
		try {
			 idList = connection.executeUpdate(insertQuery);
			 if(idList!=null)
				 AlertView.getAlertView("Agenzia inserita con successo!", AlertType.INFORMATION);
			 else
				 throw new CommonException("Non e' stato possibile inserire l'agenzia");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idList;
	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Agenzia> getAll(){
		String query="Select * from agenzia";
		Connection c=Connection.getConnection(this.daofactory);
		try {
			ResultSet r=c.executeRead(query);
			return creaElencoAgenzie(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<Agenzia> creaElencoAgenzie(ResultSet r) {
		List<Agenzia> agenzie=new ArrayList<Agenzia>();
		if(r!=null){
			try {
				while(r.next()){
					agenzie.add(new Agenzia(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4)));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return agenzie;
	}
	}
