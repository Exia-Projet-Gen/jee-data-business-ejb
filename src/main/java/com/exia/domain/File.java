/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exia.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hyaci
 */

@Entity
@Table(name="files")
public class File implements Serializable {
    
    private static final long serialVersionUID = 4L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="keyValue")
    private String key;
    
    @Column(name="fileName")
    private String fileName;
    
    @Column(name="firstWords")
    private String decodedText;
    
    @Column(name="matchPercent")
    private Double matchPercent;
    
    @Column(name="mails")
    private String mailAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMatchPercent() {
        return matchPercent;
    }

    public void setMatchPercent(Double matchPercent) {
        this.matchPercent = matchPercent;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDecodedText() {
        return decodedText;
    }

    public void setDecodedText(String decodedText) {
        this.decodedText = decodedText;
    }
}
