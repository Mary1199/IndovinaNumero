package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model;

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
    	
    	 this.model.nuovaPartita();
    	 txtRisultato.setText("");
         this.txtTentativi.setText(Integer.toString(this.model.getTMAX()));
         this.layoutTentativo.setDisable(false);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	
    	String ts = txtTentativoUtente.getText();
    	int tentativo;
    	
    	try {
    	      tentativo = Integer.parseInt(ts);
    	     }catch(NumberFormatException e) {
    	    	 txtRisultato.setText("ERRORE: Devi inserire un numero\n");
    	    	 return ;
    	     }
    	
    	this.txtTentativoUtente.setText("");
    	
    	
    	int result;
    	try {
    	result= this.model.tentativo(tentativo);
    	}catch(IllegalStateException se) {
    		this.txtRisultato.setText(se.getMessage());
    		this.layoutTentativo.setDisable(true);
    		return;
    	}catch(InvalidParameterException pe) {
    		this.txtRisultato.setText(pe.getMessage());
    		return;
    	}
    	
    	this.txtTentativi.setText(Integer.toString(model.getTMAX()-this.model.getTentativiFatti()));
    	
    	if(result == 0) {
    		txtRisultato.setText("HAI VINTO CON "+this.model.getTentativiFatti()+" TENTATIVI!\nIl numero segreto era proprio "+ this.model.getSegreto());
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	
    	if(result < 0) {
    		txtRisultato.setText("TENTATIVO TROPPO BASSO\n");
    	}
    	else {
    		txtRisultato.setText("TENTATIVO TROPPO ALTO\n");
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
    
    public void setModel (Model model) {
    	this.model = model; 
    }
}
