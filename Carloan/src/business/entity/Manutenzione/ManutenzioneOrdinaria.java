package business.entity.Manutenzione;

import java.util.Date;

public class ManutenzioneOrdinaria extends Manutenzione{
	public ManutenzioneOrdinaria(String iDManutenzione, Date datainizio,
			Date dataFine, String note) {
		super(iDManutenzione, datainizio, dataFine, note);
	}
}
