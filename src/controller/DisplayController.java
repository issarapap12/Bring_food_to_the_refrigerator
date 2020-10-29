package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.FileRW;
import model.Food;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class DisplayController {
    @FXML public GridPane gridPane;

    private String fs = File.separator;
    private String dir = System.getProperty("user.dir") + fs + "resource" + fs + "data";
    private String fileName = dir + File.separator + "RefrigeratorData.csv";
    private ArrayList<Food> food;

    @FXML
    public void initialize() {
        FileRW fileManager = new FileRW(dir, fileName);

        this.food = (ArrayList<Food>) fileManager.read();
        System.out.println(fileManager);

        setElements();
    }

    public void setElements() {
        TreeMap<String, ArrayList<ArrayList<Food>>> treeMap = new TreeMap<>();

        for (Food each : food) {
            if (!treeMap.containsKey(each.getName())) {
                treeMap.put(each.getName(), new ArrayList<>());
                treeMap.get(each.getName()).add(new ArrayList<>());
                treeMap.get(each.getName()).add(new ArrayList<>());

            }
            if (each.getType().equals("Freezer")) {
                treeMap.get(each.getName()).get(0).add(each);
            }
            else if(each.getType().equals("Fresh")){
                treeMap.get(each.getName()).get(1).add(each);
            }
        }

        int i = 0;
        for (ArrayList<ArrayList<Food>> each : treeMap.values()) {
            if(each.get(0).isEmpty()) continue;
            gridPane.add(getContent(treeMap, each.get(0)), i%2, i/2);
            i++;
        }
        i = i + (i%2) * i;
        gridPane.add(new JFXButton(), i%2, i/2);
        gridPane.add(new JFXButton(), (i+1)%2, (i+1)/2);
        i += 2;

        for (ArrayList<ArrayList<Food>> each : treeMap.values()) {
            if(each.get(1).isEmpty()) continue;
            gridPane.add(getContent(treeMap, each.get(1)), i%2, i/2);
            i++;
        }

    }

    private Parent getContent(TreeMap<String, ArrayList<ArrayList<Food>>> content, ArrayList<Food> ls) {
        Parent output = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addTakeOut.fxml"));
            output = fxmlLoader.load();
            AddTakeOutController controller = fxmlLoader.getController();
            controller.setModel(content, ls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GridPane root = new GridPane();
        // Set a CSS for the GridPane
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");


        return output;
    }
}
