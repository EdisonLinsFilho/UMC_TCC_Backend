package laboratorioPedag.UMC_TCC_BACKEND.material.dal;

import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MaterialRepository extends JpaRepository<Material, Long> {
}
