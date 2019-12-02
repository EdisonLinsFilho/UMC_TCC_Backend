package laboratorioPedag.UMC_TCC_BACKEND.agenda.model;

import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name ="agenda_materiais")
public class AgendaMateriais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Agenda agenda;

    @ManyToMany
    private List<Material> materiais;

    private Double quantidadeUtilizada;
}
