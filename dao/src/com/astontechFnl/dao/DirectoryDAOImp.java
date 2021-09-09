package com.astontechFnl.dao;

import com.astontechFnl.bo.Directory;
import com.astontechFnl.dao.mysql.MySQL;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDAOImp extends MySQL implements DirectoryDAO {
    @Override
    public Directory getDirectoryById(int directoryId) {
        Connect();
        Directory directory = null;
        try {
            String sp = "{call GetDirectory(?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_BY_ID);
            cStmt.setInt(2, directoryId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateObject(rs);
            }
            connection.close();
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directory;
    }


    public Directory getBiggestDir() {
        Connect();
        Directory directory = null;
        try {
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,Dir_Most_Files);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateObject(rs);
            }
            connection.close();
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

        return directory;
    }


    @Override
    public List<Directory> getDirectoryList() {
        Connect();
        List<Directory> directoryList = new ArrayList<Directory>();
        try {
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                directoryList.add(HydrateObject(rs));
            }
            connection.close();
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directoryList;
    }


    public Directory getLargest() {
        Connect();
        Directory directory = null;
        try {
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,Largest_Dir);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {

                directory = (HydrateObject(rs));
            }
            connection.close();
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directory;
    }

    @Override
    public int insertDirectory(Directory directory) {
        Connect();
        int id = 0;
        try {
            String sp = Execute_Dir;

            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2,0);
            cStmt.setString(3, directory.getDirName());
            cStmt.setLong(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getPath());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }
            connection.close();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return id;
    }

    @Override
    public boolean updateDirectory(Directory directory) {
        Connect();
        int id = 0;
        try {
            String sp = Execute_Dir;

            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2,directory.getDirectoryId());
            cStmt.setString(3, directory.getDirName());
            cStmt.setLong(4, directory.getDirSize());
            cStmt.setInt(5, directory.getNumberOfFiles());
            cStmt.setString(6, directory.getPath());

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }
            connection.close();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return id > 0;
    }

    @Override
    public boolean deleteDirectory(int directoryId) {
        Connect();
        int id = 0;
        try {
            String sp = Execute_Dir;

            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2,directoryId);
            cStmt.setString(3, "");
            cStmt.setInt(4, 0);
            cStmt.setInt(5, 0);
            cStmt.setString(6, "");
            cStmt.setInt(7, 0);

            ResultSet rs = cStmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }
            connection.close();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return id > 0;
    }

    public static Directory HydrateObject(ResultSet rs) throws SQLException{
        Directory directory = new Directory();

        directory.setDirectoryId(rs.getInt(1));
        directory.setDirName(rs.getString(2));
        directory.setDirSize(rs.getLong(3));
        directory.setNumberOfFiles(rs.getInt(4));
        directory.setPath(rs.getString(5));

        return directory;
    }
}
