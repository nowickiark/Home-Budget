package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;


public class Controller {


    @FXML private Label textLabel;
    @FXML private TextField asortText;
    @FXML private TextField cenaText;
    @FXML private DatePicker kiedyText;
    @FXML private DatePicker kiedyText2;
    @FXML private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    @FXML public TableView<Zakup> tablicaOkno;
    @FXML private TableColumn<Zakup,String>  nazwaKolumna;
    @FXML private TableColumn<Zakup,String>  kategoriaKolumna;
    @FXML private TableColumn<Zakup,Double>  cenaKolumna;
    @FXML private TableColumn<Zakup,LocalDate>  dataKolumna;
    @FXML private TableColumn<Zakup,String>  osobaKolumn;

    ArrayList<Zakup> lista = new ArrayList<Zakup>();

    public Controller(){};

        @FXML
        public void dodajzakup(ActionEvent e) {
        lista.add(new Zakup(asortText.getText(), Double.valueOf(cenaText.getText()), LocalDate.parse(String.valueOf(kiedyText.getValue()),formatter) ));

        }


        @FXML
        public void pokazzakupy(ActionEvent a){

            textLabel.setText("");
            for(Zakup za: lista) {
                textLabel.setText(textLabel.getText() + za.toString() +String.format("\n"));
            }
        }

        public void zapisz(ActionEvent e) throws IOException {

            PrintWriter zapis = new PrintWriter(new BufferedWriter(new FileWriter("listazakupow.txt", true)));

            for(Zakup za: lista) {
                zapis.println(za.toString());
            }

            zapis.close();
        }

    @FXML
    public void initialize() {
        textLabel.setText("");

        //ustawia kolumny w tabeli

        nazwaKolumna.setCellValueFactory(new PropertyValueFactory<Zakup,String>("Nazwa"));
        kategoriaKolumna.setCellValueFactory(new PropertyValueFactory<Zakup,String>("Kategoria"));
        cenaKolumna.setCellValueFactory(new PropertyValueFactory<Zakup,Double>("Cena"));
        dataKolumna.setCellValueFactory(new PropertyValueFactory<Zakup,LocalDate>("Data"));
        osobaKolumn.setCellValueFactory(new PropertyValueFactory<Zakup,String>("Osoba"));

        tablicaOkno.setItems(getZakupy());

        }

        @FXML
        public ObservableList<Zakup> getZakupy()
        {

            ObservableList<Zakup> zakupy = FXCollections.observableArrayList();
            zakupy.add(new Zakup("Laptop",1999.99,LocalDate.parse("13/02/19",formatter),"Trwałe","Arek"));
            zakupy.add(new Zakup("Lidl",123.76,LocalDate.parse("12/02/19",formatter),"Spozywcze","Drek"));
            zakupy.add(new Zakup("Paliwo",200.12,LocalDate.parse("13/02/19",formatter),"Saochod","Frek"));
            zakupy.add(new Zakup("Apteka",12.99,LocalDate.parse("11/01/19",formatter),"Higiena i leki","Jrek"));

            return zakupy;
        }



}
