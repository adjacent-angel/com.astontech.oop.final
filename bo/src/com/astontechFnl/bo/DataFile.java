package com.astontechFnl.bo;

public class DataFile {
    //region PROPERTIES
    private int FileId;
    private String FileName;
    private String FileType;
    private Long FileSize;
    private String Path;
    private int DirId;
    //endregion

    //region CONSTRUCTORS
    public DataFile() {}


    //endregion

    //region GETTERS / SETTERS

    public int getFileId() {
        return FileId;
    }

    public void setFileId(int fileId) {
        this.FileId = fileId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        this.FileName = fileName;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        this.FileType = fileType;
    }

    public long getFileSize() {
        return FileSize;
    }

    public void setFileSize(long fileSize) {
        FileSize = fileSize;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        this.Path = path;
    }

    public int getDirId() {
        return DirId;
    }

    public void setDirId(int dirId) {
        DirId = dirId;
    }
//endregion

}
