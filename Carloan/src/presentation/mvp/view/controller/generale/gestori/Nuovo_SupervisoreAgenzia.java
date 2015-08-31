package presentation.mvp.view.controller.generale.gestori;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Login;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Luoghi.Agenzia;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
import MessaggiFinestra.AlertView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;

public class Nuovo_SupervisoreAgenzia extends Schermata{
	@FXML
	protected TextField nome;
	@FXML
	protected TextField cognome;
	@FXML
	protected TextField indirizzo;
	@FXML
	protected TextField codfis;
	@FXML
	protected TextField nfisso;
	@FXML
	protected TextField ncell;
	@FXML
	protected TextField username;
	@FXML
	protected PasswordField password;
	@FXML
	protected RadioButton radio_m;
	@FXML
	protected RadioButton radio_f;
	@FXML
	protected DatePicker datanas;
	@FXML
	protected Label labelsedi;
	
	protected ToggleGroup tog;
	@FXML
	protected Label informazioni;
	@FXML
	protected TableView<Agenzia> table_agenzia;
	protected ObservableList<TableColumn<Agenzia,?>> agenzia;
	protected TableView<SupervisoreAgenzia> tw;
	protected Utente u;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		tog=new ToggleGroup();
		radio_f.setToggleGroup(tog);
		radio_m.setToggleGroup(tog);
		radio_m.setSelected(true);
		radio_f.setSelected(false);
		agenzia=table_agenzia.getColumns();
		this.u=UtenteCorrente.getUtente();
		if(u instanceof SupervisoreAgenzia){
			informazioni.setVisible(false);
			table_agenzia.setVisible(false);
		}
		else{
			bindValues();
			initTableAgenzie();
			}
	}
	
	protected void bindValues(){ 
		agenzia.get(0).setCellValueFactory(cellData -> new SimpleIntegerProperty(((Agenzia) cellData.getValue()).getIDAgenzia()));
		agenzia.get(1).setCellValueFactory(cellData -> new SimpleStringProperty(((Agenzia) cellData.getValue()).getNome()));
		agenzia.get(2).setCellValueFactory(cellData -> new SimpleStringProperty(((Agenzia) cellData.getValue()).getNumTelefono()));
	}
	protected List<Agenzia> DownloadAgenzie(){
		List<Agenzia> a=new LinkedList<Agenzia>();
			try {
				if(u instanceof Amministratore)
				a= (List<Agenzia>)presenter.processRequest("getAllAgenzie", null);					
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				e.printStackTrace();
			}
			return a;
	}
	protected void initTableAgenzie(){
		bindValues();
		List<Agenzia> list=DownloadAgenzie();
		ObservableList<Agenzia> obsList= FXCollections.observableList(list);
		table_agenzia.setItems(obsList);
		table_agenzia.getSelectionModel().selectFirst();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@FXML
	public void btnconferma(ActionEvent e){
		SchermataGenerale<SupervisoreSede> schermataGenerale = (SchermataGenerale<SupervisoreSede>)this.getChiamante();
		tw= ((SchermataGenerale<SupervisoreSede>)schermataGenerale).getTable("SupervisoreSede");
		try {
			SupervisoreSede a=prendiDatiDaView();
			Login login=prendiDatiPerLogIn();
			//Verifico se l'username non è stato scelto già
			presenter.processRequest("VerificaCredenziali",login);
			presenter.processRequest("InserisciSupervisoreSede", a);
			a=(SupervisoreSede) presenter.processRequest("leggiSupervisoreSedeByCodiceFiscale", a.getCodiceFiscale());
			login.setSupS(String.valueOf(a.getIdUtente()));
			presenter.processRequest("InserisciCredenziali", login);
			/*Aggiorna la tabella nella schermata generale*/
			if(u instanceof Amministratore)
			schermataGenerale.caricaTabella((List<SupervisoreSede>)presenter.processRequest("getAllSupervisoriSede",null), tw);
			else if (u instanceof SupervisoreAgenzia){
				List<Sede> ls=(List<Sede>)presenter.processRequest("getAllSediByAgenzia", ((SupervisoreAgenzia)u).getIDAgenzia());
				List<SupervisoreSede> l=new LinkedList<SupervisoreSede>();
				for(Sede s:ls)
					l.addAll((Collection<? extends SupervisoreSede>) presenter.processRequest("leggiSupervisoriSedebySede",s.getIDSede()));
				schermataGenerale.caricaTabella(l, tw);
			}
			else // SupervisoreSede
			schermataGenerale.caricaTabella((List<SupervisoreSede>) presenter.processRequest("leggiSupervisoriSedebySede",((SupervisoreSede)u).getIDSede()), tw);
			/**/
			
			chiudiFinestra();
			
			}
				catch (CommonException e1) {
					// TODO Auto-generated catch block
					e1.showMessage();
				}
		catch(InvocationTargetException e1){
			new CommonException(e1.getTargetException().getMessage()).showMessage();
		}
			 catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
				 e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	
	private Login prendiDatiPerLogIn() throws CommonException {
		String user_name=username.getText();
		if(user_name==null || user_name.isEmpty() || user_name.length()<4)
			throw new CommonException("Username non valido");
		String passw=password.getText();
		if(passw==null || passw.isEmpty() || passw.length()<4)
			throw new CommonException("Password non valida");
			
		return new Login(user_name, passw);
	}
	protected SupervisoreSede prendiDatiDaView() throws CommonException {
		SupervisoreSede a=new SupervisoreSede();
		String n=nome.getText();
		if(n==null || n.isEmpty())
			throw new CommonException("Il nome è vuoto");
		a.setNome(n);
		n=cognome.getText();
		if(n==null || n.isEmpty())
			throw new CommonException("Il cognome è vuoto");
		a.setCognome(n);
		if(radio_m.isSelected())
			a.setSesso("Maschio");
		else
			a.setSesso("Femmina");
		LocalDate datanasc=datanas.getValue();
		if(datanasc==null)
			throw new CommonException("Data nascita vuota");
		if(datanasc.isAfter(LocalDate.now()))
			throw new CommonException("Data nascita nel futuro");
		a.setDataNascita(datanasc);
		n=indirizzo.getText();
		if(n==null)
			n="";
		a.setIndirizzo(n);
		n=codfis.getText();
		if(n==null || n.isEmpty())
			throw new CommonException("Codice fiscale vuoto");
		if(n.length()!=16)
			throw new CommonException("Codice fiscale non valido(deve essere di 16 caratteri)");
		a.setCodiceFiscale(n);
		n=nfisso.getText();
		if(n==null)
			n="";
		a.setNumFisso(n);
		n=ncell.getText();
		if(n==null)
			n="";
			a.setNumCell(n);
		if(u instanceof Amministratore || u instanceof SupervisoreAgenzia)
			a.setIDSede(tb_sedi.getSelectionModel().getSelectedItem().getIDSede());
		else
			a.setIDSede(((SupervisoreSede)u).getIDSede());
		
		return  a;
	}
	@FXML
	public void btnannulla(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
}
