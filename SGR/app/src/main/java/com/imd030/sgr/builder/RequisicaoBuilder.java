package com.imd030.sgr.builder;

import com.imd030.sgr.entiitys.Paciente;
import com.imd030.sgr.entiitys.Requisicao;
import com.imd030.sgr.entiitys.Solicitante;
import com.imd030.sgr.entiitys.StatusRequisicao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Neto on 29/05/2016.
 */
public class RequisicaoBuilder {

    private static long numeroGerador = 1;

    public List<Requisicao> gerarRequisicoes() {
        List<Requisicao> requisicoes = new ArrayList<Requisicao>();
        requisicoes.add(criarRequisicao("Solicitante 1", "Machado de Assis"));
        requisicoes.add(criarRequisicao("Solicitante 1", "José de Alencar"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Lima Barreto"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Raquel de Queiroz"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Policarpo Quaresma"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Dom Casmurro"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Pedro Alvez Cabral"));
        requisicoes.add(criarRequisicao("Solicitante 1", "João da Silva"));
        return requisicoes;
    }

    public Requisicao criarRequisicao(String nomeSolicitante, String nomePaciente){

        Requisicao requisicao = new Requisicao();

        requisicao.setDataRequisicao(new Date());

        requisicao.setNumero(numeroGerador++);

        if(requisicao.getNumero()/2==0) {
            requisicao.setStatus(StatusRequisicao.SOLICITADA);
        }
        else{
            requisicao.setStatus(StatusRequisicao.EXECUCAO);
        }

        Paciente paciente = new Paciente();
        paciente.setNome(nomePaciente);
        requisicao.setPaciente(paciente);

        ExamesBulder examesBulder = new ExamesBulder();

        requisicao.setExames(examesBulder.getListaExames());
        requisicao.getExames().add(examesBulder.adicionaExameSangue());
        requisicao.getExames().add(examesBulder.adicionaExameUrina());



        Solicitante solicitante = new Solicitante();
        solicitante.setNome(nomeSolicitante);
        requisicao.setSolicitante(solicitante);

        return requisicao;
    }
}