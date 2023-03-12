package org.example.cereri;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;

public class Cerere {
    private String textCerere;
    private String numeUtilizator;
    private String dataCererii;
    private TipCerere tipCerere;
    private int prioritateCerere;

    public Cerere(String textCerere, String numeUtilizator, String tipCerere, String dataCererii, String prioritateCerere) {
        this.textCerere = textCerere;
        this.numeUtilizator = numeUtilizator;
        this.dataCererii = dataCererii;
        this.tipCerere = new TipCerere(tipCerere);
        this.prioritateCerere = Integer.parseInt(prioritateCerere);
    }

    public String getTextCerere() {
        return textCerere;
    }

    public void setTextCerere(String textCerere) {
        this.textCerere = textCerere;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }
    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getDataCererii() {
        return dataCererii;
    }

    public void setDataCererii(String dataCererii) {
        this.dataCererii = dataCererii;
    }

    public TipCerere getTipCerere() {
        return tipCerere;
    }
    public void setTipCerere(TipCerere tipCerere) {
        this.tipCerere = tipCerere;
    }

    public int getPrioritateCerere() {
        return this.prioritateCerere;
    }

    public void setPrioritateCerere(int prioritateCerere) {
        this.prioritateCerere = prioritateCerere;
    }

    public String toString() {
        return this.dataCererii + " - " + this.textCerere + this.tipCerere.toString();
    }

    public Date obtineData() throws ParseException {
        String str = this.dataCererii;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        return format.parse(str);
    }
}