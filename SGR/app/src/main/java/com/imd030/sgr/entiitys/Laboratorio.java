package com.imd030.sgr.entiitys;

import java.io.Serializable;

/**
 * Created by thiago on 29/05/16.
 */
public class Laboratorio implements Serializable{

    private String nome;

    private String telefone;

    public Laboratorio(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
