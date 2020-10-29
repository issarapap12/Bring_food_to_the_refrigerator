package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.HotWater;
import model.Ice;
import model.Water;

public class WaterController {
    @FXML
    private JFXButton close;
    @FXML
    private Label numLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label milliliter;
    @FXML
    private Label temperature;
    @FXML
    private Label water_ice;
    @FXML
    private Label typeLabel;
    @FXML
    private Label time_wi;
    @FXML
    private Label minLabel;
    @FXML
    private Label tempp;
    @FXML
    private Label degree;
    @FXML
    private JFXTextField tempText, numText;

    private Water water ;
    private Ice ice ;
    private HotWater hotWater ;


    @FXML
    public void initialize() {

    }

    @FXML
    public void waterCool(ActionEvent event) {
        if(numText.getText().isEmpty() || tempText.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Please fill out all fields.");

            alert.showAndWait();
        }
        else {

                water = new Water(Double.parseDouble(numText.getText()), Double.parseDouble(tempText.getText()));
                double ml2 = water.timeColdWater();
                double liter = water.milliliterToLiter();
                double temp = water.tempTo();
                water_ice.setText("Quantity");
                typeLabel.setText("L");
                time_wi.setText("Time");
                minLabel.setText("Minute");
                numLabel.setText(String.valueOf(liter));
                timeLabel.setText(String.valueOf(ml2));
                tempp.setText(String.valueOf(temp));
                degree.setText("Fahrenheit");
//            }

        }
//       refresh();

    }

    @FXML
    public void waterIce(ActionEvent event) {
        if(numText.getText().isEmpty() || tempText.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Please fill out all fields.");

            alert.showAndWait();
        }
        else {
                ice = new Ice(Double.parseDouble(numText.getText()), Double.parseDouble(tempText.getText()));
                double ml3 = ice.timeColdWater();
                int ml2 = ice.amountIce();
                double temp = ice.tempTo();
                milliliter.setText("Milliliter");
                temperature.setText("Temperature");
                System.out.println(ml2);
                numLabel.setText(String.valueOf(ml2));
                timeLabel.setText(String.valueOf(ml3));
                water_ice.setText("Ice");
                typeLabel.setText("Cubes");
                time_wi.setText("Time");
                minLabel.setText("Minute");
                tempp.setText(String.valueOf(temp));
                degree.setText("Kelvin");

        }

//        refresh();
    }

    @FXML
    public void waterHot(ActionEvent event) {
        if(numText.getText().isEmpty() || tempText.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Please fill out all fields.");

            alert.showAndWait();
        }
        else {

                hotWater = new HotWater(Double.parseDouble(numText.getText()), Double.parseDouble(tempText.getText()));
                double ml3 = hotWater.timeHotWater();
                double liter = hotWater.milliliterToLiter();
                double temp = hotWater.tempTo();
                milliliter.setText("Milliliter");
                temperature.setText("Temperature");
                timeLabel.setText(String.valueOf(ml3));
                water_ice.setText("Quantity");
                typeLabel.setText("L");
                time_wi.setText("Time");
                minLabel.setText("Minute");
                numLabel.setText(String.valueOf(liter));
                tempp.setText(String.valueOf(temp));
                degree.setText("Réaumur");

        }

//        refresh();
    }


    @FXML
    public void handleClose(ActionEvent event) {
        close.setOnAction(event1 -> {
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        });

    }




//    public void refresh() {
//        this.temperature.setText("");
////        this.tempText.setText("");
////        numLabel.setText("");
////        this.milliliter.setText("");
//    }

}
