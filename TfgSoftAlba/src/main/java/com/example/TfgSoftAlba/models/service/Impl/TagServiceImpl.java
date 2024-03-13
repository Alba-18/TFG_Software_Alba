package com.example.TfgSoftAlba.models.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TfgSoftAlba.models.entity.Tag;
import com.example.TfgSoftAlba.models.repository.TagRepository;
import com.example.TfgSoftAlba.models.service.TagService;

import java.util.*;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;


    @Override
    public List<Tag> getAllTags() {
        //return tagRepository.findAll();
        List<Tag> allTags = tagRepository.findAll();

        List<Tag> childTags = new ArrayList<>();

        for (Tag tag : allTags) {
            if (tag.getParent() != null) {
                childTags.add(tag);
            }
        }
        return childTags;
    }

    public List<Tag> getCategoriasPadre() {
        List<Tag> allTags = tagRepository.findAll();

        List<Tag> parentsTags = new ArrayList<>();

        for (Tag tag : allTags) {
            if (tag.getParent() == null) {
                parentsTags.add(tag);
            }
        }
        return parentsTags;
    }
    
    @Override
    public List<String> TypeLocalizacion() {
        List<String> localizacionName = tagRepository.findByTypeLocalizacion();
        return localizacionName;
    }

    @Override
    public List<String> TypeTipo() {
        return tagRepository.findByTypeTipo();
    }
}
