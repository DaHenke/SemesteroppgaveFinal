package exceptions;

import javafx.scene.control.Alert;

public class InvalidPartTypeException extends IllegalArgumentException{
    public InvalidPartTypeException(String msg){
        super(msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Feil!");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
