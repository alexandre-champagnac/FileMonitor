package com.cjp.filemonitor.filescanner;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;


public class MainApp {

    public static void main(String[] args) throws IOException {
        boolean test = true;


        File repertoireVise = new File("C:\\Users\\achampag\\IdeaProjects\\file-monitor\\src\\main\\java\\Sara");
        File fichierDeReference = new File("C:\\Users\\achampag\\IdeaProjects\\file-monitor\\src\\main\\java\\Sara\\fichierDeReference.txt");
        ScanRepertoire(repertoireVise, fichierDeReference);
    }

    public static void ScanRepertoire(File repertoireCible, File fichierDeReference) throws IOException {

        File[] Repertoire = repertoireCible.listFiles(); //Place chaque fichiers du répertoire dans un tableau

        Ini ini = new Ini(new File("C:\\Users\\achampag\\IdeaProjects\\file-monitor\\src\\main\\java\\param.ini"));
        String key = ini.get("header", "key");
        long keyint = Long.parseLong(key.trim());


        System.out.println("Voici la liste complète des fichiers du dossier:"); //Affichage du tableau
        for (File path : Repertoire) {
            System.out.println(path);
        }


        boolean presenceVieuxFichiers = false;
        long vielleDate = keyint * 60 * 1000;

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        long currentDateConverted = currentDate.getTime();

        File plusVieuxFichier = null;
        int cptNbrFichiers = 0;
        ArrayList<File> listeVieuxFichiers = new ArrayList<File>();

        if (Repertoire != null) {

            for (File f : Repertoire) {
                if ((f.lastModified() + vielleDate) < currentDateConverted) {
                    plusVieuxFichier = f;
                    presenceVieuxFichiers = true;
                    cptNbrFichiers++;
                    listeVieuxFichiers.add(f);
                }

            }
            System.out.print("Y'a t'il des vieux fichiers ? ");

            if (presenceVieuxFichiers) {
                System.out.println("Oui");
            } else {
                System.out.println("Non");
            }

            System.out.println("Il y a " + cptNbrFichiers + " vieux fichiers dans le répertoire");
            System.out.println("En voici la liste :");

            if (listeVieuxFichiers != null) {
                for (File file : listeVieuxFichiers) {
                    System.out.println(file);
                }
            } else {
                System.out.println("Il n'y en à pas");
            }

        }
    }
}
