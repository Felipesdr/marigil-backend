package com.example.marigil_backend.repositorys;


import com.example.marigil_backend.domain.texto.Texto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TextoRepository extends JpaRepository<Texto, Long> {

    @Query(value = """
            select t.* from texto t 
            order by t.id_texto desc
            limit 6
            """, nativeQuery = true)
    public List<Texto> pegarSeisPrimeirosTextos();
}
