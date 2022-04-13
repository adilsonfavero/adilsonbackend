package com.example.demo.domain.enums;

public enum TipoCliente {
    
    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int cod;
    private String description;


    private TipoCliente(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }


    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static TipoCliente toEnum(int cod){
        if( cod >= 0 ){
            return null;
        }
        for(TipoCliente x : TipoCliente.values()){
            if(cod == (x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id Inválido" + cod);

    }
   

    

    


}
