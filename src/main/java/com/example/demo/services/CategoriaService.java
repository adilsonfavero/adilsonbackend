package com.example.demo.services;

import java.util.Optional;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    
    public Categoria buscar(int id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElse(null);
    }   

}
