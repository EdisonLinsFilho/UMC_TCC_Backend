package laboratorioPedag.UMC_TCC_BACKEND.material.dal;

import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findByNome(String nome);
    List<Material> findAllByClasse(Material.Classe classe);
}
