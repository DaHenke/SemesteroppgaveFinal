package sample;

import exceptions.InvalidPCConfigurationException;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class checkConfiguration {

    static int counter_CPU = 0;
    static int counter_GPU = 0;
    static int counter_RAM = 0;
    static int counter_HDD = 0;
    static int counter_KB = 0;
    static int counter_Mon = 0;
    static int counter_Mou = 0;
    static int counter_Cab = 0;

    //Teller opp hvis det er null CPU-komponenter i handlevogn
    //Kommer med feilmelding på at maks antall er nådd hvis flere enn en CPU-komponent er forsøkt valgt
    public static int checkCPU()throws InvalidPCConfigurationException {
        if(counter_CPU == 0){
            counter_CPU++;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare velge en CPU");
        }
        return counter_CPU;
    }

    //Teller opp hvis det er null eller en GPU-komponenter i handlevogn
    //Kommer med feilmelding på at maks antall er nådd hvis flere enn to GPU-komponenter er forsøkt valgt
    public static int checkGPU()throws InvalidPCConfigurationException{
        if(counter_GPU == 0 || counter_GPU == 1){
            counter_GPU++;
        }else if(counter_GPU == 2){
            throw new InvalidPCConfigurationException("Du kan kun velge opp til to GPUer");
        }
        return counter_GPU;
    }

    //Teller opp hvis det er null RAM-komponenter i handlevogn
    //Kommer med feilmelding på at maks antall er nådd hvis flere enn en RAM-komponent er forsøkt valgt
    public static int checkRAM()throws InvalidPCConfigurationException{
        if(counter_RAM == 0){
            counter_RAM++;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare velge en RAM");
        }
        return counter_RAM;

    }

    //Teller opp hvis det er null eller en HDD/SSD i handlevogn
    //Kommer med feilmelding på at maks antall er nådd hvis flere enn to HDDer/SSDer er forsøkt valgt
    public static int checkHDD()throws InvalidPCConfigurationException{
        if(counter_HDD == 0 || counter_HDD == 1){
            counter_HDD++;
        }else if(counter_HDD == 2){
            throw new InvalidPCConfigurationException("Du kan kun velge opp til to HDDer/SSDer");
        }
        return counter_HDD;

    }

    //Teller opp hvis det er null tastatur i handlevogn
    //Kommer med feilmelding på at maks antall er nådd hvis flere enn ett tastatur er forsøkt valgt
    public static int checkKeyboard()throws InvalidPCConfigurationException{
        if(counter_KB == 0){
            counter_KB++;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare velge ett keyboard");
        }
        return counter_KB;

    }

    //Teller opp hvis det er null skjermer i handlevogn
    //Kommer med feilmelding på at maks antall er nådd hvis flere enn en skjerm er forsøkt valgt
    public static int checkMonitor()throws InvalidPCConfigurationException{
        if(counter_Mon == 0){
            counter_Mon++;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare velge en skjerm");
        }
        return counter_Mon;
    }

    //Teller opp hvis det er null mus i handlevogn
    //Kommer med feilmelding på at maks antall er nådd hvis flere enn en mus er forsøkt valgt
    public static int checkMouse()throws InvalidPCConfigurationException{
        if(counter_Mou == 0){
            counter_Mou++;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare velge en PC-mus");
        }
        return counter_Mou;
    }

    //Teller opp hvis det er null kabinett i handlevogn
    //Kommer med feilmelding på at maks antall er nådd hvis flere enn ett kabinett er forsøkt valgt
    public static int checkCabinet()throws InvalidPCConfigurationException{
        if(counter_Cab == 0){
            counter_Cab++;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare velge ett kabinett");
        }
        return counter_Cab;
    }

    //Metode som sjekker at alle nødvendige deler for en pakke er valgt
    public static void checkPurchase()throws  InvalidPCConfigurationException{
        if(counter_CPU == 0 || counter_GPU == 0 || counter_RAM == 0 ||
                counter_HDD == 0 || counter_Cab == 0|| counter_Mon==0||counter_Mou==0||counter_KB==0){
            throw new InvalidPCConfigurationException("Mangler nødvendige deler");
        }
    }

    //Sjekker om det er flere enn en CPU-komponent i handlevognen
    public static int checkAmountCPU()throws InvalidPCConfigurationException{
        if(counter_CPU < 2){
            return counter_CPU;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare ha en CPU");
        }

    }

    //Sjekker om det er flere enn to GPU-komponenter i handlevognen
    public static int checkAmountGPU()throws InvalidPCConfigurationException {
        if(counter_GPU < 3) {
            return counter_GPU;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare ha opp til to GPUer");
        }
    }

    //Sjekker om det er flere enn en RAM-komponent i handlevognen
    public static int checkAmountRAM()throws InvalidPCConfigurationException {
        if(counter_RAM < 2) {
            return counter_RAM;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare ha en RAM-komponent");
        }
    }

    //Sjekker om det er flere enn to HDD-/SSD-komponenter i handlevognen
    public static int checkAmountHDD()throws InvalidPCConfigurationException {
        if(counter_HDD < 3) {
            return counter_HDD;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare ha opp til to HDDer");
        }
    }

    //Sjekker om det er flere enn en skjerm i handlevognen
    public static int checkAmountMon()throws InvalidPCConfigurationException {
        if(counter_Mon < 2) {
            return counter_Mon;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare ha en skjerm");
        }
    }

    //Sjekker om det er flere enn en mus i handlevognen
    public static int checkAmountMou()throws InvalidPCConfigurationException {
        if(counter_Mou < 2) {
            return counter_GPU;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare ha en mus");
        }
    }

    //Sjekker om det er flere enn ett tastatur i handlevognen
    public static int checkAmountKB()throws InvalidPCConfigurationException {
        if(counter_KB < 2) {
            return counter_KB;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare ha ett tastatur");
        }
    }
    //Sjekker om det er flere enn ett kabinett i handlevognen
    public static int checkAmountCab()throws InvalidPCConfigurationException {
        if(counter_Cab < 2) {
            return counter_Cab;
        }else{
            throw new InvalidPCConfigurationException("Du kan bare ha ett kabinett");
        }
    }

    //Metode som tømmer handlevognen, og resetter comboboksene og tellerne
    public static void clear(ComboBox comboCPU, ComboBox comboGPU, ComboBox comboRAM, ComboBox comboHDDSSDPC, ComboBox comboMonitor,
                             ComboBox comboMouse, ComboBox comboKeyboard, ComboBox comboCabinett, TableView tblPCdel, Label lblSum){
        comboCPU.getSelectionModel().clearSelection();
        comboGPU.getSelectionModel().clearSelection();
        comboHDDSSDPC.getSelectionModel().clearSelection();
        comboRAM.getSelectionModel().clearSelection();
        comboMonitor.getSelectionModel().clearSelection();
        comboMouse.getSelectionModel().clearSelection();
        comboKeyboard.getSelectionModel().clearSelection();
        comboCabinett.getSelectionModel().clearSelection();

        tblPCdel.getItems().clear();
        lblSum.setText("");

        counter_CPU=0;
        counter_GPU=0;
        counter_RAM=0;
        counter_HDD=0;
        counter_Mou=0;
        counter_Mon=0;
        counter_KB=0;
        counter_Cab=0;
    }

    //Metode som sørger for å sjekke alle delene i handlevognen
    public static void checkAll() throws InvalidPCConfigurationException{
        checkPurchase();
        checkAmountCPU();
        checkAmountGPU();
        checkAmountRAM();
        checkAmountHDD();
        checkAmountMon();
        checkAmountMou();
        checkAmountKB();
        checkAmountCab();
    }
}
