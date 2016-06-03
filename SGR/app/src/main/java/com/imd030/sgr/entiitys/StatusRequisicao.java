package com.imd030.sgr.entiitys;

/**
 * Created by thiago on 29/05/16.
 */
public enum StatusRequisicao {
    SOLICITADA (0, "Solicitada"),
    RECEBIDA_PELO_LABORATORIO(1, "Recebida pelo laboratório"),
    LAUDO_PARCIAL (2, "Laudo parcial"),
    CANCELADA (3, "Cancelada"),
    LAUDO_DEFINITIVO(4, "LAUDO");

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
