/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;
import javax.xml.bind.JAXBException;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author hendisantika
 */
public class Main {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Main.class.getName());
    public static Properties prop = new Properties();
    public static InputStream input;
    
    public static void main(String args[]) throws ParseException, IOException,
            InterruptedException, JAXBException, SchedulerException {
         input = new FileInputStream("/apps/nbp/conf/config.properties");
        // load a properties file
        prop.load(input);

        int x = Integer.parseInt(prop.getProperty("cron"));
    	JobDetail job = JobBuilder.newJob(JobApp.class)
		.withIdentity("NBPJob", "group1").build();
 
    	Trigger trigger = TriggerBuilder
		.newTrigger()
		.withIdentity("CSTTrigger", "group1")
		.withSchedule(
//			CronScheduleBuilder.cronSchedule("0/"+ x + " * * * * ?"))
			CronScheduleBuilder.cronSchedule("0 0/"+ x + " * * * ?"))
		.build();
 
    	//schedule it
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);

    }
}
