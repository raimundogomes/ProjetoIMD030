package com.imd030.sgr.entiitys;

import java.util.Date;

/**
 * Created by thiago on 28/05/16.
 */
public class Requisicao {

    private Date dataRequisicao;

    private Solicitante solicitante;

    private Paciente paciente;

    private StatusRequisicao status;

    public Requisicao() {
    }

    public Requisicao(Date dataRequisicao, Solicitante solicitante, Paciente paciente, StatusRequisicao status) {
        this.dataRequisicao = dataRequisicao;
        this.solicitante = solicitante;
        this.paciente = paciente;
        this.status = status;
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

}
