package com.example.myapi_cz.ServiceCZ;

import com.example.myapi_cz.ModelsCZ.NotasCZ;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceAPICZ {

    @GET("listar")
    Call<List<NotasCZ>>getNotasList();

    @POST("agregar")
    Call<NotasCZ>addNotas(@Body NotasCZ persona);

    @POST("actualizar/{id}")
    Call<NotasCZ>updateNotas(@Body NotasCZ persona,@Path("id") int id);

    @POST("eliminar/{id}")
    Call<NotasCZ>deleteNotas(@Path("id")int id);

}
