package com.example.demo.domain;

import com.example.demo.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao   ")
public class PagamentoComCartao extends Pagamento {
    
    private Integer numeroDeParcelas;


    public PagamentoComCartao(){

    }


    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, int numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }


    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }


    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    



}
