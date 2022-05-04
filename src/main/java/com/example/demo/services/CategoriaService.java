package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;
import com.example.dto.CategoriaDTO;
import com.example.exceptions.DataIntegrityException;
import com.example.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    
    public Categoria find(int id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrado! Id " + id + ", Tipo: " + Categoria.class.getName()));
    }   

    public List<Categoria> findAll(){
        List<Categoria> objs = repo.findAll();
        return objs;
    }   

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj){
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Categoria newObj, Categoria obj){
        newObj.setNome(obj.getNome());
        
    }

    public void delete(Integer id){
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria com produtos relacionados.");
        }
        
    }

    public Page<Categoria> findPage(Integer page,Integer size, String orderBy, String direction ){

        PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);

    }

    public Categoria fromDTO(CategoriaDTO objDto){
        return new Categoria(objDto.getId(), objDto.getNome());

    }

}
