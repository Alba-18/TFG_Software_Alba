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
        return tagRepository.findAll();
    }


    
}
