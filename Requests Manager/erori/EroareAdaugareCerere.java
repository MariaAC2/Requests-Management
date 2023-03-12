package org.example.erori;
import java.io.*;

public class EroareAdaugareCerere extends Exception{
    public EroareAdaugareCerere(String tipUtilizator, String tipCerere) {
        super("Utilizatorul de tip " + tipUtilizator +
                " nu poate inainta o cerere de tip " + tipCerere);
    }
}
