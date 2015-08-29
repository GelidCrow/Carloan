package presentation.mvp.view.controller.generale;



import integration.DAO.connection.Connection;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Utente;
import business.entity.UtenteCorrente;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Fascia.Fascia;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Luoghi.Agenzia;
import business.entity.Luoghi.Sede;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.entity.pagamento.CartaDiCredito;
import business.entity.pagamento.Pagamento;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import MessaggiFinestra.AlertView;
import utility.Finestra;
import utility.ParametriFXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;

public class SchermataGenerale<T extends Entity> extends Schermata{
	@FXML
	private TabPane tabPane;
	private ObservableList<Tab> panes;
	@FXML
	private ImageView auto_image;
	@FXML
	private TableView<T> tbCliente;	
	@FXML
	private TableView<T> tbContratto;
	@FXML
	private TableView<T> tbNoleggio;
	@FXML
	private TableView<T> tbAuto;
	@FXML
	private TableView<T> tbAgenzia;
	@FXML
	private TableView<T> tbSede;
	@FXML
	private TableView<T> tablesupsede;
	@FXML
	private TableView<T> tbAmministratore;
	
	@FXML
	private Label txtBenvenuto;
	@FXML
	private TableColumn<Cliente,String> cliente;
	
	@FXML
	private ChoiceBox<Fascia> choice_fascia;
	@FXML
	private Label nome_sede;
	@FXML
	private Label nome_sa;
	@FXML
	private Label cognome_sa;
	
	@FXML
	private Label telefono_sede;
	@FXML
	private Label indirizzo_sede;
	
	@FXML
	private MenuButton btnManutenzione;
	
	@FXML
	private MenuButton btnMulta;
	private TabClienti tbClientController;

	private TabContratto tbContrattoController;
	
	private TabNoleggio tbNoleggioController;
	private TabAgenzia tbAgenziaController;
	private TabSede tbSedeController;
	private TabAmministratore tbAmministratoreController;
	private TabAuto tbAutoController;
	@FXML
	private Label nome_agenzia;
	@FXML
	private Label tel_agenzia;
	@FXML
	private Label nome_supsede;
	@FXML
	private Label cognome_supsede;
	@FXML
	private Label codfis_supsede;;
	@FXML
	private Label superv_agenzia;
	
