package com.example.barbearia3;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterAdmFotos extends RecyclerView.Adapter<AdapterAdmFotos.MyViewHolder> {

    Context context;
    ArrayList<ClasseFotos> anuncios;

    public AdapterAdmFotos(Context c, ArrayList<ClasseFotos> p) {
        context = c;
        anuncios = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAdmFotos.MyViewHolder holder, int position) {

        Picasso.get().load(anuncios.get(position).getImg()).resize(900, 700).centerInside().onlyScaleDown().into(holder.image);
        holder.onclick(position);

    }

    @Override
    public int getItemCount() {
        return anuncios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img);

        }

        public void onclick(final int position) {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String id = anuncios.get(position).getId();

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setTitle("Excluir Imagem?");
                    alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseStorage storage;
                            final StorageReference storageReference;
                            storage = FirebaseStorage.getInstance();
                            storageReference = storage.getReference("Imagem" + id);
                            DatabaseReference mDatabase;

                            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("imagens").child(id);
                            mDatabase.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(context, "Excluído com sucesso", Toast.LENGTH_SHORT)
                                                    .show();

                                        }
                                    });
                                }
                            });
                        }
                    });
                    alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }

                    });
                    alertDialog.show();
                }
            });


        }
    }

        }


