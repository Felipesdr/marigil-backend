package com.example.marigil_backend.domain.categoria;

import com.example.marigil_backend.domain.texto.Texto;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;
    private String nome;
    private boolean active;
    @ManyToMany(mappedBy = "categorias")
    private Set<Texto> textos;

    public Categoria() {
    }

    public Categoria(Long idCategoria, String nome, boolean active) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.active = active;
    }


    public Categoria(CategoriaCadastrarDTO categoriaCadastrarDTO){
        this.nome = categoriaCadastrarDTO.nome();
        this.active = true;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
