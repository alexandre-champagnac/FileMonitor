package com.cjp.filemonitor.filescanner;

import com.cjp.filemonitor.inputs.DirectoryMonitor;
import org.ini4j.Ini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;


public class MainApp {

    public static String ScanRepertoire(File repertoireCible) throws IOException {
        File[] repertoire = repertoireCible.listFiles(); //Place chaque fichiers du répertoire dans un tableau
        if (repertoire == null){
            throw new FileNotFoundException("Le repertoire n'existe pas " + repertoireCible);
        }
        long keyint = getIntervalFromConfig("C:\\Users\\achampag\\IdeaProjects\\file-monitor\\src\\main\\java\\param.ini");


        boolean presenceVieuxFichiers = false;

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        long currentDateConverted = currentDate.getTime();

        long limite = currentDateConverted - (keyint * 60 * 1000);


        File plusVieuxFichier = null;

        int oldFileCounter = 0;




        String presenceVieuxFichiersString = "";

        if (presenceVieuxFichiers) {
            presenceVieuxFichiersString = "Oui";
        } else {
            presenceVieuxFichiersString = "Non";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Presence de vieux fichiers  : ")
                .append(presenceVieuxFichiersString)
                .append(". Si oui : Il y'en a : ")
                .append(oldFileCounter)
                .append(" et ")
                .append(repertoire.length - oldFileCounter)
                .append(" fichiers récents Le plus vieux ficher est : ");

        if(plusVieuxFichier == null){
            builder.append(plusVieuxFichier)
                    .append(" et date de :")
                    .append(plusVieuxFichier.lastModified());
        }


        return builder.toString();

    }


    /**
     * @param path Path of the ini file
     * @return The value assigned to "IntervalInMinute" of the ini file.
     * @throws IOException for the INI file
     */
    public static long getIntervalFromConfig(String path) throws IOException {

        Ini iniFile = new Ini(new File(path));
        String key = iniFile.get("global", "intervalInMinute");
        long intervalInMinute = Long.parseLong(key.trim());
        return intervalInMinute;

    }

    public static String getStringFromConfig(String path, String section, String option) throws IOException {
        Ini iniFile = new Ini(new File(path));
        String var= iniFile.get(section,option);
        return var;
    }






}
