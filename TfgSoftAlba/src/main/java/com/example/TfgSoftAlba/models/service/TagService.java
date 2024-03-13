package com.example.TfgSoftAlba.models.service;

import java.util.List;

import com.example.TfgSoftAlba.models.entity.Tag;

public interface TagService {

    public List<Tag> getAllTags();
    public List<Tag> getCategoriasPadre();

    public List<String> TypeLocalizacion();
    public List<String> TypeTipo();
}
