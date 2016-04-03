/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Senju Hashirama
 */
@XmlRootElement(name="NBP")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"SourceID", "TransactionID", "MSISDN", "ServiceID",
    "SenderAddress", "RecipientAddress", "ChargedAddress", "USSDDirectCode",
    "MessageContentType", "MessagingType", "CPName", "TariffPrice",
    "ServiceType", "City", "columnCount"})


public class NBP {
    @XmlElement(name = "SourceID")
    String SourceID;
    
    @XmlElement(name = "TransactionID")
    String TransactionID;
    
    @XmlElement(name = "MSISDN")
    String MSISDN;
    
    @XmlElement(name = "ServiceID")
    String ServiceID;
    
    @XmlElement(name = "SenderAddress")
    String SenderAddress;
    
    @XmlElement(name = "RecipientAddress")
    String RecipientAddress;
    
    @XmlElement(name = "ChargedAddress")
    String ChargedAddress;
    
    @XmlElement(name = "USSDDirectCode")
    String USSDDirectCode;
    
    @XmlElement(name = "MessageContentType")
    String MessageContentType;
    
    @XmlElement(name = "MessagingType")
    String MessagingType;
    
    @XmlElement(name = "CPName")
    String CPName;
    
    @XmlElement(name = "TariffPrice")
    String TariffPrice;
    
    @XmlElement(name = "ServiceType")
    String ServiceType;
    
    @XmlElement(name = "City")
    String City;
    
    @XmlElement(name = "columnCount")
    private int columnCount;

    public String getSourceID() {
        return SourceID;
    }

    public void setSourceID(String sourceID) {
        SourceID = sourceID;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String mSISDN) {
        MSISDN = mSISDN;
    }

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        if (serviceID.equals("")) {
            ServiceID = "0";
        } else {
            ServiceID = serviceID;
        }

    }

    public String getSenderAddress() {
        return SenderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        if (senderAddress.equals("")) {
            SenderAddress = "0";
        } else {
            SenderAddress = senderAddress;
        }

    }

    public String getRecipientAddress() {
        return RecipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        if (recipientAddress.equals("")) {
            RecipientAddress = "0";
        } else {
            RecipientAddress = recipientAddress;
        }

    }

    public String getChargedAddress() {
        return ChargedAddress;
    }

    public void setChargedAddress(String chargedAddress) {
        if (chargedAddress.equals("")) {
            ChargedAddress = "0";
        } else {
            ChargedAddress = chargedAddress;
        }

    }

    public String getUSSDDirectCode() {
        return USSDDirectCode;
    }

    public void setUSSDDirectCode(String uSSDDirectCode) {
        if (uSSDDirectCode.equals("")) {
            USSDDirectCode = "0";
        } else {
            USSDDirectCode = uSSDDirectCode;
        }

    }

    public String getMessageContentType() {
        return MessageContentType;
    }

    public void setMessageContentType(String messageContentType) {
        if (messageContentType.equals("")) {
            MessageContentType = "0";

        } else {
            MessageContentType = messageContentType;
        }

    }

    public String getMessagingType() {
        return MessagingType;
    }

    public void setMessagingType(String messagingType) {
        if (messagingType.equals("")) {
            MessagingType = "0";
        } else {
            MessagingType = messagingType;
        }

    }

    public String getCPName() {
        return CPName;
    }

    public void setCPName(String cPName) {
        if (cPName.equals("")) {
            CPName = "0";
        } else {
            CPName = cPName;
        }

    }

    public String getTariffPrice() {
        return TariffPrice;
    }

    public void setTariffPrice(String tariffPrice) {
        if (tariffPrice.equals("")) {
            TariffPrice = "0";
        } else {
            TariffPrice = tariffPrice;
        }

    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        if (serviceType.equals("")) {
            ServiceType = "0";

        } else {
            ServiceType = serviceType;
        }

    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        if (city.equals("")) {
            City = "0";
        } else {
            City = city;
        }

    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

}
