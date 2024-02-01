package com.example.TfgSoftAlba.models.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.TfgSoftAlba.models.entity.Tags;
import com.example.TfgSoftAlba.models.repository.SubcategoriaTagRepository;
import com.example.TfgSoftAlba.models.service.SubcategoriaTagService;

public class SubcategoriaTagServiceImpl implements SubcategoriaTagService {

    @Autowired
    private SubcategoriaTagRepository subcategoriaTagRepository;


    @Override
    public List<Tags> ObtenerTodasSubcategorias() {
        return subcategoriaTagRepository.findAll();
    }
    
}
