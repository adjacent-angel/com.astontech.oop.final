package com.astontechFnl.dao;

import com.astontechFnl.bo.Directory;

import java.util.List;

public interface DirectoryDAO {

    // region GET METHODS
    public Directory getDirectoryById(int directoryId);
    public List<Directory> getDirectoryList();



    // endregion

    //region METHODS
    public int insertDirectory(Directory directory);
    public boolean updateDirectory(Directory directory);
    public boolean deleteDirectory(int directoryId);
    // endregion
}
