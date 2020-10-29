package controller;

import model.FileRW;
import model.Food;
import model.Utility;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fs = File.separator;
        String dir = System.getProperty("user.dir") + fs + "resource" + fs + "data";
        String dirName = dir + File.separator + "RefrigeratorData.csv";
        ArrayList<Food> model = new ArrayList<>();
        try {
            model.add(new Food("beef",10,"Freezer", Utility.dateFormat.parse("16/10/19")));
            model.add(new Food("burger",10,"Fresh", Utility.dateFormat.parse("17/10/19")));
            model.add(new Food("icecream",10,"Freezer", Utility.dateFormat.parse("18/10/19")));
            model.add(new Food("onikiri",10,"Fresh", Utility.dateFormat.parse("16/10/19")));
            model.add(new Food("pizza",10,"Freezer", Utility.dateFormat.parse("16/10/19")));
            model.add(new Food("seafood",10,"Fresh", Utility.dateFormat.parse("16/10/19")));
            model.add(new Food("water",10,"Freezer", Utility.dateFormat.parse("16/10/19")));
            model.add(new Food("vegetable",10,"Fresh", Utility.dateFormat.parse("16/10/19")));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        FileRW fileRW = new FileRW(dir,dirName);
        fileRW.write(model);
    }

}
