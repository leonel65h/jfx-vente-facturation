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
public class Achat {

    String pdt;
    int montant;

    public String getPdt() {
        return pdt;
    }

    public void setPdt(String pdt) {
        this.pdt = pdt;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Achat(String pdt, int montant) {
        this.pdt = pdt;
        this.montant = montant;
    }

    public Achat() {
    }
}
