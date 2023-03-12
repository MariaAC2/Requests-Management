package org.example.utilizatori;
import org.example.cereri.Cerere;

import org.example.erori.EroareAdaugareCerere;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.*;

public abstract class Utilizator {
    protected String nume;
    protected TreeSet<Cerere> cereriAsteptare;
    protected ArrayList<Cerere> cereriFinalizate;

    public Utilizator(String nume) {
        this.nume = nume;
        this.cereriAsteptare = new TreeSet<>(new Comparator<Cerere>() {
            @Override
            public int compare(Cerere o1, Cerere o2) {
                try {
                    return o1.obtineData().compareTo(o2.obtineData());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        this.cereriFinalizate = new ArrayList<>();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public TreeSet<Cerere> getCereriAsteptare() {
        return cereriAsteptare;
    }

    public void setCereriAsteptare(TreeSet<Cerere> cereriAsteptare) {
        this.cereriAsteptare = cereriAsteptare;
    }

    public ArrayList<Cerere> getCereriFinalizate() {
        return cereriFinalizate;
    }

    public void setCereriFinalizate(ArrayList<Cerere> cereriFinalizate) {
        this.cereriFinalizate = cereriFinalizate;
    }

    public abstract String getTextCerere();

    abstract void adaugare(Cerere cerere) throws EroareAdaugareCerere;

    public abstract String getTipUtilizator();

    public void adaugaCerere(Cerere cerere, String outputFileName) {
        try {
            this.adaugare(cerere);
        } catch (EroareAdaugareCerere e1) {
            try (FileWriter fw = new FileWriter(outputFileName, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(e1.getMessage());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public Cerere verificaAdaugareCerere(Cerere cerere) {
        for (Cerere c: cereriAsteptare) {
            if (cerere.equals(c)) {
                return c;
            }
        }

        return null;
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

    public void rezolvaCerere(Cerere cerere) {
        cereriAsteptare.remove(cerere);
        cereriFinalizate.add(cerere);
    }

    public void afiseazaCereriAsteptare(String outputFileName) {
        try (FileWriter fw = new FileWriter(outputFileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(nume + " - cereri in asteptare:");
            for (Cerere c: cereriAsteptare) {
                out.println(c.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afiseazaCereriFinalizate(String outputFileName) {
        try (FileWriter fw = new FileWriter(outputFileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(nume + " - cereri in finalizate:");
            for (Cerere c: cereriFinalizate) {
                out.println(c.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
