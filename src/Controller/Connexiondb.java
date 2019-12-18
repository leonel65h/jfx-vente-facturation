/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Add_produit;
import Model.Admin_add;
import Model.Admin_user;
import Model.Produit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.StringSeqHelper;

/**
 *
 * @author leonel65
 */
//Connexion a la base de donnee
public class Connexiondb {

    private static final String URL = "jdbc:mysql://localhost:3306/boutique";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return cnx;
        } catch (ClassNotFoundException | SQLException e) {
        }

        return null;
    }

    //ajouter dans la base de donnee
    public static int Ajouter(Admin_add info) {

        int list = 0;
        try {
            String sql = "INSERT INTO users(nom,prenom,adresse,role) VALUES(?,?,?,?)";
            Connection connecter;
            connecter = connect();
            PreparedStatement start;
            start = connecter.prepareStatement(sql);
            start.setString(1, info.getNom());
            start.setString(2, info.getPrenom());
            start.setString(3, info.getAdresse());
            start.setString(4, info.getRole());
            list = start.executeUpdate();
            connecter.close();
        } catch (SQLException e) {
        }
        return list;

    }

    public static int Ajouter1(Add_produit info) {

        int list = 0;
        try {
            String sql = "INSERT INTO produit(designation,qte,pu) VALUES(?,?,?)";
            Connection connecter;
            connecter = connect();
            PreparedStatement start;
            start = connecter.prepareStatement(sql);
            start.setString(1, info.getDesignation());
            start.setInt(2, info.getQuantite());
            start.setInt(3, info.getPu());
            list = start.executeUpdate();
            connecter.close();
        } catch (SQLException e) {
        }
        return list;

    }

    //ajouter dans la base de donnee
    public static int update(Admin_add info) {

        int list = 0;
        try {
            String sql;
            sql = "UPDATE  users SET nom = ?, prenom = ?, adresse=?, role=? WHERE id_user=?";
            Connection connecter;
            connecter = connect();
            PreparedStatement start;
            start = connecter.prepareStatement(sql);
            start.setString(1, info.getNom());
            start.setString(2, info.getPrenom());
            start.setString(3, info.getAdresse());
            start.setString(4, info.getRole());
            start.setInt(5, info.getNumero());
            list = start.executeUpdate();
            connecter.close();
        } catch (SQLException e) {
        }
        return list;

    }

    public static Admin_add recherche(int id) {
        Admin_add user = new Admin_add();

        try {

            String sql = "SELECT * FROM users WHERE id_user=? AND role!=4 AND role!=1";
            Connection conn = Connexiondb.connect();
            PreparedStatement start;
            start = conn.prepareStatement(sql);
            start.setInt(1, id);
            ResultSet result = start.executeQuery();
            if (result.next()) {
                user.setNumero(result.getInt(1));
                user.setNom(result.getString(2));
                user.setPrenom(result.getString(3));
                user.setAdresse(result.getString(5));
                user.setRole(result.getString(7));
            }
            conn.close();
        } catch (SQLException ex) {

        }
        return user;

    }

    public static int delete(int id) {

        int list = 0;
        try {
            String sql;
            sql = "DELETE FROM  users  WHERE id_user=?";
            Connection connecter;
            connecter = connect();
            PreparedStatement start;
            start = connecter.prepareStatement(sql);
            start.setInt(1, id);
            list = start.executeUpdate();
            connecter.close();
        } catch (SQLException e) {
        }
        return list;

    }

    public static int Ajouter1(Produit info) {

        int list = 0;
        try {
            String sql = "INSERT INTO produit_comm(qte_cmd) VALUES(?)";
            Connection connecter;
            connecter = connect();
            PreparedStatement start;
            start = connecter.prepareStatement(sql);
            start.setInt(1, info.getQte());
            list = start.executeUpdate();
            connecter.close();
        } catch (SQLException e) {
        }
        return list;

    }

}
