/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exia.service;

import com.exia.dao.iDictionaryDAO;
import com.exia.domain.JAXWord;
import com.exia.domain.Word;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author hyaci
 */
@Stateless
public class DictionaryBean implements DictionaryBeanRemote {

    @Inject
    private iDictionaryDAO dictionaryDAO;
    
    @Override
    public JAXWord addWord(String value) {
        Word word = new Word();
        word.setValue(value);
        
        Word addedWord = dictionaryDAO.create(word);
        JAXWord jaxAddedWord = transformWordToJsonObject(addedWord);
        
        return jaxAddedWord;
    }

    @Override
    public Boolean updateWord(Long id, String value) {
        Word word = new Word();
        word.setId(id);
        word.setValue(value);
        return dictionaryDAO.update(word);
    }

    @Override
    public Boolean deleteWord(Long id) {
        return dictionaryDAO.delete(id);
    }

    @Override
    public List<JAXWord> searchWord(String wordName) {
        List<Word> words = dictionaryDAO.findByName(wordName);
        List<JAXWord> transformedWords = new ArrayList<>();
        words.forEach((word) -> {
            transformedWords.add(transformWordToJsonObject(word));
        });
        
        return transformedWords;
    }

    @Override
    public List<JAXWord> getWords() {
        List<Word> words = dictionaryDAO.getAll();
        List<JAXWord> transformedWords = new ArrayList<>();
        words.forEach((word) -> {
            transformedWords.add(transformWordToJsonObject(word));
        });
        
        return transformedWords;
    }

    // Parse a Word OBJECT to a JAXWord which allow to send this object in xml
    private JAXWord transformWordToJsonObject(Word word) {       
        try {
            JAXWord transformedWord = new JAXWord();
            transformedWord.setId(word.getId());
            transformedWord.setValue(word.getValue());
            return transformedWord;
        } catch (Exception e) {
            return null;
        }
    }
}
