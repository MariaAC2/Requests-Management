package org.example.utilizatori;

import org.example.cereri.Cerere;
import org.example.cereri.TipCerereEnum;
import org.example.erori.EroareAdaugareCerere;

public class EntitateJuridica extends Utilizator {
    private String numeReprezentant;

    public EntitateJuridica(String numeUtilizator, String numeReprezentant) {
        super(numeUtilizator);
        this.numeReprezentant = numeReprezentant;
    }

    public String getNumeReprezentant() {
        return numeReprezentant;
    }

    public void setNumeReprezentant(String numeReprezentant) {
        this.numeReprezentant = numeReprezentant;
    }

    @Override
    public String getTextCerere() {
        return "Subsemnatul " + this.numeReprezentant +
                ", reprezentant legal al companiei " + super.nume +
                ", va rog sa-mi aprobati urmatoarea solicitare: ";
    }

    @Override
    void adaugare(Cerere cerere) throws EroareAdaugareCerere {
        if (cerere.getTipCerere().tipCerereEnum == TipCerereEnum.CREARE_ACT_CONSTITUTIV ||
                cerere.getTipCerere().tipCerereEnum == TipCerereEnum.REINNOIRE_AUTORIZATIE) {
            super.cereriAsteptare.add(cerere);
        } else {
            throw new EroareAdaugareCerere("entitate juridica", cerere.getTipCerere().toString());
        }
    }

    @Override
    public String getTipUtilizator() {
        return "entitate juridica";
    }
}
