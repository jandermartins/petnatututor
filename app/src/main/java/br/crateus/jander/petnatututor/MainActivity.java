package br.crateus.jander.petnatututor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email, senha;
    Button btEntrar;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onStart() {
        VerificaLogado();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        EditText etEmail, etSenha;
        Button btEntrar;

        etEmail = (EditText) findViewById(R.id.etEmailEntrar);
        etSenha = (EditText) findViewById(R.id.etSenhaEntrar);
        btEntrar = (Button) findViewById(R.id.btEntrar);



        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(etEmail.getText().toString(),
                        etSenha.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("ok", "signInWithEmail:success");
                            mUser = mAuth.getCurrentUser();
                            startActivity(new Intent(MainActivity.this, PaginaInicialActivity.class));
                        }if(task.isCanceled()) {
                            mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(),
                                    etSenha.getText().toString()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.i("user", "criado");
                                    mUser = mAuth.getCurrentUser();
                                    startActivity(new Intent(MainActivity.this, CadastrarUsuarioActivity.class));
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void VerificaLogado() {
        mUser = mAuth.getCurrentUser();
        if(mUser != null){
            startActivity(new Intent(MainActivity.this, PaginaInicialActivity.class));
        }
    }
}