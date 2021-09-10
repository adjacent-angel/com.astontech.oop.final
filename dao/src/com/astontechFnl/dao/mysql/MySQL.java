package com.astontechFnl.dao.mysql;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQL {
    protected final static Logger logger = Logger.getLogger(MySQL.class);

    // region CONNECTION INFO
    protected static String dbHost = "localhost";
    protected static String dbName = "Final";
    protected static String dbUser = "root";
    protected static String dbPass = "Lizard42#";
    protected static String useSSL = "false";
    protected static String procBod = "true";

    protected static Connection connection = null;
    // endregion

    // region CRUD QUERY NUMBERS
    protected static final int GET_BY_ID = 10;
    protected static final int GET_COLLECTION = 20;
    protected static final int Dir_Most_Files = 30;
    protected static final int Largest_Dir = 40;

    protected static final int INSERT = 10;
    protected static final int UPDATE = 20;
    protected static final int DELETE = 30;


    protected static final int Largest_Files = 30;
    protected static final int Truncate = 50;


    // endregion

    // region STORED PROCEDURES
    protected static final String Execute_Dir = "{call ExecuteDir(?,?,?,?,?,?)}";
    protected static final String Execute_File = "{call ExecuteFile(?,?,?,?,?,?,?)}";

    // endregion

    // region CONNECTION
    protected static void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            logger.error("MySQL Driver not found " + ex);
        }
        logger.info("MySQL driver registered");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" + dbName + "?useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod, dbUser, dbPass);
        } catch (
                SQLException ex) {
            logger.error("Connection failed" + ex);
        }

        if (connection != null) {
            logger.info("Successfully connected to SQL database");
        } else {
            logger.info("Connection failed");
        }
    }
    // endregion
}
