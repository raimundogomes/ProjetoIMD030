package com.imd030.sgr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.imd030.sgr.entiitys.Email;
import com.imd030.sgr.entiitys.Paciente;
import com.imd030.sgr.utils.Constantes;

import com.imd030.sgr.utils.EmailUtil;

public class PacienteActivity extends AppCompatActivity {

    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dados_paciente);

        Intent intent = getIntent();

        paciente= (Paciente) intent.getExtras().get(Constantes.DADOS_PACIENTE_ACTIVITY);

        atualizarTelaDadosPaciente(paciente);

    }

    private void atualizarTelaDadosPaciente(Paciente paciente) {

        TextView prontuario = (TextView) findViewById(R.id.text_prontuario);
        prontuario.setText(paciente.getNumeroProntuario());

        TextView cns = (TextView) findViewById(R.id.text_cns);
        cns.setText(paciente.getCns());


        TextView nome = (TextView) findViewById(R.id.text_paciente);
        nome.setText(paciente.getNome());

        TextView telefone = (TextView) findViewById(R.id.text_fone);
        telefone.setText("  "+paciente.getTelefone());

        TextView email = (TextView) findViewById(R.id.text_email);
        email.setText(paciente.getEmail());

    }

    public void telefonar(View v){

        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+paciente.getTelefone()));
        startActivity(callIntent);
    }

    public void enviarEmail(View v){

        Email email = new Email(new  String[]{paciente.getEmail()}, "assunto", "conteudo");

        Intent intentEmail =  new EmailUtil().enviarEmail(email);

        startActivity(intentEmail);
    }
}
