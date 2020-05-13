package pcSaver;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.*;
import sample.Package;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CsvFiles {
    public static void save(Package packList, ArrayList<Part> partList) throws IOException {
        String formatted = PCFormatter.formatPackages(packList)+PCFormatter.formatParts(partList)+"\n";
        try{
            Path PartPath = Paths.get("Semesteroppgave\\src\\sample\\Package.csv");
            FileWriter fw = new FileWriter(String.valueOf(PartPath), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(formatted);
            bw.close();
            //Sørger for at csv-filen ikke blir overskrevet og at nye pakker blir lagret nederst i filen
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setContentText("Noe gikk galt: "+e.getMessage());
            alert.showAndWait();
            System.out.println("Noe gikk galt: "+e.getMessage());
        }
    }//Metode som lagrer kjøpte pakker til CSV-fil, og gir feilmelding hvis noe går galt med lagringen
}
