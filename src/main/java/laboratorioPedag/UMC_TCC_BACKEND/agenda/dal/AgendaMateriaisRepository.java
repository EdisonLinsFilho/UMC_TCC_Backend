package laboratorioPedag.UMC_TCC_BACKEND.agenda.dal;

import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.Agenda;
import laboratorioPedag.UMC_TCC_BACKEND.agenda.model.AgendaMateriais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgendaMateriaisRepository extends JpaRepository<AgendaMateriais, Long> {

    @Query(value = "select * from agenda_materiais where agenda_id = :agendaId and materiais_id = :materialId", nativeQuery = true)
    AgendaMateriais findByAgendaAndMaterial(@Param("materialId") Long materialId, @Param("agendaId") Long agendaId);

}
