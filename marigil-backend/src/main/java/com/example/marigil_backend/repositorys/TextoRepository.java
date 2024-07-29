package com.example.marigil_backend.repositorys;


import com.example.marigil_backend.domain.texto.Texto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Set;

public interface TextoRepository extends JpaRepository<Texto, Long> {

    @Query(value = """
            select t.* from texto t 
            order by t.id_texto desc
            limit 6
            """, nativeQuery = true)
    public List<Texto> pegarSeisPrimeirosTextos();

    @Query(value = """
            select t.*  from texto t
            inner join texto_categoria tc ON t.id_texto = tc.id_texto
            where tc.id_categoria in :idsCategoria
            group by t.id_texto
            order by t.id_texto
            """, nativeQuery = true)
    public Page<Texto> filtrarTextosPorCategoria(@Param("idsCategoria") List<Long> idsCategoria, Pageable pageable);
}
