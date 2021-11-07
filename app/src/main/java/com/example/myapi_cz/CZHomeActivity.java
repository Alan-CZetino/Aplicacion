package com.example.myapi_cz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.myapi_cz.APICZ.endPointsCZ;
import com.example.myapi_cz.AdaptersCZ.NotasListAdapterCZ;
import com.example.myapi_cz.ModelsCZ.NotasCZ;
import com.example.myapi_cz.ModelsCZ.NotasList;
import com.example.myapi_cz.ServiceCZ.ServiceAPICZ;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CZHomeActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    List<NotasCZ> notasCZList;
    NotasListAdapterCZ adapter;
    FloatingActionButton btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czhome);
        recyclerView = findViewById(R.id.rcvNotas);
        btnadd = findViewById(R.id.btnAdd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CZHomeActivity.this,CZaddmodActivity.class);
                intent.putExtra("ID","");
                intent.putExtra("TITULO","");
                intent.putExtra("DESCRIPCION","");
                intent.putExtra("FECHA","");
                intent.putExtra("USUARIO","");
                startActivity(intent);
            }
        });


        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.gris1)));

        ServiceAPICZ serviceAPICZ = endPointsCZ.getRetrofit().create(ServiceAPICZ.class);
        Call<List<NotasCZ>> call = serviceAPICZ.getNotasList();
        call.enqueue(new Callback<List<NotasCZ>>() {
            @Override
            public void onResponse(Call<List<NotasCZ>> call, Response<List<NotasCZ>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                notasCZList=response.body();
                adapter = new NotasListAdapterCZ(notasCZList,CZHomeActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<NotasCZ>> call, Throwable t) {
                Log.e("TAG","ERROR"+t.getLocalizedMessage());
            }
        });


    }



}