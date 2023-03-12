package org.example.utilizatori;

import org.example.cereri.Cerere;
import org.example.cereri.TipCerereEnum;
import org.example.erori.EroareAdaugareCerere;

public class Pensionar extends Utilizator {
    public Pensionar(String numeUtilizator) {
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
                cerere.getTipCerere().tipCerereEnum == TipCerereEnum.INLOCUIRE_CARNET_DE_SOFER ||
                cerere.getTipCerere().tipCerereEnum == TipCerereEnum.INREGISTRARE_CUPOANE_DE_PENSIE) {
            super.cereriAsteptare.add(cerere);
        } else {
            throw new EroareAdaugareCerere("pensionar", cerere.getTipCerere().toString());
        }
    }

    @Override
    public String getTipUtilizator() {
        return "pensionar";
    }
}
