package com.astontechFnl.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Directory extends BaseBO{
    //region PROPERTIES
    private int DirectoryId;
    private String DirName;
    private long DirSize;
    private int NumberOfFiles;
    private String Path;

    //endregion

    //region CONSTRUCTORS
    public Directory() {}


    //endregion

    //region GETTERS / SETTERS

    public int getDirectoryId() {
        return DirectoryId;
    }

    public void setDirectoryId(int directoryId) {
        DirectoryId = directoryId;
    }

    public String getDirName() {
        return DirName;
    }

    public void setDirName(String dirName) {
        DirName = dirName;
    }

    public long getDirSize() {
        return DirSize;
    }

    public void setDirSize(long dirSize) {
        DirSize = dirSize;
    }

    public int getNumberOfFiles() {
        return NumberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        NumberOfFiles = numberOfFiles;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }


    //endregion

    @Override
    public String toString() {
        return "Directory{" +
                "DirectoryId=" + DirectoryId +
                ", DirName='" + DirName + '\'' +
                ", DirSize=" + DirSize +
                ", NumberOfFiles=" + NumberOfFiles +
                ", Path='" + Path + '\'' +
                '}';
    }
}
