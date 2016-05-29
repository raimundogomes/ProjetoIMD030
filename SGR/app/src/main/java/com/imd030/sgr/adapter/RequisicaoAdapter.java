package com.imd030.sgr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.imd030.sgr.R;
import com.imd030.sgr.entiitys.Requisicao;
import com.imd030.sgr.utils.DateUtils;

import java.util.List;

/**
 * Created by thiago on 28/05/16.
 */
public class RequisicaoAdapter extends ArrayAdapter<Requisicao>{

    private Context context;
    private List<Requisicao> requisicoes = null;

    public RequisicaoAdapter(Context context,  List<Requisicao> requisicoes) {
        super(context,0, requisicoes);
        this.requisicoes = requisicoes;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Requisicao requisicao = requisicoes.get(position);

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_requisicoes, null);
        }

        //data de requisição
        TextView textViewNumeroRequesicao = (TextView) view.findViewById(R.id.numeroRequisicao);
        textViewNumeroRequesicao.setText(requisicao.getNumeroFormatado());


        //data de requisição
        TextView textViewDataRequesicao = (TextView) view.findViewById(R.id.text_dataRequisicao);
        textViewDataRequesicao.setText(DateUtils.obterDataPorExtenso(requisicao.getDataRequisicao()));

        //status
        TextView textViewStatus = (TextView) view.findViewById(R.id.text_status);
        textViewStatus.setText(requisicao.getStatus().getDescricao());

        //paciente
        TextView textViewPaciente = (TextView) view.findViewById(R.id.text_paciente);
        textViewPaciente.setText(requisicao.getPaciente().getNome());

        //solicitante
        TextView textViewSolicitante = (TextView) view.findViewById(R.id.text_exames);
        textViewSolicitante.setText(requisicao.getExamesFormatados());

        return view;
    }
}
