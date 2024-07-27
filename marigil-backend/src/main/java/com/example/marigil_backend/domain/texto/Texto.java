package com.example.marigil_backend.domain.texto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public Texto() {
    }

    public Texto(Long idTexto, String titulo, String subTitulo, String imgUrl, LocalDate dataPostagem, String conteudo) {
        this.idTexto = idTexto;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.imgUrl = imgUrl;
        this.dataPostagem = dataPostagem;
        this.conteudo = conteudo;
    }

    public Texto(TextoCadastrarDTO dto, String imgUrl){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.titulo = dto.titulo();
        this.subTitulo = dto.subtitulo();
        this.imgUrl = imgUrl;
        this.dataPostagem = dto.dataPostagem();
        this.conteudo = dto.conteudo();
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
}
