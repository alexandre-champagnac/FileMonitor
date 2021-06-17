package com.cjp.filemonitor.inputs.database;

import com.cjp.filemonitor.config.AppConfig;
import com.cjp.filemonitor.inputs.GlobalMonitor;

import java.io.IOException;
import java.sql.*;

public class DatabaseMonitor extends GlobalMonitor {

    private final AppConfig config;
    private Connection connex;

    public DatabaseMonitor(AppConfig config) {
        this.config = config;
    }


    @Override
    public void initialize() throws Exception {
        Connection connex = DriverManager.getConnection(config.getUrlDb(), config.getPseudoDb(), config.getPassword());
        this.connex = connex;
    }

    public DataBaseReport analyse(long deadline) throws SQLException, IOException {
        /*
        String query = "SELECT MAX(datespool), `from` FROM CPSUREPROXY.ALLMESSAGE WHERE `from` LIKE '%@apicrypt.fr'";
        /*/
        String query = "SELECT MAX(date) as OldestDate FROM simulentries";
        //*/
        PreparedStatement preparedStatement = connex.prepareStatement(query);
        ResultSet results = preparedStatement.executeQuery();
        Date date = null;


        results.next();
        date = results.getDate(1);




        if(date == null){
            return  new DataBaseReport(new java.util.Date(),config.getUrlDb(),false);

        }

        boolean isOldEntriesPresent = date.getTime() > deadline;
        return new DataBaseReport(date, config.getUrlDb(), isOldEntriesPresent);








    }

    @Override
    public void close() throws Exception {
        connex.close();
    }


}
