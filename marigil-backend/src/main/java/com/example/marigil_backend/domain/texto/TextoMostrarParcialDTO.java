package com.example.marigil_backend.domain.texto;

import com.example.marigil_backend.domain.categoria.CategoriaDetalhadoDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record TextoMostrarParcialDTO(Long idTexto, String titulo, String subtitulo, String imagemUrl, Set<CategoriaDetalhadoDTO> categorias) {

    public TextoMostrarParcialDTO(Texto texto){
        this(
                texto.getIdTexto(),
                texto.getTitulo(),
                texto.getSubTitulo(),
                texto.getImgUrl(),
                texto.getCategorias().stream().map(c-> new CategoriaDetalhadoDTO(c.getIdCategoria(), c.getNome())).collect(Collectors.toSet())
        );
    }
}
