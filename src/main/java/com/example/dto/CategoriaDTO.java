package com.example.dto;

import java.io.Serializable;


import javax.validation.constraints.*;

import com.example.demo.domain.Categoria;

public class CategoriaDTO implements Serializable{

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @Size(min = 5, max = 80, message = "O tamaho deve ser entre 5 e 80 caracteres.")
    private String nome;

    
    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
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

    

}