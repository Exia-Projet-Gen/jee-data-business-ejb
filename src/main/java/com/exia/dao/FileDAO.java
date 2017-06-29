/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exia.dao;

import com.exia.domain.File;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author hyaci
 */

@Stateless
public class FileDAO implements iFileDAO{
    
    private static final long serialVersionUID = 5L;
    
    @PersistenceContext(unitName = "filesPU")
    private EntityManager em;
    
    @Override
    public Boolean save(File file) {
        try {
            if (file != null) {           
                em.persist(file);
                em.flush();
                return true;
            }
            return false;       
        }catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<File> getAll() {       
        try {
            TypedQuery <File> query = em.createQuery("SELECT f FROM File f", File.class);
            List<File> files = query.getResultList() ;
     
            return files ;
        } catch (Exception e) {
            
            return null;
        }
    }
}
