package sample;

import exceptions.InvalidSelectedRemoval;

public class removeSelected {

    //Metode som fjerner en del fra handlevogn og reduserer telleren for den typen det gjelder
    public static void remove(String type) throws InvalidSelectedRemoval{
        if(type.equals("CPU")){
            checkConfiguration.counter_CPU--;
        }else if(type.matches("GPU")){
            checkConfiguration.counter_GPU--;
        }else if(type.matches("Memory")){
            checkConfiguration.counter_RAM--;
        }else if(type.matches("HDD")){
            checkConfiguration.counter_HDD--;
        }else if(type.matches("Monitor")){
            checkConfiguration.counter_Mon--;
        }else if(type.matches("Mouse")){
            checkConfiguration.counter_Mou--;
        }else if(type.matches("Keyboard")){
            checkConfiguration.counter_KB--;
        }else if(type.matches("Cabinet")){
            checkConfiguration.counter_Cab--;
        }
        else{
            throw new InvalidSelectedRemoval("Du må velge en del");
        }
    }
}
