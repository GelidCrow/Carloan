package business.model;

import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOManutenzione;
import business.entity.Entity;
import business.entity.Auto.manutenzione.ManutenzioneOrdinaria;
import business.entity.Auto.manutenzione.ManutenzioneStraordinaria;
import business.model.Exception.CommonException;

public class ModelManutenzione implements Model{
	private DaoFactory daofactory;
	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}
	public void chiusuraManutenzione(Entity parameter) throws CommonException{
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOManutenzione)daofactory.getDao("DAOManutenzione")).aggiornamento(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOManutenzione)daofactory.getDao("DAOManutenzione")).creazione(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		//Non implementata
	}
	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ManutenzioneOrdinaria> getAll_ordinarie(int auto_id){
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOManutenzione)daofactory.getDao("DAOManutenzione")).getAll_ordinarie(auto_id);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return null;	
	}

	public List<ManutenzioneStraordinaria> getAll_straordinarie_aperte(int auto_id){
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOManutenzione)daofactory.getDao("DAOManutenzione")).getAll_straordinarie_aperte(auto_id);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return null;	
	}
	
	public List<ManutenzioneOrdinaria> getAll_ordinarie_aperte(int auto_id){
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOManutenzione)daofactory.getDao("DAOManutenzione")).getAll_ordinarie_aperte(auto_id);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return null;	
	}

	public List<ManutenzioneStraordinaria> getAll_straordinarie(int auto_id){
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOManutenzione)daofactory.getDao("DAOManutenzione")).getAll_straordinarie_aperte(auto_id);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	return null;	
	}
}
