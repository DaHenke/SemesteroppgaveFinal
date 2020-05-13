package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class PartRegister {

        public static ObservableList<Part> array = FXCollections.observableArrayList();

        public void attachTableView(TableView tv){
            tv.setItems(array);
        }//Fester array til et TableView

        public ObservableList<Part> getArray(){
            return this.array;
        }//Henter data fra et array

        public void registrerPCDel(String type, String delNavn, double delPris){
            Part enDel = new Part(type,delNavn,delPris);
            array.add(enDel);
        }//Legger til en del i arrayet

    @Override
    public String toString() {
        return String.valueOf(super.toString());
    }
}
