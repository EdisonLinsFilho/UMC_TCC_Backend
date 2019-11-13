package laboratorioPedag.UMC_TCC_BACKEND.material.model;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Material {

    public enum Classe{
        CIENCIA,
        CONSUMO,
        DESENVOLVIMENTO_COGNITIVO,
        EDUCACAO_FISICA,
        LINGUA_PORTUGUESA,
        MATEMATICA,
        NATUREZA_E_SOCIEDADE,
        OUTROS
    }

    public enum Categoria{
        CONSUMIVEL,
        DURAVEL,
        DOURADO,
        OUTROS
    }

    public enum Embalagem{
        CAIXA,
        METRO,
        PACOTE,
        UNIDADE,
        OUTROS
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;
    private String descricao;

    @NotNull
    private double quantidade;
    @NotNull
    private double quantidadeMinima;
    private Long dataLancamento;

    @Enumerated(EnumType.STRING)
    private Classe classe;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Enumerated(EnumType.STRING)
    private Embalagem embalagem;

    @Override
    public String toString() {
        return nome;
    }
}
