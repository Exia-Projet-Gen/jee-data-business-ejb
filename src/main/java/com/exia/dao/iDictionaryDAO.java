/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exia.dao;

import com.exia.domain.Word;
import java.util.List;

/**
 *
 * @author hyaci
 */
public interface iDictionaryDAO {
    List<Word> findByName(final String wordName);
    List<Word> getAll();
    Boolean update(final Word word);
    Word create(final Word word);
    Boolean delete(final Long id); 
}
