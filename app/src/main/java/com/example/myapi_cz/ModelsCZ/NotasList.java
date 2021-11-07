package com.example.myapi_cz.ModelsCZ;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NotasList {

    @SerializedName("notas")
    private ArrayList<NotasCZ> notas;

    public NotasList(ArrayList<NotasCZ> notas) {
        this.notas = notas;
    }

    public ArrayList<NotasCZ> getNotas() {
        return notas;
    }
}
