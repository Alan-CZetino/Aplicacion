package com.example.myapi_cz.APICZ;


import com.example.myapi_cz.ServiceCZ.ServiceAPICZ;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class endPointsCZ {

    public static String URL_API = "http://192.168.43.179:8080/notas/";
    public static String URL_API2 ="http://192.168.43.179:8080/notas/";
    static Retrofit retrofit;
    static  Retrofit retrofit2;

    public static Retrofit getRetrofit(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ServiceAPICZ getNotasService(){
        return  ClienteCZ.getClientCZ(URL_API).create(ServiceAPICZ.class);
    }
}
