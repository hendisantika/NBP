package com.hendi.processor;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.hendi.model.NBP;
import com.hendi.model.NBPGroup;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.xml.bind.JAXBException;
import org.apache.log4j.Logger;

/**
 *
 * @author Senju Hashirama
 * @author hendisantika@gmail.com
 * @Last Up Date Tuesday, July 22nd 2014
 */
public class XMLProcessor {

    public static Properties prop = new Properties();
    public static InputStream input;
    public static Logger log = Logger.getLogger(XMLProcessor.class.getName());

    public void Jalankan() throws JAXBException, IOException, ParseException, InterruptedException {
        // reading data from a csv file and convert to java object  
//        File fileCSV = new File("src/com/hendi/resources/NBP_TestFile.csv");
        System.out.println("Reading data from csv and convert to java object:");

//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        // ReadCsv readCsv = new ReadCsv();
        // readCsv.readCsv();

//        String dir = "E:/Testing/CSV";
//        File file = new File(Config.dir);
        String dir = prop.getProperty("dir");
        String tmp = prop.getProperty("tmp");
        File file = new File(dir);
        String comp = ".completed";

        CsvToJavaObject csvToJavaObject = new CsvToJavaObject();
        List<NBP> hasil = csvToJavaObject.convertCsvToJava();
//        csvToJavaObject.PostData(hasil);
//        MoveFiles mv = new MoveFiles();
//        mv.MoveFiles();

        int counter = 0;
        long bobo = 1000;
        int jmlTrit = 400;
        for (int i = 0; i < hasil.size(); i++) {
            new Dodol(hasil.get(i)).start();
//            hasil.get(i).getCPName();
            
            counter++;
            
            if(counter % jmlTrit == 0){
//            if((counter2 == jmlTrit) || (counter2 == hasil.size() - 1)){
                System.out.println("Iterasi ke : " + i);
                Thread.sleep(bobo);
//                System.out.println("Jojon2 : " + i);
            }
        }
        
//        Request send = new Request();
//        send.PostData(hasil);

//        SAAJClient post = new SAAJClient();
//        post.PostData();
        NBPGroup grp = new NBPGroup();
        grp.setNBP(hasil);
//        MoveFiles mv = new MoveFiles();
//        mv.MoveFiles();

//    grp.setBps (hasil);
//        File dir = new File("E:/Testing/CSV");
//        File[] files = dir.listFiles();
        File[] listFiles = file.listFiles(new FileNameFilter().new MyFileNameFilter(comp));
        if (listFiles.length == 0) {
            // File tidak ditemukan
//            System.out.println(Config.dir + " Directory doesn't exists");
            log.info(dir + " Directory doesn't exists");
            log.info("Tidak ada file untuk diprocess!");
            log.info("Proses konversi ke XML gagal!");
            log.info("================================");
        } else {
            // File ditemukan
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss"); // NBP_yyyymmddhhmm
            Date now = new Date();
            String strDate = sdfDate.format(now);

//            for (int index = 0; index < listFiles.length; index++) {
//          File file = new File("E:\\Testing\\Results\\" + Hasil[index] + ".xml");
//                File fileXML = new File("E:/Testing/Results/NBP_" + index + "_" + strDate + ".xml");
//                File fileXML = new File("E:/Testing/Results/NBP_"+ strDate + ".xml");
//            File fileXML = new File(Config.Results + "NBP_" + strDate + ".xml");
            String results = prop.getProperty("Results");
            File fileXML = new File(results + "NBP_" + strDate + ".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(NBPGroup.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // writing to a file
            jaxbMarshaller.marshal(grp, fileXML);
            // writing to console
//            jaxbMarshaller.marshal(grp, System.out);

            log.info("Proses konversi ke XML sukses!");
            log.info("");

        }

    }

}
