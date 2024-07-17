package com.libararymangementsystem.demo.restcontroller;

import com.libararymangementsystem.demo.dtos.LanguageDTO;
import com.libararymangementsystem.demo.entity.Languages;
import com.libararymangementsystem.demo.exceptionhandling.SuccessResponse;
import com.libararymangementsystem.demo.repository.LanguagesRepository;
import com.libararymangementsystem.demo.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/languages")
    public ResponseEntity<List<LanguageDTO>> findAll(){

        List<LanguageDTO>languages=languageService.findAll();

        return ResponseEntity.ok(languages);
    }
    @GetMapping("/languages/{languageId}")
    public ResponseEntity<LanguageDTO> findById(@PathVariable int languageId){
        LanguageDTO languageDTO=languageService.findById(languageId);

        return ResponseEntity.ok(languageDTO);
    }

    @PostMapping("/languages")
    public ResponseEntity<LanguageDTO> saveLang(@RequestBody LanguageDTO language){

        language.setId(0);
        LanguageDTO lang=languageService.saveLanguage(language);

        return ResponseEntity.ok(lang);
    }
    @PutMapping("/languages")
    public ResponseEntity<SuccessResponse> updateLang(@RequestBody LanguageDTO language){
        String message="Language Updated Successfully";
        SuccessResponse response=new SuccessResponse();
        response.setMessage(message);
        LanguageDTO dbLang=languageService.saveLanguage(language);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/languages/{bookId}")
    public ResponseEntity<SuccessResponse> addLangToBook(@PathVariable int bookId,
                                 @RequestBody LanguageDTO lang){
        String message="Language Successfully Added To Book Id-> " + bookId;
        SuccessResponse response=new SuccessResponse();
        response.setMessage(message);
        languageService.addLangToBook(bookId,lang);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/languages/{languageId}")
    public String deleteById(@PathVariable int languageId){
        languageService.deletById(languageId);

        return "Language Deleted Successfully -> " + languageId;
    }
}
