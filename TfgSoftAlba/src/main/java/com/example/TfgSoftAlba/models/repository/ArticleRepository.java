package com.example.TfgSoftAlba.models.repository;

import com.example.TfgSoftAlba.models.entity.Article;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    
    ArrayList<Article> findByIdIn(ArrayList<Long> ArticleIds);

    List<Article> findAll();

    List<Article> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);



}
