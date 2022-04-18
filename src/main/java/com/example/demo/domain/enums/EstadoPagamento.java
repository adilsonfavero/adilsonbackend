package com.example.demo.domain.enums;

public enum EstadoPagamento {
    
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado" );

    private int cod;
    private String description;


    private EstadoPagamento(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }


    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static EstadoPagamento toEnum(int cod){
        if( cod >= 0 ){
            return null;
        }
        for(EstadoPagamento x : EstadoPagamento.values()){
            if(cod == (x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido" + cod);

    }
   

    

    


}
