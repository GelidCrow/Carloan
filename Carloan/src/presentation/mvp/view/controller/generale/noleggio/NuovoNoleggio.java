package presentation.mvp.view.controller.generale.noleggio;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import utility.Finestra;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Noleggio.Optional.ChilometraggioIllimitato;
import business.entity.Noleggio.Optional.Guidatore;
import business.entity.Noleggio.Optional.GuidatoreAggiuntivo;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.Seggiolino;
import business.model.Exception.CommonException;

public class NuovoNoleggio extends ImpostaNoleggio<Entity>{

	private GuidatoreAggiuntivo guidatore= null;
	@FXML
	public void btnAggiungiOptionalNoleggio(ActionEvent e){
		if(tbOptionalNoleggio.getSelectionModel().getSelectedIndex()<0 ){
			AlertView.getAlertView("Nessun elemento selezionato", AlertType.WARNING);
		}
		else{
			ObservableList<Entity> listItem= tbOptionalScelti.getItems();
			Optional itemSelected = (Optional) tbOptionalNoleggio.getSelectionModel().getSelectedItem();
			int numScelto = choiceGuidatori.getSelectionModel().getSelectedItem();
			if(itemSelected instanceof GuidatoreAggiuntivo  && !listItem.contains(itemSelected)){
				
				for(int i=0;i<guidatoriAggiuntivi.size();i++){
					if(guidatoriAggiuntivi.get(i).getNumero_guidatori()==numScelto){
						guidatore= guidatoriAggiuntivi.get(i);
						listItem.add(guidatore);//metto nell'altra tabella quello con l'elemento scelto.
						break;
					}
				 }
				controllaGuidatoreAggiuntivo();
			}
			else if(!listItem.contains(itemSelected)){
				tbOptionalScelti.getItems().add(itemSelected);
				controllaChilLimitato();
				}
			else 
				AlertView.getAlertView("Hai già aggiunto quest'optional", AlertType.WARNING);
		}
	}
	@FXML
	public void btnAggiungiOptionalAuto(ActionEvent e){
		if(tbOptionalAuto.getSelectionModel().getSelectedIndex()<0 ){
			AlertView.getAlertView("Nessun elemento selezionato", AlertType.WARNING);
		}
		else{
			ObservableList<Entity> listItem= tbOptionalScelti.getItems();
			Optional itemSelected = (Optional) tbOptionalAuto.getSelectionModel().getSelectedItem();
			int numScelto = choiceSeggiolini.getSelectionModel().getSelectedItem();
			if(itemSelected instanceof Seggiolino  && !listItem.contains(itemSelected)){
				for(int i=0;i<seggiolini.size();i++){
					if(seggiolini.get(i).getnumero()==numScelto){
						listItem.add(seggiolini.get(i));//metto nell'altra tabella quello con l'elemento scelto.
						break;
					}
				 }
			}
			else if(!listItem.contains(itemSelected)){
				listItem.add(itemSelected);
			}
			else 
				AlertView.getAlertView("Hai già aggiunto quest'optional", AlertType.WARNING);
		}
	}
	/**
	 * <p>Setta ad abilitato i valori di guidatore aggiuntivo</p>
	 */
	private void impostaTrueGuidatoreAggiuntivo(){
		txtNome.setDisable(false);
		txtCognome.setDisable(false);
		txtIndirizzo.setDisable(false);
		txtCodFiscale.setDisable(false);
		txtPatente.setDisable(false);
		btnRimuovi.setVisible(true);
		tbGuidatori.setVisible(true);
		campiDisattivi=false;
	}
	
	/**
	 * <p>Setta ad abilitato i valori di guidatore aggiuntivo</p>
	 */
	private void controllaGuidatoreAggiuntivo(){
		ObservableList<Entity> listItem=  tbOptionalScelti.getItems();
		for(Entity e: listItem){
			if((Optional)e instanceof GuidatoreAggiuntivo)
			{
				impostaTrueGuidatoreAggiuntivo();
				return;
			}
		}
		impostaFalsoTxtGuidatore();
	}
	/**
	 * <p>Disabilita la scelta del limite di copertura</p>
	 */
	private void controllaChilLimitato(){
		ObservableList<Entity> listItem=  tbOptionalScelti.getItems();
		for(Entity e: listItem){
			if((Optional)e instanceof ChilometraggioIllimitato)
			{
				choiceLimite.setDisable(true);
				lblCostoKm.setDisable(true);
				return;
			}
		}
		choiceLimite.setDisable(false);
		lblCostoKm.setDisable(false);
	}
	
