package com.cjp.filemonitor.inputs;
import java.sql.*;

public class DatabaseMonitor implements GlobalMonitor {


    public Connection createConnection() throws SQLException {

        Connection connex = DriverManager.getConnection("jdbc:mysql://localhost:3306/cjp_databasetest?useSSL=false", "root", "");
        return connex;

    }



    public DataBaseReport analyse(long deadline) throws SQLException {


        Connection connex = createConnection();

        DatabaseMetaData metaData = connex.getMetaData();
        String product_name = metaData.getURL();

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


        DataBaseReport reportdb = new DataBaseReport(date,product_name,isOldEntriesPresent);

        return reportdb;

    }

}
