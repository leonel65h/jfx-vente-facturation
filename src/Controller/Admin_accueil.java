/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * FXML Controller class
 *
 * @author leonel65
 */
public class Admin_accueil implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton utilisateurs;
    @FXML
    private JFXButton dashboard;
    @FXML
    private JFXButton stock;
    @FXML
    private JFXButton stat;
    @FXML
    private JFXButton param;
    @FXML
    private Label label;
    @FXML
    private BorderPane accueil;

    private void fermer() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void utilisateur(ActionEvent event) throws IOException {

        BorderPane root = FXMLLoader.load(getClass().getResource("/Vue/admin_util.fxml"));
        root.setPadding(new Insets(-45, 0, 0, 0));
        borderPane.setCenter(root);
        utilisateurs.setStyle("-fx-background-color: #0290d1;-fx-background-radius: 0 0 0 0;");
        dashboard.setStyle("-fx-background-color: #051428;");
        stat.setStyle("-fx-background-color: #051428;");
        stock.setStyle("-fx-background-color: #051428;");
        param.setStyle("-fx-background-color: #051428;");

    }

    @FXML
    private void dashboard(ActionEvent event) throws IOException {

    }

    @FXML
    private void stock(ActionEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("/Vue/admin_stock.fxml"));
        root.setPadding(new Insets(-45, 0, 0, 0));
        borderPane.setCenter(root);
        stock.setStyle("-fx-background-color: #0290d1;-fx-background-radius: 0 0 0 0;");
        utilisateurs.setStyle("-fx-background-color: #051428;");
        stat.setStyle("-fx-background-color: #051428;");
        dashboard.setStyle("-fx-background-color: #051428;");
        param.setStyle("-fx-background-color: #051428;");
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {

        BorderPane root = FXMLLoader.load(getClass().getResource("/Vue/admin_stat.fxml"));
        root.setPadding(new Insets(-45, 0, 0, 0));
        borderPane.setCenter(root);
        stat.setStyle("-fx-background-color: #0290d1;-fx-background-radius: 0 0 0 0;");
        utilisateurs.setStyle("-fx-background-color: #051428;");
        dashboard.setStyle("-fx-background-color: #051428;");
        stock.setStyle("-fx-background-color: #051428;");
        param.setStyle("-fx-background-color: #051428;");
    }

    private void param(ActionEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("/Vue/admin_util.fxml"));
        root.setPadding(new Insets(-45, 0, 0, 0));
        borderPane.setCenter(root);
        param.setStyle("-fx-background-color: #0290d1;-fx-background-radius: 0 0 0 0;");
        utilisateurs.setStyle("-fx-background-color: #051428;");
        stat.setStyle("-fx-background-color: #051428;");
        stock.setStyle("-fx-background-color: #051428;");
        dashboard.setStyle("-fx-background-color: #051428;");
    }

    @FXML
    private void deconnexion(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deconnexion");
        alert.setHeaderText("voulez-vous vraiment vous déconnecter?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            fermer();
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Vue/authentification.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } else if (option.get() == ButtonType.CANCEL) {
        }
    }

}
