package com.example.TfgSoftAlba.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.TfgSoftAlba.models.entity.Tag;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{

    @Query(value ="Select distinct t.name from tags t where t.tags_type_id = 2",nativeQuery=true)
    public abstract List<String> findByTypeLocalizacion();

    @Query(value ="Select distinct t.name from tags t where t.tags_type_id = 1",nativeQuery=true)
    public abstract List<String> findByTypeTipo();
}
