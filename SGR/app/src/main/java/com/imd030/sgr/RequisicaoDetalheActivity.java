package com.imd030.sgr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.imd030.sgr.adapter.ExameAdapter;
import com.imd030.sgr.adapter.RequisicaoAdapter;
import com.imd030.sgr.builder.RequisicaoBuilder;
import com.imd030.sgr.entiitys.Exame;
import com.imd030.sgr.entiitys.Requisicao;
import com.imd030.sgr.utils.Constantes;
import com.imd030.sgr.utils.DateUtils;

import java.util.List;

public class RequisicaoDetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_requisicao_detalhe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Intent intent = getIntent();

        Requisicao requisicao = (Requisicao)intent.getExtras().get(Constantes.REQUISICAO_DETALHE_ACTIVITY);

        super.setTitle( " Nº " + requisicao.getNumeroFormatado() + "                 "+requisicao.getStatus().getDescricao());
        criarDadosRequisicao(requisicao);

        ListView listView = (ListView) findViewById(R.id.list_exames);

        final ExameAdapter exameAdapter = new ExameAdapter(this,  requisicao.getExames());
        listView.setAdapter(exameAdapter);
    }

    private void criarDadosRequisicao(Requisicao requisicao) {

        TextView textViewDataRequesicao = (TextView) findViewById(R.id.text_dataRequisicao);
        textViewDataRequesicao.setText(DateUtils.obterDataPorExtenso(requisicao.getDataRequisicao()));

        TextView prontuario = (TextView) findViewById(R.id.text_prontuario);
        prontuario.setText(requisicao.getPaciente().getNumeroProntuario());

        TextView cns = (TextView) findViewById(R.id.text_cns);
        cns.setText(requisicao.getPaciente().getCns());

        //paciente
        TextView textViewPaciente = (TextView) findViewById(R.id.text_paciente);
        textViewPaciente.setText(requisicao.getPaciente().getNome());

    }

}
