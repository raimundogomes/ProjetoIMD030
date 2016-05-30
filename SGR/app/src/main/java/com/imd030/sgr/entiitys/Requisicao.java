package com.imd030.sgr.entiitys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by thiago on 28/05/16.
 */
public class Requisicao implements Serializable {

    private static final String FORMATO_NUMERO = "%08d";

    private Date dataRequisicao;

    private Solicitante solicitante;

    private Paciente paciente;

    private StatusRequisicao status;

    private long numero = 1;

    private List<Exame> exames;

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }

    public Requisicao() {
        numero++;
    }

    public Requisicao(Date dataRequisicao, Solicitante solicitante, Paciente paciente, StatusRequisicao status) {
        this.dataRequisicao = dataRequisicao;
        this.solicitante = solicitante;
        this.paciente = paciente;
        this.status = status;
        this.numero = numero++;
    }

    public Date getDataRequisicao() {
        return dataRequisicao;
    }

    public void setDataRequisicao(Date dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public StatusRequisicao getStatus() {
        return status;
    }

    public void setStatus(StatusRequisicao status) {
        this.status = status;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getNumeroFormatado() {
        return String.format(FORMATO_NUMERO, this.numero);
    }

    public String getExamesFormatados(){
        return exames.toString();
    }
}
