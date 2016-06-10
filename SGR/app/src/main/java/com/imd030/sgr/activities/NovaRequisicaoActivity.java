package com.imd030.sgr.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.imd030.sgr.R;
import com.imd030.sgr.builder.PacienteBuilder;
import com.imd030.sgr.entiitys.Amostra;
import com.imd030.sgr.entiitys.Exame;
import com.imd030.sgr.entiitys.Laboratorio;
import com.imd030.sgr.entiitys.Paciente;
import com.imd030.sgr.entiitys.Requisicao;
import com.imd030.sgr.entiitys.StatusRequisicao;
import com.imd030.sgr.entiitys.TipoExame;
import com.imd030.sgr.utils.Constantes;
import com.imd030.sgr.utils.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class NovaRequisicaoActivity extends Activity implements
        View.OnClickListener,
        AdapterView.OnItemClickListener{

    public static final int ID_DATA_REQUISICAO_SANGUE = 0;

    public static final int ID_DATA_REQUISICAO_URINA = 1;

    private static final String EDIT_SANGUE = "Sangue";
    private static final String EDIT_URINA = "Urina";
    private static final String LABORATRIO = "Laboratorio";
    private static final String PACIENTE = "Paciente";

    Laboratorio laboratorio1 = new Laboratorio("Microbiologia", "84987879798");
    Laboratorio laboratorio2 = new Laboratorio("Citologia", "22222222222");

    private List<Paciente> listPacientes = PacienteBuilder.getPacientes();

    private List<Laboratorio> listLaboratorio = new ArrayList<Laboratorio>();

    private String[] arrayPaciente;

    private AutoCompleteTextView pacientes;

    private Spinner spinnerLaboratorio;

    private CheckBox checkBoxSangue;

    private Paciente pacienteSelecionado;

    private ImageButton imgBtnCalendarioSangue;

    private static TextView txtDataAmostraExameSangue;

    private static Date dataAmostraExameSangue;

    private DatePickerDialog datePickerDialogSangue;

    private CheckBox checkBoxUrina;

    private ImageButton imgBtnCalendarioUrina;

    private static TextView txtDataAmostraExameUrina;

    private static Date dataAmostraExameUrina;

    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_requisicao);

        arrayPaciente = new String[listPacientes.size()];

        for (int i = 0; i < listPacientes.size(); i++){
            arrayPaciente[i] = listPacientes.get(i).getNumeroProntuario() + " - " + listPacientes.get(i).getNome();
        }

        ArrayAdapter<Paciente> adp = new ArrayAdapter<Paciente>(this, android.R.layout.simple_dropdown_item_1line, listPacientes);
        pacientes = (AutoCompleteTextView) findViewById(R.id.pacientes);
        pacientes.setOnItemClickListener(this);
        pacientes.setAdapter(adp);

        //Spinner dos laboratórios
        spinnerLaboratorio = (Spinner) findViewById(R.id.spinnerLaboratorio);

        listLaboratorio.add(laboratorio1);
        listLaboratorio.add(laboratorio2);

        ArrayAdapter adapterLaboratorio = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listLaboratorio);
        adapterLaboratorio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLaboratorio.setAdapter(adapterLaboratorio);

        //exame de sangue
        checkBoxSangue = (CheckBox) findViewById(R.id.checkBoxExameSangue);
        checkBoxSangue.setOnClickListener(this);

        txtDataAmostraExameSangue = (TextView) findViewById(R.id.editTextDataAmostraExameSangue);

        imgBtnCalendarioSangue = (ImageButton) findViewById(R.id.imageButtonCalendarioSangue);
        imgBtnCalendarioSangue.setOnClickListener(this);

        //exame de urina
        checkBoxUrina = (CheckBox) findViewById(R.id.checkBoxExameUrina);
        checkBoxUrina.setOnClickListener(this);

        txtDataAmostraExameUrina = (TextView) findViewById(R.id.editTextDataAmostraExameUrina);

        imgBtnCalendarioUrina = (ImageButton) findViewById(R.id.imageButtonCalendarioUrina);
        imgBtnCalendarioUrina.setOnClickListener(this);

        //salvar
        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(EDIT_SANGUE, (String) txtDataAmostraExameSangue.getText().toString());
        outState.putString(EDIT_URINA, (String) txtDataAmostraExameUrina.getText().toString());
        outState.putInt(LABORATRIO, spinnerLaboratorio.getSelectedItemPosition());
        outState.putSerializable(PACIENTE, pacienteSelecionado);
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtDataAmostraExameSangue.setText(savedInstanceState.getString(EDIT_SANGUE));
        txtDataAmostraExameUrina.setText(savedInstanceState.getString(EDIT_URINA));
        pacienteSelecionado = (Paciente) savedInstanceState.getSerializable(PACIENTE);
        spinnerLaboratorio.setSelection(savedInstanceState.getInt(LABORATRIO));
    }

    @Override
    public void onClick(View v) {

        if(v == checkBoxSangue && !checkBoxSangue.isChecked()){
            txtDataAmostraExameSangue.setText("");
        }
        else if(v == checkBoxUrina && !checkBoxUrina.isChecked()){
            txtDataAmostraExameUrina.setText("");
        } else if(v == imgBtnCalendarioSangue){
            showDialog(ID_DATA_REQUISICAO_SANGUE);
        } else if(v == imgBtnCalendarioUrina){
            showDialog(ID_DATA_REQUISICAO_URINA);
        } else if(v == buttonSalvar){
            Requisicao requisicao = montarRequisicao();
            if(validarRequisicao(requisicao)){
                salvarRequisicao(requisicao);
            }else{
                String mensagemErro = getString(R.string.erro_preenchimento_obrigatorio);
                Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    private boolean validarRequisicao(Requisicao requisicao){
        return requisicao.getPaciente() != null && requisicao.getLaboratorio()!= null;
    }

    private void salvarRequisicao(Requisicao requisicao) {
        Intent result = new Intent();
        result.putExtra(Constantes.REQUISICAO_NOVA_ACTIVITY, requisicao);
        setResult(RESULT_OK, result);
        finish();
    }

    private Requisicao montarRequisicao() {
        Requisicao requisicao = new Requisicao();

        requisicao.setStatus(StatusRequisicao.SOLICITADA);
        Random gerador = new Random();
        requisicao.setNumero(gerador.nextInt(10000));
        requisicao.setDataRequisicao(new Date());

        requisicao.setPaciente(pacienteSelecionado);
        requisicao.setLaboratorio((Laboratorio) spinnerLaboratorio.getSelectedItem());

        List<Exame> listExames = new ArrayList<>();
        if(checkBoxSangue.isChecked()){
            Exame exameSangue = new Exame(TipoExame.SANGUE);
            Amostra amostraSangue = new Amostra();
            amostraSangue.setDataColeta(dataAmostraExameSangue);
            exameSangue.setAmostra(amostraSangue);
            listExames.add(exameSangue);
        }
        if(checkBoxUrina.isChecked()){
            Exame exameUrina = new Exame(TipoExame.URINA);
            Amostra amostraUrina = new Amostra();
            amostraUrina.setDataColeta(dataAmostraExameUrina);
            exameUrina.setAmostra(amostraUrina);
            listExames.add(exameUrina);
        }
        requisicao.setExames(listExames);

        return requisicao;
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar calendario = GregorianCalendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case ID_DATA_REQUISICAO_SANGUE:
                return new DatePickerDialog(this, mDateSetListenerSangue, ano, mes, dia);
            case ID_DATA_REQUISICAO_URINA:
                return new DatePickerDialog(this, mDateSetListenerUrina, ano, mes, dia);
            default:
                break;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListenerSangue = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dataAmostraExameSangue = DateUtils.obterData(year, monthOfYear, dayOfMonth);
            NovaRequisicaoActivity.txtDataAmostraExameSangue.setText(DateUtils.obterDataPorExtenso(year, monthOfYear, dayOfMonth));
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListenerUrina = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dataAmostraExameUrina = DateUtils.obterData(year, monthOfYear, dayOfMonth);
            NovaRequisicaoActivity.txtDataAmostraExameUrina.setText(DateUtils.obterDataPorExtenso(year, monthOfYear, dayOfMonth));
        }
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pacienteSelecionado = listPacientes.get(position);
    }
}
