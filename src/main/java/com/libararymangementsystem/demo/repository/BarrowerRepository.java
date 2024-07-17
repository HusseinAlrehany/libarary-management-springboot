package com.libararymangementsystem.demo.repository;

import com.libararymangementsystem.demo.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarrowerRepository extends JpaRepository<Borrower,Integer> {


    public Borrower findByEmail(String email);
    public Borrower findByName(String name);
}
