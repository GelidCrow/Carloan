package business.entity.Auto.manutenzione;

import java.time.LocalDate;

public class ManutenzioneOrdinaria extends Manutenzione{
	public ManutenzioneOrdinaria(String iDManutenzione, LocalDate localDate,
			LocalDate dataFine, String note) {
		super(iDManutenzione, localDate, dataFine, note);
	}
}
