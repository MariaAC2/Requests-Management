package org.example.utilizatori;

import org.example.cereri.Cerere;
import org.example.cereri.TipCerereEnum;
import org.example.erori.EroareAdaugareCerere;

public class Persoana extends Utilizator {
    public Persoana(String numeUtilizator) {
        super(numeUtilizator);
    }

    @Override
    public String getTextCerere() {
        return "Subsemnatul " + super.nume +
                ", va rog sa-mi aprobati urmatoarea solicitare: ";
    }

    @Override
    void adaugare(Cerere cerere) throws EroareAdaugareCerere {
        if (cerere.getTipCerere().tipCerereEnum == TipCerereEnum.INLOCUIRE_BULETIN ||
                cerere.getTipCerere().tipCerereEnum == TipCerereEnum.INLOCUIRE_CARNET_DE_SOFER) {
            super.cereriAsteptare.add(cerere);
        } else {
            throw new EroareAdaugareCerere("persoana", cerere.getTipCerere().toString());
        }
    }

    @Override
    public String getTipUtilizator() {
        return "persoana";
    }
}
