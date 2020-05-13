package sample;

import exceptions.InvalidPartTypeException;

import javax.swing.table.TableColumn;

public class CheckPartType {
    //Metode for å sjekke om typen til den angitte delen er riktig
    public static String checkTypeCB(String part) {
        if (part.matches("CPU")) {
            return part;
        } else if (part.matches("GPU")) {
            return part;
        } else if (part.matches("Memory")) {
            return part;
        } else if (part.matches("HDD")) {
            return part;
        } else if (part.matches("Monitor")) {
            return part;
        } else if (part.matches("Mouse")) {
            return part;
        } else if (part.matches("Keyboard")) {
            return part;
        } else if (part.matches("Cabinet")) {
            return part;
        } else {
            throw new InvalidPartTypeException("Ikke tilgjengelig del");
        }
    }
}
