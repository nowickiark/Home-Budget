package src.sample;

import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class MyHelper {

    public static List<Zakup> tableViewToList(TableView<Zakup> tablicaOknoClass){

        List<Zakup> tempTable = new ArrayList<>();
        /*   Zakup za = new Zakup();*/
        for(int i =0; i < tablicaOknoClass.getItems().size();i++) {
            tempTable.add(tablicaOknoClass.getItems().get(i));
        }

        return tempTable;
    }


}
