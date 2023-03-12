package org.example.birouri;

import org.example.cereri.Cerere;
import org.example.functionari.Functionar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

public class Birou {
    private TreeSet<Cerere> cereriAsteptare;
    private ArrayList<Functionar> functionari;

    public Birou() {
        cereriAsteptare = new TreeSet<>(new Comparator<Cerere>() {
            @Override
            public int compare(Cerere o1, Cerere o2) {
                if (o1.getPrioritateCerere() < o2.getPrioritateCerere()) {
                    return 1;
                } else if (o1.getPrioritateCerere() == o2.getPrioritateCerere()) {
                    try {
                        return o1.obtineData().compareTo(o2.obtineData());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                return -1;
            }
        });

        functionari = new ArrayList<>();
    }

    public TreeSet<Cerere> getCereriAsteptare() {
        return cereriAsteptare;
    }

    public void setCereriAsteptare(TreeSet<Cerere> cereriAsteptare) {
        this.cereriAsteptare = cereriAsteptare;
    }

    public ArrayList<Functionar> getFunctionari() {
        return functionari;
    }

    public void setFunctionari(ArrayList<Functionar> functionari) {
        this.functionari = functionari;
    }

    public void adaugaCerere(Cerere cerere) {
        cereriAsteptare.add(cerere);
    }

    public void retrageCerere(String dataCererii) {
        Cerere cerere = null;
        for (Cerere c: cereriAsteptare) {
            if (c.getDataCererii().equals(dataCererii)) {
                cerere = c;
            }
        }
        cereriAsteptare.remove(cerere);
    }

    public Cerere getCerereAsteptare() {
        return cereriAsteptare.first();
    }

    public void rezolvaCerere(String numeFunctionar, String outputFileName) {
        Cerere cerere = cereriAsteptare.first();
        cereriAsteptare.remove(cerere);
        scrieCereriFinalizate(numeFunctionar, cerere, outputFileName);
    }

    public void afisareCereri(String outputFileName) {
        try (FileWriter fw = new FileWriter(outputFileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Cerere c: cereriAsteptare) {
                out.println(c.getPrioritateCerere() + " - " + c.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adaugaFunctionar(Functionar functionar) {
        functionari.add(functionar);
    }

    public void scrieCereriFinalizate(String numeFunctionar, Cerere cerere, String outputFileName) {
        Functionar functionar = null;
        for (Functionar f: functionari) {
            if(f.getNumeFunctionar().equals(numeFunctionar)) {
                functionar = f;
            }
        }

        functionar.scriereCereriFinalizate(cerere.getDataCererii() + " - " + cerere.getNumeUtilizator(), outputFileName);
    }
}
