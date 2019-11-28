package laboratorioPedag.UMC_TCC_BACKEND.material.dal;

import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findAllByNomeContains(String nome);

    List<Material> findAllByClasse(Material.Classe classe);

    @Query("select quantidade from material where id = :id")
    Double findQuantidadeByMaterial(@Param("id") Long id);

    @Query("select quantidade_minima from material where id = :id")
    Double findQuantidadeMinimaByMaterial(@Param("id") Long id);
}
