package com.example.objetos_perdidos;

public class ObjetoPerdido {
    private String urlImagen;
    private String descripcion;

    public ObjetoPerdido(String urlImagen, String descripcion) {
        this.urlImagen = urlImagen;
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