		/***********  CONTRATTO *************/
	@FXML
	public void btnNuovoContratto(ActionEvent e){
		tbContrattoController.NuovoContratto();
		
	}	
	@FXML
	public void btnModificaContratto(ActionEvent e){
		try {
			tbContrattoController.ModificaContratto();
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}	
	
	@FXML
	public void btnChiudiContratto(ActionEvent e) {
		try {
			try {
				tbContrattoController.ChiudiContratto();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}		
			/************ CLIENTE *********/
	@FXML
	public void btnNuovoCliente(ActionEvent e){
		tbClientController.NuovoCliente();
	}

	
	@FXML
	public void btnModificaCliente(ActionEvent e) {
		try {
			tbClientController.ModificaCliente();
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	
	
		/*********** NOLEGGIO ************/ 
	@FXML
	public void btnNuovoNoleggio(ActionEvent e) {
		tbNoleggioController.NuovoNoleggio();
	}
	
	@FXML
	public void btnAnnullaNoleggio(ActionEvent e) {
		
	}
	
	@FXML
	public void btnChiudiNoleggio(ActionEvent e){
		
	}
	
	
	/*Auto*/
	@FXML
	public void btnNuovaAuto(ActionEvent e){
		tbAutoController.NuovaAuto();
	}
	public void btnModificaAuto(ActionEvent e){
		try {
			tbAutoController.ModificaAuto();
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	@FXML
	public void btnnuova_agenzia(ActionEvent e){
		tbAgenziaController.NuovaAgenzia();
	}
	@FXML
	public void btnmodifica_agenzia(ActionEvent e){
		try {
			tbAgenziaController.ModificaAgenzia();
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	@FXML
	public void btnnuova_sede(ActionEvent e){
		tbSedeController.NuovaSede();
	}
	@FXML
	public void btnmodifica_sede(ActionEvent e){
		try {
			tbSedeController.ModificaSede();
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}

	@FXML
	public void btnnuovo_amministratore(ActionEvent e){
		tbAmministratoreController.NuovoAmministratore();
	}
	@FXML
	public void btnmodifica_amministratore(ActionEvent e){
		try {
			tbAmministratoreController.ModificaAmministratore();
		} catch (CommonException e1) {
			// TODO Auto-generated catch block
			e1.showMessage();
		}
	}
	
	
	@FXML
	public void btnVOptional(ActionEvent e){
		tbNoleggioController.visualizzaOptional();
	}
	
	
	/*Carta di credito*/
	public void btnAggiungiCartaCredito(ActionEvent e){
		tbClientController.NuovaCartaCredito();
	}
	public void btnVisualizzaCartaCredito(ActionEvent e){
		tbClientController.VisualizzaCartaCredito();
	}
	
	
	@FXML
	public void btnLogout(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?",AlertType.CONFIRMATION);
		 
		if(result.isPresent() && result.get() == ButtonType.OK){
			chiudiFinestra();
			try {
				Connection.chiudiConnessione();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			FXMLParameter.setTitolo("Login");
		    FXMLParameter.setRidimensionabile(false);
			Finestra.visualizzaFinestra(presenter,FXMLParameter,this,"MostraLogin",Modality.WINDOW_MODAL);
		}
	}
	
	/**
	 * <p>Carica la tabella dei clienti graficamente</p>
	 * @param listaClienti
	 * @return
	 */
	public  void caricaTabella(List<T> list,TableView<T> table){
		ObservableList<T> obsList= FXCollections.observableList(list);
		table.setItems(obsList);
	}
	
	public TableView<T> getTable(String table){
		if(table.equals("Cliente")){
			return tbCliente;
		}
		else if(table.equals("Contratto"))
			return tbContratto;
		else if(table.equals("Noleggio"))
			return tbNoleggio;
		else if(table.equals("Autoveicolo"))
			return tbAuto;
		else if(table.equals("Agenzia"))
			return tbAgenzia;
		else if(table.equals("SupSede"))
			return tablesupsede;
		else if(table.equals("Sede"))
			return tbSede;
		else if(table.equals("Amministratore"))
			return tbAmministratore;
		else
			return null;
	}
	public int getElemSelezionato(String table){
		if(table.equals("Cliente")){
			return tbCliente.getSelectionModel().getSelectedIndex();
		}
		else if(table.equals("Contratto"))
			return tbContratto.getSelectionModel().getSelectedIndex();
		else if(table.equals("Noleggio"))
			return tbContratto.getSelectionModel().getSelectedIndex();
		else if(table.equals("Autoveicolo"))
			return tbAuto.getSelectionModel().getSelectedIndex();
		else if(table.equals("Agenzia"))
			return tbAgenzia.getSelectionModel().getSelectedIndex();
		else if(table.equals("Amministratore"))
			return tbAmministratore.getSelectionModel().getSelectedIndex();
		else
			return 0;
	}
	
	public T getEntitaElementoSelezionato(String table){
		if(table.equals("Cliente")){
			return tbCliente.getSelectionModel().getSelectedItem();
		}
		else if(table.equals("Contratto")){
			return tbContratto.getSelectionModel().getSelectedItem();
		}
		else if(table.equals("Noleggio")){
			return tbNoleggio.getSelectionModel().getSelectedItem();
		}
		else if(table.equals("Autoveicolo"))
			return tbAuto.getSelectionModel().getSelectedItem();
		else if(table.equals("Agenzia"))
			return tbAgenzia.getSelectionModel().getSelectedItem();
		else if(table.equals("Sede"))
			return tbSede.getSelectionModel().getSelectedItem();
		else if(table.equals("Amministratore"))
			return tbAmministratore.getSelectionModel().getSelectedItem();
		
		else
			return null;
	}
	
	
	/**
	 * <p> Ascoltatore per il cambio di tab </p>
	 */
	class TabChangeListener<X> implements ChangeListener<Tab>{
		

		/**
		 * <p>Quando selezioni il tab "Cliente" vengono caricati SOLO 1 VOLTA tutti i clienti</p>
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
	    public void changed(ObservableValue<? extends Tab> ov, Tab oldValue, Tab newValue) {
			//cliente
			if(panes.get(2)==newValue){
				if(tbClientController==null){
					try {
						//serve solo per fargli fare il binding con le colonne
						tbClientController = new TabClienti((TableView<Cliente>)tbCliente,SchermataGenerale.this);
						//carica la prima volta la tabella 
						caricaTabella((List<T>)presenter.processRequest("getAllClienti",null),tbCliente);
						
					} catch (InstantiationException | IllegalAccessException| ClassNotFoundException| NoSuchMethodException | SecurityException
							| IllegalArgumentException	
							| InvocationTargetException
							| CommonException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
			}
			//noleggio
			else if(panes.get(1)==newValue){
				if(tbNoleggioController==null){
					try {
						//serve solo per fargli fare il binding con le colonne
						tbNoleggioController = new TabNoleggio((TableView<Noleggio>)tbNoleggio,SchermataGenerale.this);
						//carica la prima volta la tabella 
						caricaTabella((List<T>)presenter.processRequest("getAllNoleggi",null),tbNoleggio);
						ObservableList<MenuItem> items=btnMulta.getItems();
						for(MenuItem m:items){
							switch(m.getText()){
							case "Aggiungi":
								m.setOnAction(new EventHandler<ActionEvent>() {
						            public void handle(ActionEvent t) {
						            	tbNoleggioController.NuovaMulta();
						            }
						        });  
								break;
							case "Visualizza":
								m.setOnAction(new EventHandler<ActionEvent>() {
						            public void handle(ActionEvent t) {
						            	tbNoleggioController.VisualizzaMulta();
						            }
						        });  
								break;
								
							}
						}
					} catch (InstantiationException | IllegalAccessException| ClassNotFoundException| NoSuchMethodException | SecurityException
							| IllegalArgumentException	
							| InvocationTargetException
							| CommonException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
			}
			else if(panes.get(0)==newValue){}
			
			//Auto
			else if(panes.get(3)==newValue){
				if(tbAutoController==null){
					tbAutoController=new TabAuto((TableView<Autoveicolo>)tbAuto,SchermataGenerale.this);
					ObservableList<MenuItem> items=btnManutenzione.getItems();
					for(MenuItem m:items){
						switch(m.getText()){
						case "Aggiungi":
							m.setOnAction(new EventHandler<ActionEvent>() {
					            public void handle(ActionEvent t) {
					            	try {
										tbAutoController.NuovaManutenzione();
									} catch (CommonException e) {
										// TODO Auto-generated catch block
										e.showMessage();
									}
					            }
					        });  
							break;
						case "Chiudi":
							m.setOnAction(new EventHandler<ActionEvent>() {
					            public void handle(ActionEvent t) {
					            	try {
										tbAutoController.ChiudiManutenzione();
									} catch (CommonException e) {
										// TODO Auto-generated catch block
										e.showMessage();
									}
					            }
					        });  
							break;
						case "Visualizza":
							m.setOnAction(new EventHandler<ActionEvent>() {
					            public void handle(ActionEvent t) {
					            	try {
										tbAutoController.VisualizzaManutenzione();
									} catch (CommonException e) {
										// TODO Auto-generated catch block
										e.showMessage();
									}
					            }
					        });  
							break;
							
						}
					}
					try {
						List<Fascia> l=(List<Fascia>)presenter.processRequest("getAllFasce", null);
						ObservableList<Fascia> obs=FXCollections.observableArrayList(l);
						choice_fascia.setItems(obs);
						choice_fascia.getSelectionModel().selectedItemProperty().addListener(new ItemChoiceSelectedFasce());
						choice_fascia.getSelectionModel().selectFirst();
						
					} catch (InstantiationException | IllegalAccessException
							| ClassNotFoundException | NoSuchMethodException
							| SecurityException | IllegalArgumentException
							| InvocationTargetException | CommonException e) {
						e.printStackTrace();
					}
				}
			}
			//agenzia
			else if(panes.get(4)==newValue){
				tbAgenziaController=new TabAgenzia((TableView<Agenzia>)tbAgenzia,SchermataGenerale.this);
				try {
					List<Agenzia> l=(List<Agenzia>)presenter.processRequest("getAllAgenzie", null);
					caricaTabella((List<T>)l, tbAgenzia);
					tbAgenzia.getSelectionModel().selectFirst();
			
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalArgumentException
						| InvocationTargetException | CommonException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//sede
			else if(panes.get(5)==newValue){
				tbSedeController=new TabSede((TableView<Sede>)tbSede,SchermataGenerale.this,(TableView<SupervisoreSede>) tablesupsede);
				try {
					Utente u=UtenteCorrente.getUtente();
					List<Sede> l;
					if(u instanceof Amministratore)
					l=(List<Sede>)presenter.processRequest("getAllSedi", null);
					else//sono un supervisore agenzia
						l=(List<Sede>)presenter.processRequest("getAllSediByAgenzia", ((SupervisoreAgenzia)u).getIDAgenzia());
					
					caricaTabella((List<T>)l, tbSede);
					tbSede.getSelectionModel().selectFirst();
			
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalArgumentException
						| InvocationTargetException | CommonException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(panes.get(6)==newValue){
				tbAmministratoreController=new TabAmministratore((TableView<Amministratore>)tbAmministratore,SchermataGenerale.this);
				List<Amministratore> l;
				try {
					l = (List<Amministratore>)presenter.processRequest("getAllAmministratori", null);
					caricaTabella((List<T>)l, tbAmministratore);
					
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalArgumentException
						| InvocationTargetException | CommonException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
	    }
	} 
	@FXML
	private ImageView imgAutoNoleggio;
	@FXML
	private Label lblTarga;
	@FXML
	private Label lblMarca;
	@FXML
	private Label lblModello;
	@FXML
	private Label lblNSede;
	@FXML
	private Label lblIndirizzo;
	@FXML
	private Label lblTPagamento;
	@FXML
	private Label lblPrezzo;
	@FXML
	private Label lblCauzione;
	@FXML
	private Label lblUltAddebiti;
	@FXML
	private Label lblIban;
	@FXML
	private Label lblCodContratto;
	@FXML
	private Label lblDAp;
	@FXML
	private Label lblDChiusura;
	@FXML
	private Label lblStato;
	@FXML
	private Label lblCodFiscale;
	@FXML
	private Label lblNome;
	@FXML
	private Label lblCognome;
	
	private class 	ItemSelectedNoleggio implements ChangeListener<Noleggio>{
		@Override
		public void changed(ObservableValue<? extends Noleggio> observable,
				Noleggio oldValue, Noleggio newValue) {
			if(newValue!=null){
				lblTarga.setText("");
				lblMarca.setText("");
				lblModello.setText("");
				lblNSede.setText("");
				lblIndirizzo.setText("");
				lblCodContratto.setText("");
				lblDAp.setText("");
				lblCodFiscale.setText("");
				lblNome.setText("");
				lblCognome.setText("");
				lblPrezzo.setText("");
				lblCauzione.setText("");
				lblUltAddebiti.setText("");
				lblTPagamento.setText("");
				lblIban.setText("");
				Autoveicolo auto;
				//IMMAGINE AUTO
				try {
					InputStream i;
					try {
						auto=(Autoveicolo)presenter.processRequest("letturaAutoveicolo", newValue.getIdAuto());
						i = (InputStream)auto.getImmagine_stream();
				
					if(i!=null)
						imgAutoNoleggio.setImage(new Image(i));
					else
						imgAutoNoleggio.setImage(null);
					
					//dati auto : 
					lblTarga.setText(auto.getTarga());
					lblMarca.setText(auto.getMarca());
					lblModello.setText(auto.getModello());
					//DATI SEDE
					Sede sede=(Sede)presenter.processRequest("leggiSede",newValue.getSedeRestituzione());
					lblNSede.setText(sede.getNome());
					lblIndirizzo.setText(sede.getIndirizzo());
					//CONTRATTO
					Contratto contratto=(Contratto)presenter.processRequest("letturaContratto",newValue.getIdcontratto());
					lblCodContratto.setText(String.valueOf(contratto.getIDContratto()));
					lblDAp.setText(contratto.getDataCreazione().toString());
					if(contratto.getDataChiusura()!=null)
						lblDChiusura.setText(contratto.getDataChiusura().toString());
					lblStato.setText(contratto.getStato());
					//CLIENTE
					Cliente cliente=(Cliente)presenter.processRequest("letturaCliente",contratto.getIdCliente());
					lblCodFiscale.setText(cliente.getCodFiscale());
					lblNome.setText(cliente.getNome());
					lblCognome.setText(cliente.getCognome());
					//PAGAMENTO
					Pagamento pagamento=(Pagamento)presenter.processRequest("letturaPagamento",newValue.getIdPagamento());
					lblPrezzo.setText(String.valueOf(pagamento.getAcconto()+pagamento.getImporto()));
					lblCauzione.setText(String.valueOf(pagamento.getDepositoCauzinale()));
					lblUltAddebiti.setText(String.valueOf(pagamento.getDetrazioneAggiuntiva()));
					if(pagamento instanceof CartaDiCredito ){
						lblTPagamento.setText("Carta di credito");
						CartaDiCredito carta = (CartaDiCredito)presenter.processRequest("letturaCarta",pagamento.getIdCarta());
						lblIban.setText(carta.getIBAN());
					}
					else{
						lblTPagamento.setText("Contanti");
					}				
				}	
				catch(CommonException e){
					e.showMessage();} 
				}
				catch (InstantiationException
						| IllegalAccessException
						| ClassNotFoundException
						| NoSuchMethodException | SecurityException
						| IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	 }
	}

	@FXML
	private Label lblCodFiscaleCliente;
	@FXML
	private Label lblNomeCliente;
	@FXML
	private Label lblCognomeCliente;
	@FXML
	private Label lblCodiceFiscaleGestore;
	@FXML
	private Label lblNomeGestore;
	@FXML
	private Label lblCognomeGestore;
	@FXML
	private Label lblContratto;

	/**
	 * <p> Ascoltatore per il cambio di elemento dal COntratto per settare i label per le info aggiuntive </p>
	 */

	private class ItemSelectedContratto implements ChangeListener<Contratto>{
	
	
		private void popolaLabelCliente(Cliente cliente){
			 lblCodFiscaleCliente.setText(cliente.getCodFiscale());
			 lblNomeCliente.setText(cliente.getNome());
			 lblCognomeCliente.setText(cliente.getCognome());
		}
		private void popolaLabelGestore(Utente utente){
			lblCodiceFiscaleGestore.setText(utente.getCodiceFiscale());
			lblNomeGestore.setText(utente.getNome());
			lblCognomeGestore.setText(utente.getCognome());
		}
		@Override
		public void changed(ObservableValue<? extends Contratto> observable,
				Contratto oldValue, Contratto newValue) {
			//interroga db
			try {
				if(newValue!=null){
					Cliente cliente = (Cliente)presenter.processRequest("letturaCliente",newValue.getIdCliente());
					popolaLabelCliente(cliente);
					Contratto contratto= (Contratto)getEntitaElementoSelezionato("Contratto");
					Utente gestore= null;
					if(contratto.getIDAmministratore()>0){
						gestore = (Amministratore)presenter.processRequest("letturaAmministratore",contratto.getIDAmministratore());
						lblContratto.setText("Contratto aggiunto dall'amministratore");
					}
					else if(contratto.getIDSupervisoreAgenzia()>0){
						gestore= (SupervisoreAgenzia)presenter.processRequest("letturaSupervisoreAgenzia",contratto.getIDSupervisoreAgenzia());
						lblContratto.setText("Contratto aggiunto dal Supervisore Agenzia");
					}
					else if(contratto.getIDSupervisoreSede()>0){
						gestore = (SupervisoreSede)presenter.processRequest("letturaSupervisoreSede",contratto.getIDSupervisoreSede());
						lblContratto.setText("Contratto aggiunto dal Supervisore Sede");
					}
					else if(contratto.getIDOperatore()>0){
						gestore = (Operatore)presenter.processRequest("letturaOperatore",contratto.getIDOperatore());
						lblContratto.setText("Contratto aggiunto dall'operatore");
					}
					popolaLabelGestore(gestore);
				}
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * <p>Elmina i tab che non possono essere usati dall'utente corrente</p>
	 */
	public void settaSchermataPerUtente(){
		Utente utente= UtenteCorrente.getUtente();
		String msgBenvenuto = "Benvenuto "+ utente.getNome() + " " + utente.getCognome() + "\n  Cod: "+  utente.getIdUtente();
		txtBenvenuto.setText(msgBenvenuto);
	
		if( utente instanceof Operatore){
			for(short i=3;i<panes.size();i++)
				panes.get(i).setDisable(true);
		}
		else if(utente instanceof SupervisoreSede){
			for(short i=4;i<9;i++)
				panes.get(i).setDisable(true);
		}
		else if(utente instanceof SupervisoreAgenzia){
			panes.get(4).setDisable(true);
			panes.get(6).setDisable(true);
			panes.get(7).setDisable(true);
		}	
	}
	private class ItemSelectedAgenzia implements ChangeListener<Agenzia>{

		@Override
		public void changed(ObservableValue<? extends Agenzia> observable,
				Agenzia oldValue, Agenzia newValue) {
			// TODO Auto-generated method stub
			if(newValue!=null){
				try {
					Object temp=presenter.processRequest("leggiSupervisoreAgenzia",((Agenzia) newValue).getIDAgenzia());
					if(temp!=null){
						SupervisoreAgenzia s=(SupervisoreAgenzia)temp;
					nome_sa.setText(s.getNome());
					cognome_sa.setText(s.getCognome());
					superv_agenzia.setVisible(false);
					}
					else{
						superv_agenzia.setVisible(true);
						nome_sa.setText("");
						cognome_sa.setText("");
					}
						
					
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalArgumentException
						| InvocationTargetException | CommonException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		}
		
	}
	private class ItemSelectedSede implements ChangeListener{
		@SuppressWarnings("unchecked")
		@Override
		public void changed(ObservableValue observable, Object oldValue,Object newValue) {
			if(newValue!=null){
			try {
				LinkedList<SupervisoreSede> temp=(LinkedList<SupervisoreSede>) presenter.processRequest("leggiSupervisoriSedebySede", ((Sede)newValue).getIDSede());
				caricaTabella((List<T>)temp, tablesupsede);
				Agenzia a=(Agenzia)presenter.processRequest("leggiAgenzia",((Sede)newValue).getIDAgenzia());
				nome_agenzia.setText(a.getNome());
				tel_agenzia.setText(a.getNumTelefono());
				
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException | CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	

		}
		
	}
	
	public Fascia getFascia(){
		return choice_fascia.getSelectionModel().getSelectedItem();
	}
	private class ItemSelectedAutoveicolo implements ChangeListener<Autoveicolo>{

		@Override
		public void changed(ObservableValue<? extends Autoveicolo> arg0,
				Autoveicolo arg1, Autoveicolo auto) {
			nome_sede.setText("");
			telefono_sede.setText("");
			indirizzo_sede.setText("");
			Autoveicolo a=auto;
			if(a!=null){
			try {
				InputStream i=(InputStream) presenter.processRequest("leggiImmagineAutoveicolo", a.getIDauto());
				if(i!=null)
				auto_image.setImage(new Image(i));
				else
					auto_image.setImage(null);
				Sede sede=(Sede)presenter.processRequest("leggiSede",a.getCodiceSedDisp());
				nome_sede.setText(sede.getNome());
				telefono_sede.setText(sede.getNumeroTelefono());
				indirizzo_sede.setText(sede.getIndirizzo());
				}
		
			catch(CommonException e){
				e.showMessage();
			}
			catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
			
		}
		
		
	}
	
	class ItemChoiceSelectedFasce implements ChangeListener<Fascia>{

		@SuppressWarnings("unchecked")
		@Override
		public void changed(ObservableValue<? extends Fascia> arg0,Fascia old, Fascia neww) {
			
				try {
					Utente utente = UtenteCorrente.getUtente();
					List<Integer> lista=null;
					if(utente instanceof Amministratore)
					caricaTabella((List<T>)presenter.processRequest("getAllAutoByFascia",neww.getIDFascia()), tbAuto);
					else if(utente instanceof SupervisoreAgenzia){
						List<Autoveicolo> autoveicoli  = new ArrayList<Autoveicolo>();
						List<Sede> sedi = (List<Sede>)presenter.processRequest("getAllSediByAgenzia",((SupervisoreAgenzia) utente).getIDAgenzia());
						for(Sede s:sedi){
							lista=new ArrayList<Integer>();
							lista.add(s.getIDSede());
							lista.add(neww.getIDFascia());
							List<Autoveicolo> auto= (List<Autoveicolo>) presenter.processRequest("getAllAutoBySedeAndFascia",lista);
							autoveicoli.addAll(auto);
						}
						caricaTabella((List<T>)autoveicoli, tbAuto);
					}
					else{ //Supervisore sede
						lista=new ArrayList<Integer>();
						lista.add(((SupervisoreSede)utente).getIDSede());
						lista.add(neww.getIDFascia());
						tbAuto.getItems().clear();
						caricaTabella((List<T>)presenter.processRequest("getAllAutoBySedeAndFascia",lista), tbAuto);
					}
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalArgumentException
						| InvocationTargetException | CommonException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
		}
		
	}
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		
		panes= tabPane.getTabs();
		//serve solo per fargli fare il binding con le colonne
		tbContrattoController = new TabContratto((TableView<Contratto>) tbContratto,SchermataGenerale.this);
		//carica la prima volta la tabella 
		try {
			List<Contratto> contratti = (List<Contratto>)presenter.processRequest("getAllContratti",null);
			caricaTabella((List<T>) contratti,tbContratto);
		
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabPane.getSelectionModel().selectedItemProperty().addListener( new TabChangeListener<Tab>());
		tbContratto.getSelectionModel().selectedItemProperty().addListener( (ChangeListener<? super T>) new ItemSelectedContratto());
		tbNoleggio.getSelectionModel().selectedItemProperty().addListener( (ChangeListener<? super T>) new ItemSelectedNoleggio());
		tbAuto.getSelectionModel().selectedItemProperty().addListener((ChangeListener<? super T>) new ItemSelectedAutoveicolo());
		tbAgenzia.getSelectionModel().selectedItemProperty().addListener((ChangeListener<? super T>) new ItemSelectedAgenzia());
		tbSede.getSelectionModel().selectedItemProperty().addListener( new ItemSelectedSede());
		
		//setta la schermata per l'utente corrente
		settaSchermataPerUtente();
	}	

}
