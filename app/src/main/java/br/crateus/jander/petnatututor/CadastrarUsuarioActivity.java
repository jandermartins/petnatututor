package br.crateus.jander.petnatututor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.crateus.jander.petnatututor.model.Tutor;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        FirebaseAuth mAuth;
        FirebaseUser mUser;
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://credible-bay-256721-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        EditText etNome = (EditText) findViewById(R.id.etNomeCadastrar);
        EditText etCpf = (EditText) findViewById(R.id.etCpfCadastrar);
        EditText etTelefone = (EditText) findViewById(R.id.etTelefoneCadastrar);
        EditText etEndereco = (EditText) findViewById(R.id.etEnderecoCadastrar);
        Button btEnviar = (Button) findViewById(R.id.btCadatroUser);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EscreverNovoUsuario(mUser, etNome, etCpf, etTelefone,etEndereco, myRef);
            }
        });
    }
    private void EscreverNovoUsuario(FirebaseUser mUser, EditText etNome, EditText etCpf, EditText etTelefone, EditText etEndereco, DatabaseReference myRef) {
        Tutor nutricionista = new Tutor(mUser.getUid(), etNome.getText().toString(),
                etCpf.getText().toString(), etTelefone.getText().toString(), etEndereco.getText().toString());
        Log.i("teste", nutricionista.getNome());
        myRef.child("usuario").child(etCpf.getText().toString()).setValue(nutricionista);
        startActivity(new Intent(CadastrarUsuarioActivity.this, PaginaInicialActivity.class));
    }
}
