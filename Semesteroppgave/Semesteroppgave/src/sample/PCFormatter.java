package sample;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import javafx.collections.ObservableList;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.List;

public class PCFormatter {
    public static String DELIMETER = ";";

    public static String formatPackage(Package part){
        return part.getPackageNumber()+DELIMETER+""+DELIMETER+part.getPackagePrice();
    } //Formatterer pakker som er kjøpt, med en DELIMETER mellom hvert verdi

    public static String formatPackageTitle(){
        return "Ordrenummer"+DELIMETER+""+DELIMETER+"Totalpris:"+"\n";
    }
    //Skriver tittel for pakkene

    public static String formatPartTitle(){
        return "Type"+DELIMETER+"Delnavn"+DELIMETER+"Delpris:"+"\n";
    }
    //Skriver tittel for delene

    public static String formatPart(Part part){
        return part.getType()+DELIMETER+part.getDelNavn()+DELIMETER+part.getDelPris();
    }
    //Formaterer deler i hver enkelt pakke, med en DELIMETER

    public static String formatPackages(Package packList){
        StringBuffer str = new StringBuffer();
        str.append(formatPackageTitle());
        Package pack = packList;
        str.append(formatPackage(pack));
        str.append("\n");


        return str.toString();
    }//Metode som formatterer hver linje med data til en angitt fil

    public static String formatParts(ArrayList<Part> partList){
        StringBuffer str = new StringBuffer();
        str.append(formatPartTitle());
        for(Part part : partList){
            str.append(formatPart(part));
            str.append("\n");
        }
        return str.toString();
    }
}

