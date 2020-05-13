package exceptions;

import javafx.scene.control.Alert;

public class InvalidUserFormatException extends IllegalArgumentException {
    public InvalidUserFormatException(String msg){
        super(msg);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Something went wrong...");
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
