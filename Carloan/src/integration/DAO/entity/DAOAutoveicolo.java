package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import Errori.AlertView;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Disponibilita;
import business.entity.Auto.Danni;

public class DAOAutoveicolo implements DAO{
private DaoFactory dao;
public DAOAutoveicolo(DaoFactory dao) {
	this.dao=dao;
}

	@Override
	public void creazione(Entity x) {
		// TODO Auto-generated method stub
		
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

	public List<Autoveicolo> getAll(){
		
			 String readQuery = "Select * from Autoveicolo";

			 Connection connection= Connection.getConnection(dao);
		        
		     ResultSet readQueryResultSet = null;
		     List<Autoveicolo> risultato = null;
		     try {
				readQueryResultSet = connection.executeRead(readQuery);	
				risultato= creaElencoAuto(readQueryResultSet);
			 } catch (SQLException  e) {
				e.printStackTrace();
				AlertView.getAlertView("Non è stato possibile leggere le auto" , AlertType.ERROR);
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

	private List<Autoveicolo> creaElencoAuto(ResultSet readQueryResultSet) {
		List<Autoveicolo> leauto=new LinkedList<Autoveicolo>();
		if(readQueryResultSet!=null){
			try {
				while(readQueryResultSet.next()){
					Autoveicolo a=new Autoveicolo();
					a.setIDauto(readQueryResultSet.getInt(1));
					a.setTarga(readQueryResultSet.getString(2));
					a.setMarca(readQueryResultSet.getString(3));
					a.setModello(readQueryResultSet.getString(4));
					a.setAlimPrincipale(readQueryResultSet.getString(5));
					a.setAlimSec(readQueryResultSet.getString(6));
					a.setColore(readQueryResultSet.getString(7));
					a.setCambio(readQueryResultSet.getString(8));
					a.setImmatricolazione(readQueryResultSet.getDate(9).toLocalDate());
					a.setCilindrata(readQueryResultSet.getInt(10));
					a.setPotenza(readQueryResultSet.getInt(11));
					a.setNroPosti(readQueryResultSet.getInt(12));
					a.setNroTelaio(readQueryResultSet.getString(13));
					String dispon=readQueryResultSet.getString(14);
					switch(dispon){
					case "Disponibile":
						a.setDisponibilita(Disponibilita.Disponibile);
						break;
					case "NonDisponibile":
						a.setDisponibilita(Disponibilita.NonDisponibile);
						break;
					case "ManutenzioneOrdinaria":
						a.setDisponibilita(Disponibilita.ManutenzioneOrdinaria);
						break;
					case "ManutenzioneStraordinaria":
						a.setDisponibilita(Disponibilita.ManutenzioneStraordinaria);
						break;
					}
					a.setUltimoKm(readQueryResultSet.getInt(15));
					a.setCapPortaBagnagli(readQueryResultSet.getInt(16));
					a.setNote(readQueryResultSet.getString(17));
					InputStream i=readQueryResultSet.getBinaryStream(18);
					if(i!=null)
						a.setImmagine(new Image(i));
					a.setDataScadAssic(readQueryResultSet.getDate(19).toLocalDate());
					a.setOptionalAuto(readQueryResultSet.getString(20));
					a.setPrezzo(readQueryResultSet.getFloat(21));
					Danni danni=new Danni(readQueryResultSet.getString(22), readQueryResultSet.getString(23));
					a.setDanni(danni);
					leauto.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return leauto;
	}

}
