package org.example.functionari;

import java.io.*;

public class Functionar {
    private String numeFunctionar;
    private String numeFisierFunctionar;
    public File fisierFunctionar;

    public Functionar(String numeFunctionar, String outputFileName) throws IOException {
        this.numeFunctionar = numeFunctionar;
        this.numeFisierFunctionar = this.getNumeFisierFunctionar(outputFileName);
        this.fisierFunctionar = new File(numeFisierFunctionar);
        this.creareFisierFunctionar();
    }

    public String getNumeFunctionar() {
        return numeFunctionar;
    }

    public String getNumeFisierFunctionar(String outputFileName) {
        String str = outputFileName.substring(0, 26);
        str += "functionar_" + this.numeFunctionar + ".txt";
        String numeFisier = str;
        return numeFisier;
    }

    public void creareFisierFunctionar() throws IOException{
        try {
            if (!fisierFunctionar.createNewFile()) {
                this.stergereCereriFinalizate();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void scriereCereriFinalizate(String cerereFinalizata, String outputFileName) {
        try (FileWriter fw = new FileWriter(getNumeFisierFunctionar(outputFileName), true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(cerereFinalizata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stergereCereriFinalizate() throws IOException {
        try (FileWriter fw = new FileWriter(numeFisierFunctionar, false);
             PrintWriter pw = new PrintWriter(fw, false)) {
            pw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
