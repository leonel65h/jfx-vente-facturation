/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Achat;
import Model.Admin_user;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author leonel65
 */
public class Vente_accueil implements Initializable {

    @FXML
    private Label label;
    @FXML
    private BorderPane root;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private ComboBox<String> produit;
    ObservableList<String> produits = FXCollections.observableArrayList();
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField qte_stock;
    @FXML
    private TextField qt_total;
    @FXML
    private TableView<Achat> tb_list_achat;
    @FXML
    private TableColumn<Achat, String> col_achat;
    @FXML
    private TableView<Achat> tb_list_montant;
    @FXML
    private TableColumn<Achat, Integer> col_montant;
    @FXML
    private JFXButton add_produit;

    ObservableList<Achat> achat_table = FXCollections.observableArrayList();
    private int id_produit;
    @FXML
    private JFXButton save;

    @FXML
    private TextField col_total;

    private void fermer() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
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

    public ObservableList liste_p() {
        Connection conn = Connexiondb.connect();
        String sql = "SELECT * FROM `produit`";
        PreparedStatement start;
        try {
            start = conn.prepareStatement(sql);
            ResultSet result = start.executeQuery();
            while (result.next()) {
                produits.add(result.getString("designation"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return produits;

    }

    @FXML
    public void select_combo(ActionEvent event) {

        if (event.getSource() == produit) {
            Connection conn = Connexiondb.connect();
            String sql = "SELECT * FROM `produit` WHERE designation =?";
            PreparedStatement start;
            try {
                start = conn.prepareStatement(sql);
                start.setString(1, produit.getValue());
                ResultSet result = start.executeQuery();
                while (result.next()) {
                    txt_prix.setText(String.valueOf(result.getInt("pu")));
                    qte_stock.setText(String.valueOf(result.getInt("qte")));
                    id_produit = result.getInt("id_p");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produit.setItems(liste_p());
    }

    @FXML
    private void add_pdt(ActionEvent event) throws SQLException {
        Connection conn = Connexiondb.connect();

        achat_table.add(new Achat(produit.getValue(), Integer.parseInt(txt_prix.getText()) * Integer.parseInt(qt_total.getText())));
        col_achat.setCellValueFactory(new PropertyValueFactory<>("pdt"));
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        int c = Integer.parseInt(txt_prix.getText()) * Integer.parseInt(qt_total.getText());
        col_total.setText("" + c);
        tb_list_achat.setItems(achat_table);
        tb_list_montant.setItems(achat_table);

    }

    @FXML
    private void save_produit(ActionEvent event) throws JRException {

        Connection conn = Connexiondb.connect();
        String sql = "INSERT INTO `facture` (`id_f`) VALUES (NULL);";
        int id_f = 0, id_c = 0;
        PreparedStatement start;
        try {
            start = conn.prepareStatement(sql);
            start.execute();
            sql = "SELECT id_f FROM facture";
            start = conn.prepareStatement(sql);
            ResultSet result = start.executeQuery();
            while (result.next()) {
                id_f = result.getInt("id_f");
            }
            sql = "INSERT INTO client(nom_client,prenom_client,tel_client) VALUES (?,?,?)";
            start = conn.prepareStatement(sql);
            start.setString(1, nom.getText());
            start.setString(2, prenom.getText());
            start.setInt(3, Integer.parseInt(tel.getText()));
            start.execute();
            start = conn.prepareStatement("SELECT id_client FROM client");
            result = start.executeQuery();
            while (result.next()) {
                id_c = result.getInt("id_client");
            }

            sql = "INSERT INTO commande(id_p,id_f,id_c,quantite,prixt,date_c) VALUES (?,?,?,?,?,?)";
            start = conn.prepareStatement(sql);
            start.setInt(1, id_produit);
            start.setInt(2, id_f);
            start.setInt(3, id_c);
            start.setInt(4, Integer.parseInt(qt_total.getText()));
            start.setInt(5, Integer.parseInt(col_total.getText()));
            start.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            start.execute();

            nom.clear();
            prenom.clear();
            tel.clear();
            tb_list_achat.getItems().clear();
            tb_list_montant.getItems().clear();
            col_total.clear();
            txt_prix.clear();
            qte_stock.clear();
            qt_total.clear();
            produit.getItems().clear();
            initialize(null, null);
            String report = "facture4.jrxml";
            JasperReport jas = JasperCompileManager.compileReport(report);
            JasperPrint print = JasperFillManager.fillReport(jas, null, Connexiondb.connect());
            JasperViewer.viewReport(print);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
