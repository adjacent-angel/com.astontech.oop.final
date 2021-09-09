package com.astontechFnl.dao;

import com.astontechFnl.bo.DataFile;

import java.util.List;

public interface DataFileDAO {
    // region GET METHODS
    public DataFile getFileById(int fileId);
    public List<DataFile> getFileList();
    public List<DataFile> getFiveLargest();
    // endregion

    //region METHODS
    public int insertFile(DataFile dataFile);
    public boolean updateDirectory(DataFile dataFile);
    public boolean deleteDirectory(int fileId);
    // endregion
}
