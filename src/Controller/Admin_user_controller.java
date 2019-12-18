/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Admin_add;
import Model.Admin_user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author leonel65
 */
public class Admin_user_controller implements Initializable {

    @FXML
    private TableView<Admin_user> table;
    @FXML
    private TableColumn<Admin_user, Integer> num;
    @FXML
    private TableColumn<Admin_user, String> nom;
    @FXML
    private TableColumn<Admin_user, String> prenom;
    @FXML
    private TableColumn<Admin_user, String> adresse;
    @FXML
    private TableColumn<Admin_user, Integer> phone;
    @FXML
    private TableColumn<Admin_user, String> function;

    ObservableList<Admin_user> obj = FXCollections.observableArrayList();
    @FXML
    private TextField firstname;
    @FXML
    private TextField secondname;
    @FXML
    private TextField adress;
    @FXML
    private ComboBox<String> role;
    private ObservableList<String> list = FXCollections.observableArrayList("Gest_stock", "Vendeur");

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.setItems(list);
        //selectionner les utilisateur de la base de donnee
        Connection conn = Connexiondb.connect();
        String sql = "SELECT * FROM `users` WHERE role!=4 AND role!=1";
        try {
            ResultSet result = conn.createStatement().executeQuery(sql);
            int i = 1;
            String role;
            while (result.next() && i != 0) {
                if (result.getInt("role") == 2) {
                    role = "gest stock";
                    obj.add(new Admin_user(result.getInt("id_user"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"),
                            result.getInt("telephone"), role));
                } else {
                    role = "Vendeur";
                    obj.add(new Admin_user(result.getInt("id_user"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"),
                            result.getInt("telephone"), role));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_user_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        num.setCellValueFactory(new PropertyValueFactory<>("numero"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        phone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        function.setCellValueFactory(new PropertyValueFactory<>("fonction"));

        table.setItems(obj);
        table.setEditable(true);
        table.setStyle("fx-font-family:arial;");

    }

    //fonction qui ajoute un nouveau vendeur / gest_stock
    @FXML
    public void ajouter(ActionEvent event) {
        String nom = firstname.getText();
        String prenom = secondname.getText();
        String adresse = adress.getText();
        String role = this.role.getValue();
        Admin_add user = new Admin_add();

        if (role == "Gest_stock") {
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setAdresse(adresse);
            user.setRole("2");
        } else {

            user.setNom(nom);
            user.setPrenom(prenom);
            user.setAdresse(adresse);
            user.setRole("3");

        }
        firstname.clear();
        secondname.clear();
        adress.clear();
        this.role.setValue(null);
        int status = Connexiondb.Ajouter(user);

    }
//suprimer user

    @FXML
    public void delete(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Vue/admin_delete_user.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }
//update user

    @FXML
    public void update(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Vue/admin_update_user.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    @FXML
    public void update1(ActionEvent event) throws IOException {
        table.getItems().clear();

        //selectionner les utilisateur de la base de donnee
        Connection conn = Connexiondb.connect();
        String sql = "SELECT * FROM `users`  WHERE role!=4 AND role!=1";
        try {
            ResultSet result = conn.createStatement().executeQuery(sql);
            int i = 1;
            String roles;
            while (result.next() && i != 0) {
                if (result.getInt("role") == 2) {
                    roles = "gest stock";
                    obj.add(new Admin_user(result.getInt("id_user"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"),
                            result.getInt("telephone"), roles));
                } else {
                    roles = "Vendeur";
                    obj.add(new Admin_user(result.getInt("id_user"), result.getString("nom"), result.getString("prenom"), result.getString("adresse"),
                            result.getInt("telephone"), roles));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin_user_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
