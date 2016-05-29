package com.imd030.sgr.entiitys;

/**
 * Created by thiago on 29/05/16.
 */
public enum StatusRequisicao {
    SOLICITADA (0, "Solicitada"),
    EXECUCAO   (1, "Execução"),
    FINALIZADA (2, "Finalizada");

    private int codigo;

    private String descricao;

    StatusRequisicao(int codigo, String descricao) {
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
