package laboratorioPedag.UMC_TCC_BACKEND.agenda.dal;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
