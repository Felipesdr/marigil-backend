package com.example.marigil_backend.domain.texto;

import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;

public record TextoCadastrarDTO(String titulo,
                                String subtitulo,
                                LocalDate dataPostagem,

                                String conteudo,
                                MultipartFile imagem,
                                Set<Long> idsCategoria) {
}
