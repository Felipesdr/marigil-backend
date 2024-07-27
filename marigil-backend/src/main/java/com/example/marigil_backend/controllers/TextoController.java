package com.example.marigil_backend.controllers;

import com.example.marigil_backend.domain.texto.Texto;
import com.example.marigil_backend.domain.texto.TextoCadastrarDTO;
import com.example.marigil_backend.domain.texto.TextoDetalhadoDTO;
import com.example.marigil_backend.repositorys.TextoRepository;
import com.example.marigil_backend.services.TextoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/textos")
public class TextoController {

    @Autowired
    TextoService service;

    @PostMapping(path = "adicionar", consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity<TextoDetalhadoDTO> adiconarTexto (@RequestParam("titulo") String titulo,
                                                            @RequestParam("subtitulo") String subtitulo,
                                                            @RequestParam("dataPostagem") LocalDate dataPostagem,
                                                            @RequestParam("conteudo") String conteudo,
                                                            @RequestParam("file") MultipartFile imagem,
                                                            UriComponentsBuilder uriBuilder){
       TextoCadastrarDTO dto = new TextoCadastrarDTO(titulo, subtitulo, dataPostagem, conteudo, imagem);
       Texto novoTexto = service.cadastrarTexto(dto);
       Long idNovoTexto = novoTexto.getIdTexto();

       URI uri = uriBuilder.path("textos/adicionar/{id}").buildAndExpand(idNovoTexto).toUri();

       return ResponseEntity.created(uri).body(new TextoDetalhadoDTO(novoTexto));
    }
}