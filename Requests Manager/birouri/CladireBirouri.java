package org.example.birouri;

import org.example.cereri.Cerere;
import org.example.functionari.Functionar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CladireBirouri {
    private Map<String, Birou> birouri;

    public CladireBirouri() {
        birouri = new HashMap<>();
        birouri.put("persoana", new Birou());
        birouri.put("angajat", new Birou());
        birouri.put("pensionar", new Birou());
        birouri.put("elev", new Birou());
        birouri.put("entitate juridica", new Birou());
    }

    public void adaugaCerere(String tipUtilizator, Cerere cerere) {
        birouri.get(tipUtilizator).adaugaCerere(cerere);
    }

    public void retrageCerere(String tipUtilizator, String dataCererii) {
        birouri.get(tipUtilizator).retrageCerere(dataCererii);
    }

    public void adaugaFunctionar(String tipUtilizator, Functionar functionar) {
        birouri.get(tipUtilizator).adaugaFunctionar(functionar);
    }

    public void rezolvaCerere(String tipUtilizator, String numeFunctionar, String outputFileName) {
        birouri.get(tipUtilizator).rezolvaCerere(numeFunctionar, outputFileName);
    }

    public Cerere getCerereAsteptare(String tipUtilizator) {
        return birouri.get(tipUtilizator).getCerereAsteptare();
    }
    public void afiseazaCereri(String tipUtilizator, String outputFileName) {
        try (FileWriter fw = new FileWriter(outputFileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(tipUtilizator + " - cereri in birou:");
        } catch (IOException e) {
            e.printStackTrace();
        }

        birouri.get(tipUtilizator).afisareCereri(outputFileName);
    }
}
