package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
		String INSERT = "INSERT INTO Cliente"
				+ "(Nome,Cognome,Sesso,DataEmissPatente,DataNascita,"
				+ "Indirizzo,CodFiscale,NumCell,NumTel,PatenteGuida,DataScadPatente,PartitaIva,Email) "
				+ "values('?','?','?','?','?','?','?','?','?','?','?','?','?');";
		
		String insertQuery = INSERT;
				
		Cliente cliente= (Cliente)x;

        insertQuery = queryReplaceFirst(insertQuery, cliente.getNome());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getCognome());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getSesso());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getDataEmissPatente().toString());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getDatanascita().toString());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getIndirizzo());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getCodFiscale());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getNumCell());
      
        insertQuery= queryReplaceFirst(insertQuery,cliente.getNumTel());

        insertQuery= queryReplaceFirst(insertQuery,cliente.getPatenteGuida());
        
        insertQuery= queryReplaceFirst(insertQuery,cliente.getDataScadPatente().toString());

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
	public Entity lettura(int id) {
		
		
		
		
		return null;
	}

	@Override
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}

	public List<Cliente> getAll(){		
		 String readQuery = "Select Nome,Cognome,Sesso,DataEmissPatente,DataNascita,"
				 			+ "Indirizzo,CodFiscale,NumCell,NumTel,PatenteGuida,DataScadPatente,PartitaIva,Email from cliente";
		 Connection connection= Connection.getConnection(daofactory);
	        
	     ResultSet readQueryResultSet = null;
	     List<Cliente> risultato = null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoClienti(readQueryResultSet);
		 } catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere i clienti " , AlertType.ERROR);
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
	
	public List<Cliente> creaElencoClienti(ResultSet resultset){
		List<Cliente> risultato = new LinkedList<>();
	
        String sParam= null;
        Date dParam = null;
        
        try {
         if(resultset!=null){
            while (resultset.next()) {
                Cliente cliente = new Cliente();
               
                sParam = resultset.getString("Nome");              
                cliente.setNome(sParam);

                sParam = resultset.getString("Cognome");
                cliente.setCognome(sParam);
                
                sParam = resultset.getString("Sesso");
                cliente.setSesso(sParam);

                dParam = resultset.getDate("DataEmissPatente");               
                cliente.setDataEmissPatente(dParam);
                
                dParam = resultset.getDate("DataNascita");             
                cliente.setDatanascita(dParam);
                
                sParam = resultset.getString("Indirizzo");
                cliente.setIndirizzo(sParam);
  
                sParam = resultset.getString("CodFiscale");
                cliente.setCodFiscale(sParam);
                
                sParam = resultset.getString("NumCell"); 
                cliente.setNumCell(sParam);

                sParam = resultset.getString("NumTel");
                cliente.setNumTel(sParam);

	            sParam = resultset.getString("PatenteGuida");
	            cliente.setPatenteGuida(sParam);

                dParam = resultset.getDate("DataScadPatente");
                cliente.setDataScadPatente(dParam);
	              
	            sParam = resultset.getString("PartitaIva");
	            cliente.setPartitaIva(sParam);
	
	            sParam = resultset.getString("Email");
	            cliente.setEmail(sParam);
                       
                risultato.add(cliente);
            }
         }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } 
		return risultato;
	}
}
