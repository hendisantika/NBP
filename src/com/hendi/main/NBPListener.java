/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.main;

import it.sauronsoftware.cron4j.SchedulerListener;
import it.sauronsoftware.cron4j.TaskExecutor;

/**
 *
 * @author hendisantika
 */
public class NBPListener implements SchedulerListener {

    @Override
    public void taskLaunching(TaskExecutor executor) {
        System.out.println("Task launched!");
    }

    @Override
    public void taskSucceeded(TaskExecutor executor) {
        System.out.println("Task completed!");
    }

    public void taskFailed(TaskExecutor executor, Throwable exception) {
        System.out.println("Task failed due to an exception!");
    }

}
