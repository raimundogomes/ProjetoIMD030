package com.imd030.sgr.entiitys;

/**
 * Created by thiago on 29/05/16.
 */
public class Exame {

    private TipoExame tipoExame;

    public Exame(TipoExame tipo) {
        tipoExame = tipo;
    }


    @Override
    public String toString() {
        return tipoExame.getDescricao();
    }
}
