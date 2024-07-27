package com.example.marigil_backend.domain.texto;

import java.time.LocalDate;

public record TextoDetalhadoDTO(Long idTexto, String titulo, String subtitulo, String imagem, LocalDate dataPostagem, String conteudo) {

    public TextoDetalhadoDTO(Texto texto){
        this(
                texto.getIdTexto(),
                texto.getTitulo(),
                texto.getSubTitulo(),
                texto.getImgUrl(),
                texto.getDataPostagem(),
                texto.getConteudo()
        );
    }
}
