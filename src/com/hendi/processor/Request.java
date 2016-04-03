/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.processor;

import com.hendi.model.NBP;
import static com.hendi.processor.CsvToJavaObject.input;
import static com.hendi.processor.CsvToJavaObject.prop;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
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
import org.apache.log4j.Logger;

/**
 *
 * @author Hendi Santika <hendisantika@gmail.com / hendisantika@yahoo.co.id >
 */
public class Request extends Thread {

    List<NBP> nbp;
    private static final Logger log = Logger.getLogger(Request.class.getName());

    public Request() {
    }

    public Request(List<NBP> nbp) {
        this.nbp = nbp;
    }

    @Override
    public void run() {
        Request send = new Request();
        try {
            send.PostData(nbp);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
//        SOAPElement soapElement = soapBody.addChildElement("sendNoReply", "ns1");
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

//                    element1.addTextNode("GenericEvent");
//                    element2.addTextNode(node.get(i).getSourceID());
//                    element3.addTextNode(node.get(i).getTransactionID());
//                    element4.addTextNode(node.get(i).getMSISDN());
//                    element5.addTextNode(node.get(i).getServiceID());
//                    element6.addTextNode(node.get(i).getSenderAddress());
//                    element7.addTextNode(node.get(i).getRecipientAddress());
//                    element8.addTextNode(node.get(i).getChargedAddress());
//                    element9.addTextNode(node.get(i).getUSSDDirectCode());
//                    element10.addTextNode(node.get(i).getMessageContentType());
//                    element11.addTextNode(node.get(i).getMessagingType());
//                    element12.addTextNode(node.get(i).getCPName());
//                    element13.addTextNode(node.get(i).getTariffPrice());
//                    element14.addTextNode(node.get(i).getServiceType());
//                    element15.addTextNode(node.get(i).getCity());
//                    element16.addTextNode("1000");
//                    
                soapMessage.saveChanges();
                log.info("\n");
                log.info("\n----------SOAP Request------------");
//                soapMessage.writeTo(System.out);
//        

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
    public void PostData2(NBP node) throws IOException {

//         input = new FileInputStream("/apps/nbp/java/conf/config.properties");
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);

//        int rowCount = node.;

//        for (int i = 0; i < rowCount; i++) {
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
//        SOAPElement soapElement = soapBody.addChildElement("sendNoReply", "ns1");
                soapElement.addChildElement("eventId").addTextNode("GenericEvent");
                soapElement.addChildElement("eventData").addTextNode(node.getSourceID());
                soapElement.addChildElement("eventData").addTextNode(node.getTransactionID());
                soapElement.addChildElement("eventData").addTextNode(node.getMSISDN());
                soapElement.addChildElement("eventData").addTextNode(node.getServiceID());
                soapElement.addChildElement("eventData").addTextNode(node.getSenderAddress());
                soapElement.addChildElement("eventData").addTextNode(node.getRecipientAddress());
                soapElement.addChildElement("eventData").addTextNode(node.getChargedAddress());
                soapElement.addChildElement("eventData").addTextNode(node.getUSSDDirectCode());
                soapElement.addChildElement("eventData").addTextNode(node.getMessageContentType());
                soapElement.addChildElement("eventData").addTextNode(node.getMessagingType());
                soapElement.addChildElement("eventData").addTextNode(node.getCPName());
                soapElement.addChildElement("eventData").addTextNode(node.getTariffPrice());
                soapElement.addChildElement("eventData").addTextNode(node.getServiceType());
                soapElement.addChildElement("eventData").addTextNode(node.getCity());
                soapElement.addChildElement("timeout").addTextNode("10000");

//                    element1.addTextNode("GenericEvent");
//                    element2.addTextNode(node.get(i).getSourceID());
//                    element3.addTextNode(node.get(i).getTransactionID());
//                    element4.addTextNode(node.get(i).getMSISDN());
//                    element5.addTextNode(node.get(i).getServiceID());
//                    element6.addTextNode(node.get(i).getSenderAddress());
//                    element7.addTextNode(node.get(i).getRecipientAddress());
//                    element8.addTextNode(node.get(i).getChargedAddress());
//                    element9.addTextNode(node.get(i).getUSSDDirectCode());
//                    element10.addTextNode(node.get(i).getMessageContentType());
//                    element11.addTextNode(node.get(i).getMessagingType());
//                    element12.addTextNode(node.get(i).getCPName());
//                    element13.addTextNode(node.get(i).getTariffPrice());
//                    element14.addTextNode(node.get(i).getServiceType());
//                    element15.addTextNode(node.get(i).getCity());
//                    element16.addTextNode("1000");
//                    
                soapMessage.saveChanges();
                log.info("\n");
                log.info("\n----------SOAP Request------------");
//                soapMessage.writeTo(System.out);
//        

//            SOAPMessage soapRequest = createSoapRequest();
                SOAPMessage soapRequest = soapMessage;
                //hit soapRequest to the server to get response
                SOAPMessage soapResponse = soapConnection.call(soapRequest, urlF5);
                createSoapResponse(soapResponse);
                soapConnection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
//        } // Block for
    }

    public void createSoapResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        log.info("\n");
        log.info("\n----------SOAP Response-----------");
        log.info("");
        StreamResult result = new StreamResult(System.out);
//        transformer.transform(sourceContent, result);
        log.info("");
    }
}
