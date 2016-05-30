package com.imd030.sgr.entiitys;

import java.io.Serializable;

/**
 * Created by thiago on 29/05/16.
 */
public class Exame implements Serializable{

    private TipoExame tipoExame;

    private Amostra amostra;

    public Exame(TipoExame tipo) {
        tipoExame = tipo;
    }


    @Override
    public String toString() {
        return tipoExame.getDescricao();
    }

    public String getDescricao(){
        return tipoExame.getDescricao();
    }

}
