/**
 *
 * @author Senju Hashirama
 * @author hendisantika@gmail.com
 * @Last Up Date Tuesday, July 22nd 2014
 */
package com.hendi.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Senju Hashirama
 */
public class RenameFile {

    Properties prop = new Properties();
    InputStream input;
    FileNameFilter filter = new FileNameFilter();
    
    
//    String dir = "E:/Testing/CSV";
//    String dir = "/apps/nbp/csv/";
//    String csv = ".csv";
    String csv = "NBP";
    String temp = ".tmp";
    String pro = ".toprocess";
    String comp = ".completed";
//     String temp = Config.temp;
//     String pro = Config.pro;
//     String comp = Config.comp;

//    public void Rename() throws ParseException {
////        File myDir = new File("E:/Testing/CSV/");
//        File myDir = new File("/apps/nbp/csv/");
////        File myDir = new File(dir);
//
//        File[] myFiles = myDir.listFiles();
//
//        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss"); // NBP_yyyymmddhhmm
//        Date now = new Date();
//        String strDate = sdfDate.format(now);
//
////        if (myDir.exists() && !myDir.isDirectory()) {
//        if (myDir.exists()) {
//            System.out.println("Checking the files .....");
//            for (int i = 0; i < myFiles.length; i++) {
//                // Set Lastmodified
//                Date newDate = sdfDate.parse(strDate);
//                myFiles[i].setLastModified(newDate.getTime());
//
////                System.out.println("Processing file: " + myDir.getAbsolutePath()
////                        + "\\" + myFiles[i]);
//                System.out.println("Processing file: " + myFiles[i]);
//
//                File oldFile = myFiles[i];
//
////                File newFile = new File("E:/Testing/CSV/NBP_" + i + "_" + strDate + ".csv");
//                File newFile = new File(dir + "NBP_" + i + "_" + strDate + ".csv");
//
//                System.out.println("......The file will be renamed to " + newFile);
//
//                boolean isFileRenamed = oldFile.renameTo(newFile);
//
//                if (isFileRenamed) {
//                    System.out.println("......File has been renamed");
//                } else {
//                    System.out.println("......Error renaming the file");
//                }
//            }
//        } else {
//            System.out.println("CSV File not found!");
//            System.out.println("The directory is empty!");
//        }
//
//    }

    public void RenameTmp() throws ParseException, FileNotFoundException, IOException {
//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        
        String dir = prop.getProperty("dir");

        File myDir = new File(dir);
        File file = new File(dir);
        File[] listFiles = file.listFiles(new FileNameFilter().new ProcessFilter(csv));

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS"); // NBP_yyyymmddhhmm
        Date now = new Date();
        String strDate = sdfDate.format(now);

//        if (myDir.exists() && !myDir.isDirectory()) {
        if (myDir.exists()) {
            System.out.println("Checking the files .....");
            for (File listFile : listFiles) {
                // Get filename
                String name = listFile.getName();
                String filename = name.substring(0, name.length() - 4);
                // Set Lastmodified
                String kapan = sdfDate.format(listFile.lastModified());
                Date newDate = sdfDate.parse(strDate);
                listFile.setLastModified(newDate.getTime());
                System.out.println("Processing file: " + listFile); // myDir.getAbsolutePath()
                File oldFile = listFile;
//                File newFile = new File("E:/Testing/CSV/NBP_Temp" + i + ".csv");
//                File newFile = new File("E:/Testing/CSV/" + filename + ".tmp");
                File newFile = new File(dir + filename + ".tmp");
//                File newFile = new File("/apps/nbp/csv/" + filename + ".tmp");
                System.out.println("......The file will be renamed to " + newFile);
                System.out.println("Last Modified : " + kapan);
                boolean isFileRenamed = oldFile.renameTo(newFile);
                if (isFileRenamed) {
                    System.out.println("......File has been renamed");
                } else {
                    System.out.println("......Error renaming the file");
                }
            }
        } else {
            System.out.println("CSV File not found!");
            System.out.println("The directory is empty!");
        }

    }

