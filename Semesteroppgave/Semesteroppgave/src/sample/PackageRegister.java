package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PackageRegister{
    static Path Path = Paths.get("Semesteroppgave\\src\\sample\\Package");
    public ObservableList<Package> array = FXCollections.observableArrayList();

    public void attachTableView(TableView tv){
        tv.setItems(array);
    }//Fester array til TableView

    public ObservableList<Package> getArray(){
        return this.array;
    }

    public void registrerPackage(String packageNumber, double packagePrice){
        Package enPackage = new Package(packageNumber,packagePrice);
        array.add(enPackage);
    }//Legger til en pakke i arrayet

    //Metode som lagrer data fra array til binær fil
    public void savePackages(ObservableList<Package> array, Path Path) throws IOException {
        OutputStream fis = Files.newOutputStream(Path);
        ObjectOutputStream oos = new ObjectOutputStream(fis);

        ArrayList<Package> newarray = new ArrayList<>(array);
        oos.writeObject(newarray);
    }

    //Metode som laster data fra binær fil til array
    public ObservableList<Package> loadPackages(Path Path) throws IOException, ClassNotFoundException {
        try {
            InputStream fis = Files.newInputStream(Path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Package> arrayPackages = (ArrayList<Package>) ois.readObject();

            ObservableList<Package> observablePackages = FXCollections.observableArrayList(arrayPackages);

            return observablePackages;
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    //Laster data fra binær fil til TableView
    public void runPackages(TableView tv){
        try {
            array = loadPackages(Path);
            tv.setItems(array);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Sjekker om ordrenummeret eksisterer i arrayet
    public boolean checkOrderNumber(String packageNumber){
        for(Package number : array){
            if(number.getPackageNumber().matches(packageNumber)) {
                return false;
            }
        }
        return true;
    }
}
