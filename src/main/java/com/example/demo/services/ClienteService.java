package com.example.demo.services;

import java.util.Optional;

import com.example.demo.domain.Cliente;
import com.example.demo.repositories.ClienteRepository;
import com.example.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    
    public Cliente find(int id){
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow( ()-> new ObjectNotFoundException("Objeto não encontrado! Id " + id + ", Tipo: " + Cliente.class.getName()));
    }   

}
