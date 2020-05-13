package sample;

import exceptions.InvalidPartTypeException;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

public class EditMethods {

    //Lar superbruker endre verdier og gir eventuelle feilmeldinger hvis data ikke er gyldig
    static void txtDataEditedDelnavn(TableView tblNyDel,newPartRegister newPart,TableColumn.CellEditEvent<Part, String> event) {
        Part part = (Part) tblNyDel.getSelectionModel().getSelectedItem();
        try{
            if(!newPart.checkName(part)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Feil!");
                alert.setHeaderText("Feil!");
                alert.setContentText("To deler kan ikke ha samme delnavn");
                alert.showAndWait();
                part.setDelNavn(event.getOldValue());
            }else{
                part.setDelNavn(event.getNewValue());
                newPart.saveNewParts(newPart.array,newPart.Path);
            }
        }catch (InvalidPartTypeException | IOException e){
            System.out.println("Not a valid part name");
        }
        tblNyDel.refresh();
    }

    //Lar superbruker endre verdier og gir eventuelle feilmeldinger hvis data ikke er gyldig
    static void txtDataEditedDelType(TableView tblNyDel,newPartRegister newPart,TableColumn.CellEditEvent<Part, String> event) {
        Part part = (Part) tblNyDel.getSelectionModel().getSelectedItem();
        try{
            part.setType(event.getNewValue());
            newPart.saveNewParts(newPart.array,newPart.Path);
        }catch (InvalidPartTypeException | IOException e){
            System.out.println("Not a valid part type");
        }
        tblNyDel.refresh();
    }

    //Lar superbruker endre verdier og gir eventuelle feilmeldinger hvis data ikke er gyldig
    static void txtDataEditedDelPris(TableView tblNyDel,newPartRegister newPart,TableColumn.CellEditEvent<Part, Double> event) {
        Part part = (Part) tblNyDel.getSelectionModel().getSelectedItem();
        try{
            if (event.getNewValue()<=0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Feil!");
                alert.setContentText("Prisen må være et tall større enn 0");
                alert.showAndWait();
                part.setDelPris(event.getOldValue());
            }else {
                part.setDelPris(event.getNewValue());
                newPart.saveNewParts(newPart.array, newPart.Path);
            }
        }catch (InvalidPartTypeException | IOException e){
            System.out.println("Not a valid price");
        }
        tblNyDel.refresh();
    }
}
