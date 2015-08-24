package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
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

	
}
