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
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.pagamento.CartaDiCredito;


public class DAOCartaDiCredito implements DAO{


	private  DaoFactory daofactory;

	public DAOCartaDiCredito(DaoFactory dao){
		this.daofactory = dao;		
	}

	@Override
	public ResultSet creazione(Entity x) {
		String INSERT = "INSERT INTO CartaDiCredito"
				+ "(Iban,numerocarta,datascadenza,circuito,idcliente)" 
				+ "  values('?','?','?','?','?')";
		
		String insertQuery = INSERT;
		
		CartaDiCredito carta= (CartaDiCredito)x;

        insertQuery = queryReplaceFirst(insertQuery, carta.getIBAN());
        insertQuery = queryReplaceFirst(insertQuery, carta.getNumeroCarta());
        insertQuery = queryReplaceFirst(insertQuery, carta.getDataScadenza().toString());
        insertQuery = queryReplaceFirst(insertQuery, carta.getCircuito().toString());
        insertQuery = queryReplaceFirst(insertQuery, String.valueOf(carta.getIDCliente()));
		
        
        Connection connection= Connection.getConnection(daofactory);
	        
	        ResultSet idList = null;
	        
			try {
				 idList = connection.executeUpdate(insertQuery);
				 AlertView.getAlertView("Carta di credito inserita con successo",AlertType.INFORMATION);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 AlertView.getAlertView("Non è stato possibile inserire la carta di credito" , AlertType.ERROR);
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
	public void aggiornamento(Entity x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<CartaDiCredito> getAllByCliente(int id){
		String read = "select IBan,numerocarta,datascadenza,circuito from cartadicredito where idcliente='?'";
		String readQuery = read;
		readQuery = queryReplaceFirst(readQuery,String.valueOf(id));
	
		Connection connection= Connection.getConnection(daofactory);
        
	   List<CartaDiCredito> risultato = null;
	   ResultSet readQueryResultSet=null;
	     try {
			readQueryResultSet = connection.executeRead(readQuery);	
			risultato= creaElencoCarte(readQueryResultSet);
		 } catch (SQLException e) {
			e.printStackTrace();
			AlertView.getAlertView("Non è stato possibile leggere le carte di credito del cliente " , AlertType.ERROR);
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
	
	
	private List<CartaDiCredito> creaElencoCarte(ResultSet resultset) throws SQLException{
		List<CartaDiCredito> risult= new ArrayList<CartaDiCredito>();
		while(resultset.next()){
			CartaDiCredito carta= ottieniCarta(resultset);
			risult.add(carta);
		}
		return risult;
	}
	
	
	private CartaDiCredito ottieniCarta(ResultSet resultset) throws SQLException{
		return  new CartaDiCredito(resultset.getString(1),
					resultset.getString(2),resultset.getDate(3).toLocalDate(),resultset.getString(4));
		
	}
}
