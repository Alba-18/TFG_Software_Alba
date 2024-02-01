package com.example.TfgSoftAlba.models.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.TfgSoftAlba.models.entity.TagsType;
import com.example.TfgSoftAlba.models.repository.CategoriaTagRepository;
import com.example.TfgSoftAlba.models.service.CategoriaTagService;

public class CategoriaTagServiceImpl implements CategoriaTagService {

    @Autowired
    private CategoriaTagRepository categoriaTagRepository;

    @Override
    public List<TagsType> ObtenerTodasCategorias() {
        return categoriaTagRepository.findAll();
    }
    
}
