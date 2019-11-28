package laboratorioPedag.UMC_TCC_BACKEND.agenda.model;

import laboratorioPedag.UMC_TCC_BACKEND.material.model.Material;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "agenda_materiais")
@Data
public class AgendaMateriais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Agenda agenda;
    @ManyToOne
    Material material;

    Double quantidadeUtilizada;
}
