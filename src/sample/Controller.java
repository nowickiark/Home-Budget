package src.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;


public class Controller {


    @FXML private Label textLabel;

    //Zmienne do pobierania danych z pól tekstowych
    @FXML private TextField asortText;
    @FXML private ComboBox<String> listaKategorii;
    @FXML private TextField cenaText;
    @FXML private TextField ktoText;
    @FXML private DatePicker kiedyText;
    @FXML private DatePicker kiedyText2;
    @FXML private PieChart pieChart;


    //Zmienne do obsługi tabeli
    @FXML private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    @FXML private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyy/MM/dd");
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

/*           Lista pól w tabeli:
            1.)Nazwa
            2.)Kategoria
            3.)Cena
            4.)Kiedy
            5.)Kto   */

            Zakup nowyZakup = new Zakup(asortText.getText(),
                                  listaKategorii.getSelectionModel().getSelectedItem(),
                                  Double.valueOf(cenaText.getText()),
                                  kiedyText.getValue(),
                                  ktoText.getText()
                                    );


            System.out.format(nowyZakup.getKategoria() + " " + nowyZakup.getNazwa()+ " " + nowyZakup.getCena() + " " + nowyZakup.getData());

            tablicaOkno.getItems().add(nowyZakup);

        }

        @FXML
        public void zapiszDoPliku(ActionEvent a)
        {
            ZapiszDoPlikuClass zas = new ZapiszDoPlikuClass(tablicaOkno);
            zas.pokazTablice();

        }

        @FXML
        public void wczytajZpliku(ActionEvent a) throws FileNotFoundException {

            WczytajZPliku.wczytaj(tablicaOkno);

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
    public void initialize() throws FileNotFoundException {
        textLabel.setText("");

            listaKategorii.getItems().addAll("Spożywcze",
                                                        "Chemia",
                                                        "Higiena",
                                                        "Rozrywka",
                                                        "Inne");

        //ustawia kolumny w tabeli

        nazwaKolumna.setCellValueFactory(new PropertyValueFactory<Zakup,String>("Nazwa"));
        kategoriaKolumna.setCellValueFactory(new PropertyValueFactory<Zakup,String>("Kategoria"));
        cenaKolumna.setCellValueFactory(new PropertyValueFactory<Zakup,Double>("Cena"));
        dataKolumna.setCellValueFactory(new PropertyValueFactory<Zakup,LocalDate>("Data"));
        osobaKolumn.setCellValueFactory(new PropertyValueFactory<Zakup,String>("Osoba"));



        WczytajZPliku.wczytaj(tablicaOkno);
        /*tablicaOkno.setItems(getZakupy());*/

       ObservableList<PieChart.Data> pieChartData =
               FXCollections.observableArrayList(
                       new PieChart.Data("Rozrywka",20),
                       new PieChart.Data("Żywność",30),
                       new PieChart.Data("Inne",10),
                       new PieChart.Data("Chemia",25),
                       new PieChart.Data("Higiena i Leki",15)
               );

       pieChart.setData(pieChartData);


        }

        //Funkcja do przycisku zapisz koszty ogólne


        @FXML
        public ObservableList<Zakup> getZakupy()
        {

            ObservableList<Zakup> zakupy = FXCollections.observableArrayList();
            zakupy.add(new Zakup("Laptop","Trwałe",1999.99,LocalDate.parse("13/02/19",formatter),"Arek"));
            zakupy.add(new Zakup("Lidl","Spozywcze",123.76,LocalDate.parse("12/02/19",formatter),"Drek"));
            zakupy.add(new Zakup("Paliwo","Saochod",200.12,LocalDate.parse("13/02/19",formatter),"Frek"));
            zakupy.add(new Zakup("Apteka","Higiena i leki",12.99,LocalDate.parse("11/01/19",formatter),"Jrek"));

            return zakupy;
        }



}
