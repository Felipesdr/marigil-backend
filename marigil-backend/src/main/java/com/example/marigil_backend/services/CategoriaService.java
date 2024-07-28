package com.example.marigil_backend.services;

import com.example.marigil_backend.domain.categoria.Categoria;
import com.example.marigil_backend.domain.categoria.CategoriaCadastrarDTO;
import com.example.marigil_backend.repositorys.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria cadastrarCategoria(CategoriaCadastrarDTO dto){
        return new Categoria();
    }
}
