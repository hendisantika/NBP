/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Senju Hashirama
 */
@XmlRootElement
//@XmlType(propOrder = {"SourceID", "TransactionID", "MSISDN", "ServiceID",
//    "SenderAddress", "RecipientAddress", "ChargedAddress", "USSDDirectCode",
//    "MessageContentType", "MessagingType", "CPName", "TariffPrice",
//    "ServiceType", "City", "columnCount"})

//@XmlElement(name = "NBP")

public class NBP2 {

    String SourceID;
    String TransactionID;
    String MSISDN;
    String ServiceID;
    String SenderAddress;
    String RecipientAddress;
    String ChargedAddress;
    String USSDDirectCode;
    String MessageContentType;
    String MessagingType;
    String CPName;
    String TariffPrice;
    String ServiceType;
    String City;
    private int columnCount;

    public NBP2(String SourceID, String TransactionID, String MSISDN, 
            String ServiceID, String SenderAddress, String RecipientAddress, 
            String ChargedAddress, String USSDDirectCode, String MessageContentType, 
            String MessagingType, String CPName, String TariffPrice, String ServiceType, 
            String City, int columnCount) {
        this.SourceID = SourceID;
        this.TransactionID = TransactionID;
        this.MSISDN = MSISDN;
        this.ServiceID = ServiceID;
        this.SenderAddress = SenderAddress;
        this.RecipientAddress = RecipientAddress;
        this.ChargedAddress = ChargedAddress;
        this.USSDDirectCode = USSDDirectCode;
        this.MessageContentType = MessageContentType;
        this.MessagingType = MessagingType;
        this.CPName = CPName;
        this.TariffPrice = TariffPrice;
        this.ServiceType = ServiceType;
        this.City = City;
        this.columnCount = columnCount;
    }

    public NBP2() {
    }

    public String getSourceID() {
        return SourceID;
    }

    @XmlElement
    public void setSourceID(String sourceID) {
        SourceID = sourceID;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    @XmlElement
    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    @XmlElement
    public void setMSISDN(String mSISDN) {
        MSISDN = mSISDN;
    }

    public String getServiceID() {
        return ServiceID;
    }

    @XmlElement
    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
    }

    public String getSenderAddress() {
        return SenderAddress;
    }

    @XmlElement
    public void setSenderAddress(String senderAddress) {
        SenderAddress = senderAddress;
    }

    public String getRecipientAddress() {
        return RecipientAddress;
    }

    @XmlElement
    public void setRecipientAddress(String recipientAddress) {
        RecipientAddress = recipientAddress;
    }

    public String getChargedAddress() {
        return ChargedAddress;
    }

    @XmlElement
    public void setChargedAddress(String chargedAddress) {
        ChargedAddress = chargedAddress;
    }

    public String getUSSDDirectCode() {
        return USSDDirectCode;
    }

    @XmlElement
    public void setUSSDDirectCode(String uSSDDirectCode) {
        USSDDirectCode = uSSDDirectCode;
    }

    public String getMessageContentType() {
        return MessageContentType;
    }

    @XmlElement
    public void setMessageContentType(String messageContentType) {
        MessageContentType = messageContentType;
    }

    public String getMessagingType() {
        return MessagingType;
    }

    @XmlElement
    public void setMessagingType(String messagingType) {
        MessagingType = messagingType;
    }

    public String getCPName() {
        return CPName;
    }

    @XmlElement
    public void setCPName(String cPName) {
        CPName = cPName;
    }

    public String getTariffPrice() {
        return TariffPrice;
    }

    @XmlElement
    public void setTariffPrice(String tariffPrice) {
        TariffPrice = tariffPrice;
    }

    public String getServiceType() {
        return ServiceType;
    }

    @XmlElement
    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getCity() {
        return City;
    }

    @XmlElement
    public void setCity(String city) {
        City = city;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

}
