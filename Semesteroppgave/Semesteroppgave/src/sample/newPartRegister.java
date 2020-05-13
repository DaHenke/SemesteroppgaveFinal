package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class newPartRegister {
    public ObservableList<Part> array = FXCollections.observableArrayList();
    public static Path Path = Paths.get("Semesteroppgave\\src\\sample\\newParts");//Setter en standard filsti for lagring og henting av data

    public void attachTableView(TableView tv){
        tv.setItems(array);
    }//Fester array til TableView

    public ObservableList<Part> getArray(){
        return this.array;
    } //Henter data fra et array

    public void registrerPCDel(Part enDel){
        array.add(enDel);
    }//Legger til en del i arrayet

    //Metode som lagrer data fra array til binær fil
    public void saveNewParts(ObservableList<Part> array, Path path) throws IOException {
        OutputStream fis = Files.newOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fis);

        ArrayList<Part> newarray = new ArrayList<>(array);
        try{
            oos.writeObject(newarray);
        }finally {
            oos.close();
        }
    }

    //Metode som laster data fra binær fil til array
    public static ObservableList loadNewParts(Path path) throws IOException, ClassNotFoundException {
        try {
            InputStream fis = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList arrayNewParts = (ArrayList) ois.readObject();
            ObservableList<Part> observableNewParts = FXCollections.observableArrayList(arrayNewParts);


            return observableNewParts;
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    //Laster data fra binær fil til TableView
    public void runDetails(TableView tv){
        try {
            array = loadNewParts(Path);
            tv.setItems(array);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Metode som gir en melding om at lagring var vellykket eller feilet
    void verifySave() throws IOException {
        if(!array.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Lagring vellykket!");
            alert.setHeaderText("Vellykket");
            alert.setContentText("Nye deler ligger inne i valgmenyene på hovedsiden");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lagring feilet!");
            alert.setHeaderText("Feil");
            alert.setContentText("Du har ikke lagt til nye deler");
            alert.showAndWait();
        }
    }

    //Metode som sjekker om et delnavn allerede ligger inne i arrayet
    public boolean checkName(Part partName) {
        for(Part p : array) {
            if (partName.getDelNavn().matches(p.getDelNavn())){
                return false;
            }
        }
        return true;
    }
}
