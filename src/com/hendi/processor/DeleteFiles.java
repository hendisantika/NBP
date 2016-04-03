/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hendi Santika <hendisantika@gmail.com / hendisantika@yahoo.co.id >
 */
public class DeleteFiles {

    public static Properties prop = new Properties();
    public static InputStream input;
    public static final Logger log = Logger.getLogger(DeleteFiles.class.getName());

    public void DeleteFiles() throws FileNotFoundException, IOException {
//        String dir = Config.dir;
//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        String dir = prop.getProperty("dir");

        String pre = "NBP";
        File file = new File(dir);

        File[] listFiles = file.listFiles(new FileNameFilter().new FilterDelete(pre));

        for (File listFile : listFiles) {
            boolean status = listFile.delete();
            if (status) {
                log.info("File " + listFile + " deleted successfully!");
            } else {
                log.info("File " + listFile + " does not exist!");
            }
        }

    }

    public void DeleteNode() throws FileNotFoundException, IOException {
//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
//        String dir = Config.dirIO;
        String dirIO = prop.getProperty("dirIO");
//        String pre = ".csv";
        File file = new File(dirIO);

//        File[] listFiles = file.listFiles(new FileNameFilter().new FilterNode(pre));
        File[] listFiles = file.listFiles();

        for (File listFile : listFiles) {
            boolean success = listFile.delete();
            if (success) {
                log.info("File " + listFile + " deleted successfully!");
            } else {
                log.info("File " + listFile + " does not exist!");
            }
        }

    }

    public void DeleteAll() throws FileNotFoundException, IOException {
        //Create File which you want to delete
//        File file = new File("myfile.txt");
//        String dir = "E:/Testing/A";
//        String dir = "E:/Testing/IO File";
//        String pre = "Node";
//        File file = new File(dir);
//        File[] listFiles = file.listFiles(new FileNameFilter().new MyFileNameFilter(pre));
//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
//        String dir = Config.dirIO;
        String dirIO = prop.getProperty("dirIO");
//        String pre = ".csv";
        File file = new File(dirIO);
        File[] listFiles = file.listFiles();
        for (File listFile : listFiles) {
            boolean status = listFile.delete();
            if (status) {
                log.log(Level.INFO, "File {0} deleted successfully!!", listFile);
            } else {
                log.log(Level.INFO, "File {0} does not exists", listFile);
            }
        }

    }

}
