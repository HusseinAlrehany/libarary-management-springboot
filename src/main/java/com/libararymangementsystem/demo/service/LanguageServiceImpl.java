package com.libararymangementsystem.demo.service;

import com.libararymangementsystem.demo.dtos.LanguageDTO;
import com.libararymangementsystem.demo.entity.Book;
import com.libararymangementsystem.demo.entity.Languages;
import com.libararymangementsystem.demo.exceptionhandling.NotFoundException;
import com.libararymangementsystem.demo.repository.BookRepository;
import com.libararymangementsystem.demo.repository.LanguagesRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguagesRepository languagesRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public LanguageDTO saveLanguage(LanguageDTO language) {
       Languages lang= modelMapper.map(language,Languages.class);
        languagesRepository.save(lang);
        return modelMapper.map(lang,LanguageDTO.class);
    }

    @Override
    public List<LanguageDTO> findAll() {

        List<Languages> languages=languagesRepository.findAll();
        if(languages.isEmpty()){
            throw new NotFoundException("No Language Found");
        }
        List<LanguageDTO> langs=modelMapper.map(languages,new TypeToken<List<LanguageDTO>>(){}.getType());
        return langs;
    }

    @Override
    public LanguageDTO findById(int languageId) {

        Optional<Languages> result=languagesRepository.findById(languageId);
        Languages lang=null;
        if(result.isPresent()){
            lang=result.get();
        }
        else {
            throw new NotFoundException("Language Not Found ID -> " + languageId);
        }
        LanguageDTO languageDTO=modelMapper.map(lang,LanguageDTO.class);
        return languageDTO;
    }

    @Override
    public void addLangToBook(int bookId, LanguageDTO lang) {
        Optional<Book> result=bookRepository.findById(bookId);
        Book dbBook=null;
        if(result.isEmpty()){
            throw new NotFoundException("Book Not Found ID -> " + bookId);
        }
        dbBook=result.get();
        Languages languages=modelMapper.map(lang,Languages.class);
        dbBook.addLang(languages);
    }

    @Override
    public void deletById(int languageId) {
        Optional<Languages> dbLanguage=languagesRepository.findById(languageId);
        if(dbLanguage.isEmpty()){
            throw new NotFoundException("Language Not Found ID->> " + languageId );
        }else{
            languagesRepository.deleteById(languageId);
        }

    }
}
