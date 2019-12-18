/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Admin_add;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author leonel65
 */
public class Admin_update implements Initializable {

    @FXML
    private TextField id_user;
    @FXML
    private TextField adresse_user;
    @FXML
    private TextField prenom_user;
    @FXML
    private TextField nom_user;
    @FXML
    private TextField role_user;
    @FXML
    private AnchorPane root;

    private void fermer() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void recherche(ActionEvent event) {

        String sid = id_user.getText();
        int id = Integer.parseInt(sid);
        Admin_add user = Connexiondb.recherche(id);
        nom_user.setText(user.getNom());
        prenom_user.setText(user.getPrenom());
        adresse_user.setText(user.getAdresse());
        role_user.setText(user.getRole());

    }

    @FXML
    private void update_user(ActionEvent event) {
        String sid = id_user.getText();
        int id = Integer.parseInt(sid);
        String nom = nom_user.getText();
        String prenom = prenom_user.getText();
        String adresse = adresse_user.getText();
        String role = role_user.getText();

        Admin_add user = new Admin_add();
        user.setNumero(id);
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setAdresse(adresse);
        user.setRole(role);

        int status = Connexiondb.update(user);
        id_user.clear();
        nom_user.clear();
        prenom_user.clear();
        adresse_user.clear();
        role_user.clear();

    }

    @FXML
    private void delete_users(ActionEvent event) {
        String sid = id_user.getText();
        int id = Integer.parseInt(sid);
        String nom = nom_user.getText();
        String prenom = prenom_user.getText();
        String adresse = adresse_user.getText();
        String role = role_user.getText();

        Admin_add user = new Admin_add();
        user.setNumero(id);
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setAdresse(adresse);
        user.setRole(role);

        int status = Connexiondb.update(user);
        int stat = Connexiondb.delete(id);
        id_user.clear();
        nom_user.clear();
        prenom_user.clear();
        adresse_user.clear();
        role_user.clear();
    }

    @FXML
    private void delete_user(ActionEvent event) {
        fermer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
