package business.model.checker.autoveicolo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class AutoveicoloChecker implements Checker {
private Autoveicolo auto;
	@Override
	public void check(Entity entity) throws CommonException {
		auto=(Autoveicolo)entity;
		checkStringhe();
		checkDate();
		checkInteri();
	}
	private void checkInteri() throws CommonException {
		if(auto.getCilindrata()<0)
			throw new CommonException("Cilindrata non valida");
		else if(auto.getPotenza()<0)
			throw new CommonException("Potenza non valida");
		else if(auto.getUltimoKm()<0)
				throw new CommonException("Kilometri non validi");
		else if(auto.getPrezzo()<0)
				throw new CommonException("Prezzo non valido");
			
			
	}
	private void checkDate() throws CommonException {
		LocalDate d=auto.getImmatricolazione();
		LocalDate d2=auto.getDataScadAssic();
		if(d==null )
			throw new CommonException("Data immatricolazione vuota");
		else if(d2==null)
				throw new CommonException("Data Scadenza assicurazione vuota");
		else if(d.getYear()<1807)
			throw new CommonException("Prima del 1807 non esistevano auto ");
		else if(d.isBefore(LocalDateTime.now().toLocalDate()))
			throw new CommonException("Immatricolazione non valida, è dal futuro!");
	}
	private void checkStringhe()  throws CommonException {
		if(auto.getMarca().isEmpty())
			throw new CommonException("La marca non può essere vuota");
		else if(auto.getModello().isEmpty())
			throw new CommonException("Il modello non può essere vuoto");
		else if(auto.getTarga().isEmpty())
			throw new CommonException("La targa non può essere vuota");
		else if(auto.getNroTelaio().isEmpty())
			throw new CommonException("Numero telaio vuoto");
	}

}
