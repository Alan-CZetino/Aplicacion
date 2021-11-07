package com.example.myapi_cz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapi_cz.APICZ.endPointsCZ;
import com.example.myapi_cz.ModelsCZ.NotasCZ;
import com.example.myapi_cz.ServiceCZ.ServiceAPICZ;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CZaddmodActivity extends AppCompatActivity {
    ServiceAPICZ serviceAPICZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_czaddmod);
        EditText edtid=(EditText)findViewById(R.id.edtId);
        final EditText edttitulo=(EditText)findViewById(R.id.edtTitulo);
        final EditText edtdescripcion=(EditText)findViewById(R.id.edtDescripcion);
        final EditText edtfecha=(EditText)findViewById(R.id.edtDescripcion);
        final EditText edtusuario=(EditText)findViewById(R.id.edtUsuario);



        Button btnSave=(Button)findViewById(R.id.addNota);
        Button btnVolver=(Button)findViewById(R.id.btnVolver);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);


        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        String title=bundle.getString("TITULO");
        String descrep=bundle.getString("DESCRIPCION");
        String date=bundle.getString("FECHA");
        String user=bundle.getString("USUARIO");

        edtid.setText(id);
        edttitulo.setText(title);
        edtdescripcion.setText(descrep);
        edtfecha.setText(date);
        edtusuario.setText(user);
        if(id.trim().length()==0||id.equals("")){
            edtid.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotasCZ notasCZ=new NotasCZ();
                notasCZ.setTitulo(edttitulo.getText().toString());
                notasCZ.setDescripcion(edtdescripcion.getText().toString());
                notasCZ.setFecha(edtfecha.getText().toString());
                notasCZ.setUsuario(edtusuario.getText().toString());
                if(id.trim().length()==0||id.equals("")){
                    addNota(notasCZ);
                    Intent intent =new Intent(CZaddmodActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    updateNota(notasCZ,Integer.valueOf(id));
                    Intent intent =new Intent(CZaddmodActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNota(Integer.valueOf(id));
                Intent intent =new Intent(CZaddmodActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(CZaddmodActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public void addNota(NotasCZ notasCZ){
        serviceAPICZ= endPointsCZ.getNotasService();
        Call<NotasCZ> call=serviceAPICZ.addNotas(notasCZ);
        call.enqueue(new Callback<NotasCZ>() {
            @Override
            public void onResponse(Call<NotasCZ> call, Response<NotasCZ> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CZaddmodActivity.this,"Se agrego conéxito", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<NotasCZ> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(CZaddmodActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void updateNota(NotasCZ notasCZ,int id){
        serviceAPICZ= endPointsCZ.getNotasService();
        Call<NotasCZ>call=serviceAPICZ.updateNotas(notasCZ,id);
        call.enqueue(new Callback<NotasCZ>() {
            @Override
            public void onResponse(Call<NotasCZ> call, Response<NotasCZ> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CZaddmodActivity.this,"Se Actualizó conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<NotasCZ> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(CZaddmodActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void deleteNota(int id){
        serviceAPICZ=endPointsCZ.getNotasService();
        Call<NotasCZ>call=serviceAPICZ.deleteNotas(id);
        call.enqueue(new Callback<NotasCZ>() {
            @Override
            public void onResponse(Call<NotasCZ> call, Response<NotasCZ> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CZaddmodActivity.this, "Se Elimino el registro",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NotasCZ> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(CZaddmodActivity.this,MainActivity.class);
        startActivity(intent);
    }
}