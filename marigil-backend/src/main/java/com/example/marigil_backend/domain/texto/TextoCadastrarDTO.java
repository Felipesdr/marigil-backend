package com.example.marigil_backend.domain.texto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record TextoCadastrarDTO(String titulo, String subtitulo, LocalDate dataPostagem, String conteudo, MultipartFile imagem) {
}
