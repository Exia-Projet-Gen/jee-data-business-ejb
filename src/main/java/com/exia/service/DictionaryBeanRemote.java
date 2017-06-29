/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exia.service;

import com.exia.domain.JAXWord;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author hyaci
 */

@Remote
public interface DictionaryBeanRemote {
    JAXWord addWord(String word);
    Boolean updateWord(Long id, String wordValue);
    Boolean deleteWord(Long id);
    List<JAXWord> searchWord(String wordName);
    List<JAXWord> getWords();
}
