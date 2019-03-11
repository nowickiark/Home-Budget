package src.sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.TableView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WczytajZPliku {


    public static void wczytaj(TableView<Zakup> tablicaOknoClass) {

        List<Zakup> temptable = new ArrayList<>();

        String currentDirectory = new File("").getAbsolutePath();


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(currentDirectory + "\\newfile.json"));
            Gson gson = new Gson();

            Type zakupyTypeList = new TypeToken<ArrayList<Zakup>>(){}.getType();

            temptable = gson.fromJson(bufferedReader, zakupyTypeList);
        }
        catch (FileNotFoundException e) { System.out.println("Błąd odczytu danych");}


        tablicaOknoClass.getItems().clear();

        for(Zakup singleZak : temptable)
        {
            tablicaOknoClass.getItems().add(singleZak);
        }

    }

}
