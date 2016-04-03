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
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Senju Hashirama
 */
public class MoveFiles {

    FileNameFilter filter = new FileNameFilter();
//        String dir = Config.dir;
    
    Properties prop = new Properties();
    InputStream input;
    String comp = ".completed";
    private final static Logger log = Logger.getLogger(MoveFiles.class.getName());
    
    public void MoveFiles() throws FileNotFoundException, IOException {
//        File src = new File(Config.dir);
//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        String dir = prop.getProperty("dir");   
        File src = new File(dir);
//        File dest = new File("E:/Testing/Back-Up/");
//        File dest = new File(Config.dest);
//        File tmp = new File(prop.getProperty("tmp"));
        File dest = new File(prop.getProperty("dest"));
        
        File fileMove = new File(dir);
        if (!fileMove.exists()) {
            log.info(dir + " Directory doesn't exists");
        }
        File[] listFiles = fileMove.listFiles(new FileNameFilter().new MyFileNameFilter(comp));
        
        if (listFiles.length == 0) { // File tidak ditemukan
            log.info("Tidak ada file yg bernama NBP_Completed!");
            log.info("File tidak jadi dipindahkan!");
            log.info("Jumlah File : " + listFiles.length);
            System.out.println("Tidak ada file yg bernama NBP_Completed!");
            System.out.println("File tidak jadi dipindahkan!");
            System.out.println("Jumlah File : " + listFiles.length);
        } else {
            // File ditemukan
            try {
                log.info("");
                System.out.println("");
                log.info("Moving File process .....");
                System.out.println("Moving File process .....");
                Thread.sleep(2000);                 // 1000 milliseconds is one second.
                filter.findFiles(dir, comp);
                boolean cek = filter.found(dir, comp);
                if (cek == true) {
                    for (File listFile : listFiles) {
//                        filter.findFiles(dir, comp);
//                        for (File file : files) {
//                    File sourceFile = new File("E:/Testing/CSV/"+ files[i].getName() );
//                    File destinationDir = new File("E:/Testing/Back-Up/"+ files[i].getName());
                        System.out.println(listFile.getName());
                        log.info(listFile.getName());
//                            System.out.println(src + "\\" + listFiles[i].getName());
//                        System.out.println(src + "/" + listFile.getName());
//                      files[i].renameTo(dest + files[i].getName());
//                            String x = src + "\\" + listFiles[i].getName();
                        String x = src + "/" + listFile.getName();
//                            String y = dest + "\\" + listFiles[i].getName();
                        String y = dest + "/" + listFile.getName();
                        File f1 = new File(x);
                        f1.renameTo(new File(y));
                        System.out.println("Moving file succees!");
                        System.out.println("Jumlah : " + listFiles.length);
                        System.out.println("");
//                        }
                    }
                    
                } else {
                    log.info("Tidak ada");
                }
//                    filter.findFiles(dir, pro);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                log.error("Error : " + ex.getMessage());
            }
            
        }
    }
    public void MoveTmp() throws FileNotFoundException, IOException {
//        File src = new File(Config.dir);
//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        String tmp = prop.getProperty("tmp");   
        File src = new File(tmp);
//        File dest = new File("E:/Testing/Back-Up/");
//        File dest = new File(Config.dest);
        File dest = new File(prop.getProperty("dest"));
        
        File fileMove = new File(tmp);
        if (!fileMove.exists()) {
            log.info(tmp + " Directory doesn't exists");
        }
        File[] listFiles = fileMove.listFiles(new FileNameFilter().new MyFileNameFilter(comp));
        
        if (listFiles.length == 0) { // File tidak ditemukan
            log.info("Tidak ada file yg bernama NBP_Completed!");
            log.info("File tidak jadi dipindahkan!");
            log.info("Jumlah File : " + listFiles.length);
        } else {
            // File ditemukan
            try {
                log.info("");
                log.info("Moving File process .....");
                Thread.sleep(2000);                 // 1000 milliseconds is one second.
                filter.findFiles(tmp, comp);
                boolean cek = filter.found(tmp, comp);
                if (cek == true) {
                    for (int i = 0; i < listFiles.length; i++) {
//                        filter.findFiles(dir, comp);
//                        for (File file : files) {
//                    File sourceFile = new File("E:/Testing/CSV/"+ files[i].getName() );
//                    File destinationDir = new File("E:/Testing/Back-Up/"+ files[i].getName());
                        System.out.println(listFiles[i].getName());
//                            System.out.println(src + "\\" + listFiles[i].getName());
                        System.out.println(src + "/" + listFiles[i].getName());
//                      files[i].renameTo(dest + files[i].getName());
//                            String x = src + "\\" + listFiles[i].getName();
                        String x = src + "/" + listFiles[i].getName();
//                            String y = dest + "\\" + listFiles[i].getName();
                        String y = dest + "/" + listFiles[i].getName();
                        File f1 = new File(x);
                        f1.renameTo(new File(y));
                        log.info("Moving file succees!");
                        log.info("Jumlah : " + listFiles.length);
                        log.info("");
//                        }
                    }
                    
                } else {
                    log.info("Tidak ada");
                }
//                    filter.findFiles(dir, pro);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                log.error("Error : " + ex.getMessage());
            }
            
        }
    }
}
