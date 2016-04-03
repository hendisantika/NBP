/**
 *
 * @author Senju Hashirama
 * @author hendisantika@gmail.com
 * @Last Up Date Tuesday, July 22nd 2014
 */
package com.hendi.main;

import com.hendi.processor.XMLProcessor;
import com.hendi.processor.MoveFiles;
import com.hendi.processor.RenameFile;
import it.sauronsoftware.cron4j.Scheduler;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Senju Hashirama
 */
public class Quickstart {

    public static Properties prop = new Properties();
    public static InputStream input;
    static final Logger log = Logger.getLogger(
            Quickstart.class.getName());

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        // Delete All Files in the path
//        DeleteFiles delete = new DeleteFiles();
//        delete.DeleteAll();

        // Creates a Scheduler instance.
        Scheduler s = new Scheduler();

        // Config file forn scheduler / crontab
//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");

        // Create Stop watch
        long lStartTime = System.currentTimeMillis();

        // load a properties file
        prop.load(input);

        // App Main Program
        final XMLProcessor app = new XMLProcessor();

        // Schedule a once-a-minute task.
        s.schedule("* * * * *", new Runnable() {
            @Override
            public void run() {
                log.info("Checking the existing files .....");
                System.out.println("Checking the existing files .....");
                try {
                    // Jalankan scenario program
                    // 1. Rename Filename
                    RenameFile rf = new RenameFile();
                    try {
                        rf.RenameTmp();
                        try {
                            Thread.sleep(2000);
                            rf.RenameProcess();
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            log.log(Level.INFO, "Error : {0}", ex.getMessage());
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(Quickstart.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // 2. Convert CSV to XML
                    app.Jalankan();
                    // 3. Move file
                    try {
                        Thread.sleep(2000);
                        MoveFiles mv = new MoveFiles();
                        mv.MoveFiles();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        log.log(Level.INFO, "Error : {0}", ex.getMessage());
                    }

                } catch (JAXBException | IOException | ParseException ex) {
                    Logger.getLogger(Quickstart.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Quickstart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Starts the scheduler.
        s.start();
        // Will run for 1 minutes.
        try {

//            input = new FileInputStream("config.properties");
            // load a properties file
//            prop.load(input);
//            int x = Integer.parseInt(prop.getProperty("minutes"));
            int x = Integer.parseInt(prop.getProperty("cron"));

            // get the property value and print it out
//            System.out.println(prop.getProperty("database"));
//            Thread.sleep( (1000L - x) * 60L * 1L);
            Thread.sleep(1000L * 60L * 1L);
            log.info("");
            log.info("Checking the existing files on the directory .....");
        } catch (InterruptedException e) {
            log.log(Level.INFO, "Error : {0}", e.getMessage());
            ;
        }
        // Stops the scheduler.
        s.stop();
        log.info("Done executing program!");
        log.info("Process Success\n");
        long lEndTime = System.currentTimeMillis();

        long milliseconds = lEndTime - lStartTime;

        int seconds = (int) ((milliseconds / 1000) % 60);
        int minutes = (int) ((milliseconds / 1000) / 60);
        int hours = (int) (minutes / 60);

        log.log(Level.INFO, "Elapsed Times : {0} hours {1} minutes {2} seconds", new Object[]{hours, minutes, seconds});

//        DeleteFiles delete = new DeleteFiles();
//        delete.DeleteFiles();
//        delete.DeleteAll();
    }

}
