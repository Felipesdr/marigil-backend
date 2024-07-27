package com.example.marigil_backend.repositorys;


import com.example.marigil_backend.domain.texto.Texto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextoRepository extends JpaRepository<Texto, Long> {

}
