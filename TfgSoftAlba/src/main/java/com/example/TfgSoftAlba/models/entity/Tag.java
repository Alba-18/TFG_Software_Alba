package com.example.TfgSoftAlba.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "tags")
public class Tag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Long tag_id;
    
    @Column(name="name", nullable = false, length = 250)
    private String name;

    @ManyToOne
    @JoinColumn(name="tags_type_id", nullable = true)
    private Tag type;


    public Long getId() {
        return tag_id;
    }

    public void setId(Long id) {
        this.tag_id = id;
    }

    // Getter y setter para el campo nombre
    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    // Getter y setter para el campo padre
    public Tag getType() {
        return type;
    }

    public void setType(Tag tipo) {
        this.type = tipo;
    }

    public Tag getParent() {
        return type;
    }

}
