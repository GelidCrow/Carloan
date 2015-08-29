package presentation.mvp.view.controller.generale.gestori;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Login;
import business.entity.Auto.Autoveicolo;
import business.entity.Gestori.Amministratore;
import business.model.Exception.CommonException;
import MessaggiFinestra.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;

public class Nuovo_Amministratore extends Schermata{
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
	protected ToggleGroup tog;
	protected TableView<Amministratore> tw;
	public void initialize(URL arg0, ResourceBundle arg1) {
		tog=new ToggleGroup();
		radio_f.setToggleGroup(tog);
		radio_m.setToggleGroup(tog);
		radio_m.setSelected(true);
		radio_f.setSelected(false);
	}
	@FXML
	public void btnconferma(ActionEvent e){
		SchermataGenerale<Amministratore> schermataGenerale = (SchermataGenerale<Amministratore>)this.getChiamante();
		tw= ((SchermataGenerale<Amministratore>)schermataGenerale).getTable("Amministratore");
		try {
			Amministratore a=prendiDatiDaView();
			Login login=prendiDatiPerLogIn();
			//Verifico se l'username non � stato scelto gi�
			presenter.processRequest("VerificaCredenziali",login);
			
			}
				catch (CommonException e1) {
					// TODO Auto-generated catch block
					e1.showMessage();
				}
			 catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException e1) {
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
	private Amministratore prendiDatiDaView() throws CommonException {
		Amministratore a=new Amministratore();
		String n=nome.getText();
		if(n==null || n.isEmpty())
			throw new CommonException("Il nome � vuoto");
		a.setNome(n);
		n=cognome.getText();
		if(n==null || n.isEmpty())
			throw new CommonException("Il cognome � vuoto");
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
		a.setCodiceFiscale(n);
		n=nfisso.getText();
		if(n==null)
			n="";
		a.setNumFisso(n);
		n=ncell.getText();
		if(n==null)
			a.setNumCell(n);
		
		return  a;
	}
	@FXML
	public void btnannulla(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
}
