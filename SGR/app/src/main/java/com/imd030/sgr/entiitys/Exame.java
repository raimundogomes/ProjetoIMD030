package com.imd030.sgr.entiitys;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by thiago on 29/05/16.
 */
public class Exame implements Serializable{

    private TipoExame tipoExame;

    private Amostra amostra;

    private ResultadoExame resultadoExame;

    public Exame(TipoExame tipo) {
        tipoExame = tipo;
        amostra = new Amostra();
    }


    @Override
    public String toString() {
        return tipoExame.getDescricao();
    }

    public String getDescricao(){
        return tipoExame.getDescricao();
    }


    public Amostra getAmostra() {
        return amostra;
    }

    public void setAmostra(Amostra amostra) {
        this.amostra = amostra;
    }

    public Date getDataColeta() {
        if(amostra !=null){
            return amostra.getDataColeta();
        }
        return null;
    }

    public String getSituacaoAmostra() {
        if(amostra !=null){
          return amostra.getSituacaoAmostra().getDescricao();
        }

        return "";
    }

    public String getResultado(){

        if(resultadoExame==null){
            return "";
        }

        return resultadoExame.getDescricao();

    }

}
