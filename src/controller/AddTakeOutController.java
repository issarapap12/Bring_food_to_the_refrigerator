package controller;

import com.jfoenix.controls.*;
import com.sun.xml.internal.ws.util.StringUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.FileRW;
import model.Food;
//import model.Fridge;
import model.Effects;
import model.Utility;


import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class AddTakeOutController extends Effects implements Initializable {
    @FXML
    public Label nameLabel;
    @FXML
    public Label amountLabel;
    @FXML
    public Label alertLabel;
    @FXML
    public DatePicker exepireDatePicker;
    @FXML
    public ImageView itemImageView;
    @FXML
    public JFXButton takeOutButton;
    @FXML
    public JFXButton addButton;
    @FXML
    public JFXTextField numberAddText;
    @FXML
    public JFXTextField numberTakeOutText;
    @FXML
    public JFXComboBox<String> selectComboBox;
    @FXML
    public JFXComboBox<String> tComboBox;


    private ArrayList<Food> model;
    private int totalAmount;
    private DisplayController superModel;
    private String fs = File.separator;
    private String dir = System.getProperty("user.dir") + fs + "resource" + fs + "data";
    private String fileName = dir + fs + "RefrigeratorData.csv";
    private String imageName;
    private TreeMap<String, ArrayList<ArrayList<Food>>> allFood;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // set element as thread
        Platform.runLater(this::setElement);
    }

    @FXML
    public void handleAdd(ActionEvent event) {
        LocalDate localDate = exepireDatePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        int amount = Integer.parseInt(numberAddText.getText());
        boolean hasZDate = false;
        if(date == null || amount<=0 ) {
            alertLabel.setText("Not completed");
//            showDialog();
        }
        else {
            for (Food each : model) {
                if (date.equals(each.getExpire())) {
                    each.add(amount);
                    hasZDate = true;
                    break;
                }
            }


            if (!hasZDate) {
                model.add(new Food(model.get(0).getName(), amount, tComboBox.getSelectionModel().getSelectedItem(), date));
            }
        }


        System.out.println("All Food: " + allFood);
        updateFile();;
        refresh();


        // Sort data array
        Comparator<Food> c = Comparator.comparing(Food::getExpire);
        this.model.sort(c);
        setElement();
    }

    public void refresh() {
        this.totalAmount = 0;
        for (Food each : this.model) {
            this.totalAmount += each.getAmount();
        }
        this.amountLabel.setText(totalAmount + "");


        numberAddText.setText("0");
        tComboBox.getItems().clear();
        exepireDatePicker.getEditor().clear();
        selectComboBox.getItems().clear();

        selectComboBox.getItems().add("All");
        selectComboBox.getSelectionModel().selectFirst();
        amountLabel.setText(this.totalAmount + "");

        tComboBox.getItems().add("Freezer");
        tComboBox.getItems().add("Fresh");

        tComboBox.getSelectionModel().select(model.get(0).getType());

        // set each date as item
        for (Food each : model) {
            selectComboBox.getItems().add(Utility.dateFormat.format(each.getExpire()));

        }

        selectComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            int index = selectComboBox.getSelectionModel().getSelectedIndex() - 1;
            if (index <= -1) {
                amountLabel.setText(this.totalAmount + "");
            }
            else {
                amountLabel.setText(this.model.get(index).getAmount() + "");
            }
        });
    }


    private void setElement() {
        // set image and label
        System.out.println("Image: " + imageName);
        itemImageView.setImage(new Image(imageName));
        nameLabel.setText(StringUtils.capitalize(model.get(0).getName()));

        refresh();
    }

    public void setModel(TreeMap<String, ArrayList<ArrayList<Food>>> allFood, ArrayList<Food> model) {
        String fs = File.separator;
        this.model = model;
        this.allFood = allFood;

        System.out.println("Model: " + model.toString());

        // sort array of food by date
        Comparator<Food> c = Comparator.comparing(Food::getExpire);
        this.model.sort(c);


        // set filename of image
        this.imageName = "data" + fs + model.get(0).getName() + ".jpg";
    }


    private void updateFile() {
        FileRW fileRW = new FileRW(dir, fileName);
        fileRW.write(allFood);
    }

    @FXML
    private void takeOut(ActionEvent event) {
        int amount = Integer.parseInt(numberTakeOutText.getText());
        String dateStr = selectComboBox.getSelectionModel().getSelectedItem();

        if (dateStr.equals("All")) {
            return;
        }
        Date date = null;
        try {
            date = Utility.dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Food each : model) {
            if (each.getExpire().equals(date)) {
                if (amount < 0) {
                    System.out.println("no");
                } else if (amount > Integer.parseInt(amountLabel.getText())) {
                    System.out.println("amount > จำนวนของ");
                } else {
                    each.takeOut(amount);
                }

                break;
            }
        }

        refresh();
        updateFile();
    }


}
