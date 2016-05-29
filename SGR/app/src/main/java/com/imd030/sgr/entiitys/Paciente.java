package com.imd030.sgr.entiitys;

/**
 * Created by thiago on 29/05/16.
 */
public class Paciente {

    private String cns; //carteira nacional de saúde

    private String nome;

    private String numeroProntuario;

    public Paciente() {
    }

    public Paciente(String cns, String nome, String numeroProntuario) {
        this.cns = cns;
        this.nome = nome;
        this.numeroProntuario = numeroProntuario;
    }

    public String getCns() {
        return cns;
    }

    public void setCns(String cns) {
        this.cns = cns;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroProntuario() {
        return numeroProntuario;
    }

    public void setNumeroProntuario(String numeroProntuario) {
        this.numeroProntuario = numeroProntuario;
    }
}
