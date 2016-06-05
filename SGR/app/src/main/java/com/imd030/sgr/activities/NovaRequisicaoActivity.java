package com.imd030.sgr.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.imd030.sgr.R;

public class NovaRequisicaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_requisicao);

        super.setTitle(super.getTitle() + " - Nova Requisição");
    }
}
