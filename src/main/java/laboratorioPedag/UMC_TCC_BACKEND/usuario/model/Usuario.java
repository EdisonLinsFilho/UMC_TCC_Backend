package laboratorioPedag.UMC_TCC_BACKEND.usuario.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Usuario implements Serializable {

    public enum Status{ ACTIVE, INACTIVE }
    public enum Acesso{ COORDENADOR, PROFESSOR, MONITOR }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Acesso acesso;
    private Status status;
    private String email;
    private String senha;
    private double rgm;

}
