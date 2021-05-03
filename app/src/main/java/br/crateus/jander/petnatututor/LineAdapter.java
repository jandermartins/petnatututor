package br.crateus.jander.petnatututor;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import br.crateus.jander.petnatututor.model.Pet;

public class LineAdapter extends RecyclerView.Adapter<LineHolder> {

    private final ArrayList<Pet> mPets;

    public LineAdapter(ArrayList<Pet> mPets) {
        this.mPets = mPets;
    }

    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext()).inflate
                (R.layout.meuspetsrecyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolder holder, int position) {
        holder.nomePet.setText(String.format(Locale.getDefault(), "%s, %s",
                mPets.get(position).getIdPet(),
                mPets.get(position).getNome()));

        holder.btMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                abrirPet(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPets != null ? mPets.size() : 0;
    }

    private void abrirPet(View v, int position) {
        Intent i = new Intent(v.getContext(), VerPetActivity.class);
        i.putExtra("idPet", mPets.get(position).getIdPet());
        //Log.i("Teste", mPets.get(position).getCpfTutor());
        i.putExtra("nomePet", mPets.get(position).getNome());
        i.putExtra("cpfTutor", mPets.get(position).getCpfTutor());
        i.putExtra("idVeterinario", mPets.get(position).getIdVeterinario());
        i.putExtra("especie", mPets.get(position).getEspecie());
        i.putExtra("raca", mPets.get(position).getRaca());
        i.putExtra("peso", mPets.get(position).getPeso());
        i.putExtra("sexo", mPets.get(position).getSexo());
        i.putExtra("nascimento", mPets.get(position).getNascimento());
        v.getContext().startActivity(i);
    }

    public void inserirItem(Pet pet){
        mPets.add(pet);
        Log.i("pet", pet.getNome());
        notifyItemInserted(getItemCount());
    }
}
