package src.sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZapiszDoPlikuClass {

    @FXML
    private TableView<Zakup> tablicaOknoClass;

    public ZapiszDoPlikuClass(TableView<Zakup> tablicaOknoClass)
    {
        this.tablicaOknoClass = tablicaOknoClass;
    }

    public void pokazTablice() {

        List<Zakup> tempTable = MyHelper.tableViewToList(tablicaOknoClass);

        //Tworzy obiekny json z uwzględnieniem wartości pustych w obiektach
        Gson gson = new GsonBuilder().serializeNulls().create();


        String Serialized = gson.toJson(tempTable);

        System.out.println(Serialized);


        List<Zakup> temTable2 = new ArrayList<>();

        //Wprowadzenie nowego typu który pozwoli zaimportować Arraylistę obiektów z typu json
        Type zakupyTypeList = new TypeToken<ArrayList<Zakup>>(){}.getType();
        temTable2 = gson.fromJson(Serialized, zakupyTypeList);

        /*System.out.println(temTable2.get(1).getNazwa());*/


        try (FileWriter file = new FileWriter("newfile.json")) {
            file.write(Serialized);
            file.flush();
        }
            catch (IOException e) {
            e.printStackTrace();
        }


    }
}
