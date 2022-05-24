package com.example.demo.dto;

import java.io.Serializable;


import javax.validation.constraints.*;

import com.example.demo.domain.Cliente;

public class ClienteDTO implements Serializable{

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Size(min = 5, max = 120, message = "O tamaho deve ser entre 5 e 120 caracteres.")
    private String nome;

    
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Email(message = "E-mail obrigatório")
    private String email;

    
    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    

}