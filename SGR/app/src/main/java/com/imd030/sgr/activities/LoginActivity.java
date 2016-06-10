package com.imd030.sgr.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.imd030.sgr.R;
import com.imd030.sgr.utils.Constantes;

public class LoginActivity extends Activity {

	private EditText usuario;
	private EditText senha;
	private CheckBox manterConectado;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        manterConectado = (CheckBox) findViewById(R.id.manterConectado);

//		if(savedInstanceState==null) {

		    SharedPreferences preferencias = getSharedPreferences(Constantes.PREF_NAME, MODE_PRIVATE);
			boolean conectado = preferencias.getBoolean(Constantes.CONFIGURACAO_CONECTADO, false);

			if (conectado) {
				finish();
				startActivity(new Intent(this, ListaRequisicaoActivity.class));
			}
//		}
    }
    
    public void entrarOnClick(View v){
    	String usuarioInformado = usuario.getText().toString();
    	String senhaInformada = senha.getText().toString();
    	
    	if("android".equals(usuarioInformado)  && "imd@2016".equals(senhaInformada)){
			SharedPreferences preferencias = getSharedPreferences(Constantes.PREF_NAME, MODE_PRIVATE);
    		Editor editor = preferencias.edit();
    		editor.putBoolean(Constantes.CONFIGURACAO_CONECTADO, manterConectado.isChecked());
    		editor.commit();
			finish();
    		startActivity(new Intent(this, ListaRequisicaoActivity.class));
    	}
    	else{
    		String mensagemErro = getString(R.string.erro_autenticacao);
    		Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
    		toast.show();
    	}
    }

	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);

	}
}