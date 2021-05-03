package br.crateus.jander.petnatututor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.crateus.jander.petnatututor.model.Pet;
import br.crateus.jander.petnatututor.model.Tutor;

public class MeusDadosActivity extends AppCompatActivity {

    EditText etNome, etCpf, etTelefone, etEndereco;
    Button btEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://credible-bay-256721-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference();


        etNome = (EditText) findViewById(R.id.etNomeMd);
        etCpf = (EditText) findViewById(R.id.etCpfMd);
        etTelefone = (EditText) findViewById(R.id.etTelefoneMd);
        etEndereco = (EditText) findViewById(R.id.etEnderecoMd);

        btEditar = (Button) findViewById(R.id.btSalvarMD);

        etNome.setText("Alex Nascimento");
        etCpf.setText("070169123332");
        etTelefone.setText("859929387347");
        etEndereco.setText("Jose Valter");


//        myRef.child("usuario").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                etNome.setText(snapshot.getValue(Tutor.class).getNome());
//                etCpf.setText(snapshot.getValue(Tutor.class).getCpf());
//                etTelefone.setText(snapshot.getValue(Tutor.class).getTelefone());
//                etEndereco.setText(snapshot.getValue(Tutor.class).getEndereco());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tutor tutor = new Tutor(mUser.getUid(), etNome.getText().toString(), etCpf.getText().toString(),
                        etTelefone.getText().toString(), etEndereco.getText().toString());
                myRef.child("tutor").child(mUser.getUid()).setValue(tutor);
            }
        });
    }
}