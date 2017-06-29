/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exia.service;

import com.exia.domain.JAXFile;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hyaci
 */

@Remote
public interface FileBeanRemote {
    Boolean sendDecodedText(String decodedText, String keyValue, String fileName);
    Boolean saveValidMessage(String decodedText, String keyValue, String fileName, Double matchPercent, String mailAddress);
    List<JAXFile> getDecodedFiles();
}
