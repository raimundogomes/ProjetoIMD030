package com.imd030.sgr.entiitys;

/**
 * Created by Neto on 04/06/2016.
 */
public enum ResultadoExame {
    POSITIVO(1, "Positivo"), Negativo(2,"Negativo");

    private int codigo;
    private String descricao;

    ResultadoExame(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
