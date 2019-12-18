/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author leonel65
 */
public class Add_produit {

    String designation;
    int quantite, pu;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPu() {
        return pu;
    }

    public void setPu(int pu) {
        this.pu = pu;
    }

    public Add_produit(String designation, int quantite, int pu) {
        this.designation = designation;
        this.quantite = quantite;
        this.pu = pu;
    }

    public Add_produit() {
    }
}
