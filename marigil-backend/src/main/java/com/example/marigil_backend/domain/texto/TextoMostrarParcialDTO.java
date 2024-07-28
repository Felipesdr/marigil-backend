package com.example.marigil_backend.domain.texto;

public record TextoMostrarParcialDTO(Long idTexto, String titulo, String subtitulo, String imagemUrl) {

    public TextoMostrarParcialDTO(Texto texto){
        this(texto.getIdTexto(), texto.getTitulo(), texto.getSubTitulo(), texto.getImgUrl());
    }
}
