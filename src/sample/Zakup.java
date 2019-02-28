package sample;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;

public class Zakup {

    private SimpleStringProperty nazwa, kategoria,osoba;
    private Double cena;
    private LocalDate data;



    public Zakup(String nazwa, Double cena, LocalDate data) {
        this.nazwa = new SimpleStringProperty(nazwa);
        this.cena = (cena);
        this.data = data;
        this.kategoria = new SimpleStringProperty("Ogólne");
    }

    public Zakup(String nazwa, Double cena ) {
        this.nazwa = new SimpleStringProperty(nazwa);
        this.cena = (cena);
    }

    public Zakup(String nazwa,String kategoria, Double cena, LocalDate data, String osoba) {
        this(nazwa,cena,data);
        this.kategoria = new SimpleStringProperty(kategoria);
        this.osoba = new SimpleStringProperty(osoba);

    }

    @Override
    public String toString()
    {
        return String.format("Zakup : %-10s , cena : %-8.2f , w dniu : %-8s",nazwa,cena,data);
    }


//########################## Gettery

    public String getNazwa() {
        return nazwa.get();
    }


    public String getKategoria() {
        return kategoria.get();
    }


    public String getOsoba() {
        return osoba.get();
    }


    public Double getCena() {
        return cena;
    }

    public LocalDate getData() {
        return data;
    }

    //######################## Settery

    public void setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
    }

    public void setKategoria(String kategoria) {
        this.kategoria.set(kategoria);
    }

    public void setOsoba(String osoba) {
        this.osoba.set(osoba);
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }



}
