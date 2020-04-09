package com.example.barbearia3;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class AdapterFotos extends RecyclerView.Adapter<AdapterFotos.MyViewHolder> {

    Context context;
    ArrayList<ClasseFotos> anuncios;

    public AdapterFotos(Context c, ArrayList<ClasseFotos> p) {
        context = c;
        anuncios = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFotos.MyViewHolder holder, int position) {

        Picasso.get().load(anuncios.get(position).getImg()).resize(900, 700).centerInside().onlyScaleDown().into(holder.image);
        holder.onclick(position);

    }

    @Override
    public int getItemCount() {
        return anuncios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bt;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
         //   produto = (TextView) itemView.findViewById(R.id.produto);
         //   descricao = (TextView) itemView.findViewById(R.id.descricao);
              image = (ImageView) itemView.findViewById(R.id.img);

        }

        public void onclick(final int position) {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String estilo = anuncios.get(position).getEstilo();
                    if(estilo.length()>0){
                    Toast toast = Toast.makeText(context, "Estilo de corte "+estilo, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    toast.show();
                    }
                    final Animation zoomAnim = AnimationUtils.loadAnimation(context, R.anim.zoom);
                    image.startAnimation(zoomAnim);


                }
            });
        }
    }
}

