package laboratorioPedag.UMC_TCC_BACKEND.agenda.dal;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.AgendaMateriais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaMaterialRepository extends JpaRepository<AgendaMateriais, Long> {

}
