package com.astontechFnl.dao;

import com.astontechFnl.bo.DataFile;
import com.astontechFnl.dao.mysql.MySQL;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataFileDAOImp extends MySQL implements DataFileDAO {
    @Override
    public DataFile getFileById(int fileId) {
        Connect();
        DataFile dataFile = null;
        try {
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_BY_ID);
            cStmt.setInt(2, fileId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                dataFile = HydrateObject(rs);
            }
            connection.close();
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return dataFile;
    }

    @Override
    public List<DataFile> getFileList() {
        Connect();
        List<DataFile> fileList = new ArrayList<DataFile>();
        try {
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                fileList.add(HydrateObject(rs));
            }
            connection.close();
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return fileList;
    }


    public List<DataFile> getFiveLargest() {
        Connect();
        List<DataFile> fileList = new ArrayList<DataFile>();
        try {
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1,Largest_Files);
            cStmt.setInt(2, 0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                fileList.add(HydrateObject(rs));
            }
            connection.close();
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return fileList;
    }

    @Override
    public int insertFile(DataFile dataFile) {
        Connect();
        int id = 0;
        try {
            String sp = Execute_File;

            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2,0);
            cStmt.setString(3, dataFile.getFileName());
            cStmt.setString(4, dataFile.getFileType());
            cStmt.setLong(5, dataFile.getFileSize());
            cStmt.setString(6, dataFile.getPath());
            cStmt.setInt(7, dataFile.getDirId());

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
    public boolean updateDirectory(DataFile dataFile) {
        Connect();
        int id = 0;
        try {
            String sp = Execute_File;

            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2,dataFile.getFileId());
            cStmt.setString(3, dataFile.getFileName());
            cStmt.setString(4, dataFile.getFileType());
            cStmt.setLong(5, dataFile.getFileSize());
            cStmt.setString(6, dataFile.getPath());
            cStmt.setInt(7, dataFile.getDirId());

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
    public boolean deleteDirectory(int fileId) {
        Connect();
        int id = 0;
        try {
            String sp = Execute_File;

            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, fileId);
            cStmt.setString(3, "");
            cStmt.setString(4, "");
            cStmt.setLong(5, 0);
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

    private static DataFile HydrateObject(ResultSet rs) throws SQLException {
        DataFile dataFile = new DataFile();

        dataFile.setFileId(rs.getInt(1));
        dataFile.setFileName(rs.getString(2));
        dataFile.setFileType(rs.getString(3));
        dataFile.setFileSize(rs.getLong(4));
        dataFile.setPath(rs.getString(5));
        dataFile.setDirId(rs.getInt(6));

        return dataFile;
    }
}
