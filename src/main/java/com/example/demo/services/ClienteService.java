package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.enums.TipoCliente;
import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.ClienteNewDTO;
import com.example.demo.exceptions.DataIntegrityException;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.EnderecoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;



@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepo;

    
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
        enderecoRepo.saveAll(obj.getEnderecos());
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

    public Cliente fromDTO(ClienteNewDTO objDto){
        Cliente cli = new Cliente( null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()) );
        Cidade cid = new Cidade(objDto.getCidadeId(),null,null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid );
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2() != null){
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3() != null){
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
        //return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
        //throw new UnsupportedOperationException();
    }


}
