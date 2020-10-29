package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Effects;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFrontFridgeIn extends Effects implements Initializable {
    @FXML
    private JFXButton close;
    @FXML
    private JFXButton minimise;
    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXButton iceButton;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

            customiseWindowButtons(close, minimise);
            moveWindow(anchorPane);

//            System.out.println(this.price);
//            pricet.setText(this.price + "");
//            priceM.setPriceMo(this.price);


    }


    public void profileOwner(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/profile.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
//                    stage.initStyle(StageStyle.UNDECORATED);
//                    stage.initModality(Modality.WINDOW_MODAL);
        stage.show();

    }

    public void openDoor(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/display.fxml"));
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


    }
    public void showDialog(){

        JFXDialogLayout dialogContent = new JFXDialogLayout();
        dialogContent.setPrefWidth(280);



        JFXButton close = new JFXButton("Close");
        close.setButtonType(JFXButton.ButtonType.RAISED);
        close.setStyle("-fx-background-color: #FF9A00; -fx-text-fill: white");
        dialogContent.setActions(close);
        JFXDialog dialog = new JFXDialog(stackPane, dialogContent, JFXDialog.DialogTransition.TOP);
        dialog.setOverlayClose(false);
        close.setOnAction(event -> {
            dialog.close();
        });
        dialog.show();

        dialog.setOnDialogOpened(event -> anchorPane.setEffect(new GaussianBlur(5d)));
        dialog.setOnDialogClosed(event -> anchorPane.setEffect(new GaussianBlur(0d)));
    }


    @FXML
    public void iceHandle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/waterView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }
}
