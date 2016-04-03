package com.hendi.processor;

import com.hendi.main.Config;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hendi.model.NBP;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.apache.log4j.Logger;

/**
 *
 * @author Senju Hashirama
 * @author hendisantika@gmail.com
 * @Last Up Date Tuesday, July 22nd 2014
 */
public class CsvToJavaObject {
    // 1000 = 1 Detik atau 10000 = 10 Detik
    // 300000 = 5 Menit

    FileNameFilter filter = new FileNameFilter();

//    String comp = ".completed";
    String comp = Config.comp;
    public static Properties prop = new Properties();
    public static InputStream input;
    public final static Logger log = Logger.getLogger(CsvToJavaObject.class.getName());

    @SuppressWarnings("finally")
    public List<NBP> convertCsvToJava() throws IOException, ParseException {

//        input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);

        int index = 0;
        int counter = 0;

        BufferedReader br = null;
        String line;
        String splitBy = "\\|";
        List<NBP> nbpList = new ArrayList<>();

        // Attribut Write CSV to File
//        String path = "E:/Testing/IO File/Node.csv";
//        String path = prop.getProperty("node");
//        String path = "/apps/nbp/io/";
//        FileWriter writer = null;
//        writer = new FileWriter(path, true);
//        String dir = "E:/Testing/CSV";
//        String dir = Config.dir;
        String dir = prop.getProperty("dir");
        String tmp = prop.getProperty("tmp");
//        String pro = "NBP_Process";
//        String pro = ".toprocess";
        String pro = ".toprocess";

//        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // NBP_yyyymmddhhmm
        Date now = new Date();
        String strDate = sdfDate.format(now);

//        File direktori = new File("E:/Testing/CSV");
//        File[] files = direktori.listFiles(new FileNameFilter().new MyFileNameFilter(pro));
        File file = new File(dir);
        File[] listFiles = file.listFiles(new FileNameFilter().new MyFileNameFilter(pro));

        if (listFiles.length > 0) {
            for (File listFile : listFiles) {
                try {
                    String name1 = listFile.getName();
//                    String filename = name1.substring(0, name1.length() - 4);
                    String filename = name1.substring(0, name1.lastIndexOf("."));
                    br = new BufferedReader(new FileReader(listFile.toString()));
                    br.readLine();
                    while ((line = br.readLine()) != null) {

                        // split on comma(',' or '|')  
                        String[] nbp = line.split(splitBy);

                        // create NBP object to store values  
                        NBP nbpObject = new NBP();

                        // for(int i = 0; i < nbpList.size(); i++) {
                        // add values from csv to nbp object  
//                        nbpObject.setSourceID(files[index].getName());  // Diambil dari nama file yg diupload
                        nbpObject.setSourceID(filename);  // Diambil dari nama file yg diupload
                        nbpObject.setTransactionID(filename + "_" + nbp[10] + "_" + strDate);  // SourceID + MSISDN + Timestamp (sd milisecond));
                        nbpObject.setMSISDN(nbp[10]);  // SourceID + MSISDN + Timestamp (sd milisecond)
                        nbpObject.setServiceID(nbp[8]);
                        nbpObject.setSenderAddress(nbp[10]);
                        nbpObject.setRecipientAddress(nbp[11]);
                        nbpObject.setChargedAddress(nbp[12]);
                        nbpObject.setUSSDDirectCode(nbp[15]);
                        nbpObject.setMessageContentType(nbp[16]);
                        nbpObject.setMessagingType(nbp[17]);
                        nbpObject.setCPName(nbp[37]);
                        nbpObject.setTariffPrice(nbp[41]);
                        nbpObject.setServiceType(nbp[42]);
                        nbpObject.setCity(nbp[2]);

                        // adding nbp objects to a list  
                        nbpList.add(nbpObject);
//                        for (int i = 0; i < nbpList.size(); i++) {
//                            writer = new FileWriter(path, true);
//                            
//                            writer.write(nbpList.get(i).getSourceID());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getTransactionID());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getMSISDN());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getServiceID());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getSenderAddress());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getRecipientAddress());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getChargedAddress());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getUSSDDirectCode());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getMessageContentType());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getMessagingType());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getCPName());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getTariffPrice());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getServiceType());
//                            writer.write("|");
//                            writer.write(nbpList.get(i).getCity());
//                            writer.write("|");
//                            writer.write("\r\n");
//                            
//                        }
//                        writer.flush();
//                        writer.close();

                    } // Block While
                    // print values stored in nbpList  
//                    printnbpList(nbpList);
//                    writer.flush();
//                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (counter == 99) {
                    new Request(nbpList).start();
                    counter = 0;
                    nbpList.clear();
                }
                counter++;
                index++;
                if (index == nbpList.size()) {
                    break;
                }

            }//end-for

        } else {
            log.info("The Directory is empty!");
            log.info("File not found!");
            log.info("Processing file cannot be done!");
            log.info("Please check the file, make sure it is existed!");

        }

        try {
            RenameFile rf = new RenameFile();
            Thread.sleep(2000);
            rf.RenameComplete();

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            log.info("Error : " + ex.getMessage());
        }

        return nbpList;

    }

