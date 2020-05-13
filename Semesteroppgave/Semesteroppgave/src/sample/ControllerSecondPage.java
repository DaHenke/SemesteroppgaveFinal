package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerSecondPage implements Initializable {

    static newPartRegister newPart = new newPartRegister();

    @FXML
    private ComboBox<String> comboType;

    @FXML
    private TextField txtNyDelnavn;

    @FXML
    private TextField txtNyPris;

    @FXML
    public TableView<Part> tblNyDel;

    @FXML
    private TableColumn<Part, String> tblNyType;

    @FXML
    private TableColumn<Part, String> tblNyDelnavn;

    @FXML
    private TableColumn<Part, Double> tblNyPris;

    @FXML
    private TextField txtSecondSearchField;

    @FXML
    private ComboBox<String> comboSearchColumn;

    //For hver gang man skriver et nytt tegn i søkefeltet vil tabellen oppdateres til å vise samsvarende verdier man har søkt på
    @FXML
    void searchNewParts(KeyEvent event) {
        SearchFilter.searchNewParts(newPart,txtSecondSearchField,comboSearchColumn,tblNyDel);
    }

    //Knapp for å gå tilbake til hovedsiden
    //Stopper programmet i fire sekunder for at data skal kunne bli lastet inn fra binær fil
    @FXML
    void backToMainPage(ActionEvent event) {
        try {
            Thread.sleep(4000);
            FXMLLoader loader = new FXMLLoader();
            Parent loginPageParent = loader.load(getClass().getResource("mainPage.fxml"));
            Scene loginPageScene = new Scene(loginPageParent);

            Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            loginWindow.setTitle("Welcome");
            loginWindow.setScene(loginPageScene);
            loginWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Knapp for å legge til en ny del som lagrer til en binær fil for så å bli lastet inn til hovedsiden når den blir åpnet
    @FXML
    void leggTilDel(ActionEvent event) throws IOException, ClassNotFoundException {
        ButtonMethods.leggTilDel(comboType,txtNyDelnavn,txtNyPris,newPart,tblNyDel);
        comboType.getSelectionModel().clearSelection();
        txtNyDelnavn.setText("");
        txtNyPris.setText("");
    }

    //Knapp for å fjerne en del fra listen og den binære filen med nødvendige metoder
    @FXML
    void removeNewPart(ActionEvent event) throws IOException, ClassNotFoundException {
        String type = tblNyDel.getSelectionModel().getSelectedItem().getType();
        String delnavn = tblNyDel.getSelectionModel().getSelectedItem().getDelNavn();
        double delpris = tblNyDel.getSelectionModel().getSelectedItem().getDelPris();
        ButtonMethods.removeNewPart(tblNyDel,newPart,type,delnavn,delpris,event);
    }

    //Lar superbruker endre verdier og gir eventuelle feilmeldinger hvis data ikke er gyldig
    @FXML
    void txtDataEditedDelPris(TableColumn.CellEditEvent<Part, Double> event) {
        EditMethods.txtDataEditedDelPris(tblNyDel,newPart,event);
    }

    //Lar superbruker endre verdier og gir eventuelle feilmeldinger hvis data ikke er gyldig
    @FXML
    void txtDataEditedDelType(TableColumn.CellEditEvent<Part, String> event) {
        EditMethods.txtDataEditedDelType(tblNyDel,newPart,event);
    }

    //Lar superbruker endre verdier og gir eventuelle feilmeldinger hvis data ikke er gyldig
    @FXML
    void txtDataEditedDelnavn(TableColumn.CellEditEvent<Part, String> event) {
        EditMethods.txtDataEditedDelnavn(tblNyDel,newPart,event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newPart.attachTableView(tblNyDel);//Fester array til TableView
        tblNyType.setCellFactory(TextFieldTableCell.forTableColumn());//Lar celler i TableView kunne bli endret
        tblNyDelnavn.setCellFactory(TextFieldTableCell.forTableColumn());//Lar celler i TableView kunne bli endret
        tblNyPris.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));//Lar celler i TableView kunne bli endret
        tblNyDel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);//Superbruker kan velge flere rader med data om gangen
        newPart.runDetails(tblNyDel);//Laster inn data fra binær fil til TableView
        comboType.getItems().addAll("CPU","GPU","Memory","HDD","Monitor","Mouse","Keyboard","Cabinet");//Legger til verdier i comboksen
        comboSearchColumn.getItems().addAll("Type","Del","Pris");//Legger til verdier i comboksen
    }
}
