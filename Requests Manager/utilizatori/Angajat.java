package org.example.utilizatori;

import org.example.cereri.Cerere;
import org.example.cereri.TipCerereEnum;
import org.example.erori.EroareAdaugareCerere;

public class Angajat extends Utilizator {
    private String numeCompanie;
    public Angajat(String numeUtilizator, String numeCompanie) {
        super(numeUtilizator);
        this.numeCompanie = numeCompanie;
    }

    public String getNumeCompanie() {
        return numeCompanie;
    }

    public void setNumeCompanie(String numeCompanie) {
        this.numeCompanie = numeCompanie;
    }

    @Override
    public String getTextCerere() {
        return "Subsemnatul " + super.nume +
                ", angajat la compania " + this.numeCompanie +
                ", va rog sa-mi aprobati urmatoarea solicitare: ";
    }

    @Override
    public void adaugare(Cerere cerere) throws EroareAdaugareCerere {
        if (cerere.getTipCerere().tipCerereEnum == TipCerereEnum.INLOCUIRE_BULETIN ||
                cerere.getTipCerere().tipCerereEnum == TipCerereEnum.INLOCUIRE_CARNET_DE_SOFER ||
                cerere.getTipCerere().tipCerereEnum == TipCerereEnum.INREGISTRARE_VENIT_SALARIAL) {
            super.cereriAsteptare.add(cerere);
        } else {
            throw new EroareAdaugareCerere("angajat", cerere.getTipCerere().toString());
        }
    }

    @Override
    public String getTipUtilizator() {
        return "angajat";
    }
}
