package business.model;

import java.io.InputStream;
import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOAutoveicolo;
import business.entity.Entity;
import business.model.Exception.CommonException;
import business.entity.Auto.Autoveicolo;
public class ModelAutoveicolo implements Model{

	private DaoFactory daofactory;


	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void Inserimento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOAutoveicolo)daofactory.getDao("DAOAutoveicolo")).creazione(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOAutoveicolo)daofactory.getDao("DAOAutoveicolo")).aggiornamento(parameter);
			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	public List<Autoveicolo> getAll() {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOAutoveicolo)daofactory.getDao("DAOAutoveicolo")).getAll();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Autoveicolo> getAllAutoByFascia(int id) {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOAutoveicolo)daofactory.getDao("DAOAutoveicolo")).getAllAutoByFascia(id);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<Autoveicolo> getAllAutoDisponibiliBySede(int id) {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOAutoveicolo)daofactory.getDao("DAOAutoveicolo")).getAllAutoDisponibiliBySede(id);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Autoveicolo> getAllAutoDisponibiliBySedeAndFascia(List<Entity> lista) {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOAutoveicolo)daofactory.getDao("DAOAutoveicolo")).getAllAutoDisponibiliBySedeAndFascia(lista);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Entity lettura(int id) {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOAutoveicolo)daofactory.getDao("DAOAutoveicolo")).lettura(id);
		} catch (InstantiationException | IllegalAccessException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public InputStream leggi_immagine(int id){
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOAutoveicolo)daofactory.getDao("DAOAutoveicolo")).leggi_immagine(id);
		} catch (InstantiationException | IllegalAccessException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	


}
