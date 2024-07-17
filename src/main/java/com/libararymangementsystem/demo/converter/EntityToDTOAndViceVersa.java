package com.libararymangementsystem.demo.converter;

import com.libararymangementsystem.demo.dtos.AuthorDTO;
import com.libararymangementsystem.demo.entity.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityToDTOAndViceVersa {

    //converting Author Entity to Author DTO
    public AuthorDTO entityToDTO(Author author){

        AuthorDTO authorDTO=new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setLastName(author.getLastName());
        authorDTO.setEmail(author.getEmail());

        return authorDTO;
    }
    //converting Author DTO to Author Entity
    public Author authorDTOToEntity(AuthorDTO authorDTO){
        Author author=new Author();
        author.setId(authorDTO.getId());
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setEmail(authorDTO.getEmail());

        return author;
    }


}
