package sample;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SearchFilter {
    public static void searchNewParts(newPartRegister newPartRegister, TextField txtSearchField, ComboBox comboSearchColumn, TableView tblPCdel) {
        FilteredList<Part> searchedParts = new FilteredList<>(newPartRegister.array, part -> true);//Oppretter en filtrert liste
        //Setter et søkeord som oppdaterer den filtrerte listen
        txtSearchField.textProperty().addListener((observable,emptyData,searchData) -> {
            searchedParts.setPredicate(part -> {
                if(searchData == null || searchData.isEmpty()){
                    return true;//Hvis det ikke er søkt på noe vil hele listen bli vist
                }
                String smallLetter = searchData.toLowerCase(); //Lar filteret bruke både små og store bokstaver
                Object value = comboSearchColumn.getValue();
                if ("Type".equals(value)) {//Hvis Type er valgt i comboksen kan man filtrere basert på type
                    return part.getType().toLowerCase().contains(smallLetter);
                } else if ("Del".equals(value)) {//Hvis Del er valgt i comboboksen kan man filtrere basert på navnet
                    return part.getDelNavn().toLowerCase().contains(smallLetter);
                } else if ("Pris".equals(value)) {//Hvis Pris er valgt i comboboksen kan man filtrere basert på prisen
                    return Double.toString(part.getDelPris()).toLowerCase().contains(smallLetter);
                }
                return false;
            });
            SortedList<Part> sortedParts = new SortedList<>(searchedParts); //Setter den filtrerte listen til en sortert liste
            tblPCdel.setItems(sortedParts);//Legger arrayet til TableView
            if(txtSearchField.getText().isEmpty()){
                tblPCdel.setItems(newPartRegister.getArray());
            }//Hvis søkefeltet er tomt vil den originale listen bli vist
        });
    }
    //Koden er delvis hentet fra https://www.youtube.com/watch?v=_agQU9BcvZc av java-tech tutorials
}
