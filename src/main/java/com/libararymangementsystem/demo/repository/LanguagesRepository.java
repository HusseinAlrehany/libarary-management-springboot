package com.libararymangementsystem.demo.repository;

import com.libararymangementsystem.demo.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguagesRepository extends JpaRepository<Languages,Integer> {


}
