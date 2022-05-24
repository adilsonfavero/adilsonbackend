package com.example.demo.dto;

import java.io.Serializable;
import com.example.demo.domain.Produto;

public class ProdutoDTO implements Serializable{ 

    private Integer id;
    private String nome;
    private double preco;

public ProdutoDTO(){

}

public ProdutoDTO(Produto obj){
    this.id = obj.getId();
    this.nome = obj.getNome();
    this.preco = obj.getPreco();

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

public double getPreco() {
    return preco;
}

public void setPreco(double preco) {
    this.preco = preco;
}

   

    
    
    

}