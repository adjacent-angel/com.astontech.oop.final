package com.astontechFnl.dao;

import com.astontechFnl.bo.DataFile;

import java.util.List;

public interface DataFileDAO {
    // region GET METHODS
    public DataFile getFileById(int fileId);
    public List<DataFile> getFileTypeList(String fileExt);
    public List<DataFile> getFiveLargest();
    // endregion

    //region METHODS
    public int insertFile(DataFile dataFile);
    public boolean updateDataFile(DataFile dataFile);
    public boolean deleteDataFile(int fileId);
    // endregion
}