    public void RenameProcess() throws ParseException, FileNotFoundException, IOException {
//        File myDir = new File("E:/Testing/CSV/");
//        String dir = prop.getProperty("dir");
//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        String dir = prop.getProperty("dir");
        File myDir = new File(dir);
//        File myDir = new File("/apps/nbp/csv/");

        File file = new File(dir);
        if (!file.exists()) {
            System.out.println(dir + " Directory doesn't exists");
        }
        File[] listFiles = file.listFiles(new FileNameFilter().new MyFileNameFilter(temp));

        boolean ada;
        ada = listFiles.length != 0;

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS"); // NBP_yyyymmddhhmm
        Date now = new Date();
        String strDate = sdfDate.format(now);

//        if (myDir.exists() && !myDir.isDirectory()) {
        if (myDir.exists() && ada == true) {
            System.out.println("Checking the files .....");
            for (File listFile : listFiles) {
                // Get filename
                String name = listFile.getName();
                String filename = name.substring(0, name.length() - 4);
                // Set Lastmodified
                String kapan = sdfDate.format(listFile.lastModified());
                Date newDate = sdfDate.parse(strDate);
                listFile.setLastModified(newDate.getTime());
//                System.out.println("Processing file: " + myDir.getAbsolutePath()
//                        + "\\" + myFiles[i]);
                System.out.println("Processing file: " + listFile);
//                File oldFile = myFiles[i];
                File oldFile = listFile;
//                File newFile = new File("E:/Testing/CSV/" + filename + ".toprocess");
                File newFile = new File(dir + filename + ".toprocess");
//                File newFile = new File("/apps/nbp/csv/" + filename + ".toprocess");
//                File newFile = new File("E:/Testing/CSV/NBP_Process_" + i + ".csv");
                System.out.println("......The file will be renamed to " + newFile);
                System.out.println("Last Modified : " + kapan);
                boolean isFileRenamed = oldFile.renameTo(newFile);
                if (isFileRenamed) {
                    System.out.println("......File has been renamed");
                } else {
                    System.out.println("......Error renaming the file");
                }
            }
        } else {
            System.out.println("CSV File not found!");
            System.out.println("The directory is empty!");
        }

    }

    public void RenameComplete() throws ParseException, FileNotFoundException, IOException {
        input = new FileInputStream("conf/config.properties");
//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        // load a properties file
        prop.load(input);
        String dir = prop.getProperty("dir");
        File myDir = new File(dir);

//        String pro = "NBP_Process";

        File file = new File(dir);
        if (!file.exists()) {
            System.out.println(dir + " Directory doesn't exists");
        }
        File[] listFiles = file.listFiles(new FileNameFilter().new MyFileNameFilter(pro));

        boolean ada;
        ada = listFiles.length != 0;

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss"); // NBP_yyyymmddhhmm
        Date now = new Date();
        String strDate = sdfDate.format(now);

//        if (myDir.exists() && !myDir.isDirectory()) {
        if (myDir.exists() && ada == true) {
            System.out.println("Checking the files .....");
            for (File listFile : listFiles) {
                // Get filename
                String name = listFile.getName();
                String filename = name.substring(0, name.length() - 10);
                // Set Lastmodified
                Date newDate = sdfDate.parse(strDate);
                listFile.setLastModified(newDate.getTime());
                System.out.println("Processing file: " + listFile);
                File oldFile = listFile;
                File newFile = new File(dir + filename + "_" + strDate + ".completed");
//                File newFile = new File("E:/Testing/CSV/" + filename + "_" + strDate + ".completed");
//                File newFile = new File("/apps/nbp/csv/" + filename + "_"+ strDate +".completed");
//                File newFile = new File("E:/Testing/CSV/NBP_Completed" + i + "_"+ strDate +"csv");
                System.out.println("......The file will be renamed to " + newFile);
                boolean isFileRenamed = oldFile.renameTo(newFile);
                if (isFileRenamed) {
                    System.out.println("......File has been renamed");
                } else {
                    System.out.println("......Error renaming the file");
                }
            }
        } else {
            System.out.println("CSV File not found!");
            System.out.println("The directory is empty!");
        }

    }

}
