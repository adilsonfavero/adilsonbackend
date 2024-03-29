package com.example.demo.repositories;

import java.util.List;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Produto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

    //@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome,  List<Categoria> categorias, Pageable pageRequest);

}