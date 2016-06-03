package com.imd030.sgr.utils;

import android.content.Intent;
import android.widget.Toast;

import com.imd030.sgr.entiitys.Email;

/**
 * Created by Neto on 02/06/2016.
 */
public class EmailUtil {
    public Intent enviarEmail(Email email) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822"); //configuração necessária para filtrar as aplicações que não enviam email
        intent.putExtra(Intent.EXTRA_EMAIL, email.getEnderecos());
        intent.putExtra(Intent.EXTRA_SUBJECT, email.getAssunto());
        intent.putExtra(Intent.EXTRA_TEXT, email.getConteudo());

        return Intent.createChooser(intent, "Enviar e-mail...");
    }
}
