package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;


public class ControllerProfile {
    @FXML
    JFXButton close;


    @FXML
    public void handleClose(ActionEvent event){
        close.setOnAction(event1 -> {
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        });
    }

}

