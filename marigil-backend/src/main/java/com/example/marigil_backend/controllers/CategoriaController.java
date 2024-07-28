package com.example.marigil_backend.controllers;

import com.example.marigil_backend.domain.categoria.Categoria;
import com.example.marigil_backend.domain.categoria.CategoriaCadastrarDTO;
import com.example.marigil_backend.domain.categoria.CategoriaDetalhadoDTO;
import com.example.marigil_backend.domain.texto.TextoDetalhadoDTO;
import com.example.marigil_backend.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @PostMapping("cadastrar")
    public ResponseEntity<CategoriaDetalhadoDTO> cadastrarCategoria (@RequestBody CategoriaCadastrarDTO dto, UriComponentsBuilder uriBuilder){

        Categoria novaCategoria = service.cadastrarCategoria(dto);
        Long idNovaCategoria = novaCategoria.getIdCategoria();

        URI uri = uriBuilder.path("api/categorias/adicionar/{id}").buildAndExpand(idNovaCategoria).toUri();

        return ResponseEntity.created(uri).body(new CategoriaDetalhadoDTO(novaCategoria.getIdCategoria(), novaCategoria.getNome()));
    }
}
