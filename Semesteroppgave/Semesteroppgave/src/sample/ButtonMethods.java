package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import pcSaver.CsvFiles;
import java.io.IOException;
import java.util.ArrayList;

public class ButtonMethods {

    //Metode som kalkulerer totalprisen for en pakke, etterhvert som handlevognen fylles opp
    public static void calculatePrice(TableView<Part> tv, TableColumn<Part, Double> tvClmnPrice, Label lblSum){
        double sum = 0;

        for(Part price : tv.getItems()){
            double pris = tvClmnPrice.getCellObservableValue(price).getValue();
            sum+=pris;
        }
        lblSum.setText(String.valueOf(sum));
    }

    //Metode som oppretter en pakke når brukeren trykker på kjøp
    public static void createPackage(Label lblSum,PartRegister newPartRegister,TableView tblPCdel,
                                     PackageRegister newPackageRegister,TableView tblPackage) throws IOException, ClassNotFoundException {
        double price = Double.parseDouble(lblSum.getText());

        ArrayList<String> array = new ArrayList<>();
        ArrayList<Part> parts = new ArrayList<>();
        for(Part p : newPartRegister.getArray()){
            array.add(p.getDelNavn());
            parts.add(p);
        }//For-løkke som løper gjennom et array og legger til delene i et nytt array

        String packageNumber = String.valueOf(generateOrderNumber(newPackageRegister));

        Package newPackage = new Package(packageNumber,price);
        newPackage.setPackagePrice(price);
        CsvFiles.save(newPackage,parts);
        //Oppretter et Package-objekt og angir pakkenavn samt pakkepris

        tblPCdel.setItems(newPartRegister.getArray());//Tableview får inn delene som blir lagt i handlevogn
        newPackageRegister.array = newPackageRegister.loadPackages(newPackageRegister.Path);//Array til pakkene henter inn tidligere kjøp fra binær fil
        newPackageRegister.registrerPackage(packageNumber,price);//Legger inn nye pakker i pakke-arrayet
        newPackageRegister.savePackages(newPackageRegister.array,newPackageRegister.Path);//Lagrer den nye pakken til binær fil
        tblPackage.setItems(newPackageRegister.getArray());//Tableview for tidligere kjøp henter inn alle pakker fra arrayet

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kjøpt");
        alert.setHeaderText("Takk for handelen!");
        alert.setContentText("Produktet er kjøpt.");
        alert.showAndWait();
        //Melding som viser at pakken er kjøpt
    }

    //Metode som generer et tilfeldig ordrenummer for et kjøp.
    //Hvis ordrenummeret allerede eksisterer vil det bli generert et nytt
    public static int generateOrderNumber(PackageRegister packageRegister){
        int orderNumber;
        orderNumber = (int)((Math.random()*900000)+1000000);
        if(!packageRegister.checkOrderNumber(String.valueOf(orderNumber))){
            generateOrderNumber(packageRegister);
        }
        return orderNumber;
    }


    //Knapp for å legge til en ny del som lagrer til en binær fil for så å bli lastet inn til hovedsiden når den blir åpnet
    public static void leggTilDel(ComboBox comboType, TextField txtNyDelnavn, TextField txtNyPris, newPartRegister newPart, TableView tblNyDel) throws IOException, ClassNotFoundException {
        Part nyDel = new Part((String) comboType.getValue(), txtNyDelnavn.getText(), Double.parseDouble(txtNyPris.getText()));
        if (!newPart.checkName(nyDel)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Feil!");
            alert.setContentText("To deler kan ikke ha samme delnavn");
            alert.showAndWait();
        } else if (txtNyDelnavn.getText() == "" || txtNyDelnavn.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setContentText("Du har ikke lagt til navn på den nye delen");
            alert.showAndWait();
        } else if (txtNyPris.getText().isEmpty() || Double.parseDouble(txtNyPris.getText()) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setContentText("Prisen må være et tall større enn 0");
            alert.showAndWait();
        } else if (comboType.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setContentText("Du må velge en av komponenttypene.");
            alert.showAndWait();
        } else {
            newPart.array = newPart.loadNewParts(newPart.Path);
            newPart.attachTableView(tblNyDel);
            newPart.registrerPCDel(nyDel);
            newPart.saveNewParts(newPart.array, newPart.Path);
            newPart.verifySave();
        }
    }

    //Metode for å fjerne en del fra handlevognen
    static void removeNewPart(TableView tblNyDel, newPartRegister newPart, String type, String delnavn, double delpris, ActionEvent event) throws IOException, ClassNotFoundException {
        ObservableList<Part> newPartChosen, allNewPartsChosen;
        allNewPartsChosen = tblNyDel.getItems();
        newPartChosen = tblNyDel.getSelectionModel().getSelectedItems();
        allNewPartsChosen.removeAll(newPartChosen);
        Part removePart = new Part(type,delnavn,delpris);
        newPart.array.remove(removePart);
        newPart.saveNewParts(newPart.array,newPart.Path);
    }

    //Metode for å legge til en del i handlevognen
    static void addToCart(PartRegister newPartRegister, String type, String navn, double pris, TableView tblPCdel, TableColumn tblPris, Label lblSum, ActionEvent event) throws InterruptedException {
        Part nyPart = new Part(type, navn, pris);
        nyPart.setType(type);
        nyPart.setDelNavn(navn);
        nyPart.setDelPris(pris);

        newPartRegister.registrerPCDel(type, navn, pris);
        tblPCdel.setItems(newPartRegister.getArray());
        ButtonMethods.calculatePrice(tblPCdel,tblPris,lblSum);
    }
}
