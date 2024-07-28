package com.example.marigil_backend.domain.texto;

import com.example.marigil_backend.domain.categoria.CategoriaDetalhadoDTO;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record TextoDetalhadoDTO(Long idTexto, String titulo, String subtitulo, String imagem, LocalDate dataPostagem, String conteudo, Set<CategoriaDetalhadoDTO> categorias) {

    public TextoDetalhadoDTO(Texto texto){
        this(
                texto.getIdTexto(),
                texto.getTitulo(),
                texto.getSubTitulo(),
                texto.getImgUrl(),
                texto.getDataPostagem(),
                texto.getConteudo(),
                texto.getCategorias().stream().map(c-> new CategoriaDetalhadoDTO(c.getIdCategoria(), c.getNome())).collect(Collectors.toSet())
        );
    }
}
