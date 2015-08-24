package business.entity.pagamento;

import java.util.ArrayList;
import java.util.List;

public enum tipiCircuiti {
	MasterCard,Visa;
	
	public static List<String> getAllCircuiti(){
		List<String> stati= new ArrayList<String>();
		stati.add("MasterCard");
		stati.add("Visa");
		return stati;
	}
	
}
