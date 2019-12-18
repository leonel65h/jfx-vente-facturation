/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Add_produit;
import Model.Admin_add;
import Model.Admin_user;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author leonel65
 */
public class Stock_accueil implements Initializable {

    private BorderPane root;
    private Label qt_pdt;
    private BorderPane borderPane;
    @FXML
    private TextField designation;
    @FXML
    private TextField quantite;
    @FXML
    private TextField pu;

    private void fermer() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }

    private void deconnexion(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection conn = Connexiondb.connect();

        String sql = "SELECT * FROM `produit` ORDER BY id_p DESC LIMIT 1; ";
        try {
            ResultSet result = conn.createStatement().executeQuery(sql);
            while (result.next()) {

                qt_pdt.setText(result.getString("id_p"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_user_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void liste_p(ActionEvent event) throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("/Vue/stock_liste.fxml"));
        borderPane.setCenter(root);
    }

    @FXML
    private void add(ActionEvent event) {

    }

    @FXML
    private void annuller(ActionEvent event) {
        System.exit(0);
    }

}
