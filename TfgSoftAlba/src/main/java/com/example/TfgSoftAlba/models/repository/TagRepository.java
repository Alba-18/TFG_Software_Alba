package com.example.TfgSoftAlba.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TfgSoftAlba.models.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    
}
