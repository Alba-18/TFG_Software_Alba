package com.example.TfgSoftAlba.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Tags_type")
public class TagsType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tags_type_id;

    @Column(name="description", nullable = false, length = 250)  
    private String description;


    public TagsType(){
    }

    public TagsType(String description) {
        this.description = description;
    }

    public TagsType(Long tags_type_id, String description) {
    }

    public Long getId() {
        return tags_type_id;
    }

    public String getdescription() {
        return description;
    }

    public void setId(Long tags_type_id) {
        this.tags_type_id = tags_type_id;
    }

    public void setdescription(String description) {
        this.description = description;
    }

}
