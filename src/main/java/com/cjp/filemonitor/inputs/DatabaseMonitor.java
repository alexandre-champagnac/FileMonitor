package com.cjp.filemonitor.inputs;
import com.cjp.filemonitor.config.AppConfig;
import com.cjp.filemonitor.config.ConfigLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;

public class DatabaseMonitor implements GlobalMonitor {

    private AppConfig config;
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




        String query = "SELECT MAX(date) as OldestDate FROM simulentries";
        PreparedStatement preparedStatement = connex.prepareStatement(query);
        ResultSet results = preparedStatement.executeQuery();
        Date date = null;

        while(results.next()){
            date = results.getDate(1);
        }

        boolean isOldEntriesPresent = false;
        if(date.getTime() > deadline){
            isOldEntriesPresent = true;
        }


        DataBaseReport reportdb = new DataBaseReport(date, config.getUrlDb(),isOldEntriesPresent);

        return reportdb;

    }

    @Override
    public void close() throws Exception {
        //TODO: implement me
    }


}
