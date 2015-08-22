package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;
import static utility.QueryStringReplacer.queryReplaceFirst;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
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
		String insert="insert into Autoveicolo (Targa,Marca,Modello,AlimPrincipale,Colore,Cambio,Immatricolazione,"
				+ "Cilindrata,Potenza,NroPosti,NroTelaio,Disponibilita,UltimoKm,CapPortaBagagli,Note,DataScadAssic,OptionalAuto,Prezzo,DanniFutili,DanniGravi,IDSede,IDFascia,Immagine)";
		String values=" values('?','?','?','?','?','?',?,?,?,?,'?','?',?,?,'?',?,'?',?,'?','?',?,?,?";
		Autoveicolo a=(Autoveicolo)x;
		values=queryReplaceFirst(values, a.getTarga());
		values=queryReplaceFirst(values, a.getMarca());
		values=queryReplaceFirst(values, a.getModello());
		values=queryReplaceFirst(values, a.getAlimPrincipale());
		values=queryReplaceFirst(values, a.getColore());
		values=queryReplaceFirst(values, a.getCambio());
		values=queryReplaceFirst(values, a.getImmatricolazione().toString());
		values=queryReplaceFirst(values, String.valueOf(a.getCilindrata()));
		values=queryReplaceFirst(values, String.valueOf(a.getPotenza()));
		values=queryReplaceFirst(values, String.valueOf(a.getNroPosti()));
		values=queryReplaceFirst(values, a.getNroTelaio());
		values=queryReplaceFirst(values, a.getDisponibilita().toString());
		values=queryReplaceFirst(values, String.valueOf(a.getUltimoKm()));
		values=queryReplaceFirst(values, String.valueOf(a.getCapPortaBagnagli()));
		values=queryReplaceFirst(values, a.getNote());
		values=queryReplaceFirst(values, a.getDataScadAssic().toString());
		
		
		
		
		
		
		
		
		
		
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
					Autoveicolo a= ottiniAutoveicolo(readQueryResultSet);
					leauto.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return leauto;
	}
	
	private Autoveicolo ottiniAutoveicolo(ResultSet resultset) throws SQLException{
		Autoveicolo a=new Autoveicolo();
		a.setIDauto(resultset.getInt(1));
		a.setTarga(resultset.getString(2));
		a.setMarca(resultset.getString(3));
		a.setModello(resultset.getString(4));
		a.setAlimPrincipale(resultset.getString(5));
		a.setAlimSec(resultset.getString(6));
		a.setColore(resultset.getString(7));
		a.setCambio(resultset.getString(8));
		a.setImmatricolazione(resultset.getDate(9).toLocalDate());
		a.setCilindrata(resultset.getInt(10));
		a.setPotenza(resultset.getInt(11));
		a.setNroPosti(resultset.getInt(12));
		a.setNroTelaio(resultset.getString(13));
		String dispon=resultset.getString(14);
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
		a.setUltimoKm(resultset.getInt(15));
		a.setCapPortaBagnagli(resultset.getInt(16));
		a.setNote(resultset.getString(17));
		InputStream i=resultset.getBinaryStream(18);
		if(i!=null)
			a.setImmagine(i);
		a.setDataScadAssic(resultset.getDate(19).toLocalDate());
		a.setOptionalAuto(resultset.getString(20));
		a.setPrezzo(resultset.getFloat(21));
		Danni danni=new Danni(resultset.getString(22), resultset.getString(23));
		a.setDanni(danni);
		return a;
	}

}
