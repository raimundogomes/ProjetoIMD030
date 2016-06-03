package com.imd030.sgr.entiitys;

import java.io.Serializable;

/**
 * Created by thiago on 29/05/16.
 */
public class Paciente implements Serializable {

    /**
     * carteira nacional de saúde - cns
     */
    private String cns;

    private String nome;

    private String numeroProntuario;

    private String email;

    private String telefone;

    public Paciente() {
    }

    /**
     * Construtor
     * @param cns carteira nacional de saúde - cns.
     * @param nome Nome do Paciente.
     * @param numeroProntuario Número do paciente.
     * @param email e-mail paciente.
     */
    public Paciente(String cns, String nome, String numeroProntuario, String email, String telefone) {
        this.cns = cns;
        this.nome = nome;
        this.numeroProntuario = numeroProntuario;
        this.email = email;
        this.telefone = telefone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }
}
