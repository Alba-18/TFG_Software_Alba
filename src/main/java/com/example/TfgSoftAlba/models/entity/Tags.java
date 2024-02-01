package com.example.TfgSoftAlba.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Tags")
public class Tags {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tag_id;

    @Column(name = "tags_type_id")
    private Long tags_type_id;

    @Column(name="name", nullable = false, length = 250)  
    private String name;


    public Tags(){
    }

    public Tags(Long tags_type_id, String name) {
        this.tags_type_id = tags_type_id;
        this.name = name;
    }

    public Tags(Long tag_id, Long tags_type_id, String name) {
    }

    public Long getId() {
        return tag_id;
    }

    public Long getIdType() {
        return tags_type_id;
    }

    public String getname() {
        return name;
    }

    public void setId(Long tag_id) {
        this.tag_id = tag_id;
    }

     public void setIdType(Long tags_type_id) {
        this.tags_type_id = tags_type_id;
    }

    public void setname(String name) {
        this.name = name;
    }

}
