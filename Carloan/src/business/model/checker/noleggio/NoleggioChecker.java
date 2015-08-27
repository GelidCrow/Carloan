package business.model.checker.noleggio;

import business.entity.Entity;
import business.entity.Noleggio.Noleggio;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class NoleggioChecker implements Checker{
	private Noleggio noleggio;
	@Override
	public void check(Entity entity) throws CommonException {
		noleggio = (Noleggio)entity;
	//	checkDataInizio();
		//checkDataRitiro();
	}

}
