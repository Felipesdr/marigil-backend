package com.example.marigil_backend.domain.texto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record TextoAtualizarDTO(Long idTexto, String titulo, String subtitulo, MultipartFile imagem, String conteudo, List<Long> idsCategoria) {
}
