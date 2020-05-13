package sample;

import exceptions.InvalidSelectedRemoval;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainPage implements Initializable {

    PartRegister newPartRegister = new PartRegister();
    PackageRegister newPackageRegister = new PackageRegister();

    @FXML
    public TableView<Part> tblPCdel;

    @FXML
    private TableColumn<Part, Double> tblPris;

    @FXML
    private TableView<Package> tblPackage;

    @FXML
    public ComboBox<Part> comboCPU;

    @FXML
    public ComboBox<Part> comboGPU;

    @FXML
    public ComboBox<Part> comboRAM;

    @FXML
    public ComboBox<Part> comboHDDSSDPC;

    @FXML
    public ComboBox<Part> comboMonitor;

    @FXML
    public ComboBox<Part> comboKeyboard;

    @FXML
    public ComboBox<Part> comboMouse;

    @FXML
    public ComboBox<Part> comboCabinett;

    @FXML
    private Label lblSum;


    //Oppretter scenen for superbruker hvis han/hun ønsker å logge inn
    @FXML
    void openAdmin(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent loginPageParent = loader.load(getClass().getResource("logIn.fxml"));
            Scene loginPageScene = new Scene(loginPageParent);

            Stage loginWindow = (Stage)((Node) event.getSource()).getScene().getWindow();

            loginWindow.setTitle("Logg inn som administrator");
            loginWindow.setScene(loginPageScene);
            loginWindow.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //Kjører nødvendige metoder når en PC blir forsøkt kjøpt
    @FXML
    void buyPC(ActionEvent event) throws IOException, ClassNotFoundException {
        ButtonMethods.calculatePrice(tblPCdel,tblPris,lblSum);
        checkConfiguration.checkAll();
        ButtonMethods.createPackage(lblSum,newPartRegister,tblPCdel,newPackageRegister,tblPackage);
        checkConfiguration.clear(comboCPU,comboGPU,comboRAM,comboHDDSSDPC,comboMonitor,comboMouse,comboKeyboard,comboCabinett,
                            tblPCdel,lblSum);
    }

    //Knapp for å legge inn CPU-komponenter i handelvognen, og kjører nødvendige metoder
    @FXML
    void regCPU(ActionEvent event) throws InterruptedException {
        checkConfiguration.checkCPU();
        String type = "CPU";
        String CPUnavn = comboCPU.getValue().DelNavn;
        double CPUpris = comboCPU.getValue().DelPris;
        ButtonMethods.addToCart(newPartRegister,type,CPUnavn,CPUpris,tblPCdel,tblPris,lblSum,event);
    }

    //Knapp for å legge inn GPU-komponenter i handelvognen, og kjører nødvendige metoder
    @FXML
    void regGPU(ActionEvent event) throws InterruptedException {
        checkConfiguration.checkGPU();
        String type = "GPU";
        String GPUnavn = comboGPU.getValue().DelNavn;
        double GPUpris = comboGPU.getValue().DelPris;
        ButtonMethods.addToCart(newPartRegister,type,GPUnavn,GPUpris,tblPCdel,tblPris,lblSum,event);
    }

    //Knapp for å legge inn RAM-komponenter i handelvognen, og kjører nødvendige metoder
    @FXML
    void regRAM(ActionEvent event) throws InterruptedException {
        checkConfiguration.checkRAM();
        String type = "Memory";
        String RAMnavn = comboRAM.getValue().DelNavn;
        double RAMpris = comboRAM.getValue().DelPris;
        ButtonMethods.addToCart(newPartRegister,type,RAMnavn,RAMpris,tblPCdel,tblPris,lblSum,event);
    }

    //Knapp for å legge inn HDD-/SSD-komponenter i handelvognen, og kjører nødvendige metoder
    @FXML
    void regHDD(ActionEvent event) throws InterruptedException {
        checkConfiguration.checkHDD();
        String type = "HDD";
        String HDDnavn = comboHDDSSDPC.getValue().DelNavn;
        double HDDpris = comboHDDSSDPC.getValue().DelPris;
        ButtonMethods.addToCart(newPartRegister,type,HDDnavn,HDDpris,tblPCdel,tblPris,lblSum,event);

    }

    //Knapp for å legge inn tastatur i handelvognen, og kjører nødvendige metoder
    @FXML
    void regKeyboard(ActionEvent event) throws InterruptedException {
        checkConfiguration.checkKeyboard();
        String type = "Keyboard";
        String keyboardNavn = comboKeyboard.getValue().DelNavn;
        double keyboardPris = comboKeyboard.getValue().DelPris;
        ButtonMethods.addToCart(newPartRegister,type,keyboardNavn,keyboardPris,tblPCdel,tblPris,lblSum,event);
    }

    //Knapp for å legge inn skjermer i handelvognen, og kjører nødvendige metoder
    @FXML
    void regMonitor(ActionEvent event) throws InterruptedException {
        checkConfiguration.checkMonitor();
        String type = "Monitor";
        String monitorNavn = comboMonitor.getValue().DelNavn;
        double monitorPris = comboMonitor.getValue().DelPris;
        ButtonMethods.addToCart(newPartRegister,type,monitorNavn,monitorPris,tblPCdel,tblPris,lblSum,event);
    }

    //Knapp for å legge inn mus i handelvognen, og kjører nødvendige metoder
    @FXML
    void regMouse(ActionEvent event) throws InterruptedException {
        checkConfiguration.checkMouse();
        String type = "Mouse";
        String mouseNavn = comboMouse.getValue().DelNavn;
        double mousePris = comboMouse.getValue().DelPris;
        ButtonMethods.addToCart(newPartRegister,type,mouseNavn,mousePris,tblPCdel,tblPris,lblSum,event);
    }

    //Knapp for å legge inn kabinett i handelvognen, og kjører nødvendige metoder
    @FXML
    void regCabinett(ActionEvent event) throws InterruptedException {
        checkConfiguration.checkCabinet();
        String type = "Cabinet";
        String cabinetName = comboCabinett.getValue().DelNavn;
        double cabinetPrice = comboCabinett.getValue().DelPris;
        ButtonMethods.addToCart(newPartRegister,type,cabinetName,cabinetPrice,tblPCdel,tblPris,lblSum,event);
    }

    //Knapp som fjerner valgte komponenter fra handlevognen, og kjører nødvendige metoder
    @FXML
    void btnRemoveGPU(ActionEvent event) throws InvalidSelectedRemoval {
        ObservableList<Part> chosenPart, allParts;
        allParts = tblPCdel.getItems();
        chosenPart = tblPCdel.getSelectionModel().getSelectedItems();
        String type = String.valueOf((tblPCdel.getSelectionModel().getSelectedItem().getType()));
        removeSelected.remove(type);
        allParts.removeAll(chosenPart);
        ButtonMethods.calculatePrice(tblPCdel,tblPris,lblSum);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        newPartRegister.attachTableView(tblPCdel); //Fester array til TableView
        newPackageRegister.attachTableView(tblPackage); //Fester array til TableView
        newPackageRegister.runPackages(tblPackage); //Leser inn tidligere kjøpte pakker fra binær fil
        AddElementComboBox.addNewParts(comboCPU,comboGPU,comboRAM,comboHDDSSDPC,comboMonitor,comboMouse,comboKeyboard,comboCabinett);//Legger til deler i Comboboksene fra binær fil
    }
}