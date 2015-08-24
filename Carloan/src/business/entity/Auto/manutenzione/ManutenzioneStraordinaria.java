package business.entity.Auto.manutenzione;

import java.time.LocalDate;

public class ManutenzioneStraordinaria extends Manutenzione{
	public ManutenzioneStraordinaria(String iDManutenzione, LocalDate datainizio,
			LocalDate dataFine, String note,int i) {
		super(iDManutenzione, datainizio, dataFine, note,i);
	}
}
