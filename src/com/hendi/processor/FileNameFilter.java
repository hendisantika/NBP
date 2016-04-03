/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hendi.processor;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Hendi Santika <hendisantika@gmail.com / hendisantika@yahoo.co.id >
 */
public class FileNameFilter {
    public void findFiles(String dir, String ext) {
        File file = new File(dir);
        if(!file.exists()) System.out.println(dir + " Directory doesn't exists");
        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        if(listFiles.length ==0){
            System.out.println(dir + " doesn't have any file with suffix "+ ext);
        }else{
            for(File f : listFiles)
                System.out.println("File: "+dir+File.separator+f.getName());
        }
    }
    
    public boolean found(String dir, String ext){
//        findFiles(dir, ext);
        boolean ada;
        File file = new File(dir);
        File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
        ada = listFiles.length != 0;
        return ada;
    }
 
    //FileNameFilter implementation
    public class MyFileNameFilter implements FilenameFilter{
         
//        private final String pre;
        private final String ext;
         
        public MyFileNameFilter(String ext){
            this.ext = ext.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
//            return name.toLowerCase().startsWith(pre);
//            return name.toLowerCase().startsWith(ext);
            return name.toLowerCase().endsWith(ext);
        }
         
    }
    
    public class ProcessFilter implements FilenameFilter{
         
//        private final String pre;
        private final String ext;
         
        public ProcessFilter(String ext){
            this.ext = ext.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
//            return name.toLowerCase().startsWith(pre);
            return name.toLowerCase().startsWith(ext);
//            return name.toLowerCase().endsWith(ext);
        }
         
    }
    
    public class FilterDelete implements FilenameFilter{
         
//        private final String pre;
        private final String pre;
         
        public FilterDelete(String pre){
            this.pre = pre.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
//            return name.toLowerCase().startsWith(pre);
//            return name.toLowerCase().startsWith(ext);
            return !name.toLowerCase().startsWith(pre);
        }
         
    }
    public class FilterNode implements FilenameFilter{
         
        private final String pre;
         
        public FilterNode(String pre){
            this.pre = pre.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
//            return name.toLowerCase().startsWith(pre);
//            return name.toLowerCase().startsWith(ext);
            return name.toLowerCase().endsWith(pre);
        }
         
    }
    
}
