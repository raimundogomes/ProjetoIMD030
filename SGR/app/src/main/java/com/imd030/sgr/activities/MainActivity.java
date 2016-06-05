package com.imd030.sgr.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.imd030.sgr.R;
import com.imd030.sgr.adapter.RequisicaoAdapter;
import com.imd030.sgr.builder.RequisicaoBuilder;
import com.imd030.sgr.comparator.RequisicaoComparator;
import com.imd030.sgr.entiitys.Requisicao;
import com.imd030.sgr.entiitys.StatusRequisicao;
import com.imd030.sgr.utils.Constantes;
import com.imd030.sgr.utils.DateUtils;
import com.imd030.sgr.utils.DetectaConexao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener,
        DialogInterface.OnClickListener,
        TextWatcher {

    public static final String SUBJECT_EMAIL = "[SGR] - Encaminhamento de Requisição";

    private List<Requisicao> requisicoes = new RequisicaoBuilder().gerarRequisicoes();

    private List<Requisicao> requisicoesfiltradas;

    private Requisicao requisicaoSelecionada = null;

    private RequisicaoAdapter requisicaoAdapter;

    private int criterioOrdenacaoSelecionado = Constantes.CRITERIO_DATA_REQUISICAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(super.getTitle() + " - Requisições");

        setContentView(R.layout.activity_main);

        //listView
        ListView listView = (ListView) findViewById(R.id.list_requisicao);

        registerForContextMenu(listView);

        requisicoesfiltradas = ((List) ((ArrayList) requisicoes).clone());

        Collections.sort(requisicoesfiltradas, new RequisicaoComparator(criterioOrdenacaoSelecionado));

        requisicaoAdapter = new RequisicaoAdapter(this,  requisicoesfiltradas);

        listView.setAdapter(requisicaoAdapter);

        listView.setOnItemClickListener(this);

        listView.setOnItemLongClickListener(this);

        //edit search
        EditText editSearch = (EditText) findViewById(R.id.edit_search);

        editSearch.addTextChangedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_sair:
                finish();
                break;
            case R.id.menu_sincronizar:
                DetectaConexao detectaConexao = new DetectaConexao(getApplicationContext());
                if(detectaConexao.existeConexao()){
                    sicronizarRequisicoes(requisicoes);
                    exibirMensagemSicronizacao();
                }
                else{
                    Toast toast = Toast.makeText(this, DetectaConexao.FALHA_CONEXAO,
                            Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
            case R.id.menu_configuracoes:
                abrirConfiguracoes();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void abrirConfiguracoes() {
        Intent intent = new Intent(this, ConfiguracoesActivity.class);
        intent.putExtra(Constantes.CONFIGURACAO_ACTIVITY, criterioOrdenacaoSelecionado);
        startActivityForResult(intent, Constantes.INDICE_ACTIVITY_CONFIGURACOES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constantes.INDICE_ACTIVITY_CONFIGURACOES:
                if(resultCode == RESULT_OK){
                    criterioOrdenacaoSelecionado = (int) data.getSerializableExtra(Constantes.CONFIGURACAO_ACTIVITY);
                    Collections.sort(requisicoesfiltradas, new RequisicaoComparator(criterioOrdenacaoSelecionado));
                    requisicaoAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void sicronizarRequisicoes(List<Requisicao> requisicoes) {
        final Random myRandom = new Random();

        int num = myRandom.nextInt(requisicoes.size()-1);
        Requisicao requisicao =  requisicoes.get(num);


        if(requisicao.getStatus()==StatusRequisicao.SOLICITADA){
            requisicao.setStatus(StatusRequisicao.CANCELADA);
        }

        Collections.sort(requisicoesfiltradas, new RequisicaoComparator(criterioOrdenacaoSelecionado));
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view,
                            int position, long id) {

        Requisicao requisicaSelecionada = requisicoes.get(position);

        Intent acao = new Intent(MainActivity.this, RequisicaoDetalheActivity.class);

        acao.putExtra(Constantes.REQUISICAO_DETALHE_ACTIVITY, requisicaSelecionada);

        startActivity(acao);

    }

    public void exibirMensagemSicronizacao() {

       final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
       dialog.setTitle("Sincronizando as requisições...");
       dialog.setMessage("Aguarde, por favor.");
       dialog.setIndeterminate(true);
       dialog.setCancelable(true);
       dialog.show();

      long delayInMillis = 2000;
      Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            }, delayInMillis);

        }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        requisicaoSelecionada = (Requisicao) parent.getItemAtPosition(position);
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto_requisicao, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cancelar_requisicao:
                cancelarRequisicao();
                break;
            case R.id.menu_encaminhar_requisicao:
                encaminharRequisicao();
                break;

            case R.id.menu_dados_paciente:
                visualizarDadosPaciente();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void visualizarDadosPaciente() {
        Intent acao = new Intent(MainActivity.this, PacienteActivity.class);

        acao.putExtra(Constantes.DADOS_PACIENTE_ACTIVITY, requisicaoSelecionada.getPaciente());

        startActivity(acao);

    }

    private void encaminharRequisicao() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822"); //configuração necessária para filtrar as aplicações que não enviam email
        intent.putExtra(Intent.EXTRA_EMAIL, requisicaoSelecionada.getPaciente().getEmail());
        intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT_EMAIL);
        intent.putExtra(Intent.EXTRA_TEXT, montarCorpoEmail());

        try {
            startActivity(Intent.createChooser(intent, "Enviar e-mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Não existe aplicativo que enviam e-mail instalados.", Toast.LENGTH_SHORT).show();
        }
    }

    private String montarCorpoEmail() {
        String corpoEmail = new String();
        corpoEmail = "Prezado(a) " + requisicaoSelecionada.getPaciente().getNome() + " segue as informações da sua requisição:" + "\n" +
                     "Número da requisição: " + requisicaoSelecionada.getNumeroFormatado() + "\n" +
                     "Data da requisição: " + DateUtils.obterDataPorExtenso(requisicaoSelecionada.getDataRequisicao()) + "\n" +
                     "Status da requisição: " + requisicaoSelecionada.getStatus().getDescricao() + "\n" +
                     "Exames: " + requisicaoSelecionada.getExamesFormatados();
        return corpoEmail;
    }

    private void cancelarRequisicao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirma o cancelamento da requisção de número " + requisicaoSelecionada.getNumeroFormatado() + "?");
        builder.setPositiveButton("Sim", this);
        builder.setNegativeButton("Não", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        requisicaoSelecionada.setStatus(StatusRequisicao.CANCELADA);
        requisicaoAdapter.notifyDataSetChanged();
        requisicaoSelecionada = null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //TODO: pesquisar forma mais eficiente de realizar a consulta - ver Filterable

        //texto digitado
        String nomePaciente = s.toString().toLowerCase();

        //limpa a lista
        requisicoesfiltradas.clear();

        //atualiza a lista
        for(Requisicao requisicao: requisicoes){
            if(requisicao.getPaciente().getNome().toLowerCase().contains(nomePaciente)){
                requisicoesfiltradas.add(requisicao);
            }
        }

        requisicaoAdapter.notifyDataSetChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {}
}