package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Cliente;
import com.example.demo.repositories.ClienteRepository;
import com.example.dto.ClienteDTO;
import com.example.exceptions.DataIntegrityException;
import com.example.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.Thrown;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    
    public Cliente find(int id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrado! Id " + id + ", Tipo: " + Cliente.class.getName()));
    } 
    
    public List<Cliente> findAll(){
        List<Cliente> objs = repo.findAll();
        return objs;
    }   

    public Cliente insert(Cliente obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public Cliente update(Cliente obj){
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public void delete(Integer id){
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir o Cliente com pedidos relacionados.");
        }
        
    }

    public Page<Cliente> findPage(Integer page,Integer size, String orderBy, String direction ){

        PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);

    }

    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
        //throw new UnsupportedOperationException();
    }

}