	private Set<Guidatore> guidatori= new HashSet<Guidatore>();//elimino i duplicati 
	ObservableList<Entity> listItem = FXCollections.observableArrayList(guidatori);
	@FXML
	public void btnAggiungiGuidatore(ActionEvent e){
		try{
			//se i campi del giudatore non sono stati disattivati
		 if(!campiDisattivi){
			 caricaTabella( listItem,tbGuidatori);
			 //se la tabella non contiene già tutti gli elementi massimi da inserire in base a quello scelto
			 
			 if(tbGuidatori.getItems().size()<guidatore.getNumero_guidatori()){
				if(txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || txtIndirizzo.getText().isEmpty() || 
						txtCodFiscale.getText().isEmpty() || txtPatente.getText().isEmpty())
					throw new CommonException("Compilare tutti i campi prima di procedere con l'aggiunta");	
				else{
				   try{
			        	Integer.parseInt(txtPatente.getText());
			        	Guidatore guidatore = new Guidatore(txtNome.getText(),txtCognome.getText(),txtIndirizzo.getText(),
			        								txtCodFiscale.getText(),txtPatente.getText(),ottieniIDOptional());
						try {
							presenter.processRequest("VerificaGuidatore", guidatore);
							//se l'elemento viene aggiunto, in quanto è un hashset, se trova un duplicato su codice fiscaale non l'aggiungerà
							if(guidatori.add(guidatore)) {
								listItem= FXCollections.observableArrayList(guidatori);
								tbGuidatori.setItems(listItem);
							}
							else {
								throw new CommonException("C'è già qualche guidatore con Patente e/o cod.Fiscale uguale a quelle inserite");
							}
						}	
						catch(CommonException e1){
							e1.showMessage();
						}
						catch(InvocationTargetException e1){
							new CommonException(e1.getTargetException().getMessage()).showMessage();
						}
						catch (InstantiationException | IllegalAccessException
								| ClassNotFoundException | NoSuchMethodException
								| SecurityException | IllegalArgumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					catch(NumberFormatException e2){
						throw new CommonException("La patente deve essere costituita da soli numeri");					
					}
					}
			 }
			 else 
				 throw new CommonException("Hai già impostato tutti i guidatori");
		}
		else 
			throw new CommonException("Se vuoi aggiungere un guidatore seleziona e aggiungi come optional il guidatore Aggiuntivo");
		}
		catch(CommonException e1){
			e1.showMessage();}
	}
	
	private int ottieniIDOptional(){
		ObservableList<Entity> listItem=  tbOptionalScelti.getItems();
		for(Entity e: listItem){
			if((Optional)e instanceof GuidatoreAggiuntivo)
			{
				return ((Optional)e).getId();
			}
		}
		return 0;
	}
	@FXML
	public void	btnRimuoviGuidatore(ActionEvent e){
		if(tbGuidatori.getSelectionModel().getSelectedIndex()<0 ){
			AlertView.getAlertView("Nessun elemento selezionato", AlertType.WARNING);
		}
		else{
			guidatori.remove(tbGuidatori.getSelectionModel().getSelectedItem());
			tbGuidatori.getItems().remove(tbGuidatori.getSelectionModel().getSelectedItem());
			controllaGuidatoreAggiuntivo();
			}
	}

	@FXML
	public void btnRimuoviOptional(ActionEvent e){
		if(tbOptionalScelti.getSelectionModel().getSelectedIndex()<0){
			AlertView.getAlertView("Nessun elemento selezionato", AlertType.WARNING);
		}
		else{
				tbOptionalScelti.getItems().remove(tbOptionalScelti.getSelectionModel().getSelectedItem());
				controllaChilLimitato();
				controllaGuidatoreAggiuntivo();
			}
	}
	
	@FXML
	public void btnAggiungiCartaCredito(ActionEvent e){
		if(cliente==null){
			AlertView.getAlertView("Selezionare prima un contratto", AlertType.WARNING);
		}
		else {	
			FXMLParameter.setRidimensionabile(false);
			FXMLParameter.setTitolo("Nuova Carta Di Credito");
			FXMLParameter.setHand(false);
			Finestra.visualizzaFinestra(presenter, FXMLParameter, this, "MostraSchermataInserimentoCartaCredito",Modality.APPLICATION_MODAL);
		 }
		}
	
	
	public Cliente getEntitaElementoSelezionato(){
		return cliente;
	}
	@FXML
	public void btnCancella(ActionEvent e){
		
	}
	@FXML
	public void btnConferma(ActionEvent e){
		
	}
	
	
	@FXML
	public void dRitiroAction(ActionEvent e){
		final int MASSIMO_INIZIO= 3;
		try {
			LocalDate lDate= dRitiro.getValue();
			if(lDate!=null){
				if(lDate.isBefore(LocalDate.now())){
					impostaDate(LocalDate.now());
				}
				if (lDate.isAfter(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()+MASSIMO_INIZIO))){
					impostaDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()+MASSIMO_INIZIO));		
				}
				else 
					impostaDate(lDate);
			}
			else 
				throw new CommonException("Data di Ritiro non impostata");
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	
	
	@FXML
	public void btnCalcolaPrezzo(ActionEvent e){
		//somma tutti i valori : 
		float acconto = 0;
		float costoKilometri=0;
		//sommo il costo degli optional
		ObservableList<Entity> optional= tbOptionalScelti.getItems();
		for(Entity op: optional){
			acconto+=((Optional)op).getPrezzo();
		}
		acconto+= ((Autoveicolo)tbAutoveicolo.getSelectionModel().getSelectedItem()).getPrezzo();//if selected controlla sennò dai messaggio.
		
		//moltiplico il costo al kilometro della fascia per il limite di chilometri.
		costoKilometri= choiceFascia.getSelectionModel().getSelectedItem().getCosto_kilometrico() * choiceLimite.getSelectionModel().getSelectedItem();
		
		
		
		
	}

}
