/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exia.service;

import com.exia.dao.iFileDAO;
import com.exia.domain.File;
import com.exia.domain.JAXFile;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;

/**
 *
 * @author hyaci
 */
@Stateless
public class FileBean implements FileBeanRemote{

    @Inject
    private JMSContext context;
    
    @Resource(lookup = "jms/decodingQueue")
    private Queue decodingQueue;
    
    @Inject
    private iFileDAO fileDAO;
    
    @Override
    public Boolean sendDecodedText(String decodedText, String keyValue, String fileName) {
        try {            
            JAXFile decodedObject = new JAXFile();
            
            decodedObject.setKey(keyValue);
            decodedObject.setFileName(fileName);
            decodedObject.setDecodedText(decodedText);
            decodedObject.setMatchPercent(0.0);
            decodedObject.setMailAddress("");
                    
            sendMessage(decodedObject);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<JAXFile> getDecodedFiles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
    private void sendMessage(JAXFile decodedText) {
        JAXBContext jaxbContext;
     
        try {
  
            // encapsulate the XML Message
            TextMessage msg = context.createTextMessage(decodedText.getDecodedText());
            msg.setStringProperty("fileName", decodedText.getFileName());
            msg.setStringProperty("keyValue", decodedText.getKey());


            // send the message
            context.createProducer().send(decodingQueue, msg); 
            
        }  catch (JMSException ex) {
            Logger.getLogger(FileBean.class.getName()).log(Level.SEVERE, null, ex);
        }                                            
    }

    @Override
    public Boolean saveValidMessage(String decodedText, String keyValue, String fileName, Double matchPercent, String mailAddress) {
        File file = new File();
        file.setDecodedText(decodedText);
        file.setKey(keyValue);
        file.setFileName(fileName);
        file.setMatchPercent(matchPercent);
        file.setMailAddress(mailAddress);
        
        Boolean addedFile = fileDAO.save(file);
        
        return addedFile;
    }
}
