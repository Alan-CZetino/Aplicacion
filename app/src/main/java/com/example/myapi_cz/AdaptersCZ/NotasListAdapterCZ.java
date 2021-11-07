package com.example.myapi_cz.AdaptersCZ;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapi_cz.ModelsCZ.NotasCZ;
import com.example.myapi_cz.R;

import java.util.ArrayList;
import java.util.List;

public class NotasListAdapterCZ extends RecyclerView.Adapter<NotasListAdapterCZ.ViewHolder>{

    List<NotasCZ> notaList;
    Context context;

    //constructor


    public NotasListAdapterCZ(List<NotasCZ> notaList, Context context) {
        this.notaList = notaList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowscz, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotasCZ notas = notaList.get(position);

        holder.tvId.setText(notas.getId()+"");
        holder.tvTitle.setText(notas.getTitulo());
        holder.tvDescription.setText(notas.getDescripcion());
        holder.tvdate.setText(notas.getFecha());
        holder.tvuser.setText(notas.getUsuario());
    }

    @Override
    public int getItemCount() {
        return notaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvId,tvTitle, tvDescription,tvdate,tvuser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.IDNota);
            tvTitle = itemView.findViewById(R.id.tituloNota);
            tvDescription = itemView.findViewById(R.id.textoNota);
            tvdate = itemView.findViewById(R.id.fechaNota);
            tvuser = itemView.findViewById(R.id.UserNota);
        }
    }
}
