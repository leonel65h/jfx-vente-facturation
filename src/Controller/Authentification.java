/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Admin_user;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author leonel65
 */
public class Authentification implements Initializable {

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField nom;
    @FXML
    private AnchorPane roots;

    private void fermer() {
        Stage stage = (Stage) roots.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        Connection conn = Controller.Connexiondb.connect();
        PreparedStatement start;
        ResultSet end;
        String sql = "SELECT * FROM users WHERE nom=? AND password=?";
        try {
            start = conn.prepareStatement(sql);
            start.setString(1, nom.getText());
            start.setString(2, password.getText());
            end = start.executeQuery();
            if (end.next()) {
                switch (end.getInt("role")) {
                    case 1: {
                        fermer();
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/Vue/admin_accueil.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.show();
                        break;
                    }
                    case 2: {
                        fermer();
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/Vue/stock_accueil.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.show();

                        break;

                    }
                    case 3: {
                        fermer();
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/Vue/vente_accueil.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.resizableProperty().setValue(Boolean.FALSE);
                        stage.show();
                        break;
                    }
                    default:
                        break;
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Authentification");
                alert.setHeaderText(null);
                alert.setContentText("Echec d'hauthentification");
                alert.showAndWait();
                nom.clear();
                password.clear();
            }
        } catch (SQLException e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
