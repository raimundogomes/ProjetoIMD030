package com.imd030.sgr.builder;

import com.imd030.sgr.entiitys.Laboratorio;
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
        requisicoes.add(criarRequisicao("Solicitante 1", "Machado de Assis", "machadoassis@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "José de Alencar", "josealencar@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Lima Barreto", "limabarreto@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Raquel de Queiroz", "raquequeiroz@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Policarpo Quaresma", "policarpo@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Dom Casmurro", "domcasmurro@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Pedro Alvez Cabral", "pedrocabral@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "João da Silva", "joaosilva@email.com"));
        return requisicoes;
    }

    public Requisicao criarRequisicao(String nomeSolicitante, String nomePaciente, String emailPaciente){

        Requisicao requisicao = new Requisicao();

        requisicao.setDataRequisicao(new Date());

        requisicao.setNumero(numeroGerador++);

        if(requisicao.getNumero()%2==0) {
            requisicao.setStatus(StatusRequisicao.SOLICITADA);
        }
        else{
            requisicao.setStatus(StatusRequisicao.RECEBIDA_PELO_LABORATORIO);
        }

        Paciente paciente = new Paciente("0"+ numeroGerador+13, nomePaciente, "0"+ numeroGerador + 31, emailPaciente, "987654321");

        requisicao.setPaciente(paciente);

        Laboratorio laboratorio = new Laboratorio("Microbiologia", "987654322");

        requisicao.setLaboratorio(laboratorio);

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
