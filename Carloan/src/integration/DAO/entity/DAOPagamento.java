package integration.DAO.entity;

import static utility.QueryStringReplacer.queryReplaceFirst;
import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.pagamento.Pagamento;
import business.model.Exception.CommonException;

public class DAOPagamento implements DAO {
	private  DaoFactory daofactory;
	
	
	public DAOPagamento(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	@Override
	public ResultSet creazione(Entity x) throws CommonException {
		
		String denaro = "INSERT INTO Pagamento"
				+ "(DepCauzionale,Acconto)"
				+	"values('?','?');";
		String conCarta = "INSERT INTO Pagamento"
				+ "(DepCauzionale,Acconto,idIban)"
				+	"values('?','?','?');";
		
		Pagamento pagamento= (Pagamento)x;
		String insertQuery=null;
		if (pagamento.getIdCarta()!=0){
			 insertQuery= conCarta;
		    insertQuery = queryReplaceFirst(insertQuery, String.valueOf(pagamento.getDepositoCauzinale()));
	        
	        insertQuery= queryReplaceFirst(insertQuery,String.valueOf(pagamento.getAcconto()));
	        insertQuery= queryReplaceFirst(insertQuery,String.valueOf(pagamento.getIdCarta()));
		}
		else {
			 insertQuery= denaro;
		    insertQuery = queryReplaceFirst(insertQuery, String.valueOf(pagamento.getDepositoCauzinale())); 
	        insertQuery= queryReplaceFirst(insertQuery,String.valueOf(pagamento.getAcconto()));
		}
    
        Connection connection= Connection.getConnection(daofactory);
        
        ResultSet idList = null;
        
		try {
			 idList = connection.executeUpdate(insertQuery);
			 if(idList==null)
				 throw new CommonException("non � stato possibile inserire il pagamento");
		} catch (SQLException e) {
			e.printStackTrace();
			 throw new CommonException("non � stato possibile inserire il pagamento");
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
	public Entity lettura(int id) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}
	

}