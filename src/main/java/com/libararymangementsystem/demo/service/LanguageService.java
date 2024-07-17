package com.libararymangementsystem.demo.service;

import com.libararymangementsystem.demo.dtos.LanguageDTO;
import com.libararymangementsystem.demo.entity.Languages;

import java.util.List;

public interface LanguageService {
    LanguageDTO saveLanguage(LanguageDTO language);

    List<LanguageDTO> findAll();

    LanguageDTO findById(int languageId);

    void addLangToBook(int bookId, LanguageDTO lang);

    void deletById(int languageId);
}
