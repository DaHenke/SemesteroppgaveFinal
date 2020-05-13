package sample;

import exceptions.InvalidPartTypeException;

import java.io.Serializable;


public class Part implements Serializable {
        public String Type,DelNavn;
        public double DelPris;

    public Part(String Type, String DelNavn, double DelPris){
        this.Type = Type;
        this.DelNavn = DelNavn;
        this.DelPris = DelPris;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        if(type.matches("CPU")){
            Type = type;
        }else if(type.matches("GPU")){
            Type = type;
        }else if(type.matches("Memory")){
            Type = type;
        }else if(type.matches("HDD")){
            Type = type;
        }else if(type.matches("Monitor")){
            Type = type;
        }else if(type.matches("Mouse")){
            Type = type;
        } else if(type.matches("Keyboard")){
            Type = type;
        }else if(type.matches("Cabinet")){
            Type = type;
        }else {
            throw new InvalidPartTypeException("Vennligst angi en gyldig komponenttype");
        }
    }

    public String getDelNavn() {
        return DelNavn;
    }

    public void setDelNavn(String delNavn) {
        DelNavn = delNavn;
    }

    public double getDelPris() {
        return DelPris;
    }

    public void setDelPris(double delPris) {
        DelPris = delPris;
    }

    @Override
    public String toString() {
        return DelNavn+"\n"+DelPris+" kr\n";
    }
}
