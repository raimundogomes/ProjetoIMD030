package com.imd030.sgr.comparator;

import com.imd030.sgr.entiitys.Requisicao;
import com.imd030.sgr.utils.Constantes;

import java.util.Comparator;
import java.util.List;

/**
 * Created by thiago on 05/06/16.
 */
public class RequisicaoComparator implements Comparator<Requisicao> {

    private int criterio;

    public RequisicaoComparator(int criterio) {
        this.criterio = criterio;
    }

    @Override
    public int compare(Requisicao lhs, Requisicao rhs) {
        switch (criterio){
            case Constantes.CRITERIO_NUMERO_REQUISICAO:
                return Long.compare(lhs.getNumero(), rhs.getNumero());
            case Constantes.CRITERIO_DATA_REQUISICAO:
                return lhs.getDataRequisicao().compareTo(rhs.getDataRequisicao());
            case Constantes.CRITERIO_NOME_PACIENTE:
                return lhs.getPaciente().getNome().compareTo(rhs.getPaciente().getNome());
            case Constantes.CRITERIO_STATUS_REQUISICAO:
                return lhs.getStatus().getDescricao().compareTo(rhs.getStatus().getDescricao());
            default:
                return  0;
        }
    }
}
