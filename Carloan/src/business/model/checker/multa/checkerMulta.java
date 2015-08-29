package business.model.checker.multa;

import business.entity.Entity;
import business.entity.Noleggio.Multa;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class checkerMulta implements Checker{
	private Multa multa;
	@Override
	public void check(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		multa=(Multa)multa;
	}

}
