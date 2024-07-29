package com.example.marigil_backend.controllers;

import com.example.marigil_backend.domain.texto.*;
import com.example.marigil_backend.repositorys.TextoRepository;
import com.example.marigil_backend.services.TextoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/textos")
public class TextoController {

    @Autowired
    TextoService service;

    @GetMapping("/{idTexto}")
    public ResponseEntity<TextoDetalhadoDTO> buscarPorId(@PathVariable Long idTexto){
        return ResponseEntity.ok(service.buscarTextoPorId(idTexto));
    }
    @GetMapping("/filtrar")
    public ResponseEntity<List<TextoDetalhadoDTO>> filtrarTextosPorCategoria(@RequestParam List<Long> idsCategoria,
                                                                             @RequestParam Integer pagina,
                                                                             @RequestParam Integer tamanho){
        return ResponseEntity.ok(service.filtrarTextosPorCategoria(idsCategoria, pagina, tamanho));
    }
    @GetMapping
    public ResponseEntity<List<TextoDetalhadoDTO>> pegarTodosOsTextos(@RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "5") Integer tamanho){
        return ResponseEntity.ok(service.pegarTodosOsTextos(pagina, tamanho));
    }

    @GetMapping("/paginaInicial")
    public ResponseEntity<List<TextoMostrarParcialDTO>> pegarSeisPrimeirosTextos(){
        return ResponseEntity.ok(service.pegarSeisPrimeirosTextos());
    }

    @PostMapping(path = "/adicionar", consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity<TextoDetalhadoDTO> adiconarTexto (@RequestParam("titulo") String titulo,
                                                            @RequestParam("subtitulo") String subtitulo,
                                                            @RequestParam("dataPostagem") LocalDate dataPostagem,
                                                            @RequestParam("conteudo") String conteudo,
                                                            @RequestParam("file") MultipartFile imagem,
                                                            @RequestParam("idsCategoria[]") List<Long> idsCategoria,
                                                            UriComponentsBuilder uriBuilder){
       TextoCadastrarDTO dto = new TextoCadastrarDTO(titulo, subtitulo, dataPostagem, conteudo, imagem, new HashSet<>(idsCategoria));
       Texto novoTexto = service.cadastrarTexto(dto);
       Long idNovoTexto = novoTexto.getIdTexto();

       URI uri = uriBuilder.path("/api/textos/adicionar/{id}").buildAndExpand(idNovoTexto).toUri();

       return ResponseEntity.created(uri).body(new TextoDetalhadoDTO(novoTexto));
    }

    @PutMapping("/atualizar/{idTexto}")
    @Transactional
    public ResponseEntity<TextoDetalhadoDTO> atualizarTexto(@PathVariable Long idTexto,
                                                            @RequestParam(name = "titulo", required = false) String titulo,
                                                            @RequestParam(name = "subtitulo", required = false) String subtitulo,
                                                            @RequestParam(name = "conteudo", required = false) String conteudo,
                                                            @RequestParam(name = "file", required = false) MultipartFile imagem,
                                                            @RequestParam(name = "idsCategoria[]", required = false) List<Long> idsCategoria) {

        TextoAtualizarDTO dto = new TextoAtualizarDTO(idTexto, titulo, subtitulo, imagem, conteudo, idsCategoria);
        return ResponseEntity.ok(new TextoDetalhadoDTO(service.atualizarTexto(dto)));
    }

    @DeleteMapping("desativar/{idTexto}")
    @Transactional
    public ResponseEntity desativarTexto(@PathVariable Long idTexto) {
        service.desativarTexto(idTexto);
        return ResponseEntity.noContent().build();
    }
}
