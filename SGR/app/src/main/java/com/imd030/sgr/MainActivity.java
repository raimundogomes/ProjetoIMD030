package com.imd030.sgr;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.imd030.sgr.adapter.RequisicaoAdapter;
import com.imd030.sgr.builder.RequisicaoBuilder;
import com.imd030.sgr.entiitys.Requisicao;
import com.imd030.sgr.entiitys.StatusRequisicao;
import com.imd030.sgr.utils.Constantes;
import com.imd030.sgr.utils.DateUtils;
import com.imd030.sgr.utils.DetectaConexao;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DialogInterface.OnClickListener {

    public static final String SUBJECT_EMAIL = "[SGR] - Encaminhamento de Requisição";
    private List<Requisicao> requisicoes = new RequisicaoBuilder().gerarRequisicoes();

    private Requisicao requisicaoSelecionada = null;

    private RequisicaoAdapter requisicaoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(super.getTitle() + " - Requisições");

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //listView
        ListView listView = (ListView) findViewById(R.id.list_requisicao);

        registerForContextMenu(listView);

        requisicaoAdapter = new RequisicaoAdapter(this,  requisicoes);

        listView.setAdapter(requisicaoAdapter);

        listView.setOnItemClickListener(this);

        listView.setOnItemClickListener(this);

        listView.setOnItemLongClickListener(this);
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

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sicronizarRequisicoes(List<Requisicao> requisicoes) {
        final Random myRandom = new Random();

        int num = myRandom.nextInt(requisicoes.size()-1);
        Requisicao requisicao =  requisicoes.get(num);


        if(requisicao.getStatus()==StatusRequisicao.SOLICITADA){
            requisicao.setStatus(StatusRequisicao.CANCELADA);
        }
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
            default:
                break;
        }
        return super.onContextItemSelected(item);
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
}