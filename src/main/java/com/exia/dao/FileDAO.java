/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exia.dao;

import com.exia.domain.File;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hyaci
 */

@Stateless
public class FileDAO implements iFileDAO{
    
    @PersistenceContext(unitName = "filesPU")
    private EntityManager em;
    
    @Override
    public Boolean save(File file) {
        try {
            if (file != null) {
                
                em.persist(file);
                return true;
            }
            return false;       
        }catch (Exception e) {
            return null;
        }
    }
}
