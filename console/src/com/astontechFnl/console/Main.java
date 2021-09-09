package com.astontechFnl.console;

import com.astontechFnl.bo.DataFile;
import com.astontechFnl.bo.Directory;
import com.astontechFnl.dao.DataFileDAO;
import com.astontechFnl.dao.DataFileDAOImp;
import com.astontechFnl.dao.DirectoryDAO;
import com.astontechFnl.dao.DirectoryDAOImp;
import com.astontechFnl.dao.mysql.MySQL;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.CallableStatement;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    final static Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {
        Menu();
    }



        public static void Menu() {
            boolean flag1 = true;
            boolean flag2 = true;

            while(flag1) {
                Scanner read = new Scanner(System.in);
                System.out.println("Select Starting Directory");
                String dir = read.nextLine();

                filler(new java.io.File(dir));


                while (flag2) {
                    Scanner reader2 = new Scanner(System.in);
                    System.out.println("<<<<<<<<<<<>>>>>>>>>>");
                    System.out.println("1) Display directory with most files:" + "\n" +
                            "2) Display directory largest in size: " + "\n" +
                            "3) Display five largest files in size: " + "\n" +
                            "4) Display all files of a certain type: " + "\n" +
                            "5) Clear the database and start over: " + "\n" +
                            "6) Exit");
                    System.out.println("<<<<<<<<<<<>>>>>>>>>>");
                    System.out.println("Select an Option: ");

                    DirectoryDAOImp directoryDAO = new DirectoryDAOImp();
                    DataFileDAOImp dataFileDAO = new DataFileDAOImp();

                    int menuChoice = reader2.nextInt();
                    if (menuChoice == 1) {
                        System.out.println("==================");
                        Directory directory = directoryDAO.getBiggestDir();
                            System.out.println("Directory with most files: <" + directory.getDirName() + " > Number of files: " + directory.getNumberOfFiles());
                        System.out.println("==================");
                    } else if (menuChoice == 2) {
                        System.out.println("==================");
                        Directory directory = directoryDAO.getLargest();
                            System.out.println("Directory with largest size: <" + directory.getDirName() + " > Number of files: " + directory.getDirSize());
                        System.out.println("==================");
                    } else if (menuChoice == 3) {
                        System.out.println("==================");
                        List<DataFile> dataFileList = dataFileDAO.getFiveLargest();
                        System.out.println("5 biggest files: \n");
                        for (DataFile dataFile : dataFileList) {
                            System.out.println("File Name: " + dataFile.getFileName() + "\n" + "   File size: " + dataFile.getFileSize() + "\n");
                            System.out.println("==== ==== ==== ===");
                        }

                    }
//                    else if (menuChoice == 4) {
//                        System.out.println("Enter file type: ");
//                        Scanner reader3 = new Scanner(System.in);
//                        String fileType = reader3.nextLine();
//
//                        System.out.println("Files of file type " + type);

                    }
                }
            }


        }


    public static void filler(java.io.File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {

                if (file.isDirectory()) {
                    Directory directory = new Directory();
                    directory.setDirectoryId(0);
                    directory.setDirName(file.getName());
                    directory.setDirSize(file.length());
                    directory.setNumberOfFiles(file.listFiles().length);
                    directory.setPath(file.getPath());

                    DirectoryDAO directoryDAO = new DirectoryDAOImp();
                    int dirId = directoryDAO.insertDirectory(directory);

                    filler(file);

                } else {

                    DataFile dataFile = new DataFile();
                    dataFile.setFileId(0);
                    dataFile.setFileName(file.getName());
                    dataFile.setFileType(file.getPath().substring(file.getPath().lastIndexOf(".") + 1));
                    dataFile.setFileSize(file.length());
                    dataFile.setPath(file.getPath());
//                    dataFile.setDirId(file.);

                    DataFileDAO dataFileDAO = new DataFileDAOImp();
                    int fileId = dataFileDAO.insertFile(dataFile);
                }
            }
        } catch (Exception ioEx) {
            logger.error(ioEx);
        }

    }



}

