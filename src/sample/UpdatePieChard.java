package src.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdatePieChard {

    public static ObservableList update (TableView<Zakup> tablicaOknoClass){

        ObservableList<PieChart.Data> pieChartData;

        HashMap<String, Double> TotalForEachCategoryMap =  populateMap(tablicaOknoClass);

        pieChartData = getChartData(TotalForEachCategoryMap);


        return pieChartData;

    }


    private static ObservableList<PieChart.Data> getChartData(HashMap<String,Double> TotalForEachCategoryMap ){

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("initialize",0)
                );


        for(String name : TotalForEachCategoryMap.keySet())
        {
            /*pieChartData = FXCollections.observableArrayList(new PieChart.Data(name,TotalForEachCategoryMap.get(name)));*/
            pieChartData.add(new PieChart.Data(name,TotalForEachCategoryMap.get(name)));

        }

        pieChartData.remove(0);

        return pieChartData;

    }

    private static HashMap<String, Double> populateMap(TableView<Zakup> tablicaOknoClass)
    {
        HashMap<String,Double> TotalForEachCategoryMap = new HashMap<>();

        List<Zakup> ZakupyList =  MyHelper.tableViewToList(tablicaOknoClass);

        TotalForEachCategoryMap.clear();

        String tempZakupyName;

        for(Zakup za : ZakupyList){

            tempZakupyName = za.getKategoria();


            if(TotalForEachCategoryMap.containsKey(tempZakupyName))
            {
                TotalForEachCategoryMap.computeIfPresent(tempZakupyName, (k,v) -> v + za.getCena());

            }else{
                TotalForEachCategoryMap.put(tempZakupyName,za.getCena());
            }

        }

        return TotalForEachCategoryMap;

    }

}
