package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class ZapiszDoPlikuClass {

    @FXML
    private TableView<Zakup> tablicaOknoClass;

    public ZapiszDoPlikuClass(TableView<Zakup> tablicaOknoClass)
    {
        this.tablicaOknoClass = tablicaOknoClass;
    }

    public void pokazTablice() {

        List<Zakup> tempTable = new ArrayList<>();
     /*   Zakup za = new Zakup();*/
        for(int i =0; i < tablicaOknoClass.getItems().size();i++) {
           /* za = tablicaOknoClass.getItems().get(i);*/
            tempTable.add(tablicaOknoClass.getItems().get(i));
        }

        for (Zakup tempZa : tempTable)
        {
            System.out.println(tempZa.toString());
        }

    }
}
