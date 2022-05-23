package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Produto;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.ProdutoRepository;
import com.example.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepo;

    
    public Produto find(int id){
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrado! Id " + id + ", Tipo: " + Produto.class.getName()));
    }   


    /*public Page<Produto>  search(String nome, List<Integer> ids,Integer page,Integer size, String orderBy, String direction  ){

        PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepo.findAllById(ids);
        return repo.search(nome, categorias, pageRequest);
        
    }*/

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}

}
