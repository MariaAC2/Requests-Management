package org.example.cereri;

public class TipCerere {
    public TipCerereEnum tipCerereEnum;

    public TipCerere(String tipCerere) {
        switch (tipCerere) {
            case "inlocuire buletin":
                this.tipCerereEnum = TipCerereEnum.INLOCUIRE_BULETIN;
                break;
            case "inregistrare venit salarial":
                this.tipCerereEnum = TipCerereEnum.INREGISTRARE_VENIT_SALARIAL;
                break;
            case "inlocuire carnet de sofer":
                this.tipCerereEnum = TipCerereEnum.INLOCUIRE_CARNET_DE_SOFER;
                break;
            case "inlocuire carnet de elev":
                this.tipCerereEnum = TipCerereEnum.INLOCUIRE_CARNET_DE_ELEV;
                break;
            case "creare act constitutiv":
                this.tipCerereEnum = TipCerereEnum.CREARE_ACT_CONSTITUTIV;
                break;
            case "reinnoire autorizatie":
                this.tipCerereEnum = TipCerereEnum.REINNOIRE_AUTORIZATIE;
                break;
            default:
                this.tipCerereEnum = TipCerereEnum.INREGISTRARE_CUPOANE_DE_PENSIE;
                break;
        }
    }

    public String toString() {
        String tipCerereStr = this.tipCerereEnum.toString();
        return tipCerereStr.toLowerCase().replace("_", " ");
    }
}