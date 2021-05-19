package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private HBox layoutTentativo;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private TextField txtTentativi;

    @FXML
    private TextField txtTentativoUtente;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	 txtRisultato.setText("");
         this.segreto = (int) (Math.random()*NMAX)+1;
         tentativiFatti = 0;
         inGioco = true;
         
         this.txtTentativi.setText(Integer.toString(TMAX));
         this.layoutTentativo.setDisable(false);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	
    	String ts = txtTentativoUtente.getText();
    	int tentativo;
    	
    	try {
    	      tentativo = Integer.parseInt(ts);
    	      if(tentativo > 100 || tentativo <= 0) {
    	    	  txtRisultato.appendText("ERRORE: Il numero deve essere compreso tra 1 e 100");
    	    	  return ;
    	      }
    	     }catch(NumberFormatException e) {
    	    	 txtRisultato.appendText("ERRORE: Devi inserire un numero");
    	    	 return ;
    	     }
    	
    	txtTentativoUtente.setText("");
    	this.tentativiFatti++;
    	this.txtTentativi.setText(Integer.toString(TMAX-this.tentativiFatti));
    	
    	if(tentativo == this.segreto) {
    		txtRisultato.setText("HAI VINTO CON "+this.tentativiFatti+" TENTATIVI");
    		this.inGioco = false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	
    	if(this.tentativiFatti == TMAX) {
    		txtRisultato.setText("HAI PERSO. IL NUMERO ERA: "+segreto);
    		this.inGioco = false;
    		return;
    	}
    	
    	if(tentativo < this.segreto) {
    		txtRisultato.setText("TENTATIVO TROPPO BASSO");
    	}
    	else {
    		txtRisultato.setText("TENTATIVO TROPPO ALTO");
    	}
    }

    @FXML
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
