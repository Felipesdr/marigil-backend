package com.example.marigil_backend.services;

import com.amazonaws.services.s3.AmazonS3;
import com.example.marigil_backend.domain.texto.Texto;
import com.example.marigil_backend.domain.texto.TextoCadastrarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class TextoService {

    @Autowired
    AmazonS3 s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public Texto cadastrarTexto(TextoCadastrarDTO  data){

        String imgUrl = null;

        if(data.imagem() !=  null){
            imgUrl = this.uploadImage(data.imagem());
        }

        return new Texto(data, imgUrl);
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
}
