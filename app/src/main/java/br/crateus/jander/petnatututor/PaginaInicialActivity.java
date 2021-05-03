package br.crateus.jander.petnatututor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PaginaInicialActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial);

        Button btMeusPets = (Button) findViewById(R.id.btMeusPets);
        Button btSair = (Button) findViewById(R.id.btSair);
        Button btDados = (Button) findViewById(R.id.btMeusDados);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        Log.i("id", mUser.getUid());

        btDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaginaInicialActivity.this, MeusDadosActivity.class));
            }
        });


        btMeusPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaginaInicialActivity.this, MeusPetsActivity.class));
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(PaginaInicialActivity.this, MainActivity.class));
            }
        });
    }
}
