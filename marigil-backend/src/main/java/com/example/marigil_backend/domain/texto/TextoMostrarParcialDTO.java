package com.example.marigil_backend.domain.texto;

public record TextoMostrarParcialDTO(String titulo, String subtitulo, String imagemUrl) {

    public TextoMostrarParcialDTO(Texto texto){
        this(texto.getTitulo(), texto.getSubTitulo(), texto.getImgUrl());
    }
}
