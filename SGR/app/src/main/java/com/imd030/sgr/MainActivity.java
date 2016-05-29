package com.imd030.sgr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.imd030.sgr.adapter.RequisicaoAdapter;
import com.imd030.sgr.bulder.ExamesBulder;
import com.imd030.sgr.entiitys.Paciente;
import com.imd030.sgr.entiitys.Requisicao;
import com.imd030.sgr.entiitys.Solicitante;
import com.imd030.sgr.entiitys.StatusRequisicao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(super.getTitle() + " - Requisições");

        setContentView(R.layout.activity_main);

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

        //listView
        ListView listView = (ListView) findViewById(R.id.list_requisicao);
        List<Requisicao> requisicoes = gerarRequisicoes();

        final RequisicaoAdapter requisicoesAdapter = new RequisicaoAdapter(this,  requisicoes);
        listView.setAdapter(requisicoesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Requisicao> gerarRequisicoes() {
        List<Requisicao> requisicoes = new ArrayList<Requisicao>();
        requisicoes.add(criarRequisicao("Solicitante 1", "Paciente 1"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Paciente 2"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Paciente 3"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Paciente 4"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Paciente 5"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Paciente 6"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Paciente 7"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Paciente 8"));
        return requisicoes;
    }

    private Requisicao criarRequisicao(String nomeSolicitante, String nomePaciente){

        Requisicao requisicao = new Requisicao();

        requisicao.setDataRequisicao(new Date());

        requisicao.setStatus(StatusRequisicao.SOLICITADA);

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
