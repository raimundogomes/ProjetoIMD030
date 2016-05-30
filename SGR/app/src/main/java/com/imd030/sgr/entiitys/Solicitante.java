package com.imd030.sgr.entiitys;

import java.io.Serializable;

/**
 * Created by thiago on 29/05/16.
 */
public class Solicitante implements Serializable {

    private String nome;

    private TipoSolicitante tipoSolicitante;

    public Solicitante() {
    }

    public Solicitante(String nome, TipoSolicitante tipoSolicitante) {
        this.nome = nome;
        this.tipoSolicitante = tipoSolicitante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoSolicitante getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(TipoSolicitante tipoSolicitante) {
        this.tipoSolicitante = tipoSolicitante;
    }
}
