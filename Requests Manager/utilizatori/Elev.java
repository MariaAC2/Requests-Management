package org.example.utilizatori;

import org.example.cereri.Cerere;
import org.example.cereri.TipCerereEnum;
import org.example.erori.EroareAdaugareCerere;

public class Elev extends Utilizator {
    private String numeScoala;

    public Elev(String numeUtilizator, String scoala) {
        super(numeUtilizator);
        this.numeScoala = scoala;
    }

    public String getNumeScoala() {
        return numeScoala;
    }

    public void setNumeScoala(String numeScoala) {
        this.numeScoala = numeScoala;
    }

    @Override
    public String getTextCerere() {
        return "Subsemnatul " + super.nume +
                ", elev la scoala " + this.numeScoala +
                ", va rog sa-mi aprobati urmatoarea solicitare: ";
    }

    @Override
    void adaugare(Cerere cerere) throws EroareAdaugareCerere {
        if (cerere.getTipCerere().tipCerereEnum == TipCerereEnum.INLOCUIRE_BULETIN ||
                cerere.getTipCerere().tipCerereEnum == TipCerereEnum.INLOCUIRE_CARNET_DE_ELEV) {
            super.cereriAsteptare.add(cerere);
        } else {
            throw new EroareAdaugareCerere("elev", cerere.getTipCerere().toString());
        }
    }

    @Override
    public String getTipUtilizator() {
        return "elev";
    }
}
