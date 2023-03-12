package org.example.instructiuni;
import jdk.jshell.execution.Util;
import org.example.birouri.Birou;
import org.example.birouri.CladireBirouri;
import org.example.cereri.Cerere;
import org.example.functionari.Functionar;
import org.example.utilizatori.*;

import javax.print.attribute.HashDocAttributeSet;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Instructiune {
    private String[] instr;

    public Instructiune(String[] instr) {
        this.instr = instr;
    }

    public String getComanda() {
        return this.instr[0];
    }

    public void adaugaFunctionar(CladireBirouri cladireBirouri, String outputFileName) throws IOException {
        Functionar functionar = new Functionar(instr[2], outputFileName);
        cladireBirouri.adaugaFunctionar(instr[1], functionar);
    }

    public void adaugaUtilizator(ArrayList<Utilizator> utilizatori) {
        try {
            Utilizator utilizator;
            switch (instr[1]) {
                case "persoana":
                    utilizator = new Persoana(instr[2]);
                    break;
                case "angajat":
                    utilizator = new Angajat(instr[2], instr[3]);
                    break;
                case "pensionar":
                    utilizator = new Pensionar(instr[2]);
                    break;
                case "elev":
                    utilizator = new Elev(instr[2], instr[3]);
                    break;
                case "entitate juridica":
                    utilizator = new EntitateJuridica(instr[2], instr[3]);
                    break;
                default:
                    throw new Exception("Eroare, tipul nu corespunde");
            }
            utilizatori.add(utilizator);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void adaugaCerere(ArrayList<Utilizator> utilizatori, CladireBirouri cladireBirouri, String outputFileName) {
        String numeCreator = instr[1];
        Utilizator creator = null;

        for (Utilizator u : utilizatori) {
            if (u.getNume().equals(numeCreator)) {
                creator = u;
            }
        }

        assert creator != null;
        Cerere cerere = new Cerere(creator.getTextCerere(), numeCreator, instr[2], instr[3], instr[4]);
        creator.adaugaCerere(cerere, outputFileName);

        if (creator.verificaAdaugareCerere(cerere) != null) {
            String tipUtilizator = creator.getTipUtilizator();
            cladireBirouri.adaugaCerere(tipUtilizator, cerere);
        }
    }

    public void retrageCerere(ArrayList<Utilizator> utilizatori, CladireBirouri cladireBirouri) {
        String numeCreator = instr[1];
        Utilizator creator = null;

        for (Utilizator u : utilizatori) {
            if (u.getNume().equals(numeCreator)) {
                creator = u;
            }
        }

        assert creator != null;
        creator.retrageCerere(instr[2]);
        String tipUtilizator = creator.getTipUtilizator();
        cladireBirouri.retrageCerere(tipUtilizator, instr[2]);
    }

    public void rezolvaCerere(ArrayList<Utilizator> utilizatori, CladireBirouri cladireBirouri, String outputFileName) {
        Cerere cerere = cladireBirouri.getCerereAsteptare(instr[1]);
        cladireBirouri.rezolvaCerere(instr[1], instr[2], outputFileName);

        String numeCreator = cerere.getNumeUtilizator();
        Utilizator creator = null;

        for (Utilizator u : utilizatori) {
            if (u.getNume().equals(numeCreator)) {
                creator = u;
            }
        }

        assert creator != null;
        creator.rezolvaCerere(cerere);
    }

    public void afiseazaCereri(CladireBirouri cladireBirouri, String outputFileName) {
        cladireBirouri.afiseazaCereri(instr[1], outputFileName);
    }

    public void afiseazaCereriAsteptare(ArrayList<Utilizator> utilizatori, String outputFileName) {
        String numeCreator = instr[1];
        Utilizator creator = null;

        for (Utilizator u : utilizatori) {
            if (u.getNume().equals(numeCreator)) {
                creator = u;
            }
        }

        assert creator != null;
        creator.afiseazaCereriAsteptare(outputFileName);
    }

    public void afiseazaCereriFinalizate(ArrayList<Utilizator> utilizatori, String outputFileName) {
        Utilizator creator = null;

        for (Utilizator u : utilizatori) {
            if (u.getNume().equals(instr[1])) {
                creator = u;
            }
        }

        assert creator != null;
        creator.afiseazaCereriFinalizate(outputFileName);
    }
}
