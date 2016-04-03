/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.processor;

import com.hendi.main.Config;
import com.hendi.model.NBP2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Senju Hashirama Tah ieu nu leresna mah kasepppp .... Rabu, 3
 * September 2014
 */
public class SAAJClient {
    FileNameFilter filter = new FileNameFilter();
    Properties prop = new Properties();
    InputStream input;
    
    public List<NBP2> ReadCSV() throws FileNotFoundException, IOException{
        BufferedReader br = null;
        String line;
        String splitBy = "\\|";
        List<NBP2> nbpList = new ArrayList<>();

        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
//        String dir = "E:/Testing/CSV";
//        String dir = Config.dirIO;
        String dir = prop.getProperty("dirIO");
//        String pro = "NBP_Process";
//        String pro = ".toprocess";
        String csv = Config.csv;

//        File direktori = new File("E:/Testing/CSV");
//        File[] files = direktori.listFiles(new FileNameFilter().new MyFileNameFilter(pro));
        File file = new File(dir);
        File[] listFiles = file.listFiles(new FileNameFilter().new MyFileNameFilter(csv));

        if (listFiles.length > 0) {
            for (File listFile : listFiles) {
                try {
                    br = new BufferedReader(new FileReader(listFile.toString()));
                    br.readLine();
                    while ((line = br.readLine()) != null) {

                        // split on comma(',' or '|')  
                        String[] nbp = line.split(splitBy);

                        String SourceID = nbp[0];
                        String TransactionID = nbp[1];
                        String MSISDN = nbp[2];
                        String ServiceID = nbp[3];
                        String SenderAddress = nbp[4];
                        String RecipientAddress = nbp[5];
                        String ChargedAddress = nbp[6];
                        String USSDDirectCode = nbp[7];
                        String MessageContentType = nbp[8];
                        String MessagingType = nbp[9];
                        String CPName = nbp[10];
                        String TariffPrice = nbp[11];
                        String ServiceType = nbp[12];
                        String City = nbp[13];
                        int columnCount = nbp.length;

                        nbpList.add(new NBP2(SourceID, TransactionID, MSISDN,
                                ServiceID, SenderAddress, RecipientAddress,
                                ChargedAddress, USSDDirectCode, MessageContentType,
                                MessagingType, CPName, TariffPrice, ServiceType,
                                City, columnCount));

                    }
                    // print values stored in nbpList  
//                    printnbpList(nbpList);
//                    int rowCOunt = nbpList.size();
//                    int columnCount = nbpList.get(rowCOunt - 1).getColumnCount();
//                    int x = 1;
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            System.out.println("The Directory is empty!");
            System.out.println("File not found!");
            System.out.println("Processing file cannot be done!");
            System.out.println("Please check the file, make sure it is existed!");

        }

        return nbpList;
    }
        

    public SOAPMessage createSoapRequest() throws Exception {
        SAAJClient baca = new SAAJClient();
        List<NBP2> node = baca.ReadCSV();
        
         input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        
        String urlF5 = prop.getProperty("urlF5");
        
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        soapEnvelope.addNamespaceDeclaration("ns1", urlF5);
//        soapEnvelope.addNamespaceDeclaration("ns1", 
//                "http://10.251.38.171:8080/LvoWebSvcServer/services/LVOWebServiceSend?wsdl");
//    	         soapEnvelope.addNamespaceDeclaration("ns1", "http://server.webservice.lvo.convergys.com");
        SOAPBody soapBody = soapEnvelope.getBody();
        SOAPElement soapElement = soapBody.addChildElement("sendSync", "ns1");
        SOAPElement element1 = soapElement.addChildElement("eventId");
        SOAPElement element2 = soapElement.addChildElement("eventData");
        SOAPElement element3 = soapElement.addChildElement("eventData");
        SOAPElement element4 = soapElement.addChildElement("eventData");
        SOAPElement element5 = soapElement.addChildElement("eventData");
        SOAPElement element6 = soapElement.addChildElement("eventData");
        SOAPElement element7 = soapElement.addChildElement("eventData");
        SOAPElement element8 = soapElement.addChildElement("eventData");
        SOAPElement element9 = soapElement.addChildElement("eventData");
        SOAPElement element10 = soapElement.addChildElement("eventData");
        SOAPElement element11 = soapElement.addChildElement("eventData");
        SOAPElement element12 = soapElement.addChildElement("eventData");
        SOAPElement element13 = soapElement.addChildElement("eventData");
        SOAPElement element14 = soapElement.addChildElement("eventData");
        SOAPElement element15 = soapElement.addChildElement("eventData");
        SOAPElement element16 = soapElement.addChildElement("timeout");
        
        int i = 0;
                    element1.addTextNode("GenericEvent");
                    element2.addTextNode(node.get(i).getSourceID());
                    element3.addTextNode(node.get(i).getTransactionID());
                    element4.addTextNode(node.get(i).getMSISDN());
                    element5.addTextNode(node.get(i).getServiceID());
                    element6.addTextNode(node.get(i).getSenderAddress());
                    element7.addTextNode(node.get(i).getRecipientAddress());
                    element8.addTextNode(node.get(i).getChargedAddress());
                    element9.addTextNode(node.get(i).getUSSDDirectCode());
                    element10.addTextNode(node.get(i).getMessageContentType());
                    element11.addTextNode(node.get(i).getMessagingType());
                    element12.addTextNode(node.get(i).getCPName());
                    element13.addTextNode(node.get(i).getTariffPrice());
                    element14.addTextNode(node.get(i).getServiceType());
                    element15.addTextNode(node.get(i).getCity());
                    element16.addTextNode("10000");
                    
        soapMessage.saveChanges();
        System.out.println("\n");
        System.out.println("----------SOAP Request------------");
        soapMessage.writeTo(System.out);
        System.out.println("");

        return soapMessage;
    }

    public void createSoapResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.println("\n");
        System.out.println("\n----------SOAP Response-----------");
        System.out.println("");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
        System.out.println("");
    }
    
    
    public void PostData() throws IOException {
        SAAJClient baca = new SAAJClient();
        List<NBP2> node = baca.ReadCSV();
        
         input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        
        
        int rowCount = node.size();
                
        for(int i = 0; i < rowCount; i++){
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
        SOAPElement element1 = soapElement.addChildElement("eventId");
        SOAPElement element2 = soapElement.addChildElement("eventData");
        SOAPElement element3 = soapElement.addChildElement("eventData");
        SOAPElement element4 = soapElement.addChildElement("eventData");
        SOAPElement element5 = soapElement.addChildElement("eventData");
        SOAPElement element6 = soapElement.addChildElement("eventData");
        SOAPElement element7 = soapElement.addChildElement("eventData");
        SOAPElement element8 = soapElement.addChildElement("eventData");
        SOAPElement element9 = soapElement.addChildElement("eventData");
        SOAPElement element10 = soapElement.addChildElement("eventData");
        SOAPElement element11 = soapElement.addChildElement("eventData");
        SOAPElement element12 = soapElement.addChildElement("eventData");
        SOAPElement element13 = soapElement.addChildElement("eventData");
        SOAPElement element14 = soapElement.addChildElement("eventData");
        SOAPElement element15 = soapElement.addChildElement("eventData");
        SOAPElement element16 = soapElement.addChildElement("timeout");
        
                    element1.addTextNode("GenericEvent");
                    element2.addTextNode(node.get(i).getSourceID());
                    element3.addTextNode(node.get(i).getTransactionID());
                    element4.addTextNode(node.get(i).getMSISDN());
                    element5.addTextNode(node.get(i).getServiceID());
                    element6.addTextNode(node.get(i).getSenderAddress());
                    element7.addTextNode(node.get(i).getRecipientAddress());
                    element8.addTextNode(node.get(i).getChargedAddress());
                    element9.addTextNode(node.get(i).getUSSDDirectCode());
                    element10.addTextNode(node.get(i).getMessageContentType());
                    element11.addTextNode(node.get(i).getMessagingType());
                    element12.addTextNode(node.get(i).getCPName());
                    element13.addTextNode(node.get(i).getTariffPrice());
                    element14.addTextNode(node.get(i).getServiceType());
                    element15.addTextNode(node.get(i).getCity());
                    element16.addTextNode("10000");
                    
        soapMessage.saveChanges();
        System.out.println("\n");
        System.out.println("----------SOAP Request------------");
        soapMessage.writeTo(System.out);
        
            
//            SOAPMessage soapRequest = createSoapRequest();
            SOAPMessage soapRequest = soapMessage;
            //hit soapRequest to the server to get response
            SOAPMessage soapResponse = soapConnection.call(soapRequest, urlF5);
            createSoapResponse(soapResponse);
            soapConnection.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
}

