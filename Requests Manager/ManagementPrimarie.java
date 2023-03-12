package org.example;

import org.example.birouri.CladireBirouri;
import org.example.instructiuni.Instructiune;
import org.example.utilizatori.Utilizator;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;

public class ManagementPrimarie {
    public static void main(String[] args) throws IOException, ParseException {
        String inputFileName = "src/main/resources/input/" + args[0];
        String outputFileName = "src/main/resources/output/" + args[0];

        File outputFile = new File(outputFileName);
        try {
            if (!outputFile.createNewFile()) {
                try (FileWriter fw = new FileWriter(outputFileName, false);
                     PrintWriter pw = new PrintWriter(fw, false)) {
                    pw.flush();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Utilizator> utilizatori = new ArrayList<>();
        CladireBirouri cladireBirouri = new CladireBirouri();

        try (FileReader fileReader = new FileReader(inputFileName);
             BufferedReader objReader = new BufferedReader(fileReader)){
            String line;
            while ((line = objReader.readLine()) != null) {
                // print line
                Instructiune instructiune = new Instructiune(line.split("; "));
                String numeInstructiune = instructiune.getComanda();

                switch (numeInstructiune) {
                    case "adauga_functionar":
                        System.out.println("111");
                        instructiune.adaugaFunctionar(cladireBirouri, outputFileName);
                        break;
                    case "adauga_utilizator":
                        instructiune.adaugaUtilizator(utilizatori);
                        break;
                    case "cerere_noua":
                        instructiune.adaugaCerere(utilizatori, cladireBirouri, outputFileName);
                        break;
                    case "retrage_cerere":
                        instructiune.retrageCerere(utilizatori, cladireBirouri);
                        break;
                    case "rezolva_cerere":
                        System.out.println("111");
                        instructiune.rezolvaCerere(utilizatori, cladireBirouri, outputFileName);
                        break;
                    case "afiseaza_cereri":
                        instructiune.afiseazaCereri(cladireBirouri, outputFileName);
                        break;
                    case "afiseaza_cereri_in_asteptare":
                        instructiune.afiseazaCereriAsteptare(utilizatori, outputFileName);
                        break;
                    default:
                        System.out.println("111");
                        instructiune.afiseazaCereriFinalizate(utilizatori, outputFileName);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}