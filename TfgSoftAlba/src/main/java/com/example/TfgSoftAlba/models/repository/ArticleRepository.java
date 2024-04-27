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

    @Query(value ="SELECT distinct a.* "+ 
            "FROM db_tfgalba.article a "+
            "LEFT JOIN db_tfgalba.article_tag t1 ON t1.article_id = a.id "+
            "LEFT JOIN db_tfgalba.tags tg1 ON tg1.tag_id = t1.tag_id AND tg1.tags_type_id = '1' "+
            "LEFT JOIN db_tfgalba.article_tag t2 ON t2.article_id = a.id "+
            "LEFT JOIN db_tfgalba.tags tg2 ON tg2.tag_id = t2.tag_id AND tg2.tags_type_id = '2' "+
            "WHERE (:selectedDate IS NULL OR DATE_FORMAT(a.dt_creationdate, '%m/%Y') = :selectedDate) "+
            "AND (:selectedType IS NULL OR tg1.name = :selectedType) "+
            "AND (:selectedLocation IS NULL OR tg2.name = :selectedLocation)", nativeQuery=true)
    List<Article> findByCreationDateAndLocationAndType(String selectedDate, String selectedLocation, String selectedType);
}