    public void printnbpList(List<NBP> nbpListToPrint) {
        for (int i = 0; i < nbpListToPrint.size(); i++) {
            System.out.println("NBP - CDDS [" + i + "]-[SourceID = " + nbpListToPrint.get(i).getSourceID()
                    + ", TransactionID  = " + nbpListToPrint.get(i).getTransactionID()
                    + ", MSISDN = " + nbpListToPrint.get(i).getMSISDN()
                    + ", Service Id = " + nbpListToPrint.get(i).getServiceID()
                    + ", Sender Address (A Party) = " + nbpListToPrint.get(i).getSenderAddress()
                    + ", Recipient Address ( B Party) = " + nbpListToPrint.get(i).getRecipientAddress()
                    + ", Charged Address = " + nbpListToPrint.get(i).getChargedAddress()
                    + ", USSD Direct code = " + nbpListToPrint.get(i).getUSSDDirectCode()
                    + ", message content type = " + nbpListToPrint.get(i).getMessageContentType()
                    + ", Messaging Type = " + nbpListToPrint.get(i).getMessagingType()
                    + ", CP Name =" + nbpListToPrint.get(i).getCPName()
                    + ", Tariff price (actual tariff) = " + nbpListToPrint.get(i).getTariffPrice()
                    + ", Service Type = " + nbpListToPrint.get(i).getServiceType()
                    + ", City = " + nbpListToPrint.get(i).getCity()
                    + "]");
        }
    }

//    public void createSoapResponse(SOAPMessage soapResponse) throws Exception {
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//        Source sourceContent = soapResponse.getSOAPPart().getContent();
//        System.out.println("\n=========  CSV to Object  ========");
//        System.out.println("\n----------SOAP Response-----------");
//        System.out.println("");
//        StreamResult result = new StreamResult(System.out);
//        transformer.transform(sourceContent, result);
//        System.out.println("");
//    }
    public void PostData(List<NBP> node) throws IOException {

//         input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);

        int rowCount = node.size();

        for (int i = 0; i < rowCount; i++) {
            try {
                SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
                SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//            String url = "http://10.251.38.171:8080/LvoWebSvcServer/services/LVOWebServiceSend";
//            String url = "http://10.251.230.201:8080/LvoWebSvcServer/services/LVOWebServiceSend?";
                String urlF5 = prop.getProperty("urlF5");

                // Ti Create Request
                MessageFactory messageFactory = MessageFactory.newInstance();
                SOAPMessage soapMessage = messageFactory.createMessage();
                SOAPPart soapPart = soapMessage.getSOAPPart();
                SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
                soapEnvelope.addNamespaceDeclaration("ns1", urlF5);
//                "http://10.251.230.201:8080/LvoWebSvcServer/services/LVOWebServiceSend?wsdl");
//        soapEnvelope.addNamespaceDeclaration("ns1", 
//                "http://10.251.38.171:8080/LvoWebSvcServer/services/LVOWebServiceSend?wsdl");
//    	         soapEnvelope.addNamespaceDeclaration("ns1", "http://server.webservice.lvo.convergys.com");
                SOAPBody soapBody = soapEnvelope.getBody();
                SOAPElement soapElement = soapBody.addChildElement("sendSync", "ns1");
                soapElement.addChildElement("eventId").addTextNode("GenericEvent");
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getSourceID());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getTransactionID());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getMSISDN());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getServiceID());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getSenderAddress());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getRecipientAddress());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getChargedAddress());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getUSSDDirectCode());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getMessageContentType());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getMessagingType());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getCPName());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getTariffPrice());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getServiceType());
                soapElement.addChildElement("eventData").addTextNode(node.get(i).getCity());
                soapElement.addChildElement("timeout").addTextNode("10000");

//                element1.addTextNode("GenericEvent");
//                element2.addTextNode(node.get(i).getSourceID());
//                element3.addTextNode(node.get(i).getTransactionID());
//                element4.addTextNode(node.get(i).getMSISDN());
//                element5.addTextNode(node.get(i).getServiceID());
//                element6.addTextNode(node.get(i).getSenderAddress());
//                element7.addTextNode(node.get(i).getRecipientAddress());
//                element8.addTextNode(node.get(i).getChargedAddress());
//                element9.addTextNode(node.get(i).getUSSDDirectCode());
//                element10.addTextNode(node.get(i).getMessageContentType());
//                element11.addTextNode(node.get(i).getMessagingType());
//                element12.addTextNode(node.get(i).getCPName());
//                element13.addTextNode(node.get(i).getTariffPrice());
//                element14.addTextNode(node.get(i).getServiceType());
//                element15.addTextNode(node.get(i).getCity());
//                element16.addTextNode("10000");
//        soapMessage.saveChanges();
//        System.out.println("\n=========  CSV to Object  ========");
//        System.out.println("\n----------SOAP Request------------");
//        soapMessage.writeTo(System.out);
//            SOAPMessage soapRequest = createSoapRequest();
//            SOAPMessage soapRequest = soapMessage;
                //hit soapRequest to the server to get response
//            SOAPMessage soapResponse = soapConnection.call(soapRequest, urlF5);
//            createSoapResponse(soapResponse);
//            soapConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
