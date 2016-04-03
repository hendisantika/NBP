/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.main;

import static com.hendi.main.Main.input;
import static com.hendi.main.Main.prop;
import com.hendi.processor.MoveFiles;
import com.hendi.processor.RenameFile;
import com.hendi.processor.XMLProcessor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author hendisantika
 */
public class JobApp implements Job {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(JobApp.class.getName());

    /**
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        try {

            input = new FileInputStream("/apps/nbp/conf/config.properties");
            // load a properties file
            prop.load(input);

            String csv = prop.getProperty("dir");

            File file = new File(csv);
            File[] lstFile = file.listFiles();

            if (lstFile.length > 0) {
                try {
                    Calendar cal = Calendar.getInstance();
                    DateFormat df = DateFormat.getTimeInstance(DateFormat.LONG);

                    String mulai = df.format(cal.getTime());

                    // Create Stop watch
                    long lStartTime = System.currentTimeMillis();

                    // App Main Program
                    final XMLProcessor app = new XMLProcessor();

                    RenameFile rf = new RenameFile();
                    rf.RenameTmp();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JobApp.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    rf.RenameProcess();
                    try {
                        app.Jalankan();
                    } catch (JAXBException | InterruptedException ex) {
                        Logger.getLogger(JobApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JobApp.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    MoveFiles mv = new MoveFiles();
                    mv.MoveFiles();

//            input = new FileInputStream("config.properties");
                    // load a properties file
//            prop.load(input);
                    long lEndTime = System.currentTimeMillis();
                    long milliseconds = lEndTime - lStartTime;

                    int seconds = (int) ((milliseconds / 1000) % 60);
                    int minutes = (int) ((milliseconds / 1000) / 60);
                    int hours = (int) (minutes / 60);
                    System.out.println("=======================================");
                    log.info("=======================================");
                    System.out.println("Start   : " + mulai);
                    log.info("Start   : " + mulai);
                    cal = Calendar.getInstance();
                    System.out.println("End     : " + df.format(cal.getTime()));
                    log.info("End     : " + df.format(cal.getTime()));
                    System.out.println("Elapsed : " + hours + " Hour(s) " + minutes + " Minute(s) " + seconds + " Second(s)");
                    log.info("Elapsed : " + hours + " Hour(s) " + minutes + " Minute(s) " + seconds + " Second(s)");
                    System.out.println("=======================================");
                    log.info("=======================================");

                } catch (ParseException | IOException ex) {
                    Logger.getLogger(JobApp.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("Getting Files .... " + dateFormat.format(date));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JobApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JobApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
