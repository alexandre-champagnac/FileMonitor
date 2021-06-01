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
        ScanRepertoire(repertoireVise);
    }

    public static String ScanRepertoire(File repertoireCible) throws IOException {

        File[] Repertoire = repertoireCible.listFiles(); //Place chaque fichiers du répertoire dans un tableau

        long keyint = getIntervalFromConfig("C:\\Users\\achampag\\IdeaProjects\\file-monitor\\src\\main\\java\\param.txt");


        boolean presenceVieuxFichiers = false;
        String presenceVieuxFichiersString = "";
        long vielleDate = keyint * 60 * 1000;


        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        long currentDateConverted = currentDate.getTime();

        File plusVieuxFichier = null;

        int cptNbrFichiers = 0;


        for (File f : Repertoire) {

            if ((f.lastModified() + vielleDate) < currentDateConverted) {
                if(plusVieuxFichier == null){
                    plusVieuxFichier = f;
                }
                if(f.lastModified() < plusVieuxFichier.lastModified()){
                    plusVieuxFichier= f;
                }


                presenceVieuxFichiers = true;
                cptNbrFichiers++;

            }

        }

        if (presenceVieuxFichiers) {
            presenceVieuxFichiersString = "Oui";
        } else {
            presenceVieuxFichiersString = "Non";
        }

        return "Presence de vieux fichiers  : " + presenceVieuxFichiersString + ". Si oui : Il y'en a : " + cptNbrFichiers +" et " + (Repertoire.length - cptNbrFichiers) + " fichiers récents"+ " Le plus vieux ficher est : " + plusVieuxFichier + " et date de :" + plusVieuxFichier.lastModified() ;
    }


    public static long getIntervalFromConfig(String path) throws IOException {

        Ini iniFile = new Ini(new File(path));
        String key = iniFile.get("global", "intervalInMinute");
        long intervalInMinute = Long.parseLong(key.trim());
        return intervalInMinute;

    }

}
