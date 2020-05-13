package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.util.ArrayList;
import static sample.ControllerSecondPage.newPart;

public class AddElementComboBox {

    //Metode som lar Comboboksene på hovedsiden hente data gjennom en for-løkke og sjekker "Type" er riktig
    //Avhengig av hvilken type det er blir det lagt inn i tilhørende comboboks
    public static void addNewParts(ComboBox cbCPU, ComboBox cbGPU, ComboBox cbRAM, ComboBox cbHDD, ComboBox cbMon, ComboBox cbMou, ComboBox cbKB, ComboBox cbCab) {
        try {
            ObservableList<Part> nyParts = newPartRegister.loadNewParts(newPart.Path);
            ArrayList<Part> arrayParts = new ArrayList<>(nyParts);
            for (Part p : arrayParts) {
                if (CheckPartType.checkTypeCB(p.Type).matches("CPU")) {
                    cbCPU.getItems().add(p);
                } else if (CheckPartType.checkTypeCB(p.Type).matches("GPU")) {
                    cbGPU.getItems().add(p);
                } else if (CheckPartType.checkTypeCB(p.Type).matches("Memory")) {
                    cbRAM.getItems().add(p);
                } else if (CheckPartType.checkTypeCB(p.Type).matches("HDD")) {
                    cbHDD.getItems().add(p);
                } else if (CheckPartType.checkTypeCB(p.Type).matches("Monitor")) {
                    cbMon.getItems().add(p);
                } else if (CheckPartType.checkTypeCB(p.Type).matches("Mouse")) {
                    cbMou.getItems().add(p);
                } else if (CheckPartType.checkTypeCB(p.Type).matches("Keyboard")) {
                    cbKB.getItems().add(p);
                } else if (CheckPartType.checkTypeCB(p.Type).matches("Cabinet")) {
                    cbCab.getItems().add(p);
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
