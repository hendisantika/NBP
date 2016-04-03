/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.processor;

import com.hendi.model.NBP;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hendisantika
 */
public class Dodol extends Thread{
    private final NBP list;

    public Dodol(NBP list) {
        this.list = list;
    }
    
    public void run(){
        try {
            new Request().PostData2(list);
        } catch (IOException ex) {
            Logger.getLogger(Dodol.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
