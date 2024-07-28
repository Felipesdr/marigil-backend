package com.example.marigil_backend.repositorys;

import com.example.marigil_backend.domain.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
