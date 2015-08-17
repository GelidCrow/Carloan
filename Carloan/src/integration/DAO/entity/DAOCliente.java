package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.scene.control.Alert.AlertType;
import Errori.AlertView;
import business.entity.Cliente;
import business.entity.Entity;

public class DAOCliente implements DAO{

	private  DaoFactory daofactory;

	
	public DAOCliente(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public void creazione(Entity x) {
		 LocalDate date= null;
		String INSERT = "INSERT INTO Cliente"
				+ "(Nome,Cognome,Sesso,DataEmissPatente,DataNascita,"
				+ "Indirizzo,CodFiscale,NumCell,NumTel,PatenteGuida,DataScadPatente,PartitaIva,Email) "
				+ "values('?','?','?','?','?','?','?','?','?','?','?','?','?');";
		
		String insertQuery = INSERT;
				
		Cliente cliente= (Cliente)x;

        insertQuery = queryReplaceFirst(insertQuery, cliente.getNome());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getCognome());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getSesso());

		date = cliente.getDataEmissPatente().getValue();

        insertQuery= queryReplaceFirst(insertQuery,date.toString());
        
        date = cliente.getDatanascita().getValue();

        insertQuery= queryReplaceFirst(insertQuery,date.toString());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getIndirizzo());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getCodFiscale());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getNumCell());
      
        insertQuery= queryReplaceFirst(insertQuery,cliente.getNumTel());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getPatenteGuida());
        
        date = cliente.getDataScadPatente().getValue();
        
        insertQuery= queryReplaceFirst(insertQuery,date.toString());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getPartitaIva());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getEmail());
    
        Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        
		try {
			 idList = connection.executeUpdate(insertQuery);
			 AlertView.getAlertView("Cliente inserito con successo",AlertType.INFORMATION);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 AlertView.getAlertView("Non è stato possibile inserire il cliente" , AlertType.ERROR);
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

	@Override
	public Entity lettura() {
		// TODO Auto-generated method stub
		return null;
	}

}
