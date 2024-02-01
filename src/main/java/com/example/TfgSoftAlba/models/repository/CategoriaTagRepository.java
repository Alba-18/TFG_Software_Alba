package com.example.TfgSoftAlba.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TfgSoftAlba.models.entity.TagsType;

public interface CategoriaTagRepository extends JpaRepository<TagsType,Long> {
    
}
