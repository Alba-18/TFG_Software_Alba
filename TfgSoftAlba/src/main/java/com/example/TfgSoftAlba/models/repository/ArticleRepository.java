package com.example.TfgSoftAlba.models.repository;

import com.example.TfgSoftAlba.models.entity.Article;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    
    ArrayList<Article> findByIdIn(ArrayList<Long> ArticleIds);

    List<Article> findAll();

    List<Article> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT a FROM Article a WHERE (:selectedDate IS NULL OR a.creationDate = :selectedDate) " +
            "AND (:selectedLocation IS NULL OR a.location = :selectedLocation) " +
            "AND (:selectedType IS NULL OR a.type = :selectedType)")
    List<Article> filterArticles(String selectedDate, String selectedLocation, String selectedType);



}
