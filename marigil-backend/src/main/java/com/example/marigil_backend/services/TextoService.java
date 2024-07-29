package com.example.marigil_backend.services;

import com.amazonaws.services.s3.AmazonS3;
import com.example.marigil_backend.domain.categoria.Categoria;
import com.example.marigil_backend.domain.texto.Texto;
import com.example.marigil_backend.domain.texto.TextoCadastrarDTO;
import com.example.marigil_backend.domain.texto.TextoDetalhadoDTO;
import com.example.marigil_backend.domain.texto.TextoMostrarParcialDTO;
import com.example.marigil_backend.repositorys.CategoriaRepository;
import com.example.marigil_backend.repositorys.TextoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TextoService {

    @Autowired
    AmazonS3 s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private TextoRepository textoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<TextoDetalhadoDTO> filtrarTextosPorCategoria(List<Long> idsCategoria, Integer pagina, Integer tamanho){
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Texto> paginaDeTextos = textoRepository.filtrarTextosPorCategoria(idsCategoria, pageable);
        return paginaDeTextos.stream().map(TextoDetalhadoDTO::new).toList();
    }

    public List<TextoDetalhadoDTO> pegarTodosOsTextos(Integer pagina, Integer tamanho){
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Texto> paginaDeTextos = textoRepository.findAll(pageable);
        return paginaDeTextos.stream().map(TextoDetalhadoDTO::new).toList();
    }

    public List<TextoMostrarParcialDTO> pegarSeisPrimeirosTextos(){
        List<Texto> seisPrimeirosTextos = textoRepository.pegarSeisPrimeirosTextos();
        return seisPrimeirosTextos.stream().map(TextoMostrarParcialDTO::new).toList();

    }

    public Texto cadastrarTexto(TextoCadastrarDTO  dto){

        String imgUrl = null;
        if(dto.imagem() !=  null){
            imgUrl = this.uploadImage(dto.imagem());
        }

        Texto novoTexto = new Texto(dto, pegarCategoriasDeUmTexto(dto), imgUrl);

        return textoRepository.save(novoTexto);
    }

    private String uploadImage(MultipartFile multipartFile) {

        String arquivoNome = UUID.randomUUID() + "-" + multipartFile.getName();

        try{
            File imagem = converterMultiPartFile(multipartFile);
            s3Client.putObject(bucketName, arquivoNome, imagem);
            imagem.delete();

            return s3Client.getUrl(bucketName, arquivoNome).toString();
        }catch (Exception e){
            System.out.println("Erro ao subir arquivo" + e.getMessage());
            return null;
        }
    }

    private File converterMultiPartFile(MultipartFile multipartFile) throws IOException {
        File arquivoCOnvertido = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(arquivoCOnvertido);
        fos.write(multipartFile.getBytes());
        fos.close();
        return arquivoCOnvertido;
    }

    private Set<Categoria> pegarCategoriasDeUmTexto(TextoCadastrarDTO dto){
        return dto.idsCategoria()
                .stream()
                .map(idCategoria-> categoriaRepository.findById(idCategoria).orElse(null))
                .collect(Collectors.toSet());
    }
}
