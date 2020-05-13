package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class ControllerLogIn {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Label lblStatus;


    //Oppretter en ny scene der superbruker kan legge til egne deler
    //Sjekker om angitt brukernavn og passord er riktig, og stopper programmet i fire sekunder for at data på neste side skal bli hentet inn
    @FXML
    private void logIn(ActionEvent event) throws Exception{
        try {
        if(txtUsername.getText().equals("admin") && txtPassword.getText().equals("12345")){

            //lblStatus.setText("");
            Thread.sleep(4000);

            FXMLLoader loader = new FXMLLoader();
            Parent loginPageParent = loader.load(getClass().getResource("secondPage.fxml"));

            Scene loginPageScene = new Scene(loginPageParent);

            Stage loginWindow = (Stage)((Node) event.getSource()).getScene().getWindow();

            loginWindow.setTitle("Legg inn nye deler");
            loginWindow.setScene(loginPageScene);
            loginWindow.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Huff da");
            alert.setContentText("Feil brukernavn/passord...");
            alert.showAndWait();
            //lblStatus.setText("Login feilet");
        }

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    //Oppretter ny scene når superbruker vil tilbake til hovedsiden
    @FXML
    void backToMainPage(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            Parent loginPageParent = loader.load(getClass().getResource("mainPage.fxml"));
            Scene loginPageScene = new Scene(loginPageParent);

            Stage loginWindow = (Stage)((Node) event.getSource()).getScene().getWindow();

            loginWindow.setTitle("Velkommen");
            loginWindow.setScene(loginPageScene);
            loginWindow.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}