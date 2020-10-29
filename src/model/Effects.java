package model;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.json.JsonObject;
import java.io.IOException;

public class Effects {
    public static boolean confirmed=false;
    private double initialx, initialy;
    protected static String page="";
    private boolean rotatedpane =false;


    public void handleClose(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }



    public void rotateButton(JFXButton btn){
        if(rotatedpane ==false){
            rotatedpane =true;
            RotateTransition rt=new RotateTransition(Duration.millis(60),btn);
            rt.setByAngle(45);
            rt.setCycleCount(2);
            rt.setAutoReverse(true);
            rt.play();

            rt.setOnFinished(event -> {
                RotateTransition rt2=new RotateTransition(Duration.millis(60),btn);
                rt2.setByAngle(-45);
                rt2.setCycleCount(2);
                rt2.setAutoReverse(true);
                rt2.play();
                rt2.setOnFinished(event1 -> rotatedpane =false);
            });
        }
    }



    public void disableAllFocus(Pane pane){
        for (Node n: pane.getChildren()){
            if(n instanceof JFXButton){
                ((JFXButton) n).setDisableVisualFocus(true);
            }else if((n instanceof AnchorPane) || (n instanceof HBox)){
                disableAllFocus((Pane) n);
            }
        }
    }




    public void moveWindow(AnchorPane pane){
        pane.setOnMousePressed(e ->{
            initialx = e.getSceneX();
            initialy = e.getSceneY();
        });
        pane.setOnMouseDragged(e -> {
            Node source = (Node) e.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setX(e.getScreenX() - initialx);
            stage.setY(e.getScreenY() - initialy);
        });
    }

    public void customiseWindowButtons(JFXButton close,  JFXButton minimise){
        close.setOnMouseEntered(e -> {
            close.setStyle("-fx-background-color:  #F6490D");
            close.setEffect(new Bloom(0.7));
        });
        close.setOnMouseExited(e -> {
            close.setStyle("-fx-background-color: transparent");
            close.setEffect(new Bloom(1));
        });

        minimise.setOnMouseEntered(e -> {
            minimise.setStyle("-fx-background-color:  #F6490D");
            minimise.setEffect(new Bloom(0.7));
        });
        minimise.setOnMouseExited(e -> {
            minimise.setStyle("-fx-background-color: transparent");
            minimise.setEffect(new Bloom(1));
        });
    }


    public void minimiseWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }



}