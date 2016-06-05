package com.imd030.sgr.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.imd030.sgr.R;
import com.imd030.sgr.utils.Constantes;


public class ConfiguracoesActivity extends Activity{

	private RadioGroup radioOrdenacaoGroup;

	private RadioButton radioNumeroRequisicaoButton;
	private RadioButton radioDataRequisicaoButton;
	private RadioButton radioNomePacienteButton;
	private RadioButton radioSituacaoButton;

	private int criterioSelecionado;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_configuracoes);

		Intent intent = getIntent();

		criterioSelecionado = (int) intent.getSerializableExtra(Constantes.CONFIGURACAO_ACTIVITY);

		radioOrdenacaoGroup = (RadioGroup) findViewById(R.id.radio_group_ordenacao);

		radioNumeroRequisicaoButton = (RadioButton) findViewById(R.id.radio_numero_requisicao);
		radioDataRequisicaoButton = (RadioButton) findViewById(R.id.radio_data_requisicao);
		radioNomePacienteButton = (RadioButton) findViewById(R.id.radio_nome_paciente);
		radioSituacaoButton = (RadioButton) findViewById(R.id.radio_status_requisicao);

		switch (criterioSelecionado){
			case Constantes.CRITERIO_DATA_REQUISICAO:
				radioDataRequisicaoButton.setChecked(true);
				break;
			case Constantes.CRITERIO_NOME_PACIENTE:
				radioNomePacienteButton.setChecked(true);
				break;
			case Constantes.CRITERIO_NUMERO_REQUISICAO:
				radioNumeroRequisicaoButton.setChecked(true);
				break;
			case Constantes.CRITERIO_STATUS_REQUISICAO:
				radioSituacaoButton.setChecked(true);
				break;
			default:
				break;
		}
    }

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();

		switch(view.getId()) {
			case R.id.radio_data_requisicao:
				if (checked)
					criterioSelecionado = Constantes.CRITERIO_DATA_REQUISICAO;
					break;
			case R.id.radio_nome_paciente:
				if (checked)
					criterioSelecionado = Constantes.CRITERIO_NOME_PACIENTE;
					break;
			case R.id.radio_numero_requisicao:
				if (checked)
					criterioSelecionado = Constantes.CRITERIO_NUMERO_REQUISICAO;
					break;
			case R.id.radio_status_requisicao:
				if (checked)
					criterioSelecionado = Constantes.CRITERIO_STATUS_REQUISICAO;
					break;
			default:
				break;
		}

		Intent result = new Intent();
		result.putExtra(Constantes.CONFIGURACAO_ACTIVITY, criterioSelecionado);
		setResult(RESULT_OK, result);
		finish();
	}
}