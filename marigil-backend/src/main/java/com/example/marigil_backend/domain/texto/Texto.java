package com.example.marigil_backend.domain.texto;

import com.example.marigil_backend.domain.categoria.Categoria;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

@Entity
public class Texto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTexto;
    private String titulo;
    private String subTitulo;
    private String imgUrl;
    private LocalDate dataPostagem;
    private String conteudo;
    @ManyToMany
    @JoinTable(
            name = "texto_categoria",
            joinColumns = @JoinColumn(name = "id_texto"),
            inverseJoinColumns =  @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias;

    public Texto() {
    }

    public Texto(Long idTexto, String titulo, String subTitulo, String imgUrl, LocalDate dataPostagem, String conteudo, Set<Categoria> categorias) {
        this.idTexto = idTexto;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.imgUrl = imgUrl;
        this.dataPostagem = dataPostagem;
        this.conteudo = conteudo;
        this.categorias = categorias;
    }

    public Texto(TextoCadastrarDTO dto, Set<Categoria> categorias, String imgUrl){
        this.titulo = dto.titulo();
        this.subTitulo = dto.subtitulo();
        this.imgUrl = imgUrl;
        this.dataPostagem = dto.dataPostagem();
        this.conteudo = dto.conteudo();
        this.categorias = categorias;
    }

    public Long getIdTexto() {
        return idTexto;
    }

    public void setIdTexto(Long idTexto) {
        this.idTexto = idTexto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDate getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDate dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void adicionarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Texto texto)) return false;
        return Objects.equals(getIdTexto(), texto.getIdTexto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdTexto());
    }
}
